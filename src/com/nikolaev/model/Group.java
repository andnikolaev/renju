package com.nikolaev.model;

import java.util.HashSet;
import java.util.Set;

public class Group {

   private Set<Point> renjuPointSet = new HashSet<>();

   public Set<Point> getRenjuPointSet() {
      return renjuPointSet;
   }

   public void setRenjuPointSet(Set<Point> renjuPointSet) {
      this.renjuPointSet = renjuPointSet;
   }
}
