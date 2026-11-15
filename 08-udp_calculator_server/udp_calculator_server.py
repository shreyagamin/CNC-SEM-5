
import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(("127.0.0.1", 20001))

print("UDP server listening")

while True:
    data, addr = s.recvfrom(1024)
    expr = data.decode().replace(" ", "")

    op = -1
    for i in range(1, len(expr)):
        if expr[i] in "+-*/%":
            op = i
            break

    try:
        if op == -1:
            result = "Invalid expression"
        else:
            a = float(expr[:op])
            b = float(expr[op+1:])
            operator = expr[op]

            if operator == '+': result = a + b
            elif operator == '-': result = a - b
            elif operator == '*': result = a * b
            elif operator == '/': result = a / b if b != 0 else "Division by zero"
            else: result = a % b if b != 0 else "Division by zero"

    except (ValueError, ZeroDivisionError):
        result = "Invalid expression"

    print("Equation:", expr, "Result:", result)
    s.sendto(str(result).encode(), addr)
