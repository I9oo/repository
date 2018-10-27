package com.itheima.dao;

import com.itheima.domain.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderDao {
    /**
     * 查询订单
     */
    @Select("select " +
            "  o.*,p.id pid,p.productnum,p.productname,p.departuretime," +
            "  p.cityName,p.productprice,p.productdesc,p.productstatus " +
            "from orders o inner join product p on o.productid=p.id")
    @Results({
            // 封装订单的主键
            @Result(property = "id",column = "id"),
            // 封装产品信息
            @Result(property = "product.id",column = "pid"),
            @Result(property = "product.productNum",column = "productNum"),
            @Result(property = "product.productName",column = "productName"),
            @Result(property = "product.departureTime",column = "departureTime"),
            @Result(property = "product.cityName",column = "cityName"),
            @Result(property = "product.productPrice",column = "productPrice"),
            @Result(property = "product.productDesc",column = "productDesc"),
            @Result(property = "product.productStatus",column = "productStatus")
    })
    List<Order> findAll();
}
