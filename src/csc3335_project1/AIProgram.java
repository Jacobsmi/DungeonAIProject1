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
        for(Room t : n.room.getNeighbors()){
            Node node = new Node(t, d.containsSword(t), d.containsExit(t));
            if(tree.isNotNode(node)){
                //System.out.println(n.room.ROOM_ID+" Neighbors "+ t.ROOM_ID);
                n.children.add(node);
                tree.addNode(node);
            }
            else{
                //System.out.println(n.room.ROOM_ID+" Neighbors "+ t.ROOM_ID + " but " + t.ROOM_ID + " is already a node");
            }
        }
        for(Room t : n.room.getNeighbors()){
            Node node = tree.returnNode(t);
            if(node.isNotNode){
                node.isNotNode =false;
                getRoomNeighbors(node);
            }
        }
        //n.printChildren();
    }
    //IterativeDeepeningDepthFirstSearch
    public void searchForSword(){
        Node goal = null;
        Set<Node> current = new HashSet<>();
        current.add(tree.head);
        boolean atGoal = false;
        for(Node n : tree.allNodes){
            if(n.hasSword == true){
                goal = n;
            }
        }
        while(!atGoal){
        }
    }
}
