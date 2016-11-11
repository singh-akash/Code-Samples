"""
Given students in a row having different scores, the problem focuses at 
alloting candies to students, such that if a student has a higher score 
than the student sitting adjacent to him, should get higher number of candies
than his him and we need to minimize the candies being given out.
"""

n = int(raw_input())
score = [int(raw_input()) for i in range(n)]

candy_count = [1] * n

for i in range(1, n):
	candy_count[i] = (candy_count[i-1] + 1) if (score[i] > score[i - 1]) else 1

for i in reversed(range(n - 1)):
	if score[i] > score[i + 1] and candy_count[i] <= candy_count[i + 1]:
		candy_count[i] = candy_count[i + 1] + 1

print(sum(candy_count))