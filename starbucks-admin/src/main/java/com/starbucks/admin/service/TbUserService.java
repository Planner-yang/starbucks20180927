package com.starbucks.admin.service;

import com.starbucks.domain.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 高级搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

    /**
     * 查找全部
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 添加
     *
     */
    void add(TbUser tbUser);

    /**
     * 获取单个信息
     * @param id
     * @return
     */
    TbUser getById(long id);

    /**
     * 删除单个信息
     * @param tbUser
     */
    void delete(TbUser tbUser);

    /**
     * 更新单个信息
     * @param tbUser
     */
    void updateInfo(TbUser tbUser);

    /**
     * 获取总数
     * @param tbUser
     * @return
     */
    int count(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     */
    int batchDelete(String[] ids);
}
