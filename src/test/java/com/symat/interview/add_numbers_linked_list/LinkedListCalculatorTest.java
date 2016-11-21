package com.symat.interview.add_numbers_linked_list;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * The task is to add two positive numbers represented by linked lists
 */
public class LinkedListCalculatorTest {

    @Test
    public void shouldAddZeroToAnyNumber() throws Exception {
        final LinkedList<Integer> numberA = asLinkedList(4, 6, 7);
        final LinkedList<Integer> numberZero = asLinkedList(0);

        final List<Integer> result = LinkedListCalculator.add(numberA, numberZero);

        assertEquals(numberA, result);
    }

    @Test
    public void shouldAddTwoNumbersWithNoRemains() throws Exception {
        final LinkedList<Integer> numberA = asLinkedList(1, 1, 1);
        final LinkedList<Integer> numberB = asLinkedList(2, 2);

        final List<Integer> result = LinkedListCalculator.add(numberA, numberB);

        assertEquals(asList(1, 3, 3), result);
    }

    @Test
    public void shouldAddTwoNumbersWithRemains() throws Exception {
        final LinkedList<Integer> numberA = asLinkedList(4, 6, 7);
        final LinkedList<Integer> numberZero = asLinkedList(9, 5, 3);

        final List<Integer> result = LinkedListCalculator.add(numberA, numberZero);

        assertEquals(asList(1, 4, 2, 0), result);
    }

    @Test
    public void shouldNotChangeOriginalNumbers() throws Exception {
        final LinkedList<Integer> numberA = asLinkedList(4, 6, 7);
        final LinkedList<Integer> numberB = asLinkedList(9, 5, 3);

        LinkedListCalculator.add(numberA, numberB);

        assertEquals(asList(4, 6, 7), numberA);
        assertEquals(asList(9, 5, 3), numberB);
    }


    @Test
    public void additionShouldBeCommutative() throws Exception {
        final LinkedList<Integer> numberA = asLinkedList(6, 4, 6, 7);
        final LinkedList<Integer> numberB = asLinkedList(9, 5, 3);

        final List<Integer> result1 = LinkedListCalculator.add(numberA, numberB);
        final List<Integer> result2 = LinkedListCalculator.add(numberB, numberA);

        assertEquals(result1, result2);
    }


    private LinkedList<Integer> asLinkedList(int... digits) {
        final LinkedList<Integer> list = new LinkedList<>();
        for (int digit : digits)
            list.add(digit);
        return list;
    }

}