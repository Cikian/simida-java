package cn.simida.chat.dao.impl;


import cn.simida.chat.dao.ApplyDao;
import cn.simida.chat.mapper.ApplyMapper;
import cn.simida.chat.pojo.entity.Apply;
import cn.simida.common.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/17 15:55
 */
@Repository
public class ApplyDaoImpl implements ApplyDao {
    @Autowired
    ApplyMapper applyMapper;

    @Override
    public int add(Apply apply) {
        return applyMapper.insert(apply);
    }

    @Override
    public List<User> getApply(String uid) {
        return applyMapper.getApply(uid);
    }
}
