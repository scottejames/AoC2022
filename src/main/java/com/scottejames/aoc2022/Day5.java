package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day5 extends AbstractDay {
    private List<String> sample;
    ArrayList<ArrayDeque<Character>> crates = new ArrayList<>();

    public Day5() throws IOException{
        super(5);

        sample = List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        );
    }
    @Override
    public String solvePart1() {
        List<String> data = getListString();

        int lineNumber = generateCrates(data);

        for (lineNumber+=2; lineNumber< data.size(); lineNumber++) {
            String line = data.get(lineNumber);
            String[] strs = line.split(" ");
            int move = Integer.parseInt(strs[1]);
            int from = Integer.parseInt(strs[3]) - 1;
            int to = Integer.parseInt(strs[5]) - 1;

            for (int i = 0; i < move; i++) {
                Character c = crates.get(from).pop();
                crates.get(to).addFirst(c);
            }


        }
       

        return getResults();
    }
    @Override
    public String solvePart2() {
        List<String> data = getListString();

        int lineNumber = generateCrates(data);

        for (lineNumber += 2; lineNumber < data.size(); lineNumber++) {

            String line = data.get(lineNumber);
            System.out.println(line);
            String[] strs = line.split(" ");
            int move = Integer.parseInt(strs[1]);
            int from = Integer.parseInt(strs[3]) - 1;
            int to = Integer.parseInt(strs[5]) - 1;
            ArrayDeque<Character> temp = new ArrayDeque<>();

            for (int i = 0; i < move; i++) {
                Character c = crates.get(from).pop();
                temp.addFirst(c);
            }
            while(!temp.isEmpty()){
                crates.get(to).addFirst(temp.pop());
            }
        }
        return getResults();
    }

    private int generateCrates(List<String> data) {
        crates =new ArrayList<>();
        int nCrates = (data.get(0).length() /4);

        for (int i = 0; i <= nCrates; i++ ){
            crates.add(new ArrayDeque<>());
        }
        int lineNumber = 0;
        for (lineNumber = 0; lineNumber< data.size(); lineNumber++){
            String line = data.get(lineNumber);
            if (Character.isDigit(line.charAt(1))) break; // crates done
            for (int i=1 ; i < line.length(); i+=4 ) {
                if (line.charAt(i) != ' ') {
                    int crateNumber = (i - 1) / 4;
                    crates.get(crateNumber).add(line.charAt(i));
                }
            }
        }
        return lineNumber; // carry on parsing from here.
    }

    String getResults(){
        StringBuffer result = new StringBuffer();
        for (ArrayDeque<Character> stack : crates) {

            result.append(stack.peek());
        }
        return result.toString();
    }
    private void printStacks(){
        int crateId = 0;
        for (ArrayDeque<Character> crate: crates){
            crateId++;
            System.out.print("(" + crateId +")");
            Iterator<Character> it = crate.descendingIterator();

            while (it.hasNext()){
                Character c = it.next();
                System.out.print("[" + c + "] ");
            }
            System.out.println();

        }
    }

}
