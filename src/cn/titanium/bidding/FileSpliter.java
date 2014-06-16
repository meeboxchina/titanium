/**   
* @Project: cdh4
* @Title: FileSpliter.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 12, 2014 1:30:16 PM
* @version V1.0   
*/
package cn.titanium.bidding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author sunyu
 *
 */
public class FileSpliter {
	private String filename;        //???????????????
	private String filepath;        //????????????
	private File infile;            //????????????
	private String checkcode;       //?????????            
	private FileInputStream fis;
	
	/**
	 * 
	 */
	public FileSpliter(String filename) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
		this.infile = new File(filename);
		this.filepath = infile.getParent();
		try {
			this.fis = new FileInputStream(infile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ????????????????????????????????????
	// ?????????????????????????????? md5+"1" md5+"2"
	public String[] split() throws NoSuchAlgorithmException, IOException{
		
		String parts[] = new String[2];
		String checkcode = this.getCheckcode();
		String filename1 = checksum(checkcode + "1");
		String filename2 = checksum(checkcode + "2");
		
		File outfile1 = new File(this.filepath + File.separator + filename1);
		File outfile2 = new File(this.filepath + File.separator + filename2);
		FileOutputStream fos1 = new FileOutputStream(outfile1);
		FileOutputStream fos2 = new FileOutputStream(outfile2);
		
		long length = this.infile.length();
		byte buffer[] = new byte[1024];
		int nRead = 0;
		
		//?????????????????????????????????
		for(long i=0; i < length/1024/2; i++){
			this.fis.read(buffer);
			fos1.write(buffer);
		}
		
		//????????????????????????????????????
		while((nRead=this.fis.read(buffer))!=-1){
			fos2.write(buffer, 0, nRead);
			fos2.flush();
		}
		
		parts[0] = this.filepath + File.separator + filename1;
		parts[1] = this.filepath + File.separator + filename2;
		
		return parts;
		
	}
	
	// ?????????????????????
	public String getCheckcode() throws NoSuchAlgorithmException, IOException{
		MappedByteBuffer byteBuffer = this.fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, this.infile.length());
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(byteBuffer);
		BigInteger bi = new BigInteger(1, md5.digest());
		this.checkcode = bi.toString();
		return bi.toString();
	}
	
	// ?????????????????????
	public String checksum(String plaintext) throws NoSuchAlgorithmException, IOException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(plaintext.getBytes());
		BigInteger bi = new BigInteger(1, md5.digest());
		return bi.toString();
	}
}
