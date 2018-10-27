package com.itheima.service.impl;

import com.itheima.dao.IProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

// 对象加入容器
@Service
// 开启声明式事务的注解
@Transactional()
public class ProductServiceImpl implements IProductService {
    // 注入dao
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(String ids) {
        if (!StringUtils.isEmpty(ids)){
            String[] array = ids.split(",");
            for(String id : array){
                // 调用dao删除
                productDao.delete(id);
            }
        }
    }

    @Override
    public PageBean<Product> findByPage(int pageNum, int pageSize) {
        if (pageNum<1){
            pageNum = 1;
        }
        //1.分页查询
        List<Product> list =
                productDao.findByPage(pageNum*pageSize,(pageNum-1)*pageSize);
        //2.查询总记录数
        long count = productDao.count();
        // 返回对象
        PageBean<Product> pb = new PageBean<>();
        pb.setList(list);
        pb.setTotalCount((int) count);
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);
        return pb;
    }
}



















