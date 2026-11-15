
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

# Create 10 nodes
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

# First TCP connection - FTP
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0

$ns connect $tcp0 $sink0

set cfile0 [open tcp0.tr w]
$tcp0 attach $cfile0
$tcp0 trace cwnd

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

# Second TCP connection - Telnet
Agent/TCP set packetSize_ 1000

set tcp1 [new Agent/TCP]
$ns attach-agent $n1 $tcp1

set sink1 [new Agent/TCPSink]
$ns attach-agent $n3 $sink1

$ns connect $tcp1 $sink1

set cfile1 [open tcp1.tr w]
$tcp1 attach $cfile1
$tcp1 trace cwnd

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
