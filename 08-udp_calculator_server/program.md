
# UDP Calculator – Exam Quick Notes

## 1. Aim

Perform arithmetic operations between two numbers using UDP socket programming.

## 2. Remember the flow

`Enter Expression → Create UDP Socket → Send Expression → Server Receives Expression → Separate Numbers and Operator → Calculate Result → Send Result → Display Result`

## 3. Key code logic

### Client

- `socket(AF_INET, SOCK_DGRAM)`: Creates a UDP socket.
- `input()`: Reads the mathematical expression.
- `sendto()`: Sends expression to the server.
- `recvfrom()`: Receives the calculated result.
- `decode()`: Converts received bytes into a string.

### Server

- `socket()`: Creates UDP socket.
- `bind()`: Assigns IP address and port number.
- `recvfrom()`: Receives expression and client address.
- `replace()`: Removes spaces from the expression.
- Find the operator: `+`, `-`, `*`, `/`, `%`.
- Convert both numbers using `float()`.
- Use `if-elif` / `switch` to perform the operation.
- Check whether the second number is zero for division and modulo.
- `sendto()`: Sends the result back to the client.

## 4. Memory tricks

- **Client:** Create → Enter → Send → Receive → Display.
- **Server:** Create → Bind → Receive → Calculate → Send.
- `SOCK_DGRAM` = UDP socket.
- `sendto()` = Send data.
- `recvfrom()` = Receive data.
- Port number = `20001`.
- Five operators: `+ - * / %`.

## 5. Tiny dry run

Input expression: `10 + 5`

- Client sends: `10 + 5`.
- Server separates: `10`, `+`, `5`.
- Server calculates: `10 + 5 = 15`.
- Server sends: `15.0`.
- Client displays: `10 + 5 = 15.0`.

If the expression is `10 / 0`, the server returns:

`Division by zero`

## 6. One-line recall

"Client sends an expression, server identifies the operator, calculates the result, and sends it back."
