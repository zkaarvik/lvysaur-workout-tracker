package com.kaarvik.lvysaurworkouttracker.utils;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;

/**
 * Created by zkaar on 2017-09-22.
 */

public class PlateCalculator {

    public static String calculatePlates(double totalWeight, String exerciseType) {
        //TODO: Determine if we are using lbs or kgs

        return getWeightStringPounds(totalWeight, exerciseType);
    }

    private static String getWeightStringPounds(double totalWeight, String exerciseType) {
        double remainingWeight = totalWeight;
        int numWeights = 0;
        String weightsRequired = "";

        //TODO: Determine which weights we have
        int num45 = 0;
        int num25 = 0;
        int num10 = 0;
        int num5 = 0;
        int num2_5 = 0;

        //If this exercise uses a barbell, subtract its weight, and divide by 2
        if(Exercise.isUsingBarbell(exerciseType)) {
            remainingWeight -= 45;
            remainingWeight /= 2;
        }

        //Calculate number of each plate used
        if (remainingWeight >= 45) {
            numWeights = (int) Math.floor(remainingWeight / 45);
            num45 += numWeights;
            remainingWeight -= numWeights * 45;
        }

        if (remainingWeight >= 25) {
            numWeights = (int) Math.floor(remainingWeight / 25);
            num25 += numWeights;
            remainingWeight -= numWeights * 25;
        }

        if (remainingWeight >= 10) {
            numWeights = (int) Math.floor(remainingWeight / 10);
            num10 += numWeights;
            remainingWeight -= numWeights * 10;
        }

        if (remainingWeight >= 5) {
            numWeights = (int) Math.floor(remainingWeight / 5);
            num5 += numWeights;
            remainingWeight -= numWeights * 5;
        }

        if (remainingWeight >= 2.5) {
            numWeights = (int) Math.floor(remainingWeight / 2.5);
            num2_5 += numWeights;
            remainingWeight -= numWeights * 2.5;
        }

        //Build the string based on the plates used
        if (num45 > 0) {
            weightsRequired += "\n" + num45 + " x 45";
        }
        if (num25 > 0) {
            weightsRequired += "\n" + num25 + " x 25";
        }
        if (num10 > 0) {
            weightsRequired += "\n" + num10 + " x 10";
        }
        if (num5 > 0) {
            weightsRequired += "\n" + num5 + " x 5";
        }
        if (num2_5 > 0) {
            weightsRequired += "\n" + num2_5 + " x 2.5";
        }

        //Chop the leading \n from the string
        if(!weightsRequired.equals("")){
            weightsRequired = weightsRequired.substring(1);
        }

        return weightsRequired;
    }

    private static String getWeightStringKilograms(double totalWeight, String exerciseType) {
        return "";
    }

}
