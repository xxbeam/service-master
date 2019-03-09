package com.platform.bill.dao;

import com.platform.bill.bo.BillBook;
import com.platform.bill.bo.BillBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BillBook record);

    int insertSelective(BillBook record);

    List<BillBook> selectByExample(BillBookExample example);

    BillBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BillBook record, @Param("example") BillBookExample example);

    int updateByExample(@Param("record") BillBook record, @Param("example") BillBookExample example);

    int updateByPrimaryKeySelective(BillBook record);

    int updateByPrimaryKey(BillBook record);
}