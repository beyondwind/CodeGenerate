<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../../include/header.jsp"%>
<style type="text/css">
</style>
</head>

<body>

	<h3>新增</h3>
	<p class="text-error">${requestScope.msg}</p>
	<form class="form-horizontal" method="post" action="choosed/create.htm">
		<div class="control-group">
			<label class="control-label">图片id</label>
			<div class="controls">
				<input type="text" name="pid" value="${requestScope.choosed.pid}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名称</label>
			<div class="controls">
				<input type="text" name="userName"
					value="${requestScope.choosed.userName}">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">新增</button>
				<a class="btn" href="choosed.htm">取消</a>
			</div>
		</div>
	</form>

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