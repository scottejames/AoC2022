package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Direction;
import com.scottejames.aoc.util.Point;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 extends AbstractDay {
    private List<String> sample;


    public Day9() throws IOException{
        super(9);
        sample = List.of(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"
        );
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();
        Point head = new Point(0,0);
        Point tail = new Point(0,0);
        Set<Point> visitedSet = new HashSet<>();
        for (String i: input){
            String[] split = i.split(" ");
            Direction direction = Direction.fromStr(split[0]);
            int distance = Integer.parseInt(split[1]);
            for (int d = 0;d< distance;d++){
                head = moveHead(head,direction);
                tail = moveTail(head,tail);
                visitedSet.add(tail);
            }
        }
        return String.valueOf(visitedSet.size());
    }

    @Override
    public String solvePart2() {
        List<String> input = getListString();
        Point head = new Point(0,0);
        Point[] tail = new Point[9];
        for (int i =0;i <tail.length;i++){
            tail[i] = new Point();
        }
        Set<Point> visitedTails = new HashSet<>();
        for (String i: input) {
            String[] split = i.split(" ");
            Direction direction = Direction.fromStr(split[0]);
            int distance = Integer.parseInt(split[1]);
            for (int d = 0; d < distance; d++) {
                head = moveHead(head,direction);
                tail[0] = moveTail(head, tail[0]);
                for (int t = 0; t < tail.length- 1; t++) {
                    tail[t+1] = moveTail(tail[t], tail[t+1]);
                }
                visitedTails.add(tail[tail.length - 1]);

            }

        }
        return String.valueOf(visitedTails.size());
    }

    private Point moveHead(Point p, Direction d){
        return switch (d){
            case LEFT -> p.add(1,0);
            case RIGHT -> p.add(-1,0);
            case UP -> p.add(0,-1);
            case DOWN -> p.add(0,1);

        };
    }

    private Point moveTail(Point head, Point tail){
        Point delta = head.delta(tail);

        if ((Math.abs(delta.x) == 2) || (Math.abs(delta.y) == 2)){
            return tail.add(Integer.signum(delta.x),Integer.signum(delta.y));
        }
        return tail;
    }
}
