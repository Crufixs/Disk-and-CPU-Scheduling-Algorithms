package os_project;

// Java program to demonstrate
// LOOK Disk Scheduling algorithm
import java.util.*;

class LOOK {

    static int size = 0;
    static int disk_size = 0;

    public static void LOOKK(int arr[], int head,
            String direction) {
        int seek_count = 0;
        int distance, cur_track;
        int truHead = head;

        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // Appending values which are
        // currently at left and right
        // direction from the head.
        for (int i = 0; i < size; i++) {
            if (arr[i] < head) {
                left.add(arr[i]);
            }
            if (arr[i] > head) {
                right.add(arr[i]);
            }
        }

        // Sorting left and right vectors
        // for servicing tracks in the
        // correct sequence.
        Collections.sort(left);
        Collections.sort(right);

        // Run the while loop two times.
        // one by one scanning right
        // and left side of the head
        int run = 2;
        while (run-- > 0) {
            if (direction == "left") {
                for (int i = left.size() - 1;
                        i >= 0; i--) {
                    cur_track = left.get(i);

                    // Appending current track to
                    // seek sequence
                    seek_sequence.add(cur_track);

                    // Calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // Increase the total count
                    seek_count += distance;

                    // Accessed track is now the new head
                    head = cur_track;
                }

                // Reversing the direction
                direction = "right";
            } else if (direction == "right") {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);

                    // Appending current track to
                    // seek sequence
                    seek_sequence.add(cur_track);

                    // Calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // Increase the total count
                    seek_count += distance;

                    // Accessed track is now new head
                    head = cur_track;
                }

                // Reversing the direction
                direction = "left";
            }
        }

        System.out.println("\nTOTAL HEAD MOVEMENT = " + seek_count);

        System.out.println("AVE. SEEK TIME = " + (Double.valueOf(seek_count) / Double.valueOf(arr.length)));

        System.out.print("SEEK SEQUENCE: " + truHead + " -> ");

        for (int i = 0; i < seek_sequence.size(); i++) {
            System.out.print(seek_sequence.get(i) + " -> ");
        }
        System.out.print("END");
    }

// Driver code
    public static void main(int arr[], int head, int sizee, String direction) throws Exception {

        // Request array
        //int arr[] = {176, 79, 34, 60,92, 11, 41, 114};
        //int head = 50;
        size = arr.length;
        disk_size = sizee;
        LOOKK(arr, head, direction);
    }
}

