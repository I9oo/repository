package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IOrderDao;
import com.itheima.dao.IProductDao;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.IOrderService;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

// 对象加入容器
@Service
// 开启声明式事务的注解
@Transactional
public class OrderServiceImpl implements IOrderService {
    // 注入dao
    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public PageInfo<Order> findByPage(int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        // 紧跟在PageHelper.startPage()其后的第一条查询将会被自动分页（拦截器拼接的sql）
        PageHelper.startPage(pageNum,pageSize);
        // 调用dao查询
        List<Order> list = orderDao.findAll();
        // 分页参数
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
