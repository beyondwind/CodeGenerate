<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp"%>

</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:void(0);">CodeGenerator</a>
			</div>

			<div id="navbar" class="collapse navbar-collapse navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="<%=basePath%>">Home</a></li>
					<li class="active"><a href="javascript:void(0);">step1</a></li>
					<li><a href="javascript:void(0);">step2</a></li>
					<li><a href="javascript:void(0);">step3</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<div class="starter-template">

			<c:if
				test="${requestScope.result.success and !empty requestScope.result.msg }">
				<div class="alert alert-info alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Success!</strong>&nbsp;&nbsp;${requestScope.result.msg }
				</div>
			</c:if>
			<c:if
				test="${!requestScope.result.success and !empty requestScope.result.msg }">
				<div class="alert alert-danger alert-dismissible fade in"
					role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Holy guacamole!</strong>&nbsp;&nbsp;${requestScope.result.msg }
				</div>
			</c:if>
			<div class="panel panel-primary">

				<div class="panel-heading">Please fill database connection
					properties</div>
				<!--/.panel-heading -->

				<div class="panel-body">
					<form class="form-horizontal"
						action="<%=basePath%>chooseTablePage.htm" method="post"
						id="property_form">
						<div class="form-group">
							<label for="ip_address" class="col-sm-3 control-label">IP
								Address</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="ip_address"
									placeholder="127.0.0.1" name="ipAddress"
									value="${requestScope.generateInfo.ipAddress }" />
							</div>
						</div>

						<div class="form-group">
							<label for="port" class="col-sm-3 control-label">Port</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="port"
									placeholder="3306" name="port"
									value="${requestScope.generateInfo.port }" />
							</div>
						</div>

						<div class="form-group">
							<label for="user_name" class="col-sm-3 control-label">User
								Name</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="user_name"
									placeholder="root" name="userName"
									value="${requestScope.generateInfo.userName }" />
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" id="password"
									placeholder="Password" name="password"
									value="${requestScope.generateInfo.password }" />
							</div>
						</div>

						<div class="form-group">
							<label for="database_type" class="col-sm-3 control-label">Database
								Type</label>
							<div class="col-sm-5">
								<label class="radio-inline"> <input type="radio"
									name="databaseType" id="database_type1" value="1" checked>MySQL
								</label> <label class="radio-inline"> <input type="radio"
									name="databaseType" id="database_type2" value="2">Oracle
								</label>
							</div>
						</div>

						<div class="form-group">
							<label for="database_name" class="col-sm-3 control-label">Database
								Name/SID</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="database_name"
									placeholder="bpdb" name="dbName"
									value="${requestScope.generateInfo.dbName }" />
							</div>
						</div>

						<div class="form-group">
							<label for="package_path" class="col-sm-3 control-label">Package
								Path</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="package_path"
									placeholder="com.sbkj.orderaide" name="packagePath"
									value="${requestScope.generateInfo.packagePath }" />
							</div>
						</div>
					</form>
				</div>
				<!--/.panel-body -->

				<div class="panel-footer">
					<nav>
						<ul class="pager">
							<!--<li class="previous"><a href="#"><span aria-hidden="true">&larr;</span>Previous Step</a></li>-->
							<li class="next"><a onclick="$('#property_form').submit();"
								style="cursor: pointer;">Next Step <span aria-hidden="true">&rarr;</span>
							</a></li>
						</ul>
					</nav>
				</div>
				<!--/.panel-footer -->
			</div>
		</div>

		<hr />

		<footer>
			<p class="text-center">
				Code Generator for <a href="http://www.bizpartner.cn/"> ©
					Bizpartner</a>, by <a href="https://github.com/beyondwind">@紫英</a>.
			</p>
		</footer>
	</div>
	<!-- /.container -->

</body>
</html>