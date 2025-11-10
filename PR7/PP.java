import java.util.*;

class Process {
    int pid;    
    int at;     
    int bt;     
     int rt;     
    int pr;     
    int ct;     
      int tat;    
    int wt;     

    Process(int pid, int at, int bt, int pr) {
        this.pid = pid;
        this.at = at;
          this.bt = bt;
        this.rt = bt;
        this.pr = pr;
    }
}

public class PP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time, Burst Time and Priority for Process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int pr = sc.nextInt();
            p[i] = new Process(i + 1, at, bt, pr);
        }

       
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        int completed = 0, time = 0;
        while (completed < n) {
            int idx = -1;
            int bestPriority = Integer.MAX_VALUE;

            
            for (int i = 0; i < n; i++) {
                if (p[i].at <= time && p[i].rt > 0 && p[i].pr < bestPriority) {
                    bestPriority = p[i].pr;
                    idx = i;
                }
            }

            if (idx == -1) {
                time++; 
                continue;
            }

            
            p[idx].rt--;
            time++;

            if (p[idx].rt == 0) { 
                p[idx].ct = time;
                p[idx].tat = p[idx].ct - p[idx].at;
                p[idx].wt = p[idx].tat - p[idx].bt;
                completed++;
            }
        }

        
        System.out.println("\nPriority Preemptive Scheduling:");
        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (Process pr : p) {
            System.out.println(pr.pid + "\t" + pr.at + "\t" + pr.bt + "\t" + pr.pr +
                               "\t" + pr.ct + "\t" + pr.tat + "\t" + pr.wt);
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
