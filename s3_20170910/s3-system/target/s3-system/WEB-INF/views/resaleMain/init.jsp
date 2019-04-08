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
		<td>销售时间：</td>
		<td>
			<input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"/>
			至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>产品类型：</td>
		<td><input type="text" id="productType" name="productType"  type="combo"></td>
		<td>产品名称：</td>
		<td><input type="text" id="product" name="product"  type="combo"></td>
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
	   		<li><a href="#" onclick="showEdit('/resale/showEdit','resaleId',700,280);"><span class="menu13"></span>编辑</a></li>
	   		<li><a href="#" onclick="removeRow('resaleId');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="border:false,fit:true"></table>
</body>
<script type="text/javascript">
$(function() {
    $('#grid').datagrid({
        url:'${ctx}/resale/list.action',
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
				{width : '12%',title : '销售时间',field : 'startTime'},
				{width : '12%',title : '产品类型',field : 'productType'},
				{width : '12%',title : '产品名称',field : 'product'},
				{width : '12%',title : '单价',field : 'price'},
				{width : '12%',title : '数量',field : 'productNum'},
                {width : '12%',title : '单位',field : 'unit'},
                {width : '18%',title : '打印',field : 'resaleId',formatter:function(value,row,index){
                    return "<button id=\"infoPrint\" onclick=\"printView("+value+")\" type=\"button\" class=\"button\" style=\"border: 1px solid #3186E6;background: #3186E6;color: #fff;width: 50px;border-radius: 4px;\">打印</button>";
				}}
        	]
		]
    });

    $('#product').combobox({
        data:[],
        editable:true,
        width:216,
        panelHeight:'auto'
    });

    $('#productType').combobox({
        data:JSON.parse('${productCombo}'),
        editable:true,
        width:216,
        panelHeight:'auto',
        onSelect:function(record){
            var value = record.value;
            $.post(ctx+"resale/detialDict.action", {"type":value},
                function(msg){
                    var temp = []
                    var detailDict = msg.detialDict;
                    for( var i=0;i<detailDict.length;i++ ){
                        temp.push(JSON.parse(detailDict[i]));
                    }
                    $("#product").combobox("loadData",temp);
                },"json");
        }
    });

});
function showDetailAdd(){
	$.dialog.open(ctx+'/resale/showAdd', {
		lock: true,
		width:1000,
		height:400
	});
}

function printView(id){
    $.dialog.open(ctx+'resale/print?resaleId='+id, {
        lock: true,
        width:700,
        height:280
    });
}
</script>
</html>