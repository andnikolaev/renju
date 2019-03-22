package com.nikolaev.simplemodel;

import com.nikolaev.model.Group;
import com.nikolaev.model.Situation;

import java.util.Set;

public class SimpleGame {

   private int xPointMax;
   private int yPointMax;
   private int countPointsToWin;

   public SimpleGame(int xPointMax, int countPointsToWin) {
      this.xPointMax = xPointMax;
      this.yPointMax = yPointMax;
      this.countPointsToWin = countPointsToWin;
   }

   public Node generateNewSituation(Node node, int color) {
      int count = node.getCount();
      int temp = 0;
      for (int i = 0; i < node.getSituation().getPoints().length; i++) {
         for (int j = 1; j < node.getSituation().getPoints()[i].length; j++) {
            if (node.getSituation().getPoints()[i][j] == 0) {
               if ((color == 2 && count == temp) || (color == 1 && count + 1 == temp)) {
                  node.addCount();
                  Node newNode = new Node(new SimpleSituation(copy(node.getSituation().getPoints())), node);
                  newNode.getSituation().setPoint(i, j, color);
                  node.addNode(newNode);
                  return newNode;
               }
               temp++;
            }
         }
      }
      return null;
   }

   private int[][] copy(int[][] origin) {
      int[][] copy = new int[origin.length][origin.length];
      for (int i = 0; i < origin.length; i++) {
         for (int j = 0; j < origin[i].length; j++) {
            copy[i][j] = origin[i][j];
         }
      }
      return copy;
   }

   public int checkGameFinished(SimpleSituation situation) {
      int[][] points = situation.getPoints();
      int count = 1;
      for (int i = 0; i < points.length; i++) {
         for (int j = 1; j < points[i].length; j++) {
            if (points[i][j - 1] == points[i][j] && points[i][j] != 0) {
               count++;
               if (count == countPointsToWin) {
                  return points[i][j];
               }
            } else {
               count = 1;
            }
         }
      }
      for (int i = 0; i < points.length; i++) {
         for (int j = 1; j < points[i].length; j++) {
            if (points[j - 1][i] == points[j][i] && points[j][i] != 0) {
               count++;
               if (count == countPointsToWin) {
                  return points[i][j];
               }
            } else {
               count = 1;
            }
         }
      }
      return 0;
   }
}
