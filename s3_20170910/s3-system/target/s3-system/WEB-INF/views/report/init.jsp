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
	<title>convertion detail</title>
</head>
<body>


<div id="toolbar">
	<form id="list" action="list">
		<div id="search" class="easyui-panel" title="" data-options="fit:true,collapsible:true,border:0">
		  <table class="searchTable">
			<tr>
				<td>campaign id：</td>
				<td><input type="text" id="campId" name="campId"></td>
				<td>deviceid：</td>
				<td><input type="text" id="deviceId" name="deviceId"></td>
				<td style="width:25px;">&nbsp;</td>
				<td><button id="queryBtn" type="button" class="button">search</button></td>
			</tr>
		   <tr>
				<td>time：</td>
				<td><input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"></input>
					至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
				</td>  
				<td>search day：</td>
				<td ><input type="hidden" id="convertionTime" name="convertionTime">
					<a  id="id01" style="color:#AFABAB;font-size:20px;text-decoration:none;" href="javascript:getToday()">today</a>&nbsp;&nbsp;
					<a  id="id02" style="color:#AFABAB;font-size:20px;text-decoration:none;" href="javascript:getYesterday()">yesterday</a>
				</td>
				<td style="width:15px;">&nbsp;</td>
				<td><button id="clearBtnSpe" onclick="clearAllCondition()" type="button" class="button">clean</button></td>
			</tr>
		   </table>
		</div>
	</form>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
        pagination: true,
		toolbar : '#toolbar',
	    columns : [[ {width : '100',title : 'campaign id',field : 'campaignId'},
			         {width : '150',title : 'campaign name',field : 'campaignName'},
	                 {width : '300',title : 'deviceid',field : 'deviceid'},
	                 {width : '250',title : 'convertion time',field : 'convertionTime'}
	                 ]]
	}); 
});

/**
 * 获得今天时间戳的方法
 */
function getToday() {
    var today = new Date();
    $('#convertionTime').val(today.Format("yyyy-MM-dd"));
    $("#id01").css("color","#870000");
    $("#id02").css("color","#AFABAB");
    
}

/**
 * 获取昨天的时间戳
 */
function getYesterday() {
    var day1 = new Date();
    day1.setTime(day1.getTime()-24*60*60*1000);
    $('#convertionTime').val(day1.Format("yyyy-MM-dd"));
    $("#id02").css("color","#870000");
    $("#id01").css("color","#AFABAB");
}



Date.prototype.Format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month

        "d+" : this.getDate(), //day

        "h+" : this.getHours(), //hour

        "m+" : this.getMinutes(), //minute

        "s+" : this.getSeconds(), //second

        "q+" : Math.floor((this.getMonth()+3)/3), //quarter

        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

/**
 * 清空按钮事件
 */
function clearAllCondition() {
	  $("#id02").css("color","#AFABAB");
	  $("#id01").css("color","#AFABAB");
	  $("#convertionTime").val("");
    var conditionForms = $("#list");
    var conditionArray = conditionForms[0].elements;

    for(var i=0;i<conditionArray.length;i++){
        var field = conditionArray[i];
        field.value = "";
	}
}

</script>
</html>