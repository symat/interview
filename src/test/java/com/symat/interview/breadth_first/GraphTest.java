package com.symat.interview.breadth_first;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.internal.util.collections.Sets.newSet;


public class GraphTest {

    @Test
    public void shouldCreateEmptyGraph() throws Exception {
        final Graph emptyGraph = new Graph();
        assertTrue(emptyGraph.getAllNodes().isEmpty());
    }

    @Test
    public void shouldCreateGraphWithSingleNode() throws Exception {
        final Graph testGraph = new Graph().addNode(0);

        assertEquals(1, testGraph.getAllNodes().size());
        assertEquals(newSet(0), testGraph.getAllNodes());
    }

    @Test
    public void shouldCreateMoreComplexGraph() throws Exception {
        final Graph testGraph = new Graph()
                .addConnection(0, 1)
                .addConnection(1, 2)
                .addConnection(2, 0)
                .addConnection(3, 1);

        assertEquals(4, testGraph.getAllNodes().size());
        assertEquals(newSet(0, 1, 2, 3), testGraph.getAllNodes());
        assertEquals(newSet(1, 2), testGraph.getNeighbors(0));
        assertEquals(newSet(0, 2, 3), testGraph.getNeighbors(1));
        assertEquals(newSet(0, 1), testGraph.getNeighbors(2));
        assertEquals(newSet(1), testGraph.getNeighbors(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNeighborsForUnknownNode() throws Exception {
        final Graph testGraph = new Graph();
        testGraph.getNeighbors(-1);
    }
}