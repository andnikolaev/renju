package com.nikolaev.model;

import java.util.HashSet;
import java.util.Set;

public class Situation {

   private Set<Group> pointGroups = new HashSet<>();

   public Set<Group> getPointGroups() {
      return pointGroups;
   }

   public Set<Group> getPointPointsGroupByColor(Color color) {
      Set<Group> pointGroupsByColor = new HashSet<>();
      pointGroupsByColor.forEach(group -> {
         if (color.equals(group.getColor())) {
            pointGroupsByColor.add(group);
         }
      });
      return pointGroupsByColor;
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
