# Disk-and-CPU-Scheduling-Algorithms
This repository is a project I did with my groupmates for our course Operating Systems (ICS26012). The system is designed to be a simulation of a calculator for different CPU Scheduling and Disk Scheduling algorithms. For CPU Scheduling, we implemented two preemptive algorithms, namely: 
*   Shortest Job First (SJF)
*   Priority (P-Prio)
and two non-preemptive algorithms, namely:
*   First Come First Serve (FCFS)
*   Shortest Job First (SJF)
For Disk Scheduling, four algorithms were implemented:
*   First Come First Serve (FCFS)
*   Shortest Seek Time First (SSTF)
*   SCAN
*   LOOK
## Implementation
The project was implemented using the java programming language and coded through the Netbeans 8.2 IDE. 

## Program Flow
*   The program first asks the user which type of scheduling algorithm should the user try. If the user chose CPU scheduling, then they are prompted whether they want to choose preemptive or non-preemptive algorithms. 

<img src="OS_Images/CPU_SCHEDULING.png" width="300" title="Program Flow1">

*   After choosing, the users will then be prompted regarding the CPU scheduling algorithm that they will use. 

<img src="OS_Images/CPU_SCHEDULING_CHOICE.png" width="300" title="Program Flow3">

*   If the user chose Disk Scheduling, they will immediately be prompted regarding the Disk scheduling algorithm that they will use. 

<img src="OS_Images/DISK_SCHEDULING_CHOICE.png" width="300" title="Program Flow2">

## Sample Run
*   CPU Scheduling - Priority (Preemptive)

<img src="OS_Images/PRIORITY_SCHEDULING.png" width="400" title="Priority (Preemptive)">


*   CPU Scheduling - Shortest Job First (Non-preemptive)

<img src="/OS_Images/SJF.png" width="400" title="Shortest Job First (Non-preemptive)">


*   Disk Scheduling - Scan (left-first)

<img src="OS_Images/SCAN.png" width="400" title="Scan (left-first)">


*   Disk Scheduling - Shortest Seek Time First

<img src="OS_Images/SSTF.png" width="400" title="Shortest Seek Time First">
