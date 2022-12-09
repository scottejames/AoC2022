package com.scottejames.aoc.util;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static Direction fromStr(String s) {
        char c = s.charAt(0);
        return switch (c) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            case 'U' -> UP;
            case 'D' -> DOWN;
            default -> throw new IllegalArgumentException("Unknown direction: " + c);
        };
    }
    public static Direction fromChar(char c) {
        return switch (c) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            case 'U' -> UP;
            case 'D' -> DOWN;
            default -> throw new IllegalArgumentException("Unknown direction: " + c);
        };
    }
}
