
# Frame Sorting – README

## 1. Save the program

Save the Java code as:

`FrameSorting.java`

## 2. Compile the program

Open the terminal in the folder containing the Java file.

```bash
javac FrameSorting.java
```

## 3. Run the program

```bash
java FrameSorting
```

## 4. Sample Output 1

**Input:** `HelloWorld`

```text
Enter the message: HelloWorld
Frames: [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Shuffled frames: [[2, 'orl'], [0, 'Hel'], [3, 'd'], [1, 'loW']]
Sorted frames: [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Final message: HelloWorld
```

## 5. Sample Output 2

**Input:** `COMPUTER`

```text
Enter the message: COMPUTER
Frames: [[0, 'COM'], [1, 'PUT'], [2, 'ER']]
Shuffled frames: [[2, 'ER'], [0, 'COM'], [1, 'PUT']]
Sorted frames: [[0, 'COM'], [1, 'PUT'], [2, 'ER']]
Final message: COMPUTER
```

## 6. Sample Output 3

**Input:** `CAT`

```text
Enter the message: CAT
Frames: [[0, 'CAT']]
Shuffled frames: [[0, 'CAT']]
Sorted frames: [[0, 'CAT']]
Final message: CAT
```

## 7. Sample Output 4

**Input:** `HELLO`

```text
Enter the message: HELLO
Frames: [[0, 'HEL'], [1, 'LO']]
Shuffled frames: [[1, 'LO'], [0, 'HEL']]
Sorted frames: [[0, 'HEL'], [1, 'LO']]
Final message: HELLO
```

## 8. Important Points

- Each frame contains up to 3 characters.
- The last frame may contain fewer than 3 characters.
- Sequence numbers start from 0.
- Shuffled order changes randomly every time.
- Sorting arranges frames in ascending sequence number.
- StringBuilder joins the frames to reconstruct the original message.

## 9. If Java is not installed

Check Java installation:

```bash
java -version
javac -version
```

Install JDK:

```bash
sudo apt update
sudo apt install default-jdk
```

## 10. Quick Revision

Compile: `javac FrameSorting.java`

Run: `java FrameSorting`

Flow: `Divide → Shuffle → Sort → Join`
