<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="insert">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
			<tr>
				<td><span class="required">*</span>登录账号：</td>
				<td><input type="text" id="userName" name="userName"></td>
				<td><span class="required">*</span>初始密码：</td>
				<td><input type="text" id="userPwd" name="userPwd" value="${initPassword}" readonly="readonly"></td>
			</tr>
			<tr>
				<td><span class="required">*</span>登陆人姓名：</td>
				<td><input type="text" id="realName" name="realName"></td>
				<td>电话：</td>
				<td><input type="text" id="mobile" name="mobile"></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" id="email" name="email"></td>
				<td>性别：</td>
				<td><input id="sex" name="sex" type="combo"></td> 
			</tr>
			<tr>
				<td>顺序：</td>
				<td><input type="text" id="userOrder" name="userOrder"></td>
				<td>角色：</td>
				<td>
					<select id="userType" name="userType">
						<option value="1">系统用户</option>
						<option value="2">关联讲师</option>
					</select>
				</td>
			</tr>
			<tr id="relate" style="display: none;">
				<td><span class="required">*</span>关联讲师：</td>
				<td colspan="3">
					<select id="relateUserId" name="relateUserId" style="width: 446px;">
						<option value="">请选择一个讲师</option>
						<c:forEach items="${leatureList }" var="leather">
							<option value="${leather['USER_ID']}">[ID:${leather['USER_ID']}]--${leather['USER_NAME']}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
	   </table>
	   <div class="editBtn">
			<button id="btnSubmit" type="button"class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	//验证长度不超过32位，不能重命，不能为空
	$('#userName').validatebox({
		required:true,
		validType:['isExist["checkUserName","userName","用名户已经存在,请重新输入"]','maxLength[32]']
	}); 
	$('#realName').validatebox({required:true}); 
	$('#userOrder').validatebox({
		validType:['number','maxLength[8]']
	});
	$('#email').validatebox({
		validType:'email',
		invalidMessage:'输入的邮箱格式不对'
	});
	$('#mobile').validatebox({validType:'mobile'});
    $('#sex').combobox({  
    	data:JSON.parse('${sexCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:0
    }); 
    
    $("#userType").change(function(){
    	if($(this).val() == 1){
    		$("#relate").hide();
    	}else{
    		$("#relate").show();
    	}
    });
    
    $('#btnSubmit').click(function(){
		if($("#form1").form('validate')){
			if($("#userType").val() == 2 && $("#relateUserId").val() == ""){
				$.messager.alert('提示:','请选择一个讲师！'); 
				return false;
			}
		
			$.messager.progress({
				title:'提示',
				msg:'服务器正在处理中，请耐心等待....'
			});
			$('#form1').submit();
		}
	});
});
</script>
</html>