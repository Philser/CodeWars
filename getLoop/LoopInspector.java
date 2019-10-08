import java.util.ArrayList;

public class LoopInspector {

  public int loopSize(Node node) { 
    Node currNode = node;
    ArrayList<Node> nodeList = new ArrayList<Node>();
    
    int tail = 0;
    
    while(nodeList.contains(currNode) == false){    
      nodeList.add(currNode);
      currNode=currNode.getNext();
    }
    
    tail=nodeList.indexOf(currNode);
    
    return nodeList.size()-tail;    
  }
}
