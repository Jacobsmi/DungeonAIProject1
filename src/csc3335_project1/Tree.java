package csc3335_project1;

import csc3335.dungeon_crawl.Room;

import java.util.HashSet;

public class Tree {
    Node head;
    HashSet<Room> allNodes = new HashSet<>();
    public Tree(Node h){
        this.head = h;
        this.allNodes.add(head.room);
    }

}
