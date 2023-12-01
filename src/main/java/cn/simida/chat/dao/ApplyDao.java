package cn.simida.chat.dao;


import cn.simida.chat.pojo.entity.Apply;
import cn.simida.common.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyDao {
    int add(@Param("apply") Apply apply);

    List<User> getApply(String uid);
}
