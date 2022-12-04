package com.wu.web.servlets;

import com.alibaba.fastjson.JSON;
import com.wu.pojo.Brand;
import com.wu.pojo.PageBean;
import com.wu.sercive.BrandService;
import com.wu.sercive.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 执行所有和brand 相关的操作
 * 所有和 brand 有关的请求都要在前面加上 brand
 * 每一个 pojo 只有一个 servlet
 */


@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 JSON 数据
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine();//JSON 数据

        //2. 转化为Brand 类型
        Brand brand = JSON.parseObject(params, Brand.class);

        //3, 执行插入
        brandService.insert(brand);

        //4
        response.getWriter().write("successfully");

    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllServlet");
        //查询数据库获取数据
        List<Brand> brands = brandService.selectAll();
        System.out.println(brands);
        //转化为 Json 文件
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);//返回给浏览器
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 JSON 数据[1,2,3]
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine();//JSON 数据

        //2. 转化数类型
        int[] ids = JSON.parseObject(params, int[].class);

        //3,
        brandService.deleteByIds(ids);

        //4
        response.getWriter().write("successfully");

    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        brandService.deleteById(id);

        response.getWriter().write("successfully");

    }

    //更新品牌
    public void updateBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 JSON 数据
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine();//JSON 数据

        //2. 转化数类型
        Brand brand = JSON.parseObject(params, Brand.class);

        brandService.updateBrand(brand);


        response.getWriter().write("successfully");
    }

    //分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    //分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        //1. 接收 查询 JSON 数据
        BufferedReader bufferedReader = request.getReader();
        String params = bufferedReader.readLine();//JSON 数据

        //2. 转化数类型
        Brand brand = JSON.parseObject(params, Brand.class);


        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        //3. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //4. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }


}
