import java.util.Scanner;

public class FirstFit {

    public static void firstFit(int blockSize[], int m, int processSize[], int n, int allocation[]) {
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break; 
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        int totalMemory = 0;
        
        System.out.println("Enter size of each memory block: ");
        for (int i = 0; i < m; i++) {
            int size = sc.nextInt();
            blockSize[i] = size;
            totalMemory += size;
        }
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }
        firstFit(blockSize, m, processSize, n, allocation);

        System.out.println("\n--- First Fit Allocation Results ---");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        
        int usedMemory = 0;
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.println(allocation[i] + 1);
                usedMemory += processSize[i];
            } else {
                System.out.println("Not Allocated");
            }
        }

        int totalFragmentation = totalMemory - usedMemory;
        double memoryUsage = (totalMemory > 0) ? ((double) usedMemory / totalMemory) * 100 : 0.0;
        
        System.out.println("\nTotal Initial Memory: " + totalMemory);
        System.out.println("Total Allocated Memory: " + usedMemory);
        System.out.println("Total Fragmentation: " + totalFragmentation);
        System.out.printf("Memory Usage: %.2f%%\n", memoryUsage);
        
        sc.close();
    }
}
