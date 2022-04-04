<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mysql.jdbc.Driver" %>
<%@page import="java.sql.*" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  <%
    response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("username");
	String password=request.getParameter("password");
    if(name.equals("username")&&password.equals("password")){
      RequestDispatcher retq=request.getRequestDispatcher("/01_start.jsp");//身份为普通用户，跳转到User.jsp
	  retq.forward(request,response);
	}
	else{
	  RequestDispatcher retq=request.getRequestDispatcher("/Login.jsp");//未选择身份，跳转到Login.jsp
	  retq.forward(request,response);
	}

 %> 
 
  </body>
</html>