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
  <input type="hidden" id="dictId" name="dictId" value="${dictId}">
  <table class="searchTable">
	<tr>
		<td>frequencyName：</td>
		<td><input type="text" id="frequencyName" name="frequencyName"></td>
		<td style="width:15px;">&nbsp;</td>
		<td><button id="queryBtn" type="button" class="button">search</button></td>
		<td><button id="clearBtn" type="button" class="button">clean</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">frequency List</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="showInfo();"><span class="menu13"></span>show</a></li>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>create</a></li>
	   		<li><a href="#" onclick="showEdit('/frequency/showEdit','frequencyId',1100,'95%');"><span class="menu13"></span>update</a></li>
	   		<li><a href="#" onclick="removeRow('frequencyId');"><span class="menu11"></span>delete</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
    $('#grid').datagrid({
        url:'${ctx}/frequency/list',
        pageSize :20,
        pageList : [10, 20, 30, 40, 50 ],
        striped : true,
        rownumbers : true,
        pagination : true,
        toolbar : '#toolbar',
        columns : [[
            {width : '30', field : 'ck',checkbox:true},
            {width : '120',title : 'frequency_name',field : 'frequencyName'},
            {width : '50',title : 'hour1',field : 'hour1'},
            {width : '50',title : 'hour2',field : 'hour2'},
            {width : '50',title : 'hour3',field : 'hour3'},
            {width : '50',title : 'hour4',field : 'hour4'},
            {width : '50',title : 'hour5',field : 'hour5'},
            {width : '50',title : 'hour6',field : 'hour6'},
            {width : '50',title : 'hour7',field : 'hour7'},
            {width : '50',title : 'hour8',field : 'hour8'},
            {width : '50',title : 'hour9',field : 'hour9'},
            {width : '50',title : 'hour10',field : 'hour10'},
            {width : '50',title : 'hour11',field : 'hour11'},
            {width : '50',title : 'hour12',field : 'hour12'},
            {width : '50',title : 'hour13',field : 'hour13'},
            {width : '50',title : 'hour14',field : 'hour14'},
            {width : '50',title : 'hour15',field : 'hour15'},
            {width : '50',title : 'hour16',field : 'hour16'},
            {width : '50',title : 'hour17',field : 'hour17'},
            {width : '50',title : 'hour18',field : 'hour18'},
            {width : '50',title : 'hour19',field : 'hour19'},
            {width : '50',title : 'hour20',field : 'hour20'},
            {width : '50',title : 'hour21',field : 'hour21'},
            {width : '50',title : 'hour22',field : 'hour22'},
            {width : '50',title : 'hour23',field : 'hour23'},
            {width : '50',title : 'hour24',field : 'hour24'}
        ]]
    });

    $("#queryBtn").click(function(){
        var frequencyName = $("#frequencyName").val();;
        $('#grid').datagrid({
            url:'${ctx}/frequency/search?frequencyName='+frequencyName,
            pageSize :20,
            pageList : [10, 20, 30, 40, 50 ],
            striped : true,
            rownumbers : true,
            pagination : true,
            toolbar : '#toolbar',
            columns : [[
                {width : '30', field : 'ck',checkbox:true},
                {width : '50',title : 'frequency_name',field : 'frequencyName'},
                {width : '50',title : 'hour1',field : 'hour1'},
                {width : '50',title : 'hour2',field : 'hour2'},
                {width : '50',title : 'hour3',field : 'hour3'},
                {width : '50',title : 'hour4',field : 'hour4'},
                {width : '50',title : 'hour5',field : 'hour5'},
                {width : '50',title : 'hour6',field : 'hour6'},
                {width : '50',title : 'hour7',field : 'hour7'},
                {width : '50',title : 'hour8',field : 'hour8'},
                {width : '50',title : 'hour9',field : 'hour9'},
                {width : '50',title : 'hour10',field : 'hour10'},
                {width : '50',title : 'hour11',field : 'hour11'},
                {width : '50',title : 'hour12',field : 'hour12'},
                {width : '50',title : 'hour13',field : 'hour13'},
                {width : '50',title : 'hour14',field : 'hour14'},
                {width : '50',title : 'hour15',field : 'hour15'},
                {width : '50',title : 'hour16',field : 'hour16'},
                {width : '50',title : 'hour17',field : 'hour17'},
                {width : '50',title : 'hour18',field : 'hour18'},
                {width : '50',title : 'hour19',field : 'hour19'},
                {width : '50',title : 'hour20',field : 'hour20'},
                {width : '50',title : 'hour21',field : 'hour21'},
                {width : '50',title : 'hour22',field : 'hour22'},
                {width : '50',title : 'hour23',field : 'hour23'},
                {width : '50',title : 'hour24',field : 'hour24'}
            ]]
        });
    })


});
function showDetailAdd(){
	$.dialog.open(ctx+'/frequency/showAdd', {
		lock: true,
		width:1100,
		height:'95%'
	});
}

function showInfo(){
    var selections = $('#grid').datagrid('getSelections');
    if (selections.length != 1) {
        $.messager.alert('提示:','只能选择一行记录!');
        return false;
    }
    var id = selections[0]['frequencyId'];
    $.dialog.open(ctx+'/frequency/showInfo?frequencyId='+id, {
        lock: true,
        width:1100,
        height:"95%"
    });
}


</script>
</html>