package com.symat.interview.breadth_first;


import java.util.*;

public class Graph {
    private final SortedMap<Integer, SortedSet<Integer>> neighborInfo = new TreeMap<>();

    public Set<Integer> getAllNodes() {
        return Collections.unmodifiableSet(neighborInfo.keySet());
    }

    public Set<Integer> getNeighbors(final int nodeId) {
        if (!neighborInfo.containsKey(nodeId))
            throw new IllegalArgumentException("unknown node id: " + nodeId);

        return Collections.unmodifiableSortedSet(neighborInfo.get(nodeId));
    }

    public Graph addConnection(final int src, final int trg) {
        addNode(src);
        addNode(trg);
        neighborInfo.get(src).add(trg);
        neighborInfo.get(trg).add(src);
        return this;
    }

    public Graph addNode(final int nodeId) {
        if (!neighborInfo.containsKey(nodeId))
            neighborInfo.put(nodeId, new TreeSet<>());
        return this;
    }
}
