package Encryption;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
* Decrypts strings that have been encrypted. Using public 
* and/or private keys
*/
public class Decryptor {
	
	private int privateKey = 5;
	private String keyWord = "bacon";
	private int aidenCipherKey = 5;
	private int decryptorKey = 0;
	
	/*
	 * Decrypts a method using key integer as offset. iterates over message and substracts
	 * corresponding key to each letter in message.
	 * @param message String containing message to be decrypted
	 * @return result decrypted string
	 */
	public String decryptUsingNumber(String message) throws IllegalArgumentException{
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		message = message.toLowerCase();
		String result = "";
		for(int i = 0; i < message.length(); i++){
			if((int)message.charAt(i) != 32){
				result+=(char)(97+(message.charAt(i)+(26-privateKey)-97)%26);
			}
			else{
				result+=" ";
			}
		}
		return result;
	}
	
	/*
	 * decrypts a String of int values that have been encrypted by "Aiden Cipher".
	 * Method uses BigInteger and BigDecimal class so it could feasibly be able to 
	 * deal with very large numbers.
	 * @param message encryption to be decrypted
	 * @return original message
	 */
	public String decryptAidenCipher(String message){
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		String result = "";
		String [] strings = message.split(":");
		for(int i = 0; i < strings.length; i++){
			if(strings[i].charAt(0) != ' '){
				BigInteger b1 = new BigInteger(new BigDecimal(strings[i]).toPlainString());
				BigInteger b2 = new BigInteger(Factorial(aidenCipherKey)+"");
				result+= (char)squareRoot(b1.divide(b2), 3);
			}
			else{
				result+=" ";
			}
		}
		return result;
	}
	
	public int Factorial(int num){
		int result = 1;
		for(int i  = 0; i < num-1; i++){
			result*=num-i;
		}
		return result;
	}
	
	public int squareRoot(BigInteger b, int power){
		BigInteger b3 = new BigInteger("0");
		for(int i = 0; i < b.intValue(); i++){
			BigInteger b2 = new BigInteger(i+"");
			if(Math.pow(i, power) == b.intValue() && b.mod(b2).compareTo(b3) == 0){
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * Decrypts a method using another string as offset. iterates over message and substracts
	 * corresponding integer value of each letter in keyWord to each letter in message.
	 * keyWord repeats if message is longer than it.
	 * @param message String containing message to be decrypted
	 * @return result decrypted string
	 */
	public String decryptUsingString(String message) throws IllegalArgumentException{
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		message = message.toLowerCase();
		String result = "";
		int keyWordInteger = 0;
		for(int i = 0, j = 0; i < message.length(); i++, j++){
			if((int)message.charAt(i) != 32){
				keyWordInteger = (int)(keyWord.charAt(j%keyWord.length())-96);//gets corresponding keyword int value
				result+=(char)(97+(message.charAt(i)+(26-keyWordInteger)-97)%26);//adds value to char, reseting to beginning of alphabet if necessary
			}
			else{
				result+=" ";
				j--;
			}
		}
		return result;
	}
	
	/*
	 * Generates e and n variable to be given to encryptor. 
	 * @ param e random exponent that has no common factors with n
	 * @ param n phi function evaluation of two randomly generated prime numbers
	 */
	public int [] generatePublicKeys(){
		while(true){
			int lowerBound = (int)(3+(3*Math.random()))*10;
			int p1 = getRandomPrime(lowerBound, lowerBound + 5);
			int p2 = getRandomPrime(lowerBound + 6, lowerBound + 10);
			int n = p1*p2;
			int phiOfN = (p1-1)*(p2-1);
			int e = (int)(3*Math.random());
			if(e <= 1){
				e = 3;
			}
			else if(e <= 2 && e > 1){
				e = 5;
			}
			else if(e <= 3 && e > 2){
				e = 7;
			}
			int k = 1;
			while((k*phiOfN+1) % e != 0){
				k++;
				if(k > 50){
					break;
				}
			}
			if((k*phiOfN+1) % e != 0){
				continue;
			}
			decryptorKey = (k*phiOfN+1)/e;
			return new int[]{e, n};
		}
	}
	
	public String decryptRSA(String numbers, int n){
		String [] rawLetters = numbers.split(" ");
		int [] Letters  = new int [rawLetters.length];
		String message = "";
		for(int i = 0; i < rawLetters.length; i++){
			Letters[i] = (int)(Math.pow(Double.parseDouble(rawLetters[i]), decryptorKey)%n);	
			System.out.println(Letters[i]);
		}
		for(int i  = 0; i < Letters.length; i++){
			message+=(char)Letters[i];
		}
		return message;
	}
	
	/*
	 * Finds random prime number in the given interval.
	 * @param low bottom of range of desired values
	 * @param top top of range of desired values
	 */
	public int getRandomPrime(int bottom, int top){
		int prime = -1;
		outerloop:
		for(int i = bottom; i <= top; i++){
			boolean primeBool = true;
			for(int j = i-1; j > 1; j--){
				if(i%j == 0){
					primeBool = false;
				}
			}
			if(primeBool){
				prime = i;
				break outerloop;
			}
		}
		return prime;
	}
}
