# CRC – Exam Quick Notes

## 1. Aim

Detect errors during data transmission using **Cyclic Redundancy Check (CRC)** by performing XOR division.

## 2. Remember the flow

**Sender:** `Read data → Append zeros → XOR division → Get CRC → Send codeword`

**Receiver:** `Receive codeword → XOR division → Check remainder → Detect error`

## 3. Key code logic

### Sender

- `div[]`: Stores the divisor (generator polynomial).
- `m = div.length`: Finds the divisor length.
- `temp[]`: Stores data followed by `m-1` zeros.
- Perform XOR division:
    - If `temp[i] == 1`, XOR the divisor with the current bits.
    - If `temp[i] == 0`, move to the next position.
- Extract the last `m-1` bits as `crc[]`.
- Create `code[]` by combining original data and CRC.

### Receiver

- Perform XOR division on the received `code[]`.
- Check the last `m-1` bits (remainder).
- If all bits are `0` → **No error detected.**
- If any bit is `1` → **Error detected.**

## 4. Memory tricks

- **Append zeros:** Number of zeros = divisor length − 1.
- **XOR division:** Divide only when the current bit is `1`.
- **CRC:** Last `m-1` bits of the sender's division.
- **Codeword:** Original data + CRC.
- **Receiver:** Checks whether the remainder is zero.

## 5. Tiny dry run

Data: `1 0 1 1`

Divisor: `1 0 1`

- Divisor length = 3.
- Append 2 zeros: `1 0 1 1 0 0`.
- Perform XOR division.
- Suppose the CRC obtained is `1 0`.
- Codeword: `1 0 1 1 1 0`.

The receiver divides the received codeword again. A zero remainder means no error is detected.

**One-line recall:** "Append zeros, XOR divide, attach CRC; receiver divides again and checks for a zero remainder."
