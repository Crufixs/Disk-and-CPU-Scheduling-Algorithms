package os_project;

// Java program for implementation of
// SSTF disk scheduling
class node {
/**
 *
 * @author angelo
 */
    // represent difference between
    // head position and track number
    int distance = 0;

    // true if track has been accessed
    boolean accessed = false;
}

public class SSTF {

    // Calculates difference of each
    // track number with the head position
    public static void calculateDifference(int queue[],
            int head, node diff[]) {
        for (int i = 0; i < diff.length; i++) {
            diff[i].distance = Math.abs(queue[i] - head);
        }
    }

    // find unaccessed track
    // which is at minimum distance from head
    public static int findMin(node diff[]) {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {

                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void shortestSeekTimeFirst(int request[], int head, int size) {
        
        int truHead = head;
        if (request.length == 0) {
            return;
        }

        // create array of objects of class node
        node diff[] = new node[request.length];

        // initialize array
        for (int i = 0; i < diff.length; i++) {
            diff[i] = new node();
        }

        // count total number of seek operation
        int seek_count = 0;

        // stores sequence in which disk access is done
        int[] seek_sequence = new int[request.length + 1];

        for (int i = 0; i < request.length; i++) {

            seek_sequence[i] = head;
            calculateDifference(request, head, diff);

            int index = findMin(diff);

            diff[index].accessed = true;

            // increase the total count
            seek_count += diff[index].distance;

            // accessed track is now new head
            head = request[index];
        }

        // for last accessed track
        seek_sequence[seek_sequence.length - 1] = head;

        System.out.println("\nTOTAL HEAD MOVEMENT = "
                + seek_count);
        System.out.println("AVE. SEEK TIME = " + (Double.valueOf(seek_count) / Double.valueOf(size)));
        System.out.print("SEEK SEQUENCE : ");

        // print the sequence
        for (int i = 0; i < seek_sequence.length; i++) {
            System.out.print(seek_sequence[i] + " -> ");
        }
        System.out.print("END");
    }

    public static void main(int arr[], int head, int size) {
        shortestSeekTimeFirst(arr, head, size);
    }
}
