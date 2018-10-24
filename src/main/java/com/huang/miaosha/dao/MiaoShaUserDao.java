package com.huang.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.huang.miaosha.domain.MiaoShaUser;

@Mapper
public interface MiaoShaUserDao {
    @Insert("insert into miaosha_user values ()")
    public void insertMiaoShaUser(MiaoShaUser user);
}
