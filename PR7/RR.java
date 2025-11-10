// RoundRobin
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

public class RR {
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

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        Queue<Process> q = new LinkedList<>();
        int time = 0, completed = 0;
        int i = 0;

        while (completed < n) {
            
            while (i < n && p[i].at <= time) {
                q.add(p[i]);
                i++;
            }

            if (q.isEmpty()) {
                time++; 
                continue;
            }

            Process cur = q.poll();

            
            int execTime = Math.min(tq, cur.rt);
            cur.rt -= execTime;
            time += execTime;

            
            while (i < n && p[i].at <= time) {
                q.add(p[i]);
                i++;
            }

            if (cur.rt > 0) {
                q.add(cur); 
            } else {
                cur.ct = time;
                cur.tat = cur.ct - cur.at;
                cur.wt = cur.tat - cur.bt;
                completed++;
            }
        }

        
        System.out.println("\nRound Robin Scheduling:");
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
