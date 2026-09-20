# ==============================================================================
# QUESTION:
# Program to implement Bit Stuffing concept in Data Link Layer.
# ==============================================================================

def sender():
    # Read total number of input bits from user
    n = int(input("Enter the no of bits: "))
    
    print(f"Enter {n} bits :")
    data = [int(input()) for _ in range(n)]  # Read input bits one by one into a list
    
    # Flag pattern (01111110) used to mark start and end of a frame
    add = [0, 1, 1, 1, 1, 1, 1, 0]
    
    frame = []
    frame += add  # Add starting flag bit pattern to the frame
    
    count = 0  # Counter to track consecutive 1's
    
    # Perform Bit Stuffing logic
    for i in data:
        if count == 5:
            frame.append(0)  # Insert a '0' bit after 5 consecutive 1's
            count = 0        # Reset consecutive 1's count
            
        frame.append(i)      # Append current bit to frame
        
        if i == 1:
            count += 1       # Increment count if current bit is 1
        else:
            count = 0        # Reset count if bit is 0
            
    frame += add  # Add ending flag bit pattern to the frame
    
    print("Sent Frame : ", frame)
    
    # Pass the stuffed frame to the receiver
    receiver(frame)


def receiver(frame):
    data = []
    count = 0  # Counter to track consecutive 1's
    
    # Remove starting and ending 8-bit flag patterns [01111110]
    frame_body = frame[8:-8]
    
    # Process framed payload and perform De-stuffing
    for i in frame_body:
        if i == 1:
            data.append(i)
            count += 1
        elif i == 0:
            if count == 5:
                # Ignore/remove stuffed '0' after 5 consecutive 1's
                count = 0
            else:
                data.append(i)
                count = 0
                
    print("Data Received is : ", data)


# Function Call / Entry Point
sender()
