# Bit Stuffing --- Exam Quick Notes

## 1. Aim

Prevent the **flag pattern** from appearing inside data by inserting a
`0` after every **five consecutive `1`s**.

## 2. Remember the flow

**Sender:** `Read → Add start flag → Stuff bits → Add end flag → Send`

**Receiver:** `Remove flags → Destuff → Print original data`

## 3. Key code logic

### Sender

-   Flag: `01111110` (8 bits).
-   Start `count = 0`.
-   Before adding a bit: if `count == 5`, insert `0` and reset `count`.
-   Add the actual bit.
-   If bit is `1`, increment `count`; otherwise reset it to `0`.
-   Add the ending flag.

### Receiver

-   Ignore the first and last 8 bits (flags).
-   For each data bit:
    -   If it is `1`: add it and increment `count`.
    -   If it is `0` and `count == 5`: skip it (stuffed bit) and reset
        `count`.
    -   Otherwise add the `0` and reset `count`.

## 4. Memory tricks

-   **5 ones → stuff 1 zero.**
-   **Sender inserts; receiver skips.**
-   `count` tracks only consecutive `1`s.
-   A real `0` resets the count.
-   Flags are added at both ends and removed before decoding.

## 5. Tiny dry run

Input: `1 1 1 1 1 1`

Stuffed data: `1 1 1 1 1 0 1`

The inserted `0` is removed by the receiver, restoring the original six
bits.

## 6. Exam checklist

-   [ ] Flag is `01111110`.
-   [ ] Stuff `0` after every five consecutive `1`s.
-   [ ] Check for stuffing **before adding the next bit**.
-   [ ] Receiver removes only the stuffed `0`.
-   [ ] Original data is recovered.

**One-line recall:** "Frame with flags, count five 1s, insert zero;
receiver removes that zero."
