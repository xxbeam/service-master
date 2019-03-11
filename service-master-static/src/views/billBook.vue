<!-- 记账 -->
<template>
  <div>
      <van-nav-bar
        title="保存账单"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
        />

    <h2 class="van-doc-demo-block__title">支付方式</h2>
    <van-radio-group v-model="payType">
      <van-cell-group>
        <van-cell title="支付宝" clickable @click="radio = '1'">
          <van-radio name="1" />
        </van-cell>
        <van-cell title="微信" clickable @click="radio = '2'">
          <van-radio name="2" />
        </van-cell>
        <van-cell title="现金" clickable @click="radio = '0'">
          <van-radio name="0" />
        </van-cell>
      </van-cell-group>
    </van-radio-group>
    <h2 class="van-doc-demo-block__title">账单类型</h2>
    <van-radio-group v-model="accountType">
      <van-cell-group>
        <van-cell title="支出" clickable @click="radio = '1'">
          <van-radio name="1" />
        </van-cell>
        <van-cell title="收入" clickable @click="radio = '0'">
          <van-radio name="0" />
        </van-cell>
      </van-cell-group>
    </van-radio-group>
    <van-cell-group>
        <van-field v-model="bookTypeName" label="标签" disabled />
        <van-field v-model="amount" type="number" step="0.01" clearable label="金额" placeholder="请输入金额" />
    </van-cell-group>
    <van-cell-group>
        <van-field
          v-model="billTime"
          center
          clearable
          label="记账时间"
        >
          <van-button slot="button" size="small" type="primary"  @click="showDatePicker">选择</van-button>
        </van-field>
    </van-cell-group>
    <van-button type="primary" size="large" @click="saveBook">保存</van-button>
    <van-actionsheet v-model="show" >
      <van-datetime-picker title="请选择时间" 
            v-model="currentDate" @confirm="confirmDate" @cancel="hideDatePicker"
            type="datetime" 
          />
    </van-actionsheet>
    
  </div>
</template>

<script>
import {saveBillBook} from '../js/api.js'
import localStorageUtil from '../js/localSotrageUtil.js'
import {formatDate} from '../js/date.js'
export default {
  data () {
    return {
        userId:'',
        token:'',
        payType:"1",
        accountType:"1",
        bookTypeName:'',
        amount:"",
        billTime:'',
        currentDate: new Date(),
        show:false
    };
  },

  components: {},

  computed: {},

  mounted:function(){
        this.token = localStorageUtil.getItem("token");
        if(!this.token){
            this.$router.push({name:'login'})
        }
        this.userId = localStorageUtil.getItem("userId");
        this.bookTypeName = this.$route.params.bookTypeName;
        this.billTime = formatDate(this.currentDate,'yyyy-MM-dd hh:mm:ss')
  },

  methods: {
        onClickLeft : function(){
            this.$router.back(-1);
        },
        saveBook : function(){
            saveBillBook(this.userId,this.payType,this.accountType,this.bookTypeName,this.amount,this.billTime,this.token).then(data => {
                if(data.retcode==0){
                      //请求成功，添加标签
                      this.$dialog.alert({
                          message: "添加成功"
                          }).then(() => {
                          this.$router.push({name:'user-center'}); 
                      });
                  }else if(data.retcode==3){
                      common.clearLogin();
                      this.$router.push({name:'login'});
                  }else{
                      this.$dialog.alert({
                          message: data.retmsg
                          }).then(() => {
                      });
                }
            })
        },
        confirmDate : function(value){
          this.billTime = formatDate(this.currentDate,'yyyy-MM-dd hh:mm:ss')
          this.hideDatePicker();
        },
        showDatePicker: function(){
          this.show = true;
        },
        hideDatePicker: function(){
          this.show = false;
        }
  }
}

</script>
<style lang='less' scoped>
  .van-doc-demo-block__title {
      margin: 0;
      font-weight: 400;
      font-size: 14px;
      color: rgba(69,90,100,.6);
      padding: 35px 15px 15px;
      padding-top: 20px;
  }

</style>