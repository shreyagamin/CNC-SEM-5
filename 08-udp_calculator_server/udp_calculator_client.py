
import socket

client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

expr = input("Enter an expression : ")

client.sendto(expr.encode(), ("127.0.0.1", 20001))

data, address = client.recvfrom(1024)

print(expr, "=", data.decode())

client.close()
