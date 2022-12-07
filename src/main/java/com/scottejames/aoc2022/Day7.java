package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 extends AbstractDay {

    List<String> sample;

    public Day7() throws IOException {
        super(7);

        sample = List.of(
                "$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k"
        );
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();
        Node root = new Node("/");
        buildDirectory(input, root);

        return String.valueOf(sumDirs(root));
    }
    @Override
    public String solvePart2() {
        List<String> input = getListString();
        Node root = new Node("/");
        buildDirectory(input, root);
        long required = 30000000 - (70000000 - dirSize(root));

        return String.valueOf(findSmallestDir(root,required));
    }
    private static void buildDirectory(List<String> input, Node root) {
        Node curr = root;
        for (String ip: input){
            if (ip.startsWith("$")){
               String command = ip.split(" ")[1];

               switch (command){
                   case "cd":
                       String args = ip.split(" ")[2];
                       if (args.equals("/")){
                           // swallow this
                       }else if (args.equals("..")){
                           curr = curr.parent;
                       } else {
                           Node next = new Node(args,curr);
                           curr.addChild(next);
                           curr = next;
                       }
                       break;
                   case"ls":
                       // No use for this today
                       break;
               }
            } else {
                if (ip.startsWith("dir")) {
                    // not sure we do anything with this.
                } else {
                    int size = Integer.parseInt(ip.split(" ")[0]);
                    curr.totalFileSize+=size;
                }
            }
        }
    }

    public long dirSize(Node dir){
        long total = 0;
        for (Node n: dir.contents){
            total += dirSize(n);
        }
        total += dir.totalFileSize;
        return total;
    }
    public long sumDirs(Node dir){
        long total = 0;
        for (Node n: dir.contents){
            total += sumDirs(n);
        }
        if (dirSize(dir) <= 100000){
            total += dirSize(dir);
        }
        return total;
    }

    public long findSmallestDir(Node dir, long required){
        long smallest = Long.MAX_VALUE;
        for (Node n: dir.contents)
            smallest = Math.min(smallest,findSmallestDir(n,required));
        if (dirSize(dir) > required)
            smallest = Math.min(smallest, dirSize(dir));
        return smallest;
    }

    public static class Node {
        public String name;
        public List<Node> contents = new ArrayList<>();
        public Node parent;
        public int totalFileSize;

        public Node(String name) {
            this.name = name;
            this.parent = null;
        }
        public Node(String name,Node parent) {
            this.name = name;
            this.parent = parent;
        }

        public void addChild(Node file) {
            this.contents.add(file);
        }

    }
}
