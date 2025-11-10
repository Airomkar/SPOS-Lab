import java.util.Scanner;

public class SimpleBankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of processes and resources
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] allocation = new int[n][m];
        int[][] max = new int[n][m];
        int[][] need = new int[n][m];
        int[] available = new int[m];
        boolean[] finished = new boolean[n];
        int[] safeSeq = new int[n];

        // Input allocation matrix
        System.out.println("Enter Allocation Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                allocation[i][j] = sc.nextInt();

        // Input max matrix
        System.out.println("Enter Max Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max[i][j] = sc.nextInt();

        // Calculate Need Matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                need[i][j] = max[i][j] - allocation[i][j];

        // Input available resources
        System.out.println("Enter Available Resources:");
        for (int i = 0; i < m; i++)
            available[i] = sc.nextInt();

        // Safety Algorithm
        int count = 0;
        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finished[i]) {
                    boolean canExecute = true;
                    for (int j = 0; j < m; j++) {
                        if (need[i][j] > available[j]) {
                            canExecute = false;
                            break;
                        }
                    }

                    if (canExecute) {
                        // Process can safely execute
                        for (int j = 0; j < m; j++)
                            available[j] += allocation[i][j];
                        safeSeq[count++] = i;
                        finished[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) { // No process could be executed
                System.out.println("System is not in a safe state!");
                return;
            }
        }

        // Print Safe Sequence
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSeq[i]);
            if (i != n - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}

// Enter number of processes: 5
// Enter number of resources: 3

// Enter Allocation Matrix:
// 0 1 0
// 2 0 0
// 3 0 2
// 2 1 1
// 0 0 2

// Enter Max Matrix:
// 7 5 3
// 3 2 2
// 9 0 2
// 2 2 2
// 4 3 3

// Enter Available Resources:
// 3 3 2

// Safe Sequence: P1 -> P3 -> P4 -> P0 -> P2
