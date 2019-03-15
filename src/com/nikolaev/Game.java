package com.nikolaev;

import com.nikolaev.model.Group;
import com.nikolaev.model.Point;
import com.nikolaev.model.PointColor;
import com.nikolaev.model.Situation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

   private int xPointMax;
   private int yPointMax;
   private int countPointsToWin;

   public Game(int xPointMax, int yPointMax, int countPointsToWin) {
      this.xPointMax = xPointMax;
      this.yPointMax = yPointMax;
      this.countPointsToWin = countPointsToWin;
   }

   public boolean checkGameFinished(Situation situation) {
      Set<Group> checkedGroups = generateCheckedGroups(situation.getPointGroups());
      return checkedGroups.stream().anyMatch(this::checkGroup);
   }

   public Set<Situation> generateAllSituations(Situation situation, PointColor pointColor) {
      Set<Situation> allSituations = new HashSet<>();
      Set<Point> availablePoints = generateAvailablePoints(situation, pointColor);
      availablePoints.forEach(point -> allSituations.add(generateNewSituation(situation, point)));

      return allSituations;
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

   private Situation generateNewSituation(Situation situation, Point point) {
      Situation newSituation = new Situation();
      newSituation.getWhitePoints().addAll(situation.getWhitePoints());
      newSituation.getBlackPoints().addAll(situation.getBlackPoints());
      newSituation.addPoint(point);
      return newSituation;
   }

   private Set<Point> generateAvailablePoints(Situation situation, PointColor pointColor) {
      Set<Point> availablePoints = new HashSet<>();
      for (int x = 0; x < xPointMax; x++) {
         for (int y = 0; y < yPointMax; y++) {
            Point point = new Point(x, y, pointColor);
            if (!situation.checkPointOnExist(point)) {
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
