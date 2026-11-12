

import java.util.*;

public class BitStuffing {

    // SENDER: Performs bit stuffing
    static void sender(Scanner sc) {

        System.out.print("Enter the no of bits: ");
        int n = sc.nextInt();

        int[] data = new int[n];

        System.out.println("Enter " + n + " bits:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        // Flag pattern
        int[] flag = {0, 1, 1, 1, 1, 1, 1, 0};

        ArrayList<Integer> frame = new ArrayList<>();

        // Add starting flag
        for (int bit : flag)
            frame.add(bit);

        int count = 0;

        // Bit stuffing
        for (int bit : data) {

            // Insert 0 after five consecutive 1s
            if (count == 5) {
                frame.add(0);
                count = 0;
            }

            frame.add(bit);

            if (bit == 1)
                count++;
            else
                count = 0;
        }

        // Add ending flag
        for (int bit : flag)
            frame.add(bit);

        System.out.println("Sent Frame: " + frame);

        // Send frame to receiver
        receiver(frame);
    }


    // RECEIVER: Performs bit de-stuffing
    static void receiver(ArrayList<Integer> frame) {

        ArrayList<Integer> data = new ArrayList<>();

        int count = 0;

        // Remove starting and ending flags
        for (int i = 8; i < frame.size() - 8; i++) {

            int bit = frame.get(i);

            if (bit == 1) {
                data.add(bit);
                count++;
            }
            else {
                // Skip stuffed 0 after five 1s
                if (count == 5) {
                    count = 0;
                }
                else {
                    data.add(bit);
                    count = 0;
                }
            }
        }

        System.out.println("Data Received: " + data);
    }


    // MAIN FUNCTION
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        sender(sc);

        sc.close();
    }
}
