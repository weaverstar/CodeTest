<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js?version=${jsVersion}"></script> --%>
<style>
	input[type='checkbox']{
		vertical-align: middle;
	}
	#search{
		font-size: 14px;
	    line-height: 30px;
	    height: 30px;
	    margin-top: -3px;
	}
</style>
</head>
<body>
<form id="form1" action="bindUser">
<input type="hidden" name="roleId" value="${roleId}"/>
<input type="hidden" name="userIds" id="userIds"/>
<div class="dialogPage" style="margin-top: 14px;">
	<div class="om-panel-header">
		绑定用户
		<div class="bindUser" style="top: 16px;">
			<a href="#" onclick="doSubmit()"><span class="menu4"></span>bind</a>
		</div>
		<div class="bindUser" style="top: 16px;left: 150px;">
			<a href="#" onclick="javascript:art.dialog.close();"><span class="menu11"></span>close</a>
		</div>
	</div>
	<div id="content" class="editDiv"></div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	var sUserRoleList = ${sUserRoleList};
	var data = ${userData};
	$('.editDiv').height(615);
	$('#content').userChoose(data,sUserRoleList);	
	/* $('#content').mCustomScrollbar({theme:"minimal-dark"}); */
});
function doSubmit(){
	var userIds="";
	$('input[name="userId"]:checked').each(function(){ 
		 userIds+=$(this).val()+','; 
	}); 
	console.log(userIds);
	$("#userIds").val(userIds);	
	$("#form1").submit();
}
</script>
</html>