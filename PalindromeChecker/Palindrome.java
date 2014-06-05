import java.util.Scanner;

public class Palindrome {
	//Checks if passed string is a palindrome.
	//cS is an additional argument for each
	//method and determines if we are checking
	//for case-sensitivity. (Not passing a cS
	//argument assumes we are check w/ case-sensitivity.)
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean isPal = false;
		System.out.print("Enter string to check if palindrome: ");
		String str = s.nextLine();
		System.out.print("Check with case-sensitivity? (Y/N): ");
		char c = s.nextLine().charAt(0);
		if(Character.toUpperCase(c) == 'Y')
			isPal = myPalindrome(str);
		else
			isPal = myPalindrome(str, false);
		if(isPal)
			System.out.println(str+" is palindrome.");
		else
			System.out.println(str+" is not palindrome.");
		
	}
	public static boolean anotherPalindrome(String str){
		String reversed = "";
		for(int i=str.length()-1; i>=0; i--){
			reversed+=str.charAt(i);
		}
		if(reversed.equals(str))
			return true;
		return false;
	}
	public static boolean anotherPalindrome(String str, boolean cS){
		boolean isPal = true;
		if(!cS){
			String reversed = "";
			for(int i=str.length()-1; i>=0; i--){
				reversed+=str.charAt(i);
			}
			if(reversed.equalsIgnoreCase(str))
				return true;
			return false;
		}else{
			isPal = anotherPalindrome(str);
		}
		
		return isPal;
	}
	public static boolean myPalindrome(String str){
		int endPointer = str.length();
		for(int i=0; i<str.length(); i++, endPointer--){
			if(str.charAt(i)!=str.charAt(endPointer-1))
				return false;
		}
		return true;
	}
	public static boolean myPalindrome(String str, boolean cS){
		boolean isPal = true;
		if(!cS){
			int endPointer = str.length();
			for(int i=0; i<str.length(); i++, endPointer--){
				if(Character.toUpperCase(str.charAt(i))!=Character.toUpperCase(str.charAt(endPointer-1))){
					isPal = false;
					break;
				}
			}
		}else{
			isPal = myPalindrome(str);
		}
		return isPal;
	}
	
}