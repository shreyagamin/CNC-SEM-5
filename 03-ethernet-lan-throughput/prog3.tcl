# ==============================================================================
# QUESTION:
# Simulate an Ethernet LAN using N-nodes (6-10), change error rate and data rate,
# and compare the throughput.
# ==============================================================================

# ==============================================================================
# TOPOLOGY LAYOUT:
#
#   (n6)  (n7)  (n8)  (n9)  (n10) (n11)
#    |     |     |     |     |     |
#  ======================================= Ethernet LAN (100Mb, 10ms, 802.3 MAC)
#    |     |     |     |     |     |
#   (n0)  (n1)  (n2)  (n3)  (n4)  (n5)
#    |                 |
#  [src]             [dest]
#  (tcp0) -------> (sink0)
#    |
#  [ftp0]
# ==============================================================================

# Create a Simulator object
set ns [new Simulator]

# Open NAM trace file for visualization
set nf [open out.nam w]
$ns namtrace-all $nf

# Open trace file for recording simulation event data
set tf [open out.tr w]
$ns trace-all $tf

# Define finish procedure to flush buffers, close files, and open NAM
proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam out.nam &
    exit 0
}

# Create 12 nodes (n0 to n11) for the Ethernet LAN environment
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
set n10 [$ns node]
set n11 [$ns node]

# Create an Ethernet LAN connecting nodes n0 through n11
# Parameters: Node list, Bandwidth (100Mb), Delay (10ms), Link Layer (LL), Queue type (DropTail), MAC protocol (Mac/802_3)
$ns make-lan "$n0 $n1 $n2 $n3 $n4 $n5 $n6 $n7 $n8 $n9 $n10 $n11" 100Mb 10ms LL Queue/DropTail Mac/802_3

# --- TCP Agent Setup (Source: n0) ---
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

# --- TCP Sink Setup (Destination: n3) ---
set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0

# Connect TCP source at n0 to TCP Sink at n3
$ns connect $tcp0 $sink0

# --- FTP Application Setup ---
set ftp0 [new Application/FTP]
$ftp0 set packetSize_ 500  ;# Set FTP packet size to 500 bytes
$ftp0 attach-agent $tcp0

# Schedule simulation events
$ns at 0.5 "$ftp0 start"
$ns at 4.5 "$ftp0 stop"
$ns at 5.0 "finish"

# Start the simulation
$ns run
