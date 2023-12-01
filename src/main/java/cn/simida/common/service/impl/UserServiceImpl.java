package cn.simida.common.service.impl;

import cn.simida.common.dao.UserDao;
import cn.simida.common.pojo.User;
import cn.simida.common.service.UserService;
import cn.simida.socialFeed.dao.FollowDao;
import cn.simida.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private FollowDao followDao;

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }

    @Caching(
            evict = @CacheEvict(value = "user", key = "'allUser'")
    )
    @Override
    public User addUser(User user) {
        String nowTime = TimeUtils.getNowTime();
        user.setPw(user.getPassword());
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        String password = user.getPassword() + "cikian";
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5Password);
        return userDao.addUser(user);
    }

    @Override
    public Result login(String userName, String password) {
        Result result = new Result();
        User user = this.getUserByUserName(userName);
        if (user == null) {
            result.setCode(ErrorCode.LOGIN_FAIL);
            result.setData("/login");
            result.setMsg("用户名不存在");
        } else {
            boolean flag = UserUtils.checkPassword(password, user.getPassword());
            if (flag) {
                result.setCode(ErrorCode.LOGIN_SUCCESS);
                result.setData(user);
            } else {
                result.setCode(ErrorCode.LOGIN_FAIL);
                result.setData("/login");
                result.setMsg("密码错误");
            }
        }
        return result;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "user", key = "'allUser'"),
                    @CacheEvict(value = "user", key = "#user.userId")
            }
    )
    @Override
    public String update(User user) {
        String nowTime = TimeUtils.getNowTime();
        user.setUpdateTime(nowTime);
        boolean flag = userDao.update(user);
        String token = null;
        if (flag){
            token = JWTUtils.createJWT(user, "update", null);
        }
        return token;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "user", key = "#userId"),
                    @CacheEvict(value = "user", key = "'allUser'")
            }
    )
    @Override
    public Result delete(String userId, String password) {
        User user = this.getById(userId);
        if (!UserUtils.checkPassword(password, user.getPassword())) {
            return new Result(ErrorCode.DELETE_FAIL, null, "密码错误");
        }
        boolean flag = userDao.delete(userId);
        String msg = flag ? "删除成功" : "删除失败";
        String url = flag ? "/user/login" : "";
        return new Result(flag ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL, url, msg);
    }

    @Override
    public boolean updatePasswd(String newPasswd, User user) {
        user.setPw(newPasswd);
        String nowTime = TimeUtils.getNowTime();
        newPasswd += "cikian";
        String md5NewPasswd = DigestUtils.md5DigestAsHex(newPasswd.getBytes(StandardCharsets.UTF_8));
        user.setUpdateTime(nowTime);
        return userDao.updatePasswd(md5NewPasswd, user);
    }

    @Override
    public boolean checkPasswd(String userId, String inputPasswd) {
        User user = userDao.getById(userId);
        inputPasswd += "cikian";
        String md5InputPasswd = DigestUtils.md5DigestAsHex(inputPasswd.getBytes(StandardCharsets.UTF_8));
        return user.getPassword().equals(md5InputPasswd);
    }

    @Caching(
            put = @CachePut(value = "user", key = "#user.userId"),
            evict = {
                    @CacheEvict(value = "user", key = "'allUser'"),
                    @CacheEvict(value = "user", key = "#user.userId")
            }
    )
    @Override
    public String changeBg(String url, User user) {
        boolean flag = false;
        String token = null;
        if (url != null) {
            String nowTime = TimeUtils.getNowTime();
            user.setBgImageUrl(url);
            user.setUpdateTime(nowTime);
            flag = userDao.update(user);
            if (flag) {
                token = JWTUtils.createJWT(user, "login", null);
            }
        }
        return token;
    }

    @Override
    public List<User> getRecommendUser(String userId) {
        List<String> userIds = followDao.getRecommendUserId(userId);
        List<User> users = new ArrayList<>();
        if (userIds != null && !userIds.isEmpty()) {
            for (String id : userIds) {
                User user = userDao.getById(id);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public List<User> searchUser(String nickNameLike) {
        // 模糊查询
        List<User> users = userDao.searchUser(nickNameLike);
        if (users != null && !users.isEmpty()) {
            return users;
        }
        return null;
    }

    @Override
    public boolean changeUserStatus(String userId) {
        return userDao.changeUserStatus(userId);
    }

    @Cacheable(value = "user", key = "#userId")
    @Override
    public User getById(String userId) {
        return userDao.getById(userId);
    }

    @Cacheable(value = "user", key = "'allUser'")
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
