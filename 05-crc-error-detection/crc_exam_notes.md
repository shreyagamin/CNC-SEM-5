# CRC --- Exam Quick Notes

## 1. Aim

**Cyclic Redundancy Check (CRC)** detects transmission errors using
modulo-2 division (XOR).

## 2. Remember the flow

**Sender:**
`Read data → Append zeros → Divide using XOR → Get CRC → Attach CRC`

**Receiver:**
`Receive codeword → Divide using XOR → Check remainder → Detect error`

## 3. Key code logic

### Sender

-   Divisor: `1001` (4 bits), so `m = 4`.
-   Append `m - 1` zeros to the data.
-   Divide using modulo-2 division:
    -   If the current bit is `1`, XOR the next `m` bits with the
        divisor.
    -   If it is `0`, move to the next position.
-   Last `m - 1` bits are the **CRC remainder**.
-   Build transmitted codeword: `original data + CRC`.

### Error simulation

-   If user enters `Y`, flip the selected bit:
    `tx_code[pos - 1] = 1 - tx_code[pos - 1]`
-   `pos - 1` converts the user's 1-based position to Java's 0-based
    index.

### Receiver

-   Clone the received codeword.
-   Repeat the same XOR division.
-   Extract the last `m - 1` bits as the remainder.
-   If **any remainder bit is 1** → `Error detected`.
-   If all remainder bits are 0 → `No Error detected`.

## 4. Memory tricks

-   **Divisor length 4 → append 3 zeros → CRC has 3 bits.**
-   **XOR only when the current bit is 1.**
-   XOR rules: `0^0=0`, `1^1=0`, `0^1=1`, `1^0=1`.
-   Sender calculates and attaches CRC; receiver recalculates and
    checks.
-   Non-zero remainder means error detected.

## 5. Exam checklist

-   [ ] `m = div.length`
-   [ ] Working array size = `n + m - 1`
-   [ ] Append `m - 1` zeros.
-   [ ] Use nested loops for XOR division.
-   [ ] CRC = last `m - 1` bits.
-   [ ] Receiver repeats division and checks remainder.

**One-line recall:** "Add zeros, XOR-divide, attach remainder; receiver
divides again---zero remainder means no error detected."

**Note:** A zero remainder means no error was detected; CRC cannot
guarantee detection of every possible error.
