package csc3335_project1;

import csc3335.dungeon_crawl.Dungeon;

/**
 * Example starter code for CSC 3335 Project 1
 * @author stuetzlec
 */
public class CSC3335_Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dungeon d = new Dungeon(3000, 1, true);
        d.generateDungeon();
        System.out.println("Done generating dungeon");
            //d.printDungeon();
        AIProgram bot = new AIProgram(d);
        bot.createTree();bot.search();
    }   
}
