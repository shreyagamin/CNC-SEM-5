
# Distance Vector Routing – Exam Quick Notes

## 1. Aim

Find the shortest path and minimum cost between routers using the Distance Vector Routing algorithm.

## 2. Remember the flow

`Input Routers → Enter Cost Matrix → Initialize Distance Table → Compare Routes Through Other Routers → Update Minimum Cost → Repeat Until No Changes`

## 3. Key code logic

### Initialization

- `INF = 999`: Represents infinity (no direct connection).
- `routers[]`: Stores router names A, B, C, D...
- `cost[][]`: Stores the original cost matrix.
- `dist[][]`: Stores the current minimum distance.
- `next[][]`: Stores the next-hop router.
- Initially, `dist = cost` and `next[i][j] = j`.

### Distance Calculation

- `updated = false`: Checks whether any route changes.
- `old[][]`: Keeps a copy of the previous distance table.
- Three loops `i, j, k` compare routes:
    - `i`: Current router.
    - `j`: Destination router.
    - `k`: Intermediate router.
- New cost = `cost[i][k] + old[k][j]`.
- If the new cost is smaller:
    - Update `dist[i][j]`.
    - Update `next[i][j] = k`.
    - Set `updated = true`.

### Convergence

- Repeat iterations while `updated == true`.
- When no route changes, the algorithm stops.
- Print the final routing tables.

### Printing Tables

- Display destination, minimum cost and next hop.
- If cost is 999 or more, display `INF`.

## 4. Memory tricks

- **cost[][]:** Original link costs.
- **dist[][]:** Best known distances.
- **next[][]:** Which router to visit next.
- **i, j, k:** Source, destination, intermediate router.
- **New cost:** Cost to intermediate + intermediate to destination.
- **Update:** Only when the new cost is smaller.
- **Convergence:** Stop when no updates happen.

## 5. Tiny dry run

Consider:

`A → B = 2`

`B → C = 3`

`A → C = INF`

Initially, A cannot directly reach C.

Through B:

`A → B → C = 2 + 3 = 5`

So A updates its cost to C:

- Old cost = INF
- New cost = 5
- Next hop = B

Final route:

`A → B → C`

Minimum cost = 5.

## 6. One-line recall

"Compare direct cost with the cost through an intermediate router, update the smaller cost, and repeat until no changes occur."
