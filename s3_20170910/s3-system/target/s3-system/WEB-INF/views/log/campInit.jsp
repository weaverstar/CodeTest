<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
a{text-decoration:underline;}
a:hover{text-decoration:underline;color:red;cursor:pointer}

</style>
	<title>campaign set log</title>
</head>
<body>


<div id="toolbar">
	<form id="list" action="list">
		<div id="search" class="easyui-panel" title="" data-options="fit:true,collapsible:true,border:0">
			<table class="searchTable">
				<tr>
					<td>login name：</td>
					<td><input type="text" id="loginName" name="loginName"></td>
					<td>edit time：</td>
					<td><input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
						至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
					</td>
					<td style="width:25px;">&nbsp;</td>
					<td><button id="queryBtn" type="button" class="button">search</button></td>
					<td><button id="clearBtn"  type="button" class="button">clean</button></td>
				</tr>
			</table>
		</div>
	</form>
</div>

<table id="grid" data-options="border:false,fit:true"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list',
	    pageSize :20,
		pageList : [ 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
        pagination: true,
		toolbar : '#toolbar',
	    columns : [[ {width : '100',title : 'login name',field : 'userName'},
	                 {width : '130',title : 'edit time',field : 'updateTime'},
            		 {width : '100',title : 'campaign id',field : 'campaignId'},
            		 {width : '200',title : 'campaign_name',field : 'campaignName'},
            		 
           			 {width : '150',title : 'edit key',field : 'editKey'},
			         {width : '150',title : 'old edit value',field : 'oldEditValue'},
			         {width : '150',title : 'new edit value',field : 'newEditValue'}
	                 ]]
	}); 
});


</script>
</html>