<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="../css/Login.css">
</head>
<body>
	<!--  头部 -->
	<div id="header">
		<!--  头部的第一行-->
		<div id="line1">
			<img src="../imgs/logo.jpg" /> <span class="line"></span> <span
				class="wel_reg">欢迎登录</span>
		</div>
		<!--  头部的第二行-->
		<div id="line2">
			<span>未有账号？</span> <a href="#">请注册</a>
		</div>
	</div>

	<!--  主体 -->
	<div id="middle">
		<!-- 表单 -->
		<form action="01_start.jsp">
			<table>
				<tr>
					<!-- 用户名-->
					<td><input type="text" name="username" placeholder="用户名">
					</td>
				</tr>
				<tr>
					<!-- 用户名（提示消息）-->
					<td class="msg">请输入你的用户名</td>
				</tr>
				<tr>
					<!-- 密码-->
					<td><input type="password" name="userpassword"
						placeholder="设置密码"></td>
				</tr>
				<tr>
					<!-- 密码（提示消息）-->
					<td class="paw">请输入你的密码</td>
				</tr>
				<!-- <tr>
					确认密码
					<td><input type="password" name="reuserpassword"
						placeholder="确认密码"></td>
				</tr>
				<tr>
					确认密码（提示消息）
					<td class="repaw alert">两次密码输入不一致</td>
				</tr>
				<tr>
					验证手机
					<td class="yzsj"><input type="text" name="phone"
						placeholder="验证手机"></input> &nbsp;<span>或 </span>&nbsp; <a
						href="#">验证邮箱</a></td>
				</tr> -->
				<tr>
					<!-- 同意注册协议-->
					<td class="protocol"><span>我已阅读并同意 </span>&nbsp; <a href="#">《京淘用户注册协议》</a>
					</td>
				</tr>
				<tr>
					<td>
						<!--  立即登录--> <input type="submit" value="立即登录">
					</td>
				</tr>
			</table>
		</form>
		<!-- 手机快速注册图片-->
		<img src="../imgs/msg.jpg" />
		<!-- 手机快速注册  -->
	</div>

	<!--  尾部 -->
	<div id="footer">
		<p>
			关于我们 | 联系我们 | 人才招聘 | 商家入驻 | 营销中心 | 手机京淘 | 友情连接 | 销售联盟 | 京淘社区 | 京淘公益 |
			English Site <br> Copyright © 2015 - 2019 京淘jingtao.com 版权所有
		</p>
	</div>
</body>
</html>