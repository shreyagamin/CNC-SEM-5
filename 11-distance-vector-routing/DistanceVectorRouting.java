
import java.util.Scanner;

public class DistanceVectorRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int INF = 999;

        System.out.print("Enter number of routers: ");
        int n = sc.nextInt();

        char routers[] = new char[n];
        int cost[][] = new int[n][n];
        int dist[][] = new int[n][n];
        int next[][] = new int[n][n];

        System.out.println("Enter cost matrix:");

        for (int i = 0; i < n; i++) {
            routers[i] = (char)('A' + i);

            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];
                next[i][j] = j;
            }
        }

        boolean updated = true;
        int iteration = 0;

        while (updated) {
            updated = false;
            iteration++;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {

                        if (dist[i][j] > cost[i][k] + dist[k][j]) {
                            dist[i][j] = cost[i][k] + dist[k][j];
                            next[i][j] = k;
                            updated = true;
                        }
                    }
                }
            }
        }

        // Print routing tables
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouter " + routers[i]);
            System.out.println("Destination  Cost  Next Hop");

            for (int j = 0; j < n; j++) {
                String c = dist[i][j] >= INF ? "INF" : "" + dist[i][j];

                System.out.println(routers[j] + "           " + c + "     " + routers[next[i][j]]);
            }
        }

        System.out.println("\nConverged after " + iteration + " iterations.");

        sc.close();
    }
}
