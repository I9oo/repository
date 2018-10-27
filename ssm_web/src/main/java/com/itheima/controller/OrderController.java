package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.IOrderService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    // 注入service
    @Autowired
    private IOrderService orderService;

    /**
     * 0.分页查询
     * 注意：当前页默认是1、也大小默认2
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "2") int pageSize){
        //1.调用service
        PageInfo<Order> pageInfo = orderService.findByPage(pageNum, pageSize);

        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    /**
     * 1.查询
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //1.调用service
        List<Order> list = orderService.findAll();
        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order-list");
        mv.addObject("list",list);
        return mv;
    }

    /**
     * 导出excel
     */
    @RequestMapping("/exportOrder")
    public void exportOrder(HttpServletResponse response) throws IOException {
        //1. 在内存中构建一个excel
        //1.1 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //1.2 创建工作表
        Sheet sheet = workbook.createSheet("订单列表");
        sheet.setColumnWidth(0,10*256);
        sheet.setColumnWidth(1,10*256);
        sheet.setColumnWidth(2,10*256);
        sheet.setColumnWidth(3,50*256);
        //1.3 创建第一行
        String[] titles = {"订单编号","出行人数","产品编号","产品名称"};
        Row row = sheet.createRow(0);
        Cell cell = null;
        for(int i=0;i<titles.length;i++){
            // 创建第一行的每一列
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //1.4 调用service查询，再创建数据行
        List<Order> list = orderService.findAll();
        for(int i=0; i<list.size();i++){
            // 获取订单，一个订单对象导出一行
            Order order = list.get(i);
            // 创建行
            row = sheet.createRow(i+1);
            // 创建单元格
            row.createCell(0).setCellValue(order.getOrderNum());
            row.createCell(1).setCellValue(order.getPeopleCount());
            row.createCell(2).setCellValue(order.getProduct().getProductNum());
            row.createCell(3).setCellValue(order.getProduct().getProductName());
        }

        //2. 把excel写到response输出流实现下载
        //2.1 响应体编码
        response.setCharacterEncoding("UTF-8");
        //2.2 下载响应头
        response.setHeader("content-disposition","attachment;fileName=order.xlsx");
        //2.3 输出流
        ServletOutputStream out = response.getOutputStream();
        //2.4 把excel文件流写入到response输出流
        workbook.write(out);
        out.close();
        workbook.close();
    }

}
