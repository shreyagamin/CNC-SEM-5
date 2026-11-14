
import java.util.Scanner;

public class DistanceVectorRouting {
    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of routers: ");
        int n = sc.nextInt();

        int cost[][] = new int[n][n];
        int dist[][] = new int[n][n];
        int next[][] = new int[n][n];
        char r[] = new char[n];

        // Assign router names and read cost matrix
        for (int i = 0; i < n; i++) {
            r[i] = (char)('A' + i);
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];
                next[i][j] = j;
            }
        }

        boolean updated;
        int iteration = 0;

        // Repeat until no changes
        do {
            updated = false;
            iteration++;

            int old[][] = new int[n][n];

            for (int i = 0; i < n; i++)
                old[i] = dist[i].clone();

            // Find shorter paths
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (cost[i][k] != INF && old[k][j] != INF &&
                            cost[i][k] + old[k][j] < dist[i][j]) {

                            dist[i][j] = cost[i][k] + old[k][j];
                            next[i][j] = k;
                            updated = true;
                        }
                    }
                }
            }

            // Print routing tables
            System.out.println("\nIteration " + iteration);

            for (int i = 0; i < n; i++) {
                System.out.println("\nRouter " + r[i]);
                System.out.println("Destination  Cost  Next Hop");

                for (int j = 0; j < n; j++) {
                    System.out.println(r[j] + "           " +
                        (dist[i][j] == INF ? "INF" : dist[i][j]) +
                        "     " + r[next[i][j]]);
                }
            }

        } while (updated);

        System.out.println("\nConverged!");
        sc.close();
    }
}
