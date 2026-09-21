# ==============================================================================
# QUESTION:
# Write a program for error detection code using CRC-CCITT (16-bits / 4-bit divisor).
# ==============================================================================

# Divisor polynomial (4-bit binary generator polynomial, e.g., 1001)
div = [1, 0, 0, 1]

# Read total number of data bits from the user
n = int(input("Enter the number of bits : "))

# Read each bit into the data list
data = []
for i in range(n):
    bit = int(input(f"Enter bit {i+1} : "))
    data.append(bit)

# Get the length of the divisor polynomial
m = len(div)

# Validate data length against divisor length
if n < m:
    print("Invalid data")
else:
    # Create a working copy of original data and append (m - 1) zeros
    cdata = data.copy()
    for i in range(m - 1):
        cdata.append(0)

    print("Initial codeword : ", cdata)

    # Perform Modulo-2 Division at the sender side
    for i in range(n):
        if cdata[i] == 1:
            for j in range(m):
                cdata[i + j] = cdata[i + j] ^ div[j]  # Bitwise XOR operation

    # Extract CRC remainder bits (last m-1 bits)
    crc = cdata[n:]
    print("CRC bits : ", crc)

    # Prepare transmitted codeword = Original Data + CRC bits
    tx_code = data + crc
    print("Codeword to be transmitted : ", tx_code)

    # Simulate transmission channel / error introduction
    err = input("Introduce error: Y -> yes, N -> no : ").strip().lower()

    if err == 'y':
        pos = int(input("Enter the position : "))
        # Flip bit at position (1-indexed input adjusted to 0-indexed)
        tx_code[pos - 1] = 1 - tx_code[pos - 1]

    print("Received data : ", tx_code)

    # Receiver side Modulo-2 Division check
    rdata = tx_code.copy()
    for i in range(n):
        if rdata[i] == 1:
            for j in range(m):
                rdata[i + j] = rdata[i + j] ^ div[j]

    # Extract receiver remainder
    rem = rdata[n:]
    print("Remainder : ", rem)

    # Verify if remainder contains any non-zero bits
    if any(rem):
        print("Error detected")
    else:
        print("No Error detected")
