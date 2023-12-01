
package cn.simida.common.dao;


import cn.simida.common.pojo.User;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String username);
    User addUser(User user);
    List<User> getAllUser();
    User getById(String userId);
    boolean update(User user);
    boolean updatePasswd(String newPasswd,User user);
    boolean delete(String userId);
    boolean changeBg(String url, User user);
    List<User> searchUser(String nickNameLike);
    boolean changeUserStatus(String userId);

}
