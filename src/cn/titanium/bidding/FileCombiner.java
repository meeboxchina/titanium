/**   
* @Project: cdh4
* @Title: FileCombiner.java
* @Package com.madhouse.devops.cdh4
* @Description: TODO
* @author sunyu
* @date Jun 12, 2014 2:35:04 PM
* @version V1.0   
*/
package cn.titanium.bidding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author sunyu
 *
 */
public class FileCombiner {
	private String[] paths = new String[2];
	private String path1;
	private String path2;
	private String outpath;

	/**
	 * 
	 */
	public FileCombiner(String[] paths) {
		// TODO Auto-generated constructor stub
		this.paths = paths;
		this.path1 = paths[0];
		this.path2 = paths[1];
	}
	
	public FileCombiner(String path1, String path2) {
		// TODO Auto-generated constructor stub
		this.path1 = path1;
		this.path2 = path2;
	}
	
	public String combiner() throws IOException{
		
		File infile1 = new File(this.path1);
		File infile2 = new File(this.path2);
		
		FileInputStream fis1 = new FileInputStream(infile1);
		FileInputStream fis2 = new FileInputStream(infile2);
		
		Date date = new Date();
		long outfilename = date.getTime();
		File outfile = new File(infile1.getParent() + File.separator + outfilename);
		FileOutputStream fos = new FileOutputStream(outfile);
		
		byte buffer[] = new byte[1024];
		int nRead = 0;
		while((nRead=fis1.read(buffer))!=-1){
			fos.write(buffer, 0, nRead);
			fos.flush();
		}
		fis1.close();
		
		while((nRead=fis2.read(buffer))!=-1){
			fos.write(buffer, 0, nRead);
			fos.flush();
		}
		fis2.close();
		fos.close();
		
		return infile1.getParent() + File.separator + outfilename;
	}

}
