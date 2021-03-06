package challenges;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

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

    int total = 0;
    for (Node n : nodes) {
      for (Integer i : n.entries) {
        total += i;
      }
    }
    String[] ret = {String.valueOf(total)};
    return ret;
  }

  Pair<Integer, Node> addNodes(List<Node> nodes, List<Integer> numLine, int startIndex) {
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
      Node n = new Node(numChildren, numEntries, children, entries);
      nodes.add(n);
      return new Pair<>(currentIndex + numEntries, n);
    }

    for (int i = 0; i < numChildren; i++) {
      Pair<Integer, Node> p = addNodes(nodes, numLine, currentIndex);
      currentIndex = p.getKey();
      children.add(p.getValue());
    }

    for (int i = 0; i < numEntries; i++) {
      entries.add(numLine.get(currentIndex + i));
    }

    Node n = new Node(numChildren, numEntries, children, entries);
    nodes.add(n);
    return new Pair<>(currentIndex + numEntries, n);
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

    int getValue() {
      int total = 0;

      if (this.numChildren == 0) {
        for (Integer i : entries) {
          total += i;
        }
      } else {
        for (Integer i : entries) {
          if (i > 0 && i <= children.size()) {
            total += children.get(i - 1).getValue();
          }
        }
      }

      return total;
    }
  }
}
