import java.util.Stack;

public class ValidBraces {

	public static void main(String[] args) {
		System.out.println(isValidBraces("{[}]"));
	}
	
	static String[] braces(String[] values) {
		String[] results = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			results[i] = isValidBraces(values[i]) ? "YES" : "NO";
		}
		return results;
    }
	
	static boolean isValidBraces(String brace) {
		
		Stack<Character> bracesSoFar = new Stack<Character>();
		for (char c: brace.toCharArray()) {
			if (c == '{' || c == '[' || c == '(')
				bracesSoFar.push(c);
			else {
				if (bracesSoFar.isEmpty())
					return false;

				char poppedChar = bracesSoFar.pop();
				if (c == '}' && poppedChar != '{')
					return false;
				else if (c == ']' && poppedChar != '[')
					return false;
				else if (c == ')' && poppedChar != '(')
					return false;
			}
		}
		
		return bracesSoFar.isEmpty();
	}
}