<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="synonymList">
<input type="text" name="none" style="display: none;" >
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>同义词：</td>
		<td><input name="synonymFrom" type="text" /></td>
		<td style="width:15px;"></td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">同义词列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/synonym/showAdd',460,380);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/synonym/showEdit','synonymId',460,380);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="deleteOne();"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'synonymList', 
	    pageSize :20,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		singleSelect:true,
		checkOnSelect:true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '50', field : 'ck',checkbox:true},
					 {width : '500',title : '同义词',field : 'synonymFrom'},
	                 /* {width : '150',title : '原词',field : 'synonymTo'}, */
					 {width : '150',title : '创建时间',field : 'createTime'}
	               ]
	    		]
	});     
});

function deleteOne(){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {
		$.messager.alert('提示:','请只选择一行记录'); 
		return false;
	}
	var synonymId = selections[0]['synonymId'];
	$.messager.confirm('提示:','你确认要删除吗?',function(e){ 
		if(e){ 
  		   $.post('delete',{"synonymId":synonymId}, function(data) {
  			   if(data.success){
  					$("#grid").datagrid('reload');  					
  					$.messager.show({ 
  						title:'温馨提示:', 
  						msg:'删除数据成功!', 
  						timeout:1500, 
  						showType:'slide'
  					}); 
  				} else {
  					if(data.msg){
  						$.messager.alert('提示:',data.msg,'warning');
  					}
  				}
  			}, 'json');
		}
	}); 
}

</script>
</html>