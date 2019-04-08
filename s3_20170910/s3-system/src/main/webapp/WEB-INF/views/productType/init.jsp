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
.datagrid-cell input{
    appearance: none;
    -webkit-appearance: none;
    position: relative;
    top:2px;
    width: 47px;
    height: 22px;
    background: #dfdfdf;
    border-radius: 16px;
    border: 1px solid #dfdfdf;
    outline: 0;
    box-sizing: border-box;
}
.datagrid-cell input:checked{
    border-color: #04be02;
    background-color: #04be02;
}
.datagrid-cell input:before, .datagrid-cell input:after{
    content: " ";
    position: absolute;
    top: 0;
    left: 0;
    height: 20px;
    border-radius: 15px;
    transition: transform 0.3s;
    transition: -webkit-transform 0.3s;
    transition: transform 0.3s, -webkit-transform 0.3s;
    -webkit-transition: -webkit-transform 0.3s;
}
.datagrid-cell input:before{
    width: 45px;
    background-color: #fdfdfd;
}
.datagrid-cell input:after{
    width: 30px;
    background-color: white;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.4);
}
.datagrid-cell input:checked:before{
    transform: scale(0);
    -webkit-transform: scale(0);
}
.datagrid-cell input:checked:after{
    transform: translateX(15px);
    -webkit-transform: translateX(15px);
}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0">
  <input type="hidden" id="dictId" name="dictId" value="${dictId}">
  <table class="searchTable">
	<tr>
		<td>创建时间:</td>
		<td>
			<input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
			至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>产品类型:</td>
		<td><input type="text" id="typeId" name="typeId"  type="combo"></td>
		<td>是否有效:</td>
		<td><input type="text" id="statusId" name="statusId"  type="combo"/></td>
	</tr>
	<tr>
		<td colspan="3"><button id="queryBtn" type="button" class="button">查询</button>
		<td colspan="3"><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">产品类型清单</div>
	<div class="icon">
		<ul>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>新增</a></li>
	   		<li><a href="#" onclick="showEdit('/productType/showEdit','id',700,280);"><span class="menu13"></span>编辑</a></li>
	   		<li><a href="#" onclick="removeRow('id');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="border:false,fit:true"></table>
</body>
<script type="text/javascript">
$(function() {
    $('#grid').datagrid({
        url:'${ctx}/productType/list.action',
        pageSize :20,
        pageList : [10, 20, 30, 40, 50],
        // striped : true,
        rownumbers : true,
        pagination : true,
        toolbar : '#toolbar',
        // singleSelect : true,
        columns : [
            [
                {width : '10%', field : 'ck',checkbox:true},
				{width : '12%',title : '创建时间',field : 'createTime'},
                {width : '12%',title : '产品类型',field : 'productType'},
                {width : '18%',title : '状态',field : 'statusId',formatter:function(value,row,index){
                    return JSON.parse('${statusMap}')[value]
                }
                }
        	]
		]
    });

    $('#typeId').combobox({
        data:JSON.parse('${productTypeMap}'),
        editable:true,
        width:216,
        panelHeight:'auto'
    });

    $('#statusId').combobox({
        data:JSON.parse('${status}'),
        editable:true,
        width:216,
        panelHeight:'auto'
    });
});



function showDetailAdd(){
	$.dialog.open(ctx+'/productType/showAdd', {
		lock: true,
		width:800,
		height:300
	});
}
</script>
</html>