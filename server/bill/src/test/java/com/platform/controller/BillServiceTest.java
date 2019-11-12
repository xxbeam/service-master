package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.base.RetCode;
import com.platform.bill.service.BillBookService;
import com.platform.dto.bill.BillBookDTO;
import com.platform.util.ResultUtil;
import com.platform.util.jwt.JwtUserConst;
import com.platform.vo.JsonResult;
import com.platform.vo.bill.BillBookTypeVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
