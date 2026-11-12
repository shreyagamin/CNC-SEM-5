import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             InputStream in = clientSocket.getInputStream();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter the file name : ");
            String fileName = sc.nextLine();
            out.println(fileName);

            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);

            if (bytesRead != -1) {
                String response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                if (response.equals("File not found")) {
                    System.out.println("File not found on the server.");
                } else {
                    System.out.println("File Contents :\n" + response);
                    try (FileWriter fileWriter = new FileWriter("received_" + fileName)) {
                        fileWriter.write(response);
                    }
                    System.out.println("File received and saved as 'received_" + fileName + "'");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
OUTPUT:
Enter the file name : Test.txt
File Contents :
Welcome to Computer Network World
Socket Programming.
File received and saved as 'received_Test.txt'
*/
