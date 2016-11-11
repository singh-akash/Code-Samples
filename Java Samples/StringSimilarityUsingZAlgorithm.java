
public class StringSimilarityUsingZAlgorithm {
	
	public static void main(String[] args)
	{
		System.out.println(stringSimilarity("bcdedeccaabdaebdddaeedabedccdddccbbaaadcbbabccbaadbbbeddecacddbababbabadcbbbacecdaee"));
	}
	
	static long stringSimilarity(String a)
	{
		return getZarr(a + "$" + a, new int[2 * a.length() + 1]);
	}
	
	static long getZarr(String str, int Z[])
	{
	    int n = str.length();
	    int L, R, k;
	    long count = 0;
	    // L & R make a window which match with prefix of s
	    L = R = 0;
	    for (int i = 1; i < n; ++i)
	    {
	        // Compute Z[i] using naive way as nothing matches.
	        if (i > R)
	        {
	            L = R = i;
	 
	            while (R<n && str.charAt(R-L) == str.charAt(R))
	            {
	                R++;
	            }
	            
	            Z[i] = R-L;
	            R--;
	        }
	        else
	        {
	            // k gives the number which matches in the window of L and R
	            k = i-L;
	 
	            if (Z[k] < R-i+1)
	            {
	                 Z[i] = Z[k];
	            }	 
	            else
	            {
	                L = i;
	                while (R<n && str.charAt(R-L) == str.charAt(R))
	                {
	                    R++;
	                }
	                
	                Z[i] = R-L;
	                R--;
	            }
	        }
	    }
	    
	    for (int i = str.length() / 2; i < str.length(); i++)
	    {
	    	count += Z[i];
	    }
	    
	    return count;
	}
}