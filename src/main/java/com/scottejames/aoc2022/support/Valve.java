package com.scottejames.aoc2022.support;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Valve {

    private static final Pattern PATTERN = Pattern
            .compile("Valve (\\w+) has flow rate=(\\d+); \\w+ \\w+ to \\w+ (.+)");

    public String id;
    public int flowRate;
    public List<String> available;

    Valve(String id, int flowRate, List<String> available) {
        this.id = id;
        this.flowRate = flowRate;
        this.available = available;
    }

    public static Valve parse(String line) {
        var matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            String id = matcher.group(1);
            int rate = Integer.parseInt(matcher.group(2));
            List<String> next = Arrays.asList(matcher.group(3).trim().split(", "));
            return new Valve(id, rate, next);
        }
        throw new IllegalArgumentException("does not match: " + line);
    }
}
