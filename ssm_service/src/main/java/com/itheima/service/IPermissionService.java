package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;

public interface IPermissionService {

    /**
     * 分页查询
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
    PageInfo<Permission> findByPage(int pageNum, int pageSize);

    /**
     * 添加
     */
    void save(Permission permission);
}
