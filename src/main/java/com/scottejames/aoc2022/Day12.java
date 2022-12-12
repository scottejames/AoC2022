package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Grid;
import com.scottejames.aoc.util.Point;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 extends AbstractDay {

    private List<String> sample = List.of(
            "Sabqponm", "abcryxxl", "accszExk", "acctuvwj", "abdefghi");
    private Grid<Character> grid = new Grid<>();

    public Day12() throws IOException {
        super(12);
        List<String> input = getListString();
        parse(input);
    }
    @Override
    public String solvePart1() {
            Point start = grid.locate('S');
            Point end = grid.locate('E');
            int length = length(start, grid);

            return String.valueOf(length);
    }

    @Override
    public String solvePart2() {
        List<Point> allA = grid.getAllDataMatching('a');
        int distance = allA.parallelStream().mapToInt(p -> length(p,grid)).min().orElseThrow();
        return String.valueOf(distance);
    }
    private void parse(List<String> input) {
        int row = 0;
        int column = 0;
        for (String s: input){
            for(char c: s.toCharArray()) {
                grid.add(new Point(column,row),c);
                column++;
            }
            row++;
            column=0;
        }
    }

    public int length(Point start, Grid<Character> grid) {
        Set<Point> visited = new HashSet<>();
        ArrayDeque<Path> queue = new ArrayDeque<>();
        queue.add(new Path(start, 0));
        while (!queue.isEmpty() && grid.get(queue.peek().end()) != 'E') {
            var next = queue.poll();
            for(var c:getNeighbours(next.end(),grid)){
                if (!visited.contains(c))
                    queue.add(new Path(c, next.stepCount() + 1));
                    visited.add(c);
            }
        }
        if (queue.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return queue.poll().stepCount();
    }

    public Set<Point> getNeighbours(Point point,Grid<Character>grid) {
        Set<Point> paths = point.getCartNeighboursPositive();
        paths = paths.stream().
                filter(p->(grid.withinGrid(p)==true)).
                filter(p->isValid(point,p)).
                collect(Collectors.toSet());

        return paths;
    }
    public boolean isValid(Point from, Point to){
        char fromChar = grid.get(from);
        char toChar = grid.get(to);
        if (fromChar=='S') fromChar = 'a';
        if (toChar=='E') toChar = 'z';
        return (int) toChar <= (int) fromChar + 1;

    }
    private static final record Path(Point end, int stepCount) {
    }
}
