package com.nikolaev.model;

import java.util.HashSet;
import java.util.Set;

public class Situation {

   private Set<Group> whitePoints = new HashSet<>();
   private Set<Group> blackPoints = new HashSet<>();

   public Set<Group> getPointGroups() {
      Set<Group> pointGroups = new HashSet<>(whitePoints);
      pointGroups.addAll(this.blackPoints);
      return pointGroups;
   }

   public Set<Group> getWhitePoints() {
      return whitePoints;
   }

   public void setWhitePoints(Set<Group> whitePoints) {
      this.whitePoints = whitePoints;
   }

   public Set<Group> getBlackPoints() {
      return blackPoints;
   }

   public void setBlackPoints(Set<Group> blackPoints) {
      this.blackPoints = blackPoints;
   }

   //TODO: find group for point and add
   public void addPoint(Point point) {

   }

   //TODO:
   public boolean checkPointOnExist(Point point) {
      for (Group group : getPointGroups()) {
         if (group.getRenjuPointSet().contains(point)) {
            return true;
         }
      }
      return false;
   }
}
