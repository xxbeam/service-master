
var type_date_str = "date_str"


function setItem(key,value,expires,type){
    if(expires){
        //type==String 表明传递的是时间字符串，需要转成毫秒
        if(type==this.type_date_str){
            var data = {
                value:value,
                expires:new Date(expires).getTime(),
            }
        }else{
            var data = {
                value:value,
                expires:expires,
            }
        }
    }else{
        var data = {
            value : value
        }
    }
    
    localStorage.setItem(key,JSON.stringify(data));
}

function getItem(key){
    var data = localStorage.getItem(key)
    if(!data){
        return data;
    }
    try{
        data = JSON.parse(data);
    }catch(error){
        //不是json数据就直接返回
        return data;
    }
    if(data.expires){
        var curTime = new Date().getTime();
        if(data.expires-curTime>0){
            return data.value;
        }else{
            localStorage.removeItem(key);
            return null;
        }
    }else{
        return data.value;
    }
}

function removeItem(key){
    localStorage.removeItem(key);
}

export default{
    setItem,
    getItem,
    removeItem,
    type_date_str
}