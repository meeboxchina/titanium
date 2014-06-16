/**   
* @Project: cdh4
* @Title: FileEncryptor.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 12, 2014 3:07:26 PM
* @version V1.0   
*/
package cn.titanium.bidding;

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
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author sunyu
 *
 */
public class FileEncryptor {

	/**
	 * 
	 */
	private String filename;
	private String seed;
	private boolean del;
	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
    //private static final int CACHE_SIZE = 1024;
	
	public FileEncryptor(String filename, String seed) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
		this.seed = seed;
	}
	
	//del: ????????????????????????????????????
	public String encryt(boolean del) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{
		
		File infile = new File(this.filename);
		FileInputStream fis =  new FileInputStream(infile);
		
		//?????????????????????
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(this.seed.getBytes());
		keygen.init(KEY_SIZE, random);
				
		//?????????????????????????????????????????????
		SecretKey key = keygen.generateKey();
				
		//??????
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		Date date = new Date();
		long outfilename = date.getTime();
		File outfile = new File(infile.getParent() + File.separator + outfilename);
		FileOutputStream fos = new FileOutputStream(outfile);
				
		CipherOutputStream cout = new CipherOutputStream(fos, cipher);
				
		byte[] buffer = new byte[1024];
		int nRead = 0;
		while ((nRead = fis.read(buffer))!=-1){
			cout.write(buffer, 0, nRead);
			cout.flush();
		}
				
		cout.close();
		fos.close();
		if(del){
			infile.delete();
		}else{
			//outfile.renameTo(infile);
		}
		
		return infile.getParent() + File.separator + outfilename;
	}

}
