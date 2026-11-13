
import socket

client = socket.socket()
client.connect(('localhost', 8888))

name = input("Enter the file name : ")
client.send(name.encode())

data = b''
while True:
    part = client.recv(1024)
    if not part:
        break
    data += part

if data == b'File not found':
    print("File not found on the server.")
else:
    print("File Contents :\n", data.decode())

    with open("received_" + name, 'wb') as file:
        file.write(data)

    print("File received and saved as 'received_" + name + "'")

client.close()
