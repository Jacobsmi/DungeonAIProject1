package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;
import csc3335.dungeon_crawl.Room;

import java.util.*;

/**
 * This class represents creates an instance of an an AI that is able to take in
 * the layout of of a dungeon generated from the Dungeon and Room classes in the
 * csc3335.dungeon_crawl package and find the elements of the dungeon necessary to escape it.
 *
 * @author Michael Jacobs and Vatsal Khatri
 */
public class AIProgram {
    //Declaring global variables
    Dungeon d;
    Tree tree;
    Room exit;
    boolean hasKey = false;
    HashSet<Node> nodes = new HashSet<>();
    /**
     * Overloaded: Initialize the AIProgram with a dungeon that contains information
     * for the dungeon the AI will be solving.
     *
     * @param d the dungeon that the AI will be solving
     */
    public AIProgram(Dungeon d){
        this.d = d;
    }
    /**
     *
     */
    public void createTree(){
        Room inRoom = d.getRandomRoom();
        Node head = new Node(inRoom);
        nodes.add(head);
        tree = new Tree(head);
        getRoomNeighbors(head);
    }
    /**
     * Contains all method calls necessary for the search function of the AI
     *
     */
    public void search(){
        Node inRoom = searchForSword(tree.head);
        if(inRoom != null){
            inRoom = searchForDragon(inRoom);
        }
        if(inRoom != null){
            searchForExit(inRoom);
        }
        checkWin();
    }
    /**
     * Gets all the rooms in the dungeon and sets the possible rooms they can move to
     *
     * @param n The head node aka the room the AI starts in
     */
    public void getRoomNeighbors(Node n){
        Deque<Node> newNodes = new LinkedList<>();
        newNodes.addLast(n);
        while(!newNodes.isEmpty()){
            Node m = newNodes.removeFirst();
            for(Room t : m.room.getNeighbors()){
                Node node = new Node(t);
                if(!tree.allNodes.contains(t)){
                    m.children.add(node);
                    newNodes.addLast(node);
                    tree.allNodes.add(t);
                    nodes.add(node);
                }
                else{
                    m.children.add(getNode(t));
                }
            }
        }
    }
    /**
     * Now that the layout of the dungeon is known the AI can now search for the sword by using a combination of belief
     * states and a hill climb search that chooses a room that could contain the sword over one that could not.
     *
     * @param n The head node aka the room the AI starts in
     */
    public Node searchForSword(Node n) {
        HashSet<Node> swordPredictions = new HashSet<>();
        swordPredictions.addAll(nodes);
        Node nextNode = null;
        Node current = n;
        Node sword = null;
        Node predictedNode = null;
        Node previous= null;
        boolean swordFound = false;
        while(!swordFound){
            if(d.containsExit(current.room)){
                current.hasExit = true;
            }
            if(d.containsSword(current.room)){
                System.out.println("Sword found!");
                sword = current;
                swordFound = true;
            }
            HashSet<Node> possibleNodes = new HashSet<>();
            for(Node m : current.children){
                possibleNodes.add(m);
                possibleNodes.remove(previous);
            }
            for(Node x: swordPredictions){
                predictedNode = x;
            }

            if(swordPredictions.isEmpty() || swordPredictions.size() == 0){
                swordPredictions.addAll(nodes);
            }
            else{
                swordPredictions.remove(current);
            }
            previous = current;
            current = predictedNode;
        }
        return sword;
    }
    public Node searchForDragon(Node n) {
        HashSet<Node> dragonPredictions = new HashSet<>();
        dragonPredictions.addAll(nodes);
        Node predictedNode = null;
        Node current;
        current = n;
        Node dragon = null;
        Node previous = null;
        while (!hasKey){
            if (d.containsDragon(current.room)) {
                hasKey = true;
                dragon = current;
                return dragon;
            }
            HashSet<Node> possibleNodes = new HashSet<>();
            for(Node m : current.children){
                possibleNodes.add(m);
                possibleNodes.remove(previous);
            }
            for(Node x: dragonPredictions){
                predictedNode = x;
            }

            if(dragonPredictions.isEmpty() || dragonPredictions.size() == 0){
                dragonPredictions.addAll(nodes);
            }
            else{
                dragonPredictions.remove(current);
            }
            previous = current;
            current = predictedNode;

        }
        return null;
    }
    public void searchForExit(Node n) {
        HashSet<Node> exitPredictions = new HashSet<>();
        exitPredictions.addAll(nodes);
        Node predictedNode = null;
        boolean hasExit = false;
        Node current;
        current = n;
        Node previous = null;
        for(Node m: nodes){
            if(m.hasExit == true){
                current = m;
            }
        }
        while (!hasExit){
            if (d.containsExit(current.room)) {
                exit = current.room;
                hasExit = true;
            }
            HashSet<Node> possibleNodes = new HashSet<>();
            for(Node m : current.children){
                possibleNodes.add(m);
                possibleNodes.remove(previous);
            }
            for(Node x: exitPredictions){
                predictedNode = x;
            }

            if(exitPredictions.isEmpty() || exitPredictions.size() == 0){
                exitPredictions.addAll(nodes);
            }
            else{
                exitPredictions.remove(current);
            }
            previous = current;
            current = predictedNode;

        }
    }
    public void checkWin(){
        if(hasKey == true && d.containsExit(exit)){
            System.out.println("--------------------Escaped the dungeon--------------------------");
        }
        else{
            System.out.println("!!!!!!!!!!!!!!!!!!!!Did not solve the dungeon!!!!!!!!!!!!!!!!!!!!");
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
