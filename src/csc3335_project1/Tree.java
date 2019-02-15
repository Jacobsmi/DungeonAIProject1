package csc3335_project1;

import csc3335.dungeon_crawl.Room;

import java.util.HashSet;
import java.util.Set;

public class Tree {
    Node head;
    Set<Node> allNodes = new HashSet<>();
    public Tree(Node h){
        this.head = h;
        this.addNode(head);
    }
    //Returns true if not a node else returns false
    public boolean isNotNode(Node n){
        for(Node m : allNodes){
            if(m.room.ROOM_ID == n.room.ROOM_ID){
                return false;
            }
        }
        return true;
    }

    public void addNode(Node n){
        allNodes.add(n);
    }

    public Node returnNode(Room r){
        for(Node m : allNodes){
            if(m.room.ROOM_ID == r.ROOM_ID){
                return m;
            }
        }
        return null;
    }
}
