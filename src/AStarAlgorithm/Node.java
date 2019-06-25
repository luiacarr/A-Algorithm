package AStarAlgorithm;

import java.awt.Point;
import java.util.Stack;
/**
 *
 * @author jhon_
 */
public class Node implements Comparable<Node>{
    
    private String name;
    private Double distance = Double.MAX_VALUE;
    
    private Point coord;
    private Double heuristic;
    private Node previous = null;
    
    public Node(String name) {
        this.name = name;
    }
	
    public Node(String name, int x, int y) {
        this(name);
        coord = new Point(x ,y);
    }
	
    @Override
    public String toString() {
        return name;
    }
	
    public Double getDistance() {
        return distance;
    }
    
    public Node getPrevious() {
        return previous;
    }
	
    public void setPrevious(Node n) {
        previous = n;
    }
	
    public void setDistance(Double d) {
        distance = d;
    }
	
    @Override
    public boolean equals(Object o) {
        return coord.equals(((Node)o).coord);
    }
	
    public void setHeuristic(Node destination) {
        System.out.println(this);
        this.heuristic = Node.distanceFrom(this, destination);
    }
    
    public static Double distanceFrom(Node n1, Node n2) {
		return Math.sqrt((n1.coord.x-n2.coord.x)*(n1.coord.x-n2.coord.x) + (n1.coord.y-n2.coord.y)*(n1.coord.y-n2.coord.y));
	}
	
    public static void printPath(Node destination) {
        System.out.println("Total distance traveled: " + destination.getDistance());
        Node current = destination;
        Stack path = new Stack();
        path.push(destination);
		
        while(current.getPrevious() != null) {
            current = current.getPrevious();
            path.push(current);
        }
		
        int i = 0;
        do {
            System.out.println(++i + ": " + path.pop());
        }while(!path.isEmpty());
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(heuristic + distance, n.heuristic + n.distance);
    }
    
}
