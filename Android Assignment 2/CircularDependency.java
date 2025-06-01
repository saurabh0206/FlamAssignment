import java.util.*;

public class CircularDependency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] tokens = sc.nextLine().split(" ");
        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < tokens.length; i += 2) {
            int a = Integer.parseInt(tokens[i]);
            int b = Integer.parseInt(tokens[i + 1]);
            edges.add(Arrays.asList(a, b));
        }

        boolean result = hasCircularDependency(n, edges);
        System.out.println(result);
    }

    public static boolean hasCircularDependency(int n, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) graph.get(edge.get(0)).add(edge.get(1));
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, visited)) return true;
        }
        return false;
    }

    public static boolean dfs(int node, List<List<Integer>> graph, int[] visited) {
        if (visited[node] == 1) return true;
        if (visited[node] == 2) return false;
        visited[node] = 1;
        for (int nei : graph.get(node)) {
            if (dfs(nei, graph, visited)) return true;
        }
        visited[node] = 2;
        return false;
    }
}
