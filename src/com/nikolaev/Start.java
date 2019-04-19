package com.nikolaev;

import com.nikolaev.core.Finder;
import com.nikolaev.simplemodel.RenjuGame;
import com.nikolaev.simplemodel.RenjuPrinter;
import com.nikolaev.simplemodel.RenjuSituation;

public class Start {

   public static int[][] initSituatuin() {
      return new int[][]{
              {1, 1, 1, 0, 2},
              {1, 1, 1, 2, 2},
              {2, 0, 2, 2, 2},
              {2, 2, 1, 1, 2},
              {1, 1, 1, 2, 0}};
   }



   public static void main(String[] args) {
      RenjuSituation renjuSituation = new RenjuSituation(initSituatuin());
      RenjuGame renjuGame = new RenjuGame( 5);
      Finder finder = new Finder(renjuSituation, renjuGame, new RenjuPrinter());
      finder.startModelingDepthSearch(5);
      finder.startModelingWidthSearch(5);
   }


}
