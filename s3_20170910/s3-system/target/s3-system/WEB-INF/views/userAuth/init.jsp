<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
	    <td>手机号码：</td>
		<td><input type="text" id="mobile" name="mobile"/></td>
		<td>昵称：</td>
		<td><input type="text" id="userName" name="userName" /></td>
		<td>真实姓名：</td>
		<td><input type="text" id="realName" name="realName" /></td>	
		<td style="width: 100px;">认证申请时间：</td>
		<td><input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
			至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
		</td> 
		<td style="width:15px;">&nbsp;</td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td> 
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>	
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">待审核认证信息</div>
	<div class="icon">
		<ul>
    		<li><a href="#"  title="审核"  onclick="showAuthInfo();"><span class="menu3"></span>审核</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :20,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		singleSelect:true,
		checkOnSelect:true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '100',title : '用户ID',field : 'userId'},
	                 {width : '100',title : '昵称',field : 'userName'},	                 
	                 {width : '100',title : '真实姓名',field : 'realName'},
	                 {width : '100',title : '手机号码',field : 'mobile'},
	                 {width : '150',title : '身份证号',field : 'regNumber'},
	                 {width : '170',title : '认证申请时间',field : 'authDate'},
	                 {width : '80',title : '认证状态',field : 'status',formatter:function(v,r){return {'-1':'未认证',0:'待审核',1:'已认证',2:'认证未通过'}[v]}},
					 {width : '80',title : '审核',field : '审核', 
						 formatter:function(v,r){ return '<a href="#" title="审核" onclick="showAuthInfo(\'' +r.userId+ '\');"><span class="menu3"></span></a>' }
					 },
					 {width : '80',title : '用户资料',field : '用户资料',
						 formatter:function(v,r){ return '<a href="#" title="用户资料" onclick="userDetail(\''+r.userId+'\');"><span class="menu12"></span></a>' }
					 }
		  ]]
	}); 
    
});

function showAuthInfo(userId){
	if(userId==undefined || userId==null){
		var selections = $('#grid').datagrid('getSelections');
		if (selections.length == 0) {
			$.messager.alert('提示:','请选择一行记录'); 
			return ;
		}
		userId = selections[0]['userId'];
	}
	$.dialog.open(ctx+'/userAuth/showAuthInfo?userId='+userId, {
		lock: true,
		width:850,
		height:640
	});
}


function userDetail(userId){
	if(userId==undefined || userId==null){
		var selections = $('#grid').datagrid('getSelections');
		if (selections.length == 0) {
			$.messager.alert('提示:','请选择一行记录'); 
			return ;
		}
		userId = selections[0]['userId'];
	}
	$.dialog.open(ctx+'/userInfo/userDetail?userId='+userId, {
		lock: true,
		width:880,
		height:460
	});
}
</script>
</html>