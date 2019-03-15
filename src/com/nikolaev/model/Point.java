package com.nikolaev.model;

import java.util.Objects;

public class Point {

   private int x;
   private int y;
   private PointColor pointColor;

   public Point(int x, int y, PointColor pointColor) {
      this.x = x;
      this.y = y;
      this.pointColor = pointColor;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public PointColor getPointColor() {
      return pointColor;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
   }

   @Override
   public int hashCode() {
      return Objects.hash(x, y);
   }
}
