# Lab Program 5: Error Detection using CRC-CCITT

## Question Statement
Write a program for error detection code using CRC-CCITT (16-bits / 4-bit divisor).

---

## Output / Execution Commands

### Command to Execute
```bash
python3 prog5.py
```

## Sample Execution Results
### Case 1: Error Introduced (Error Detected) 
Plaintext 
Enter the number of bits : 8 
Enter bit 1 : 1 
Enter bit 2 : 1 
Enter bit 3 : 0 
Enter bit 4 : 0 
Enter bit 5 : 1 
Enter bit 6 : 0 
Enter bit 7 : 0 
Enter bit 8 : 1 
Initial codeword : [1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0] 
CRC bits : [0, 1, 1] 
Codeword to be transmitted : [1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1] 
Introduce error: Y -> yes, N -> no : y 
Enter the position : 3 
Received data : [1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1] 
Remainder : [1, 0, 0] 
Error detected 

### Case 2: No Error Introduced (No Error Detected) 

Enter the number of bits : 8 
Enter bit 1 : 1 
Enter bit 2 : 1 
Enter bit 3 : 0 
Enter bit 4 : 0 
Enter bit 5 : 1 
Enter bit 6 : 0 
Enter bit 7 : 0 
Enter bit 8 : 1 
Initial codeword : [1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0] 
CRC bits : [0, 1, 1] 
Codeword to be transmitted : [1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1] 
Introduce error: Y -> yes, N -> no : n 
Remainder : [0, 0, 0] 
No Error detected 
