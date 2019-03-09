<!-- 选择记账标签 -->
<template>
  <div>
    <van-nav-bar
        title="选择标签"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
        />
    <van-cell-group>
        <van-cell v-for="billBookType in billBookTypes" v-bind:title="billBookType.typeName" is-link @click="goSaveBook(billBookType.typeName)" />
    </van-cell-group>
    <van-cell-group>
        <van-field
            v-model="bookTypeName"
            center
            clearable
            label="新增标签"
            placeholder="请输入标签"
        >
            <van-button slot="button" size="small" type="primary" @click="goSaveBook()">确定</van-button>
        </van-field>
    </van-cell-group>
   
    {{bookTypeName}}
    
    
  </div>
</template>

<script>
    import {getBillBookType} from '../js/api.js'
    import localStorageUtil from '../js/localSotrageUtil.js'
    export default {
        data () {
            return {
                token:"",
                billBookTypes:[],
                bookTypeName:""
            };
        },

        components: {},

        computed: {
        },

        mounted : function(){
            
            this.token = localStorageUtil.getItem("token");
            if(!this.token){
                this.$router.push({name:'login'})
            }
            getBillBookType(this.token).then(data => {
                if(data.retcode==0){
                    //请求成功，添加标签
                    this.billBookTypes = data.retdata;
                }else if(data.retcode==3){
                    this.$router.push({name:'login'});
                }else{
                    this.$dialog.alert({
                        message: data.retmsg
                        }).then(() => {
                    });
                }
            })
        },

        methods: {
            onClickLeft : function(){
                this.$router.back(-1);
            },
            goSaveBook : function(bookTypeName){
                if(!bookTypeName){
                    bookTypeName = this.bookTypeName;
                }
                if(!bookTypeName){
                    this.$dialog.alert({
                        message: "记账标签不能为空"
                        }).then(() => {
                    });
                }
                console.log(bookTypeName)
                this.$router.push({name:'billBook', params: { bookTypeName: bookTypeName }});
            }

        }
    }

</script>
<style lang='less' scoped>
</style>