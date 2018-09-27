package com.starbucks.admin.service.impl;

import com.starbucks.admin.dao.TbUserDao;
import com.starbucks.admin.service.TbUserService;
import com.starbucks.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public void add(TbUser tbUser) {
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserDao.add(tbUser);
    }

    @Override
    public TbUser getById(long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void delete(TbUser tbUser) {
        tbUserDao.delete(tbUser);
    }

    @Override
    public void updateInfo(TbUser tbUser) {
        tbUserDao.updateInfo(tbUser);
    }

    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    @Override
    public int batchDelete(String[] ids) {
        return tbUserDao.batchDelete(ids);
    }
}
