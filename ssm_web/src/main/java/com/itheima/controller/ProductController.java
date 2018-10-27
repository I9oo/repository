package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    // 注入service
    @Autowired
    private IProductService productService;

    /**
     * 0.分页查询
     * 注意：当前页默认是1、也大小默认2
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "2") int pageSize){
        //1.调用service
        PageBean<Product> pb = productService.findByPage(pageNum, pageSize);

        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-list");
        mv.addObject("pb",pb);
        return mv;
    }

    /**
     * 1.查询所有产品
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //1.调用service
        List<Product> list = productService.findAll();
        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-list");
        mv.addObject("list",list);
        return mv;
    }
    /**
     * 2.保存产品
     */
    @RequestMapping("/save")
    public String save(Product product){
        //2.1 调用service保存产品
        productService.save(product);
        //2.2 重定向到列表
        return "redirect:/product/findAll";
    }

    /**
     * 3.进入修改页面
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(int id){
        //3.1根据id查询
        Product product = productService.findById(id);
        //3.2返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-update");
        mv.addObject("product",product);
        return mv;
    }

    /**
     * 4.修改
     */
    @RequestMapping("/update")
    public String update(Product product){
        //4.1 调用service
        productService.update(product);
        //4.2 重定向到列表
        return "redirect:/product/findAll";
    }
    /**
     * 5.删除
     */
    @RequestMapping("/delete")
    public String delete(String ids){
        //5.1 调用service
        productService.delete(ids);
        //5.2 重定向到列表
        return "redirect:/product/findAll";
    }
}
