<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
a{text-decoration:underline;}
a:hover{text-decoration:underline;color:red;cursor:pointer}
#detailTitle{color:red}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0">
  <table class="searchTable">
	<tr>
		<td>字段名称：</td>
		<td><input type="text" id="dictName" name="dictName"></td>
		<td style="width:15px;">&nbsp;</td>	
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清除</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">字段管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/prg/dict/showAdd',600,230);"><span class="menu1"></span>新增</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/dict/showEdit','dictId',600,230);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('dictId');"><span class="menu11"></span>删除</a></li>
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
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
	                 {width : '200',title : '字段名称',field : 'dictName',sortable:true},
	                 {width : '350',title : '字段描述',field : 'dictDesc',sortable:true},
	                 {width : '100',title : '状态',field : 'dictStatus',sortable:true,formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '150',title : '更新时间',field : 'updateTime',sortable:true},
	                 {width : '50',title : '操作',field : 'action',sortable:true,formatter:function(value,row){
	                	 return '<a href="<c:url value='/system/prg/detail/init'/>?dictId='+row.dictId+'"><img border=0 src="<c:url value='/static/images/childs.gif'/>" /></a>';
	    			 }
	    			}]]
	}); 
});



</script>
</html>