package cn.simida.common.dao.impl;

import cn.simida.common.dao.UserDao;
import cn.simida.common.mapper.UserMapper;
import cn.simida.common.pojo.User;
import cn.simida.utils.TimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/17 15:33
 */

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_name", username);
        return userMapper.selectOne(qw);
    }

    @Override
    public User addUser(User user) {
        int insert = userMapper.insert(user);
        if (insert > 0) {
            String id = user.getUserId();
            user.setUserId(id);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public User getById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public boolean update(User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        return userMapper.update(user, uw) > 0;
    }

    @Override
    public boolean updatePasswd(String newPasswd, User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        user.setPassword(newPasswd);
        return userMapper.update(user, uw) > 0;
    }

    @Override
    public boolean delete(String userId) {
        return userMapper.deleteById(userId) > 0;
    }

    @Override
    public boolean changeBg(String url, User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        user.setBgImageUrl(url);
        return userMapper.update(user, uw) > 0;
    }

    @Override
    public List<User> searchUser(String nickNameLike) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like("nick_name", nickNameLike);
        return userMapper.selectList(qw);
    }

    @Override
    public boolean changeUserStatus(String userId) {
        // 将用户id为userId的用户的new_user字段设置为0
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        String nowTime = TimeUtils.getNowTime();
        uw.eq("user_id", userId);
        User user = new User();
        user.setNewUser(0);
        user.setUpdateTime(nowTime);
        return userMapper.update(user, uw) > 0;
    }

}
