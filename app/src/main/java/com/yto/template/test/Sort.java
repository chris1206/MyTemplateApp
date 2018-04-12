package com.yto.template.test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Zc on 2018/4/4.
 */

public class Sort {
    private static String[] items = {
            "京", "沪", "浙", "苏", "粤", "鲁", "晋", "冀", "豫",
            "川", "渝", "辽", "吉", "黑", "皖", "鄂", "湘", "赣",
            "闽", "陕", "甘", "宁", "蒙", "津",  "贵", "云","桂",
            "琼", "青", "新", "藏",
    };
    private final static Comparator CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
    public static void main(String[] args) {
        List<String> sList = new ArrayList<>(Arrays.asList(items));
        Collections.sort(sList,CHINA_COMPARE);
        for (int i=0;i<sList.size();i++) {
            System.out.println(sList.get(i));
        }
    }
}
