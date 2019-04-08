<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="id" name="id" value="${word.id}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>自定义词语：</td>
			<td><input type="text" id="word" name="word"  maxlength="50"  value="${word.word}"  /></td>
		</tr>	
		<tr>
			<td>状态：</td>
			<td><input id="state" name="state" type="combo"></td> 
		</tr>		
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button" >&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	//验证长度不超过50位，不能重复，不能为空
	$('#word').validatebox({
		required:true,
		validType:['isExist["checkWordExist","word,id","修改后的词语和已经存在的重复"]','maxLength[50]']  
	}); 
	
    $('#state').combobox({  
    	data:JSON.parse('${statusCombo}'),
    	panelHeight:'auto',
    	editable:false,
    	value:'${word.state}'
    }); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>