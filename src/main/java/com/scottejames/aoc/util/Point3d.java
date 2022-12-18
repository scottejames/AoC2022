package com.scottejames.aoc.util;

import java.util.HashSet;
import java.util.Set;

public class Point3d {
    public int x;
    public int y;
    public int z;

    public Point3d(){
        this.x=0;
        this.y=0;
        this.z=0;
    }
    public Point3d(int x,int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Point3d(String ip){
        String[] s = ip.split(",");
        this.x = Integer.parseInt(s[0].trim());
        this.y = Integer.parseInt(s[1].trim());
        this.z = Integer.parseInt(s[2].trim());

    }

    Set<Point3d> getCartesianNeighbours(){
       HashSet<Point3d> results = new HashSet<>();
       results.add(new Point3d(x-1,y,z));
       results.add(new Point3d(x,y-1,z));
       results.add(new Point3d(x,y,z-1));
       results.add(new Point3d(x+1,y,z));
       results.add(new Point3d(x,y+1,z));
       results.add(new Point3d(x,y,z+1));
       return results;

    }
}
