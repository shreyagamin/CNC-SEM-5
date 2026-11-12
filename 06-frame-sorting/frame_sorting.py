import random as rd

msg = input("Enter the message : ")
frames = [[i // 3, msg[i:i + 3]] for i in range(0, len(msg), 3)]
print("Frames :", frames)

rd.shuffle(frames)
print("Shuffled frames :", frames)

frames.sort()
print("Sorted frames :", frames)

message = ''.join(frame[1] for frame in frames)
print("Final message :", message)


"""
OUTPUT:
Enter the message : HelloWorld
Frames : [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Shuffled frames : [[2, 'orl'], [0, 'Hel'], [3, 'd'], [1, 'loW']]
Sorted frames : [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Final message : HelloWorld
"""
