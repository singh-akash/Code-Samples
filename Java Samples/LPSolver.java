import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ilog.concert.*;
import ilog.concert.IloCopyManager.Check;
import ilog.cplex.*;

public class LPSolver {

	/* Linear programming solver which takes input from console
	 * and maximises the value.
	 */
	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		
		s = br.readLine().split(" ");
		
		double[] objvals = new double[n];
		double[] lb = new double[n];
		double[] ub = new double[n];
		
		// set bounds
		
		for (int i = 0; i < n; i++)
		{
			objvals[i] = Double.valueOf(s[i]);
			lb[i] = 0.0;
			ub[i] = Double.MAX_VALUE;
		}
		
		// Bounds Set
		
		// Scan for constraints
		
		int constraints [][] = new int[c][n + 1]; 
		
		for (int i = 0; i < c; i++)
		{
			s = br.readLine().split(" ");
			
			for (int j = 0; j < n + 1; j++)
			{
				constraints [i][j] = Integer.parseInt(s[j]);
			}
		}
		
		// Constraints done. Solve 
		
		try 
		{
			IloCplex cplex = new IloCplex();
			IloNumVar[] x = cplex.numVarArray(n, lb, ub);
			
			cplex.addMaximize(cplex.scalProd(x, objvals));
			
			for (int i = 0; i < c; i++)
			{
				IloNumExpr curr_expr[] = new IloNumExpr[n];
				
				for (int j = 0; j < n; j++)
				{	
					curr_expr[j] = cplex.prod(constraints[i][j], x[j]);
				}
				
				cplex.addLe(cplex.sum(curr_expr), constraints[i][n]);
			}
			
			if (cplex.solve()) 
			{
				// Printing as double casted to long for understanding of absolute values
				System.out.println("Maximized Value = " + (long) cplex.getObjValue());
				double[] val = cplex.getValues(x);
				int ncols = cplex.getNcols();
				for (int j = 0; j < ncols; ++j)
				{
					// Printing as double casted to long for understanding of absolute values
					System.out.println("Objective Value of the element " +  j + " : " +  (long) val[j]);
				}
			}
			cplex.end();
		}
		catch (IloException e)
		{
			System.err.println("Concert exception '" + e + "' caught");
		}
	}
}