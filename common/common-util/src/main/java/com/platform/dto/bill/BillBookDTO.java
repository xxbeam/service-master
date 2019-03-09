package com.platform.dto.bill;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BillBookDTO {

    @NotNull(message="用户名id为空")
    private Integer userId;

    @NotNull(message="支付类型不能为空")
    private Byte payType;

    @NotNull(message="金额不能为空")
    private BigDecimal amount;

    @NotNull(message="收支类型不能为空")
    private Byte accountType;

    @NotEmpty(message="账目类型不能为空")
    private String bookTypeName;

    private String billTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }
}
