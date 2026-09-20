# ==============================================================================
# QUESTION:
# Simulate a 4-node point-to-point network and connect the links as follows:
# n0-n2, n1-n2, and n2-n3.
# Apply TCP agent between n0-n3 and UDP agent between n1-n3.
# Change the parameters and determine the number of packets dropped by TCP/UDP.
# ==============================================================================

# ==============================================================================
# TOPOLOGY LAYOUT:
#
#       (n0) [TCP Source]  --- FTP ---
#           \
#            \
#             ---> (n2) [Router/Switch] ---> (n3) [TCP Sink & UDP Sink]
#            /
#           /
#       (n1) [UDP Source]  --- CBR ---
# ==============================================================================

# Create a Simulator object
set ns [new Simulator]

# Open trace file for writing simulation events
set tf [open out.tr w]
$ns trace-all $tf

# Open NAM trace file for network animation
set nf [open out.nam w]
$ns namtrace-all $nf

# Define finish procedure to flush traces, close files, and open NAM
proc finish {} {
    global ns tf nf
    $ns flush-trace
    close $tf
    close $nf
    exec nam out.nam &
    exit 0
}

# Create four network nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Set node colors for visual differentiation in NAM
$n0 color "red"
$n1 color "blue"
$n2 color "green"

# Define duplex links between nodes with bandwidth, delay, and queue type
$ns duplex-link $n0 $n2 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns duplex-link $n2 $n3 1Mb 10ms DropTail

# Set queue limits (buffer size) for each link
$ns queue-limit $n0 $n2 50
$ns queue-limit $n1 $n2 50
$ns queue-limit $n2 $n3 50

# --- TCP Setup (n0 to n3) ---
# Create TCP agent and attach it to node n0
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp

# Create TCP Sink agent and attach it to node n3
set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0

# Connect TCP agent at n0 to TCP Sink at n3
$ns connect $tcp $sink0

# Attach FTP application to TCP agent
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp
$tcp set packetSize_ 500   ;# Set TCP packet size to 500 bytes

# --- UDP Setup (n1 to n3) ---
# Create UDP agent and attach it to node n1
set udp0 [new Agent/UDP]
$ns attach-agent $n1 $udp0

# Create Null agent (Sink) and attach it to node n3
set null0 [new Agent/Null]
$ns attach-agent $n3 $null0

# Connect UDP agent at n1 to Null sink at n3
$ns connect $udp0 $null0

# Attach CBR traffic generator to UDP agent
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
$cbr0 set packetSize_ 500  ;# Set CBR packet size to 500 bytes
$cbr0 set interval_ 0.005  ;# Set packet transmission interval to 0.005s

# Schedule traffic events and simulation end time
$ns at 0.5 "$cbr0 start"
$ns at 0.8 "$ftp0 start"
$ns at 4.5 "$ftp0 stop"
$ns at 4.5 "$cbr0 stop"
$ns at 5.0 "finish"

# Start simulation
$ns run
