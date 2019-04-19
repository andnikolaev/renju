package com.nikolaev.simplemodel;

import com.nikolaev.core.Situation;

public class RenjuSituation implements Situation {

   int[][] points;

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
   }
}
