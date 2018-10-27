package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    // 注入service
    @Autowired
    private IPermissionService permissionService;

    /**
     * 1.分页查询
     * 注意：当前页默认是1、也大小默认2
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "2") int pageSize){
        //1.调用service
        PageInfo<Permission> pageInfo = permissionService.findByPage(pageNum, pageSize);

        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("permission-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 2.保存产品
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        //2.1 调用service保存产品
        permissionService.save(permission);
        //2.2 重定向到列表
        return "redirect:/permission/findByPage";
    }
}













