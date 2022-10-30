package com.huahuo.utils.common;

import java.util.*;

/**
 * @作者 花火
 * @创建日期 2022/10/30 11:26
 */

/**
 * 加权随机数算法
 */
public class WeightedRandomUtil {

    private static class Bean {
        Double key;
        Double sumWeight;
    }

    private static Map<Double, Double> map;
    private static List<Bean> beans = new ArrayList<>();
    private static Double sumWeight = 100.0;

    private static void init() {
        addBean(1.0, 30.0);
        addBean(0.8, 30.0);
        addBean(0.5, 20.0);
        addBean(0.1, 10.0);
        addBean(0.3, 10.0);
    }

    private static void addBean(Double key, Double value) {
        Bean bean = new Bean();
        bean.key = key;
        bean.sumWeight = value;
        beans.add(bean);
    }

    public static Double getRandom() {
        init();
        Double nowSumWeight = 0.0;
        Double r = new Random().nextDouble() * sumWeight;
        for (Bean bean : beans) {
            nowSumWeight = nowSumWeight + bean.sumWeight;
            if (nowSumWeight >= r) return bean.key;
        }
        return 0.6;
    }
    
}
