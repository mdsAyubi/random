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

        // tg.topoSort();

        int M[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };
        // tg.countIslands(M);

        // tg.dijkstra(2);

        GraphAdjMatrix ga = new GraphAdjMatrix(5);
        ga.addEdge(1, 0);
        ga.addEdge(0, 2);
        ga.addEdge(2, 1);
        ga.addEdge(0, 3);
        ga.addEdge(3, 4);
        // ga.stronglyConnectedComponents();

        int mat[][] = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };

        Point source = new Point(0, 0);
        Point dest = new Point(3, 4);

        // ga.shortestPathInMaze(mat, source, dest);
        // s.connectStringsInCircles(List.of("aab", "abb"));
        // s.alienDictionaryOrder(List.of("caa", "aaa", "aab"), 3);

        int N = 30;
        int moves[] = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        s.minDiceThrowsForSnakeAndLadder(moves, N);

    }

    public void connectStringsInCircles(List<String> strings) {
        GraphAdjMatrix g = new GraphAdjMatrix(26);

        for (String string : strings) {
            var lower = string.toLowerCase();
            g.addEdge(lower.charAt(0) - 'a', lower.charAt(lower.length() - 1) - 'a');
        }

        var isEulerian = g.isEulerian();
        System.out.println(isEulerian);
    }

    public void alienDictionaryOrder(List<String> words, int alpha) {
        GraphAdjMatrix g = new GraphAdjMatrix(alpha);

        for (int i = 0; i < words.size() - 1; i++) {
            var w1 = words.get(i);
            var w2 = words.get(i + 1);

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    g.addEdge(w1.charAt(j) - 'a', w2.charAt(j) - 'a');
                    break; // stop the inner loop
                }
            }
        }

        java.util.Stack<Integer> stack = g.topoSort();
        while (!stack.isEmpty()) {
            System.out.println((char) ('a' + stack.pop()));
        }
    }

    /**
     * Find the minimum number of dice throws required to get to the end of the
     * snake ladder board(https://www.geeksforgeeks.org/snake-ladder-problem-2/)
     * 
     * @param move
     * @param n
     */
    public void minDiceThrowsForSnakeAndLadder(int[] move, int n) {
        var visited = new boolean[n];
        var queue = new ArrayDeque<SLNode>();

        visited[0] = true;
        queue.offer(new SLNode(0, 0));

        while (!queue.isEmpty()) {
            var node = queue.poll();

            if (node.index == n - 1) {
                System.out.println(node.distance);
                break;
            }

            for (int i = node.index; i < node.index + 6 && i < n; i++) {
                if (!visited[i]) {
                    var newD = node.distance + 1;
                    var newIndex = move[i] != -1 ? move[i] : i;
                    queue.add(new SLNode(newIndex, newD));
                }
            }
        }
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

    IntGraph(int[][] arr) {
        V = arr.length;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<>(arr[i].length));
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                adj.get(i).set(j, arr[i][j]);
            }
        }
    }

    public void print() {
        System.out.println(adj.toString());
    }

    /**
     * Count the number of islands in the graph
     * 
     * @param arr
     */
    public void countIslands(int arr[][]) {
        var visited = new boolean[V][V];
        var count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    dfsHelper(i, j, arr, visited);
                    count++;
                }
            }
        }
        System.out.println(count);
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

    private void dfsHelper(int row, int column, int[][] arr, boolean[][] visited) {
        System.out.println("Current DFS Node:" + row + "-" + column);
        visited[row][column] = true;
        var adjRow = new int[] { -1, -1, -1, 0, 1, 1, 1, 0, };
        var adjCol = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };

        for (int i = 0; i < adjCol.length; i++) {
            var nextRow = row + adjRow[i];
            var nextCol = column + adjCol[i];
            if (isSafe(nextRow, nextCol, arr) && !visited[nextRow][nextCol] && arr[nextRow][nextCol] == 1) {
                dfsHelper(nextRow, nextCol, arr, visited);
            }
        }

    }

    private boolean isSafe(int nextRow, int nextCol, int[][] arr) {
        return nextRow >= 0 && nextRow < arr.length && nextCol >= 0 && nextCol < arr.length;
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
     * 1. Put the <node,distance> pair in a min heap(comparator is by distance),
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
    public void dijkstra(int src) {

        var pq = new PriorityQueue<NodePair>((NodePair n1, NodePair n2) -> {
            return n1.distance - n2.distance;
        });
        var parentNodes = new HashMap<Integer, Integer>();
        var distance = new HashMap<Integer, Integer>();

        pq.add(new NodePair(src, 0));
        distance.put(src, 0);
        parentNodes.put(src, null);

        while (!pq.isEmpty()) {

            NodePair current = pq.poll();
            int u = current.node;
            System.out.println("Processing --" + u);
            for (var i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i);
                int newD = distance.get(u) + 1; // adj.get(u).get(v) for weighted;

                if (newD < distance.getOrDefault(v, Integer.MAX_VALUE)) {
                    distance.put(v, newD);
                    pq.add(new NodePair(v, newD));
                    parentNodes.put(v, u);
                }
            }
        }

        System.out.println(distance);

        System.out.println("Paths--");
        for (int i = 0; i < V; i++) {
            System.out.println("Path from " + i);
            int n = i;
            while (parentNodes.get(n) != null) {
                System.out.print(parentNodes.get(n) + " ");
                n = parentNodes.get(n);
            }
            System.out.println();
        }

    }

    /**
     * Implement dijkstra
     * 
     * Approach:
     * 
     * 1. Put the <node,distance> pair in a min heap(comparator is by distance),
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
    public void dijkstraAdjMatrix(int[][] arr, int src) {

        var pq = new PriorityQueue<NodePair>((NodePair n1, NodePair n2) -> {
            return n1.distance - n2.distance;
        });
        var parentNodes = new HashMap<Integer, Integer>();
        var distance = new HashMap<Integer, Integer>();

        pq.add(new NodePair(src, 0));
        distance.put(src, 0);
        parentNodes.put(src, null);

        while (!pq.isEmpty()) {

            NodePair current = pq.poll();
            int u = current.node;
            System.out.println("Processing --" + u);
            for (var v = 0; v < arr[u].length && arr[u][v] != -1; v++) {
                int newD = distance.get(u) + arr[u][v];

                if (newD < distance.getOrDefault(v, Integer.MAX_VALUE)) {
                    distance.put(v, newD);
                    pq.add(new NodePair(v, newD));
                    parentNodes.put(v, u);
                }
            }
        }

        System.out.println(distance);

        System.out.println("Paths--");
        for (int i = 0; i < V; i++) {
            System.out.println("Path from " + i);
            int n = i;
            while (parentNodes.get(n) != null) {
                System.out.print(parentNodes.get(n) + " ");
                n = parentNodes.get(n);
            }
            System.out.println();
        }
    }
}

class NodePair {
    int node;
    int distance;

    NodePair(int n, int d) {
        this.node = n;
        this.distance = d;
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

        visited.put(u, true);
        q.add(u);

        while (q.size() != 0) {
            var node = q.poll();
            System.out.println("Current BFS Node:" + node);

            for (var next : adj.get(node)) {
                if (!visited.getOrDefault(next, false)) {
                    visited.put(next, true);
                    q.add(next);
                }
            }
        }
    }

    public boolean isReachable(E u, E v) {
        var queue = new ArrayDeque<E>();
        var visited = new HashMap<E, Boolean>();

        queue.offer(u);
        visited.put(u, true);

        while (!queue.isEmpty()) {
            var node = queue.poll();

            if (node.equals(v)) {
                return true;
            }

            for (var adjNode : adj.get(node)) {
                if (!visited.getOrDefault(adjNode, false)) {
                    visited.put(adjNode, true);
                    queue.add(adjNode);
                }
            }
        }
        return false;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class GraphAdjMatrix {
    int[][] g;
    int V;
    int[] in;
    int[] out;

    GraphAdjMatrix(int v) {
        this.V = v;
        g = new int[v][v];
        in = new int[v];
        out = new int[v];
    }

    GraphAdjMatrix(int[][] matrix) {
        this.V = matrix.length;
        g = matrix;
        in = new int[this.V];
        out = new int[this.V];
        updateInOut();
    }

    private void updateInOut() {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] != 0) {
                    out[i]++;
                    in[j]++;
                }
            }
        }
    }

    public void addEdge(int u, int v) {
        g[u][v] = 1;
        in[v]++;
        out[u]++;
    }

    public void addEdge(int u, int v, int weight) {
        g[u][v] = weight;
        in[v]++;
        out[u]++;
    }

    public GraphAdjMatrix transpose() {

        var mT = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                mT[i][j] = g[j][i];
            }
        }

        var gT = new GraphAdjMatrix(mT);
        return gT;
    }

    public List<Integer> dfs(int src) {
        var visited = new boolean[V];
        var nodes = new ArrayList<Integer>();
        dfsHelper(src, visited, nodes);
        return nodes;
    }

    private void dfsHelper(int u, boolean[] visited, ArrayList<Integer> nodes) {
        visited[u] = true;
        nodes.add(u);
        for (int v = 0; v < g[u].length; v++) {
            if (g[u][v] == 1 && !visited[v])
                dfsHelper(v, visited, nodes);
        }
    }

    private void fillOrder(int u, boolean[] visited, java.util.Stack<Integer> stack) {
        visited[u] = true;

        for (int v = 0; v < g[u].length; v++) {
            if (g[u][v] == 1 && !visited[v])
                fillOrder(v, visited, stack);
        }

        stack.push(u);
    }

    public java.util.Stack<Integer> topoSort() {
        var visited = new boolean[V];
        java.util.Stack<Integer> stack = new java.util.Stack<Integer>();

        for (var i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSortHelper(i, visited, stack);
            }
        }
        return stack;
    }

    private void topoSortHelper(int u, boolean[] visited, java.util.Stack<Integer> stack) {
        visited[u] = true;

        for (int v = 0; v < g[u].length; v++) {
            if (g[u][v] == 1 && !visited[v])
                topoSortHelper(v, visited, stack);
        }

        stack.push(u);
    }

    /**
     * find the Strongly Connected
     * Components(https://www.geeksforgeeks.org/strongly-connected-components/)
     * 
     * Approach:
     * 
     * 1) Create an empty stack ‘S’ and do DFS traversal of a graph. In DFS
     * traversal, after calling recursive DFS for adjacent vertices of a vertex,
     * push the vertex to stack. In the above graph, if we start DFS from vertex 0,
     * we get vertices in stack as 1, 2, 4, 3, 0.
     * 
     * 2) Reverse directions of all arcs to obtain the transpose graph.
     * 
     * 3) One by one pop a vertex from S while S is not empty. Let the popped vertex
     * be ‘v’. Take v as source and do DFS (call DFSUtil(v)). The DFS starting from
     * v prints strongly connected component of v. In the above example, we process
     * vertices in order 0, 3, 4, 2, 1 (One by one popped from stack).
     */
    public void stronglyConnectedComponents() {
        var stack = new java.util.Stack<Integer>();
        var visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        var graphT = this.transpose();
        Arrays.fill(visited, false);

        while (!stack.empty()) {
            var node = stack.pop();
            if (!visited[node]) {
                var nodes = new ArrayList<Integer>();
                graphT.dfsHelper(node, visited, nodes);
                System.out.println(nodes);
            }
        }
    }

    /**
     * 
     */
    public boolean isStronglyConnected() {
        var visited = new boolean[V];
        int n = 0;
        for (int i = 0; i < V; i++) {
            if (out[i] > 0) {
                n = i;
                break;
            }
        }

        var nodes = new ArrayList<Integer>();
        this.dfsHelper(n, visited, nodes);
        // If dfs could not visit any nodes
        for (int i = 0; i < visited.length; i++) {
            if (out[i] > 0 && !visited[i]) {
                return false;
            }
        }

        var graphT = this.transpose();
        Arrays.fill(visited, false);

        var nodes2 = new ArrayList<Integer>();
        graphT.dfsHelper(n, visited, nodes2);

        // if the nodes could not be visited
        for (int i = 0; i < visited.length; i++) {
            if (out[i] > 0 && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the graph has a eulerian circuit or not
     * 
     * Approach: A graph has eulerian circuit if it strongly connected and the
     * indegree of all the nodes with positive outdegrees match
     * 
     * @return
     */
    public boolean isEulerian() {
        if (!isStronglyConnected()) {
            return false;
        }

        for (int i = 0; i < V; i++) {
            if (in[i] != out[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Find the shorted path between source and destination in a maze()
     * 
     * @param maze
     * @param source
     * @param dest
     */
    public void shortestPathInMaze(int[][] maze, Point source, Point dest) {

        var queue = new ArrayDeque<MazeNode>();
        var visited = new boolean[maze.length][maze[0].length];

        if (maze[source.x][source.y] != 1 || maze[dest.x][dest.y] != 1) {
            System.out.println("Invalid");
        }

        var rowShift = new int[] { -1, 0, 1, 0 };
        var colShift = new int[] { 0, 1, 0, -1 };

        queue.offer(new MazeNode(source, 0));
        visited[source.x][source.y] = true;

        while (!queue.isEmpty()) {
            MazeNode current = queue.poll();

            if (current.p.x == dest.x && current.p.y == dest.y) {
                System.out.println("Shorted path is of " + current.distance);
                return;
            }

            for (int i = 0; i < 4; i++) {
                var newRow = current.p.x + rowShift[i];
                var newCol = current.p.y + colShift[i];

                if (isSafe(newRow, newCol, maze) && maze[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.add(new MazeNode(new Point(newRow, newCol), current.distance + 1));
                }
            }
        }

        System.out.println("Path not found");

    }

    private boolean isSafe(int newRow, int newCol, int[][] maze) {
        return newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length;
    }

    /**
     * Find the minimum cost path between (0,0) and (row,col)
     * 
     * @param arr
     * @param row
     * @param col
     * @return
     */
    public int minCostPath(int[][] arr, int row, int col) {
        if (row < 0 || col < 0) {
            return Integer.MAX_VALUE;
        }
        if (row == 0 && col == 0) {
            return arr[row][col];
        }

        return arr[row][col] + min(minCostPath(arr, row - 1, col - 1), minCostPath(arr, row - 1, col),
                minCostPath(arr, row, col - 1));
    }

    int min(int x, int y, int z) {
        if (x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    }

    public int[][] floydWarshall() {
        var result = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (g[i][j] == 0)
                    result[i][j] = Integer.MAX_VALUE;
                else
                    result[i][j] = g[i][j];
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }

        return result;
    }
}

class SLNode {
    int index;
    int distance;

    SLNode(int index, int d) {
        this.index = index;
        this.distance = d;
    }
}

class MazeNode {
    Point p;
    int distance;

    MazeNode(Point p, int d) {
        this.p = p;
        this.distance = d;
    }
}