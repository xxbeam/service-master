import axios from 'axios';
import { promises } from 'fs';
import { resolve } from 'path';
import { reject } from 'q';
import { Dialog } from 'vant';

function post(url,param,headers) {
    return new Promise((resolve, reject) => {
        axios.post(url, param, {
            headers: headers
        })
        .then(function (res) {
            resolve(res.data)
        })
        .catch(function (error) {
            Dialog.alert({
                message: "请求失败"
                }).then(() => {
                // on close
            });
        });
    })
}

function get(url,param,headers) {
    return new Promise((resolve, reject) => {
        axios.get(url, {param:param, 
            headers: headers
        })
        .then(function (res) {
            resolve(res.data)
        })
        .catch(function (error) {
            Dialog.alert({
                message: "请求失败"
                }).then(() => {
                // on close
            });
        });
    })
}

export default {
    get,
    post
}