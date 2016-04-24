package Encryption;

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
}
