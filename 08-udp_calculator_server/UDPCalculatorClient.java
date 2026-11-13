
import java.net.*;
import java.util.Scanner;

public class UDPCalculatorClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an expression : ");
        String expr = sc.nextLine();

        byte[] sendData = expr.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");

        DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length, address, 20001);
        client.send(sendPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        client.receive(receivePacket);

        String result = new String(
                receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println(expr + " = " + result);

        client.close();
        sc.close();
    }
}
