package com.kaarvik.lvysaurworkouttracker.utils;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zkaar on 2017-09-23.
 */
public class PlateCalculatorTest {
    @Test
    public void calculatePlates_noWeightWithBar() throws Exception {
        String exerciseType = Exercise.TYPE_BENCHPRESS;
        double exerciseWeight = 45.0;

        String actual = PlateCalculator.calculatePlates(exerciseWeight, exerciseType);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    public void calculatePlates_noWeightNoBar() throws Exception {
        String exerciseType = Exercise.TYPE_CHINUPS;
        double exerciseWeight = 0.0;

        String actual = PlateCalculator.calculatePlates(exerciseWeight, exerciseType);

        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    public void calculatePlates_weightWithBar() throws Exception {
        String exerciseType = Exercise.TYPE_BARBELLROWS;
        double exerciseWeight = 175.0;

        String actual = PlateCalculator.calculatePlates(exerciseWeight, exerciseType);

        String expected = "1 x 45\n2 x 10";

        assertEquals(expected, actual);
    }

    @Test
    public void calculatePlates_heavyWeightWithBar() throws Exception {
        String exerciseType = Exercise.TYPE_SQUAT;
        double exerciseWeight = 380.0;

        String actual = PlateCalculator.calculatePlates(exerciseWeight, exerciseType);

        String expected = "3 x 45\n1 x 25\n1 x 5\n1 x 2.5";

        assertEquals(expected, actual);
    }

    @Test
    public void calculatePlates_weightNoBar() throws Exception {
        String exerciseType = Exercise.TYPE_CHINUPS;
        double exerciseWeight = 105;

        String actual = PlateCalculator.calculatePlates(exerciseWeight, exerciseType);

        String expected = "2 x 45\n1 x 10\n1 x 5";

        assertEquals(expected, actual);
    }

}