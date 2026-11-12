import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPCalculatorClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int serverPort = 20001;

        try (Scanner sc = new Scanner(System.in);
             DatagramSocket clientSocket = new DatagramSocket()) {

            System.out.print("Enter an expression : ");
            String expr = sc.nextLine();

            byte[] bytesToSend = expr.getBytes(StandardCharsets.UTF_8);
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String msgFromServer = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println(expr + " = " + msgFromServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
OUTPUT:
Enter an expression : 10 + 5
10 + 5 = 15.0
*/
