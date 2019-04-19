package com.nikolaev.simplemodel;

import com.nikolaev.core.Situation;
import com.nikolaev.core.SituationPrinter;

public class RenjuPrinter implements SituationPrinter {
   public boolean printWinner(int res) {
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

   public void printSituation(Situation situation) {
      RenjuSituation renjuSituation = (RenjuSituation) situation;
      for (int i = 0; i < renjuSituation.getPoints().length; i++) {
         for (int j = 0; j < renjuSituation.getPoints()[i].length; j++) {
            int point = renjuSituation.getPoints()[i][j];
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
