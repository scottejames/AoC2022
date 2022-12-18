package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc2022.support.Range;
import com.scottejames.aoc2022.support.Sensor;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 extends AbstractDay{
    public List<String> sample;

    public Day15() throws IOException{
        super(15);
        sample = List.of(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
        );
    }
    @Override
    public String solvePart1() {

        List<Sensor> sensors = getInput(getListString());
        int row = 2000000;
        Stack<Range> combined = getRanges(sensors, row);
        long total = combined.stream().
                mapToInt(Range::size).sum() - sensors.stream().filter(s -> s.beaconY == row).
                map(s -> s.beaconX).
                distinct().
                count();

        return String.valueOf(total);
    }

    @Override
    public String solvePart2() {
        List<Sensor> sensors = getInput(getListString());
        int row = 2000000;
        long total;
        long result=0;
        for(int i=0; i<4000000; i++) {
            Stack<Range> combined = getRanges(sensors, i);

            combined.get(0).min = Math.max(0, combined.get(0).min);
            combined.peek().max = Math.min(4000000, combined.peek().max);

            total = combined.stream().mapToInt(Range::size).sum();


            if( total != 4000001 ) {
                for( int ind=0; ind<combined.size()-1; ind++ ) {

                    if(combined.get(ind).max < combined.get(ind+1).min) {

                        result = (4000000L*(combined.get(ind).max+1) + i);
                        break;
                    }
                }
            }
        }
        return String.valueOf(result);
    }

    Stack<Range> getRanges(List<Sensor> sensors, int row){
        // Get ranges in order for chosen row
        List<Range> ranges = sensors.stream()
                .map(s -> s.rangeAt(row))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(r -> r.min))
                .toList();

        Stack<Range> combined = new Stack<>();
        for(Range current: ranges) {
            if( combined.empty() || combined.peek().max < current.min ) {
                combined.push(current);
            }
            else {
                combined.peek().max = Math.max(combined.peek().max, current.max);
            }
        }
        return combined;
    }

    List<Sensor> getInput(List<String> input){
        List<Sensor> result = new ArrayList<Sensor>();
        for(String line: input){
            Pattern pattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
            Matcher m = pattern.matcher(line);
            m.matches();
            result.add(new Sensor(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
        }
        return result;

    }
}
