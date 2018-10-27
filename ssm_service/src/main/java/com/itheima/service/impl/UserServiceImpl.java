package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    // 注入dao
    @Autowired
    private IUserDao userDao;
    // 注入Spring提供的加密类
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名查询
        List<SysUser> list = userDao.findByUsername(username);
        if (list == null || list.size() == 0) {
            return null;
        }
        //2.获取登陆用户
        SysUser sysUser = list.get(0);

        // 获取用户的角色信息
        List<Role> roles = sysUser.getRoles();

        //3.返回信息：用户名、密码、用户角色
        //3.1 封装用户角色（先写死，后期改为数据库查询）
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 遍历角色
        for (Role r : roles) {
            // 动态添加用户的角色
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));

        }
        //3.2 返回
        User user = new User(username, sysUser.getPassword(), authorities);
        return user;
    }

    @Override
    public PageInfo<SysUser> findByPage(int pageNum, int pageSize) {
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 查询用户
        List<SysUser> list = userDao.findAll();
        // 返回结果
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void save(SysUser sysUser) {
        // 密码加密
        String pwd = passwordEncoder.encode(sysUser.getPassword());
        // 设置到用户对象中
        sysUser.setPassword(pwd);
        // 调用dao
        userDao.save(sysUser);
    }

    @Override
    public SysUser findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(int userId, int[] roleIds) {
        //先解除关系，删除中间表数据:
        userDao.deleteUserRole(userId);
        //再往中间表添加数据，即给用户添加角色关系维护:
        if(roleIds != null && roleIds.length>0) {
            for (int roleId : roleIds) {
                userDao.saveUserRole(userId, roleId);
            }
        }
    }
}
















