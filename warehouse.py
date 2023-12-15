def count_vec(weg, weg_limit):
    
    sorted_weg = sorted(filter(lambda x: x != 0, weg), reverse=True)
    
    l, r = 0, len(sorted_weg) - 1
    vec = 0
        
    while l <= r:
        if sorted_weg[l] + sorted_weg[r] <= weg_limit:
            r -= 1
        l += 1
        vec += 1

    return vec

# Example usage:
weg = list(map(int, input().split()))
weg_limit = int(input())

a = count_vec(weg, weg_limit)
print(a,end="")