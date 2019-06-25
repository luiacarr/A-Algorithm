package AStarAlgorithm;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
/**
 *
 * @author jhon_
 */
public class Astar {
    Graph graph;
    private List<Edge> edges;
    private Set<Node> searched;
    private Queue<Node> unsearched;
    
    public Astar(Graph graph) {
        this.graph = graph;
        edges = graph.getEdges();
    }
    
    public static void main(String[] args) {
        
    }
    
    public void run(Node start, Node end) {
        searched = new HashSet();
	unsearched = new PriorityQueue();
        
        Node current = start;
        start.setDistance(0.0);
        start.setHeuristic(end);
        unsearched.add(start);
        
        while(!unsearched.isEmpty()) {
            current = unsearched.poll();
            System.out.println(current);
            if(current.equals(end)) {
                Node.printPath(end);
                return;
            }
            searched.add(current);
            updateNeighbor(current, end);
            
        }
        System.out.println("Shortest path between " + start + " and " + end + " was not found.");
    }
    
    public void updateNeighbor(Node curr, Node destination) {
        List<Node> neighbors = Graph.getNeighbors(edges, curr);
        Double distance = curr.getDistance();
        for(Node neighbor : neighbors) {
            Double temp = Graph.getDistanceFrom(edges, curr, neighbor);
            if(!searched.contains(neighbor)) {
                if(distance + temp < neighbor.getDistance()) {
                    neighbor.setPrevious(curr);
                    neighbor.setDistance(distance + temp);
                    neighbor.setHeuristic(destination);
                    
                    unsearched.add(neighbor);
                }
            }
        }
    }
}
