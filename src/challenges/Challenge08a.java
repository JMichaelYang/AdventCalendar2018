package challenges;

import java.util.ArrayList;
import java.util.List;

public class Challenge08a implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    List<Integer> numLine = new ArrayList<>();
    String first = input[0];
    for (String s : first.split(" ")) {
      numLine.add(Integer.valueOf(s));
    }
    List<Node> nodes = new ArrayList<>();
    addNodes(nodes, numLine, 0);
    int totalEntries = 0;
    for (Node n : nodes) {
      for (Integer i : n.entries) {
        totalEntries += i;
      }
    }

    String[] ret = {String.valueOf(totalEntries)};
    return ret;
  }

  int addNodes(List<Node> nodes, List<Integer> numLine, int startIndex) {
    int currentIndex = startIndex;
    int numChildren = numLine.get(currentIndex);
    currentIndex++;
    List<Node> children = new ArrayList<>();
    int numEntries = numLine.get(currentIndex);
    List<Integer> entries = new ArrayList<>();
    currentIndex++;

    if (numChildren == 0) {
      for (int i = currentIndex; i < currentIndex + numEntries; i++) {
        entries.add(numLine.get(i));
      }
      nodes.add(new Node(numChildren, numEntries, children, entries));
      return currentIndex + numEntries;
    }

    for (int i = 0; i < numChildren; i++) {
      currentIndex = addNodes(nodes, numLine, currentIndex);
    }

    for (int i = 0; i < numEntries; i++) {
      entries.add(numLine.get(currentIndex + i));
    }

    nodes.add(new Node(numChildren, numEntries, children, entries));
    return currentIndex + numEntries;
  }

  class Node {

    final int numChildren;
    final int numEntries;
    final List<Node> children;
    final List<Integer> entries;

    Node(int numChildren, int numEntries, List<Node> children, List<Integer> entries) {
      this.numChildren = numChildren;
      this.numEntries = numEntries;
      this.children = children;
      this.entries = entries;
    }
  }
}
