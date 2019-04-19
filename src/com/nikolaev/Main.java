package com.nikolaev;

import com.nikolaev.model.Color;
import com.nikolaev.model.Point;
import com.nikolaev.model.RenjuSituation;

public class Main {

   private int[][] initSituatuin() {
      return new int[][]{
              {0, 0, 1, 2, 2},
              {1, 0, 1, 1, 2},
              {1, 0, 2, 2, 2},
              {2, 0, 1, 1, 2},
              {1, 0, 1, 2, 2}
      };
   }

   public static void main(String[] args) {
      Main main = new Main();
      RenjuSituation renjuSituation = new RenjuSituation();
      int[][] initSituations = main.initSituatuin();
      for (int i = 0; i < initSituations.length; i++) {
         for (int j = 0; j < initSituations[i].length; j++) {
            int point = initSituations[i][j];
            if (point == 1) {
               renjuSituation.addPoint(new Point(i, j, Color.WHITE));
            }
            if (point == 2) {
               renjuSituation.addPoint(new Point(i, j, Color.BLACK));
            }
         }
      }
      System.out.println(renjuSituation.getPointGroups().size());
      main.printSituation(initSituations.length, initSituations[0].length, renjuSituation);
   }

   private void printSituation(int width, int height, RenjuSituation renjuSituation) {
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            Point point = renjuSituation.getPoint(i, j);
            System.out.printf("%8s",point.toString());
         }
         System.out.println();
      }
   }
}
