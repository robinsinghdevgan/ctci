import java.util.*;

class DirectedGraph <T>{
	private class Node {
		T data;
		ArrayList<Node> connections = null;
		Node(T data) {
			this.data = data;
			connections = new ArrayList<Node>();
		}
		public void addConnection(Node newConnection) {
			connections.add(newConnection);
		}
		private String printConnections() {
			StringBuilder sb = new StringBuilder();
			for (Node n : connections)
				sb.append(n.data.toString() + ", ");
			return sb.toString();
		}
		@Override
		public String toString() {
			if(connections != null)
				return "Node data: " + data.toString() + " \t\t[Connections: " + printConnections() + "]\n";
			return "";
        }
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DirectedGraph.Node)) {
                return false;
            }
            Node cmp = (Node) obj;
            return this.data == cmp.data;
        }

	}
    private ArrayList<Node> g = new ArrayList<Node>();
    
    @Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        Integer i = 0;
        for (Node n : g) {
            sb.append(i++ + ") "+ n.toString());
        }
        return sb.toString();
    }

    //Add nodes to graph
	public void add(T data) {
		Node newNode = new Node(data);
		g.add(newNode);
    }

    //Create a connection from Node A to Node B
	public void connectTwoNodes(T n1, T n2) {
		getNode(n1).addConnection(getNode(n2));
    }
    
    //Given a value, find if there exists a node with such a value
    private Node getNode(T data) {
        for (Node n : g) {
            if (n.data == data)
                return n;
        }
        return null;
    }
    //Check if a node exists in the graph
    public boolean contains(T value) {
        for (Node n : g) {
            if (n.data == value)
                return true;
        }
        return false;
    }

    /*
        SEARCH OPERATIONS
    */
    
    public String routeBetweenNodes(T startNodeData, T nodeContaingThisDataToFind) {
        Stack<Node> stack = new Stack<Node>();
        if (contains(startNodeData) && contains(nodeContaingThisDataToFind))
            if (routeBetweenNodes(getNode(startNodeData), getNode(nodeContaingThisDataToFind), stack)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Path Found from " +  startNodeData + " to " + nodeContaingThisDataToFind + "\n");
                sb.append(startNodeData + "->");
                while (!stack.isEmpty()) {
                    sb.append(stack.pop().data + "->");
                }
                sb.delete(sb.length()-2, sb.length());
                return sb.toString();
            }
        return "No Path exists";
    }
    
    private boolean routeBetweenNodes(Node start, Node destination, Stack<Node> stack) {
        if (start == destination) {
            return true;
        }
        for (Node n : start.connections) {
            if (routeBetweenNodes(n, destination, stack)) {
                stack.add(n);
                return true;
            }
        }
        return false;
    }

    // Depth First Search using values as arguments
    public boolean dfs(T startNodeData, T nodeContaingThisDataToFind) {
        if (contains(startNodeData) && contains(nodeContaingThisDataToFind))
            return dfs(getNode(startNodeData), getNode(nodeContaingThisDataToFind));
        return false;
    }

    //DFS using node as arguments
    private boolean dfs(Node start, Node destination) {
        
        if (start == destination)
            return true;
        for (Node n : start.connections) {
            if (dfs(n, destination))
                return true;
        }
        return false;
    }

    //Breadth First Search
    public boolean bfs(T startNodeData, T nodeContaingThisDataToFind) {
        if (contains(startNodeData) && contains(nodeContaingThisDataToFind))
            return bfs(getNode(startNodeData), getNode(nodeContaingThisDataToFind));
        return false;
    }

    //BFS using node as arguments
    private boolean bfs(Node start, Node destination) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(start);
        while(!q.isEmpty()) {
            //add all connections to q
            for(Node n : q.peek().connections)
                q.add(n);
            if (q.poll() == destination)
                return true;
        }
        return false;
    }

    public List<Node> topologicalSort() {
        var dependencies = new Stack<Node>();
        boolean[] visited = new boolean[g.size()];
        for (Node n : g)
            topologicalSort(n, dependencies, visited);
        return dependencies;
    }

    private void topologicalSort(DirectedGraph<T>.Node n, Stack<DirectedGraph<T>.Node> dependencies,
            boolean[] visited) {
        if(visited[g.indexOf(n)])
           return;
        visited[g.indexOf(n)] = true;
        for(Node c : n.connections) {
            topologicalSort(c,dependencies,visited);
        }
        dependencies.add(n);
    }

}

public class Ques4_7 {
	public static void main(String[] args) {
        DirectedGraph<Character> g = new DirectedGraph<Character>();

        Character[] nodes = {'a','b','c','d','e','f'};
        for (Character i : nodes)
            g.add(i);
        
        g.connectTwoNodes('a', 'd');
        g.connectTwoNodes('f', 'b');
        g.connectTwoNodes('b', 'd');
        g.connectTwoNodes('f', 'a');
        g.connectTwoNodes('d', 'c');
        
        System.out.println(g.topologicalSort());
	}
}