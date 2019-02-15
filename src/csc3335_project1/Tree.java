package csc3335_project1;

import java.util.HashSet;
import java.util.Set;

public class Tree {
    Node head;
    Set<Node> allNodes = new HashSet<>();
    public Tree(Node h){
        this.head = h;
        addNode(head);
    }
    public boolean addNode(Node n){
        for(Node m : allNodes){
            if(m.room.ROOM_ID == n.room.ROOM_ID){
                return false;
            }
        }
        allNodes.add(n);
        return true;
    }
}
