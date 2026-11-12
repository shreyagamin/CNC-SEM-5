INF = 999

def input_topology():
    n = int(input("Enter the number of routers: "))
    routers = [chr(65 + i) for i in range(n)]
    print("\nEnter the cost matrix (use 999 for infinity): ")
    cost = []
    for i in range(n):
        row = list(map(int, input(f"Row {i + 1}: ").split()))
        cost.append(row)
    return n, routers, cost

def distance_vector_routing(n, routers, cost):
    dist = [[cost[i][j] for j in range(n)] for i in range(n)]
    next_hop = [[j for j in range(n)] for i in range(n)]
    
    print("\nInitial distance tables:")
    print_tables(routers, dist, next_hop)
    
    updated = True
    iteration = 0
    while updated:
        updated = False
        iteration += 1
        print(f"\n== Iteration {iteration} ==")
        for i in range(n):
            for j in range(n):
                for k in range(n):
                    if dist[i][j] > cost[i][k] + dist[k][j]:
                        dist[i][j] = cost[i][k] + dist[k][j]
                        next_hop[i][j] = k
                        updated = True
                        
    print_tables(routers, dist, next_hop)
    print(f"\nConverged after {iteration} iteration(s).")
    return dist, next_hop

def print_tables(routers, dist, next_hop):
    n = len(routers)
    for i in range(n):
        print(f"\nRouter {routers[i]} Routing Tables:")
        print("Destination | Cost | Next Hop")
        for j in range(n):
            cost_str = "INF" if dist[i][j] >= INF else str(dist[i][j])
            nh = routers[next_hop[i][j]]
            print(f"{routers[j]} | {cost_str:>3} | {nh}")
        print("-" * 30)

def main():
    n, routers, cost = input_topology()
    distance_vector_routing(n, routers, cost)

if __name__ == "__main__":
    main()


"""
OUTPUT:
Enter the number of routers: 4

Enter the cost matrix (use 999 for infinity): 
Row 1: 0 2 999 1
Row 2: 2 0 3 7
Row 3: 999 3 0 11
Row 4: 1 7 11 0

Initial distance tables:

Router A Routing Tables:
Destination | Cost | Next Hop
A |   0 | A
B |   2 | B
C | INF | C
D |   1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A |   2 | A
B |   0 | B
C |   3 | C
D |   7 | D
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A | INF | A
B |   3 | B
C |   0 | C
D |  11 | D
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A |   1 | A
B |   7 | B
C |  11 | C
D |   0 | D
------------------------------

== Iteration 1 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A |   0 | A
B |   2 | B
C |   5 | B
D |   1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A |   2 | A
B |   0 | B
C |   3 | C
D |   3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A |   5 | B
B |   3 | B
C |   0 | C
D |  11 | D
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A |   1 | A
B |   3 | A
C |  11 | C
D |   0 | D
------------------------------

== Iteration 2 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A |   0 | A
B |   2 | B
C |   5 | B
D |   1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A |   2 | A
B |   0 | B
C |   3 | C
D |   3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A |   5 | B
B |   3 | B
C |   0 | C
D |   6 | B
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A |   1 | A
B |   3 | A
C |   6 | A
D |   0 | D
------------------------------

== Iteration 3 ==

Router A Routing Tables:
Destination | Cost | Next Hop
A |   0 | A
B |   2 | B
C |   5 | B
D |   1 | D
------------------------------

Router B Routing Tables:
Destination | Cost | Next Hop
A |   2 | A
B |   0 | B
C |   3 | C
D |   3 | A
------------------------------

Router C Routing Tables:
Destination | Cost | Next Hop
A |   5 | B
B |   3 | B
C |   0 | C
D |   6 | B
------------------------------

Router D Routing Tables:
Destination | Cost | Next Hop
A |   1 | A
B |   3 | A
C |   6 | A
D |   0 | D
------------------------------

Converged after 3 iteration(s).
"""
