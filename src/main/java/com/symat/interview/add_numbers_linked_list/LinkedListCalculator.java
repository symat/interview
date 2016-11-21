package com.symat.interview.add_numbers_linked_list;


import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListCalculator {

    public static LinkedList<Integer> add(final LinkedList<Integer> numberA, final LinkedList<Integer> numberB) {
        final Iterator<Integer> iteratorA = numberA.descendingIterator();
        final Iterator<Integer> iteratorB = numberB.descendingIterator();
        final LinkedList<Integer> sum = new LinkedList<>();

        int remain = 0;
        while (iteratorA.hasNext() || iteratorB.hasNext() || remain > 0) {
            final Integer digitA = iteratorA.hasNext() ? iteratorA.next() : 0;
            final Integer digitB = iteratorB.hasNext() ? iteratorB.next() : 0;
            final int digitSum = digitA + digitB + remain;
            remain = digitSum / 10;
            sum.addFirst(digitSum % 10);
        }

        return sum;
    }

}
