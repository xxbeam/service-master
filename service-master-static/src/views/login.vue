<!-- 登陆页面 -->
<template>
    <div>
        <van-nav-bar title="登陆" />
       
        <div id="user-form">
            <van-cell-group>
                <van-field type="text" label="用户名" v-model="username" placeholder="请输入用户名" />
            </van-cell-group>
            <van-cell-group>
                <van-field  type="password" label="密码" v-model="password" placeholder="请输入密码" />
            </van-cell-group>
        </div>
        <van-button type="primary" size="large" @click="login">登陆</van-button>
    </div>
</template>

<script>
import {login} from '../js/api.js';
import common from '../js/common.js';
import localStorageUtil from '../js/localSotrageUtil.js'
export default {
  data () {
    return {
        username:"",
        password:""
    };
  },

  methods: {
    login: function (event) {
        login(this.username,this.password).then(data =>{
            if(data.retcode==0){
                //登陆成功 保存用户数据
                localStorageUtil.setItem('userId',data.retdata.id,data.retdata.expires,localStorageUtil.type_date_str);
                localStorageUtil.setItem('nickname',data.retdata.nickname,data.retdata.expires,localStorageUtil.type_date_str);
                localStorageUtil.setItem('token',data.retdata.token,data.retdata.expires,localStorageUtil.type_date_str);

                this.$router.push({name:'user-center'})
            }else{
                this.$dialog.alert({
                    message: data.retmsg
                    }).then(() => {
                 });
            }
        }).catch(err => {
             common.praseDataError(err);
        });     
    }
  },

  mounted : function(){
      if(localStorageUtil.getItem("token")){
          this.$router.push({name:'user-center'})
      }
  }
}

</script>
<style lang='less'>
    #user-form{
        margin-bottom: 5%;
        margin-top: 20%;
    }


</style>