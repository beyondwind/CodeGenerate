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
					<li><a href="javascript:void(0);">step1</a></li>
					<li class="active"><a href="javascript:void(0);">step2</a></li>
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

				<div class="panel-heading">fill the tables you want.</div>
				<!--/.panel-heading -->

				<form action="<%=basePath%>downloadPage.htm" method="post"
					id="table_form">
					<div class="panel-body">
						<table class="table table-striped">
							<colgroup>
								<col width="10%" align="left" />
								<col width="30%" align="left" />
								<col width="60%" align="left" />
							</colgroup>
							<thead>
								<tr>
									<th>#</th>
									<th>table name</th>
									<th>table comment</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="table"
									items="${requestScope.result.dataObject }" varStatus="status">
									<tr>
										<td>${status.count }.&nbsp;<input type="checkbox"
											value="${table.tableName }" name="choosedTable"/></td>
										<td>${table.tableName }</td>
										<td>${table.tableComments }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--/.panel-body -->
				</form>

				<div class="panel-footer">
					<nav>
						<ul class="pager">
							<li class="previous"><a
								href="<%=basePath%>propertySettingPage.htm"><span
									aria-hidden="true">&larr;</span>Previous Step</a></li>
							<li class="next"><a style="cursor: pointer;" id="btn_table_submit">Next Step <span aria-hidden="true">&rarr;</span>
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
		
		<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="myModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content well">
					<p class="lead"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>我们正在玩命得生成数据！</p>
					<p class="lead">请耐心等待。。</p>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->

	<script>
		$(function() {
			var nexted=false;
			$('.table tbody tr').click(function() {
				var firstCheckbox = $('input[type="checkbox"]', this)[0];
				firstCheckbox.checked = !firstCheckbox.checked;
			});
			$('.table tbody tr input[type="checkbox"]').click(function(e) {
				e.stopPropagation();
			});
			$('#btn_table_submit').click(function(){
				if(!nexted){
					$('#table_form').submit();
				}
				$(this).attr('disabled','disabled');
				$('#myModal').modal({
				  keyboard: false
				})
				nexted=true;
			});
		});
	</script>

</body>
</html>