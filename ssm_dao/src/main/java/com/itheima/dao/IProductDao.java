package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Repository // 可选dao的接口交给spring包扫描器扫描，从而创建代理对象
public interface IProductDao {

    /**
     * 查询全部
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 添加
     */
    @Insert("insert into product values(seq_product.nextval,#{productNum},#{productName},\n" +
            "#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 主键查询
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(int id);

    /**
     * 修改
     * @param product
     */
    @Update("update product set productNum=#{productNum},productName=#{productName}," +
            "cityName=#{cityName},departureTime=#{departureTime}," +
            "productPrice=#{productPrice},productDesc=#{productDesc}," +
            "productStatus=#{productStatus} where id=#{id}")
    void update(Product product);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from product where id=#{id}")
    void delete(String id);

    /**
     * 产品分页（手动分页）
     * 注意：如果方法参数有多个，可以通过@Param建立形参与占位符参数的映射关系。
     */
    @Select("select t.* from (" +
            "select p.*,rownum rn from product p where rownum<=#{start}" +
            ") t where rn>#{end}")
    List<Product> findByPage(@Param("start") int start, @Param("end") int end);
    /**
     * 查询总记录数
     */
    @Select("select count(1) from product")
    long count();
}
