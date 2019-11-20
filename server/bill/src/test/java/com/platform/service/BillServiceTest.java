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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author xiongxin
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class BillServiceTest  extends TestCase {

    @InjectMocks
    BillController billController;

    @Mock
    private BillBookService billBookService;

    @Test
    public void getBillBookTypeByUser() {
        when(billBookService.getBillBookTypeByUser(1)).thenReturn(selectBillBookTypeByNameAndUser(new BillBookTypeExample()));
        JsonResult jsonResult = billController.getBillBookType(1);
        System.out.println(JSON.toJSONString(jsonResult));
        Assert.assertTrue(jsonResult!=null);

    }

    private List<BillBookTypeVo> selectBillBookTypeByNameAndUser(BillBookTypeExample billBookTypeExample){
        BillBookTypeVo billBookType = new BillBookTypeVo();
        billBookType.setId(1);
        billBookType.setTypeName("111");
        List<BillBookTypeVo> list = new ArrayList<>();
        list.add(billBookType);
        return list;
    }

}
