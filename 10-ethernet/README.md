
# Ethernet LAN Congestion Window Simulation

## Files

- `ethernet_lan.tcl` – NS2 simulation program
- `congestion_window.awk` – Extracts congestion-window values

## 1. Install Required Tools

```bash
sudo apt update
sudo apt install ns2 nam xgraph
```

## 2. Run the NS2 Simulation

```bash
ns ethernet_lan.tcl
```

This runs the simulation and opens the NAM animation.

Generated files:
- `out.nam`
- `out.tr`
- `tcp0.tr`
- `tcp1.tr`

## 3. Extract Congestion Window Values

For TCP0:

```bash
awk -f congestion_window.awk tcp0.tr > tcp0_cwnd.txt
```

For TCP1:

```bash
awk -f congestion_window.awk tcp1.tr > tcp1_cwnd.txt
```

## 4. Plot the Graph

```bash
xgraph tcp0_cwnd.txt tcp1_cwnd.txt -geometry 800x400 &
```

The graph displays the congestion window against simulation time for both TCP connections.

## 5. Sample Output

Example contents of `tcp0_cwnd.txt`:

```text
0.750000    1.000000
0.771200    2.000000
0.792400    3.000000
0.813600    4.000000
...
```

The exact values and number of records may vary depending on the simulation.

## 6. Important Points

1. Total nodes = 10.
2. Ethernet LAN bandwidth = 50 Mb.
3. LAN delay = 10 ms.
4. TCP0 uses FTP.
5. TCP1 uses Telnet.
6. Both connections send data to node n3.
7. `cwnd_` records the congestion window.
8. AWK extracts time and congestion-window values.
9. Xgraph plots the extracted values.

## Quick Revision

```text
TCL → Run simulation
AWK → Extract cwnd values
XGRAPH → Plot congestion window
NAM → View network animation
```
