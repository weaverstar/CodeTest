<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style>
		.dialogPage{
			margin:0 auto;
		}
		.editBtn{
			padding:20px 0;
		}
	</style>
</head>

<body>
<form id="form1" action="update">
	<div class="dialogPage">
		<div class="om-panel-header">Add TimeScale</div>
		<table class="editDiv">
			<table class="editTable" style="margin: 2px;">
				<tr>
					<td><span class="required"></span>scalName：</td>
					<td><input type="text" id="frequencyName" name="frequencyName" value="${hourScaleModel.frequencyName}" readonly></td>
				</tr>
			</table>
			<div style="overflow: hidden;width:1055px;">
				<div style="float:left" class="editDiv">
				<input type="hidden" name="userId" name="userId" value="${userId}">
				<input type="hidden" name="userName" name="userName" value="${userName}">
				<input type="hidden" name="frequencyId" name="frequencyId" value="${hourScaleModel.frequencyId}">
				<table class="editTable" style="margin: 2px;">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>00:00-01:00:</td>
						<td><input type="text" index="1" id="hour1" name="hour1" value="${hourScaleModel.hour1}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>01:00-02:00:</td>
						<td><input type="text"  index="2"  id="hour2" name="hour2" value="${hourScaleModel.hour2}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>02:00-03:00:</td>
						<td><input type="text" index="3" id="hour3" name="hour3" value="${hourScaleModel.hour3}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>03:00-04:00:</td>
						<td><input type="text" index="4"  id="hour4" name="hour4" value="${hourScaleModel.hour4}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>04:00-05:00:</td>
						<td><input type="text" index="5"  id="hour5" name="hour5" value="${hourScaleModel.hour5}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>05:00-06:00:</td>
						<td><input type="text" index="6" id="hour6" name="hour6" value="${hourScaleModel.hour6}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>06:00-07:00:</td>
						<td><input type="text" index="7"  id="hour7" name="hour7" value="${hourScaleModel.hour7}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>07:00-08:00:</td>
						<td><input type="text" index="8"  id="hour8" name="hour8" value="${hourScaleModel.hour8}" readonly></td>
					</tr>
				</table>
			</div>

			<div style="float:left" class="editDiv">
				<table class="editTable" style="margin: 2px;">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>08:00-09:00:</td>
						<td><input type="text"  index="9" id="hour9" name="hour9" value="${hourScaleModel.hour9}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>09:00-10:00:</td>
						<td><input type="text" index="10"  id="hour10" name="hour10" value="${hourScaleModel.hour10}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>10:00-11:00:</td>
						<td><input type="text" index="11"  id="hour11" name="hour11" value="${hourScaleModel.hour11}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>11:00-12:00:</td>
						<td><input type="text" index="12" id="hour12" name="hour12" value="${hourScaleModel.hour12}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>12:00-13:00:</td>
						<td><input type="text" index="13"  id="hour13" name="hour13" value="${hourScaleModel.hour13}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>13:00-14:00:</td>
						<td><input type="text"  index="14" id="hour14" name="hour14" value="${hourScaleModel.hour14}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>14:00-15:00:</td>
						<td><input type="text"  index="15" id="hour15" name="hour15" value="${hourScaleModel.hour15}" readonly></td>
					</tr>
					<tr>
						<td><span class="required">*</span>15:00-16:00:</td>
						<td><input type="text" index="16"  id="hour16" name="hour16" value="${hourScaleModel.hour16}" readonly></td>
					</tr>
				</table>
			</div>

			<div style="float:left" class="editDiv">
				<table class="editTable" style="margin: 2px;">
					<tr>
						<th>Time slot</th>
						<th>Weight setting</th>
					</tr>
					<tr>
						<td><span class="required">*</span>16:00-17:00:</td>
						<td><input type="text"  index="17" id="hour17" name="hour17" value="${hourScaleModel.hour17}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>17:00-18:00:</td>
						<td><input type="text" index="18"  id="hour18" name="hour18" value="${hourScaleModel.hour18}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>18:00-19:00:</td>
						<td><input type="text" index="19"  id="hour19" name="hour19" value="${hourScaleModel.hour19}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>19:00-20:00:</td>
						<td><input type="text" index="20"  id="hour20" name="hour20" value="${hourScaleModel.hour20}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>20:00-21:00:</td>
						<td><input type="text"  index="21" id="hour21" name="hour21" value="${hourScaleModel.hour21}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>21:00-22:00:</td>
						<td><input type="text" index="22"  id="hour22" name="hour22" value="${hourScaleModel.hour22}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>22:00-23:00:</td>
						<td><input type="text"  index="23" id="hour23" name="hour23" value="${hourScaleModel.hour23}"></td>
					</tr>
					<tr>
						<td><span class="required">*</span>23:00-00:00:</td>
						<td><input type="text"  index="24" id="hour24" name="hour24" value="${hourScaleModel.hour24}"></td>
					</tr>
				</table>
			</div>
			</div>
	</div>
	</div>
</form>
<div id="container" style="min-width: 200px;min-height: 200px;"></div>
</body>
<script type="text/javascript">
    var chart ;
    var dataInfo =  [
		                    ${hourScaleModel.hour1},${hourScaleModel.hour2},${hourScaleModel.hour3},${hourScaleModel.hour4},${hourScaleModel.hour5},${hourScaleModel.hour6},${hourScaleModel.hour7},${hourScaleModel.hour8},
							${hourScaleModel.hour9},${hourScaleModel.hour10},${hourScaleModel.hour11},${hourScaleModel.hour12},${hourScaleModel.hour13},${hourScaleModel.hour14}, ${hourScaleModel.hour15},${hourScaleModel.hour16},
							${hourScaleModel.hour17},${hourScaleModel.hour18},${hourScaleModel.hour19},${hourScaleModel.hour20},${hourScaleModel.hour21},${hourScaleModel.hour22},${hourScaleModel.hour23},${hourScaleModel.hour24}
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
        //验证是否为空
        for(var i=1;i<25;i++) {
            $('#hour'+i).validatebox({
                required: true,
                digits:true
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
                  var position = $(this).attr("index");
                  if(/^\d+$/.test($(this).val())) {
                      dataInfo[position - 1] = parseInt($(this).val());
                  }
                  $("#container").html("");
                 chart = new Highcharts.Chart(option);
			})
		})
        //初始化图标
        chart = new Highcharts.Chart(option);
    });

    function restForm(){
        form1.reset();
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
</script>
</html>