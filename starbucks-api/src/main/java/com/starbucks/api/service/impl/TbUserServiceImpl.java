package com.starbucks.api.service.impl;

import com.starbucks.api.dao.TbUserDao;
import com.starbucks.api.service.TbUserService;
import com.starbucks.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public TbUser get(String str) {
        if (str.indexOf("@") == -1) {
            return tbUserDao.getByUsername(str);
        }

        else {
            return tbUserDao.getByEmail(str);
        }

    }

    @Override
    public void add(TbUser tbUser) {
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserDao.add(tbUser);
    }
}
