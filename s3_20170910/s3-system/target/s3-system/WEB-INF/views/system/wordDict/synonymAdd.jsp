<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	.dialogPage .editDiv .editTable a{display: inline-block;margin-top: 5px;margin-right: 5px; cursor: pointer;}
</style>
</head>
<body>
<form id="form1" action="insert" method="post">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
			<!-- <tr>
				<td><span class="required">*</span>原词：</td>
				<td colspan="2"><input type="text" id="synonymTo" name="synonymTo" maxlength="50" /></td>			
			</tr> -->	
			<tr>
				<td><span class="required">*</span>同义词：</td>
				<td><input type="text" id="sT" name="synonymFrom"  maxlength="50" /></td>
				<!-- <td style="text-align: left;"><a onclick="javascript:addOne();"><span class="menu1"></span></a></td> -->				
			</tr>
	   </table>
	   <div class="editBtn">
			<button type="button" id="subM" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	
	//验证长度不超过50位，不能重复，不能为空 
	/* $('#synonymTo').validatebox({
		required:true,
		validType:['isExist["checkSynonymExist","synonymTo","该词已经存在,请重新输入"]','maxLength[50]']
	}); */ 
	$('#sT').validatebox({required:true}); 
 
	$("#subM").click(function(){
		if(!$("#form1").form('validate')){return false;}
		
		/* $("input[name='synonymFrom']").each(function(i){
			var t=$(this);
			if($.trim(t.val())==''){
				t.parent().parent().remove();
			}
		}); */
		
		$("#form1").submit();
		
	});
});

function addOne(){
	var t=$(".editTable");
	if($(".editTable tr").length > 10){
		return false;
	}
	
	var html=[];
	html.push('<tr><td><a onclick="javascript:deleteOne(this);"><span class="menu11"></span></a></td>');
	html.push('<td><input type="text" name="synonymFrom"  maxlength="50"></td>');
	html.push('<td style="text-align: left;"><a onclick="javascript:addOne();"><span class="menu1"></span></a></td></tr>');
	t.append(html.join(''));
}

function deleteOne(obj){
	var o=$(obj);
	var tr=o.parent().parent();
	tr.remove();
	
}

</script>
</html>