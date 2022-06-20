
package os_project;

// Java program to demonstrate
// FCFS Disk Scheduling algorithm
class FCFS_DISK
{
/**
 *
 * @author angelo
 */
static void FCFS(int arr[], int head, int size)
{
	int seek_count = 0;
	int distance, cur_track;
        int truHead = head;

	for (int i = 0; i < size; i++)
	{
		cur_track = arr[i];

		// calculate absolute distance
		distance = Math.abs(cur_track - head);

		// increase the total count
		seek_count += distance;

		// accessed track is now new head
		head = cur_track;
	}

	System.out.println("\nTOTAL HEAD MOVEMENT = "+seek_count);
	System.out.println("AVE. SEEK TIME = " + (Double.valueOf(seek_count)/Double.valueOf(arr.length)));
	System.out.print("SEEK SEQUENCE: " + truHead + " -> ");

	for (int i = 0; i < size; i++)
	{
		System.out.print(arr[i]+ " -> ");
	}
        System.out.print("END");
}

//driver code
public static void myMain(int [] arr, int head, int size)
{
	FCFS(arr, head, size);
}
}
  
