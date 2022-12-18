package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc2022.support.Valve;

import java.io.IOException;
import java.util.*;

public class Day16 extends AbstractDay {
    List<String> sample;
    Map<String, Valve> valveMap = new HashMap<>();

    public Day16() throws IOException {
        super(16);
        sample = List.of(
                "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                "Valve HH has flow rate=22; tunnel leads to valve GG",
                "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                "Valve JJ has flow rate=21; tunnel leads to valve II");
        for(String line: sample){
            Valve v = Valve.parse(line);
            valveMap.put(v.id,v);
        }

    }


    @Override
    public String solvePart1() {
        Queue<Step> steps = new ArrayDeque<>();
        steps.add(new Step("AA", 0, 0, 0, new HashSet<String>(), Set.of("AA")));
        int maxReleased = Integer.MIN_VALUE;
        Step last = null;
        while (!steps.isEmpty()) {

        }
        return String.valueOf(maxReleased);
    }


    @Override
    public String solvePart2() {
        return null;
    }

    private final record Step(String at, int minute, int releases, int released, Set<String> open,
                              Set<String> visited) {

        Set<String> open(String v) {
            var copy = new HashSet<>(open);
            copy.add(v);
            return copy;
        }

        Set<String> visit(String v) {
            var copy = new HashSet<>(visited);
            copy.add(v);
            return copy;
        }

        int calcMaxRelease() {
            return released + (30 - minute) * releases;
        }
    }
}


