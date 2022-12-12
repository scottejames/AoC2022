package com.scottejames.aoc2022;

import com.scottejames.aoc.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Day12SLOW extends AbstractDay {
    private List<String> sample = List.of(
        "Sabqponm", "abcryxxl", "accszExk", "acctuvwj", "abdefghi");
    private Grid<Character> grid = new Grid<>();

    public Day12SLOW() throws IOException {
        super(12);
        List<String> input = getListString();
        parse(input);
    }
    @Override
    public String solvePart1() {
        Point start = grid.locate('S');
        Point end = grid.locate('E');

        NextPoint np = new NextPoint(grid);
        DepthFirstSearch dfs = new DepthFirstSearch(start,end,grid,np);

        List<Point> result = dfs.solve();

        printPathInGrid(result);
        grid.showGrid();
        printPath(result);
        return null;
    }

    @Override
    public String solvePart2() {
        return null;
    }

    private void printPathInGrid(List<Point> path){
        Point from = path.get(0);

        for (int i =1 ; i< path.size(); i++){
            Point to = path.get(i);
            char c = Direction.directionToChar(from,to);
            grid.add(from,c);
            from = to;
        }
    }
    private void printPath(List<Point> path){
        Point from = path.get(0);
        StringJoiner s = new StringJoiner(", ");
        for (int i =1 ; i< path.size(); i++){
            Point to = path.get(i);
            s.add("" + Direction.direction(from,to));
            from = to;
        }
        System.out.println(s.toString());
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
    public class NextPoint implements GetSetOfNewPoints{

        Grid<Character> grid;

        public NextPoint(Grid grid){
            this.grid = grid;
        }
        @Override
        public Set<Point> go(Point point) {
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
    }




}
