import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

	/**
	 * The doGet method of the servlet1. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		doPost(request, response);//当采用超链方式跳转到Servlet的时候，数据提交方式为GET
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		
		if(action.equals("add")){
		// 1. 添加数据 读取从add页面传过来的数据，写数据库
		// （1） 读取页面数据
		//int id=Integer.parseInt(request.getParameter("id"));
		String username=request.getParameter("username");//用户名
		String password=request.getParameter("password");//密码
		String password2=request.getParameter("password2");//确认密码
		String email=request.getParameter("email");//电子邮件
		String sex=request.getParameter("sex");//性别
		String birthday=request.getParameter("birthday");//生日
		String hobby=request.getParameter("hobby");//爱好
		String personalDesciption=request.getParameter("personalDesciption");//个人说明
		
		// （2） 写进数据库
		try{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
		    Statement st=conn.createStatement();
		    
		    String addSql="insert into user(name,password,password2,email,sex,birthday,hobby,personalDesciption)"
		                   +" values('"+username+"','"+password+"','"+password2+"','"+email+"','"+sex+"','"+birthday+"','"+hobby+"','"+personalDesciption+"')";
		    	    
		    int  result=st.executeUpdate(addSql);// 表示受影响的行数
		    if(result==0){
		    	out.println("添加失败！");		    	 	
		    }
		    else{
		    	out.println("添加成功！");	
		    	out.println("添加语句："+addSql);
		    	out.println("<a href='Administrator.jsp'>返回到首页</a>");
		    }		
		}catch(Exception e){e.printStackTrace();}
		}// end of add if
		
	
		// 2. 删除数据----------------------------------
				if(action.equals("delete")){
					int id=Integer.parseInt(request.getParameter("id"));
				 //   delete from sttable where sid=100;
					String sqlDelete="delete from user where id="+id;
					Connection conn=null;
					Statement st=null;
					int result=0;
					try{
						 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
						     st=conn.createStatement();
						result=st.executeUpdate(sqlDelete);
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					if(result==0)
					{out.println("删除失败！");
					}
					else{
						out.println("删除成功！"+"<br>");	
						out.println("删除语句："+sqlDelete+"<br>");	
						out.println("<a href='Administrator.jsp'>返回到首页<a>");
						
					}
					
					
				}// end of delete
				
				//3. 修改数据
				if(action.equals("edit")){//修改数据请求，根据修改id查询数据库获得，需要修改的记录
					int id=Integer.parseInt(request.getParameter("id"));
					String sqlquery=" select * from user where id='"+id+"'";
					Connection conn=null;
					Statement st=null;
					ResultSet rs=null;
					try{
						 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
						     st=conn.createStatement();
						     rs=st.executeQuery(sqlquery);
					
					if(rs.next()){//如果查找到需要修改的记录，将查询结果放在request中，以便在修改页面显示要修改的数据
						request.setAttribute("id", rs.getInt("id"));//属性值为Integer类型
						request.setAttribute("username", rs.getString("username"));
						request.setAttribute("password", rs.getString("password"));
						request.setAttribute("password2", rs.getString("password2"));
						request.setAttribute("email", rs.getString("email"));
						request.setAttribute("sex", rs.getString("sex"));
						request.setAttribute("birthday", rs.getString("birthday"));
						request.setAttribute("hobby", rs.getString("hobby"));
						request.setAttribute("personalDesciption", rs.getString("personalDesciption"));	
						//request.setAttribute("action", action);
					    
						// 跳转到修改页面	Edit.jsp	    
						request.getRequestDispatcher("Edit.jsp").forward(request, response);
							}
					}catch(Exception e){
						
					}	
		      
		        
				}// end of edit if
				
				
				 // 修改保存
				if(action.equals("save")){
					System.out.println("save");
					String id=request.getParameter("id");
					String username=request.getParameter("username");
					String password=request.getParameter("password");
					String password2=request.getParameter("password2");
					String email=request.getParameter("email");
					String sex=request.getParameter("sex");
					String birthday=request.getParameter("birthday");
					String hobby=request.getParameter("hobby");
					String personalDesciption=request.getParameter("personalDesciption");
					String sqlEdit="update user set "
					                +"username='"+username+"',"
					              	+"password='"+password+"',"
					              	+"password2='"+password2+"' "
					              	+"email='"+email+"',"
					              	+"sex='"+sex+"',"
					              	+"birthday='"+birthday+"',"
					              	+"hobby='"+hobby+"',"
					              	+"personalDesciption='"+personalDesciption+"',"
		                            +"where id="+id;
					out.println(" 修改语句："+sqlEdit);
					
					Connection conn=null;
					Statement st=null;
					try{
						 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
						     st=conn.createStatement();
						int result=st.executeUpdate(sqlEdit);
						if(result==0){
						out.println("影响行数为0，修改失败！");	
						System.out.println("影响行数为0，修改失败！");	
						}
						else{
							System.out.println("修改成功，影响行数为:"+result);
							out.println("修改成功，影响行数为:"+result);
							//out.println("<br>");
							out.println("<a href='Administrator.jsp'>返回到首页<a>");	
						}
						}catch(Exception e){e.printStackTrace();}	
			
					
				}// end of save if
		
		
		
		
	
	}

}
