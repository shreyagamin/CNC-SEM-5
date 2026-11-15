import socket

c = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

expr = input("Enter an expression: ")
c.sendto(expr.encode(), ("127.0.0.1", 20001))

data, addr = c.recvfrom(1024)
print(expr, "=", data.decode())

c.close()
