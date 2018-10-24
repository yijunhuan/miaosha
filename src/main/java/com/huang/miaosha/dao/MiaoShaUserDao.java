package com.huang.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.huang.miaosha.domain.MiaoShaUser;

@Mapper
public interface MiaoShaUserDao {
    @Insert("insert into miaosha_user values ()")
    public void insertMiaoShaUser(MiaoShaUser user);
    
    @Select("select * from miaosha_user where id = #{id} ")
    public MiaoShaUser getById(@Param("id")long id) ;
}
