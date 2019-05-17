package com.nikolaev.core;

import com.nikolaev.simplemodel.Node;

import java.util.List;

public interface Game {

  Node generateNewSituation(Node node, int color);

  List<Node> generateAllSituation(Node node, int color);

  Node generateSituationWithEvaluatingFunction(Node node, int color, boolean change);

  Node generateSituationWithParticipalPath(Node node, int color);

  Node generateSituationWithParticipalPathAndClipping(Node node, int color, int depth);

  int checkGoal(Situation situation);

}
