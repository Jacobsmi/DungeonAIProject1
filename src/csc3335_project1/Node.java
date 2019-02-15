package csc3335_project1;

import csc3335.dungeon_crawl.Room;

import java.util.HashSet;
import java.util.Set;

public class Node {
    Room room;
    boolean hasSword;
    boolean hasExit;
    Set<Node> children = new HashSet();
    public Node(Room r, boolean hS, boolean hE){
        this.room = r;
        this.hasSword = hS;
        this.hasExit = hE;
    }
}
