package com.nikolaev.core;

import com.nikolaev.simplemodel.Node;

import java.util.List;

public interface Game {

  Node generateNewSituation(Node node, int color);

  List<Node> generateAllSituation(Node node, int color);

  int checkGoal(Situation situation);

}
