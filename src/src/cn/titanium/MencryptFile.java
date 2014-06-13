/**   
* @Project: cdh4
* @Title: MencryptFile.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 11, 2014 5:34:49 PM
* @version V1.0   
*/
package cn.titanium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author sunyu
 *
 */
public class MencryptFile {

	/**
	 * @param args
	 */
	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
    private static final int CACHE_SIZE = 1024;
    
	public String filename;
	
	/**
	 * @param filename
	 */
	public MencryptFile(String filename) {
		super();
		this.filename = filename;
	}

	/**
	 * @return String
	 * @throws NoSuchAlgorithmException 
	 */
	private static String getSecretKey(String seed) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance(seed);
		
		keygen.init(KEY_SIZE, random);
		SecretKey secretKey = keygen.generateKey();
		
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//MencryptFile encryptfile = new MencryptFile("/home/sunyu/new");
		//System.out.print(encryptfile.getSecretKey());
		
		String seed = "shanghai";
		
		//获取密钥生成器
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(seed.getBytes());
		keygen.init(KEY_SIZE, random);
		
		//生成密钥，可用多种方法保存密钥
		SecretKey key = keygen.generateKey();
		
		//加密
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		String plaintext = "shanghaibeijing";
		
		byte ptext[] = plaintext.getBytes();
		byte ctext[] = cipher.doFinal(ptext);
		
		
		File in = new File("/home/sunyu/rsyslog.conf");
		FileInputStream fis = new FileInputStream(in);
		
		
		File file = new File("/home/sunyu/cipher");
		FileOutputStream fos = new FileOutputStream(file);
		
		CipherOutputStream cout = new CipherOutputStream(fos, cipher);
		
		byte[] cache = new byte[1024];
		int nRead = 0;
		while ((nRead = fis.read(cache))!=-1){
			cout.write(cache, 0, nRead);
			cout.flush();
		}
		
		cout.close();
		fos.close();
		
	}

	




}
