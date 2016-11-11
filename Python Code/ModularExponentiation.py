"""
Takes a, x and n as inputs and outputs a to the power n mod x. Modular exponentiation is used for 
optimized computation. It even prints intermediate outputs and the bit form of the supplied number.
"""

def int_to_bin(x):
	s = ''
	if x == 0:
		s = "0"
	while x:
		s = str(x & 1) + s
		x >>= 1
	return s

def mod_exp(a, x, n):
	s = int_to_bin(x)
	print s

	exp = a % n
	result = 1

	for i in reversed(s):
		print exp
		if(i == '1'):
			result = (result * exp) % n
		exp = (exp * exp) % n
	
	return result

# Testing the program 
a = 3
x = 7
n = 10
print(mod_exp(a, x, n))