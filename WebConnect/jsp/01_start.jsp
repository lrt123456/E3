<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>01背包问题_散点图</title>

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
					<li class="active"><a href="01_start.jsp">散点图 <span class="sr-only">(current)</span></a></li>
					<li><a href="02_start.jsp">价值,重量比排序 </a></li>
					<li><a href="03_start.jsp">最优解</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
					<li class="active">散点图</li>
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
								<li><a href="01_IDKP0.jsp">IDKP0</a></li>
								<li><a href="#">IDKP1</a></li>
								<li><a href="#">IDKP2</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</span>
						<input type="submit" class="but " value="Search...">
						<!-- <select
							class="sel form-control">
							<option><a href="">IDKP0</a></option>
							<option><a>IDKP1</a></option>
							<option><a>IDKP2</a></option>
							<option><a>IDKP3</a></option>
							<option><a>IDKP4</a></option>
							<option><a>IDKP5</a></option>
							<option><a>IDKP6</a></option>
							<option><a>IDKP7</a></option>
						</select> <input type="submit" class="but " value="Search..."> -->

					</form>
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