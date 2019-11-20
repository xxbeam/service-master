package com.platform.service;

import com.alibaba.fastjson.JSON;
import com.platform.bill.bo.BillBookType;
import com.platform.bill.bo.BillBookTypeExample;
import com.platform.bill.controller.BillController;
import com.platform.bill.dao.BillBookTypeMapper;
import com.platform.bill.service.BillBookService;
import com.platform.bill.service.impl.BillBookServiceImpl;
import com.platform.vo.JsonResult;
import com.platform.vo.bill.BillBookTypeVo;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author xiongxin
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class BillServiceTest  extends TestCase {


    @Mock
    private BillBookTypeMapper billBookTypeMapper;;

    @Autowired
    BillBookService billBookService;

    @Test
    public void getBillBookTypeByUser() {
        //通过ReflectionTestUtils注入需要的非public字段数据
        ReflectionTestUtils.setField(billBookService, "billBookTypeMapper", billBookTypeMapper);
        when(billBookTypeMapper.selectByExample(any())).thenReturn(selectBillBookTypeByNameAndUser());
        List<BillBookTypeVo> list = billBookService.getBillBookTypeByUser(1);
        System.out.println(JSON.toJSONString(list));
        Assert.assertTrue(list!=null);

    }

    private List<BillBookType> selectBillBookTypeByNameAndUser(){
        BillBookType billBookType = new BillBookType();
        billBookType.setId(1);
        billBookType.setTypeName("111");
        List<BillBookType> list = new ArrayList<>();
        list.add(billBookType);
        return list;
    }

}
