package com.wu.sercive.impl;

import com.wu.pojo.Brand;
import com.wu.mapper.BrandMapper;
import com.wu.pojo.PageBean;
import com.wu.sercive.BrandService;
import com.wu.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        BrandMapper brandMapper =  session.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        session.close();
        return brands;
    }

    public void insert(Brand brand) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.Insert(brand);
        session.commit();
        session.close();
    }

    public void deleteByIds(int[] ids) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        session.commit();
        session.close();
    }

    public void deleteById(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.deleteById(id);
        session.commit();
        session.close();
    }

    public void updateBrand(Brand brand) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.updateBrand(brand);
        session.commit();
        session.close();
    }

    public PageBean<Brand> selectByPage(int curPage, int pageSize) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        int begin =( curPage-1)*pageSize;
        //????????????????????????
        List<Brand> brands = brandMapper.selectByPage(begin, pageSize);
        //???????????????
        int totalCount = brandMapper.selectTotalCount();

        //????????????
        PageBean<Brand> brandPageBean = new PageBean<Brand>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(totalCount);


        session.commit();
        //????????????
        session.close();
        return brandPageBean;

    }

    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession session = sqlSessionFactory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);

        int begin =( currentPage-1)*pageSize;

        // ??????brand????????????????????????
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        //????????????????????????
        List<Brand> brands = brandMapper.selectByPageAndCondition(begin, pageSize, brand);
        //???????????????
        int totalCount = brandMapper.selectTotalCountByCondition(brand);

        //????????????
        PageBean<Brand> brandPageBean = new PageBean<Brand>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(totalCount);

        session.commit();
        //????????????
        session.close();
        return brandPageBean;

    }

}
