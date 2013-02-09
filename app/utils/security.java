package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class security {
	
	//Create SHA256 hexadecimal Hash
	public static String SHA256HexHash(String password) {
	    MessageDigest sha256 = null;
	    
	    try {
	    	sha256 = MessageDigest.getInstance("SHA-256");        
	    }
	    catch (NoSuchAlgorithmException e) {
	    	System.err.print(e);
	    	System.exit(2); //dirty but I don't wanna throw an Exc.
	    }
	    
	    byte[] passBytes = password.getBytes();
	    byte[] passHash = sha256.digest(passBytes);
	    
	    StringBuffer HexString = new StringBuffer();
	    for(byte b : passHash) 
	        HexString.append(String.format("%02x", b));
	    
	    return HexString.toString();
	}

}
