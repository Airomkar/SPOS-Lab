import java.util.*;

class Process {
    int pid;    
     int at;     
    int bt;     
      int ct;     
    int tat;    
     int wt;     

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        }
}

public class FCFS {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

          for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            p[i] = new Process(i + 1, at, bt);
        }

        
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

         int time = 0;
          for (int i = 0; i < n; i++) {
            if (time < p[i].at) time = p[i].at; 
            p[i].ct = time + p[i].bt;
            time = p[i].ct;

            p[i].tat = p[i].ct - p[i].at;
            p[i].wt = p[i].tat - p[i].bt;
        }

       
        System.out.println("\nFCFS Scheduling:");
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (Process pr : p) {
            System.out.println(pr.pid + "\t" + pr.at + "\t" + pr.bt + "\t" +
                               pr.ct + "\t" + pr.tat + "\t" + pr.wt);
        }

        
        double avgTAT = 0, avgWT = 0;
        for (Process pr : p) {
            avgTAT += pr.tat;
            avgWT += pr.wt;
        }
        avgTAT /= n;
        avgWT /= n;

        System.out.println("\nAverage Turnaround Time = " + avgTAT);
        System.out.println("Average Waiting Time = " + avgWT);

        sc.close();
    }
}
