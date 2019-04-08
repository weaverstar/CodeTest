<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        .ui-multiselect-checkboxes span{
            position: relative;
            top:-4px;
            left:5px;
        }
        .textbox{
            width: 156px !important;
            height: 28px !important;
            border-radius: 4px;
        }
        .textbox-addon-right{
            top:4px;
        }
        .textbox-text{
            width: 100% !important;
            border: none !important;
            line-height: 27px !important;
            margin-top: 0 !important;
        }
        .textbox-icon{
            position: relative;
            top:-4px;
        }
        .editTable{
            width:600px;
        }
        .editBtn{
            padding:20px 0;
        }
        .editTable tr{
            height:38px;
        }

		.sreach-input,.select-all{
			width:500px !important;
			padding-left:5px;
		}
		.search-result{
			position: absolute;
			top:64px;
			width:504px;
			background: #fff;
			z-index: 10;
			border:1px solid #ccc;
			height: 130px;
			overflow: scroll;
			display: none;
		}
		.search-result li{
			height:28px;
			line-height:28px;
			padding-left:5px;

		}
		.search-result li:hover{
			background:#ccc;
		}

    </style>
</head>
<body>
<form id="form1" action="update">
	<input type="hidden" name="status" value="0">
	<input type="hidden" id="campaignId" name="campaignId" value="${campaignRuleModel.campaignId}">
	<div class="dialogPage">
		<div class="om-panel-header">Edit</div>
		<div class="editDiv">
			<table class="editTable">
				<tr>
					<td><span class="required">*</span>campaign_id：</td>
					<td><input type="text" id="campaignId2" name="campaignId2" value="${campaignRuleModel.campaignId}" readonly></td>
					<td><span class="required">*</span>campaign_name：</td>
					<td><input type="text" id="campaignName" name="campaignName" value="${campaignRuleModel.campaignName}" ></td>
				</tr>
				<tr>
					<td><span class="required">*</span>amountpd(万)：</td>
					<td><input type="text" id="amountpd" name="amountpd" value="${campaignRuleModel.amountpd}"></td>
					<td>column：</td>
					<td>
						<select type="text" id="campColumn" name="campColumn" multiple="multiple"  size="5" style="width:200px;" >
							<option value="IDFA">IDFA</option>
							<option value="UA">UA</option>
							<option value="IP">IP</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><span class="required">*</span>frequency：</td>
					<td colspan="3">
						<select type="text" id="frequencyId" name="frequencyId" onclick="initFrequency()">

						</select>
						<button id="create" type="button"class="button" onclick="createTimePre();" style="float:right">&nbsp;create&nbsp;</button>
					</td>
				</tr>
				<tr>
					<td><span class="required">*</span>landingpage：</td>
					<td colspan="3" >
						<input type="text" id="landingpage" name="landingpage" style="width:520px" value="${campaignRuleModel.landingpage}">
					</td>
				</tr>
				<tr>
					<td><span class="required">*</span>last operater：</td>
					<td>
						<input type="hidden" id="userId" name="userId"  value="${userId}">
						<input type="hidden" id="userName" name="userName" value="${userName}">
						${userName}
					</td>
					<td><span class="required">*</span>rd：</td>
					<td><input type="text" id="rd" name="rd" value="${campaignRuleModel.rd}"></td>
				</tr>
				<tr>
					<td><span class="required">*</span>beginDate：</td>
					<td><input id="startTime" name="startTime"  class="easyui-datetimebox" value="${campaignRuleModel.startTime}" /></td>
					<td><span class="required">*</span>endDate：</td>
					<td><input id="endTime" name="endTime"  class="easyui-datetimebox" value="${campaignRuleModel.endTime}"/></td>
				</tr>
				<tr>
					<td><span class="required">*</span> blacklist：</td>
					<td colspan="3" style="position:relative">
						<input id="blacklist" type="hidden" name="blacklist" value="${campaignRuleModel.blacklist}">
                        <input type="text" class="select-all" placeholder="此处为blacklist选中结果,只能删除不能再次输入" value="${nameInfo}">
						<!-- <input type="text" class="sreach-input" value="${nameInfo}"> -->
                        <input type="text" class="sreach-input">
						<ul class="search-result">
						</ul>
					</td>
				</tr>
				<tr>
					<td><span class="required">*</span>province：</td>
					<td>
						<select  id="province" name="province"  multiple="multiple"  size="5">

						</select>
					</td>
				</tr>
			</table>
			<div class="editBtn">
				<button id="btnSubmit" type="button"class="button">&nbsp;confirm&nbsp;</button>
				<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;close&nbsp;</button>
			</div>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
    $(function(){
        //验证长度不超过32位,不能重命,不能为空
        $('#btnSubmit').click(function(){
            if($("#form1").form('validate')){
                $.messager.progress({
                    title:'提示',
                    msg:'服务器正在处理中,请耐心等待....'
                })

                $('#form1').submit();
            }
        });
        //初始化省份
        initBlackList();
        //初始化省份
        initprovince();
        //初始化时间权重下拉框
        initFrequency();
        intiColumn();

        var searchTime=null;
        var searchVal="";
        var inputVal="";
        var inputArr=[];
        var currentStr=[];
        var allSelect=[];

        $(".select-all").blur(function(){
            getFinallyId();
            return false;
        })

        $(".sreach-input").click(function(event) {
            $(".search-result").slideDown(100);
            return false;
        });
        $(".sreach-input").keyup(function(event) {
            inputVal=$(this).val();
            // inputArr=inputVal.split(",")
            // currentStr=inputArr.slice(0,inputArr.length-1).join(",");
            // searchVal=inputArr[inputArr.length-1;
            inputArr=$(".select-all").val().split(",");
            currentStr=inputArr.slice(0,inputArr.length-1).join(",");
            searchVal=inputVal;
            $(".search-result").slideDown(100);
            clearTimeout(searchTime);
            searchTime=setTimeout(function(){
                initBlackList(currentStr,searchVal);
            },100)
            return false;
        });
        var resultAll="";
        var selectVal="";
        var dataId="";

        var initBlacklist=$(".select-all").val().split(",")
        var initId=$("#blacklist").val().split(",");
        for (var i = 0; i < initId.length; i++) {
            allSelect.push({
                dataId:initId[i],
                selectVal:initBlacklist[i]
            })
        }
        console.log(allSelect)
        $(".search-result").on("click","li",function(event) {
            for (var i = 0; i < allSelect.length; i++) {
                if($(this).text()==allSelect[i].selectVal){
                    return false;
                }
            }
            allSelect.push({
                dataId:$(this).attr('data-id'),
                selectVal:$(this).text()
            })
            // inputArr=$(".sreach-input").val().split(",");
            /*获取已经选中的*/
            inputArr=$(".select-all").val().split(",");
            selectVal=$(this).text()+",";
            if(inputArr.length==0){
                inputArr[0]=selectVal
            }else{
                inputArr[inputArr.length-1]=selectVal;
            }
            //$(".sreach-input").val(inputArr.join(","));
             $(".select-all").val(inputArr.join(","));
             getFinallyId();
        });
        var finallyId=[];
        $(".sreach-input").blur(function(){
            setTimeout(function(){
                getFinallyId();
            },100)
        });

        function getFinallyId(){
            finallyId=[];
            $(".search-result").slideUp(100);
            //inputArr=$(".sreach-input").val().split(",");
            inputArr=$(".select-all").val().split(",");
            inputArr[inputArr.length-1]="";
            //$(".sreach-input").val(inputArr.slice(0, inputArr.length).join(","));
            $(".select-all").val(inputArr.slice(0, inputArr.length).join(","));
            for (var i = 0; i < inputArr.length; i++) {
                for (var j = 0; j < allSelect.length; j++) {
                    if(inputArr[i]==allSelect[j].selectVal){
                        finallyId.push(allSelect[j].dataId);
                        break;
                    }
                }
            }
            $("#blacklist").val(finallyId.join(","))
        }

        function initBlackList(parm1,parm2){
            $.post(
                '${ctx}/blackList/init',
                {parm1:parm1,parm2:parm2},
                function(data) {
                    $(".search-result").html("");
                    var str = "";
                    for(var i=0;i<data.length;i++){
                        str +="<li data-id =\""+data[i].blackCode+"\">"+data[i].blackName+"</li>"
                    }
                    $(".search-result").html(str);
                },
                'json'
            );
        }
    });

    function initprovince() {
         var array =new Array (11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82);
        var array2 = new Array("北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江市", "上海市", "江苏省", "浙江省", "安徽省",
            "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区", "海南省", "重庆市", "四川省",
            "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "台湾省", "香港特别行政区","澳门特别行政区");

        var ins = "${campaignRuleModel.province}".split(",");
        var str = "";
        var selected = "";
        for (var i = 0; i < array.length; i++) {
            selected = "";
            if($.inArray(""+array[i]+"",ins) > -1){
                selected = "selected";
            }
            str += "<option value=\"" + array[i] + "\" "+selected+">" + array2[i] + "</option>";
        }
        $("#province").html(str);
        $("#province").multiselect();
    }

    function createTimePre(){
        $.dialog.open(ctx+'/campaign/showTimePreAdd?dictId=${dictId}', {
            lock: true,
            width:1100,
            height:'95%'
        });
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
                $("#frequencyId").html(str);
            },
            'json'
        );
    }

    function initBlackList(){
        $.post(
            '${ctx}/blackList/init',
            {},
            function(data) {
                var str = "";
                var ins = "${campaignRuleModel.blacklist}".split(",");
                var selected = "";
                for(var i=0;i<data.length;i++){
                    selected = "";
                    if($.inArray(data[i].blackCode,ins) > -1){
                        selected = "selected";
                    }
                    str +="<option value=\""+data[i].blackCode+"\" "+selected+" >"+data[i].blackName+"</option>"
                }
                $("#blacklist").html(str);
                $("#blacklist").multiselect();
            },
            'json'
        );
    }

    function intiColumn(){
        var array = new Array("IDFA", "UA", "IP");
        var ins = "${campaignRuleModel.campColumn}".split(",");
        var selected = "";
        var str = "";
        for (var i = 0; i < array.length; i++) {
            selected = "";
            if($.inArray(array[i],ins) > -1){
                selected = "selected";
            }
            str += "<option value=\"" + array[i] + "\" "+selected+">" + array[i] + "</option>";
        }
        $("#campColumn").html(str);
        $("#campColumn").multiselect();
	}
</script>
</html>