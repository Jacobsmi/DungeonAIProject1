package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;
import csc3335.dungeon_crawl.Room;

import java.util.*;

public class AIProgram {
    Dungeon d;
    Tree tree;
    Room exit;
    boolean hasKey = false;
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
    public void search(){
        searchForSword(tree.head);
        searchForDragon(tree.head);
        searchForExit(tree.head);
        checkWin();
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

    public void searchForSword(Node n) {
        HashSet<Node> swordPredictions;
        swordPredictions = nodes;
        Deque<Node> path = new LinkedList<>();
        Node nextNode = null;
        Node current = n;
        boolean swordFound = false;
        while(!swordFound){
            if(d.containsExit(current.room)){
                current.hasExit = true;
            }
            if(d.containsSword(current.room)){
                System.out.println("Sword found!");
                swordFound = true;
            }
            for(Node m : current.children){
                if(swordPredictions.contains(m)){
                    nextNode = m;
                }
            }
            path.addFirst(current);
            swordPredictions.remove(current);
            current = nextNode;
            if(swordPredictions.isEmpty()){
                System.out.println("Sword cannot be found");
                break;
            }
        }
    }
    public void searchForDragon(Node n) {
        HashSet<Node> dragonPredictions;
        dragonPredictions = nodes;
        Node nextNode = null;
        Node current = n;
        while(!hasKey){
            if(d.containsDragon(current.room)){
                hasKey = true;
                System.out.println("Dragon killed and key dropped");
                break;
            }
            for(Node m : current.children){
                if(dragonPredictions.contains(m)){
                    nextNode = m;
                }
            }
            dragonPredictions.remove(current);
            current = nextNode;
            if(dragonPredictions.isEmpty()){
                dragonPredictions = nodes;
            }
        }

    }
    public void searchForExit(Node n) {
        for(Node m: nodes){
            if(m.hasExit == true){
                System.out.println("Already have exit");
            }
        }
        HashSet<Node> exitPredictions;
        exitPredictions = nodes;
        Deque<Node> path = new LinkedList<>();
        Node nextNode = null;
        Node current = n;
        boolean exitFound = false;
        while(!exitFound){
            if(d.containsExit(current.room)){
                System.out.println("Exit found!");
                exit = current.room;
                exitFound = true;
            }
            for(Node m : current.children){
                if(exitPredictions.contains(m)){
                    nextNode = m;
                }
            }
            path.addFirst(current);
            exitPredictions.remove(current);
            current = nextNode;
            if(exitPredictions.isEmpty()){
                System.out.println("Exit cannot be found");
                break;
            }
        }
    }
    public void checkWin(){
        if(hasKey == true && d.containsExit(exit)){
            System.out.println("Escaped the dungeon");
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
