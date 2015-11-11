<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../include/old_header.jsp"%>
<style type="text/css">
html {
	height: 100%;
	width: 100%;
}

body {
	background: #fafafa;
}
</style>
</head>

<body>

	<div class="container">
		<form class="form-horizontal" action="chooseTablePage.htm"
			method="post" id="setForm">

			<h4>1、必填初始数据</h4>
			<hr class="featurette-divider" />
			<div class="control-group">
				<label class="control-label" for="project_path">【项目路径】</label>
				<div class="controls">
					<input type="text" id="project_path" name="project_path"
						placeholder="项目路径" class="span4"
						value="${sessionScope.project_path }" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="package_name">【基础包名】</label>
				<div class="controls">
					<input type="text" id="package_name" name="package_name"
						placeholder="基础包名" class="span4"
						value="${sessionScope.package_name }" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="optionsRadios">【数据库类型】</label>
				<div class="controls">
					<c:choose>
						<c:when test="${sessionScope.databse_type eq 'oracle' }">
							<label class="radio inline"> <input type="radio"
								name="databse_type" value="oracle" checked="checked" />oracle
							</label>
							<label class="radio inline"> <input type="radio"
								name="databse_type" value="mysql" />mysql
							</label>
						</c:when>
						<c:otherwise>
							<label class="radio inline"> <input type="radio"
								name="databse_type" value="oracle" />oracle
							</label>
							<label class="radio inline"> <input type="radio"
								name="databse_type" value="mysql" checked="checked" />mysql
							</label>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="database_owner">【数据库用户/数据库】</label>
				<div class="controls">
					<input type="text" id="database_owner" name="database_owner"
						placeholder="数据库用户/数据库" class="span4"
						value="${sessionScope.database_owner }" />
				</div>
			</div>
			<h4>2、附加选择项</h4>
			<hr class="featurette-divider" />

			<div class="control-group">
				<label class="control-label" for="modual_name">【模块名称】</label>
				<div class="controls">
					<input type="text" id="modual_name" name="modual_name"
						placeholder="模块名称" class="span4"
						value="${sessionScope.modual_name }" />
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<button class="btn btn-primary" type="submit">下一步</button>
				</div>
			</div>
		</form>
	</div>
	<!-- /container -->

	<footer>
		<p class="text-center">
			浙江工业大学-<a href="http://www.zjut.com/" title="精弘">精弘网络</a>-随风而已 版权所有
		</p>
		<p class="text-center">
			© 2013 <a href="javascript:void(0);" title="我的浙江工业大学">MYZJUT</a>
		</p>
	</footer>

	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-header">
			<h3 id="myModalLabel">提示</h3>
		</div>
		<div class="modal-body">
			<p>正在加载数据库表。。。</p>
			<div class="progress progress-striped active">
				<div class="bar" style="width: 0%;" id="prosessBar"></div>
			</div>
		</div>
		<div class="modal-footer"></div>
	</div>
	<script type="text/javascript">
		var persent = 0;

		$('#setForm').submit(function() {
			$('#myModal').modal('show');
			setTimeout(addProsess, 900);
			return true;
		});

		function addProsess() {
			persent = persent + (100 - persent) / 4;
			console.info("persent:" + persent);
			$('#prosessBar').css('width', persent + '%');
			setTimeout(addProsess, 500);
		}
	</script>
</body>
</html>