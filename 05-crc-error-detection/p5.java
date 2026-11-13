
import java.util.*;

public class CRC {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] div = {1, 0, 0, 1};
        int m = div.length;

        System.out.print("Enter number of bits: ");
        int n = sc.nextInt();

        int[] data = new int[n];

        System.out.println("Enter data bits:");
        for (int i = 0; i < n; i++)
            data[i] = sc.nextInt();

        if (n < m) {
            System.out.println("Invalid data");
        }
        else {

            // Append zeros
            int[] temp = new int[n + m - 1];

            for (int i = 0; i < n; i++)
                temp[i] = data[i];

            // Sender: XOR division
            for (int i = 0; i < n; i++) {
                if (temp[i] == 1) {
                    for (int j = 0; j < m; j++)
                        temp[i + j] ^= div[j];
                }
            }

            // Get CRC bits
            int[] crc = new int[m - 1];

            for (int i = 0; i < m - 1; i++)
                crc[i] = temp[n + i];

            System.out.println("CRC bits: " + Arrays.toString(crc));

            // Create transmitted codeword
            int[] code = new int[n + m - 1];

            for (int i = 0; i < n; i++)
                code[i] = data[i];

            for (int i = 0; i < m - 1; i++)
                code[n + i] = crc[i];

            System.out.println("Transmitted codeword: " +
                    Arrays.toString(code));

            // Introduce error
            System.out.print("Introduce error (Y/N): ");
            String choice = sc.next();

            if (choice.equalsIgnoreCase("Y")) {
                System.out.print("Enter position: ");
                int pos = sc.nextInt();

                code[pos - 1] = 1 - code[pos - 1];
            }

            System.out.println("Received data: " +
                    Arrays.toString(code));

            // Receiver: XOR division
            for (int i = 0; i < n; i++) {
                if (code[i] == 1) {
                    for (int j = 0; j < m; j++)
                        code[i + j] ^= div[j];
                }
            }

            // Check remainder
            boolean error = false;

            for (int i = n; i < n + m - 1; i++) {
                if (code[i] != 0)
                    error = true;
            }

            System.out.println("Remainder: " +
                    Arrays.toString(Arrays.copyOfRange(code, n, n + m - 1)));

            if (error)
                System.out.println("Error detected");
            else
                System.out.println("No Error detected");
        }

        sc.close();
    }
}
