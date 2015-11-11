<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>CodeGenerator</title>

	<!--  Bootstrap   CSS   -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="./css/cover.css">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

<div class="site-wrapper">

	<div class="site-wrapper-inner">

		<div class="cover-container">

			<div class="masthead clearfix">
				<div class="inner">
					<h3 class="masthead-brand">CodeGenerator</h3>

					<nav>
						<ul class="nav masthead-nav">
							<li class="active"><a href="javascript:void(0);">Home</a></li>
							<li><a href="javascript:void(0);">step1</a></li>
							<li><a href="javascript:void(0);">step2</a></li>
							<li><a href="javascript:void(0);">step3</a></li>
						</ul>
					</nav>
				</div>
			</div>

			<div class="inner cover">
				<h1 class="cover-heading">Guide Page</h1>
				<p class="lead">Follow the <strong>3</strong> steps below, a easy way to generate code for you!</p>

				<dl class="dl-horizontal lead">
					<dt><span class="glyphicon glyphicon-check" aria-hidden="true"></span>&nbsp;step1</dt>
					<dd class="text-left">
						fill&lt;<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&gt; &nbsp;database connection properties.
					</dd>

					<dt><span class="glyphicon glyphicon-check" aria-hidden="true"></span>&nbsp;step2</dt>
					<dd class="text-left">
						choose&lt;<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&gt; &nbsp;the tables you want.
					</dd>

					<dt><span class="glyphicon glyphicon-check" aria-hidden="true"></span>&nbsp;step3</dt>
					<dd class="text-left">
						download&lt;<span class="glyphicon glyphicon-download" aria-hidden="true"></span>&gt; &nbsp;the code in Rar file.
					</dd>
				</dl>
				
				<p class="lead">
					<a href="<%=basePath %>propertySettingPage.htm" class="btn btn-lg btn-default">Let's Begin&nbsp;
						<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
					</a>
				</p>
			</div>

			<div class="mastfoot">
				<div class="inner">
					<p>Code Generator for <a href="http://www.bizpartner.cn/"> © Bizpartner</a>, by <a href="https://github.com/beyondwind">@紫英</a>.</p>
				</div>
			</div>
		</div>
  </div>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!--  Bootstrap  JavaScript  -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>