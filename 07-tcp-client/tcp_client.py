Welcome to Computer Network World
Socket Programming.
```[span_2](start_span)[span_2](end_span)

---

### Python Implementation

#### 1. `tcp_server.py`
```python
import socket
import os

server_address = ('localhost', 8888)
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(server_address)
server_socket.listen(1)
print("Server is listening for incoming connections...")

while True:
    client_socket, client_address = server_socket.accept()
    print("Accepted connection from", client_address)
    file_name = client_socket.recv(1024).decode()
    print("Received file name :", file_name)
    
    if os.path.exists(file_name):
        with open(file_name, 'rb') as file:
            file_contents = file.read()
        client_socket.send(file_contents)
    else:
        client_socket.send(b'File not found')
        
    client_socket.close()


"""
OUTPUT:
Server is listening for incoming connections...
Accepted connection from ('127.0.0.1', 54321)
Received file name : Test.txt
"""
```[span_3](start_span)[span_3](end_span)[span_4](start_span)[span_4](end_span)

#### 2. `tcp_client.py`
```python
import socket

server_address = ('localhost', 8888)
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(server_address)

file_name = input("Enter the file name : ")
client_socket.send(file_name.encode())

file_contents = client_socket.recv(1024)
if file_contents == b'File not found':
    print("File not found on the server.")
else:
    print("File Contents :\n", file_contents.decode())
    with open("received_" + file_name, 'w') as file:
        file.write(file_contents.decode())
    print("File received and saved as 'received_" + file_name + "'")

client_socket.close()


"""
OUTPUT:
Enter the file name : Test.txt
File Contents :
 Welcome to Computer Network World
Socket Programming.
File received and saved as 'received_Test.txt'
"""
```[span_5](start_span)[span_5](end_span)[span_6](start_span)[span_6](end_span)

---

### Java Implementation

#### 1. `TCPServer.java`
```java
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening for incoming connections...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     OutputStream out = clientSocket.getOutputStream()) {

                    System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());
                    String fileName = in.readLine();
                    System.out.println("Received file name : " + fileName);

                    File file = new File(fileName);
                    if (file.exists() && !file.isDirectory()) {
                        byte[] fileContents = Files.readAllBytes(file.toPath());
                        out.write(fileContents);
                    } else {
                        out.write("File not found".getBytes());
                    }
                    out.flush();
                } catch (IOException e) {
                    System.out.println("Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
OUTPUT:
Server is listening for incoming connections...
Accepted connection from /127.0.0.1:54321
Received file name : Test.txt
*/
