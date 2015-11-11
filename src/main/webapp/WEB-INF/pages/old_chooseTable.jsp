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

.title {
	text-shadow: 1px 1px #fff, -1px -1px #333;
}
</style>
</head>

<body>

	<div class="container">
		<form class="form" action="finish.htm" method="post">
			<hr class="featurette-divider" />
			<h3 class="title">选择需要生成的表</h3>
			<table class="table table-bordered table-hover">
				<col width="5%">
				<col width="35%">
				<col width="60%">
				<thead>
					<tr>
						<th>选择</th>
						<th>表名</th>
						<th>表备注</th>
					</tr>
				</thead>
				<tfoot></tfoot>
				<tbody>
					<c:forEach var="table" items="${requestScope.tableList}">
						<tr>
							<td><input value="${ table.table_name}"
								name="selectedTables" type="checkbox" /></td>
							<td>${ table.table_name}</td>
							<td>${ table.table_comments}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary">完成</button>
					<a href="additionSetPage.htm" class="btn"
						style="margin-left: 10px;">上一步</a>
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
</body>
</html>