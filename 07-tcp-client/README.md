
# TCP Socket Programming – README

## 1. Required files

### Python
- tcp_server.py
- tcp_client.py

### Java
- TCPServer.java
- TCPClient.java

## 2. Create a sample file

Create a file named `Test.txt` in the same folder as the server program.

Contents of Test.txt:

```text
Welcome to Computer Network World
Socket Programming.
```

---

# PYTHON IMPLEMENTATION

## 3. Run Python Server

Open Terminal 1.

```bash
python3 tcp_server.py
```

Server output:

```text
Server is listening for incoming connections...
Accepted connection from ('127.0.0.1', 54321)
Received file name : Test.txt
```

The client address and port number may vary.

## 4. Run Python Client

Open Terminal 2.

```bash
python3 tcp_client.py
```

### Output 1: File found

```text
Enter the file name : Test.txt
File Contents :
 Welcome to Computer Network World
Socket Programming.

File received and saved as 'received_Test.txt'
```

The received file is saved as:

`received_Test.txt`

### Output 2: File not found

```text
Enter the file name : ABC.txt
File not found on the server.
```

---

# JAVA IMPLEMENTATION

## 5. Compile Java programs

Open a terminal in the folder containing the Java files.

```bash
javac TCPServer.java
javac TCPClient.java
```

## 6. Run Java Server

Open Terminal 1.

```bash
java TCPServer
```

Server output:

```text
Server is listening for incoming connections...
Accepted connection from /127.0.0.1:54321
Received file name : Test.txt
```

The client address and port number may vary.

## 7. Run Java Client

Open Terminal 2.

```bash
java TCPClient
```

### Output 1: File found

```text
Enter the file name : Test.txt
File Contents :
Welcome to Computer Network World
Socket Programming.

File received and saved as 'received_Test.txt'
```

The received file is saved as:

`received_Test.txt`

### Output 2: File not found

```text
Enter the file name : ABC.txt
File not found on the server.
```

---

## 8. Important Commands

### Check Python version

```bash
python3 --version
```

### Check Java version

```bash
java -version
javac -version
```

### Install Java if required

```bash
sudo apt update
sudo apt install default-jdk
```

### Stop the server

Press:

`Ctrl + C`

## 9. Important Points

- Server and client must run in separate terminals.
- Both must use the same port number: 8888.
- Run the server before running the client.
- Keep Test.txt in the server's working directory.
- Python files are run using python3.
- Java files must be compiled before execution.
- Do not run the Python and Java servers simultaneously because both use port 8888.

## 10. Quick Revision

Python:
```bash
python3 tcp_server.py
python3 tcp_client.py
```

Java:
```bash
javac TCPServer.java
javac TCPClient.java
java TCPServer
java TCPClient
```

Flow:

`Connect → Request Filename → Check File → Transfer → Save`
