# NS2 – 3-Node Point-to-Point Network

## Question
Simulate a 3-node point-to-point network with duplex links. Set the queue size and find the number of packets dropped.

## Complete Code (Quick Revision)

```tcl
# 1. Create simulator
set ns [new Simulator]

# 2. Trace and NAM files
set tf [open out.tr w]
$ns trace-all $tf

set nf [open out.nam w]
$ns namtrace-all $nf

# 3. Finish procedure
proc finish {} {
    global ns tf nf
    $ns flush-trace
    close $tf
    close $nf
    exec nam out.nam &
    exit 0
}

# 4. Create 3 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

# 5. Duplex links and queue limits
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 0.5Mb 10ms DropTail

$ns queue-limit $n0 $n1 50
$ns queue-limit $n1 $n2 50

# 6. UDP sender + CBR traffic
set udp [new Agent/UDP]
$ns attach-agent $n0 $udp

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp

$cbr set packetSize_ 500
$cbr set interval_ 0.005

# Null receiver
set null0 [new Agent/Null]
$ns attach-agent $n2 $null0

# Connect sender to receiver
$ns connect $udp $null0

# 7. Schedule events
$ns at 0.5 "$cbr start"
$ns at 4.5 "$cbr stop"
$ns at 5.0 "finish"

# 8. Run simulation
$ns run
```

## Recall Order

**S – F – F – N – L – A – E – R**

1. **S**imulator
2. **F**iles: trace + NAM
3. **F**inish procedure
4. **N**odes: n0, n1, n2
5. **L**inks + queue limits
6. **A**gents: UDP → CBR → Null → connect
7. **E**vents: start → stop → finish
8. **R**un

## Key Values to Remember

| Item | Value |
|---|---|
| Nodes | 3 |
| Link 1 | n0–n1, 1Mb, 10ms, DropTail |
| Link 2 | n1–n2, 0.5Mb, 10ms, DropTail |
| Queue limit | 50 packets on each link |
| Packet size | 500 bytes |
| CBR interval | 0.005 seconds |
| Start traffic | 0.5 seconds |
| Stop traffic | 4.5 seconds |
| Finish simulation | 5.0 seconds |

## Count Dropped Packets

In the NS2 trace file, `d` indicates a dropped packet.

```bash
awk '$1 == "d" {count++} END {print count}' out.tr
```

**Remember:** Queue limit 50 means the queue can hold up to 50 packets; it does NOT mean 50 packets are dropped. The actual drop count comes from the simulation trace.
