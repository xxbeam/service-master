package com.platform.bill.service;

import com.platform.dto.bill.BillBookDTO;
import com.platform.vo.bill.BillBookTypeVo;

import java.util.List;

public interface BillBookService {

    /**
     * 添加账目流水
     * @param billBookDTO
     */
    public void saveBillBook(BillBookDTO billBookDTO);

    public List<BillBookTypeVo> getBillBookTypeByUser(Integer userId);
}
