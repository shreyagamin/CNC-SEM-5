# CRC – Quick Lab Revision

### What is the program about?
CRC (Cyclic Redundancy Check) is used to detect errors during data transmission using XOR division.

### What should I write? (Program flow)

1. **Input:** Read data bits and declare divisor (`1001`).
2. **Append zeros:** Create an array of size `n + m - 1` (append `m-1` zeros).
3. **Sender division:** Perform XOR division using nested loops.
4. **Get CRC:** Extract the last `m-1` bits as the CRC remainder.
5. **Codeword:** Combine original data + CRC.
6. **Introduce error:** Ask Y/N and flip a bit if required.
7. **Receiver:** Perform XOR division again on received codeword.
8. **Check:** All-zero remainder = No error; non-zero remainder = Error detected.

### Remember these important points

- Divisor: `1001`
- `m` = Divisor length.
- Zeros appended = `m-1`.
- CRC length = `m-1`.
- XOR operator = `^`.
- Same nested XOR loops are used at sender and receiver.

### Most important code logic

```java
for (int i = 0; i < n; i++) {
    if (temp[i] == 1) {
        for (int j = 0; j < m; j++) {
            temp[i+j] ^= div[j];
        }
    }
}
```

**Memory trick:**

Input → Append zeros → Divide → Get CRC → Attach → Transmit → Divide again → Check error.
