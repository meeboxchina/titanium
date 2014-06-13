package cn.titanium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**   
 * @Project: cdh4
 * @Title: Mdecypt.java
 * @Package 
 * @Description: TODO
 * @author sunyu
 * @date Jun 12, 2014 11:25:11 AM
 * @version V1.0   
 */
import java.io.FileNotFoundException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author sunyu
 *
 */
public class Mdecypt {

	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
    private static final int CACHE_SIZE = 1024;
    
    
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("/home/sunyu/cipher");
		FileInputStream fis = new FileInputStream(file);
		
		String seed = "shanghai1";
		
		//获取密钥生成器
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(seed.getBytes());
		keygen.init(KEY_SIZE, random);
		
		//生成密钥，可用多种方法保存密钥
		SecretKey key = keygen.generateKey();
		
		//加密
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		CipherInputStream cin = new CipherInputStream(fis, cipher);
		
		
		File de = new File("/home/sunyu/de");
		FileOutputStream fos = new FileOutputStream(de);
		
		byte cache[] = new byte[1024];
		int nRead;
		
		while((nRead=cin.read(cache))!=-1){
			fos.write(cache, 0, nRead);
			fos.flush();
		}
		
		fos.close();
		cin.close();
	}

}
