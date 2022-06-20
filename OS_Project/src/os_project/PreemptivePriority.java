package os_project;

import java.util.*;

//Java implementation for Priority Scheduling with
//Different Arrival Time priority scheduling
import java.util.*;

/// Data Structure
class Priority {

    int processNumber, arrivalTime, burstTime, priorityNumber, startTime, completionTime, turnAroundTime, waitingTime, remainingBurst;
    boolean isCompleted;

    Priority(int processNumber, int arrivalTime, int burstTime, int priorityNumber, int startTime, int completionTime, int turnAroundTime, int waitingTime, int remainingBurst, boolean isCompleted) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.turnAroundTime = turnAroundTime;
        this.waitingTime = waitingTime;
        this.remainingBurst = remainingBurst;
        this.isCompleted = isCompleted;
    }

    Priority(int processNumber, int arrivalTime, int completionTime) {
        this.processNumber = processNumber;
        this.completionTime = completionTime;
        this.arrivalTime = arrivalTime;
    }
}

class PreemptivePriority {

    public static void priorityP(int[] AT, int[] BT, int[] prio, int processes) {
        Scanner input = new Scanner(System.in);
        //Priority Preemptive
        Priority priorityArray[] = new Priority[processes];
        for (int i = 0; i < processes; i++) {
            priorityArray[i] = new Priority(i + 1, AT[i], BT[i], prio[i], 0, 0, 0, 0, BT[i], false);
        }

        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        float averageWaitingTime = 0;
        float averageTurnAroundTime = 0;

        int currentTime = 0;
        int completed = 0;
        //int previous = 0;

        //process
        ArrayList<Priority> seekQ = new ArrayList<>();
        boolean processing = false;
        int prev = -1;
        while (completed != processes) {
            //find the process with the minimum priority time among the processes that are in ready queue at currentTime
            int index = -1;
            int min = 999;
            //if the process is found
            for (int i = 0; i < processes; i++) {
                if ((priorityArray[i].arrivalTime <= currentTime) && priorityArray[i].isCompleted == false) {
                    if (priorityArray[i].priorityNumber < min) {
                        min = priorityArray[i].priorityNumber;
                        index = i;
                    }
                    if (priorityArray[i].priorityNumber == min) {
                        if (priorityArray[i].arrivalTime < priorityArray[index].arrivalTime) {
                            min = priorityArray[i].priorityNumber;
                            index = i;
                        }
                    }
                }
            }
            if (index == -1) {
                currentTime++;
                continue;
            }
            if (index != -1) {
                if (!processing || (processing && prev != index)) {
                    if (!seekQ.isEmpty()) {
                        Priority prevProcess = seekQ.get(seekQ.size() - 1);
                        prevProcess.completionTime++;
                    }

                    seekQ.add(new Priority(index, currentTime, currentTime));
                    processing = true;
                    prev = index;
                }
                if (!(prev != index)) {
                    Priority process = seekQ.get(seekQ.size() - 1);
                    process.completionTime++;
                }

                if (priorityArray[index].remainingBurst == priorityArray[index].burstTime) {
                    priorityArray[index].startTime = currentTime;
                }
            }

            priorityArray[index].remainingBurst -= 1;
            currentTime++;
            if (priorityArray[index].remainingBurst == 0) {
                priorityArray[index].completionTime = currentTime;
                priorityArray[index].turnAroundTime = priorityArray[index].completionTime - priorityArray[index].arrivalTime;
                priorityArray[index].waitingTime = priorityArray[index].turnAroundTime - priorityArray[index].burstTime;

                totalWaitingTime += priorityArray[index].waitingTime;
                totalTurnAroundTime += priorityArray[index].turnAroundTime;

                priorityArray[index].isCompleted = true;
                completed++;
            }
        }
        averageWaitingTime = (float) totalWaitingTime / processes;
        averageTurnAroundTime = (float) totalTurnAroundTime / processes;

        //output
        System.out.println("AT\tBT\tWT\tTAT");
        for (int i = 0; i < priorityArray.length; i++) {
            System.out.println(AT[i] + "\t" + BT[i] + "\t" + priorityArray[i].waitingTime + "\t" + priorityArray[i].turnAroundTime);
        }
        System.out.println("\nAVE. WT = " + averageWaitingTime);
        System.out.println("AVE. TAT = " + averageTurnAroundTime);
        System.out.println("\n=-=-=-=-= GANTT CHART =-=-=-=-=");
        for (int i = 0; i < seekQ.size(); i++) {
            Priority p = seekQ.get(i);
            if (i != seekQ.size() - 1) {
                System.out.printf("%d [P%d] ", p.arrivalTime, p.processNumber);
            } else {
                System.out.printf("%d [P%d] %d ", p.arrivalTime, p.processNumber, p.completionTime);
            }
        }
        System.out.println("");

    }
}
