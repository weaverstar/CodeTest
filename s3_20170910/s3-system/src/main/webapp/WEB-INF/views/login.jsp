<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>S3系统</title>
<script type="text/javascript">
if (window.location != window.top.location) {
	window.top.location = window.location;
}
</script>
<link href="<c:url value="/static/css/login.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/static/js/easyui1.4.1/jquery.min.js'/>"></script>
</head>
<body>
<form id="form1" method="post">
	<h1 class="log_img"></h1>
	<div class="content">
		<div class="login-div">
	        <div class="form_ipt">
	        	<p><i class="user"></i><input type="text" placeholder="请输入用户名" id="userName" name="username"></p>
	        	<p><i class="pwd"></i><input type="password" placeholder="请输入密码" id="passWord" name="passWord"></p>
	        </div>
	        <div id="loginBtn" class="login-btn">立即登录</div>
	        <div id="errorMessages" class="errorMessages"></div> 		
		</div>
	</div>
	<%--<div class="copyright">优网科技有限公司版权所有 </div>--%>
</form>
<script>
$(function(){
	$('#loginBtn').click(function(){
		var userName = $('#userName').val();
		var passWord = $('#passWord').val();
		if (userName.length == 0 && passWord.length == 0) {
			$('#errorMessages').html("请输入用户名与密码！");
			return false;
		}
		if (userName.length == 0) {
			$('#errorMessages').html("请输入用户名！");
			return false;
		}
		if (passWord.length == 0) {
			$('#errorMessages').html("请输入密码！");
			return false;
		}
		$.post("login", {"userName" : userName,"passWord" : passWord}, function(data) {
			if (data.success) {
				window.location.href = 'main';
			} else {
				$('#errorMessages').html(data.msg);
			}
		}, "json");
	});
	$(document).keydown(function(e) {
		//13等于回车键(Enter)键值,ctrlKey 等于 Ctrl
		if (e.which == 13) {
			$('#loginBtn').click();
		}
	});
});
</script>
</body>
</html>
