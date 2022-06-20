package os_project;

import java.util.*;

public class FCFS {

    public static void main(int p, int[] ar, int[] bt) {
        int n = p;
        int pid[] = new int[n];   // process ids
        int ct[] = new int[n];     // completion times
        int ta[] = new int[n];     // turn around times
        int wt[] = new int[n];     // waiting times
        int temp;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
        }

//sorting according to arrival times
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (ar[j] > ar[j + 1]) {
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }
// finding completion times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];
            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else {
                    ct[i] = ct[i - 1] + bt[i];
                }
            }
            ta[i] = ct[i] - ar[i];        // turnaround time= completion time- arrival time
            wt[i] = ta[i] - bt[i];        // waiting time= turnaround time- burst time
            avgwt += wt[i];               // total waiting time
            avgta += ta[i];               // total turnaround time
        }
        System.out.println("=-=-=-=-= GANTT CHART =-=-=-=-=");
        System.out.print("| " + ar[0]);
        for (int i = 0; i < n; i++) {
            System.out.print("(P"+pid[i]+")" + ct[i] + " | ");
            if(i+1<n) {
                System.out.print(ct[i]);
            }
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\nAVE. WT = " + (avgwt / n));     // printing average waiting time.
        System.out.println("AVE. TAT = " + (avgta / n));    // printing average turnaround time.
        
        
    }
}
