package com.nikolaev.simplemodel;

import com.nikolaev.core.Game;
import com.nikolaev.core.Situation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RenjuGame implements Game {

   private int countPointsToWin;
   private int maxDepth;

   public RenjuGame(int countPointsToWin, int maxDepth) {
      this.countPointsToWin = countPointsToWin;
      this.maxDepth = maxDepth;
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

   @Override
   public Node generateSituationWithEvaluatingFunction(Node node, int color, boolean change) {
      Map<Integer, Node> nodeList = new TreeMap<>();
      List<Integer> integerLint = new LinkedList<>();
      int count = node.getCount();
      RenjuSituation renjuSituation = (RenjuSituation) node.getSituation();
      for (int i = 0; i < renjuSituation.getPoints().length; i++) {
         for (int j = 1; j < renjuSituation.getPoints()[i].length; j++) {
            if (renjuSituation.getPoints()[i][j] == 0) {
               node.addCount();
               RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
               if (change) {
                  if (color == 1) {
                     newRenjuSituation.setPoint(i, j, 2);
                  } else {
                     newRenjuSituation.setPoint(i, j, 1);
                  }
               } else {
                  newRenjuSituation.setPoint(i, j, color);
               }

               Node newNode = new Node(newRenjuSituation, node);
               node.addNode(newNode);
               int value = evaluatingFunction(newNode.getSituation(), i, j, color);
               nodeList.put(value, newNode);
               integerLint.add(value);
            }
         }
      }
      integerLint.sort(Collections.reverseOrder());
      if (count >= integerLint.size()) {
         count = 0;
      }
      if (integerLint.size() == 0) {
         return null;
      }
      return nodeList.get(integerLint.get(count));
   }

   @Override
   public Node generateSituationWithParticipalPath(Node node, int color) {
      Map<Integer, Node> nodeList = new HashMap<>();
      List<Integer> integerLint = new LinkedList<>();
      int count = node.getCount();
      List<Node> allNodes = new ArrayList<>();
      List<Node> nodes = generateFullAllSituation(node, color);
      for (Node node1 : nodes) {
         if (color == 1) {
            allNodes.addAll(generateFullAllSituation(node1, 2));
         } else {
            allNodes.addAll(generateFullAllSituation(node1, 1));
         }
      }
      for (Node lastNode : allNodes) {
         RenjuSituation renjuSituation = (RenjuSituation) lastNode.getSituation();
         RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
         Node newSit = new Node(newRenjuSituation, lastNode);
         int value = evaluatingFunction(newRenjuSituation, newRenjuSituation.getLastI(), newRenjuSituation.getLastJ(), color);
         nodeList.put(value, newSit);
         integerLint.add(value);
      }

      node.addCount();

      integerLint.sort(Collections.reverseOrder());
      if (count >= integerLint.size()) {
         count = 0;
      }
      if (integerLint.size() == 0) {
         return null;
      }
      return nodeList.get(integerLint.get(count)).getParentNode().getParentNode();
   }

   @Override
   public Node generateSituationWithParticipalPathAndClipping(Node node, int color, int depth) {
      if (depth > maxDepth) {
         return null;
      }
      Map<Integer, Node> nodeList = new HashMap<>();
      List<Integer> integerLint = new LinkedList<>();
      int count = node.getCount();
      List<Node> allNodes = new ArrayList<>();
      List<Node> nodes = generateFullAllSituation(node, color);
      for (Node node1 : nodes) {
         if (color == 1) {
            allNodes.addAll(generateFullAllSituation(node1, 2));
         } else {
            allNodes.addAll(generateFullAllSituation(node1, 1));
         }
      }
      Node result = generateNewSituation(node, color);
      for (Node lastNode : allNodes) {
         RenjuSituation renjuSituation = (RenjuSituation) lastNode.getSituation();
         RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
         int value = evaluatingFunction(newRenjuSituation, newRenjuSituation.getLastI(), newRenjuSituation.getLastJ(), color);
         nodeList.put(value, lastNode);
         lastNode.getParentNode().setEvaluatuion(value);
         integerLint.add(value);
      }

      node.addCount();

      integerLint.sort(Collections.reverseOrder());
      if (count >= integerLint.size()) {
         count = 0;
      }
      if (integerLint.size() == 0) {
         return null;
      }
      node = nodeList.get(integerLint.get(count));
      Node newNodeRes = node;
      newNodeRes = generateSituationWithParticipalPathAndClipping(node, 2, depth++);
      if (newNodeRes == null) {
         return result;
      } else {
         return result;
      }
   }

   private List<Node> generateFullAllSituation(Node node, int color) {
      List<Node> nodeList = new ArrayList<>();
      RenjuSituation renjuSituation = (RenjuSituation) node.getSituation();
      for (int i = 0; i < renjuSituation.getPoints().length; i++) {
         for (int j = 1; j < renjuSituation.getPoints()[i].length; j++) {
            if (renjuSituation.getPoints()[i][j] == 0) {
               RenjuSituation newRenjuSituation = new RenjuSituation(copy(renjuSituation.getPoints()));
               newRenjuSituation.setPoint(i, j, color);
               Node newNode = new Node(newRenjuSituation, node);
               node.addNode(newNode);
               nodeList.add(newNode);
            }
         }
      }
      return nodeList;
   }

   private int evaluatingFunction(Situation situation, int i, int j, int color) {
      RenjuSituation sit = (RenjuSituation) situation;
      int[][] points = sit.getPoints();
      int count = 1;
      boolean openEnd1 = false;
      boolean openEnd2 = false;
      for (int ii = i; ii < points.length; ii++) {
         if (points[ii][j] == color) {
            count++;
            if (ii + 1 < points.length && points[ii + 1][j] == 0) {
               openEnd1 = true;
            }
         } else {
            break;
         }
      }
      for (int ii = i; ii >= 0; ii--) {
         if (points[ii][j] == color) {
            count++;
            if (ii - 1 >= 0 && points[ii - 1][j] == 0) {
               openEnd2 = true;
            }
         } else {
            break;
         }
      }
      count -= 2;
      int tempCount = 1;
      boolean tempOpenEnd1 = false;
      boolean tempOpenEnd2 = false;
      for (int jj = j; jj < points.length; jj++) {
         if (points[i][jj] == color) {
            tempCount++;
            if (jj + 1 < points.length && points[i][jj] == 0) {
               tempOpenEnd1 = true;
            }
         } else {
            break;
         }
      }
      for (int jj = j; jj >= 0; jj--) {
         if (points[i][jj] == color) {
            tempCount++;
            if (jj - 1 >= 0 && points[i][jj] == 0) {
               tempOpenEnd2 = true;
            }
         } else {
            break;
         }

      }
      tempCount -= 2;
      int mulltiple = 1;
      if (tempCount == count) {
         mulltiple += 1;
      }
      if (tempCount != 1 && count != 1) {
         mulltiple += 1;
      }
      int max = 0;
      if (tempCount > count) {
         max = tempCount;
      } else {
         max = count;
      }
      if (tempOpenEnd1) {
         mulltiple += 1;
      }
      if (tempOpenEnd2) {
         mulltiple += 1;
      }
      if (openEnd1) {
         mulltiple += 1;
      }
      if (openEnd2) {
         mulltiple += 1;
      }
      if (max == 1) {
         max = 2;
      }
      if (max == 2) {
         max = 6;
      }
      if (max == 3) {
         max = 10;
      }
      if (max == 4) {
         max = 50;
      }
      return max * mulltiple;

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
