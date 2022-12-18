package com.scottejames.aoc2022.support;

public class Sensor {
    public int x;
    public int y;
    public int beaconX;
    public int beaconY;

    public Sensor(int x, int y, int beaconX, int beaconY) {
        this.x = x;
        this.y = y;
        this.beaconX = beaconX;
        this.beaconY = beaconY;
    }
    public Range rangeAt(int row) {
        int dist = Math.abs(x-beaconX)+ Math.abs(y-beaconY);
        if( row < y-dist || row > y+dist ) {
            return null;
        }
        return new Range( x-(dist - Math.abs(y-row)), x+ (dist - Math.abs(y-row)));
    }
}