import java.util.Scanner;


public class CandyProblem {

	/*
	 * Given students in a row having different scores, the problem focuses at 
	 * alloting candies to students, such that if a student has a higher score 
	 * than the student sitting adjacent to him, should get higher number of candies
	 * than his him and we need to minimize the candies being given out.
	 */

	public static int allotCandies(int[] score) {

	    int n = score.length;
	    int[] candy_count = new int[n];

	    candy_count[0] = 1;
	    for (int i = 1; i < n; ++i) 
	    {
	    	candy_count[i] = (score[i] > score[i - 1]) ? candy_count[i - 1] + 1 : 1;
	    }

	    int final_amount = candy_count [n - 1];
	    for (int i = n - 2; i >= 0; i--) 
	    {
	        if (score[i] > score[i + 1] && candy_count[i] <= candy_count[i + 1]) 
	        {
	            candy_count[i] = candy_count[i + 1] + 1;
	        }
	        final_amount += candy_count[i];    
	    }
	    return final_amount;
	}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] score = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			score[i] = sc.nextInt();
		}
		
		System.out.println(allotCandies(score));
	}
}
