package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IRoleService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    // 注入service
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * 1.分页查询
     * 注意：当前页默认是1、也大小默认2
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "2") int pageSize) {
        //1.调用service
        PageInfo<SysUser> pageInfo = userService.findByPage(pageNum, pageSize);

        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-list");
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }

    /**
     * 2.保存产品
     */
    @RequestMapping("/save")
    public String save(SysUser sysUser) {
        //2.1 调用service保存产品
        userService.save(sysUser);
        //2.2 重定向到列表
        return "redirect:/user/findByPage";
    }

    /**
     * 3.用户详情
     */
    @RequestMapping("/findById")
    public ModelAndView findById(int id) {
        //根据用户id查询
        SysUser sysUser = userService.findById(id);
        //返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-show");
        mv.addObject("user",sysUser);
        return mv;
    }

    /**
     * 4.进入用户角色页面(user-role-add.jsp)
     */
    @RequestMapping("/toUserRole")
    public ModelAndView toUserRole(int id){
        //4.1 查询用户
        SysUser user = userService.findById(id);

        //4.2 获取用户角色
        List<Role> roles = user.getRoles();
        //4.2.1 保存用户的所有角色
        String roleStr = "";
        if (roles != null && roles.size()>0) {
            for (Role r : roles) {
                roleStr += r.getRoleName() + ",";
            }
        }
        //4.3 查询所有角色
        List<Role> roleList = roleService.findAll();

        //4.4 返回结果
        ModelAndView modelAndView = new ModelAndView();
        // 跳转的页面
        modelAndView.setViewName("user-role-add");
        // 保存查询的数据
        modelAndView.addObject("user",user);
        modelAndView.addObject("roleStr",roleStr);
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }

    /**
     * 5.保存用户角色
     */
    //5.1.	接收页面提交的：用户id、选中的多个角色id
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(int userId,int[] ids){
        //5.2.调用service给用户添加角色（或者删除用户角色）
        userService.addRoleToUser(userId,ids);
        //5.3.操作成功，跳转到用户列表
        return "redirect:/user/findByPage";
    }
}













