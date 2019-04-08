<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="userId" name="userId" value="${sUser.userId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录账号：</td>
			<td><input type="text" id="userName" name="userName" value="${sUser.userName}"></td>
			<td>登陆人姓名：</td>
			<td><input type="text" id="realName" name="realName" value="${sUser.realName}"></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><input type="text" id="mobile" name="mobile" value="${sUser.mobile}"></td>
			<td>邮箱：</td>
			<td><input type="text" id="email" name="email" value="${sUser.email}"></td>
		</tr>
		<tr>
			<td>性别：</td>
			<td><input id="sex" name="sex" type="combo"></td> 
			<td>状态：</td>
			<td><input id="userStatus" name="userStatus" type="combo"></td> 
		</tr>
		<tr>
			<td>顺序：</td>
			<td><input type="text" id="userOrder" name="userOrder" value="${sUser.userOrder}"></td>
			<td>角色：</td>
			<td>
				<select id="userType" name="userType"> 
					<option value="1" <c:if test="${sUser.userType ==1 }">selected="selected"</c:if>>系统用户</option>
					<option value="2" <c:if test="${sUser.userType ==2 }">selected="selected"</c:if>>关联讲师</option>
				</select>
			</td>
		</tr>
		<tr id="relate">
				<td><span class="required">*</span>关联讲师：</td>
				<td colspan="3">
					<select id="relateUserId" name="relateUserId" style="width: 446px;">
						<option value="">请选择一个讲师</option>
						<c:forEach items="${leatureList }" var="leather">
							<option value="${leather['USER_ID']}" <c:if test="${leather['USER_ID'] == sUser.relateUserId}">selected="selected"</c:if>>
								[ID:${leather['USER_ID']}]--${leather['USER_NAME']}</option>
						</c:forEach>
					</select>
				</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" >&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	var userT='${sUser.userType}';
	if(userT == '1'){
		$("#relate").hide();
	}else{
		$("#relate").show();
	}
	
	//验证长度不超过32位，不能重命，不能为空
	$('#userName').validatebox({
		required:true,
		validType:['isExist["checkUserName","userName,userId"]','maxLength[32]']  
	}); 
	$('#realName').validatebox({required: true}); 
	$('#userOrder').validatebox({
		validType:['number','maxLength[8]']
	});
	$('#email').validatebox({validType:'email'});
	$('#mobile').validatebox({validType:'mobile'});
    $('#sex').combobox({  
    	data:JSON.parse('${sexCombo}'),
    	panelHeight:'auto',
    	value:'${sUser.sex}'
    }); 
    $('#userStatus').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${sUser.userStatus}'
    }); 
    
    $("#userType").change(function(){
    	if($(this).val() == 1){
    		$("#relate").hide();
    	}else{
    		$("#relate").show();
    	}
    });
    
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>