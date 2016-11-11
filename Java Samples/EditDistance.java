
public class EditDistance {

	public static int min (int x, int y)
	{
		return (x < y) ? x : y;
	} 
	
	public static int min (int x, int y, int z)
	{
		return (min (min (x, y), z));
	}
	
	public static int editDistance (String s1, String s2)
	{
		int m = s1.length();
		int n = s2.length();
		
		int d[][] = new int[m + 1][n + 1];
		
		for (int i = 0; i <= m; i++)
		{
			for (int j = 0; j <= n; j++)
			{
				if (i == 0)
				{
					d[i][j] = j;
				}
				else if (j == 0)
				{
					d[i][j] = i;
				}
				else if (s1.charAt(i - 1) == s2.charAt(j - 1))
				{
					d[i][j] = d[i - 1][j - 1];
				}
				else
				{
					d[i][j] = 1 + min (d[i][j - 1],
										d[i - 1][j],
										(1 + d[i - 1][j - 1]));
				}
			}
		}
		
		return d[m][n];
	}
	
	public static void main(String[] args) {
		System.out.println(editDistance("intentiona", "executions"));
	}
}
