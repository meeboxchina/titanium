package cn.titanium.bidding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**   
 * @Project: cdh4
 * @Title: DBManager.java
 * @Package 
 * @Description: TODO
 * @author sunyu
 * @date Jun 12, 2014 6:55:35 PM
 * @version V1.0   
 */

/**
 * @author sunyu
 *
 */
public class DBManager {

	/**
	 * 
	 */
	private String dbuser;
	private String dbpass;
	private String dbhost;
	private int    dbport;
	private String database;
	private String dburl;
	private Properties prop;
	private Connection conn;
	private String driver = "com.mysql.jdbc.Driver";

	
	
	public DBManager(String dbhost, int dhport, String database, String dbuser, String dbpass) {
		// TODO Auto-generated constructor stub
		this.dbhost = dbhost;
		this.dbport = dhport;
		this.database = database;
		this.dbuser = dbuser;
		this.dbpass = dbpass;
		
		this.dburl = "jdbc:mysql://" + this.dbhost + ":" + this.dbport + "/" + this.database;
	}
	
	public void conncect(){
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println();
			System.out.println(dburl);
			conn = DriverManager.getConnection(this.dburl,this.dbuser, this.dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("connect database error");
			e.printStackTrace();
		}
	}
	
	public String[] query(String sql){
		String[] result = new String[2];
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			 rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.first();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("no result");
			e1.printStackTrace();
		}
		try {
			result[0] = rs.getString("id");
			result[1] = rs.getString("filename");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void insert(String filename){
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into file (filename) values (\"" + filename + "\")";
		System.out.print(sql);
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			 stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
