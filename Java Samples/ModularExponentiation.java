import java.math.BigInteger;
import java.util.Scanner;

public class ModularExponentiation {
	
	/**
	 * This function computes modular exponentiation of the expression (a ^ x) mod n.
	 * @param a - a term of the above expression  
	 * @param x - x term of the above expression
	 * @param n - n term of the above expression
	 * @return - The result of the modular exponentiation on the expression (a ^ x) mod n.
	 */
	public static BigInteger computeModularExponentiation(BigInteger a, BigInteger x, BigInteger n)
	{
		String binary_string = x.toString(2);
		
		// Output of the binary string
		System.out.println(binary_string);
	
		BigInteger modular_remainder = a.mod(n);
		BigInteger result = BigInteger.ONE;
		
		for (int i = binary_string.length() - 1; i >= 0; i--)
		{
			if (isBitSet(binary_string, i))
			{
				result = result.multiply(modular_remainder).mod(n);
			}
			// Output of the iteration.
			System.out.println(modular_remainder);
			modular_remainder = modular_remainder.multiply(modular_remainder).mod(n);
		}
		
		// Returning the final result.
		return result.mod(n);
	}
	
	/**
	 * The function checks whether a bit position in the binary 
	 * string is set or not 
	 * @param binary_string - a non null string made of of binary digits
	 * @param bit_position - the index position of the binary string which is to be evaluated
	 * 						 whether it is set to 1 or not. Should be in the range of positions
	 * 						 available in the string..
	 * @return a boolean to indicate if the requested bit position is set or not
	 */
	public static boolean isBitSet(String binary_string, int bit_position)
	{
		return ((binary_string != null) &&
				(binary_string.length() > bit_position) &&
				('1' == (binary_string.charAt(bit_position))));
	}
	
	/**
	 * Takes input from System.in in a single line of three long variables,
	 * computes modular exponentiation, and returns the final result.
	 */
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		BigInteger a = sc.nextBigInteger();
		BigInteger x = sc.nextBigInteger();
		BigInteger n = sc.nextBigInteger();
		
		//Printing the result of modular exponentiation.
		System.out.print(computeModularExponentiation(a, x, n));
	}
}