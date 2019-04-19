package com.nikolaev.simplemodel;

import com.nikolaev.core.Situation;

import java.util.ArrayList;
import java.util.List;

public class Node{

   private Situation situation;
   private Node parentNode;
   private List<Node> childNodes = new ArrayList<>();
   private int count = 0;

   public Node(Situation situation, Node parentNode) {
      this.situation = situation;
      this.parentNode = parentNode;
   }

   public Situation getSituation() {
      return situation;
   }

   public int getCount() {
      return count;
   }

   public void addCount() {
      count++;
   }

   public void addNode(Node newNode) {
      childNodes.add(newNode);
   }

   public Node getParentNode() {
      return parentNode;
   }
}

