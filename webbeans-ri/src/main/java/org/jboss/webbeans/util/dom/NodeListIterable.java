package org.jboss.webbeans.util.dom;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIterable implements Iterable<Node>
{
   
   private final NodeList nodeList;
   
   public NodeListIterable(NodeList nodeList)
   {
      this.nodeList = nodeList;
   }

   public Iterator<Node> iterator()
   {
      return new NodeListIterator(nodeList);
   }
   
}
