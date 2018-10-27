package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询全部
     */
    List<Product> findAll();

    /**
     * 保存
     * @param product
     */
    void save(Product product);

    /**
     * 主键查询
     * @param id
     * @return
     */
    Product findById(int id);

    /**
     * 修改
     * @param product
     */
    void update(Product product);

    /**
     * 根据多个id查询
     * @param ids 多个id用逗号隔开
     */
    void delete(String ids);

    /**
     * 分页查询
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
    PageBean<Product> findByPage(int pageNum, int pageSize);
}
