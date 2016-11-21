package com.symat.interview.graph.breadth_first;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/*
    write a class that is responsible for traversing a graph breadth first starting from a given node
    graph should be provided as a node and edge list
    graph is undirected
    return value is the list of node ids
 */


public class BreadthFirstTraversalTest {

    public static final int NODE_A = 1;
    public static final int NODE_B = 2;
    public static final int NODE_C = 3;
    public static final int NODE_D = 4;
    public static final int NODE_E = 5;
    public static final int NODE_F = 6;

    @Test
    public void shouldTraverseGraphWithSingleNode() throws Exception {
        final Graph testGraph = new Graph().addNode(NODE_A);
        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);

        assertEquals(asList(NODE_A), traversal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowForInvalidStartNode() throws Exception {
        final Graph testGraph = new Graph();
        BreadthFirstTraversal.traverse(testGraph, NODE_A);
    }

    @Test
    public void shouldTraverseGraphWithSingleLink() throws Exception {
        final Graph testGraph = new Graph().addConnection(NODE_A, NODE_B);

        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);
        assertEquals(asList(NODE_A, NODE_B), traversal);
    }

    @Test
    public void shouldTraverseChainGraph() throws Exception {
        final Graph testGraph = new Graph()
                .addConnection(NODE_A, NODE_B)
                .addConnection(NODE_B, NODE_C)
                .addConnection(NODE_C, NODE_D);

        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);
        assertEquals(asList(NODE_A, NODE_B, NODE_C, NODE_D), traversal);
    }

    @Test
    public void shouldTraverseGraphWithLoop() throws Exception {
        final Graph testGraph = new Graph().addConnection(NODE_A, NODE_A);

        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);
        assertEquals(asList(NODE_A), traversal);
    }


    /*
            A --- B --- C ---- E -----F
               |               |
               |-------- D ----|
     */
    @Test
    public void shouldTraverseGraphWithCircle() throws Exception {
        final Graph testGraph = new Graph()
                .addConnection(NODE_A, NODE_B)
                .addConnection(NODE_A, NODE_D)
                .addConnection(NODE_B, NODE_C)
                .addConnection(NODE_C, NODE_E)
                .addConnection(NODE_D, NODE_E)
                .addConnection(NODE_E, NODE_F);

        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);
        assertEquals(asList(NODE_A, NODE_B, NODE_C, NODE_E, NODE_D, NODE_F), traversal);
    }

    @Test
    public void shouldNotReachUnreachableNode() throws Exception {
        final Graph testGraph = new Graph()
                .addConnection(NODE_A, NODE_B)
                .addNode(NODE_C);

        final List<Integer> traversal = BreadthFirstTraversal.traverse(testGraph, NODE_A);
        assertEquals(asList(NODE_A, NODE_B), traversal);
    }
}