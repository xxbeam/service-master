import { Dialog } from 'vant';


function praseDataError(err){
    Dialog.alert({
        message: "数据解析失败"
        }).then(() => {
        // on close
    });
    console.log(err);
}

export default{
    praseDataError
}