package com.nikolaev.model;

import java.util.Objects;

public class Point {

   private int x;
   private int y;
   private Color color;

   public Point(int x, int y, Color color) {
      this.x = x;
      this.y = y;
      this.color = color;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public Color getColor() {
      return color;
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
