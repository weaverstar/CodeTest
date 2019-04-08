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
  <table class="searchTable">
	<tr>
		<td>销售时间:</td>
		<td>
			<input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
			至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>客户:</td>
		<td><input type="text" id="customerId" name="customerId"  type="combo"/></td>
	</tr>
	  <tr>
		  <td>城市:</td>
		  <td><input type="text" id="cityId" name="cityId"  type="combo"/></td>
		  <td>是否付款:</td>
		  <td><input type="text" id="temp2" name="temp2"  type="combo"/></td>
	  </tr>
	<tr>
		<td colspan="3"><button id="queryBtn" type="button" class="button">查询</button>
		<td colspan="3"><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">销售清单</div>
	<div class="icon">
		<ul>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>新增</a></li>
	   		<li><a href="#" onclick="showEdit('/wholeSale/showEdit','mainId',850,380);"><span class="menu13"></span>编辑</a></li>
	   		<li><a href="#" onclick="removeRow('mainId');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="border:false,fit:true"></table>
</body>
<script type="text/javascript">
$(function() {
    $('#grid').datagrid({
        url:'${ctx}/wholeSale/list.action',
        pageSize :20,
        pageList : [10, 20, 30, 40, 50],
        // striped : true,
        rownumbers : true,
        pagination : true,
        toolbar : '#toolbar',
        // singleSelect : true,
        columns : [
            [
                {width : '8%', field : 'ck',checkbox:true},
				{width : '12%',title : '销售时间',field : 'operatorTime'},
                {width : '10%',title : '流水号',field : 'temp3'},
				{width : '10%',title : '城市',field : 'city'},
				{width : '10%',title : '乡镇',field : 'town'},
				{width : '10%',title : '姓名',field : 'customerId'},
				{width : '10%',title : '电话',field : 'phone'},
                {width : '10%',title : '合计',field : 'temp1'},
                {width : '10%',title : '是否付款',field : 'temp2',formatter:function(value,row,index){
                    if(value == "未付款"){
                        return '<a href="#" onclick="payStatus('+row.mainId+');"><span style="color:red" >'+value+'</span></a>';
					}else{
                        return '<span style="color:green" color="green">'+value+'</span>';
					}
                }},
                {width : '10%',title : '打印',field : 'mainId',formatter:function(value,row,index){
                    return "<button id=\"infoPrint\" type=\"button\" class=\"button\" onclick=\"doPrint("+row.mainId+")\" style=\"border: 1px solid #3186E6;background: #3186E6;color: #fff;width: 50px;border-radius: 4px;\">打印</button>";
                }}
        	]
		]
    });

    $('#customerId').combobox({
        data:JSON.parse('${customerMap}'),
        editable:true,
        width:216,
        panelHeight:'auto'
    });

    $('#cityId').combobox({
        data:JSON.parse('${cityMap}'),
        editable:true,
        width:216,
        panelHeight:'auto'
    });

    $('#temp2').combobox({
        data:JSON.parse('${payMap}'),
        editable:true,
        width:216,
        panelHeight:'auto'
    });

});
function showDetailAdd(){
	$.dialog.open(ctx+'wholeSale/showAdd', {
		lock: true,
		width:850,
		height:380
	});
}

function payStatus(id){
    $.dialog.open(ctx+'wholeSale/pay.action?id='+id, {
        lock: true,
        width:350,
        height:200
    });
}

function doPrint(id){
    $.dialog.open(ctx+'wholeSale/print.action?id='+id, {
        lock: true,
        width:700,
        height:350
    });
}


</script>
</html>