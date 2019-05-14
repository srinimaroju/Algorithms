/**
 * Covers following algorithms
 * BFS
 * DFS
 * Djikstras - Shortest Path
 * Minimum Spanning Tree
 */
package Graph;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class GraphNode {
	int weight;
	int vertex;

	GraphNode(int v, int w) {
		vertex = v;
		weight = w;
	}

	public String toString() {
		return vertex + ":" + weight;
	}	
	// Overriding equals() to compare two Complex objects 
    @Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof GraphNode)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        GraphNode n = (GraphNode) o; 
          
        // Compare the data members and return accordingly  
        return weight == n.weight;
    } 

}

public class GraphOps {
	LinkedList<GraphNode>[] graph;
	int vertices;
	public static void main(String []args) {
		GraphOps g = new GraphOps(9);
		g.addEdge(0, 1, 4); 
		g.addEdge(0, 7, 8); 
		g.addEdge(1, 2, 8); 
		g.addEdge(1, 7, 11); 
		g.addEdge(2, 3, 7); 
		g.addEdge(2, 8, 2); 
		g.addEdge(2, 5, 4); 
		g.addEdge(3, 4, 9); 
		g.addEdge(3, 5, 14); 
		g.addEdge(4, 5, 10); 
		g.addEdge(5, 6, 2); 
		g.addEdge(6, 7, 1); 
		g.addEdge(6, 8, 6); 
		g.addEdge(7, 8, 7); 
		System.out.println(g);
		g.Djikstras(0);

	}
	public GraphOps(int vertices) {
		this.vertices = vertices;
		this.graph =  new LinkedList[vertices];

		for(int index=0; index<vertices; index++) {
			LinkedList<GraphNode> element = new LinkedList<GraphNode>();
			this.graph[index] = element;
		}
	}
	public String toString() {
		StringBuffer output = new StringBuffer();
		for(int index=0; index<vertices; index++) {
			output.append(index + " --> " + this.graph[index].toString() + "\n");
		}
		return output.toString();
	}

	public void BFS() {

	}
	public void DFS() {

	}
	private void printGraphNodesArray(GraphNode[] graphNodes) {
		int vertices = graphNodes.length;
		System.out.print("[");
		for(int index=0; index<vertices; index++) {
			System.out.print(graphNodes[index]);
			if(index<vertices-1) {
				System.out.print(", ");
			} else {
				System.out.println("]");
			}
		}
	}
	public void Djikstras(int source) {

		PriorityQueue<GraphNode> pq = new PriorityQueue<GraphNode>(vertices, 
			new Comparator<GraphNode>() {
				public int compare(GraphNode n1, GraphNode n2) {
					return (new Integer(n1.weight).compareTo(new Integer(n2.weight)));
				}
			}
		);

		int[] visited = new int[vertices];
		for(int i=0;i<vertices;i++) {
			visited[i] = -1;
		}

		GraphNode[] visitedGraphNodes = new GraphNode[vertices];

		for(int index=0; index<vertices; index++) {
			int distance = 10000;
			if(index == source) {
				distance = 0 ;
			}
			GraphNode gnode = new GraphNode(index, distance);
			pq.add(gnode);
			visitedGraphNodes[index] = gnode;
		}
		System.out.println(" PQ" + pq);

		int iteration = 1;
		while(!pq.isEmpty()) {
			GraphNode node = pq.poll();
			System.out.println("Removed node " + node.vertex);
			int vertex = node.vertex;
			for(int i=0; i<this.graph[vertex].size(); i++) {
				GraphNode dest = this.graph[vertex].get(i);
				if(visited[dest.vertex] == -1) {
					int newDistance = dest.weight + visitedGraphNodes[node.vertex].weight;
					int currentDistance = visitedGraphNodes[dest.vertex].weight;
					if(newDistance < currentDistance) {
						visitedGraphNodes[dest.vertex].weight = newDistance;
					}
					pq.remove(visitedGraphNodes[dest.vertex]);
					pq.add(visitedGraphNodes[dest.vertex]);

					//if(distance < pq.)
				}
				visited[vertex] = 1;
			}
			System.out.println("----------------------" + (iteration++) + "--------------------------------------");
			System.out.println(" PQ" + pq);
			System.out.print("VGN");
			printGraphNodesArray(visitedGraphNodes);
			System.out.println("-----------------------------------------------------");
			if(iteration==2) {
				//break;
			}
		}
	}

	public void addEdge(int source, int dest, int distance) {
		GraphNode node = new GraphNode(dest, distance);
		this.graph[source].add(node);
		return;
	}

}
