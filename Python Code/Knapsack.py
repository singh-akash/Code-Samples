""" 
The program computes Knapsack problem and returns the max value possible without exceeding the given max weight.
"""

def knapsack(max_weight, element_weights, element_values, number_of_elements):
	A = [[0 for x in range(max_weight + 1)] for x in range(number_of_elements + 1)]

	# Building table A

	for i in range(number_of_elements + 1):
		for j in range(max_weight + 1):
			if (i == 0 or j == 0):
				A[i][j] = 0
			elif element_weights[i - 1] <= j:
				A[i][j] = max(element_values[i - 1] + A[i - 1][j - element_weights[i - 1]], A[i - 1][j])
			else:
				A[i][j] = A[i - 1][j]

	return A[number_of_elements][max_weight]

# Testing the program 
element_values = [30, 14, 16, 9]
element_weights = [6, 3, 4, 2]
max_weight = 10
number_of_elements = len(element_values)
print(knapsack(max_weight, element_weights, element_values, number_of_elements))