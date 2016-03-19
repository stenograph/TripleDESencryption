package steganography;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class TripleDESTest {


		public static void main(String[] args) throws Exception {
			  
		    System.out.println("enter a string to encrypt: ");
		    Scanner obj = new Scanner(System.in);
		    String text = obj.nextLine();
		    String codedtext = new TripleDESTest()._encrypt(text,"SecretKey");
		    String decodedtext = new TripleDESTest()._decrypt(codedtext,"SecretKey");
		 	System.out.println(codedtext + " ---> " + decodedtext);
		
		  }
	  
	  
		private String _encrypt(String message, String secretKey) throws Exception {
		
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");;
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			byte[] plainTextBytes = message.getBytes("utf-8");
		    byte[] buf = cipher.doFinal(plainTextBytes);
		    byte [] base64Bytes = Base64.encodeBase64(buf);
		    String base64EncryptedString = new String(base64Bytes);
		    
		    return base64EncryptedString;
		}

		private String _decrypt(String encryptedText, String secretKey) throws Exception {
		
		    byte[] message = Base64.decodeBase64(encryptedText.getBytes("utf-8"));
			
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			
			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] plainText = decipher.doFinal(message);
			
			return new String(plainText, "UTF-8");
		}
	}
