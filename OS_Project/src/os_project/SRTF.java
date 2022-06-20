package os_project;

import java.io.*;
import java.util.LinkedList;

class Process {

    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
    int st; // Start Time
    int ct; // Complete Time

    public Process(int pid, int bt, int art) {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }

    public Process(Process p) {
        this.pid = p.pid;
        this.bt = p.bt;
        this.art = p.art;
    }
}

public class SRTF {

    // Method to find the waiting time for all
    // processes
    private static LinkedList<Process> seekSequence = new LinkedList();

    static void findWaitingTime(Process proc[], int n,
            int wt[]) {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++) {
            rt[i] = proc[i].bt;
        }

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        Process prev = null;
        while (complete != n) {
            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t)
                        && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }
            if (prev == null) {
                prev = new Process(proc[shortest]);
                prev.st = t;
                prev.ct = t + 1;
                //System.out.printf("P%d STARTED AT %d ENDED AT %d\n", prev.pid, prev.st, prev.ct);
                seekSequence.add(prev);
            } else if (prev.pid == proc[shortest].pid) {
                prev.ct++;
            } else if (prev.pid != proc[shortest].pid) {
                //System.out.printf("P%d STARTED AT %d ENDED AT %d\n", prev.pid, prev.st, prev.ct);
                prev = new Process(proc[shortest]);
                prev.st = t;
                prev.ct = t + 1;
                seekSequence.add(prev);
                //System.out.printf("P%d STARTED AT %d ENDED AT %d\n", prev.pid, prev.st, prev.ct);
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0) {
                minm = Integer.MAX_VALUE;
            }

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time
                        - proc[shortest].bt
                        - proc[shortest].art;

                if (wt[shortest] < 0) {
                    wt[shortest] = 0;
                }
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process proc[], int n,
            int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++) {
            tat[i] = proc[i].bt + wt[i];
        }
    }

    // Method to calculate average time
    static void findavgTime(Process proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("\nPID\tAT\tBT\tWT\tTAT");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(proc[i].pid + "\t" + proc[i].art + "\t"
                    + proc[i].bt + "\t " + wt[i]
                    + "\t" + tat[i]);
        }

        System.out.println("\nAverage waiting time = "
                + (float) total_wt / (float) n);
        System.out.println("Average turn around time = "
                + (float) total_tat / (float) n);

        System.out.println("\n=-=-=-=-= GANTT CHART =-=-=-=-=");
        for (int i = 0; i < seekSequence.size(); i++) {
            Process node = seekSequence.get(i);
            if (i + 1 < seekSequence.size()) {
                System.out.printf("%d [P%d] ", node.st, node.pid+1);
            } else {
                System.out.printf("%d [P%d] %d", node.st, node.pid+1, node.ct);
            }
        }
        System.out.println("\n");
    }

    // Driver Method
    public static void run(int[] AT, int[] BT, int n) {
        Process proc[] = new Process[n];
        for (int i = 0; i < n; i++) {
            proc[i] = new Process(i, BT[i], AT[i]);
        }

        findavgTime(proc, proc.length);
    }
}
