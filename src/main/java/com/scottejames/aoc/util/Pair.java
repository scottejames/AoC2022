package com.scottejames.aoc.util;

import java.util.Objects;

public class Pair<LHS,RHS>{

    LHS lhs;
    RHS rhs;

    public Pair(LHS lhs, RHS rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "lhs=" + lhs +
                ", rhs=" + rhs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(lhs, pair.lhs) && Objects.equals(rhs, pair.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }

    public LHS getLhs() {
        return lhs;
    }

    public void setLhs(LHS lhs) {
        this.lhs = lhs;
    }

    public RHS getRhs() {
        return rhs;
    }

    public void setRhs(RHS rhs) {
        this.rhs = rhs;
    }

    public static class Points extends Pair<Point, Point> {

        public Points(Point point, Point point2) {
            super(point, point2);
        }
    }
}