<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="insert">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>自定义词语：</td>
			<td><input type="text" id="word" name="word"  maxlength="50"  value=""></td>			
		</tr>		
	   </table>
	   <div class="editBtn">
			<button type="submit" class="button">&nbsp;保存&nbsp;</button>
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
		validType:['isExist["checkWordExist","word","该词语已经存在,请重新输入"]','maxLength[50]']
	}); 
 
	$(":submit").click(function(){
		if(!$("#form1").form('validate')){return false;}
	});
});
</script>
</html>