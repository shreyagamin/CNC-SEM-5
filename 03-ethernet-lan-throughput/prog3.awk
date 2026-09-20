# ==============================================================================
# AWK script to calculate and display throughput over the transmission duration
# ==============================================================================

BEGIN {
    sSize = 0;          # Cumulative size of received packets in bytes
    startTime = 5.0;    # Initialize start time to maximum simulation limit
    stopTime = 0.1;     # Initialize stop time to minimal start threshold
    Tput = 0;           # Initialize throughput variable
}

{
    event = $1;         # First column: event type (+ for enqueue, r for receive)
    time = $2;          # Second column: event timestamp
    size = $6;          # Sixth column: packet size in bytes

    # Capture start time when the first packet is enqueued
    if (event == "+") {
        if (time < startTime) {
            startTime = time;
        }
    }

    # Process packet reception events to aggregate received data and calculate throughput
    if (event == "r") {
        if (time > stopTime) {
            stopTime = time;
        }
        sSize += size;  # Accumulate received packet size
        
        # Calculate throughput in kbps: (Bytes / Duration) * (8 bits / 1000)
        Tput = (sSize / (stopTime - startTime)) * (8 / 1000);
        
        # Print time step and calculated throughput
        printf("%f\t%.2f\n", time, Tput);
    }
}

END {
    # Final cleanup at end of trace execution
}
