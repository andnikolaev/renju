package com.nikolaev.simplemodel;

import com.nikolaev.model.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleSituation {

   int[][] points;

   public SimpleSituation(int[][] points) {
      this.points = points;
   }

   public SimpleSituation(int size) {
      this.points = new int[size][size];
   }

   public int[][] getPoints() {
      return points;
   }

   public void setPoint(int i, int j, int color) {
      points[i][j] = color;
   }
}
