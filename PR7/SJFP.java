import java.util.*;

class Process {
    int pid;    
    int at;     
    int bt;     
    int rt;     
     int ct;    
    int tat;    
    int wt;     

    Process(int pid, int at, int bt) {
         this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
    }
}

public class SJFP {
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

        int completed = 0, time = 0;
        while (completed < n) {
            int idx = -1, minRT = Integer.MAX_VALUE;

            
            for (int i = 0; i < n; i++) {
                if (p[i].at <= time && p[i].rt > 0 && p[i].rt < minRT) {
                    minRT = p[i].rt;
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

        
        System.out.println("\nSJF Preemptive Scheduling (SRTF):");
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