package com.starbucks.api.service;

import com.starbucks.domain.TbUser;

public interface TbUserService {

    /**
     * 根据用户名或者邮箱查找用户信息
     * @param str
     * @return
     */
    TbUser get(String str);

    /**
     * 添加
     *
     */
    void add(TbUser tbUser);
}
