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
		<td>自定义词语：</td>
		<td><input type="text" id="word" name="word"  maxlength="50"  value="" /></td>
		<td>状态：</td>
		<td><input id="state" name="state" type="combo" /></td>
		<td>创建时间：</td>
		<td><input id="createTimeStart" name="createTimeStart"  type="datetime" class="easyui-datebox" />
			至 <input id="createTimeEnd" name="createTimeEnd"   type="datetime" class="easyui-datebox"/> &nbsp; &nbsp;
		</td> 
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
	
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">自定义词库列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/es/wordDict/showAdd',450,180);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/es/wordDict/showEdit','id',460,210);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('id');"><span class="menu11"></span>删除</a></li>
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
	    columns : [[ {width : '100', field : 'ck',checkbox:true},
	                 {width : '200',title : '词语',field : 'word'},	 
					 {width : '200',title : '创建时间',field : 'creatTime'},
					 {width : '100',title : '状态',field : 'state', formatter:function(v,r){ return JSON.parse('${statusMap}')[v]}},
					]
	    		]
	}); 
    $('#state').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
});

</script>
</html>