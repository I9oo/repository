package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderService {
    /**
     * 查询订单
     */
    List<Order> findAll();

    /**
     * 分页查询
     */
    PageInfo<Order> findByPage(int pageNum, int pageSize);
}
