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
		
		doPost(request, response);//�����ó�����ʽ��ת��Servlet��ʱ�������ύ��ʽΪGET
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		
		if(action.equals("add")){
		// 1. ������� ��ȡ��addҳ�洫���������ݣ�д���ݿ�
		// ��1�� ��ȡҳ������
		//int id=Integer.parseInt(request.getParameter("id"));
		String username=request.getParameter("username");//�û���
		String password=request.getParameter("password");//����
		String password2=request.getParameter("password2");//ȷ������
		String email=request.getParameter("email");//�����ʼ�
		String sex=request.getParameter("sex");//�Ա�
		String birthday=request.getParameter("birthday");//����
		String hobby=request.getParameter("hobby");//����
		String personalDesciption=request.getParameter("personalDesciption");//����˵��
		
		// ��2�� д�����ݿ�
		try{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
		    Statement st=conn.createStatement();
		    
		    String addSql="insert into user(name,password,password2,email,sex,birthday,hobby,personalDesciption)"
		                   +" values('"+username+"','"+password+"','"+password2+"','"+email+"','"+sex+"','"+birthday+"','"+hobby+"','"+personalDesciption+"')";
		    	    
		    int  result=st.executeUpdate(addSql);// ��ʾ��Ӱ�������
		    if(result==0){
		    	out.println("���ʧ�ܣ�");		    	 	
		    }
		    else{
		    	out.println("��ӳɹ���");	
		    	out.println("�����䣺"+addSql);
		    	out.println("<a href='Administrator.jsp'>���ص���ҳ</a>");
		    }		
		}catch(Exception e){e.printStackTrace();}
		}// end of add if
		
	
		// 2. ɾ������----------------------------------
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
					{out.println("ɾ��ʧ�ܣ�");
					}
					else{
						out.println("ɾ���ɹ���"+"<br>");	
						out.println("ɾ����䣺"+sqlDelete+"<br>");	
						out.println("<a href='Administrator.jsp'>���ص���ҳ<a>");
						
					}
					
					
				}// end of delete
				
				//3. �޸�����
				if(action.equals("edit")){//�޸��������󣬸����޸�id��ѯ���ݿ��ã���Ҫ�޸ĵļ�¼
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
					
					if(rs.next()){//������ҵ���Ҫ�޸ĵļ�¼������ѯ�������request�У��Ա����޸�ҳ����ʾҪ�޸ĵ�����
						request.setAttribute("id", rs.getInt("id"));//����ֵΪInteger����
						request.setAttribute("username", rs.getString("username"));
						request.setAttribute("password", rs.getString("password"));
						request.setAttribute("password2", rs.getString("password2"));
						request.setAttribute("email", rs.getString("email"));
						request.setAttribute("sex", rs.getString("sex"));
						request.setAttribute("birthday", rs.getString("birthday"));
						request.setAttribute("hobby", rs.getString("hobby"));
						request.setAttribute("personalDesciption", rs.getString("personalDesciption"));	
						//request.setAttribute("action", action);
					    
						// ��ת���޸�ҳ��	Edit.jsp	    
						request.getRequestDispatcher("Edit.jsp").forward(request, response);
							}
					}catch(Exception e){
						
					}	
		      
		        
				}// end of edit if
				
				
				 // �޸ı���
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
					out.println(" �޸���䣺"+sqlEdit);
					
					Connection conn=null;
					Statement st=null;
					try{
						 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						   conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
						     st=conn.createStatement();
						int result=st.executeUpdate(sqlEdit);
						if(result==0){
						out.println("Ӱ������Ϊ0���޸�ʧ�ܣ�");	
						System.out.println("Ӱ������Ϊ0���޸�ʧ�ܣ�");	
						}
						else{
							System.out.println("�޸ĳɹ���Ӱ������Ϊ:"+result);
							out.println("�޸ĳɹ���Ӱ������Ϊ:"+result);
							//out.println("<br>");
							out.println("<a href='Administrator.jsp'>���ص���ҳ<a>");	
						}
						}catch(Exception e){e.printStackTrace();}	
			
					
				}// end of save if
		
		
		
		
	
	}

}
