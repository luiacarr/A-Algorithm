package AStarAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jhon_
 */
public class Runner {
    
    public static final int startX = 2, startY = 0, endX = 1, endY = 2;
    
    private List<Node> nodes;
    private List<Edge> edges;
    private List<String> streetNames;
    
    private void getEdges(String path) {
        FileReader file;
        try {
            file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file);
            String line = null;
            Random r = new Random();
            while((line = reader.readLine()) != null) {
                String[] arr = line.split(";");
                String name = streetNames.get(r.nextInt(streetNames.size()));
                if(arr.length > 2) {
                    Node start = new Node("" + Integer.parseInt(arr[0]) + ", " + Integer.parseInt(arr[1]), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                    Node end = new Node("" + Integer.parseInt(arr[2]) + ", " + Integer.parseInt(arr[3]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                    boolean startAdded = false;
                    boolean endAdded = false;
                    for(Node n : nodes) {
                        if(start.equals(n)) {
                            start = n;
                            startAdded = true;
                        }
                        if(end.equals(n)) {
                            end = n;
                            endAdded = true;
                        }
                    }
                    if(!startAdded) nodes.add(start);
                    if(!endAdded) nodes.add(end);
                    addEdge(name, start, end);}
                else {
                    Node node = new Node("" + Integer.parseInt(arr[0]) + ", " + Integer.parseInt(arr[1]), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                    nodes.add(node);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    private Node getNode(int x, int y) {
        Node node = new Node("", x, y);
        for(Node n : nodes) {
            if(node.equals(n)) {
                return n;
            }
        }
        return null;
    }
	
    private void getStreetNames(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file);
            String line = null;
            while((line = reader.readLine()) != null) {
                streetNames.add(line.split(",")[0]);
            }
            file.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Edge addEdge(String name, Node start, Node end) {
        Edge edge = new Edge(name, start, end, Node.distanceFrom(start, end));
        edges.add(edge);
        return edge;
    }
    
    public void testExecute() {
        nodes = new ArrayList<>();
	edges = new ArrayList<>();
	streetNames = new ArrayList<>();
        
        getStreetNames("src/res/Street_Names.txt");
        getEdges("src/res/edges.txt");
        
        Astar a = new Astar(new Graph(nodes, edges));
        Node start = getNode(startX, startY);
        Node end = getNode(endX, endY);
        
        a.run(start, end);
    }

    public static void main(String[] args) {
        new Runner().testExecute();
    }
    
}
