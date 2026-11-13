
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
for {set i 0} {$i < 10} {incr i} {
    set n($i) [$ns node]
}

# Create Ethernet LAN
$ns make-lan "$n(0) $n(1) $n(2) $n(3) $n(4) $n(5) $n(6) $n(7) $n(8) $n(9)" 50Mb 10ms LL Queue/DropTail Mac/802_3

# TCP0 with FTP: n0 to n3
set tcp0 [new Agent/TCP]
$ns attach-agent $n(0) $tcp0

set sink0 [new Agent/TCPSink]
$ns attach-agent $n(3) $sink0
$ns connect $tcp0 $sink0

set cfile0 [open tcp0.tr w]
$tcp0 attach $cfile0
$tcp0 trace cwnd_

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

# TCP1 with Telnet: n1 to n3
Agent/TCP set packetSize_ 1000

set tcp1 [new Agent/TCP]
$ns attach-agent $n(1) $tcp1

set sink1 [new Agent/TCPSink]
$ns attach-agent $n(3) $sink1
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
