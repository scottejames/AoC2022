package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends AbstractDay{
    private List<String> sample = null;
    public Day13() throws IOException {
        super(13);
        sample = List.of(
                "[1,1,3,1,1]",
                "[1,1,5,1,1]",
                "",
                "[[1],[2,3,4]]",
                "[[1],4]",
                "",
                "[9]",
                "[[8,7,6]]",
                "",
                "[[4,4],4,4]",
                "[[4,4],4,4,4]",
                "",
                "[7,7,7,7]",
                "[7,7,7]",
                "",
                "[]",
                "[3]",
                "",
                "[[[]]]",
                "[[]]",
                "",
                "[1,[2,[3,[4,[5,6,7]]]],8,9]",
                "[1,[2,[3,[4,[5,6,0]]]],8,9]");
    }

    @Override
    public String solvePart1() {
        List<String> input = getListString();
        List<Pair<String, String>> pairs = new ArrayList<>();

        for (int i = 0; i < (input.size() + 1) / 3; i++) {
            var pair = new Pair(input.get(i * 3), input.get(i * 3 + 1));
            pairs.add(pair);
        }
        int count = 0;

        for (int i = 0; i < pairs.size(); i++){
            String lhs = pairs.get(i).getLhs();
            String rhs = pairs.get(i).getRhs();
            if (compareStrings(lhs, rhs) == true) {
             //   System.out.println("Correct: " + (i + 1));
                count+=i +1;
            }
        }

        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        List<String> input = getListString();
        input = input.stream().filter(i->!i.isEmpty()).collect(Collectors.toList());
        input.add("[[2]]");
        input.add("[[6]]");
        Collections.sort(input, comparator);
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("[[2]]")) {
                i1 = i + 1;
            } else if (input.get(i).equals("[[6]]")) {
                i2 = i + 1;
            }
        }
        return String.valueOf(i1 * i2);
    }
    private static Comparator<String> comparator = new Comparator<>() {
        @Override
        public int compare(String left, String right) {
            if (compareStrings(left, right)) {
                return -1;
            }
            return 1;
        }
    };


    public static Boolean compareStrings(String lhs, String rhs){


        if (closing(lhs) && closing(rhs)) {
            return null;
        } else if (closing(lhs)) {
            return true;
        } else if (closing(rhs)) {
            return false;
        }
       // System.out.println("Comparing " + lhs + " and " + rhs);
        boolean leftArray = isArray(lhs);
        boolean rightArray = isArray(rhs);

        // Process the arrays
        if (leftArray && rightArray) {
            int leftClosing = findClosing(lhs);
            int rightClosing = findClosing(rhs);
            Boolean compared = compareStrings(lhs.substring(1, leftClosing),
                    rhs.substring(1, rightClosing));
            if (compared != null) {
                return compared;
            }
            lhs = lhs.substring(leftClosing);
            rhs = rhs.substring(rightClosing);

        } else if (leftArray && !rightArray) {
            return compareStrings(lhs, toArray(rhs));
        } else if (!leftArray && rightArray) {
            return compareStrings(toArray(lhs), rhs);
        }
        int l = Character.getNumericValue(lhs.charAt(0));
        int r = Character.getNumericValue(rhs.charAt(0));
        if (l == 1 && lhs.length() > 1 && lhs.charAt(1) == '0') {
            l = 10;
        }
        if (r == 1 && rhs.length() > 1 && rhs.charAt(1) == '0') {
            r = 10;
        }
        if (l != -1) {
  //          System.out.println("Comparing integers " + l + " and " + r);
        }
        if (l < r) {
            return true;
        }

        if (l > r) {
            return false;
        }
        return compareStrings(lhs.substring(l == 10 ? 2 : 1), rhs.substring(l == 10 ? 2 : 1));
    }

    public static Boolean isArray(String test){
        return (test.charAt(0) == '[');
    }

    private static boolean closing(String s) {
        return s.isEmpty() || s.equals("]");
    }

    private static String toArray(String s) {
        if (s.length() > 1 && s.substring(0, 2).equals("10")) {
            s = "[10]" + s.substring(2);
        } else {
            s = "[" + s.charAt(0) + "]" + s.substring(1);
        }
        return s;
    }
    private static int findClosing(String s) {
        int count = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == '[') {
                count++;
            } else if (c == ']') {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }
        return 0;
    }
}
