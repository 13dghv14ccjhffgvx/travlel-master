package com.hjq.util;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  17:07
 * @Description: TODO
 * @Version: 1.0
 */
public class Demo {
    public Demo(){
        this.m1();
        //this代表当前对象，即谁调用，就代表谁
        System.out.println(this);//com.hjq.util.Demo1@49476842
    }
    public static void main(String[] args) {
        Demo m = new Demo1();
    }
    public void m1(){
        System.out.println("111");
    }
}