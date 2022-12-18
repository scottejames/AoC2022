package com.scottejames.aoc2022.support;

public class Range {
    public int min, max;
    Range (int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int size() {
        return 1+max-min;
    }
}