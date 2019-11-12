package com.platform.service;

import com.alibaba.fastjson.JSON;
import com.platform.bill.service.BillBookService;
import com.platform.vo.bill.BillBookTypeVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author xiongxin
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class BillServiceTest {

    @Autowired
    BillBookService billBookService;

    @Test
    public void getBillBookTypeByUser() {

        List<BillBookTypeVo> list = billBookService.getBillBookTypeByUser(1);
        System.out.println(JSON.toJSONString(list));
        Assert.assertTrue(list!=null);

    }

}
