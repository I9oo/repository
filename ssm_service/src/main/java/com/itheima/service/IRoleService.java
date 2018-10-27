package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IRoleService{

    /**
     * 分页查询用户
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
    PageInfo<Role> findByPage(int pageNum, int pageSize);

    /**
     * 添加用户
     */
    void save(Role role);

    List<Role> findAll();
}
