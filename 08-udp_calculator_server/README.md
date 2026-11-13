
# UDP Calculator – README

## 1. Required Files

### Python
- udp_calculator_server.py
- udp_calculator_client.py

### Java
- UDPCalculatorServer.java
- UDPCalculatorClient.java

---

# PYTHON IMPLEMENTATION

## 2. Run Python Server

Open Terminal 1:

```bash
python3 udp_calculator_server.py
```

Output:

```text
UDP server up and listening
```

## 3. Run Python Client

Open Terminal 2:

```bash
python3 udp_calculator_client.py
```

## 4. Sample Outputs – Python

### Output 1: Addition

Client:

```text
Enter an expression : 10 + 5
10 + 5 = 15.0
```

Server:

```text
UDP server up and listening
Equation from Client : ('127.0.0.1', 51432) : 10+5
Result to Client : 15.0
```

### Output 2: Subtraction

```text
Enter an expression : 10 - 5
10 - 5 = 5.0
```

### Output 3: Multiplication

```text
Enter an expression : 10 * 5
10 * 5 = 50.0
```

### Output 4: Division

```text
Enter an expression : 10 / 5
10 / 5 = 2.0
```

### Output 5: Modulo

```text
Enter an expression : 10 % 3
10 % 3 = 1.0
```

### Output 6: Division by zero

```text
Enter an expression : 10 / 0
10 / 0 = Division by zero
```

### Output 7: Modulo by zero

```text
Enter an expression : 10 % 0
10 % 0 = Division by zero
```

### Output 8: Invalid expression

```text
Enter an expression : hello
hello = Invalid expression
```

---

# JAVA IMPLEMENTATION

## 5. Compile Java Programs

```bash
javac UDPCalculatorServer.java
javac UDPCalculatorClient.java
```

## 6. Run Java Server

Open Terminal 1:

```bash
java UDPCalculatorServer
```

Output:

```text
UDP server up and listening
```

## 7. Run Java Client

Open Terminal 2:

```bash
java UDPCalculatorClient
```

## 8. Sample Outputs – Java

### Output 1: Addition

Client:

```text
Enter an expression : 10 + 5
10 + 5 = 15.0
```

Server:

```text
UDP server up and listening
Equation from Client : /127.0.0.1 : 10+5
Result to Client : 15.0
```

### Output 2: Subtraction

```text
Enter an expression : 10 - 5
10 - 5 = 5.0
```

### Output 3: Multiplication

```text
Enter an expression : 10 * 5
10 * 5 = 50.0
```

### Output 4: Division

```text
Enter an expression : 10 / 5
10 / 5 = 2.0
```

### Output 5: Modulo

```text
Enter an expression : 10 % 3
10 % 3 = 1.0
```

### Output 6: Division by zero

```text
Enter an expression : 10 / 0
10 / 0 = Division by zero
```

### Output 7: Modulo by zero

```text
Enter an expression : 10 % 0
10 % 0 = Division by zero
```

### Output 8: Invalid expression

```text
Enter an expression : hello
hello = Invalid expression
```

---

## 9. Important Commands

Check Python:

```bash
python3 --version
```

Check Java:

```bash
java -version
javac -version
```

Install Java if required:

```bash
sudo apt update
sudo apt install default-jdk
```

## 10. Important Points

- UDP uses `SOCK_DGRAM`.
- IP address = `127.0.0.1`.
- Port number = `20001`.
- Server must run before the client.
- Run server and client in separate terminals.
- `sendto()` sends data along with the destination address.
- `recvfrom()` receives data along with the sender's address.
- UDP is connectionless; no connection establishment is required.
- Both division and modulo by zero are handled.

## 11. Quick Revision

Python:

```bash
python3 udp_calculator_server.py
python3 udp_calculator_client.py
```

Java:

```bash
javac UDPCalculatorServer.java
javac UDPCalculatorClient.java
java UDPCalculatorServer
java UDPCalculatorClient
```

Flow:

`Enter → Send → Receive → Calculate → Reply → Display`
