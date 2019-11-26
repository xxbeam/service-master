package com.platform.bill.service.impl;

import com.platform.base.BaseConst;
import com.platform.bill.bo.BillBook;
import com.platform.bill.bo.BillBookType;
import com.platform.bill.bo.BillBookTypeExample;
import com.platform.bill.dao.BillBookMapper;
import com.platform.bill.dao.BillBookTypeMapper;
import com.platform.bill.service.BillBookService;
import com.platform.dto.bill.BillBookDTO;
import com.platform.util.DateUtil;
import com.platform.vo.bill.BillBookTypeVo;
import com.tuyang.beanutils.BeanCopyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillBookServiceImpl implements BillBookService {

    @Autowired
    private BillBookMapper billBookMapper;
    @Autowired
    private BillBookTypeMapper billBookTypeMapper;

    @Override
    @Transactional
    public void saveBillBook(BillBookDTO billBookDTO) {
        List<BillBookType> billBookTypes = this.selectBillBookTypeByNameAndUser(billBookDTO.getBookTypeName(),billBookDTO.getUserId());
        BillBookType billBookType = null;
        if(billBookTypes==null||billBookTypes.size()==0){
            billBookType = this.saveBillBookType(billBookDTO.getBookTypeName(),billBookDTO.getUserId());
        }else{
            billBookType = billBookTypes.get(0);
        }
        //新增账目
        BillBook billBook = new BillBook();
        billBook.setUserId(billBookDTO.getUserId());
        billBook.setAmount(billBookDTO.getAmount());
        billBook.setCreateTime(new Date());
        billBook.setPayType(billBookDTO.getPayType());
        billBook.setAccountType(billBookDTO.getAccountType());
        if(StringUtils.isNotEmpty(billBookDTO.getBillTime())){
            billBook.setBillTime(DateUtil.parseDate(billBookDTO.getBillTime(),DateUtil.DATE_TIME));
        }else{
            billBook.setBillTime(new Date());
        }
        billBook.setStatus(BaseConst.STATUS_EFFECTIVE);
        billBook.setBookTypeId(billBookType.getId());
        billBook.setBookTypeName(billBookType.getTypeName());

        billBookMapper.insertSelective(billBook);
    }

    @Override
    public List<BillBookTypeVo> getBillBookTypeByUser(Integer userId) {
        if(userId==null){
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
            System.out.println(22222222);
        }
        List<BillBookType> billBookTypeList = this.selectBillBookTypeByNameAndUser(null,userId);
        List<BillBookTypeVo> voList = BeanCopyUtils.copyList(billBookTypeList,BillBookTypeVo.class);
        return voList;
    }

    /**
     * 根据类型名称和用户id查询类型
     * @param typeName
     * @param userId
     * @return
     */
    public List<BillBookType> selectBillBookTypeByNameAndUser(String typeName, Integer userId){
        BillBookTypeExample billBookTypeExample = new BillBookTypeExample();
        BillBookTypeExample.Criteria criteria = billBookTypeExample.createCriteria();


        criteria.andStatusEqualTo(BaseConst.STATUS_EFFECTIVE);
        if(StringUtils.isNotEmpty(typeName)){
            criteria.andTypeNameEqualTo(typeName);
        }
        if(userId!=null){
            List<Integer> userIds = new ArrayList<>();
            userIds.add(userId);
            userIds.add(BillBookType.DEFAULT_USER);
            criteria.andUserIdIn(userIds);
        }else{
            criteria.andUserIdEqualTo(BillBookType.DEFAULT_USER);
        }
        List<BillBookType> billBookTypeList = billBookTypeMapper.selectByExample(billBookTypeExample);
        if(billBookTypeList.isEmpty()){
            return null;
        }
        return billBookTypeList;
    }

    /**
     * 保存账目类型
     * @param typeName
     * @param userId
     * @return
     */
    private BillBookType saveBillBookType(String typeName, Integer userId){
        BillBookType billBookType = new BillBookType();
        billBookType.setTypeName(typeName);
        billBookType.setUserId(userId);
        billBookType.setCreateTime(new Date());
        billBookType.setStatus(BaseConst.STATUS_EFFECTIVE);
        billBookTypeMapper.insertSelective(billBookType);
        return billBookType;
    }
}
