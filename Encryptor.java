package Encryption;

/* TODO:
* make a padding for encrypted number stream since patterns may allow for it to be cracked:
*	- maybe an invisible escape character? I think that could be caught though
*	- hashcode
*	- each number could be preceded by a binary number (inverted every other time and increasing by two) not very robust, but hard to decode
*	- it has sto be something that changes each time
* finish RSA encryption/decryption, maybe make methods more efficient
* organize code and methods, add javadocs

/*
* Encrypts strings using various techniques and output strings and 
* public keys for the decryptor to use.
*/
public class Encryptor {
	
	private int privateKey = 5;
	private String keyWord = "bacon";
	private int aidenCipherKey = 5;
	
	/*
	 * Encrypts a method using key integer as offset. iterates over message and adds
	 * corresponding key to each letter in message.
	 * @param message String containing message to be encrypted
	 * @return result encrypted string
	 */
	public String encryptUsingNumber(String message) throws IllegalArgumentException{
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		message = message.toLowerCase();
		String result = "";
		for(int i = 0; i < message.length(); i++){
			if((int)message.charAt(i) != 32){
				result+=(char)(97+(message.charAt(i)+privateKey-97)%26);
			}
			else{
				result+=" ";
			}
		}
		return result;
	}
	
	/*
	 * Encrypts a method using another string as offset. iterates over message and adds
	 * corresponding integer value of each letter in keyWord to each letter in message.
	 * keyWord repeats if message is longer than it.
	 * @param message String containing message to be encrypted
	 * @return result encrypted string
	 */
	public String encryptUsingString(String message) throws IllegalArgumentException{
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		message = message.toLowerCase();
		String result = "";
		int keyWordInteger = 0;
		for(int i = 0, j = 0; i < message.length(); i++, j++){
			if((int)message.charAt(i) != 32){
				keyWordInteger = (int)(keyWord.charAt(j%keyWord.length())-96);//gets corresponding keyword int value
				result+=(char)(97+(message.charAt(i)+keyWordInteger-97)%26);//adds value to char, reseting to beginning of alphabet if necessary
			}
			else{
				result+=" ";
				j--;
			}
		}
		return result;
	}
	
	/*
	 * Calculates factorial of a number
	 */
	public int Factorial(int num){
		int result = 1;
		for(int i = 0; i < num; i++){
			result*=num-i;
		}
		return result;
	}
	
	/*
	 * encrypts message by cubing the int value of each letter in the message then
	 * multiplying it by the factorial of the public key of the system.
	 * @param message string to be encrypted
	 * @return message encrypted
	 */
	public String encryptAidenCipher(String message){
		if(message.length() == 0){
			throw  new IllegalArgumentException("String length is 0");
		}
		message = message.toLowerCase();
		String result = "";
		for(int i = 0; i < message.length(); i++){
			if((int)message.charAt(i) != 32){
				result+=Math.pow((int)(message.charAt(i)),3)*Factorial(aidenCipherKey);//algorithm that calculates encryption value for each letter
			}
			else{
				result+=" ";
			}
			result+=":";
		}
		return result;
	}
	
	/*
	 * lists primes from given lower bound to given higher bound
	 */
	public int [] listPrimes(int bottom, int top){
		int [] primes = new int[top-bottom];
		int k = 0;
		for(int i = bottom; i <= top; i++){
			boolean primeBool = true;
			for(int j = i-1; j > 1; j--){
				if(i%j == 0){
					primeBool = false;
				}
			}
			if(primeBool){
				primes[k] = i;
				k++;
				
			}
		}
		int count = 0;
		for(int i = 0; i < primes.length; i++){
			if(primes[i] != 0){
				count++;
			}
		}
		int [] newPrimes = new int[count];
		for(int i = 0, g = 0; i < primes.length; i++){
			if(primes[i] != 0){
				newPrimes[g] = primes[i];
				g++;
			}
		}
		return newPrimes;
	}
	
	/*
	 * Asserts that String contains only letters and spaces
	 * @param message message to be verified
	 * @return false if parameters aren't met, true otherwise
	 */
	public static boolean isString(String message){
		for(int i = 0; i < message.length(); i++){
			if(((int)message.charAt(i) < 65 || (int)message.charAt(i) > 90) && (int)message.charAt(i) != 32){
				return false;
			}
		}
		return true;
	}
	
	/*
	 * RSA encryption using prime factorization, the Phi function and Euler's theorem.
	 * Encrypts message base on two public keys given by the private key holder. Through
	 * a mathematical algorithm message is encoded into a group of numbers separated by 
	 * a space. 
	 * @param message message to be encrypted
	 * @ e variable used in equation as exponent, given by decrypting system
	 * @ n variable used in equation as modulo character
	 * return encrypted strings of numbers separated by a space
	 */
	public String encryptRSA(String message, int e, int n){
		String code = "";
		for(int i = 0; i < message.length(); i++){
			code+=(int)(Math.pow(message.charAt(i), e))%n+" ";
		}
		return code.trim();
	}
}
