package com.symat.interview.edit_distance;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
    You have three types of edit operations: remove a character, change a character, insert a character
    The goal is to found a minimal set of operations needed to produce string2 from string1.

    Examples:
    "get"     -->   "git"       : 1 operation (change e to i)
    "child"   -->   "children"  : 3 operations (three insertion)
    "apple"   -->   "apricot"   : 8 operations (three remove, and 5 insertion)

    It is enough if the function returns the distance, the actual list of operations are not required.
 */


public class EditDistanceCalculatorTest {

    @Test
    public void shouldReturnZeroDistanceForSameWords() throws Exception {
        assertEquals(0, EditDistanceCalculator.calculateDistance("foo", "foo"));
    }

    @Test
    public void shouldFindSingleInsertion() throws Exception {
        assertEquals(1, EditDistanceCalculator.calculateDistance("aple", "apple"));
    }

    @Test
    public void shouldFindSingleRemoval() throws Exception {
        assertEquals(1, EditDistanceCalculator.calculateDistance("bannana", "banana"));
    }

    @Test
    public void shouldFindSingleChange() throws Exception {
        assertEquals(1, EditDistanceCalculator.calculateDistance("get", "git"));
    }

    @Test
    public void shouldFindDistanceIfSourceIsEmpty() throws Exception {
        assertEquals(5, EditDistanceCalculator.calculateDistance("", "apple"));
    }

    @Test
    public void shouldFindDistanceIfTargetIsEmpty() throws Exception {
        assertEquals(5, EditDistanceCalculator.calculateDistance("apple", ""));
    }

    @Test
    public void shouldFindDistanceWhenMultipleSolutionsExist() throws Exception {
        assertEquals(2, EditDistanceCalculator.calculateDistance("abababab", "babababa"));
    }

    @Test
    public void shouldFindDistanceInMoreComplexScenario() throws Exception {
        assertEquals(3, EditDistanceCalculator.calculateDistance("cabb", "abac"));
        // eg. remove C, then change last B to A, then add a C to the end
    }


}