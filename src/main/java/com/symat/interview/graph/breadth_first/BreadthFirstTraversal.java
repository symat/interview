package com.symat.interview.graph.breadth_first;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BreadthFirstTraversal {

    public static List<Integer> traverse(final Graph graph, final int rootNode) {
        if (!graph.getAllNodes().contains(rootNode))
            throw new IllegalArgumentException("invalid starting node");

        return traverseFurther(graph, rootNode, new HashSet<>());
    }

    private static List<Integer> traverseFurther(final Graph testGraph, final int rootNode,
                                                 final Set<Integer> visitedNodes) {
        final List<Integer> traversal = new LinkedList<>();
        traversal.add(rootNode);
        visitedNodes.add(rootNode);
        final Set<Integer> children = testGraph.getNeighbors(rootNode);
        for (final Integer child : children) {
            if (!visitedNodes.contains(child)) {
                traversal.addAll(traverseFurther(testGraph, child, visitedNodes));
            }
        }
        return traversal;
    }
}
