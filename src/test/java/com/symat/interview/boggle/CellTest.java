package com.symat.interview.boggle;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public void shouldFindNeighborsForSingleCellTable() throws Exception {
        final Cell cell = new Cell(0, 0, 1);
        final List<Cell> expectedNeighbors = emptyList();
        assertEquals(expectedNeighbors, cell.neighbors());
    }

    @Test
    public void shouldFindNeighborsForCentralElement() throws Exception {
        final int size = 3;
        final Cell cell = new Cell(1, 1, size);
        final List<Cell> expectedNeighbors = asList(new Cell(0, 0, size), new Cell(0, 1, size), new Cell(0, 2, size),
                                                    new Cell(1, 0, size), new Cell(1, 2, size),
                                                    new Cell(2, 0, size), new Cell(2, 1, size), new Cell(2, 2, size));
        assertEquals(expectedNeighbors, cell.neighbors());
    }

    @Test
    public void shouldFindNeighborsForCornerElement() throws Exception {
        final int size = 3;
        final Cell cell = new Cell(2, 2, size);
        final List<Cell> expectedNeighbors = asList(new Cell(1, 1, size), new Cell(1, 2, size), new Cell(2, 1, size));
        assertEquals(expectedNeighbors, cell.neighbors());
    }

    @Test
    public void shouldFindNeighborsForLeftMiddleElement() throws Exception {
        final int size = 3;
        final Cell cell = new Cell(1, 0, size);
        final List<Cell> expectedNeighbors = asList(new Cell(0, 0, size), new Cell(0, 1, size),
                                                    new Cell(1, 1, size),
                                                    new Cell(2, 0, size), new Cell(2, 1, size));
        assertEquals(expectedNeighbors, cell.neighbors());
    }
}