# ==============================================================================
# QUESTION:
# Simulate a 3-node point-to-point network with duplex links between them.
# Set the queue size and find the number of packets dropped.
# ==============================================================================

# Create a new Simulator object
set ns [new Simulator]

# Open trace file 'out.tr' in write mode to record simulation events
set tf [open out.tr w]
$ns trace-all $tf

# Open NAM (Network Animator) file 'out.nam' in write mode
set nf [open out.nam w]
$ns namtrace-all $nf

# Define the finish procedure to close files and execute NAM
proc finish {} {
    global ns tf nf
    $ns flush-trace       ;# Flush all remaining trace buffers
    close $tf             ;# Close the trace file
    close $nf             ;# Close the NAM trace file
    exec nam out.nam &    ;# Launch NAM in the background
    exit 0                ;# Terminate the script cleanly
}

# Create three network nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

# Setup duplex links between nodes with bandwidth, delay, and queue type (DropTail)
# Link between n0 and n1: 1Mb bandwidth, 10ms delay
$ns duplex-link $n0 $n1 1Mb 10ms DropTail

# Link between n1 and n2: 0.5Mb bandwidth, 10ms delay
$ns duplex-link $n1 $n2 0.5Mb 10ms DropTail

# Set queue limits (buffer size) for the links
$ns queue-limit $n0 $n1 50
$ns queue-limit $n1 $n2 50

# Create a UDP agent and attach it to node n0 (Sender)
set udp [new Agent/UDP]
$ns attach-agent $n0 $udp

# Create a CBR (Constant Bit Rate) traffic generator and attach it to UDP
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp

# Set properties for the CBR traffic stream
$cbr set packetSize_ 500   ;# Packet size in bytes
$cbr set interval_ 0.005   ;# Inter-packet transmission interval in seconds

# Create a Null agent (traffic sink) and attach it to node n2 (Receiver)
set null0 [new Agent/Null]
$ns attach-agent $n2 $null0

# Connect the UDP source agent at n0 to the Null sink agent at n2
$ns connect $udp $null0

# Schedule simulation events
$ns at 0.5 "$cbr start"   ;# Start CBR traffic generator at 0.5s
$ns at 4.5 "$cbr stop"    ;# Stop CBR traffic generator at 4.5s
$ns at 5.0 "finish"       ;# Call finish procedure at 5.0s

# Run the simulation
$ns run
