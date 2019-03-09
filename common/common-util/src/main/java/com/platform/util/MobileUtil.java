package com.platform.util;

public class MobileUtil {

    public static String hideMobile(String mobile){
        mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        return mobile;
    }

    public static void main(String[] args) {
        System.out.println(hideMobile("15070083561"));
    }
}
