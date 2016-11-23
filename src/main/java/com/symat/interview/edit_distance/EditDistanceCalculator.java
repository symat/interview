package com.symat.interview.edit_distance;

import static java.lang.Integer.min;

public class EditDistanceCalculator {

    // the solution below is a nice, recursive algorithm, but also it is an exponential one
    // can we give a polynomial algorithm?


    public static int calculateDistance(final String sourceString, final String targetString) {

        // trivial cases
        if (sourceString.equals(targetString)) return 0;
        if (sourceString.isEmpty()) return targetString.length();
        if (targetString.isEmpty()) return sourceString.length();

        final char firstSourceChar = sourceString.charAt(0);
        final char firstTargetChar = targetString.charAt(0);
        final String restSourceString = sourceString.substring(1);
        final String restTargetString = targetString.substring(1);

        if (firstSourceChar == firstTargetChar)
            return calculateDistance(restSourceString, restTargetString);

        // else try all three operation and return with the minimal solution
        final int distanceIfWeChangeFirstChar = 1 + calculateDistance(restSourceString, restTargetString);
        final int distanceIfWeInsertFirstChar = 1 + calculateDistance(sourceString, restTargetString);
        final int distanceIfWeRemoveFirstChar = 1 + calculateDistance(restSourceString, targetString);
        return min(distanceIfWeChangeFirstChar, min(distanceIfWeInsertFirstChar, distanceIfWeRemoveFirstChar));
    }
}
