package com.yto.template.mytemplateapp;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Chris on 2018/5/16.
 */

public class GenericTest {
    @Test
    public void generic_test() {

        Type[] types;
        ParameterizedType genType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genType).getActualTypeArguments();
//        actualTypeArguments[0];
    }

}
