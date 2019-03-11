import { Dialog } from 'vant';
import localStorageUtil from '../js/localSotrageUtil.js'


function praseDataError(err){
    Dialog.alert({
        message: "数据解析失败"
        }).then(() => {
        // on close
    });
    console.log(err);
}

function clearLogin(){
    localStorageUtil.removeItem("token");
    localStorageUtil.removeItem("userId");
    localStorageUtil.removeItem("nickname");
}

export default{
    praseDataError,
    clearLogin
}