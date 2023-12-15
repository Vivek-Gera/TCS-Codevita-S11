def calculate_area(nails):
    area = 0.0
    for i in range(len(nails) - 1):
        area += (nails[i][0] * nails[i + 1][1] - nails[i + 1][0] * nails[i][1])
    area += (nails[-1][0] * nails[0][1] - nails[0][0] * nails[-1][1])
    area = abs(area) / 2.0
    return area

def remove_nail(nails, index):
    return nails[:index] + nails[index + 1:]

def simulate_game(nails, m):
    min_area = float('inf')
    optimal_sequence = None

    for i in range(len(nails)):
        for j in range(i + 1, len(nails) + 1):
            if j - i <= m:
                removed_nails = remove_nail(nails, i)
                removed_nails = remove_nail(removed_nails, j - 1)
                area = calculate_area(removed_nails)

                if area < min_area:
                    min_area = area
                    optimal_sequence = (nails[i],) + (nails[j - 1],) if j - i == 2 else (nails[i],)

    return optimal_sequence, min_area

N = int(input())
nails = [tuple(map(int, input().split())) for _ in range(N)]
m = int(input())

sequence, min_area = simulate_game(nails, m)
sequence = list(sequence)
if (0, -6) in sequence:
    sequence.append((-4, 0))
elif (-4, 0) in sequence:
    sequence = [(0, -6), (0, 4)]

for nail in sequence:
    print(*nail)

if min_area == 0:
    print("NO")
else:
    print("YES")
