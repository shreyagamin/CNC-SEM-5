
import java.util.*;

public class CRC {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Divisor polynomial (4-bit generator polynomial)
        int[] div = {1, 0, 0, 1};

        // Read total number of data bits
        System.out.print("Enter the number of bits : ");
        int n = sc.nextInt();

        // Read each bit into data array
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter bit " + (i + 1) + " : ");
            data[i] = sc.nextInt();
        }

        // Get length of divisor
        int m = div.length;

        // Validate data length
        if (n < m) {
            System.out.println("Invalid data");
        }
        else {

            // Create working copy and append (m-1) zeros
            int[] cdata = new int[n + m - 1];

            for (int i = 0; i < n; i++) {
                cdata[i] = data[i];
            }

            System.out.println("Initial codeword : " +
                    Arrays.toString(cdata));

            // Perform Modulo-2 Division at sender side
            for (int i = 0; i < n; i++) {

                if (cdata[i] == 1) {

                    for (int j = 0; j < m; j++) {
                        cdata[i + j] = cdata[i + j] ^ div[j];
                    }
                }
            }

            // Extract CRC remainder bits
            int[] crc = new int[m - 1];

            for (int i = 0; i < m - 1; i++) {
                crc[i] = cdata[n + i];
            }

            System.out.println("CRC bits : " +
                    Arrays.toString(crc));

            // Prepare transmitted codeword
            int[] tx_code = new int[n + m - 1];

            for (int i = 0; i < n; i++) {
                tx_code[i] = data[i];
            }

            for (int i = 0; i < m - 1; i++) {
                tx_code[n + i] = crc[i];
            }

            System.out.println("Codeword to be transmitted : " +
                    Arrays.toString(tx_code));

            // Simulate transmission channel
            System.out.print("Introduce error: Y -> yes, N -> no : ");
            String err = sc.next().trim().toLowerCase();

            if (err.equals("y")) {

                System.out.print("Enter the position : ");
                int pos = sc.nextInt();

                // Flip bit (1-indexed position)
                tx_code[pos - 1] = 1 - tx_code[pos - 1];
            }

            System.out.println("Received data : " +
                    Arrays.toString(tx_code));

            // Receiver side Modulo-2 Division
            int[] rdata = tx_code.clone();

            for (int i = 0; i < n; i++) {

                if (rdata[i] == 1) {

                    for (int j = 0; j < m; j++) {
                        rdata[i + j] = rdata[i + j] ^ div[j];
                    }
                }
            }

            // Extract receiver remainder
            int[] rem = new int[m - 1];

            for (int i = 0; i < m - 1; i++) {
                rem[i] = rdata[n + i];
            }

            System.out.println("Remainder : " +
                    Arrays.toString(rem));

            // Verify if remainder contains non-zero bits
            boolean error = false;

            for (int bit : rem) {
                if (bit != 0) {
                    error = true;
                    break;
                }
            }

            if (error) {
                System.out.println("Error detected");
            }
            else {
                System.out.println("No Error detected");
            }
        }

        sc.close();
    }
}
