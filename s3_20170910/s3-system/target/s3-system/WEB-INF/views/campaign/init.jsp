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
		<td>campaignName：</td>
		<td><input type="text" id="campaignNameSearch" name="detailName"></td>
		<td style="width:15px;">&nbsp;</td>
		<td><button id="queryBtn" type="button" class="button">search</button></td>
		<td><button id="clearBtn" type="button" class="button">clean</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">Campaign List</div>
	<div class="icon">
		<ul>
	   		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>create</a></li>
	   		<li><a href="#" onclick="showEdit('/campaign/showEdit','campaignId',800,500);"><span class="menu13"></span>update</a></li>
	   		<li><a href="#" onclick="removeRow('campaignId');"><span class="menu11"></span>delete</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="border:false,fit:true"></table>
</body>
<script type="text/javascript">
$(function() {
    $('#grid').datagrid({
        url:'${ctx}/campaign/search?dictId=${dictId}',
        pageSize :20,
        pageList : [10, 20, 30, 40, 50],
        // striped : true,
        rownumbers : true,
        pagination : true,
        toolbar : '#toolbar',
        // singleSelect : true,
        columns : [[ {width : '70', field : 'ck',checkbox:true},
            {width : '70',title : 'status',field : 'status',formatter:function(val,row,index){
                if(val == 0){
                    return "<input type=\"checkbox\" onclick=\"updateStatus("+val+","+row.campaignId+")\" class='al-toggle-button'>";
                }else{
                    return "<input type=\"checkbox\" onclick=\"updateStatus("+val+","+row.campaignId+")\" checked class='al-toggle-button'>";
                }
            }},
            {width : '100',title : 'campaign_id',field : 'campaignId'},
            {width : '100',title : 'campaign_name',field : 'campaignName'},
            {width : '100',title : 'amountpd',field : 'amountpd'},
            {width : '100',title : 'frequency',field : 'frequencyId',formatter:function(val,row,index){
               return row.hourScaleModel.frequencyName;
            }},
            {width : '200',title : 'landingpage',field : 'landingpage'},
            {width : '100',title : 'column',field : 'campColumn'},
            {width : '100',title : 'last operater',field : 'userName'},
            {width : '100',title : 'rd',field : 'rd'},
            {width : '100',title : 'startTime',field : 'startTime'},
            {width : '100',title : 'endTime',field : 'endTime'}
        ]]
    });

	$("#queryBtn").click(function(){
        $('#grid').html("");
	    var campaignNameSearch = $("#campaignNameSearch").val();
        $('#grid').datagrid({
            url:'${ctx}/campaign/search?campaignNameSearch='+campaignNameSearch,
            pageSize :20,
            pageList : [10, 20, 30, 40, 50 ],
            striped : true,
            rownumbers : true,
            pagination : true,
            toolbar : '#toolbar',
            columns : [[ {width : '50', field : 'ck',checkbox:true},
                {width : '70',title : 'status',field : 'status',formatter:function(val,row,index){
                    if(val == 0){
                        return "<input type=\"checkbox\" onclick=\"updateStatus("+val+","+row.campaignId+")\" >";
                    }else{
                        return "<input type=\"checkbox\" onclick=\"updateStatus("+val+","+row.campaignId+")\" checked>";
                    }
                }},
                {width : '100',title : 'campaign_id',field : 'campaignId'},
                {width : '200',title : 'campaign_name',field : 'campaignName'},
                {width : '100',title : 'amountpd',field : 'amountpd'},
                {width : '100',title : 'frequency',field : 'frequencyId',formatter:function(val,row,index){
                    return row.hourScaleModel.frequencyName;
                }},
                {width : '300',title : 'landingpage',field : 'landingpage'},
                {width : '100',title : 'column',field : 'campColumn'},
                {width : '100',title : 'last operater',field : 'userName'},
                {width : '100',title : 'rd',field : 'rd'},
                {width : '200',title : 'startTime',field : 'startTime'},
                {width : '200',title : 'endTime',field : 'endTime'}
            ]]
        });
	})
});
function showDetailAdd(){
	$.dialog.open(ctx+'/campaign/showAdd?dictId=${dictId}', {
		lock: true,
		width:800,
		height:500
	});
}

function updateStatus(val,campaignId){
    var status = 0;
    if(val == 0){
        status =1;
	}else{
        status =0;
	}
    $.post(
        'updateStatus',
        {status:status.toString(),campaignId:campaignId.toString()},
		function(data) {
            $("#grid").datagrid('reload');
		},
		'json'
	);
}
</script>
</html>