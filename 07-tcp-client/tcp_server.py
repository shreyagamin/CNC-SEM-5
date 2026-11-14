
import socket
import os

# Create server
server = socket.socket()
server.bind(('localhost', 8888))
server.listen(1)

print("Server is listening...")

while True:
    # Accept client
    client, addr = server.accept()

    # Receive file name
    name = client.recv(1024).decode()
    print("Received file name:", name)

    # Check if file exists
    if os.path.exists(name) and os.path.isfile(name):

        # Open and send file
        with open(name, 'rb') as file:
            client.sendall(file.read())

    else:
        # Send error message
        client.sendall(b'File not found')

    # Close client
    client.close()
