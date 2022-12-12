package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Direction;
import com.scottejames.aoc.util.Grid;
import com.scottejames.aoc.util.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
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
        List<Point> path = new ArrayList<>();
        distance(start,end,path);
       printPath(path);
        return String.valueOf(path.size());
    }

    @Override
    public String solvePart2() {
        return null;
    }

    public List<Point> distance(Point start, Point end, List<Point> path){
        if (start.equals(end)){
            return path; // Yay
        }
        path.add(start);
        Set<Point> paths = start.getCartNeighboursPositive();
        paths = paths.stream().filter(p->(grid.withinGrid(p)==true)).collect(Collectors.toSet());
        for (Point next: paths){
            if (path.contains(next)){
                continue;
            }
            if (isValid(start,next)){
                return distance(next,end,path);
            }
        }
        return null;
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
    public boolean isValid(Point from, Point to){
        char fromChar = grid.get(from);
        char toChar = grid.get(to);
        if (fromChar=='S') fromChar = 'a';
        if (toChar=='E') toChar = 'z';
        return (int) toChar <= (int) fromChar + 1;

    }
    private void parse(List<String> input) {
        int row = 0;
        int column = 0;
        for (String s: input){
            for(char c: s.toCharArray()) {
                grid.add(new Point(row,column),c);
                column++;
            }
            row++;
            column=0;
        }
        grid.showGrid();
    }





}
