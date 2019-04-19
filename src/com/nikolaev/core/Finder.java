package com.nikolaev.core;

import com.nikolaev.simplemodel.Node;

import java.util.List;

public class Finder {
   private Situation situation;
   private Game game;
   private SituationPrinter renjuPrinter;
   private boolean winner = false;

   public static int[][] initSituatuin() {
      return new int[][]{
              {1, 1, 1, 0, 2},
              {1, 1, 1, 2, 2},
              {2, 0, 2, 2, 2},
              {2, 2, 1, 1, 2},
              {1, 1, 1, 2, 0}};
   }

   public Finder(Situation situation, Game game, SituationPrinter printer) {
      this.situation = situation;
      this.game = game;
      this.renjuPrinter = printer;
   }

   public void startModelingDepthSearch(int maxStepsCount){
      Node node = new Node(situation, null);
      renjuPrinter.printSituation(node.getSituation());

      for (int i = 0; i < maxStepsCount; i++) {
         System.out.println(game.checkGoal(node.getSituation()));
         System.out.println("BLACK STEP");
         node = game.generateNewSituation(node, 2);
         renjuPrinter.printSituation(node.getSituation());
         int res = game.checkGoal(node.getSituation());
         if (renjuPrinter.printWinner(res)) {
            break;
         }
         System.out.println("WHITE STEP");
         node = game.generateNewSituation(node, 1);
         renjuPrinter.printSituation(node.getSituation());
         res = game.checkGoal(node.getSituation());
         if (renjuPrinter.printWinner(res)) {
            break;
         }
         node = node.getParentNode().getParentNode();
         if (node == null) {
            System.out.println("NO WINNER");
            break;
         }
      }
   }

   public void startModelingWidthSearch(int maxStepsCount){
      Node node = new Node(situation, null);
      renjuPrinter.printSituation(node.getSituation());

      for (int i = 0; i < maxStepsCount; i++) {
         System.out.println(game.checkGoal(node.getSituation()));
         System.out.println("BLACK STEP");
         List<Node> nodeList = game.generateAllSituation(node, 2);
         for (Node node1 : nodeList) {
            int res = game.checkGoal(node1.getSituation());
            renjuPrinter.printSituation(node1.getSituation());
            if (renjuPrinter.printWinner(res)) {
               winner = true;
               break;
            }
            List<Node> nodeListSecond = game.generateAllSituation(node1, 2);
            for (Node nodeSecond : nodeListSecond) {
               renjuPrinter.printSituation(nodeSecond.getSituation());
               res = game.checkGoal(nodeSecond.getSituation());
               if (renjuPrinter.printWinner(res)) {
                  winner = true;
                  break;
               }
            }
            if (winner) {
               break;
            }
         }
         if (winner) {
            break;
         }
         node = node.getParentNode().getParentNode();
         if (node == null) {
            System.out.println("NO WINNER");
            break;
         }
      }
   }
}
