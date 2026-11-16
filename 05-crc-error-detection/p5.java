
import java.util.*;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int div[] = {1, 0, 0, 1};
        int m = div.length;

        System.out.print("Enter number of bits: ");
        int n = sc.nextInt();

        int data[] = new int[n];

        System.out.println("Enter data bits:");
        for (int i = 0; i < n; i++)
            data[i] = sc.nextInt();

        if (n < m) {
            System.out.println("Invalid data");
        } else {
            int code[] = new int[n + m - 1];

            // Copy data and append zeros
            for (int i = 0; i < n; i++)
                code[i] = data[i];

            // Sender: CRC division
            for (int i = 0; i < n; i++)
                if (code[i] == 1)
                    for (int j = 0; j < m; j++)
                        code[i + j] ^= div[j];

            // Add CRC to original data
            for (int i = 0; i < n; i++)
                code[i] = data[i];

            System.out.println("CRC: " +
                Arrays.toString(Arrays.copyOfRange(code, n, n + m - 1)));

            System.out.println("Transmitted codeword: " +
                Arrays.toString(code));

            // Introduce error
            System.out.print("Introduce error (Y/N): ");
            if (sc.next().equalsIgnoreCase("Y")) {
                System.out.print("Enter position: ");
                int pos = sc.nextInt();

                if (pos >= 1 && pos <= code.length)
                    code[pos - 1] ^= 1;
                else
                    System.out.println("Invalid position");
            }

            System.out.println("Received data: " +
                Arrays.toString(code));

            // Receiver: CRC division
            for (int i = 0; i < n; i++)
                if (code[i] == 1)
                    for (int j = 0; j < m; j++)
                        code[i + j] ^= div[j];

            // Check remainder
            boolean error = false;

            for (int i = n; i < code.length; i++)
                if (code[i] != 0)
                    error = true;

            System.out.println("Remainder: " +
                Arrays.toString(Arrays.copyOfRange(code, n, code.length)));

            System.out.println(error ? "Error detected" : "No Error detected");
        }

        sc.close();
    }
}
