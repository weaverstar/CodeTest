<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style>
		.editTable td input[type="text"],.editTable td input[type="text"]:focus{
			border:none;
			margin:0;
		}
		#frequencyName{
			border:1px solid #ccc;
		}
	</style>
</head>

<body>
<form id="form1" action="/frequency/insert">
	<div class="dialogPage">
		<div class="om-panel-header">Add TimeScale</div>
			<table class="editTable" style="margin: 2px;width:50%;">
				<tr>
					<td><span class="required"></span>scalName：</td>
					<td><input type="text" id="frequencyName" name="frequencyName" style=""></td>

					<td><span class="required"></span>RemainingPercentage：</td>
					<td><span id="last">100</span></td>
				</tr>
			</table>
		<div>
		<div style="overflow: hidden;width:1045px;">
			<div style="float:left" class="editDiv">
				<input type="hidden" name="userId" name="userId" value="${userId}">
				<input type="hidden" name="userName" name="userName" value="${userName}">
				<table class="editTable">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>00:00-01:00:</td>
						<td><input type="text"  index="1"  id="hour1" name="hour1"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>01:00-02:00:</td>
						<td><input type="text"  index="2"  id="hour2" name="hour2"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>02:00-03:00:</td>
						<td><input type="text"  index="3"  id="hour3" name="hour3"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>03:00-04:00:</td>
						<td><input type="text"   index="4"  id="hour4" name="hour4"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>04:00-05:00:</td>
						<td><input type="text"  index="5"   id="hour5" name="hour5"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>05:00-06:00:</td>
						<td><input type="text"  index="6"  id="hour6" name="hour6"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>06:00-07:00:</td>
						<td><input type="text"   index="7"   id="hour7" name="hour7"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>07:00-08:00:</td>
						<td><input type="text"  index="8"  id="hour8" name="hour8"  class="easyui-numberbox" ></td>
					</tr>
				</table>
			</div>
			<div style="float:left" class="editDiv">
				<table class="editTable">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>08:00-09:00:</td>
						<td><input type="text"  index="9"  id="hour9" name="hour9"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>09:00-10:00:</td>
						<td><input type="text"  index="10" id="hour10" name="hour10"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>10:00-11:00:</td>
						<td><input type="text"  index="11" id="hour11" name="hour11"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>11:00-12:00:</td>
						<td><input type="text"  index="12"  id="hour12" name="hour12"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>12:00-13:00:</td>
						<td><input type="text" index="13"  id="hour13" name="hour13"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>13:00-14:00:</td>
						<td><input type="text" index="14"  id="hour14" name="hour14"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>14:00-15:00:</td>
						<td><input type="text" index="15"  id="hour15" name="hour15"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>15:00-16:00:</td>
						<td><input type="text" index="16"  id="hour16" name="hour16"  class="easyui-numberbox" ></td>
					</tr>
				</table>
			</div>
			<div style="float:left" class="editDiv">
				<table class="editTable">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>16:00-17:00:</td>
						<td><input type="text" index="17"   id="hour17" name="hour17"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>17:00-18:00:</td>
						<td><input type="text" index="18"  id="hour18" name="hour18"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>18:00-19:00:</td>
						<td><input type="text" index="19"  id="hour19" name="hour19"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>19:00-20:00:</td>
						<td><input type="text" index="20"  id="hour20" name="hour20"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>20:00-21:00:</td>
						<td><input type="text"  index="21" id="hour21" name="hour21"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>21:00-22:00:</td>
						<td><input type="text"  index="22"  id="hour22" name="hour22"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>22:00-23:00:</td>
						<td><input type="text"  index="23" id="hour23" name="hour23"  class="easyui-numberbox" ></td>
					</tr>
					<tr>
						<td><span class="required">*</span>23:00-00:00:</td>
						<td><input type="text" index="24"  id="hour24" name="hour24"  class="easyui-numberbox" ></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="editBtn" style="padding: 20px;">
			<button id="btnSubmit" type="button"class="button">&nbsp;confirm&nbsp;</button>
			<button type="button" class="button" onclick="restForm();">&nbsp;reset&nbsp;</button>
		</div>
	</div>
</form>
<div id="container" style="min-width: 200px;min-height: 200px;"></div>
</body>
<script type="text/javascript">

    var chart ;
    var dataInfo =  [
        0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0
    ];
    var option = {
        chart: {
            renderTo: 'container',
            type: 'line',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: '曲线',
            x: -20
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24']
        },
        yAxis: {
            title: {
                text: 'scale'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'proportion',
            data:dataInfo
        }]
    }


    $(function(){
        //验证必填
        for(var i=1;i<25;i++) {
            $('#hour'+i).validatebox({
                required: true,
//                digits:true,

            });
        }
        //绑定提交事件
        $('#btnSubmit').click(function(){
            if($("#form1").form('validate')){
                var total = 0;
                for(var i=1 ;i<25;i++) {
                    var hour = $("#hour" + i).val()
                    if(hour != ""){
                        total += parseInt(hour);
                    }
                }
                if(total != 100){
                    $.messager.alert('提示:','权重加起来必须是100');
                    return false;
                }
                $.messager.progress({
                    title:'提示',
                    msg:'服务器正在处理中，请耐心等待....'
                });
                $('#form1').submit();
            }
        });

        //绑定change事件
        $("input[name!='frequencyName']").each(function(index,element){
            $(element).change(function(){
                var position = $(this).parent().prev().attr("index");
                if(/^\d+$/.test($(this).val())) {
                    dataInfo[position - 1] = parseInt($(this).val());
                }
                $("#container").html("");
                chart = new Highcharts.Chart(option);
            })
        })

        //绑定blur事件
        $("input[name!='frequencyName']").blur(function(){
            var sum = 100;
            for(var i=1;i<25;i++){
                var val = $("#hour"+i).val();
                if(val !=""){
                    sum = sum - parseInt(val);
                }
			}
            if(sum < 0){
                $("#last").html("<font color='red'>权重加起来不能超过100</font>");
            }else{
                $("#last").html(sum);
            }
		})




        //初始化图标
        chart = new Highcharts.Chart(option);
    });

    function resetChart(){
        dataInfo =  [
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        ];
        chart = new Highcharts.Chart(option);
	}


    function restForm(){
        form1.reset();
        resetChart();
        $("#last").html(100);
    }


    function initFrequency(){
        $.post(
            'getHourScale',
            {},
            function(data) {
                var str = "<option value=\"0\"></option>";
                for(var i=0;i<data.length;i++){
                    str +="<option value=\""+data[i].frequencyId+"\" ";
                    if(data[i].frequencyId == "${campaignRuleModel.frequencyId}"){
                        str += " selected";
                    }
                    str += ">"+data[i].frequencyName+"</option>"
                }
                $("#frequencyId",window.parent.frames[0].document).html(str);
            },
            'json'
        );
    }


    /**
     * 自定义 校验数字的方法
     * author saerdna
     */


</script>
</html>