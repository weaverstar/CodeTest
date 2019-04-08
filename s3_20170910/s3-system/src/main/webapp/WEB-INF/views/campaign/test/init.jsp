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
th{
    font-size: 14px;
    color: #666;
    font-weight: normal;
}
.ui-multiselect-checkboxes span{
    position: relative;
    top: -3px;
    left: 10px;
}
.disabled{
    background:#a3a4a6 !important;
    border-color:#a3a4a6 !important;
}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
	<div class="dialogPage">
		<div class="om-panel-header">Test</div>
		<div class="editDiv">
			<table class="editTable">
				<tr>
					<th>campaign_id</th>
					<th>device_id</th>
					<th>landingpage</th>
					<th>operate</th>
				</tr>

				<tr>
					<td>
						<select id="campaignId" name="campaignId"  onchange="initEdit()">
							<option ></option>
							<c:forEach items="${campaignRuleModel }" var="column">
								<option value="${column.campaignId}">${column.campaignName}</option>
							</c:forEach>
						</select>
					</td>
					<td  id="deviceInfo">
						<input type="text" id="deviceId" name="deviceId">
					</td>
					<td>
						<input type="text" name="landingpage" id="landingpage">
					</td>
					<td>
						<div class="editBtn" id="buttonInfo">
                            <button class="disabled" disabled="disabled">禁止</button>
						</div>
					</td>
				</tr>
			</table>
</form>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
function testCampaign(){
    var campaignId = $("#campaignId").val();
    var deviceId = $("#deviceId").val();
    var landingpage = $("#landingpage").val();
    $.messager.progress({
        title:'提示',
        msg:'服务器正在处理中，请耐心等待....'
    })

    $.post(
        '${ctx}/campaignTest/testResult',{landingpage:landingpage,campaignId:campaignId,deviceId:deviceId},
        function (data) {
            //弹出信息窗口
            $(".messager-body").window('close');
            var url = "${ctx}/campaignTest/resultInfo?campaignId="+data.campaignId+"&deviceId="+data.deviceId+
                       "&landingpage="+data.landingpage+"&result="+data.result+"&reason="+data.reason;
            $.dialog.open(url,{
                lock: true,
                width:400,
                height:300
            })
        },
        'json'
    );
}
    function initEdit() {
        var campaignId = $("#campaignId").val();
        if(campaignId != ""){
            var button = "<button id=\"btnSubmit\" onclick=\"testCampaign()\" type=\"button\"class=\"button\">&nbsp;test&nbsp;</button>";
            $("#buttonInfo").html(button);
        }else{
            var button = "<button class=\"disabled\" disabled=\"disabled\">禁止</button>";
            $("#buttonInfo").html(button);
		}
        if (campaignId != "") {
			$.post(
				'${ctx}/campaign/showInfo?campaignId='+campaignId,
				{},
				function (data) {
                    $("#deviceId").val(data.campColumn);
                    $("#landingpage").val(data.landingpage);
				},
				'json'
			);
   		 }
    }

</script>
</html>