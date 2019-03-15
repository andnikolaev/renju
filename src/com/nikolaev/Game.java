package com.nikolaev;

import com.nikolaev.model.Group;
import com.nikolaev.model.Point;
import com.nikolaev.model.Color;
import com.nikolaev.model.Situation;

import java.util.HashSet;
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

   public Set<Situation> generateAllSituations(Situation situation, Color color) {
      Set<Situation> allSituations = new HashSet<>();
      Set<Point> availablePoints = generateAvailablePoints(situation, color);
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
      newSituation.getPointGroups().addAll(situation.getPointGroups());
      newSituation.addPoint(point);
      return newSituation;
   }

   private Set<Point> generateAvailablePoints(Situation situation, Color color) {
      Set<Point> availablePoints = new HashSet<>();
      for (int x = 0; x < xPointMax; x++) {
         for (int y = 0; y < yPointMax; y++) {
            Point point = new Point(x, y, color);
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
