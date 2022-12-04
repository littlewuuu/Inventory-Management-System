package com.wu.sercive;

import com.wu.pojo.Brand;
import com.wu.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     */
    void insert(Brand brand);

    void deleteByIds(int[] ids);

    void deleteById(int id);

    void updateBrand(Brand brand);

    //实现分页查询
    PageBean<Brand> selectByPage(int curPage, int pageSize);

    //分页条件查询
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

}
