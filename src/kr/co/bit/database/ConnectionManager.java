package kr.co.bit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

		public Connection getConnetion() {
		/*	
			Connection con = null;
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String driver = "oracle.jdbc.OracleDriver";
			
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,"hr","1234");
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return con;*/
			
			Connection con = null;
				try {
					InitialContext initCtx = new InitialContext();
					Context ctx = (Context)initCtx.lookup("java:comp/env/");
					DataSource ds = (DataSource)ctx.lookup("jdbc/oracle");
					con = ds.getConnection();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			return con;
			
		}
		
		public void connectClose(Connection con,Statement stmt,ResultSet rs) {
			if(rs!=null) {
				
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (stmt!=null) {
				
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			if (con!=null) {
				
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			/* String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String driver =  "oracle.jdbc.OracleDriver";   driver에  패키지명(주소) 
			Class.forName(driver);  // 인스턴스 자동으로 만들어냄   인스턴스 만드는 방법 1.new 인스턴스();   2. getInstance(); 3. 클론?  4. class.forname(string)
					// 드라이버를 만들어 주었고, */
		}
}
