package com.starbucks.api.dao;

import com.starbucks.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao {

    /**
     * 根据email获取用户信息
     * @param email
     * @return
     */
   TbUser getByEmail(String email);

    /**
     * 根据email获取用户信息
     * @param username
     * @return
     */
    TbUser getByUsername(String username);


    /**
     * 添加
     *
     */
    void add(TbUser tbUser);
}
