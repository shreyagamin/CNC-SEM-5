
import java.net.*;

public class UDPCalculatorServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(20001);
        byte[] buffer = new byte[1024];

        System.out.println("UDP server up and listening");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);

            String expr = new String(packet.getData(), 0, packet.getLength())
                    .replace(" ", "");

            int op = -1;
            for (int i = 1; i < expr.length(); i++) {
                if ("+-*/%".indexOf(expr.charAt(i)) >= 0) {
                    op = i;
                    break;
                }
            }

            String result;

            try {
                if (op == -1) {
                    result = "Invalid expression";
                } else {
                    double a = Double.parseDouble(expr.substring(0, op));
                    double b = Double.parseDouble(expr.substring(op + 1));
                    char ch = expr.charAt(op);

                    switch (ch) {
                        case '+': result = String.valueOf(a + b); break;
                        case '-': result = String.valueOf(a - b); break;
                        case '*': result = String.valueOf(a * b); break;
                        case '/': result = b == 0 ? "Division by zero" : String.valueOf(a / b); break;
                        case '%': result = b == 0 ? "Division by zero" : String.valueOf(a % b); break;
                        default: result = "Invalid expression";
                    }
                }
            } catch (NumberFormatException e) {
                result = "Invalid expression";
            }

            System.out.println("Equation from Client : " +
                    packet.getAddress() + " : " + expr);
            System.out.println("Result to Client : " + result);

            byte[] sendData = result.getBytes();
            DatagramPacket reply = new DatagramPacket(
                    sendData, sendData.length,
                    packet.getAddress(), packet.getPort());

            server.send(reply);
        }
    }
}
