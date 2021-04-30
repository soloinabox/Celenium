package com.solo.game.util;

import java.util.Random;

public class RandomUtil {

    private static Random random;

    public static void init() {

        random = new Random();

    }

    public static float nextFloat(float min, float max) {

        return (min + random.nextFloat()%(max-min));

    }

    public static int nextInt(int min, int max) {

        return (min + random.nextInt(max-min));

    }
}
