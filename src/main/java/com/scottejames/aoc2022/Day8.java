package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.List;

import static com.scottejames.aoc.util.ArrayHelper.convertToArrayOfIntArrays;

public class Day8 extends AbstractDay {
    List<String> sample;
    public Day8() throws IOException {
        super(8);
        sample = List.of(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390");
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();
        int[][] trees = convertToArrayOfIntArrays(input);

        int innerCount = 0;
        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[0].length - 1; j++) {
                if (isTreeVisibleFromOutside(trees, i, j)) {
                    innerCount++;
                }
            }
        }
        int outerCount = trees.length  *2 +(trees[0].length - 2) *2;
        int count = innerCount + outerCount;
        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        long score = Long.MIN_VALUE;
        List<String> input = getListString();
        int[][] trees = convertToArrayOfIntArrays(input);

        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[0].length - 1; j++) {
                final long scenicScore = getScenicScore(trees, i, j);
                score = Math.max(scenicScore, score);
            }
        }
        return String.valueOf(score);
    }
    private static boolean isTreeVisibleFromOutside(final int[][] trees, final int row, final int column) {
        final int tree = trees[row][column];

        return isVisibleUp(trees, tree, row, column)
                || isVisibleDown(trees, tree, row, column)
                || isVisibleLeft(trees, tree, row, column)
                || isVisibleRight(trees, tree, row, column);
    }

    private static boolean isVisibleUp(final int[][] trees, final int tree, final int row, final int column) {
        for (int i = (row - 1); i >= 0; i--) {
            if (tree <= trees[i][column]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleDown(final int[][] trees, final int tree, final int row, final int column) {
        for (int i = (row + 1); i < trees.length; i++) {
            if (tree <= trees[i][column]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleLeft(final int[][] trees, final int tree, final int row, final int column) {
        for (int j = (column - 1); j >= 0; j--) {
            if (tree <= trees[row][j]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleRight(final int[][] trees, final int tree, final int row, final int column) {
        for (int j = (column + 1); j < trees[0].length; j++) {
            if (tree <= trees[row][j]) {
                return false;
            }
        }

        return true;
    }
    private static long getScenicScore(final int[][] trees, final int row, final int column) {
        final int tree = trees[row][column];

        return getScenicScoreUp(trees, row, column, tree)
                * getScenicScoreDown(trees, row, column, tree)
                * getScenicScoreLeft(trees, row, column, tree)
                * getScenicScoreRight(trees, row, column, tree);
    }

    private static long getScenicScoreUp( int[][] trees,  int row,  int column,  int tree) {
        long upScore = 0;
        int start = row - 1;

        for (int i = start; i >= 0; i--) {
            if (tree <= trees[i][column]) {
                upScore++;
                break;
            }
            upScore++;
        }
        return upScore;
    }

    private static long getScenicScoreDown(final int[][] trees, final int row, final int column, final int tree) {
        long downScore = 0;
        for (int i = (row + 1); i < trees.length; i++) {
            if (tree <= trees[i][column]) {
                downScore++;
                break;
            }
            downScore++;
        }
        return downScore;
    }

    private static long getScenicScoreLeft(final int[][] trees, final int row, final int column, final int tree) {
        long leftScore = 0;
        for (int j = (column - 1); j >= 0; j--) {
            if (tree <= trees[row][j]) {
                leftScore++;
                break;
            }
            leftScore++;
        }
        return leftScore;
    }

    private static long getScenicScoreRight(final int[][] trees, final int row, final int column, final int tree) {
        int rightScore = 0;
        for (int j = (column + 1); j < trees[0].length; j++) {
            if (tree <= trees[row][j]) {
                rightScore++;
                break;
            }
            rightScore++;
        }
        return rightScore;
    }
}
