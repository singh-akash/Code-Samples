import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DwarfProblemPS5 {

	/*
	 * Given a 2D array of numbers, the program finds the maximum sum possible such that
	 * no two entires added were adjacent horizontally or vertically.
	 */
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str[] = br.readLine().split(" ");
		int r = Integer.parseInt(str[0]);
		int c = Integer.parseInt(str[1]);
		
		int[][] values = new int[r][c];
		
		for (int i = 0; i < r; i++)
		{
			str = br.readLine().split(" ");
			
			for (int j = 0; j < c; j++)
			{
				// Storing values in reverse order column wise
				values[i][(c - j - 1)] = Integer.parseInt(str[j]);
			}
		}
		
		// Values read
		
		ArrayList<Integer> mask = getMask(c);
		
		int[][] result_generator = new int[r][mask.size()];
		
		// Adding first mask values sum
		for (int i = 0; i < mask.size(); i++)
		{
			result_generator[0][i] = getMaskAddValue(values[0], mask.get(i), c);
		}
		
		// First row processed. Now continue with remaining rows using DP
		
		for (int i = 1; i < r; i++)
		{
			for (int j = 0; j < mask.size(); j++)
			{
				result_generator[i][j] = getMaskAddValue(values[i], mask.get(j), c) +
						getMaxNonConflictingValFromPrev(result_generator[i - 1],
								mask.get(j), mask, c);
			}
		}
		
		// End of result generation
		
		int max_result = 0;
		for (int i = 0; i < mask.size(); i++)
		{
			if (result_generator[r - 1][i] > max_result)
			{
				max_result = result_generator[r - 1][i];
			}
		}
		System.out.println(max_result);
	}
	
	public static int getMaxNonConflictingValFromPrev(
		int prev_result_generator_vals[], 
		int curr_mask,
		ArrayList<Integer> mask,
		int c)
	{
		int max = 0;
		
		for (int i = 0; i < mask.size(); i++)
		{
			if ((curr_mask & mask.get(i)) == 0)
			{
				// Doesn't conflict
				if (prev_result_generator_vals[i] > max)
				{
					max = prev_result_generator_vals[i];
				}
			}
		}
		
		return max;
	}
	
	public static int getMaskAddValue(int[] values, int mask, int c)
	{
		int sum = 0;
		for (int i = 0; i < c; i++)
		{
			if (isBitSet(mask, i))
			{
				sum += values[i];
			}
		}
		return sum;
	}
	
	public static ArrayList<Integer> getMask(int c)
	{
		ArrayList<Integer> mask = new ArrayList<Integer>();
		
		int max_mask = (int) Math.pow(2, c);
		
		for (int i = 0; i < max_mask; i++)
		{
			String binary_i = Integer.toBinaryString(i);
			if (!binary_i.contains("11"))
			{
				mask.add(i);
			}
		}
		
		return mask;
	}
	
	// Considering bit positions also start from index 0 from the right end
	public static boolean isBitSet(int bit, int bit_posn)
	{
		return ((bit & (int) Math.pow(2, bit_posn)) >> bit_posn) == 1;
	}
}
