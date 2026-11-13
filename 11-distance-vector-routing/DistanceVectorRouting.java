
import java.util.Scanner;

public class DistanceVectorRouting {
    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of routers: ");
        int n = sc.nextInt();

        char routers[] = new char[n];
        int cost[][] = new int[n][n];
        int dist[][] = new int[n][n];
        int next[][] = new int[n][n];

        for (int i = 0; i < n; i++)
            routers[i] = (char) ('A' + i);

        System.out.println("Enter the cost matrix (use 999 for infinity):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];
                next[i][j] = j;
            }
        }

        System.out.println("\nInitial distance tables:");
        printTables(n, routers, dist, next);

        int iteration = 0;
        boolean updated;

        do {
            updated = false;
            iteration++;

            int old[][] = new int[n][n];
            int newDist[][] = new int[n][n];
            int newNext[][] = new int[n][n];

            for (int i = 0; i < n; i++) {
                old[i] = dist[i].clone();
                newDist[i] = dist[i].clone();
                newNext[i] = next[i].clone();
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (cost[i][k] != INF && old[k][j] != INF &&
                            cost[i][k] + old[k][j] < newDist[i][j]) {

                            newDist[i][j] = cost[i][k] + old[k][j];
                            newNext[i][j] = k;
                            updated = true;
                        }
                    }
                }
            }

            dist = newDist;
            next = newNext;

            System.out.println("\n== Iteration " + iteration + " ==");
            printTables(n, routers, dist, next);

        } while (updated);

        System.out.println("\nConverged after " + iteration + " iteration(s).");

        sc.close();
    }

    static void printTables(int n, char routers[], int dist[][], int next[][]) {
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouter " + routers[i] + " Routing Tables:");
            System.out.println("Destination | Cost | Next Hop");

            for (int j = 0; j < n; j++) {
                String c = dist[i][j] >= INF ? "INF" : "" + dist[i][j];
                System.out.println(routers[j] + " | " + c + " | " + routers[next[i][j]]);
            }

            System.out.println("------------------------------");
        }
    }
}
