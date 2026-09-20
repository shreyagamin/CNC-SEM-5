# ==============================================================================
# OUTPUT / EXECUTION COMMANDS:
#
# Commands to execute:
# $ ns prog1.tcl
# $ awk -f prog1.awk out.tr
#
# ------------------------------------------------------------------------------
# Case 1:
# Link n0-n1: Bandwidth = 1Mb,  queue-limit = 100, delay = 10ms
# Link n1-n2: Bandwidth = 1Mb,  queue-limit = 100, delay = 10ms
# Result: Number of packets dropped = 0
#
# ------------------------------------------------------------------------------
# Case 2:
# Link n0-n1: Bandwidth = 0.95Mb, queue-limit = 50, delay = 10ms
# Link n1-n2: Bandwidth = 1Mb,    queue-limit = 50, delay = 10ms
# Result: Number of packets dropped = 252
#
# ------------------------------------------------------------------------------
# Case 3:
# Link n0-n1: Bandwidth = 0.95Mb, queue-limit = 50, delay = 10ms
# Link n1-n2: Bandwidth = 1Mb,    queue-limit = 50, delay = 10ms
# Result: Number of packets dropped = 502
# ==============================================================================
