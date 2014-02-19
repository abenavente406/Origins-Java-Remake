/*
 * Copyright (c) 2014 Anthony Benavente
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ambenavente.origins.util;

import java.util.Random;

/**
 * Random number generator wrapper that can get a variety of random numbers not
 * provided by the Random class provided by the Java API
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class MyRandom {

    private static Random rand = new Random();

    /**
     * Gets a random number between two bounds, a low bound and a high bound.
     * The high parameter MUST BE greater than the low parameter or else the
     * method will throw an IllegalArgumentException
     *
     * @param low  The lowest possible number for the number
     * @param high The highest possible number (not including this number)
     * @return A random number between low and high
     */
    public static int getRandBetween(int low, int high) {
        if (high > low) {
            return rand.nextInt(high - low) + low;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Gets a random number between two bounds, a low bound and a high bound.
     * The high parameter MUST BE greater than the low parameter or else the
     * method will throw an IllegalArgumentException
     *
     * @param low  The lowest possible number for the number
     * @param high The highest possible number (not including this number)
     * @return A random number between low and high
     */
    public static float getRandBetween(float low, float high) {
        if (high > low) {
            return rand.nextFloat() * (high - low) + low;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
