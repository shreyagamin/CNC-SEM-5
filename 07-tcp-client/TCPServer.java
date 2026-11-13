
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Server is listening for incoming connections...");

        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted connection from " + client.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));

            OutputStream out = client.getOutputStream();

            String name = in.readLine();
            System.out.println("Received file name : " + name);

            File file = new File(name);

            if (file.exists() && file.isFile()) {
                out.write(Files.readAllBytes(file.toPath()));
            } else {
                out.write("File not found".getBytes());
            }

            out.flush();
            client.close();
        }
    }
}
