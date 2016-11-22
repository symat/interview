package com.symat.interview.boggle;

import java.util.LinkedList;
import java.util.List;

class Cell {
    public final int row;
    public final int col;
    private final int size;

    public Cell(final int row, final int col, final int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }


    public List<Cell> neighbors() {
        final List<Cell> neighbors = new LinkedList<>();
        neighbors.add(new Cell(row - 1, col - 1, size));
        neighbors.add(new Cell(row - 1, col, size));
        neighbors.add(new Cell(row - 1, col + 1, size));
        neighbors.add(new Cell(row, col - 1, size));
        neighbors.add(new Cell(row, col + 1, size));
        neighbors.add(new Cell(row + 1, col - 1, size));
        neighbors.add(new Cell(row + 1, col, size));
        neighbors.add(new Cell(row + 1, col + 1, size));
        neighbors.removeIf(cell -> cell.row < 0 || cell.row >= size || cell.col < 0 || cell.col >= size);
        return neighbors;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Cell cell = (Cell) o;

        if (row != cell.row) return false;
        return col == cell.col;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", size=" + size +
                '}';
    }
}
