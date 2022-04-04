import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class dbtool {
	public static Connection conn;
	public static Connection st;
	dbtool(){
		//驱动注册
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
		}catch(Exception e){
			
		}
	}
	
	//得到连接对象
	public static Connection getConnection(){
		
		 try{
			 conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
		 }catch(Exception e){
			 //return conn;	
			}
		return conn;
		
	}
	public static Connection getStatement(){
		try{
			st=(Connection) conn.createStatement(); 
		}catch(Exception e){
			 //return st;	
		}
		
		return st;	
	}
	
	//关闭相关资源 Connection tStatement ResultSet
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement st){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
