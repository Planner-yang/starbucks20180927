package com.starbucks.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class IdUniqueUtils {
    public static long count = 0;
    public static int idLength = 3;

    public static long generateId () {
        long currentTimeMillis = System.currentTimeMillis();

        Random random = new Random();
        int i = random.nextInt(899) + 100;
        count++;

        if (String.valueOf(count).length() > idLength) {
            count = 0;
        }

        String id = String.format("%s%s%s", currentTimeMillis, i, StringUtils.leftPad(String.valueOf(count), idLength, "0"));

        return Long.parseLong(id);
    }

}
