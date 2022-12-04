package com.wu.mapper;

import com.wu.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void Insert(Brand brand);

    void deleteByIds(@Param("ids") int[] ids); //注意加上@Para 标签

    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    @Update("update tb_brand set brand_name=#{brandName}, company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void updateBrand(Brand brand);

    //查询所有记录数
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //查询每页数据
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    //条件查询返回集合
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("brand") Brand brand);

    //条件查询总条件数
    int selectTotalCountByCondition(@Param("brand") Brand brand);//要写上注解

}
