# ==============================================================================
# AWK script to count total packets dropped or sent for TCP and UDP flows
# ==============================================================================

BEGIN {
    tcp = 0;   # Initialize TCP packet counter
    udp = 0;   # Initialize UDP packet counter
}

{
    pkt = $5;  # Extract packet type column from trace file (5th field)
    
    # Check packet type and increment appropriate counter
    if (pkt == "tcp") {
        tcp++;
    }
    if (pkt == "cbr") {
        udp++;
    }
}

END {
    # Print the final counts for TCP and UDP packets
    printf("\n Number of packets sent \n TCP : %d \n UDP : %d \n", tcp, udp);
}
