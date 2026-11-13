
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8888);

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        InputStream in = client.getInputStream();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file name : ");
        String name = sc.nextLine();

        out.println(name);

        byte[] data = in.readAllBytes();
        String response = new String(data, StandardCharsets.UTF_8);

        if (response.equals("File not found")) {
            System.out.println("File not found on the server.");
        } else {
            System.out.println("File Contents :\n" + response);

            Files.write(Paths.get("received_" + name), data);

            System.out.println("File received and saved as 'received_" + name + "'");
        }

        client.close();
        sc.close();
    }
}
