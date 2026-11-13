
# Ethernet LAN Congestion Window — Exam Quick Notes

## 1. Aim

To simulate an Ethernet LAN using NS2 and plot the congestion window of two TCP connections.

## 2. Remember the flow

Create Simulator → Create 10 Nodes → Create Ethernet LAN → Setup TCP + FTP → Setup TCP + Telnet → Schedule Events → Run Simulation → Extract cwnd → Plot Graph

## 3. Key Code Logic

### Simulator and Trace Files

- `new Simulator` creates the simulator.
- `out.nam` stores animation trace.
- `out.tr` stores network trace.
- `finish` closes traces and opens NAM.

### Create Nodes and LAN

- Create 10 nodes using a loop.
- `make-lan` connects all nodes.
- LAN bandwidth = 50 Mb and delay = 10 ms.

### TCP0 and FTP

- Source: n0
- Destination: n3
- `Agent/TCP` creates TCP agent.
- `TCPSink` receives TCP packets.
- `FTP` generates data continuously.

### TCP1 and Telnet

- Source: n1
- Destination: n3
- `Telnet` generates traffic at an interval of 0.005 seconds.

### Congestion Window

- `attach` connects the trace file to the TCP agent.
- `trace cwnd_` records congestion-window changes.
- `cwnd_` represents the TCP congestion window.

### Schedule Events

- FTP starts at 0.75 and stops at 4.75.
- Telnet starts at 0.5 and stops at 4.5.
- Simulation finishes at 5.0 seconds.

### AWK Logic

- `$6 == "cwnd_"` selects congestion-window records.
- `$1` represents time.
- `$7` represents congestion-window value.
- `printf` prints time and cwnd.

## 4. Memory Tricks

- FTP → TCP0 → n0 to n3.
- Telnet → TCP1 → n1 to n3.
- `cwnd_` → congestion window.
- AWK `$1` → time; `$7` → window size.
- `nam` → animation; `xgraph` → graph.

## 5. One-line Recall

Create LAN → Configure two TCP applications → Schedule traffic → Trace cwnd → Extract using AWK → Plot using Xgraph.
