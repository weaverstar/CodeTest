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
.datagrid-btable,.datagrid-header,.datagrid-header-inner,.datagrid-htable{width:100%;}

#qxt{
	position:absolute;
    right: -24%;
    top: 110px;
	/*background-color: #aa00aa;*/
	width:49%;
	height:300px;
    transform: translateX(-50%);
    -webkit-transform: translateX(-50%);
}
.datebox .combo-arrow{
	background-image: url(${img}/datebox_arrow.png);
}
</style>
	<title>s2s log</title>
</head>
<body>


<div id="toolbar">
<br>
<form id="list" action="list">
<input id="convertionTime" type="hidden"  name="convertionTime" >
<div id="search" class="easyui-panel" title="" data-options="fit:true,collapsible:true,border:0">
  <table class="searchTable">
	   <tr>
			<td>campaign id：</td>
			<td><input type="text" id="campId" name="campId"></td>
			<td width="50px">campaign name：</td>
			<td><input type="text" id="campainName" name="campainName"></td>
			<td style="width:25px;">&nbsp;</td>
			<td><button id="queryBtn" type="button" class="button">search</button></td>
		</tr>
		<tr>
		   <td>DayScal：</td>
			<td><input id="createTimeStart" name="createTimeStart" type="datetime" class="easyui-datebox"></input>
				至 <input id="createTimeEnd" name="createTimeEnd"  type="datetime" class="easyui-datebox"/>
			</td> 
		    <td>search day：</td>
			<td >
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
<br>
<table id="grid" data-options="border:false,fit:false" style="width:50%;height:87%;"></table>
<div id="qxt" ></div>
</body>
<script type="text/javascript">
//图表下标
var xData = ["00:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20;00","21：00","22：00","23：00"];

$(function() {
	$('#grid').datagrid({
        onClickRow:function(rowIndex,rowData){
        	initLine(rowData);
        },
        onLoadSuccess:drawQxt,
        singleSelect:true,
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
        pagination: true,
	    columns : [[ {width : '80',title : 'campaign id',field : 'campaignId',align:'center'},
	                 {width : '150',title : 'campaign name',field : 'campaignName',align:'center'},
	                 {width : '150',title : 'convertion_day',field :'convertionDay',align:'center',formatter:function(v){return v.substr(0,10);}},
	                 {width : '100',title : 'click',field : 'succeedNum',align:'center'}
	                 ]]
	}); 
});
/**
 * 表格加载成功 事件
 */
function drawQxt(data) {
    var day;
    if(data.rows[0]!=null){
        day = data.rows[0].convertionDay;
	}
    $.get("perView", { day:day, campaignId:data.rows[0].campaignId},
        function(data){
            echartLine(xData,data);
        });
}
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
        "M+" : this.getMonth()+1, 
        "d+" : this.getDate(), 
        "h+" : this.getHours(), 
        "m+" : this.getMinutes(), 
        "s+" : this.getSeconds(), 
        "q+" : Math.floor((this.getMonth()+3)/3), 
        "S" : this.getMilliseconds() 
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
    $("#id01").css("color","#AFABAB");
    $("#id02").css("color","#AFABAB");
    var conditionForms = $("#list");
    var conditionArray = conditionForms[0].elements;
    for(var i=0;i<conditionArray.length;i++){
        var field = conditionArray[i];
        field.value = "";
	}
}
function initLine(rowData) {
	var day = rowData["convertionDay"];
	var campaignId = rowData["campaignId"];
    $.get("perView", { day:day ,campaignId:campaignId },
        function(data){
            echartLine(xData,data);
        });
}

function echartLine(xData,data){
	/*获取插入的div的id*/
    var myChart = echarts.init(document.getElementById('qxt'));
	/*图表的一些配置*/
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['上报数据','回调数据']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        calculable : true,
        xAxis : [
                 {
                     type : 'category',
                     data : xData,
                     axisTick: {
                         alignWithLabel: true
                     }
                 }
             ],
          yAxis : [
                   {
                            type: 'value',
                            name: '上报数据',
                            position: 'left'
                   },
                   {
                       type: 'value',
                       name: '回调数据',
                       position: 'right'
                   },
               ],
        series: [
            {
                name:'上报数据',
                type:'line',
                stack: '总量',
                data:data.send
            },
            {
                name:'回调数据',
                type:'line',
                yAxisIndex: 1,
                data:data.back
            }
        ]
        
    };
    myChart.setOption(option);
}
</script>
</html>