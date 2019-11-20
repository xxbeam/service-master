package com.platform.bill.controller;

import com.platform.base.BaseController;
import com.platform.base.RetCode;
import com.platform.bill.service.BillBookService;
import com.platform.dto.bill.BillBookDTO;
import com.platform.util.ResultUtil;
import com.platform.util.jwt.JwtUserConst;
import com.platform.vo.JsonResult;
import com.platform.vo.bill.BillBookTypeVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController extends BaseController {

    @Autowired
    BillBookService billBookService;

    //@ApiOperation(value="添加账目明细", notes="添加账目明细")
    //@ApiImplicitParam(name = "billBookDTO", value = "账目流水数据传输类", required = true,  dataType = "BillBookDTO")
    @RequestMapping(value="/saveBillBook", method=RequestMethod.POST)
    public JsonResult saveBillBook(@RequestBody @Valid BillBookDTO billBookDTO) {
        //校验用户是否为当前登陆用户
        if(this.checkTokenUser(billBookDTO.getUserId(), JwtUserConst.USER)){
            billBookService.saveBillBook(billBookDTO);
        }else{
            ResultUtil.error(RetCode.ERROR,"无法操作非当前用户");
        }

        return ResultUtil.ok();
    }

    @ApiOperation(value="查询账目分类", notes="查询账目分类")
    @RequestMapping(value="/getBillBookType", method=RequestMethod.GET)
    public JsonResult getBillBookType(Integer loginUserId) {
//        String token = this.getToken();
//        int loginUserId = this.getUserIdByToken(token,JwtUserConst.USER);
        List<BillBookTypeVo> list = billBookService.getBillBookTypeByUser(loginUserId);
        return ResultUtil.ok(list);
    }

}
