package com.app.project.wangyi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/21 14:03
 * @Describe
 */
public class ChineseNumber {

    private static final Map<String, String> NUMBER_MAPPING = new HashMap<>();

//    private static List<String> mMonthList = new ArrayList<>();

    static {
//        NUMBER_MAPPING.put(0, "零");
//        NUMBER_MAPPING.put(1, "壹");
//        NUMBER_MAPPING.put(2, "贰");
//        NUMBER_MAPPING.put(3, "叁");
//        NUMBER_MAPPING.put(4, "肆");
//        NUMBER_MAPPING.put(5, "伍");
//        NUMBER_MAPPING.put(6, "陆");
//        NUMBER_MAPPING.put(7, "柒");
//        NUMBER_MAPPING.put(8, "捌");
//        NUMBER_MAPPING.put(9, "玖");

        NUMBER_MAPPING.put("0", "零");
        NUMBER_MAPPING.put("1", "一");
        NUMBER_MAPPING.put("2", "二");
        NUMBER_MAPPING.put("3", "三");
        NUMBER_MAPPING.put("4", "四");
        NUMBER_MAPPING.put("5", "五");
        NUMBER_MAPPING.put("6", "六");
        NUMBER_MAPPING.put("7", "七");
        NUMBER_MAPPING.put("8", "八");
        NUMBER_MAPPING.put("9", "九");

//        mMonthList.add("一月");
//        mMonthList.add("二月");
//        mMonthList.add("三月");
//        mMonthList.add("四月");
//        mMonthList.add("五月");
//        mMonthList.add("六月");
//        mMonthList.add("七月");
//        mMonthList.add("八月");
//        mMonthList.add("九月");
//        mMonthList.add("十月");
//        mMonthList.add("十一月");
//        mMonthList.add("十二月");
    }

    public static String getNumToString(int num) {
        String sNum = String.valueOf(num);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sNum.length(); i++) {
            sb.append(NUMBER_MAPPING.get(sNum.substring(i, i + 1)));
        }
        return sb.toString();
    }

//    public static List<String> getmMonthList() {
//        return mMonthList;
//    }
}
