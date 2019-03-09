<template>
  <div>
    <van-nav-bar title="用户中心" />
    <div style="width:100%;margin:5% 0;text-align:center;">
          欢迎，{{nickname}}
    </div>

    <van-cell-group>
      <van-cell icon="coupon-o" title="我要记账" is-link to="billBookTypeList"/>
    </van-cell-group>
  </div>
</template>

<script>
import common from '../js/common.js';
import localStorageUtil from '../js/localSotrageUtil.js'
export default {
    data () {
      return {
        nickname : '',
        userId:''
      };
    },

    methods: {
      
    },

    mounted : function(){
        if(!localStorageUtil.getItem("token")){
            this.$router.push({name:'login'})
        }
        this.nickname = localStorageUtil.getItem("nickname");
        this.userId = localStorageUtil.getItem("userId");
    }


};
</script>

<style lang="less">
.user {
  &-poster {
    width: 100%;
    display: block;
  }
  &-group {
    margin-bottom: 15px;
  }
  &-links {
    padding: 15px 0;
    font-size: 12px;
    text-align: center;
    background-color: #fff;
    .van-icon {
      display: block;
      font-size: 24px;
    }
  }
}
</style>