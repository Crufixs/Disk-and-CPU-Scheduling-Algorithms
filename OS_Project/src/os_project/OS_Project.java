package os_project;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OS_Project {
 /**
 *
 * @author angelo
 */

    static Scanner scanny = new Scanner(System.in);
    static boolean continueTask = true;

    public static void main(String[] args) {

        do {
            System.out.print("\nCHOOSE A TASK \n[A]CPU SCHEDULING\n[B]DISK SCHEDULING\n: ");
            String task = scanny.nextLine();
            if (task.equalsIgnoreCase("A")) { //IF CPU SCHEDULING
                task = "";
                System.out.print("\nCHOOSE A TASK \n[A]PREEMPTIVE SCHEDULING\n[B]NON-PREEMPTIVE SCHEDULING\n: ");
                task = scanny.nextLine();
                if (task.equalsIgnoreCase("A")) { //IF PREEMPTIVE SCHEDULING
                    task = "";
                    System.out.print("\nCHOOSE A TASK\n[A]SHORTEST REMAINING TIME FIRST\n[B]PRIORITY SCHEDULING\n: ");
                    task = scanny.nextLine();
                    if (task.equalsIgnoreCase("A")) {//IF SJF
                        task = "";
                        printTask("SHORTEST REMAINING TIME FIRST");
                        do {
                            SJF(inputProcesses());
                            continueTask();
                        } while (continueTask);
                    } else if (task.equalsIgnoreCase("B")) {// IF PRIORITY
                        task = "";
                        printTask("PRIORITY SCHEDULING");
                        do {
                            PreemptivePriority(inputProcesses());
                            continueTask();
                        } while (continueTask);
                    }
                } else if (task.equalsIgnoreCase("B")) { // IF NON-PREEMPTIVE SCHEDULING
                    task = "";
                    System.out.print("\nCHOOSE A TASK\n[A]FIRST COME FIRST SERVE\n[B]SHORTEST JOB FIRST\n: ");
                    task = scanny.nextLine();
                    if (task.equalsIgnoreCase("A")) {//IF FCFS
                        task = "";
                        printTask("FIRST COME FIRST SERVE");
                        do {
                            FCFS(inputProcesses());
                            continueTask();
                        } while (continueTask);
                    } else if (task.equalsIgnoreCase("B")) {//IF FCFS
                        task = "";
                        printTask("SHORTEST JOB FIRST");
                        do {
                            SJF_NPE(inputProcesses());
                            System.out.println("");
                            continueTask();
                        } while (continueTask);
                    }
                }
            } else if (task.equalsIgnoreCase("B")) {// IF DISK SCHEDULING
                task = "";
                System.out.print("\nCHOOSE A TASK\n[A]FCFS\n[B]SSTF\n[C]SCAN\n[D]LOOK\n: ");
                task = scanny.nextLine();
                if (task.equalsIgnoreCase("A")) {//IF A
                    task = "";
                    printTask("FIRST COME FIRST SERVE");
                    do {
                        System.out.print("\nINPUT CURRENT HEAD POSITION: ");
                        FCFS_2(scanny.nextInt());
                        System.out.println("");
                        continueTask();
                    } while (continueTask);
                } else if (task.equalsIgnoreCase("B")) {//IF B
                    task = "";
                    printTask("SHORTEST SEEK TIME FIRST");
                    do {
                        System.out.print("\nINPUT CURRENT HEAD POSITION: ");
                        SSTF(scanny.nextInt());
                        System.out.println("");
                        continueTask();
                    } while (continueTask);
                } else if (task.equalsIgnoreCase("C")) {//IF C
                    task = "";
                    printTask("SCAN");
                    do {
                        System.out.print("\nINPUT CURRENT HEAD POSITION: ");
                        SCAN(scanny.nextInt());
                        System.out.println("");
                        continueTask();
                    } while (continueTask);
                } else if (task.equalsIgnoreCase("D")) {//IF D
                    task = "";
                    printTask("LOOK");
                    do {
                        System.out.print("\nINPUT CURRENT HEAD POSITION: ");
                        LOOK(scanny.nextInt());
                        System.out.println("");
                        continueTask();
                    } while (continueTask);
                }
            }
        } while (continueTask);

    }

    public static int inputProcesses() {
        int processes = 0;
        do {
            System.out.print("\nENTER NUMBER OF PROCESSES: ");
            processes = scanny.nextInt();
        } while (processes > 9 || processes < 2);
        return processes;
    }

    //PRINTING THE CHOSEN TASK
    public static void printTask(String task) {
        System.out.println("\n=-=-=-=-= " + task + " =-=-=-=-=");
    }

    //
    public static int[] inputBT(int processes) {
        int[] BT = new int[processes];
        System.out.println("");
        for (int x = 0; x < processes; x++) {
            System.out.print("ENTER BURST TIME [" + (x + 1) + "]: ");
            BT[x] = scanny.nextInt();
        }
        return BT;
    }

    public static int[] inputAT(int processes) {
        int[] AT = new int[processes];
        System.out.println("");
        for (int x = 0; x < processes; x++) {
            System.out.print("ENTER ARRIVAL TIME [" + (x + 1) + "]: ");
            AT[x] = scanny.nextInt();
        }
        return AT;
    }

    public static int[] inputPrio(int processes) {
        int[] prio = new int[processes];
        System.out.println("");
        for (int x = 0; x < processes; x++) {
            System.out.print("ENTER PRIORITY [" + (x + 1) + "]: ");
            prio[x] = scanny.nextInt();
        }
        return prio;
    }

    //CONTINUE TASK
    public static void continueTask() {
        System.out.print("\nINPUT AGAIN? ENTER 'Y' OR 'N': ");
        scanny.nextLine();
        String yesNo = scanny.nextLine();
        if (yesNo.equalsIgnoreCase("Y")) {
            continueTask = true;
        } else {
            continueTask = false;
        }
    }

    public static void SJF(int processes) {
        int[] AT = inputAT(processes);
        int[] BT = inputBT(processes);
        Process proc[] = new Process[processes];
        for (int x = 1; x <= processes; x++) {
            proc[x - 1] = new Process(x, BT[x - 1], AT[x - 1]);
        }

        //findavgTime(proc, proc.length);
        SRTF.run(AT, BT, processes);
    }

    public static void PreemptivePriority(int processes) {
        int[] AT = inputAT(processes);
        int[] BT = inputBT(processes);
        int[] prio = inputPrio(processes);
        System.out.println("");
        PreemptivePriority.priorityP(AT, BT, prio, processes);

    }

    public static void FCFS(int processes) {
        int[] AT = inputAT(processes);
        int[] BT = inputBT(processes);
        System.out.println("");
        FCFS.main(processes, AT, BT);
    }

    public static void SJF_NPE(int processes) {
        int[] AT = inputAT(processes);
        int[] BT = inputBT(processes);
        System.out.println("");
        SJF_NPE.main(processes, AT, BT);
    }

    //DISK SCHEDULING
    public static int getSize() {
        System.out.print("INPUT TRACK SIZE: ");
        int size = scanny.nextInt();
        return size;
    }

    public static int getReqs() {
        System.out.print("INPUT NUMBER OF REQUESTS(MAX 10): ");
        int reqs = scanny.nextInt();
        return reqs;
    }

    public static String getDirection() {
        do {
            System.out.print("INPUT DIRECTION [1] LEFT, [2] RIGHT: ");
            int chosen = scanny.nextInt();

            String direction = "";
            switch (chosen) {
                case 1:
                    return "left";
                case 2:
                    return "right";
                default:
                    System.out.println("INVALID INPUT");
            }
        } while (true);
    }

    public static void FCFS_2(int head) {
        int size = getSize();
        int reqs = getReqs();

        System.out.println("");
        int[] arr = new int[reqs];
        for (int x = 0; x < reqs; x++) {
            System.out.print("LOCATION [" + (x + 1) + "]: ");
            arr[x] = scanny.nextInt();
        }

        FCFS_DISK.myMain(arr, head, reqs);
    }

    public static void SSTF(int head) {
        int size = getSize();
        int reqs = getReqs();

        System.out.println("");
        int[] arr = new int[reqs];
        for (int x = 0; x < reqs; x++) {
            System.out.print("LOCATION [" + (x + 1) + "]: ");
            arr[x] = scanny.nextInt();
        }

        SSTF.main(arr, head, reqs);
    }

    public static void SCAN(int head) {
        int size = getSize();
        int reqs = getReqs();
        String direction = getDirection();

        System.out.println("");
        int[] arr = new int[reqs];
        for (int x = 0; x < reqs; x++) {
            System.out.print("LOCATION [" + (x + 1) + "]: ");
            arr[x] = scanny.nextInt();
        }

        SCAN.main(arr, head, size, direction);
    }

    public static void LOOK(int head) {
        int size = getSize();
        int reqs = getReqs();
        String direction = getDirection();

        System.out.println("");
        int[] arr = new int[reqs];
        for (int x = 0; x < reqs; x++) {
            System.out.print("LOCATION [" + (x + 1) + "]: ");
            arr[x] = scanny.nextInt();
        }

        try {
            LOOK.main(arr, head, size, direction);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

 
}
