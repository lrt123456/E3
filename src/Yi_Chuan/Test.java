package Yi_Chuan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {

	long timeStart = System.currentTimeMillis();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			doPost(request, response);//当采用超链方式跳转到Servlet的时候，数据提交方式为GET
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
       
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		
		
		//public static void main(String[] args) {
		     GeneticAlgorith g=new GeneticAlgorith(100);
		     g.geneticAlgorithProcess();
		     out.println(g.sumCharge());
		     out.println(g.sumWeight());
		     g=null;
		    //}catch(Exception e){e.printStackTrace();}
		     
		 	try{
			    // ...
			} catch(Exception e) {
			    e.printStackTrace();
			}

		     
	}
	

}
