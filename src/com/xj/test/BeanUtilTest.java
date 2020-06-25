package com.xj.test;

import com.xj.bean.User;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilTest {
    public static void test01(){
        
        //(bean,name,value)
        User user = new User();
        System.out.println("前:" + user);
        try {
            BeanUtils.setProperty(user,"username","小明");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("后:" + user);
    }

    public static void main(String[] args) {
        test01();
    }
}
