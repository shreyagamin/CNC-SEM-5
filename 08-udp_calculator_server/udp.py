import socket
import re

localIP = "127.0.0.1"
localPort = 20001
bufferSize = 1024

UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
UDPServerSocket.bind((localIP, localPort))
print("UDP server up and listening")

while True:
    bytesAddressPair = UDPServerSocket.recvfrom(bufferSize)
    expression = bytesAddressPair[0].decode()
    client_address = bytesAddressPair[1]

    num1, num2 = re.split(r'\+|-|\*|/|%', expression.replace(' ', ''))
    num1 = float(num1)
    num2 = float(num2)
    result = 0

    if '+' in expression:
        result = num1 + num2
    elif '-' in expression:
        result = num1 - num2
    elif '*' in expression:
        result = num1 * num2
    elif '/' in expression:
        if num2 == 0:
            result = 'Division by zero'
        else:
            result = num1 / num2
    elif '%' in expression:
        result = num1 % num2
    else:
        result = "Invalid expression"

    print("Equation from Client :", client_address, ":", expression)
    print("Result to Client :", result)
    UDPServerSocket.sendto(str(result).encode(), client_address)


"""
OUTPUT:
UDP server up and listening
Equation from Client : ('127.0.0.1', 51432) : 10 + 5
Result to Client : 15.0
"""
```[span_1](start_span)[span_1](end_span)[span_2](start_span)[span_2](end_span)

#### 2. `udp_calculator_client.py`
```python
import socket

expr = input("Enter an expression : ")
bytesToSend = str.encode(expr)
serverAddressPort = ("127.0.0.1", 20001)
bufferSize = 1024

UDPClientSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
UDPClientSocket.sendto(bytesToSend, serverAddressPort)
msgFromServer = UDPClientSocket.recvfrom(bufferSize)
print(expr, "=", msgFromServer[0].decode())


"""
OUTPUT:
Enter an expression : 10 + 5
10 + 5 = 15.0
"""
```[span_3](start_span)[span_3](end_span)

---

### Java Implementation

#### 1. `UDPCalculatorServer.java`
```java
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPCalculatorServer {
    public static void main(String[] args) {
        int localPort = 20001;
        byte[] receiveBuffer = new byte[1024];

        try (DatagramSocket serverSocket = new DatagramSocket(localPort)) {
            System.out.println("UDP server up and listening");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String expression = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String cleanExpr = expression.replace(" ", "");
                String[] parts = cleanExpr.split("\\+|-|\\*|/|%");
                String result;

                if (parts.length != 2) {
                    result = "Invalid expression";
                } else {
                    try {
                        double num1 = Double.parseDouble(parts[0]);
                        double num2 = Double.parseDouble(parts[1]);

                        if (cleanExpr.contains("+")) {
                            result = String.valueOf(num1 + num2);
                        } else if (cleanExpr.contains("-")) {
                            result = String.valueOf(num1 - num2);
                        } else if (cleanExpr.contains("*")) {
                            result = String.valueOf(num1 * num2);
                        } else if (cleanExpr.contains("/")) {
                            result = (num2 == 0) ? "Division by zero" : String.valueOf(num1 / num2);
                        } else if (cleanExpr.contains("%")) {
                            result = (num2 == 0) ? "Division by zero" : String.valueOf(num1 % num2);
                        } else {
                            result = "Invalid expression";
                        }
                    } catch (NumberFormatException e) {
                        result = "Invalid expression";
                    }
                }

                System.out.println("Equation from Client : (" + clientAddress.getHostAddress() + ", " + clientPort + ") : " + expression);
                System.out.println("Result to Client : " + result);

                byte[] sendBuffer = result.getBytes(StandardCharsets.UTF_8);
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
OUTPUT:
UDP server up and listening
Equation from Client : (127.0.0.1, 51432) : 10 + 5
Result to Client : 15.0
*/
