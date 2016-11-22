package com.symat.interview.boggle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Boggle {

    public static Set<String> findWords(final String[] dictionary, final String[][] boggle) {
        final Set<String> results = new HashSet<>();
        final Set<String> wordsToFind = Arrays.stream(dictionary).collect(toSet());

        final int size = boggle.length;
        for (int row = 0; row < size && !wordsToFind.isEmpty(); row++)
            for (int col = 0; col < size && !wordsToFind.isEmpty(); col++) {
                final Set<String> wordsFound =
                        walkTheBoggle(boggle, new Cell(row, col, size), /* visited cells */ new HashSet<>(),
                                      /*current word*/ "", wordsToFind, /* words found so far*/ new HashSet<>());
                results.addAll(wordsFound);
                wordsToFind.removeAll(wordsFound);
            }

        return results;
    }

    private static Set<String> walkTheBoggle(final String[][] boggle, final Cell currentCell,
                                             // following three params are handled immutable, belong to the current path from the starting point
                                             final Set<Cell> cellsVisitedSoFar,
                                             final String wordWalkedSoFar,
                                             final Set<String> potentialWordsStillCanBeFound,
                                             // following param is mutable, collecting results
                                             final Set<String> wordsFoundSoFar) {
        final String currentWord = wordWalkedSoFar + boggle[currentCell.row][currentCell.col];
        final Set<String> wordsStillPotential = potentialWordsStillCanBeFound.stream().filter(s -> s.startsWith(currentWord)).collect(toSet());
        if (wordsStillPotential.contains(currentWord)) {
            wordsFoundSoFar.add(currentWord);
            wordsStillPotential.remove(currentWord);
        }

        if (!wordsStillPotential.isEmpty()) {
            final Set<Cell> visitedCells = new HashSet<>(cellsVisitedSoFar);
            visitedCells.add(currentCell);
            currentCell.neighbors().forEach(nextCell -> walkTheBoggle(boggle, nextCell, visitedCells, currentWord, wordsStillPotential, wordsFoundSoFar));
        }

        return wordsFoundSoFar;
    }


}
