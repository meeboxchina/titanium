/**   
* @Project: titanium
* @Title: Test.java
* @Package cn.titanium
* @Description: TODO
* @author sunyu
* @date Jun 13, 2014 3:57:15 PM
* @version V1.0   
*/
package cn.titanium;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.xml.XmlDocument;

/**
 * @author sunyu
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MFile mfile = new MFile();
		mfile.setFilename("shanghai");
		
		Configuration cfg = new Configuration();
		
		//XmlDocument xml = new XmlDocument("conf/hibernate.cfg.xml");
		
		//cfg.add("conf/hibernate.cfg.xml");
        SessionFactory sf = cfg.configure().buildSessionFactory();
         
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(mfile);
        session.getTransaction().commit();
        session.close();
        sf.close(); 
	}

}
