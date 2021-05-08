import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Edge {
  private String source;
  private String destination;
  private Double weight;

  public Edge(String source, String destination, Double weight){ this.source=source; this.destination=destination; this.weight=weight; }
  public String source(){ return this.source; }
  public String destination(){ return this.destination; }
  public Double weight(){ return this.weight; }
  public void print(){ System.out.println("("+source+", "+destination+": "+weight+")"); }
}

public class Graph {
  private List<String> vertices;
  private List<Edge> edges;

  public Graph(){ this.vertices = new ArrayList<String>(); this.edges = new ArrayList<Edge>(); }
  /**
   * Adds new verticies to vertex list, will also add edge (source, dest) and (dest, source)
   * 
   * @param source - key for source vertex
   * @param destination - key for destination vertex
   * @param weight - the weight of the edge
   */
  public void addEdge(String source, String destination, Double weight){
    if(!this.vertices.contains(source))
      vertices.add(source);
    if(!this.vertices.contains(destination))
      vertices.add(destination);
    
    //Assumes an edge won't be added twice
    edges.add(new Edge(source, destination, weight));
    edges.add(new Edge(destination, source, weight));
  }

  /**
   * Prints the edges and vertices in the Graph
   */
  public void print(){
    System.out.println("Edges");
    for(Edge edge: edges)
      edge.print();
    System.out.println("Vertices");
    for(String v: this.vertices)
      System.out.println(v);
  }

  public void dijkstra(String start, String end){
    HashMap<String, String> changedAt = new HashMap<>();
    changedAt.put(start, null);

    ArrayList<String> visited = new ArrayList<String>(); //Tracks which nodes have been visited
    HashMap<String, Double> shortestPathMap = new HashMap<>(); //Tracks shortest path and weight
    String currentNode = null; //Used to iterate through the nodes of graph

    //Inits the mapping shortestPathMap
    for (String node : this.vertices) {
      if(node.equals(start)){
        currentNode = node;
        shortestPathMap.put(node, 0.0);
        visited.add(node);
      }
      shortestPathMap.put(node, Double.POSITIVE_INFINITY);
    }

    //Goes through the first iteration
    for (Edge edge : edges) {
      if(edge.source().equals(start) && !visited.contains(edge.destination())){
        shortestPathMap.put(edge.destination(), edge.weight());
        changedAt.put(edge.destination(), start);
      }
    }

    while(true){
      currentNode = closestReachableUnvisited(shortestPathMap, currentNode, visited, end);
      visited.add(currentNode);

      if(currentNode == null) { //Prints that there is no path
        System.out.println("There isn't a path between " + start + " and " + end);
        return;
      } else if(currentNode.equals(end)) { //Prints shortest path
        printPath(shortestPathMap, changedAt, start, end);
        return;
      }
      
      //Checks adjacent nodes connected to the current node
      for (Edge edge : this.edges) {
        if(edge.source().equals(currentNode) && !visited.contains(edge.destination())){ //Passes if an adjacent node
          if (shortestPathMap.get(currentNode) + edge.weight() < shortestPathMap.get(edge.destination())) { //Passes if it can relax
            shortestPathMap.put(edge.destination(),shortestPathMap.get(currentNode) + edge.weight());
            changedAt.put(edge.destination(), currentNode);
          }
        }
      }
    }
  }

  private String closestReachableUnvisited(HashMap<String, Double> shortestPathMap, String currentNode, ArrayList<String> visited, String end) {
    Double shortestDistance = Double.POSITIVE_INFINITY;
    String closestReachableNode = null;

    for(Edge edge: this.edges){
      if(edge.source().equals(currentNode)){
        String node = edge.destination();

        if(visited.contains(node))
          continue;
        
        Double currentDistance = shortestPathMap.get(node);
        if (currentDistance == Double.POSITIVE_INFINITY)
            continue;
        if(node.equals(end)){
          closestReachableNode=node;
          shortestDistance=currentDistance;
          break;
        } else if (currentDistance < shortestDistance) {
            shortestDistance = currentDistance;
            closestReachableNode = node;
        }
      }
    }
    return closestReachableNode;
  }

  /**
   * After the algorithm runs this function prints the path
   * 
   * @param shortestPathMap - the mapping used to track the smallest weight of each graph
   * @param changedAt - the parent child relationship mapping
   * @param start - source node
   * @param end - end node
   */
  private void printPath(HashMap<String, Double> shortestPathMap, HashMap<String, String> changedAt, String start, String end){
    System.out.println("\nShortest path between "+start+" and "+end+":");
    String child = end;
    String path = end;
    
    // Since our changedAt map keeps track of child -> parent relations
    // in order to print the path we need to add the parent before the child and it's descendants
    while (true) {
      String parent = changedAt.get(child);
      if (parent == null) {
          break;
      }
      path = parent + " " + path;
      child = parent;
    }
    System.out.println("  Path: "+path+ " \n  Cost: "+shortestPathMap.get(end));
  }
}