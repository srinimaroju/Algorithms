package stringfind;
/**
 * Find a Secret phrase from a list of files.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


import java.util.Base64;

public class KeyFinder {
	public static final String PATH = "/Users/smaroju/Downloads/temp-findthatphrase";
	public int validStrings = 0;
	public int totalStrings = 0;
	public static int dirs = 0;


	public void findYahooCatchPhrase(String dir) {
		this.readFilesRecursivelyAndFind(dir);
	}

	public void readFilesRecursivelyAndFind(String dir) {
		File[] faFiles = new File(dir).listFiles();
		List<String> sub_dirs = new ArrayList<String>();
		List<byte[]> validKeys = new ArrayList<byte[]>();
		List<byte[]> decodedStrings = new ArrayList<byte[]>();
		
		//Only 2 levels, no need of recursion.
		//Collect all sub_dirs first
		for(File file: faFiles){
			if(file.isDirectory()){
				String path = file.getAbsolutePath();
				sub_dirs.add(path);
				dirs++;
			} 
		}
		
		//Loop through all dirs
		for(int i=0;i<sub_dirs.size();i++) {
			String sub_dir = sub_dirs.get(i);
			File[] allFiles = new File(sub_dir).listFiles();
			if(allFiles!=null) {

				for(File file: allFiles){
					if(!file.isDirectory()){
						String path = file.getAbsolutePath();
						String content = readFileContent(path).trim();
						byte[] decoded = Base64.getDecoder().decode(content);
						
						//128 bit key
						if(decoded.length==16) {
							this.validStrings++;
							validKeys.add(decoded);
						} 
						//Encrypted key will be multiples of decoded
						else if(decoded.length %16 ==0) {
							decodedStrings.add(decoded);
						}
						this.totalStrings++;
					} 
				}
			}
		}

		System.out.println("TotalStrings: " + decodedStrings.size());
		System.out.println("TotalValidKeys: " + validKeys.size());
		//int validKeysSize = validKeys.size();
		//long i=0;
		
		String finalResult = "";
		boolean found=false;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(byte[] key : validKeys) {
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			try {
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(byte[]data: decodedStrings) {
				
				if(key!=data) {
					try {
						String result = decrypt(cipher, key, data);
						if(result.contains("Yahoo")) {
							found=true;
							finalResult = result;
							System.out.println("Found" + result); break;
						}
					}
					catch(Exception e) {
						//Do - Nothing if its key padding exception;
					}
				}
			}
			//i++;
			//System.out.println("Itertion" + i + " of " + validKeysSize );
		}
		System.out.println("Found: " + found);
		if(found) {
			System.out.println("Final value:" + finalResult);
		}
	}
	
	public static String decrypt(Cipher cipher, byte[] key, byte[] data) throws Exception {

		try {
			byte[] original = cipher.doFinal(data);
			return new String(original);
		} catch (Exception ex) {
			return "";
		}
	}
	private static String readFileContent(String filePath)
	{
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				contentBuilder.append(sCurrentLine).append("\n");
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
	public static void main(String []args) {
		KeyFinder kf = new KeyFinder();
		kf.findYahooCatchPhrase(PATH);
	}
}
