# ==============================================================================
# OUTPUT / EXPERIMENT RESULTS:
#
# Execution commands:
# $ ns prog2.tcl
# $ awk -f prog2.awk out.tr
#
# ------------------------------------------------------------------------------
# Case 1:
# Link n0-n2-n3: Bandwidth = 1Mb, Queue Limit = 50
# Link n1-n2-n3: Bandwidth = 1Mb, Queue Limit = 50
# Packet Size = 500
#
# Result:
# Number of packets dropped = 500
# TCP : 1746
# UDP : 2602
#
# ------------------------------------------------------------------------------
# Case 2:
# Link n0-n2: Bandwidth = 0.5Mb, Queue Limit = 50
# Link n1-n2: Bandwidth = 0.5Mb, Queue Limit = 50
# Link n2-n3: Bandwidth = 0.5Mb, Queue Limit = 50
# TCP Packet Size = 500
# UDP Packet Size = 500
#
# Result:
# Number of packets dropped:
# TCP : 474
# UDP : 1363
# ==============================================================================
