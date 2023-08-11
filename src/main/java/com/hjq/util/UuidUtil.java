package com.hjq.util;

import java.util.UUID;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-29  21:13
 * @Description: TODO
 * @Version: 1.0
 */
public class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println();
    }
}
