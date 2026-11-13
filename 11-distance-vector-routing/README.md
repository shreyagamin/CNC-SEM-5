
# Distance Vector Routing – README

## 1. Required File

- DistanceVectorRouting.java

## 2. Compile the Program

Open the terminal in the folder containing the Java file.

```bash
javac DistanceVectorRouting.java
```

## 3. Run the Program

```bash
java DistanceVectorRouting
```

## 4. Sample Input

```text
Enter the number of routers: 4

Enter the cost matrix (use 999 for infinity):
0 2 999 1
2 0 3 7
999 3 0 11
1 7 11 0
```

## 5. Sample Output

```text
Initial distance tables:

Router A Routing Tables:
Destination | Cost | Next Hop
A | 0 | A
B | 2 | B
C | INF | C
D | 1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A | 2 | A
B | 0 | B
C | 3 | C
D | 7 | D
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A | INF | A
B | 3 | B
C | 0 | C
D | 11 | D
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A | 1 | A
B | 7 | B
C | 11 | C
D | 0 | D
------------------------------

== Iteration 1 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A | 0 | A
B | 2 | B
C | 5 | B
D | 1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A | 2 | A
B | 0 | B
C | 3 | C
D | 3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A | 5 | B
B | 3 | B
C | 0 | C
D | 10 | B
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A | 1 | A
B | 3 | A
C | 10 | B
D | 0 | D
------------------------------

== Iteration 2 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A | 0 | A
B | 2 | B
C | 5 | B
D | 1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A | 2 | A
B | 0 | B
C | 3 | C
D | 3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A | 5 | B
B | 3 | B
C | 0 | C
D | 6 | B
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A | 1 | A
B | 3 | A
C | 6 | A
D | 0 | D
------------------------------

== Iteration 3 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A | 0 | A
B | 2 | B
C | 5 | B
D | 1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A | 2 | A
B | 0 | B
C | 3 | C
D | 3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A | 5 | B
B | 3 | B
C | 0 | C
D | 6 | B
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A | 1 | A
B | 3 | A
C | 6 | A
D | 0 | D
------------------------------

Converged after 3 iteration(s).
```

## 6. Important Points

- 999 represents infinity (no direct connection).
- Router names start from A.
- Distance table initially contains the cost matrix.
- The algorithm finds shorter paths through intermediate routers.
- Next Hop indicates the router to visit first.
- Iterations continue until no distance changes.
- Convergence means all routing tables have stabilized.

## 7. If Java is Not Installed

Check Java:

```bash
java -version
javac -version
```

Install Java:

```bash
sudo apt update
sudo apt install default-jdk
```

## 8. Quick Revision

Compile:

```bash
javac DistanceVectorRouting.java
```

Run:

```bash
java DistanceVectorRouting
```

Flow:

`Input → Initialize → Compare → Update → Repeat → Converge`
