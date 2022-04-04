<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<titleI>IDKP0排序
</title>

<link href="../bs/css/bootstrap.min.css" rel="stylesheet">
<link href="../bs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="../css/dashboard.css" rel="stylesheet">
<script src="../bs/assets/js/ie-emulation-modes-warning.js"></script>
<style type="text/css">
.but {
	background: #428bca;
	color: #fff;
	font-size: 20px;
	text-indent: 0px;
	border-radius：30px；
}

.sel {
	background: #428bca;
	color: #fff;
}
</style>
</head>
<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Tony And Honey</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <!-- <select>
				<option value="1990">1990</option>
				</select> -->散点图
					</a></li>
					<li><a href="#">价值，重量比排序</a></li>
					<li><a href="#">最优解</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="01_start.jsp">散点图</a></li>
					<li class="active"><a href="02_start.jsp">价值,重量比排序 <span
							class="sr-only">(current)</span></a></li>
					<li><a href="03_start.jsp">最优解</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="03_start.jsp">价值，重量比排序</a></li>
					<li class="active">选择的具体数据：IDKP0_00</li>
				</ol>
				<h2 class="page-header">请输入查询数据</h2>

				<div class="row placeholders">
					<form class="navbar-form navbar-left" action="01_start.jsp">
						请输入文件名：<input type="text" class="form-control"
							placeholder="请输入文件名..."> &emsp;&emsp;&emsp;&emsp;
						请输入数据行数：<input type="text" class="form-control"
							placeholder="请输入数据行数..."> <span class="dropdown">
							<button class="btn btn-default dropdown-toggle"
								id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true">
								选择数据的行数<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="02_IDKP0.jsp">IDKP0</a></li>
								<li><a href="#">IDKP1</a></li>
								<li><a href="#">IDKP2</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</span> <input type="submit" class="but " value="Search...">

					</form>
				</div>

				<h2 class="sub-header">idkp1-10/IDKP0</h2>


				<%
					// 连接数据库并读取数据
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft", "root", "root");
					Statement st = conn.createStatement();

					String querySql = "select * from data1";
					ResultSet rs = st.executeQuery(querySql);
				%>
				<!-- //构造显示数据的表格，放到一个form中 -->
				<form action="dbServlet" method="post">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>number</th>
									<th>value</th>
									<th>weight</th>
									<th>name</th>
									<th>value/weight</th>
								</tr>
							</thead>
							<tbody>
								<%
									while (rs.next()) {
										int id = rs.getInt("id");
										String v = rs.getString("v");
										String weight = rs.getString("weight");
										String na = rs.getString("na");
										String ratio = rs.getString("ratio");
										out.println("<tr>");// 程序生成行开始标签
										//  out.println("<td width='100'> <input type=checkbox  name=id value="+id+"></td>");// 复选框列
										out.println("<td width='200'>" + id + "</td>");// 商品序号列
										out.println("<td width='200'>" + v + "</td>");
										out.println("<td width='200'>" + weight + "</td>");
										out.println("<td width='200'>" + na + "</td>");
										out.println("<td width='150'>" + ratio + "</td>");

										out.println("<td>");//最后一列，显示“删除”、“修改”超链接

										/* out.println("<a href='dbServlet?id=" + id + "&action=delete" + "'"
												+ "onclick='return confirm(\"确定删除此条记录?\")'> 删除</a>");
										
										out.println("<a href='dbServlet?action=edit&id=" + id + "'" + "> 修改</a>");// 如果是修改，跳转到operateServlet，目标页面根据id查找相关行的数据
										*/
										out.println("</td>");

										// 删除和修改先不写

									}
								%>
							
						</table>
					</div>
				</form>

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>

							<div class="col-sm-6  image ">
								<div class="thumbnail">
									<div class="caption">
										<h3>对数据排序？</h3>
										<p>
											<a href="#" class="btn btn-primary" role="button">排序</a> <a
												href="02_IDKP0_PX.jsp" class="btn btn-default" role="button">显示排序结果</a>
										</p>
									</div>
								</div>
							</div>

						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../bs/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="../bs/js/bootstrap.min.js"></script>
	<script src="../bs/assets/js/vendor/holder.min.js"></script>
	<script src="../bs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>