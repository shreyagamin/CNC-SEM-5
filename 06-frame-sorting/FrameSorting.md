# Frame Sorting – Exam Quick Notes

## 1. Aim

Divide a message into smaller frames, shuffle them, sort them using sequence numbers, and reconstruct the original message.

## 2. Remember the flow

**Sender:** `Read message → Divide into frames → Assign sequence numbers`

**Receiver:** `Shuffle frames → Sort by sequence number → Join data → Get original message`

## 3. Key code logic

### Frame Creation

- `Frame` class stores `seqNo` and `data`.
- `List<Frame>` stores all frames.
- `i += 3`: Divides the message into 3-character chunks.
- `substring()`: Extracts each chunk.
- `i / 3`: Assigns the sequence number.

### Shuffling and Sorting

- `Collections.shuffle(frames)`: Randomly changes frame order.
- `frames.sort()`: Sorts frames using sequence numbers.
- `a.seqNo - b.seqNo`: Sorts frames in ascending order.

### Message Reconstruction

- `StringBuilder`: Builds the original message.
- `append(f.data)`: Joins each frame's data in sorted order.
- Prints the final message.

## 4. Memory tricks

- **Frame class:** Stores sequence number and data.
- **i += 3:** Takes 3 characters at a time.
- **i / 3:** Assigns sequence number.
- **Shuffle:** Mixes the frames.
- **Sort:** Arranges frames in correct order.
- **Append:** Joins the frames to recover the message.

## 5. Tiny dry run

Input: `HelloWorld`

Frames:

`[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']`

After shuffling:

`[2, 'orl'], [0, 'Hel'], [3, 'd'], [1, 'loW']`

After sorting:

`[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']`

Final message: `HelloWorld`

## 6. One-line recall

"Divide into 3-character frames, shuffle, sort using sequence numbers, and join the data."
