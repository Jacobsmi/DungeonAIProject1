package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;
import csc3335.dungeon_crawl.Room;

import java.util.HashSet;
import java.util.Set;

public class AIProgram {
    Dungeon d;
    Tree tree;
    public AIProgram(Dungeon d){
        this.d = d;
    }
    public void createTree(){
        Room r = d.getRandomRoom();
        Node head = new Node(r, d.containsSword(r), d.containsExit(r));
        tree = new Tree(head);
        getRoomNeighbors(head);

    }
    public void getRoomNeighbors(Node n){
        System.out.println(n.room);
        Set<Node> neighbors = new HashSet<>();
        n.room.getNeighbors();
        for(Room t : n.room.getNeighbors()){
            Node node = new Node(t, d.containsSword(t), d.containsExit(t));
            neighbors.add(node);
        }
        for(Node m : neighbors){
            if(tree.addNode(m)){
                n.children.add(m);
                getRoomNeighbors(m);
            }
        }
    }
}
