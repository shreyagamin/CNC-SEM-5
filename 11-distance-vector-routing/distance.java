
import java.util.*;

public class DistanceVectorRouting {

    static final int INF = 999;

    // Input topology
    static void input_topology(Scanner sc) {

        System.out.print("Enter the number of routers: ");
        int n = sc.nextInt();

        char[] routers = new char[n];

        for (int i = 0; i < n; i++) {
            routers[i] = (char) ('A' + i);
        }

        System.out.println("\nEnter the cost matrix (use 999 for infinity): ");

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.out.print("Row " + (i + 1) + ": ");

            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        distance_vector_routing(n, routers, cost);
    }

    // Distance Vector Routing
    static void distance_vector_routing(int n, char[] routers, int[][] cost) {

        int[][] dist = new int[n][n];
        int[][] next_hop = new int[n][n];

        // Initialize distance and next hop
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = cost[i][j];
                next_hop[i][j] = j;
            }
        }

        System.out.println("\nInitial distance tables:");
        print_tables(routers, dist, next_hop);

        boolean updated = true;
        int iteration = 0;

        while (updated) {

            updated = false;
            iteration++;

            System.out.println("\n== Iteration " + iteration + " ==");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {

                        if (dist[i][j] > cost[i][k] + dist[k][j]) {

                            dist[i][j] = cost[i][k] + dist[k][j];

                            next_hop[i][j] = k;

                            updated = true;
                        }
                    }
                }
            }
        }

        print_tables(routers, dist, next_hop);

        System.out.println("\nConverged after " + iteration + " iteration(s).");
    }

    // Print routing tables
    static void print_tables(char[] routers, int[][] dist, int[][] next_hop) {

        int n = routers.length;

        for (int i = 0; i < n; i++) {

            System.out.println("\nRouter " + routers[i] + " Routing Tables:");

            System.out.println("Destination | Cost | Next Hop");

            for (int j = 0; j < n; j++) {

                String cost_str = (dist[i][j] >= INF)
                        ? "INF"
                        : String.valueOf(dist[i][j]);

                char nh = routers[next_hop[i][j]];

                System.out.printf("%-11s | %3s | %c%n",
                        routers[j], cost_str, nh);
            }

            System.out.println("------------------------------");
        }
    }

    // Main function
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        input_topology(sc);

        sc.close();
    }
}
