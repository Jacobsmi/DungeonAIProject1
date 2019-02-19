package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;
import csc3335.dungeon_crawl.Room;

import java.util.*;

public class AIProgram {
    Dungeon d;
    Tree tree;
    Node head;
    HashSet<Node> nodes = new HashSet<>();
    public AIProgram(Dungeon d){
        this.d = d;
    }
    public void createTree(){
        Room inRoom = d.getRandomRoom();
        Node head = new Node(inRoom);
        nodes.add(head);
        tree = new Tree(head);
        getRoomNeighbors(head);
    }
    public void searchForS(){
        search(tree.head);
    }

    public void getRoomNeighbors(Node n){
        Deque<Node> newNodes = new LinkedList<>();
        newNodes.addLast(n);
        while(!newNodes.isEmpty()){
            Node m = newNodes.removeFirst();
            for(Room t : m.room.getNeighbors()){
                Node node = new Node(t);
                if(!tree.allNodes.contains(t)){
                    System.out.println(m.room.ROOM_ID+" Neighbors "+ t.ROOM_ID);
                    m.children.add(node);
                    newNodes.addLast(node);
                    tree.allNodes.add(t);
                    nodes.add(node);
                }
                else{
                    //m.children.add(getNode(t));
                    System.out.println(m.room.ROOM_ID+" Neighbors "+ t.ROOM_ID + " but " + t.ROOM_ID + " is already a node");
                }
            }
        }
    }

    public void search(Node n) {
        HashSet<Node> swordPredictions;
        swordPredictions = nodes;
        Deque<Node> path = new LinkedList<>();
        boolean goodChildren = false;
        Node current = n;
        boolean swordFound = false;
        while(!swordFound){
            if(!d.containsSword(current.room)){

            }
        }


    }
    public Node getNode(Room t){
        for(Node n: nodes){
            if(t == n.room){
                return n;
            }
        }
        return null;
    }

}
