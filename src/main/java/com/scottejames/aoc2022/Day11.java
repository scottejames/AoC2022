package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc2022.support.Item;
import com.scottejames.aoc2022.support.Monkey;

import java.io.IOException;
import java.util.*;


public class Day11 extends AbstractDay {

    List<Monkey> monkeyList = new ArrayList<>();
    int lcm = 0;
    public void initList(){
        monkeyList.clear();
        lcm = 7 * 13 * 5 * 19 * 2 * 11 * 17 * 3;

//        monkeyList.add(new Monkey(0,19,23,(a,b)->a*b,2,3, Arrays.asList(79,98),lcm));
//        monkeyList.add(new Monkey(1,6,19,(a,b)->a+b,2,0, Arrays.asList(54,65,75,74),lcm));
//        monkeyList.add(new Monkey(2, 0,13,(a,b)->a*a,1,3, Arrays.asList(79,60,97),lcm));
//        monkeyList.add(new Monkey(3, 3,17,(a,b)->a+b,0,1, Arrays.asList(74),lcm));

        monkeyList.add(new Monkey(0,11,7,(a,b) -> a * b,6,7,Arrays.asList(66,79),lcm));
        monkeyList.add(new Monkey(1,17,13,(a,b) -> a * b,5,2,Arrays.asList(84,94,94,81,98,75),lcm));
        monkeyList.add(new Monkey(2,8,5,(a,b) -> a + b,4,5,Arrays.asList(85,79,59,64,79,95,67),lcm));
        monkeyList.add(new Monkey(3,3,19,(a,b) -> a + b,6,0,Arrays.asList(70),lcm));
        monkeyList.add(new Monkey(4,4,2,(a,b) -> a + b,0,3,Arrays.asList(57,69,78,78),lcm));
        monkeyList.add(new Monkey(5,7,11,(a,b) -> a + b,3,4,Arrays.asList(65,92,60,74,72),lcm));
        monkeyList.add(new Monkey(6,0,17,(a,b) -> a * a,1,7,Arrays.asList(77,91,91),lcm));
        monkeyList.add(new Monkey(7,6,3,(a,b) -> a + b,2,1,Arrays.asList(76,58,57,55,67,77,54,99),lcm));

    }
    public Day11() throws IOException{
        super(11);

        /**
         * Monkey 0:
         *   Starting items: 79, 98
         *   Operation: new = old * 19
         *   Test: divisible by 23
         *     If true: throw to monkey 2
         *     If false: throw to monkey 3
         */



    }

    @Override
    public String solvePart1() {
            initList();

            for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeyList) {
                for (Item item : monkey.items) {
                    int destination = monkey.evaluate(item,true);
                    monkeyList.get(destination).items.add(item);
                }
                monkey.items.clear();
            }
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (Monkey monkey: monkeyList){
            set.add(monkey.counter);
        }
        int firstMonkey = set.pollLast();
        int secondMonkey= set.pollLast();
        return String.valueOf(firstMonkey*secondMonkey);
    }

    @Override
    public String solvePart2() {
        initList();

        for (Monkey m: monkeyList) {
            m.counter = 0;
        }

            for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeyList) {
                for (Item item : monkey.items) {
                    int destination = monkey.evaluate(item,false);
                    monkeyList.get(destination).items.add(item);
                }
                monkey.items.clear();
            }
        }
        for (Monkey m: monkeyList){
            System.out.println(m);
        }

        TreeSet<Long> set = new TreeSet<>();
        for (Monkey monkey: monkeyList){
            set.add(new Long(monkey.counter));
        }
        long firstMonkey = set.pollLast();
        long secondMonkey= set.pollLast();
        return String.valueOf(firstMonkey * secondMonkey);
    }
}
