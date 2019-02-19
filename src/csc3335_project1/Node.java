package csc3335_project1;

import csc3335.dungeon_crawl.Room;

import java.util.HashSet;
import java.util.Set;

public class Node {
    Room room;
    boolean isNotNode = true;
    boolean hasExit = false;
    Set<Node> children = new HashSet();
    Node parent = null;

    public Node(Room r){
        this.room = r;

    }

    public void printChildren(){
        System.out.println("The child of room "+this.room.ROOM_ID);
        for(Node m : this.children){
            System.out.println(m.room.ROOM_ID);
        }
    }
}
