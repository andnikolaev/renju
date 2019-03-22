package com.nikolaev;

import com.nikolaev.simplemodel.Node;
import com.nikolaev.simplemodel.SimpleGame;
import com.nikolaev.simplemodel.SimpleSituation;

public class Start {

   private int[][] initSituatuin() {
      return new int[][]{
              {1, 1, 1, 0, 2},
              {1, 1, 1, 2, 2},
              {2, 0, 2, 2, 2},
              {2, 2, 1, 1, 2},
              {1, 1, 1, 2, 0}};
   }

   public static void main(String[] args) {
      Start start = new Start();
      Node node = new Node(new SimpleSituation(start.initSituatuin()), null);
      start.printSituation(node);
      SimpleGame simpleGame = new SimpleGame(node.getSituation().getPoints().length, 5);

      for (int i = 0; i < 5; i++) {

         System.out.println(simpleGame.checkGameFinished(node.getSituation()));
         System.out.println("BLACK STEP");
         node = simpleGame.generateNewSituation(node, 2);
         start.printSituation(node);
         int res = simpleGame.checkGameFinished(node.getSituation());
         if (printWinner(res)) {
            break;
         }
         System.out.println("WHITE STEP");
         node = simpleGame.generateNewSituation(node, 1);
         start.printSituation(node);
         res = simpleGame.checkGameFinished(node.getSituation());
         if (printWinner(res)) {
            break;
         }
         node = node.getParentNode().getParentNode();
         if (node == null) {
            System.out.println("NO WINNER");
            break;
         }
      }
   }

   private static boolean printWinner(int res) {
      if (res != 0) {
         if (res == 1) {
            System.out.println("WHITE WIN");
         }
         if (res == 2) {
            System.out.println("BLACK WIN");
         }
         return true;
      }
      return false;
   }

   private void printSituation(Node node) {
      for (int i = 0; i < node.getSituation().getPoints().length; i++) {
         for (int j = 0; j < node.getSituation().getPoints()[i].length; j++) {
            int point = node.getSituation().getPoints()[i][j];
            if (point == 0) {
               System.out.printf("%8s", "NONE");
            }
            if (point == 1) {
               System.out.printf("%8s", "WHITE");
            }
            if (point == 2) {
               System.out.printf("%8s", "BLACK");
            }
         }
         System.out.println();
      }
      System.out.println();
   }
}
