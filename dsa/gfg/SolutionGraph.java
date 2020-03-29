import java.util.*;

class SolutionGraph {
    public static void main(final String[] args) {
        final var s = new SolutionGraph();

        var g = new Graph<Integer>();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        // g.dfs(0);
        // g.bfs(2);

        var g1 = new IntGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        // g1.isCyclicUndirected();

        var graph = new IntGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        // graph.isCyclicDirected();

        // Create a graph given in the above diagram
        IntGraph tg = new IntGraph(6);
        tg.addEdge(5, 2);
        tg.addEdge(5, 0);
        tg.addEdge(4, 0);
        tg.addEdge(4, 1);
        tg.addEdge(2, 3);
        tg.addEdge(3, 1);

        tg.topoSort();

    }

}

class IntGraph {
    int V;
    List<List<Integer>> adj;

    IntGraph(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public void dfs(int u) {
        boolean[] visited = new boolean[V];
        dfsHelper(u, visited);
    }

    private void dfsHelper(int node, boolean[] visited) {
        System.out.println("Current DFS Node:" + node);
        visited[node] = true;

        for (int n : adj.get(node)) {
            if (!visited[n]) {
                dfsHelper(n, visited);
            }
        }
    }

    /**
     * Find if there is a cycle in the graph
     * 
     * Approach: Make a visited array - all false. Start with the unvisited nodes
     * with paren as -1.
     * 
     * When there is a visited node which is not the parent of the current node, it
     * means you have reached a cycle
     */
    public void isCyclicUndirected() {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclicHelper(i, -1, visited)) {
                    System.out.println("Found cycle from " + i);
                }
            }
        }
    }

    private boolean isCyclicHelper(int current, int parent, boolean[] visited) {
        visited[current] = true;

        for (int node : adj.get(current)) {
            if (!visited[node]) {
                if (isCyclicHelper(node, current, visited)) {
                    return true;
                }
            } else {
                if (node != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 
     */
    public void isCyclicDirected() {
        var visited = new boolean[V];
        var recStack = new boolean[V];

        for (int i = 0; i < V; i++) {

            if (isCyclicHelperDirected(i, visited, recStack)) {
                System.out.println("Found cycle from:" + i);
                break;
            }

        }
    }

    private boolean isCyclicHelperDirected(int current, boolean[] visited, boolean[] recStack) {
        if (recStack[current]) {
            return true;
        }

        if (visited[current]) {
            return false;
        }

        visited[current] = true;
        recStack[current] = true;
        for (var node : adj.get(current)) {
            if (isCyclicHelperDirected(node, visited, recStack)) {
                return true;
            }
        }
        recStack[current] = false;
        return false;
    }

    public void topoSort() {
        var visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (var i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSortHelper(i, visited, stack);
            }
        }
        System.out.println(stack.toString());
    }

    private void topoSortHelper(int current, boolean[] visited, Deque<Integer> stack) {

        visited[current] = true;

        for (var node : adj.get(current)) {
            if (!visited[node]) {
                topoSortHelper(node, visited, stack);
            }
        }

        stack.push(current);
    }

    /**
     * 
     */
    public void connectedComponents() {

        var visited = new boolean[V];
        var count = 0;
        for (var i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsHelper(i, visited);
                count++;
            }
        }
        System.out.println(count);

    }

    /**
     * Implement dijkstra
     * 
     * Approach:
     * 
     * 1. Put the <node,distance> pair in a min heap(comparator is by distabce),
     * make source as <source, 0>
     * 
     * 2. Initialize a visited set of nodes with {}
     * 
     * 3. Initialize the parent array with null for every node
     * 
     * 4. while the heap is not empty, pick the min from the heap, if not in visted
     * set already
     * 
     * - update the distance from source to the adjacent nodes
     * 
     * - put this node in the visited set
     * 
     * - update the parent
     */
    public void dijkstra() {

    }
}

class Graph<E extends Comparable<E>> {
    Map<E, ArrayList<E>> adj;
    int V;

    public Graph() {
        adj = new HashMap<E, ArrayList<E>>();
    }

    public void addEdge(final E u, final E w) {
        adj.putIfAbsent(u, new ArrayList<E>());
        adj.get(u).add(w);
    }

    public Set<E> getVertices() {
        return adj.keySet();
    }

    public void dfs(E u) {
        var visited = new HashMap<E, Boolean>();
        for (var vertex : adj.keySet()) {
            visited.put(vertex, false);
        }
        dfsHelper(u, visited);
    }

    private void dfsHelper(final E node, final Map<E, Boolean> visited) {
        System.out.println("Current DFS Node:" + node);
        visited.put(node, true);

        for (var n : adj.get(node)) {
            if (!visited.get(n)) {
                dfsHelper(n, visited);
            }
        }
    }

    public void bfs(E u) {
        var q = new LinkedList<E>();
        var visited = new HashMap<E, Boolean>();
        for (var vertex : adj.keySet()) {
            visited.put(vertex, false);
        }

        visited.put(u, true);
        q.add(u);

        while (q.size() != 0) {
            var node = q.poll();
            System.out.println("Current BFS Node:" + node);

            for (var next : adj.get(node)) {
                if (!visited.get(next)) {
                    visited.put(next, true);
                    q.add(next);
                }
            }
        }

    }
}