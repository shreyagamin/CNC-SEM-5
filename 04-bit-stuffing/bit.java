
import java.util.*;

public class BitStuffing {

    static void sender(Scanner sc) {

        // Read total number of input bits from user
        System.out.print("Enter the no of bits: ");
        int n = sc.nextInt();

        System.out.println("Enter " + n + " bits :");

        int[] data = new int[n];

        // Read input bits one by one
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        // Flag pattern (01111110)
        int[] add = {0, 1, 1, 1, 1, 1, 1, 0};

        ArrayList<Integer> frame = new ArrayList<>();

        // Add starting flag
        for (int bit : add) {
            frame.add(bit);
        }

        int count = 0;

        // Perform Bit Stuffing logic
        for (int i : data) {

            if (count == 5) {
                frame.add(0);  // Insert 0 after 5 consecutive 1's
                count = 0;
            }

            frame.add(i);

            if (i == 1) {
                count++;
            } else {
                count = 0;
            }
        }

        // Add ending flag
        for (int bit : add) {
            frame.add(bit);
        }

        System.out.println("Sent Frame : " + frame);

        // Pass stuffed frame to receiver
        receiver(frame);
    }

    static void receiver(ArrayList<Integer> frame) {

        ArrayList<Integer> data = new ArrayList<>();

        int count = 0;

        // Remove starting and ending flag patterns
        List<Integer> frame_body = frame.subList(8, frame.size() - 8);

        // Perform De-stuffing
        for (int i : frame_body) {

            if (i == 1) {
                data.add(i);
                count++;
            }
            else if (i == 0) {

                if (count == 5) {
                    // Ignore/remove stuffed 0
                    count = 0;
                }
                else {
                    data.add(i);
                    count = 0;
                }
            }
        }

        System.out.println("Data Received is : " + data);
    }

    // Main function
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        sender(sc);

        sc.close();
    }
}
