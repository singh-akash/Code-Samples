import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ShortestPathBFS {

	// Variable to access the train schedule to determine the shortest path
	static Hashtable<Integer, ArrayList<TrainNode>> train_schedule = 
			new Hashtable<Integer, ArrayList<TrainNode>>();
	
	// Scanner to take inputs from System.in
	static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) 
	{		
		int destination = sc.nextInt();
		int no_of_train_rides = sc.nextInt();
		int max_time_at_station = sc.nextInt();
		
		// Construct the graph of train rides to find the min path later
		makeGraph(no_of_train_rides);
		
		// Finding the time it takes to reach the destination
		int dest_time = findMinDistance(destination, max_time_at_station);
		
		// Print the output on the screen
		printOutput(dest_time);
	}
	
	/**
	 * Generates the graph for train schedule which later will be traversed
	 * to find the min time to destination.
	 * @param no_of_train_rides - no of available train rides which are to be
	 *    read from the System.in
	 */
	public static void makeGraph(int no_of_train_rides)
	{
		for (int i = 0; i < no_of_train_rides; i++)
		{
			int current_station = sc.nextInt();
			int next_station = sc.nextInt();
			int leave_x = sc.nextInt();
			int arrive_y = sc.nextInt();
			
			ArrayList<TrainNode> train_nodes = train_schedule.get(current_station);
			if (train_nodes == null)
			{
				train_nodes = new ArrayList<TrainNode>();
			}
			
			train_nodes.add(new TrainNode(next_station, leave_x, arrive_y));
			train_schedule.put(current_station, train_nodes);
		}
	}
	
	/**
	 * The algorithm to traverse the graph and find the minimum path and distance
	 * to the destination
	 * @param destination - the destination station
	 * @param max_time_station - max time Jim Bone can wait at a station
	 * @return - the time to reach destination
	 */
	public static int findMinDistance(int destination, int max_time_station)
	{
		// Queue that will be used to traverse the train_schedule graph to find min time
		// to the destination.
		ArrayList<TrainNode> queue = new ArrayList<TrainNode>();
		
		queue.add(new TrainNode(1, 0, 0));
		
		int dest_time = Integer.MAX_VALUE;
		
		for (int i = 0; i < queue.size(); i++)
		{
			TrainNode current_node = queue.get(i);
			
			ArrayList<TrainNode> train_nodes = train_schedule.get(current_node.next_station);
		
			// The next station is destination and reachable and whether the time
			// to reach the destination is lesser than the one already computed.
			if (current_node.next_station == destination &&
				current_node.arrive_y < dest_time)
			{
				dest_time = current_node.arrive_y;
			}
			
			if (train_nodes != null)
			{
				for (TrainNode t : train_nodes)
				{	
					// Push on queue, iff the next train can be boarded from the current station
					// based upon the time Jim Bone can wait at the station and the time at which
					// the train has arrived on the station.
					if (t.leave_x <= (current_node.arrive_y + max_time_station) &&
						t.leave_x >= current_node.arrive_y)
					{
						queue.add(t);
					}
				}
			}
		}
		
		return dest_time;
	}
	
	/** Prints the output on the console
	 * @param dest_time - Time taken to reach the destination
	 */
	public static void printOutput(int dest_time)
	{
		if (Integer.MAX_VALUE == dest_time)
		{
			System.out.println("NO");
		}
		else
		{
			System.out.println("YES " + dest_time);
		}
	}
	
	/**
	 * An Inner class structure to be able to correctly access the train node.
	 */
	public static class TrainNode
	{
		public TrainNode( int next_station, int leave_x, int arrive_y)
		{
			this.next_station = next_station;
			this.leave_x = leave_x;
			this.arrive_y = arrive_y;
		}
		
		int leave_x;
		int arrive_y;
		int next_station;
	}
}