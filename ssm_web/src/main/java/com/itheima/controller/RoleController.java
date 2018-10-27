package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IRoleService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

/**
 * 方式1：jsr-250
 * @RolesAllowed("ROLE_ADMIN")
 * 访问当然控制器的所有方法，必须有角色：ROLE_ADMIN
 *
 * 方式2:security框架提供的注解支持
 * @Secured("ROLE_ADMIN")
 *
 * 方式3：spel表达式
 * @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
 */
@Controller
@RequestMapping("/role")
//@RolesAllowed("ROLE_ADMIN")
//@Secured("ROLE_ADMIN")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {
    // 注入service
    @Autowired
    private IRoleService roleService;

    /**
     * 1.分页查询
     * 注意：当前页默认是1、也大小默认2
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "2") int pageSize){

        /**
         * 测试：登陆后获取用户信息
         */
        //1. 获取绑定到当前线程上的SecurityContext对象
        SecurityContext context = SecurityContextHolder.getContext();
        //2. 获取认证器对象
        Authentication authentication = context.getAuthentication();
        //3. 获取认证的身份对象
        User user = (User) authentication.getPrincipal();
        //4. 获取用户名
        System.out.println(user.getUsername());

        //1.调用service
        PageInfo<Role> pageInfo = roleService.findByPage(pageNum, pageSize);

        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 2.保存产品
     */
    @RequestMapping("/save")
    public String save(Role role){
        //2.1 调用service保存产品
        roleService.save(role);
        //2.2 重定向到列表
        return "redirect:/role/findByPage";
    }
}













