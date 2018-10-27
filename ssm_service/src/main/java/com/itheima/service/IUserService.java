package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{

    /**
     * 分页查询用户
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
    PageInfo<SysUser> findByPage(int pageNum, int pageSize);

    /**
     * 添加用户
     */
    void save(SysUser sysUser);

    /**
     * 根据用户id查询（用户、角色、权限）
     * @param id
     * @return
     */
    SysUser findById(int id);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(int userId, int[] roleIds);
}













