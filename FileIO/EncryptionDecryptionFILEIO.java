import java.io.*;
import java.util.Random;

public class EncryptionDecryptionFILEIO {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Random r = new Random();
		final int SEED = r.nextInt();
		System.out.println("Encryption w/ seed: "+SEED);
		String pass = "H3Ll0ThiSiSmYPlAiNTexxxxTpASSW0Rd";
		String encrypted = encrypt(pass, SEED);
		outputEncrypted(encrypted);
		inputAndDecrypt(new ObjectInputStream(new FileInputStream("out.dat")), SEED);
	}
	public static void inputAndDecrypt(ObjectInputStream in, int seed) throws ClassNotFoundException, IOException{
		String o = (String)in.readObject();
		System.out.println("Decrypted string: "+decrypt(o,seed));
	}
	public static void outputEncrypted(String enc) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.dat"));
		out.writeObject(enc);
		System.out.println("Encrypted string was written to file.");
		out.close();
	}
	public static String decrypt(String enc, int seed){
		char[] encArr = enc.toCharArray();
		char[] decArr = new char[encArr.length];
		for(int i=0; i<encArr.length; i++){
			decArr[i] = (char)(encArr[i]-seed);
		}
		String decrypted = new String(decArr);
		//System.out.println("Decrypted string as: "+decrypted);
		return decrypted;
	}
	public static String encrypt(String pT, int seed){
		char[] arr = pT.toCharArray();
		char[] encryptedArr = new char[arr.length];
		for(int i=0; i<arr.length; i++){
			char e = (char)(arr[i]+seed);
			encryptedArr[i] = e;
		}
		/*for(char x : encryptedArr)
			System.out.print((char)(x-seed));*/
		String encStr = new String(encryptedArr);
		//System.out.println("Successfully encrypted string as: "+encStr);
		return encStr;
	}
}