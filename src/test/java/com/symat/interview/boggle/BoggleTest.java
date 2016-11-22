package com.symat.interview.boggle;

import org.junit.Test;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.Assert.assertEquals;
import static org.mockito.internal.util.collections.Sets.newSet;

// You should implement a word puzzle game solver. You can expect same number of cols and rows in the character table.
//
// EXAMPLE:
// Input:
//     dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
//     boggle[][]   = {{'G','I','Z'},
//                     {'U','E','K'},
//                     {'Q','S','E'}};
//
//     Output:  The words from the dictionary found in the boggle: GEEKS, QUIZ


public class BoggleTest {

    @Test
    public void shouldFindWordsWithSingleCharacter() throws Exception {
        final String[] dictionary = {"G", "F", "Q"};
        final String[][] boggle = {{"G", "I", "Z"},
                                   {"U", "E", "K"},
                                   {"Q", "S", "E"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(newSet("G", "Q"), results);
    }

    @Test
    public void shouldFindNoWordIfDictionaryIsEmpty() throws Exception {
        final String[] dictionary = {};
        final String[][] boggle = {{"G", "I", "Z"},
                                   {"U", "E", "K"},
                                   {"Q", "S", "E"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(emptySet(), results);
    }

    @Test
    public void shouldFindNoWordIfBoggleIsEmpty() throws Exception {
        final String[] dictionary = {"G", "I", "Z"};
        final String[][] boggle = {};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(emptySet(), results);
    }

    @Test
    public void shouldFindWordsInSameRow() throws Exception {
        final String[] dictionary = {"ABC", "EFG", "XYZ"};
        final String[][] boggle = {{"A", "B", "C"},
                                   {"U", "E", "K"},
                                   {"X", "Y", "Z"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(newSet("ABC", "XYZ"), results);
    }

    @Test
    public void shouldFindWordsInSameCol() throws Exception {
        final String[] dictionary = {"ABC", "EFG", "XYZ"};
        final String[][] boggle = {{"A", "B", "X"},
                                   {"B", "E", "Y"},
                                   {"C", "Y", "Z"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(newSet("ABC", "XYZ"), results);
    }

    @Test
    public void shouldFindWordsInDiagonal() throws Exception {
        final String[] dictionary = {"AYC", "EFG", "XYZ"};
        final String[][] boggle = {{"A", "H", "Z"},
                                   {"H", "Y", "H"},
                                   {"X", "H", "C"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(newSet("AYC", "XYZ"), results);
    }

    @Test
    public void shouldNotFindWordWhenCaractersAreNotConnected() throws Exception {
        final String[] dictionary = {"IQ"};
        final String[][] boggle = {{"A", "I", "A"},
                                   {"A", "A", "A"},
                                   {"Q", "A", "A"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(emptySet(), results);
    }

    @Test
    public void shouldFindWordsInMoreComplexTable() throws Exception {
        final String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        final String[][] boggle = {{"G", "I", "Z"},
                                   {"U", "E", "K"},
                                   {"Q", "S", "E"}};

        final Set<String> results = Boggle.findWords(dictionary, boggle);
        assertEquals(newSet("GEEKS", "QUIZ"), results);
    }

}