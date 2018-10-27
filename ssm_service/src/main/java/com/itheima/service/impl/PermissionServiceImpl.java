package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService{

    // 注入dao
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public PageInfo<Permission> findByPage(int pageNum, int pageSize) {
        // 分页
        PageHelper.startPage(pageNum,pageSize);
        // 查询
        return new PageInfo<>(permissionDao.findAll());
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
