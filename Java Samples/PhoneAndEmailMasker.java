import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class PhoneAndEmailMasker {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line.substring(0, 2) + getMaskedString(line.substring(2)));
		}
	}
	
	public static String getMaskedString(String s) {
		int index = s.lastIndexOf('@');
		return (index == -1) ? getMaskedPhoneNumber(s) :
								getMaskedEmailAddress(s, index);
	}
	
	public static String getMaskedPhoneNumber(String phoneNum) {
		StringBuffer parsedPhoneNum = new StringBuffer();
		for (char c: phoneNum.toCharArray()) {
			if ((c >= 48 && c <= 57) || c == '+') {
				parsedPhoneNum.append(c);
			}
		}
		phoneNum = parsedPhoneNum.toString();
		
		StringBuffer result = new StringBuffer();
		
		if (phoneNum.length() > 10) { 
			result.append('+');
			for (int i = 0; i < phoneNum.length() - 11; i++)
				result.append('*');
			result.append('-');
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				result.append('*');
			}
			result.append('-');
		}
		result.append(phoneNum.substring(phoneNum.length() - 4));
		
		return result.toString();
	}
	
	public static String getMaskedEmailAddress(String email, int index) {
		StringBuffer result = new StringBuffer();
		result.append(email.charAt(0));
		for (int i = 0; i < 5; i++) {
			result.append('*');
		}
		result.append(email.charAt(index - 1));
		result.append(email.substring(index));
		return result.toString();
	}
}