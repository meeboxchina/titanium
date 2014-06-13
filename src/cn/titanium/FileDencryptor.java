/**   
* @Project: cdh4
* @Title: FileDencryptor.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 12, 2014 4:10:55 PM
* @version V1.0   
*/
package cn.titanium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author sunyu
 *
 */
public class FileDencryptor {
	
	private String filename;
	private String seed;
	private boolean del;
	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
    //private static final int CACHE_SIZE = 1024;
	
	public FileDencryptor(String filename, String seed) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
		this.seed = seed;
	}

	// 解密文件
	// 返回加密解密的文件名
	// del 是否删除原文件
	public String dencrypt(boolean del) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{
		File infile = new File(this.filename);
		FileInputStream fis = new FileInputStream(infile);
		
		Date date = new Date();
		long outfilename = date.getTime();
		
		File outfile = new File(infile.getParent() + File.separator + outfilename);
		FileOutputStream fos = new FileOutputStream(outfile);
		
		//获取密钥生成器
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(this.seed.getBytes());
		keygen.init(KEY_SIZE, random);
		
		//生成密钥，可用多种方法保存密钥
		SecretKey key = keygen.generateKey();
		
		//加密
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		CipherInputStream cin = new CipherInputStream(fis, cipher);
		
		byte cache[] = new byte[1024];
		int nRead;
		
		while((nRead=cin.read(cache))!=-1){
			fos.write(cache, 0, nRead);
			fos.flush();
		}
		
		fos.close();
		cin.close();
		
		if(del){
			infile.delete();
		}else{
			
		}
		
		return infile.getParent() + File.separator + outfilename;
	}
}
