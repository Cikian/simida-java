package cn.simida.common.service;

import cn.simida.common.pojo.User;
import cn.simida.utils.Result;

import java.util.List;

public interface UserService {
    User getUserByUserName(String username);

    List<User> getAllUser();

    User getById(String userId);

    User addUser(User user);

    Result login(String userName, String password);

    String update(User user);

    Result delete(String userId, String password);

    boolean updatePasswd(String newPasswd,User user);

    boolean checkPasswd(String userId, String inputPasswd);

    String changeBg(String url, User user);

    List<User> getRecommendUser(String userId);

    List<User> searchUser(String nickNameLike);

    boolean changeUserStatus(String userId);

}
