package com.scottejames.aoc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DepthFirstSearch <T>{
    private Point end;
    private Point start;
    private Grid<Character> grid;
    int itter = 0;

    private GetSetOfNewPoints getSetOfNewPoints;
    public DepthFirstSearch(Point start, Point end, Grid grid, GetSetOfNewPoints getSetOfNewPoints){
        this.end = end;
        this.start = start;
        this.grid = grid;
        this.getSetOfNewPoints = getSetOfNewPoints;
    }


    public List<Point> solve(){
        List<Point> path = new ArrayList<Point>();
        if (explore(grid, start, path )){
            return path;
        }
        return null;
    }

    private boolean explore(Grid grid, Point current, List<Point> path){
        itter++;
        if (itter %100000 ==0) System.out.println("Iteration = " + itter + " path size = " +  path.size());
        if (current.equals(end)) return true;
        path.add(current);

        Set<Point> neigbours = getSetOfNewPoints.go(current);

        for(Point next : neigbours){
            if (path.contains(next)) {
                continue;
            }
            if (explore(grid, next,path)){
                return true;
            }
        }
        path.remove(path.size()-1);
        return false;
    }

}
