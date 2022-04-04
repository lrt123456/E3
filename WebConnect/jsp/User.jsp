<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户类型</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<table>
		<tr>
			<th width="100">序号</th>
			<th width="100">用户名</th>
			<th width="100">密码</th>
			<th width="100">确认密码</th>
			<th width="200">邮箱</th>
			<th width="100">性别</th>
			<th width="200">生日</th>
			<th width="100">爱好</th>
			<th width="300">个人介绍</th>
		</tr>
		<%
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/midsemester", "root", "root");
			Statement st = conn.createStatement();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sql = "select * from user where username='" + username + "' and password='" + password + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				int id = rs.getInt("id");
				// String name1=rs.getString("username");
				//String password=rs.getString("password");
				String password2 = rs.getString("password");
				String mail = rs.getString("email");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String hobby = rs.getString("hobby");
				String personalDesciption = rs.getString("personalDesciption");

				out.println("<tr>");
				out.println("<td width='100'> <input type=checkbox  name=id value=" + id + "></td>");
				out.println("<td width='100'>" + username + "</td>");
				out.println("<td width='100'>" + password + "</td>");
				out.println("<td width='100'>" + password2 + "</td>");
				out.println("<td width='200'>" + mail + "</td>");
				out.println("<td width='100'>" + sex + "</td>");
				out.println("<td width='100'>" + birthday + "</td>");
				out.println("<td width='100'>" + hobby + "</td>");
				out.println("<td width='100'>" + personalDesciption + "</td>");
				out.println("<td>");
				out.println("<a href='Register.jsp'>修改</a>");
				out.println("</td>");
				out.println("</tr>");
			}
		%>

	</table>
</body>
</html>
