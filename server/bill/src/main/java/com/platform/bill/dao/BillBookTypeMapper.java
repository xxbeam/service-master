package com.platform.bill.dao;

import com.platform.bill.bo.BillBookType;
import com.platform.bill.bo.BillBookTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillBookTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BillBookType record);

    int insertSelective(BillBookType record);

    List<BillBookType> selectByExample(BillBookTypeExample example);

    BillBookType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BillBookType record, @Param("example") BillBookTypeExample example);

    int updateByExample(@Param("record") BillBookType record, @Param("example") BillBookTypeExample example);

    int updateByPrimaryKeySelective(BillBookType record);

    int updateByPrimaryKey(BillBookType record);
}