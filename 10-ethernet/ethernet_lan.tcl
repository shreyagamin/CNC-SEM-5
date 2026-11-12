set ns [new Simulator]

set nf [open out.nam w]
$ns namtrace-all $nf

set tf [open out.tr w]
$ns trace-all $tf

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam out.nam &
    exit 0
}

# Create 10 nodes (n0 to n9)
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]
set n9 [$ns node]

# Create Ethernet LAN
$ns make-lan "$n0 $n1 $n2 $n3 $n4 $n5 $n6 $n7 $n8 $n9" 50Mb 10ms LL Queue/DropTail Mac/802_3

# Setup TCP0 and FTP0 (Source: n0 -> Destination: n3)
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0
$ns connect $tcp0 $sink0

set cfile0 [open tcp0.tr w]
$tcp0 attach $cfile0
$tcp0 trace cwnd_

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

# Setup TCP1 and Telnet0 (Source: n1 -> Destination: n3)
Agent/TCP set packetSize_ 1000
set tcp1 [new Agent/TCP]
$ns attach-agent $n1 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n3 $sink1
$ns connect $tcp1 $sink1

set cfile1 [open tcp1.tr w]
$tcp1 attach $cfile1
$tcp1 trace cwnd_

set telnet0 [new Application/Telnet]
$telnet0 set interval_ 0.005
$telnet0 attach-agent $tcp1

# Schedule events
$ns at 0.75 "$ftp0 start"
$ns at 4.75 "$ftp0 stop"
$ns at 0.5 "$telnet0 start"
$ns at 4.5 "$telnet0 stop"
$ns at 5.0 "finish"

$ns run
```[span_3](start_span)[span_3](end_span)[span_4](start_span)[span_4](end_span)[span_5](start_span)[span_5](end_span)

---

### 2. AWK Script (`congestion_window.awk`)

```awk
BEGIN {
}
{
    if ($6 == "cwnd_") {
        printf("%f \t %f \t \n", $1, $7);
    }
}
END {
}
```[span_6](start_span)[span_6](end_span)

---

### Execution & Plotting Commands (Ubuntu Terminal)

```bash
# 1. Run the NS2 simulation
ns ethernet_lan.tcl

# 2. Extract congestion window (cwnd_) values for TCP0 and TCP1
awk -f congestion_window.awk tcp0.tr > tcp0_cwnd.txt
awk -f congestion_window.awk tcp1.tr > tcp1_cwnd.txt

# 3. Plot the congestion window graph using xgraph
xgraph tcp0_cwnd.txt tcp1_cwnd.txt -geometry 800x400 &
