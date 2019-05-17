package com.nikolaev.simplemodel;

import com.nikolaev.core.Situation;

public class RenjuSituation implements Situation {

   int[][] points;

   int lastI = 0;
   int lastJ = 0;

   public int getLastI() {
      return lastI;
   }

   public int getLastJ() {
      return lastJ;
   }

   public RenjuSituation(int[][] points) {
      this.points = points;
   }

   public RenjuSituation(int size) {
      this.points = new int[size][size];
   }

   public int[][] getPoints() {
      return points;
   }

   public void setPoint(int i, int j, int color) {
      points[i][j] = color;
      lastI = i;
      lastJ = j;
   }
}
