package com.nikolaev.simplemodel;

import com.nikolaev.core.Game;
import com.nikolaev.core.Situation;

import java.util.ArrayList;
import java.util.List;

public class RenjuGame implements Game {

   private int countPointsToWin;

   public RenjuGame(int countPointsToWin) {
      this.countPointsToWin = countPointsToWin;
   }

   @Override
   public Node generateNewSituation(Node node, int color) {
      int count = node.getCount();
      int temp = 0;
      RenjuSituation renjuSituation = (RenjuSituation) node.getSituation();
      for (int i = 0; i < renjuSituation.getPoints().length; i++) {
         for (int j = 1; j < renjuSituation.getPoints()[i].length; j++) {
            if (renjuSituation.getPoints()[i][j] == 0) {
               if ((color == 2 && count == temp) || (color == 1 && count + 1 == temp)) {
                  node.addCount();
                  RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
                  newRenjuSituation.setPoint(i, j, color);
                  Node newNode = new Node(newRenjuSituation, node);
                  node.addNode(newNode);
                  return newNode;
               }
               temp++;
            }
         }
      }
      return null;
   }

   @Override
   public List<Node> generateAllSituation(Node node, int color) {
      List<Node> nodeList = new ArrayList<>();
      int count = node.getCount();
      int temp = 0;
      RenjuSituation renjuSituation = (RenjuSituation) node.getSituation();
      for (int i = 0; i < renjuSituation.getPoints().length; i++) {
         for (int j = 1; j < renjuSituation.getPoints()[i].length; j++) {
            if (renjuSituation.getPoints()[i][j] == 0) {
               if ((color == 2 && count == temp) || (color == 1 && count + 1 == temp)) {
                  node.addCount();
                  RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
                  newRenjuSituation.setPoint(i, j, color);
                  Node newNode = new Node(newRenjuSituation, node);
                  node.addNode(newNode);
                  nodeList.add(newNode);
               }
               temp++;
            }
         }
      }
      return nodeList;
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

   public int checkGoal(Situation situation) {
      RenjuSituation sit = (RenjuSituation) situation;
      int[][] points = sit.getPoints();
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
