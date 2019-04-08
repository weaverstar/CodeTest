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
        .dialogPage{
            margin:0 auto;
        }
        /*.editTable{
            width:600px;
        }*/
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
            display: none;
			height: 130px;
			overflow: scroll;
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
<form id="form1" action="/resale/update.action">
	<input type="hidden" name="resaleId" value="${resaleModel.resaleId}">
	<div class="dialogPage">
		<div class="om-panel-header">新增</div>
		<div class="editDiv">
			<table class="editTable">
				<tr>
					<td><span class="required">*</span>产品类型：</td>
					<td ><input type="text" id="productType" name="productType" type="combo"></td>
					<td><span class="required">*</span>产品名称：</td>
					<td ><input type="text" id="product" name="product"  type="combo" ></td>
				</tr>
				<tr>
					<td><span class="required">*</span>单价：</td>
					<td ><input type="text" id="price" name="price"  class="easyui-numberbox" value="${resaleModel.price}"></td>
					<td><span class="required">*</span>数量：</td>
					<td >
						<input type="text" id="productNum" name="productNum" class="easyui-numberbox" value="${resaleModel.productNum}">
					</td>
				</tr>
				<tr>
					<td><span class="required">*</span>单位：</td>
					<td colspan="3"><input type="text" id="unit" name="unit" type="combo"></td>
				</tr>
			</table>
			<div class="editBtn">
				<button id="btnSubmit" type="button"class="button">&nbsp;确认&nbsp;</button>
				<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
			</div>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
    $(function(){
        //验证长度不超过32位,不能重命,不能为空
		$('#campaignName').validatebox({
		 	required:true
		});
		$('#amountpd').validatebox({required:true});
		$('#frequencyId').validatebox({required:true});
        $('#landingpage').validatebox({required:true});
        $('#rd').validatebox({required:true});

        $('#product').combobox({
            data:JSON.parse('${productDetailCombo}'),
            editable:true,
            value:'${resaleModel.product}',
            width:216,
            panelHeight:'auto'
        });

        $('#productType').combobox({
            data:JSON.parse('${productCombo}'),
            editable:true,
            width:216,
			value:'${resaleModel.productType}',
            panelHeight:'auto',
            onSelect:function(record){
                var value = record.value;
                $.post("/resale/detialDict.action", {"type":value},
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

        $('#unit').combobox({
            data:JSON.parse('${unitCombo}'),
            editable:true,
			value:'${resaleModel.unit}',
            width:216,
            panelHeight:'auto'
        });

        $('#btnSubmit').click(function(){
            if($("#form1").form('validate')){
                $.messager.progress({
                    title:'提示',
                    msg:'服务器正在处理中,请耐心等待....'
                });
                $('#form1').submit();
            }
        });
    });
</script>
</html>