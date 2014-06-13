/**   
* @Project: cdh4
* @Title: AES.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 11, 2014 11:58:19 AM
* @version V1.0   
*/
package cn.titanium;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author sunyu
 *
 */
public class AES {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		      
		String content = "beijing1111111111adfadfadfssssssssssssasdfffffadfadfadssssssssssssssssssssssssssssssssssssssssssssssssssssssss11";
		String password = "shanghai";
		
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            kgen.init(128, new SecureRandom(password.getBytes()));  
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            byte[] byteContent = content.getBytes("utf-8");  
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
            byte[] result = cipher.doFinal(byteContent);  
            
            
            System.out.print(result[0]); // 加密   
            System.out.print(result[1]); // 加密 
            System.out.print(result[2]); // 加密 
            
            
            
	}

}
