package com.platform.sso.dao;

import com.platform.sso.bo.AccessAuth;
import com.platform.sso.bo.AccessAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessAuth record);

    int insertSelective(AccessAuth record);

    List<AccessAuth> selectByExample(AccessAuthExample example);

    AccessAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccessAuth record, @Param("example") AccessAuthExample example);

    int updateByExample(@Param("record") AccessAuth record, @Param("example") AccessAuthExample example);

    int updateByPrimaryKeySelective(AccessAuth record);

    int updateByPrimaryKey(AccessAuth record);
}