package com.scottejames.aoc2022.support;

import com.scottejames.aoc.util.Op;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Monkey {
    private final int name;

    private final int rhs;
    private final int test;
    private final int throwTrue;
    private final int throwFalse;
    private Op operation;
    public int counter = 0;
    public Queue<Item> items = new LinkedList<>();
    private int lcm;

    public Monkey(int name,
                  int rhs,
                  int test,
                  Op op,
                  int throwTrue,
                  int throwFalse,
                  List<Integer> worryLevels,
                  int lcm){
        this.name = name;
        this.rhs = rhs;
        this.test = test;
        this.operation = op;
        this.throwTrue = throwTrue;
        this.throwFalse = throwFalse;
        this.operation = operation;
        this.lcm = lcm;

        var  itemlist = worryLevels.stream()
                                    .map(worryLevel -> new Item(worryLevel))
                                    .collect(Collectors.toList());
        items.addAll(itemlist);
    }

    public int evaluate(Item item,boolean div){
        counter++;
        long newWorry = operation.doOperation(item.worryLevel,rhs);
        if (div==true)
            newWorry = newWorry/3;
        else
            newWorry = newWorry % lcm;
        item.worryLevel = newWorry;
        newWorry = newWorry % test;
        if (newWorry % test ==0){
            return throwTrue;
        } else {
            return throwFalse;
        }
    }
    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[ "," ]");
        for (Item item:items){
            joiner.add(String.valueOf(item.worryLevel));
        }
        return "<" + name + "> : " + joiner.toString() + " count " + counter;
    }

}
