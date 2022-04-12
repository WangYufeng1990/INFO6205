package Assignment7;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Clone Graph
    private Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node newNode = new Node(node.val, new ArrayList<>());
        visited.put(node, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }

    //2.Shortest Path Visiting All Nodes
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[len][1 << len];
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int index = tuple[0], mask = tuple[1], distance = tuple[2];

            if (mask == (1 << len) - 1) return distance;

            for (int node : graph[index]) {
                int nextMask = mask | (1 << node);
                if (!visited[node][nextMask]) {
                    queue.offer(new int[]{node, nextMask, distance + 1});
                    visited[node][nextMask] = true;
                }
            }
        }
        return 0;
    }

    //3.Maximum Path Quality of a Graph
    int res = 0;
    Map<Integer,List<int[]>> adj;
    int len;
    int[] vis;
    int[] values;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.len = values.length;
        this.adj = new HashMap<>();
        this.vis = new int[len];
        vis[0] = 1;
        this.values = values;
        for(int i = 0 ; i < len ; i++){
            adj.put(i , new ArrayList<>());
        }
        for(int[] e : edges){
            int start = e[0] , end = e[1] , value = e[2];
            adj.get(start).add(new int[]{end , value});
            adj.get(end).add(new int[]{start , value});
        }
        dfs(0 , maxTime , values[0]);
        return res;
    }
    void dfs(int cur , int remain , int sum){
        if(cur == 0){
            res = Math.max(res , sum);
        }
        for(int[] t : adj.get(cur)){
            if(t[1] > remain) continue;
            if(++vis[t[0]] == 1){
                sum += values[t[0]];
            }
            dfs(t[0] , remain - t[1] , sum);
            if(--vis[t[0]] == 0){
                sum -= values[t[0]];
            }
        }
    }
}
