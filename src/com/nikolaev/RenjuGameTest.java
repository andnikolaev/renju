package com.nikolaev;

import com.nikolaev.model.Group;
import com.nikolaev.model.Point;
import com.nikolaev.model.Color;
import com.nikolaev.model.RenjuSituation;

import java.util.HashSet;
import java.util.Set;

public class RenjuGameTest {

   private int xPointMax;
   private int yPointMax;
   private int countPointsToWin;

   public RenjuGameTest(int xPointMax, int yPointMax, int countPointsToWin) {
      this.xPointMax = xPointMax;
      this.yPointMax = yPointMax;
      this.countPointsToWin = countPointsToWin;
   }

   public boolean checkGameFinished(RenjuSituation renjuSituation) {
      Set<Group> checkedGroups = generateCheckedGroups(renjuSituation.getPointGroups());
      return checkedGroups.stream().anyMatch(this::checkGroup);
   }

   public Set<RenjuSituation> generateAllSituations(RenjuSituation renjuSituation, Color color) {
      Set<RenjuSituation> allRenjuSituations = new HashSet<>();
      Set<Point> availablePoints = generateAvailablePoints(renjuSituation, color);
      availablePoints.forEach(point -> allRenjuSituations.add(generateNewSituation(renjuSituation, point)));

      return allRenjuSituations;
   }

   //TODO:
   private boolean checkGroup(Group group) {

      return false;
   }

   private Set<Group> generateCheckedGroups(Set<Group> groups) {
      Set<Group> groupsForCheck = new HashSet<>();
      groups.forEach(group -> {
         if (group.getRenjuPointSet().size() >= countPointsToWin) {
            groupsForCheck.add(group);
         }
      });

      return groupsForCheck;
   }

   private RenjuSituation generateNewSituation(RenjuSituation renjuSituation, Point point) {
      RenjuSituation newRenjuSituation = new RenjuSituation();
      newRenjuSituation.getPointGroups().addAll(renjuSituation.getPointGroups());
      newRenjuSituation.addPoint(point);
      return newRenjuSituation;
   }

   private Set<Point> generateAvailablePoints(RenjuSituation renjuSituation, Color color) {
      Set<Point> availablePoints = new HashSet<>();
      for (int x = 0; x < xPointMax; x++) {
         for (int y = 0; y < yPointMax; y++) {
            Point point = new Point(x, y, color);
            if (!renjuSituation.checkPointOnExist(point)) {
               availablePoints.add(point);
            }
         }
      }
      return availablePoints;
   }

   public int getxPointMax() {
      return xPointMax;
   }

   public int getyPointMax() {
      return yPointMax;
   }

   public int getCountPointsToWin() {
      return countPointsToWin;
   }
}
