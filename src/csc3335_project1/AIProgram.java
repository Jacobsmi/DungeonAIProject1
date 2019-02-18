package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;
import csc3335.dungeon_crawl.Room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    public void searchTree(){
        Node swordRoom = findSwordRoom(tree.head);
        List<Node> path = new ArrayList<>();
        List<Node> finalPath;
        finalPath = searchForSword(tree.head, swordRoom, path);
        for(Node n: finalPath){
            System.out.println(n.room.ROOM_ID);
        }
    }
    public void getRoomNeighbors(Node n){
        for(Room t : n.room.getNeighbors()){
            Node node = new Node(t, d.containsSword(t), d.containsExit(t));
            if(tree.isNotNode(node)){
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
    }
    public Node findSwordRoom(Node head){
        for(Node n : tree.allNodes){
            if(n.hasSword == true){
                return n;
            }
        }
        return null;
    }
    //IterativeDeepeningDepthFirstSearch
    public List searchForSword(Node n, Node goal, List path){
        path.add(n);
        for(Node l : n.children){
            if (l == goal){
                System.out.println("Found room");
                return path;
            }
        }
        for(Node l : n.children){
            return searchForSword(l, goal, path);
        }
        return null;
    }
}
