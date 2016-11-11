import java.util.PriorityQueue;
import java.util.Queue;

public class StringSubsequences {
	public static void main(String[] args) {
		String s = buildSubsequences("abc");
		System.out.println(s);
	}
	
	static Queue<String> resultStrings = new PriorityQueue<String>();
	
	static String buildSubsequences(String s)
	{
		build(s, "");
		resultStrings.poll();
		return resultStrings.poll();
	}
	
	public static void build(String s, String prefix) {
		if (s == null || s.equals("")) {
			resultStrings.offer(prefix);
			return;
		}
		build (s.substring(1), prefix + s.charAt(0));
		build (s.substring(1), prefix);
	}
}
