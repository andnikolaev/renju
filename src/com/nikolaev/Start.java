package com.nikolaev;

import com.nikolaev.core.Finder;
import com.nikolaev.simplemodel.RenjuGame;
import com.nikolaev.simplemodel.RenjuPrinter;
import com.nikolaev.simplemodel.RenjuSituation;

public class Start {

//   public static int[][] initSituatuin() {
//      return new int[][]{
//              {1, 0, 2, 1, 2},
//              {0, 1, 2, 0, 1},
//              {2, 0, 0, 2, 2},
//              {2, 2, 0, 0, 2},
//              {0, 1, 1, 2, 0}};
//   }

   public static int[][] initSituatuin() {
      return new int[][]{
              {0, 0, 0, 0, 1},
              {1, 0, 0, 2, 2},
              {0, 0, 1, 0, 1},
              {0, 2, 1, 0, 2},
              {1, 2, 0, 2, 0}};
   }




   public static void main(String[] args) {
      RenjuSituation renjuSituation = new RenjuSituation(initSituatuin());
      RenjuGame renjuGame = new RenjuGame( 4,3);
      Finder finder = new Finder(renjuSituation, renjuGame, new RenjuPrinter());
//      finder.startModelingDepthSearch(6);
//      finder.startModelingWidthSearch(5);
      finder.startModelingGradient(10);
//      finder.startModelingParticialPath(10);
//      finder.startModelingParticialPathMinMax(15);
//      finder.startModelingParticialABClipping(10);
//            finder.startModelingMinMax(10);
   }


}
