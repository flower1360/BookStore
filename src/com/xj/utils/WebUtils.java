package com.xj.utils;


import com.xj.bean.Cart;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * web相关的工具
 */

public class WebUtils {
    public static<T> T paramToBean(HttpServletRequest request, T t){
        //封装对象并返回
        //1,获取所有声明的属性
        Field[] fields = t.getClass().getDeclaredFields();
        //2,每个属性都有name值
        for(Field field:fields){
            //获取属性名
            String name = field.getName();
            //在request中获取相应的属性值
            String value = request.getParameter(name);
            try {
                BeanUtils.setProperty(t,name,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }
    public static<T> T paramToBean2(HttpServletRequest request, T t){
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(t,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            //没有购物车,就创建一个
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        return cart;
    }
}
