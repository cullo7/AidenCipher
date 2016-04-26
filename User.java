package Encryption;

import java.math.BigInteger;
import java.util.Arrays;

/*
* represents an applicant using the encryption and decryption classes.
*/
public class User {

	public static void main(String [] args){
		Encryptor encrypt = new Encryptor();
		Decryptor decrypt = new Decryptor();
		
		System.out.println("================== CAESAR CIPHER ==================\n");
		System.out.println("Message to Encrypt: all quiet on the western front");
		System.out.println("Number offset: 5");
		System.out.println("String offset: bacon");
		String encryption1 = encrypt.encryptUsingNumber("all quiet on the western front");
		System.out.println("Encryption using Number Offset: "+encryption1);
		String encryption2 = encrypt.encryptUsingString("all quiet on the western front");
		System.out.println("Encryption using String Offset: "+encryption2);
		String decryption1 = decrypt.decryptUsingNumber(encryption1);
		System.out.println("Decryption using Number Offset: "+decryption1);
		String decryption2 = decrypt.decryptUsingString(encryption2);
		System.out.println("Decryption using String Offset: "+decryption2);
		
		System.out.println("\n================== AIDEN CIPHER ==================\n");
		
		System.out.println("Message to Encrypt: all quiet on the western front");
		System.out.println("Private Key: 5");
		String encryption3 = encrypt.encryptAidenCipher("all quiet on the western front");
		System.out.println("Encryption: "+encryption3);
		String decryption3 = decrypt.decryptAidenCipher(encryption3);
		System.out.println("Decryption: "+decryption3);

		System.out.println("\n================== RSA ENCRYPTION ==================\n");
		
		int [] array = decrypt.generatePublicKeys();
		System.out.println(Arrays.toString(array));
		String encryption = encrypt.encryptRSA("Ground control to major Tom", array[0], array[1]);
		System.out.println(encryption);
		String decryption = decrypt.decryptRSA(encryption, array[1]);
		System.out.println(decryption);	
	}
}
