
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {

        // Create server on port 8888
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Server waiting...");

        while (true) {

            // Accept client connection
            Socket client = server.accept();

            // Read file name from client
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));

            String name = in.readLine();

            // Get output stream to send data
            OutputStream out = client.getOutputStream();

            // Check whether file exists
            File file = new File(name);

            if (file.exists() && file.isFile()) {

                // Read file and send contents
                byte[] data = Files.readAllBytes(file.toPath());
                out.write(data);

                System.out.println("File sent successfully");

            } else {

                // Send error if file is missing
                out.write("File not found".getBytes());
                System.out.println("File not found");
            }

            // Close client connection
            out.flush();
            client.close();
        }
    }
}
