
import socket

# Create and connect client
client = socket.socket()
client.connect(('localhost', 8888))

# Enter and send file name
name = input("Enter the file name: ")
client.send(name.encode())

# Receive file data
data = b''
while True:
    part = client.recv(1024)
    if not part:
        break
    data += part

# Check file availability
if data == b'File not found':
    print("File not found on the server.")

else:
    # Display file contents
    print("File Contents:\n", data.decode())

    # Save received file
    with open("received_" + name, 'wb') as file:
        file.write(data)

    print("File received and saved successfully.")

# Close client
client.close()
