A, B, C = [int(l) for l in input().split()]


if A==B and A==C:
    r = "*"
elif A==B and A!=C:
    r = "C"
elif C==B and C!=A:
    r = "A"
elif A==C and A!=B:
    r = "B"

print(r)
