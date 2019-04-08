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
<form id="form1" action="update">
    <input type="hidden" name="resaleId" value="${resaleModel.resaleId}">
    <div class="dialogPage">
        <div class="om-panel-header">新增</div>
        <div class="editDiv">
            <table class="editTable">
                <tr>
                    <td><span style="font-weight:bold">批次号:</span></td>
                    <td><span style="font-weight:bold">${flowId}</span></td>
                    <input type="hidden" name="flowId" value="${flowId}">
                </tr>
            </table>
            <table style="width:90%" id="detail">
                <tr>
                    <td colspan="6" style="text-align: right">
                        <div style="float:right"><span class="menu72"></span><span onclick="delRow()" style="margin-bottom:50px;">删除</span></div>
                        <div style="margin-right: 60px;"><span class="menu71"></span><span onclick="addRow()">新增</span></div>
                    </td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th>产品类型</th>
                    <th>产品名称</th>
                    <th>零售单价</th>
                    <th>数量</th>
                    <th>单位</th>
                </tr>

            <c:forEach items="${resaleModelList}" var="resaleModel" varStatus="index">
                <tr id="edit_num">
                    <td>
                        <input type="checkbox" id="num_${index.index}_0">
                    </td>
                    <td>
                        <input type="hidden" id="productTypeId_${index.index}_1" name="productTypeId_${index.index}_1" value="${resaleModel.product_type}">
                        <input type="text" id="productType_${index.index}_1" name="productType_${index.index}_1" value="${resaleModel.productType}">
                    </td>
                    <td>
                        <input type="text" id="product_${index.index}_2" name="product_${index.index}_2" value="${resaleModel.product}">
                    </td>
                    <td>
                        <input type="text" id="price_${index.index}_3" name="price_${index.index}_3" value="${resaleModel.price}">
                    </td>
                    <td>
                        <input type="text" id="productNum_${index.index}_4" name="productNum_${index.index}_4" value="${resaleModel.productNum}">
                    </td>
                    <td>
                        <input type="text" id="unit_${index.index}_5" name="unit_${index.index}_5" value="${resaleModel.unit}">
                    </td>
                </tr>
            </c:forEach>
                <input type="hidden" id="trNum" name="trNum" value="${trNum}">
                <input type="hidden" id="mainId" name="mainId" value="${mainId}">
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
        $("#detail").find("tr[id='edit_num']").each(function(){
                var allMap =  JSON.parse('${allCombo}');
                $(this).find("input").each(function(){
                var input = $(this);
                var id = input.attr("id");
                var index = id.split("_")[1];
                var value = input.val();
                if(id.indexOf("productType_") > -1){
                    comboxComponent(id,"product_"+index+"_2",'${productCombo}',value);
                }else if(id.indexOf("product_") > -1){
                    var productTypeId = $("#"+"productTypeId_"+index+"_1").val();
                    comboxComponent(id,"",allMap[productTypeId],value,"price_"+index+"_3");
                }else if(id.indexOf("unit_") > -1){
                    comboxComponent(id,"",'${unitCombo}',value);
                }else if(id.indexOf("price_") > -1){
                    $("#price_"+index+"_3").numberbox({disabled:false});
                }else if(id.indexOf("productNum_") > -1){
                    $("#productNum_"+index+"_4").numberbox({disabled:false});
                }
            })
        })
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

    function addRow(){
        var table = document.getElementById("detail");
        var tr = table.insertRow();
        var trNum = parseInt($("#trNum").val()) + 1;
        for(var i=0;i<6;i++){
            var td = tr.insertCell(i);
            if(i == 0){
                td.innerHTML = '<input type="checkbox" id="num_'+trNum+'_'+i+'">';
            } else if(i == 1){
                td.innerHTML = '<input type="text" id="productType_'+trNum+'_'+i+'" name="productType_'+trNum+'_'+i+'" type="combo">';
            }else if(i == 2){
                td.innerHTML = '<input type="text" id="product_'+trNum+'_'+i+'" name="product_'+trNum+'_'+i+'" type="combo">';
            }else if(i == 3){
                td.innerHTML = '<input type="text" id="price_'+trNum+'_'+i+'" name="price_'+trNum+'_'+i+'">';
            }else if(i == 4){
                td.innerHTML = '<input type="text" id="productNum_'+trNum+'_'+i+'" name="productNum_'+trNum+'_'+i+'">';
            }else{
                td.innerHTML = '<input type="text" id="unit_'+trNum+'_'+i+'" name="unit_'+trNum+'_'+i+'" type="combo">';
            }
            comboxComponent("product_"+trNum+"_2","","","","price_"+trNum+"_3");
            var data = '${productCombo}';
            comboxComponent("productType_"+trNum+"_1","product_"+trNum+"_2",data,"","");
            var data2 = '${unitCombo}';
            comboxComponent("unit_"+trNum+"_5","",data2,"","");
            $("#price_"+trNum+"_3").numberbox({disabled:false});
            $("#productNum_"+trNum+"_4").numberbox({disabled:false});
        }
        var trNum = parseInt($("#trNum").val()) + 1;
        $("#trNum").val(trNum)
    }

    function delRow(){
        $("#detail").find("input[type='checkbox']").each(function(){
            if($(this).is(':checked')){
                $(this).parent().parent().remove();
                var trNum = parseInt($("#trNum").val()) - 1;
                $("#trNum").val(trNum)
            }
        })
    }

    function comboxComponent(typeid,id,data,value){
        var temp;
        if(data == ""){
            temp = [];
        }else{
            if(typeof data == 'string'){
                temp = JSON.parse(data);
            }else{
                temp = data;
            }
            for(var i=0;i<temp.length;i++){
                var text = temp[i].text
                if(text == value){
                    temp[i].selected = true;
                }
            }
        }
        $('#'+typeid).combobox({
            data:temp,
            editable:true,
            width:216,
            panelHeight:'auto',
            onSelect:function(record) {
                var value = record.value;
                if (id != "") {
                    $.post(ctx+"wholeSale/getProduct.action", {"id": value},
                        function (row) {
                            var temp = []
                            for (var i = 0; i < row.length; i++) {
                                temp.push(row[i]);
                            }
                            $("#"+id).combobox("loadData", temp);
                        }, "json");
                }else{
                    if(typeid.indexOf("product_") > -1){
                        $.post(ctx+"wholeSale/getPrice.action", {"id": value},
                            function (row) {
                                $("#"+td).numberbox('setValue',row.price);
                            }, "json");
                    }
                }
            }
        })
    }
</script>
</html>