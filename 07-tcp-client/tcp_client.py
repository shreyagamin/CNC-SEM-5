
import socket

c = socket.socket()
c.connect(('localhost', 8888))

name = input("Enter file name: ")
c.send(name.encode())

data = b''
while True:
    part = c.recv(1024)
    if not part:
        break
    data += part

if data == b'File not found':
    print("File not found")
else:
    print("File Contents:\n", data.decode())
    with open("received_" + name, 'wb') as f:
        f.write(data)
    print("File received successfully")

c.close()
