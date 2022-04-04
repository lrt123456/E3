import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

public class dbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//如果要输入中文，就要加上此代码，防止乱码
		response.setContentType("text.html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//通过response获取一个输出流（这个流可以向浏览器发送数据)
		PrintWriter out=response.getWriter();
		doPost(request, response);//当采用超链方式跳转到Servlet的时候，数据提交方式为GET
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String action=request.getParameter("action");

		if(action.equals("add")){
			// 1. 添加数据 读取从add页面传过来的数据，写数据库
			// （1） 读取页面数据
			//int id=Integer.parseInt(request.getParameter("id"));
			String v=request.getParameter("v");
			String weight=request.getParameter("weight");
			String na=request.getParameter("na");
			String ratio=request.getParameter("ratio");

			//使用data1DAO添加数据
			Data1 p1=new Data1 ();
			p1.setV(v);
			p1.setWeight(weight);
			p1.setNa(na);
			p1.setRatio(ratio);

			try{
				Data1DAO.insert(p1);
			}catch(SQLException e){
				e.printStackTrace();
			}

			Statement st=null;
			PreparedStatement pst=null;

			// （2） 写进数据库
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
				st=conn.createStatement();

				//使用数据库工具对象
				//st=dbtool.getStatement();


				String addSql="insert into data1(v,weight,na,ratio)"
						+" values('"+v+"','"+weight+",'"+na+"','"+ratio+"')";

				int  result=st.executeUpdate(addSql);// 表示受影响的行数

				//使用PreparStatement
				String addsql="insert into table_product(name,price,description) values(?,?,?)";
				//pst=conn.preparedStatement(addsql);//预编译SQL语句
				//设置参数值


				pst.setString(1,v);
				//pst.setInt(2, id);
				pst.setString(3, weight);
				pst.setString(4, na);
				pst.setString(5, ratio);


				if(result==0){
					out.println("添加失败！");		    	 	
				}
				else{
					out.println("添加成功！");	
					out.println("添加语句："+addSql);
					out.println("<a href='index.jsp'>返回到首页</a>");
				}		
			}catch(Exception e){e.printStackTrace();}

			//释放资源
			//dbtool.closeConnecteion(conn);
			//dbtool.closeConnection(st);
			//dbtool.closeConnecteion(rs);
		}// end of add if


		// 2. 删除数据----------------------------------
		if(action.equals("delete")){
			int id=Integer.parseInt(request.getParameter("id"));
			//   delete from sttable where sid=100;
			String sqlDelete="delete from data1 where id="+id;
			Connection conn=null;
			Statement st=null;
			int result=0;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
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
				out.println("<a href='index.jsp'>返回到首页<a>");

			}


		}// end of delete

		//3. 修改数据
		if(action.equals("edit")){//修改数据请求，根据修改id查询数据库获得，需要修改的记录
			int id=Integer.parseInt(request.getParameter("id"));
			String sqlquery=" select * from data1 where id='"+id+"'";
			Connection conn=null;
			Statement st=null;
			ResultSet rs=null;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
				st=conn.createStatement();
				rs=st.executeQuery(sqlquery);

				if(rs.next()){//如果查找到需要修改的记录，将查询结果放在request中，以便在修改页面显示要修改的数据
					request.setAttribute("id", rs.getInt("id"));//属性值为Integer类型
					request.setAttribute("v", rs.getString("v"));
					request.setAttribute("weight", rs.getInt("weight"));
					request.setAttribute("na", rs.getString("na"));	
					request.setAttribute("ratio", rs.getString("ratio"));	

					// 跳转到修改页面	edit.jsp	    
					request.getRequestDispatcher("edit.jsp").forward(request, response);
				}
			}catch(Exception e){

			}	
		}// end of edit if


		// 修改保存
		if(action.equals("save")){
			System.out.println("save");
			String id=request.getParameter("id");
			String v=request.getParameter("v");
			String weight=request.getParameter("weight");
			String na=request.getParameter("na");
			String ratio=request.getParameter("ratio");
			//String s= update sttable set sname='张三', smajor='计算机'，sdecription='兴趣广泛' where id=100;
			String sqlEdit="update data1 set "+" v='"+v+"',"
					+"weight='"+weight+"',"
					+"na='"+na+"',"
					+"na='"+ratio+"',"
					+"where id="+id;
			out.println(" 修改语句："+sqlEdit);

			Connection conn=null;
			Statement st=null;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
				st=conn.createStatement();
				int result=st.executeUpdate(sqlEdit);
				if(result==0){
					out.println("影响行数为0，修改失败");	
					System.out.println("影响行数为0，修改失败");	
				}
				else{
					System.out.println("修改成功，影响行数为:"+result);
					out.println("修改成功，影响行数为:"+result);
					out.println("<a href='index.jsp'>返回到首页<a>");

				}
			}catch(Exception e){e.printStackTrace();}	
		}
	}
}
