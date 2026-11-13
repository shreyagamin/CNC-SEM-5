
# Socket Programming – Exam Quick Notes

## 1. Aim

Transfer a file from a server to a client using TCP socket programming.

## 2. Remember the flow

`Create Socket → Connect Client → Send File Name → Server Checks File → Send Contents → Client Displays and Saves File`

## 3. Key code logic

### Server

- `socket()` / `ServerSocket()`: Creates the server socket.
- `bind()`: Assigns address and port (8888).
- `listen()`: Waits for client connections.
- `accept()`: Accepts a client connection.
- `recv()` / `readLine()`: Receives the requested filename.
- `exists()`: Checks whether the file is available.
- `sendall()` / `write()`: Sends file contents or error message.
- `close()`: Closes the client connection.

### Client

- `socket()`: Creates a client socket.
- `connect()`: Connects to the server.
- `input()`: Reads the filename.
- `send()` / `println()`: Sends filename to server.
- `recv()` / `readAllBytes()`: Receives file contents.
- `open()` / `Files.write()`: Saves the received file.
- `close()`: Closes the connection.

## 4. Memory tricks

- **Server:** Create → Bind → Listen → Accept.
- **Client:** Create → Connect → Send filename.
- **Server:** Receive filename → Check file → Send response.
- **Client:** Receive → Display → Save.
- Port number = `8888`.
- TCP uses `SOCK_STREAM` in Python.

## 5. Tiny dry run

File name: `Test.txt`

File contents:

`Welcome to Computer Network World`
`Socket Programming.`

Client sends: `Test.txt`

Server checks the file and sends its contents.

Client displays the contents and saves them as:

`received_Test.txt`

If the file does not exist, the client displays:

`File not found on the server.`

## 6. One-line recall

"Client requests a filename, server checks and sends the file, and client receives and saves it."
