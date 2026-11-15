
import socket
import os

s = socket.socket()
s.bind(('localhost', 8888))
s.listen(1)

print("Server is listening...")

while True:
    c, addr = s.accept()
    name = c.recv(1024).decode()

    if os.path.isfile(name):
        with open(name, 'rb') as f:
            c.sendall(f.read())
    else:
        c.sendall(b'File not found')

    c.close()
