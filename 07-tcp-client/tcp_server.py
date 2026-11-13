
import socket
import os

server = socket.socket()
server.bind(('localhost', 8888))
server.listen(1)

print("Server is listening for incoming connections...")

while True:
    client, addr = server.accept()
    print("Accepted connection from", addr)

    name = client.recv(1024).decode()
    print("Received file name :", name)

    if os.path.exists(name) and os.path.isfile(name):
        with open(name, 'rb') as file:
            client.sendall(file.read())
    else:
        client.sendall(b'File not found')

    client.close()
