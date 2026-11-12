BEGIN {
}
{
    if ($6 == "cwnd_") {
        printf("%f \t %f \t \n", $1, $7);
    }
}
END {
}
```[span_6](start_span)[span_6](end_span)

---

### Execution & Plotting Commands (Ubuntu Terminal)

```bash
# 1. Run the NS2 simulation
ns ethernet_lan.tcl

# 2. Extract congestion window (cwnd_) values for TCP0 and TCP1
awk -f congestion_window.awk tcp0.tr > tcp0_cwnd.txt
awk -f congestion_window.awk tcp1.tr > tcp1_cwnd.txt

# 3. Plot the congestion window graph using xgraph
xgraph tcp0_cwnd.txt tcp1_cwnd.txt -geometry 800x400 &# SAMPLE OUTPUT (tcp0_cwnd.txt):
0.750000 	 1.000000 	 
0.771200 	 2.000000 	 
0.792400 	 3.000000 	 
0.813600 	 4.000000 	 
...
