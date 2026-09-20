# ==============================================================================
# AWK script to count the total number of dropped packets from 'out.tr'
# ==============================================================================

BEGIN {
    count = 0;   # Initialize dropped packet counter to 0
}

{
    event = $1;  # Record event type from the first column of trace file
    
    # Check if the event action is 'd' (dropped packet)
    if (event == "d") {
        count++; # Increment counter for every dropped packet
    }
}

END {
    # Print the total count of dropped packets
    printf("\n Number of packets dropped is: %d \n", count);
}
