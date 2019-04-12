package com.sinmn.core.utils.util;

public class BooleanUtil {

    public static boolean isTrue(String b){
        if ("true".equals(b.trim())){
            return true;
        }
        return false;
    }
}
