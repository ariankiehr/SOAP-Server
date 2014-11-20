package com.soap.ws.hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.ws.hash.IHash")
public class Hash implements IHash {

	@Override
	public String getMD5(String source) {
		String passwordToHash = source;
		String salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}

		String securePassword = getSecurePassword(passwordToHash, salt, "MD5");

		return securePassword;
	}

	private static String getSecurePassword(String passwordToHash, String salt,
			String type) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			//md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	private static String getSalt() throws NoSuchAlgorithmException,
			NoSuchProviderException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}

	@Override
	public String getSHA1(String source) {
		String passwordToHash = source;
		String salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}

		String securePassword = getSecurePassword(passwordToHash, salt, "SHA-1");

		return securePassword;
	}

	@Override
	public String getSHA256(String source) {
		String passwordToHash = source;
		String salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}

		String securePassword = getSecurePassword(passwordToHash, salt,
				"SHA-256");

		return securePassword;
	}

	@Override
	public String getSHA384(String source) {
		String passwordToHash = source;
		String salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}

		String securePassword = getSecurePassword(passwordToHash, salt,
				"SHA-384");
		return securePassword;
	}

	@Override
	public String getSHA512(String source) {
		String passwordToHash = source;
		String salt = null;
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}

		String securePassword = getSecurePassword(passwordToHash, salt,
				"SHA-512");

		return securePassword;
	}

	@Override
	public String getPBKDF2(String source) {

		String originalPassword = source;
		String generatedSecuredPasswordHash = null;
		try {
			generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return generatedSecuredPasswordHash;
	}

	private static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = null;
		try {
			salt = getSalt().getBytes();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
	
	private List<String> solucion;

	@Override
	public List<String> getHashType(String hash) {
		solucion = new LinkedList<String>();
		_16(hash);
		_32(hash);
		_52(hash);
		_64(hash);
		_128(hash);
		_136(hash);
		_148(hash);
		_160(hash);
		_164(hash);
		_192(hash);
		_196(hash);
		_208(hash);
		_224(hash);
		_256(hash);
		_260(hash);
		_312(hash);
		_320(hash);
		_384(hash);
		_392(hash);
		_440(hash);
		_512(hash);
		return solucion;
	}

	private boolean isNumeric(String cadena){
	    if(cadena.matches("\\d*")) return true;
	   	return false;
    }
    
    private boolean isAlpha(String cadena){
		if(cadena.matches("[a-zA-Z]*")) return true;
	   	return false;
    }
    
    private boolean isLower(String cadena){
    	if(cadena.matches("[a-z]*")) return true;
	   	return false;
    }
    
    private boolean isAlphaNumeric(String cadena){
	    if(cadena.matches("\\w*")) return true;
	   	return false;
    }

    private void _16(String hash){
		if( hash.length()==4 && isAlpha(hash)==false && isAlphaNumeric(hash)==true){
			solucion.add("CRC-16");
		}
		if (hash.length()==4 && isNumeric(hash)==false && isAlpha(hash)==false && isAlphaNumeric(hash)==true){
			solucion.add("CRC-16-CCITT");
			solucion.add("FCS-16");
		}
	}

	private void _32(String hash){
	    if(hash.length()==8 && isNumeric(hash)==false && isAlpha(hash)==false && isAlphaNumeric(hash)==true) {
	    	solucion.add("ADLER-32");
        	solucion.add("CRC-32");
        	solucion.add("CRC-32B");
	        solucion.add("XOR-32");
		}
    	if (hash.length()==8 && isNumeric(hash)==true && isAlpha(hash)==false && isAlphaNumeric(hash)==true){
        	solucion.add("GHash-32-3");
        	solucion.add("GHash-32-5");
		}
	}

	private void _52(String hash) {
	    if(hash.length()==13 && isNumeric(hash)==false && isAlpha(hash)==false)
	        solucion.add("DES(Unix)");
	}
	
	private void _64(String hash){
	    if(hash.length()==16 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("MD5(Half)");
	        solucion.add("MD5(Middle)");
	        solucion.add("MySQL");
	    }
	}
	
	private void _128(String hash){
	    if(hash.length()==32 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("Domain Cached Credentials - MD4(MD4(($pass)).(strtolower($username)))");
	        solucion.add("Haval-128");
	        solucion.add("Haval-128(HMAC)");
	        solucion.add("MD2");
	        solucion.add("MD2(HMAC)");
	        solucion.add("MD4");
	        solucion.add("MD4(HMAC)");
	        solucion.add("MD5");
	        solucion.add("MD5(HMAC)");
	        solucion.add("MD5(HMAC(Wordpress))");
	        solucion.add("NTLM");
	        solucion.add("RAdmin v2.x");
	        solucion.add("RipeMD-128");
	        solucion.add("RipeMD-128(HMAC)");
	        solucion.add("SNEFRU-128");
	        solucion.add("SNEFRU-128(HMAC)");
	        solucion.add("Tiger-128");
	        solucion.add("Tiger-128(HMAC)");
	        solucion.add("md5($pass.$salt)");
	        solucion.add("md5($salt.'-'.md5($pass))");
	        solucion.add("md5($salt.$pass)");
	        solucion.add("md5($salt.$pass.$salt)");
	        solucion.add("md5($salt.$pass.$username)");
	        solucion.add("md5($salt.md5($pass))");
	        solucion.add("md5($salt.md5($pass).$salt)");
	        solucion.add("md5($salt.md5($pass.$salt))");
	        solucion.add("md5($salt.md5($salt.$pass))");
	        solucion.add("md5($salt.md5(md5($pass).$salt))");
	        solucion.add("md5($username.0.$pass)");
	        solucion.add("md5($username.LF.$pass)");
	        solucion.add("md5($username.md5($pass).$salt)");
	        solucion.add("md5(md5($pass))");
	        solucion.add("md5(md5($pass).$salt)");
	        solucion.add("md5(md5($pass).md5($salt))");
	        solucion.add("md5(md5($salt).$pass)");
	        solucion.add("md5(md5($salt).md5($pass))");
	        solucion.add("md5(md5($username.$pass).$salt)");
	        solucion.add("md5(md5(md5($pass)))");
	        solucion.add("md5(md5(md5(md5($pass))))");
	        solucion.add("md5(md5(md5(md5(md5($pass)))))");
	        solucion.add("md5(sha1($pass))");
	        solucion.add("md5(sha1(md5($pass)))");
	        solucion.add("md5(sha1(md5(sha1($pass))))");
	        solucion.add("md5(strtoupper(md5($pass)))");
	    }
	}
	
	
	private void _136(String hash){
	    if(hash.length()==34 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true && hash.substring(0,2).equals("0x"))
	        solucion.add("Lineage II C4");
	    if(hash.length()==34 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,3).equals("$H$"))
	        solucion.add("MD5(phpBB3)");
	    if(hash.length()==34 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,3).equals("$1$"))
	        solucion.add("MD5(Unix)");
	    if(hash.length()==34 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,3).equals("$P$"))
	        solucion.add("MD5(Wordpress)");
	}

	private void _148(String hash){
	    if(hash.length()==37 && isNumeric(hash)==false && isAlpha(hash)==false &&  hash.substring(0,4).equals("$apr"))
	        solucion.add("MD5(APR)");
	}

	private void _160(String hash){
	    if(hash.length()==40 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("Haval-160");
	        solucion.add("Haval-160(HMAC)");
	        solucion.add("MySQL5 - SHA-1(SHA-1($pass))");
	        solucion.add("RipeMD-160");
	        solucion.add("RipeMD-160(HMAC)");
	        solucion.add("SHA-1");
	        solucion.add("SHA-1(HMAC)");
	        solucion.add("109220");
	        solucion.add("SHA-1(MaNGOS2)");
	        solucion.add("Tiger-160");
	        solucion.add("Tiger-160(HMAC)");
	        solucion.add("sha1($pass.$salt)");
	        solucion.add("sha1($salt.$pass)");
	        solucion.add("sha1($salt.md5($pass))");
	        solucion.add("sha1($salt.md5($pass).$salt)");
	        solucion.add("sha1($salt.sha1($pass))");
	        solucion.add("sha1($salt.sha1($salt.sha1($pass)))");
	        solucion.add("sha1($username.$pass)");
	        solucion.add("sha1($username.$pass.$salt)");
	        solucion.add("sha1(md5($pass))");
	        solucion.add("sha1(md5($pass).$salt)");
	        solucion.add("sha1(md5(sha1($pass)))");
	        solucion.add("sha1(sha1($pass))");
	        solucion.add("sha1(sha1($pass).$salt)");
	        solucion.add("sha1(sha1($pass).substr($pass,0,3))");
	        solucion.add("sha1(sha1($salt.$pass))");
	        solucion.add("sha1(sha1(sha1($pass)))");
	        solucion.add("sha1(strtolower($username).$pass)");
	    }
	}
	
	private void _164(String hash){
	    if(hash.length()==41 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,1).equals("*"))
	        solucion.add("MySQL 160bit - SHA-1(SHA-1($pass))");
	}
	
	private void _192(String hash){
	    if(hash.length()==48 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("Haval-192");
	        solucion.add("Haval-192(HMAC)");
	        solucion.add("Tiger-192");
	        solucion.add("Tiger-192(HMAC)");
		}
	}
	
	private void _196(String hash){
	    if(hash.length()==49 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false && hash.substring(32,33).equals(":"))
	        solucion.add("md5($pass.$salt)");
	}
	
	private void _208(String hash){
	    if(hash.length()==52 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,5).equals("sha1$"))
	        solucion.add("SHA-1(Django)");
	}
	
	private void _224(String hash){
	    if(hash.length()==56 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("Haval-224");
	        solucion.add("Haval-224(HMAC)");
	        solucion.add("SHA-224");
	        solucion.add("SHA-224(HMAC)");
		}
	}
	        
	private void _256(String hash){
	    if(hash.length()==64 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("SHA-256");
	        solucion.add("SHA-256(HMAC)");
	        solucion.add("Haval-256");
	        solucion.add("Haval-256(HMAC)");
	        solucion.add("GOST R 34.11-94");
	        solucion.add("RipeMD-256");
	        solucion.add("RipeMD-256(HMAC)");
	        solucion.add("115100");
	        solucion.add("115180");
	        solucion.add("SHA-256(md5($pass))");
	        solucion.add("SHA-256(sha1($pass))");
		}
	}
	
	private void _260(String hash){
	    if(hash.length()==65 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(32,33).equals(":"))
	        solucion.add("md5($pass.$salt) - Joomla");
	    if(hash.length()==65 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false && isLower(hash)==false &&  hash.substring(32,33).equals(":"))
	        solucion.add("SAM - (LM_hash:NT_hash)");
	}
	
	private void _312(String hash){
	    if(hash.length()==78 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,6).equals("sha256"))
	        solucion.add("SHA-256(Django)");
	}
	
	private void _320(String hash){
	    if(hash.length()==80 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true) {
	        solucion.add("RipeMD-320");
	        solucion.add("RipeMD-320(HMAC)");
		}
	}
	
	private void _384(String hash){
	    if(hash.length()==96 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true){
	        solucion.add("SHA-384");
	        solucion.add("SHA-384(HMAC)");
	    }
	}
	
	private void _392(String hash){
	    if(hash.length()==98 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,3).equals("$6$"))
	        solucion.add("SHA-256");
	}
	
	private void _440(String hash){
	    if(hash.length()==110 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==false &&  hash.substring(0,6).equals("sha384"))
	        solucion.add("SHA-384(Django)");
	}
	
	private void _512(String hash){
	    if(hash.length()==128 && isNumeric(hash)==false && isAlpha(hash)==false &&isAlphaNumeric(hash)==true) {
	        solucion.add("SHA-512");
	        solucion.add("SHA-512(HMAC)");
	        solucion.add("Whirlpool");
	        solucion.add("Whirlpool(HMAC)");
	    }
	}

	@Override
	public String breakHash(final HashToBreak hashToBreak) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Callable<Object> task = new Callable<Object>() {
		   public Object call() {
		      return breakHashLogic(hashToBreak);
		   }
		};
		
		Future<Object> future = executor.submit(task);
		Object result = "";
		try {
		   result = future.get(hashToBreak.timeout, TimeUnit.MILLISECONDS); 
		} catch (TimeoutException ex) {
			return "CONTROL: Time out";
		} catch (InterruptedException e) {
		   // handle the interrupts
		} catch (ExecutionException e) {
		   // handle other exceptions
		} finally {
		   future.cancel(true);
		   executor.shutdown();
		}
		
		return result.toString();
		
	}
	
	public final String breakHashLogic(final HashToBreak hashToBreak) {
		URL input = getClass().getResource("wordlist.txt");
		
		File wordlist = new File(input.getPath());
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(wordlist));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line;
		try {
			while ((line = br.readLine()) != null) {
				switch( hashToBreak.type ) {
					case MD5 :
						if( hashToBreak.hash.equals( getMD5(line)  ) ) {
							return line;
						}
						break;
						
					case SHA1 :
						if( hashToBreak.hash.equals( getSHA1(line)  ) ) {
							return line;
						}
						break;
				
				}

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "CONTROL: Hash no encontrado";
	}

	
}
