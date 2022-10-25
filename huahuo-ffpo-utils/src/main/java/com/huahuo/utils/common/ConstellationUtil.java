package com.huahuo.utils.common;

import java.util.HashMap;
import java.util.Map;

public class ConstellationUtil {
    /**
     * 星座匹配分值
     */
    public static final Map<String, Double> constellationScoreMap = new HashMap<>();

    /**
     * 初始化星座匹配分数
     * 1水瓶座 2双鱼座 3白羊座 4金牛座 5双子座 6巨蟹座 7狮子座 8处女座 9天秤座 10天蝎座 11射手座 12摩羯座
     */
    static {
        //1水瓶座
        constellationScoreMap.put("1-1", 87.0);
        constellationScoreMap.put("1-2", 64.5);
        constellationScoreMap.put("1-3", 80.0);
        constellationScoreMap.put("1-4", 69.0);
        constellationScoreMap.put("1-5", 95.0);
        constellationScoreMap.put("1-6", 66.0);
        constellationScoreMap.put("1-7", 81.0);
        constellationScoreMap.put("1-8", 59.5);
        constellationScoreMap.put("1-9", 95.5);
        constellationScoreMap.put("1-10", 54.0);
        constellationScoreMap.put("1-11", 80.0);
        constellationScoreMap.put("1-12", 63.0);
        //2双鱼座
        constellationScoreMap.put("2-1", 64.5);
        constellationScoreMap.put("2-2", 88.0);
        constellationScoreMap.put("2-3", 75.0);
        constellationScoreMap.put("2-4", 79.5);
        constellationScoreMap.put("2-5", 47.0);
        constellationScoreMap.put("2-6", 95.0);
        constellationScoreMap.put("2-7", 61.5);
        constellationScoreMap.put("2-8", 74.5);
        constellationScoreMap.put("2-9", 69.0);
        constellationScoreMap.put("2-10", 95.5);
        constellationScoreMap.put("2-11", 49.5);
        constellationScoreMap.put("2-12", 79.5);
        //3白羊座
        constellationScoreMap.put("3-1", 80.0);
        constellationScoreMap.put("3-2", 75.0);
        constellationScoreMap.put("3-3", 90.0);
        constellationScoreMap.put("3-4", 70.0);
        constellationScoreMap.put("3-5", 81.0);
        constellationScoreMap.put("3-6", 49.5);
        constellationScoreMap.put("3-7", 95.5);
        constellationScoreMap.put("3-8", 63.0);
        constellationScoreMap.put("3-9", 85.0);
        constellationScoreMap.put("3-10", 65.0);
        constellationScoreMap.put("3-11", 95.5);
        constellationScoreMap.put("3-12", 50.5);
        //4金牛座
        constellationScoreMap.put("4-1", 53.5);
        constellationScoreMap.put("4-2", 79.5);
        constellationScoreMap.put("4-3", 71.5);
        constellationScoreMap.put("4-4", 88.0);
        constellationScoreMap.put("4-5", 74.0);
        constellationScoreMap.put("4-6", 78.5);
        constellationScoreMap.put("4-7", 50.5);
        constellationScoreMap.put("4-8", 94.0);
        constellationScoreMap.put("4-9", 65.5);
        constellationScoreMap.put("4-10", 79.0);
        constellationScoreMap.put("4-11", 65.5);
        constellationScoreMap.put("4-12", 95.0);
        //5双子座
        constellationScoreMap.put("5-1", 94.0);
        constellationScoreMap.put("5-2", 47.0);
        constellationScoreMap.put("5-3", 81.0);
        constellationScoreMap.put("5-4", 74.0);
        constellationScoreMap.put("5-5", 89.0);
        constellationScoreMap.put("5-6", 75.0);
        constellationScoreMap.put("5-7", 80.0);
        constellationScoreMap.put("5-8", 66.5);
        constellationScoreMap.put("5-9", 95.5);
        constellationScoreMap.put("5-10", 68.5);
        constellationScoreMap.put("5-11", 75.5);
        constellationScoreMap.put("5-12", 78.0);
        //6巨蟹座
        constellationScoreMap.put("6-1", 66.0);
        constellationScoreMap.put("6-2", 95.0);
        constellationScoreMap.put("6-3", 49.5);
        constellationScoreMap.put("6-4", 77.0);
        constellationScoreMap.put("6-5", 74.5);
        constellationScoreMap.put("6-6", 89.0);
        constellationScoreMap.put("6-7", 65.0);
        constellationScoreMap.put("6-8", 86.0);
        constellationScoreMap.put("6-9", 62.0);
        constellationScoreMap.put("6-10", 94.5);
        constellationScoreMap.put("6-11", 67.5);
        constellationScoreMap.put("6-12", 83.5);
        //7狮子座
        constellationScoreMap.put("7-1", 81.0);
        constellationScoreMap.put("7-2", 61.5);
        constellationScoreMap.put("7-3", 95.5);
        constellationScoreMap.put("7-4", 50.5);
        constellationScoreMap.put("7-5", 80.5);
        constellationScoreMap.put("7-6", 65.0);
        constellationScoreMap.put("7-7", 87.0);
        constellationScoreMap.put("7-8", 69.0);
        constellationScoreMap.put("7-9", 81.0);
        constellationScoreMap.put("7-10", 55.0);
        constellationScoreMap.put("7-11", 95.0);
        constellationScoreMap.put("7-12", 68.0);
        //8处女座
        constellationScoreMap.put("8-1", 59.5);
        constellationScoreMap.put("8-2", 74.5);
        constellationScoreMap.put("8-3", 63.0);
        constellationScoreMap.put("8-4", 94.0);
        constellationScoreMap.put("8-5", 66.5);
        constellationScoreMap.put("8-6", 86.0);
        constellationScoreMap.put("8-7", 69.0);
        constellationScoreMap.put("8-8", 89.0);
        constellationScoreMap.put("8-9", 62.5);
        constellationScoreMap.put("8-10", 82.5);
        constellationScoreMap.put("8-11", 65.5);
        constellationScoreMap.put("8-12", 93.5);
        //9天秤座
        constellationScoreMap.put("9-1", 95.5);
        constellationScoreMap.put("9-2", 69.0);
        constellationScoreMap.put("9-3", 85.0);
        constellationScoreMap.put("9-4", 65.5);
        constellationScoreMap.put("9-5", 95.5);
        constellationScoreMap.put("9-6", 62.0);
        constellationScoreMap.put("9-7", 84.5);
        constellationScoreMap.put("9-8", 62.5);
        constellationScoreMap.put("9-9", 90.0);
        constellationScoreMap.put("9-10", 72.0);
        constellationScoreMap.put("9-11", 83.0);
        constellationScoreMap.put("9-12", 49.0);
        //10天蝎座
        constellationScoreMap.put("10-1", 54.0);
        constellationScoreMap.put("10-2", 95.5);
        constellationScoreMap.put("10-3", 65.5);
        constellationScoreMap.put("10-4", 79.0);
        constellationScoreMap.put("10-5", 68.5);
        constellationScoreMap.put("10-6", 94.5);
        constellationScoreMap.put("10-7", 55.0);
        constellationScoreMap.put("10-8", 82.5);
        constellationScoreMap.put("10-9", 72.0);
        constellationScoreMap.put("10-10", 87.0);
        constellationScoreMap.put("10-11", 57.5);
        constellationScoreMap.put("10-12", 80.5);
        //11射手座
        constellationScoreMap.put("11-1", 80.0);
        constellationScoreMap.put("11-2", 49.0);
        constellationScoreMap.put("11-3", 95.5);
        constellationScoreMap.put("11-4", 65.5);
        constellationScoreMap.put("11-5", 83.5);
        constellationScoreMap.put("11-6", 67.5);
        constellationScoreMap.put("11-7", 95.0);
        constellationScoreMap.put("11-8", 65.0);
        constellationScoreMap.put("11-9", 83.0);
        constellationScoreMap.put("11-10", 57.5);
        constellationScoreMap.put("11-11", 89.0);
        constellationScoreMap.put("11-12", 69.5);
        //12摩羯座
        constellationScoreMap.put("12-1", 71.5);
        constellationScoreMap.put("12-2", 79.5);
        constellationScoreMap.put("12-3", 50.5);
        constellationScoreMap.put("12-4", 95.0);
        constellationScoreMap.put("12-5", 65.0);
        constellationScoreMap.put("12-6", 82.0);
        constellationScoreMap.put("12-7", 68.0);
        constellationScoreMap.put("12-8", 93.5);
        constellationScoreMap.put("12-9", 49.0);
        constellationScoreMap.put("12-10", 80.5);
        constellationScoreMap.put("12-11", 69.5);
        constellationScoreMap.put("12-12", 88.0);
    }
}
