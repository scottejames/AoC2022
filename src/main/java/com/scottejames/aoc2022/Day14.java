package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Grid;
import com.scottejames.aoc.util.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14 extends AbstractDay {
    Grid<Character> grid = null;

    public List<String> sample;
    public Day14() throws IOException{
        super(14);
        sample = List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9");
    }

    @Override
    public String solvePart1() {
        List<String> input = getListString();
        grid = new Grid<>();
        loadData(input);
        int results = 0;
        int maxDepth = grid.getHeight();

        while (dropSand(maxDepth)){
            results++;
        }

    //    grid.showGrid();

        return String.valueOf(results);
    }
    @Override
    public String solvePart2() {
        List<String> input = getListString();
        grid = new Grid<>();
        loadData(input);
        int maxDepth = grid.getHeight() + 2;


        // add floor!
        for (int i = 500 - maxDepth - 5; i <= 500 + maxDepth + 5; i++) {
            grid.add(new Point(i, maxDepth),'=');
        }
        int results = 0;

        while (dropSand(maxDepth+1)){

            results++;
            if (grid.get(new Point(500,0))!=null) {
                //filled up origin so we are done!
                break;
            }
        }
        grid.showGrid();
        return String.valueOf(results);
    }
    private void loadData(List<String> input) {
        for (String line: input) {
            List<Point> rockPoints = new ArrayList<Point>();

            var rocks = line.split("->");
            for (String rock : rocks) {
                rockPoints.add(new Point(rock));
            }

            for (int i = 0; i < rockPoints.size() - 1; i++) {
                Point start = rockPoints.get(i);
                Point end = rockPoints.get(i + 1);

                List<Point> trail = start.trail(end);
                grid.setListOfPoints(trail,'#');

            }

        }
    }

    public boolean dropSand(int maxDepth){

        Point p = new Point(500,0);
        while (true) {
            if (p.y > maxDepth){
                return false;
            }
            var down = new Point(p.x, p.y + 1);
            var left = new Point(p.x-1, p.y + 1);
            var right = new Point(p.x+1, p.y + 1);

            if (grid.get(down)==null){
                p.y++;
            } else if (grid.get(left)==null){
                p.x--;
                p.y++;
            } else if (grid.get(right)==null){
                p.x++;
                p.y++;
            } else {
                grid.add(p,'S');
                return true;
            }
        }
    }

}
