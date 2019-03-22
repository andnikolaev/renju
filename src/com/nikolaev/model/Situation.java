package com.nikolaev.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

   public void addPoint(Point point) {
      Set<Group> allGroup = getPointPointsGroupByColor(point.getColor());
      List<Group> allGroups = new ArrayList<>();
      if (!allGroup.isEmpty()) {
         for (Group group : allGroup) {
            if (isNeibour(group, point)) {
               allGroups.add(group);
            }
         }
         if (allGroups.size() == 1) {
            allGroups.get(0).addPoint(point);
         } else if (allGroups.size() > 1) {
            Group group = allGroups.get(0);
            for (int i = 1; i < allGroups.size(); i++) {
               group.addListPoints(allGroups.get(i).getRenjuPointSet());
               pointGroups.remove(allGroups.get(i));
            }
         }
      }
      if (allGroups.isEmpty() || allGroup.isEmpty()) {
         Group group = new Group();
         group.setColor(point.getColor());
         group.addPoint(point);
         pointGroups.add(group);
      }
   }

   private boolean isNeibour(Group group, Point point) {
      for (Point groupPoint : group.getRenjuPointSet()) {
         if (Math.abs(groupPoint.getX() - point.getX()) == 1 || Math.abs(groupPoint.getY() - point.getY()) == 1) {
            return true;
         }
      }
      return false;
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

   public Point getPoint(int x, int y) {
      Point point = new Point(x, y, Color.NONE);
      for (Group group : getPointGroups()) {
         if (group.getRenjuPointSet().contains(point)) {
            return group.getPointByXY(point);
         }
      }
      return point;
   }

   @Override
   public String toString() {
      return "Situation{" + "pointGroups=" + pointGroups + '}';
   }
}
