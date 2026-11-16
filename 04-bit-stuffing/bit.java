
import java.util.*;

public class BitStuffing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of bits: ");
        int n = sc.nextInt();

        int data[] = new int[n];

        System.out.println("Enter the bits:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        int flag[] = {0, 1, 1, 1, 1, 1, 1, 0};
        ArrayList<Integer> frame = new ArrayList<>();

        // Add starting flag
        for (int bit : flag)
            frame.add(bit);

        // Bit stuffing
        int count = 0;

        for (int bit : data) {
            frame.add(bit);

            if (bit == 1)
                count++;
            else
                count = 0;

            if (count == 5) {
                frame.add(0);
                count = 0;
            }
        }

        // Add ending flag
        for (int bit : flag)
            frame.add(bit);

        System.out.println("Sent Frame: " + frame);

        // Bit de-stuffing
        ArrayList<Integer> received = new ArrayList<>();
        count = 0;

        for (int i = 8; i < frame.size() - 8; i++) {
            int bit = frame.get(i);

            if (bit == 1) {
                received.add(bit);
                count++;
            } else {
                if (count != 5)
                    received.add(bit);

                count = 0;
            }
        }

        System.out.println("Data Received: " + received);

        sc.close();
    }
}
