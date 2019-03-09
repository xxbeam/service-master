import request from './request.js';
import { Promise, reject } from 'q';
import { resolve } from 'url';

//var base_url = "http://39.98.85.65:8088";
var base_url = "http://39.98.85.65:31000";

export function login(loginName,password) {
    var url = base_url+'/sso/user/login';
    var param = {
        loginName: loginName,
        password: password
        }
    return request.post(url,param)
}

export function getBillBookType(token) {
    var url = base_url+'/bill/bill/getBillBookType';
    var param = {};
    var headers = {
        'token':token
    }
    return request.get(url,param,headers)
}

export function saveBillBook(userId,payType,accountType,bookTypeName,amount,billTime,token) {
    var url = base_url+'/bill/bill/saveBillBook';
    var param = {
        userId:userId,
        payType:payType,
        amount:amount,
        accountType:accountType,
        bookTypeName:bookTypeName,
        billTime,billTime
    };
    var headers = {
        'token':token
    }
    return request.post(url,param,headers)
}