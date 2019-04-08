<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
<meta name="renderer" content="webkit">
<style>
.content{margin-top:15px}
.skin{padding:10px 0 10px 5px;margin-left:100px;font-size:12px;border:#ddd 1px solid;width:680px;}
.tleft{float:left;width:100px;vertical-align:middle;}
.tname{float:right;font-weight:bold;color:#0165BA;font-size:14px;}
.bleft{width:140px;height:25px;text-align:right;}

.form-group .form-control {
    color: #5d5d5d;
    margin-left: 20px;
    display: inline-block;
    border: 1px solid #dbdbdb;
    border-radius: 5px;
}
form .uploadinput {
    position: absolute;
    top: 0;
    margin: 0;
    opacity: 0;
    -ms-filter: 'alpha(opacity=0)';
    direction: ltr;
    cursor: pointer;
    width: 90px;
    z-index:1000;
}
.img-show {
    width: 196px;
    height: 130px;
}
</style>
</head>
<body>
<form id="form1" action="submitAuthInfo"  method="post"  enctype="multipart/form-data" >
<input type="hidden"  id="userId"  name="userId"  value="${userId}" />
<div class="dialogPage">
	<div class="om-panel-header">申请认证</div>
	<div class="editDiv">		       
	            <div class="content">
					<div class="tleft">
						<span class="tname">&nbsp;</span>
					</div>
					<div class="skin" style="height: 100%">
						<table>
							<tr>
								<td class="bleft"><span class="required">*</span>真实姓名：</td>
								<td width="250px"><input type="text"  id="realName"  name="realName"  value="${authInfo.realName}"  maxlength="20" /></td>
								<td class="bleft"><span class="required">*</span>身份证号：</td>
								<td width="250px"><input type="text"  id="regNumber"  name="regNumber"  value="${authInfo.regNumber}"  maxlength="18" /></td>																	
							</tr>
							<tr>
							    <td class="bleft" valign="top"><span class="required">*</span>身份证照片：</td>
							    <td width="640px" colspan="3" style="position:relative;">
								      <input type="file"  class="uploadinput"  placeholder="点击上传身份证照片"  name="tp1">
							          <button class="btn" style="margin-left:0px;" type="button">上传图片</button>
							          <span class="span-control1">支持jpg/gif/png格式，长&lt;1000，宽&lt;1000，大小&lt;4M。</span>
							          <br/>	
							          <c:if test="${not empty  authInfo.idCardPic}"> <img  id="idCardPic"  src="${authInfo.idCardPic}" width="196"  height="130" alt="default"  class="img-control default"> </c:if>	
							          <c:if test="${empty  authInfo.idCardPic}"> <img  id="idCardPic"  src="${img}/auth_default.png" width="196"  height="130" alt="default"  class="img-control default"> </c:if>									
								</td>
							</tr>	
						    <tr><td colspan="4"><span style="margin-left:25px;color:#000000;font-weight:bold;">
						                         温馨提示： 以下两个证书的照片至少需要上传一张</span>
						        </td>
						    </tr>	
							<tr>
							    <td class="bleft" valign="top">资格认证照片：</td>
							    <td width="640px" colspan="3"  style="position:relative;">
								      <input type="file"  class="uploadinput"  placeholder="点击上传资格认证照片"  name="tp2">
							          <button class="btn" style="margin-left:0px;" type="button">上传图片</button>
							          <span class="span-control1">支持jpg/gif/png格式，长&lt;1000，宽&lt;1000，大小&lt;4M。</span>
							          <br/>
							        <c:if test="${not empty  authInfo.traningCompanyPic}"> <img  id="traningCompanyPic"  src="${authInfo.traningCompanyPic}" width="196"  height="130" alt="default"  class="img-control default"> </c:if>	
							        <c:if test="${empty  authInfo.traningCompanyPic}"> <img  id="traningCompanyPic"  src="${img}/auth_default.png" width="196"  height="130" alt="default"  class="img-control default"> </c:if>									
								</td>
							</tr>
							<tr>
							    <td class="bleft" valign="top">国家技术等级：</td>
							    <td width="640px" colspan="3"  style="position:relative;">
								      <input type="file"  class="uploadinput"  placeholder="点击上传国家技术等级证书照片"  name="tp3">
							          <button class="btn" style="margin-left:0px;" type="button">上传图片</button>
							          <span class="span-control1">支持jpg/gif/png格式，长&lt;1000，宽&lt;1000，大小&lt;4M。</span>
							          <br/>
							        <c:if test="${not empty  authInfo.skillLevlPic}"> <img  id="skillLevlPic"  src="${authInfo.skillLevlPic}" width="196"  height="130" alt="default"  class="img-control default"> </c:if>	
							        <c:if test="${empty  authInfo.skillLevlPic}"> <img  id="skillLevlPic"  src="${img}/auth_default.png" width="196"  height="130" alt="default"  class="img-control default"> </c:if>									
								</td>
							</tr>						
					 </table>					 
					 <div class="editBtn" style="margin-top:12px;">
	                       <button type="button" class="button" onclick="javascript:submitAuthInfo();">&nbsp;提交认证&nbsp;</button>
			               <button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		             </div>								
				 </div>
			  </div>	
			     
	    </div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('.uploadinput').on('change', function(){
	    var $input = $(this);
	    var files = $input.get(0).files;
	    var $that = $(this);
	    if(!/image\/\w+/.test(files[0].type)){
	      $.messager.alert('提示:','文件必须为图片！'); 
	      return false;
	    }
	    //size以字节b为单位。
	    if(files[0].size > 4*1024*1024){
	      $.messager.alert('提示:','图片大小不能超过4M！'); 
	      return false;
	    }
	    try{
	      if(!window.FileReader){
	        $input.get(0).select();
	        var reallocalpath = document.selection.createRange().text;
	        var img = $('<img>').addClass('img-show');
	        img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
	        img[0].src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
	        $that.parent().append(img);
	      }else{
	        var reader = new FileReader();
	        reader.readAsDataURL(files[0]);
	        reader.onload = function(e){
	          var img = $('<img>').addClass('img-show img-control');
	          img[0].src = this.result;
	          if(img[0].width >1000 || img[0].height >1000){
	            $.messager.alert('提示:','图片尺寸不能超过1000*1000'); 
	            return false;
	          }
	          $that.siblings('.img-show').remove();
	          if($that.attr('name') == 'tp'){
	            $that.parent().prepend(img).find('.default').hide();
	          }else{
	            $that.parent().append(img).find('.default').hide();
	          }
	        }
	      }
	    }catch(ex){
	      var fileSrc = $input.val(), fileName = fileSrc.substring(fileSrc.lastIndexOf('\\')+1);
	      $that.parent().append('上传的文件：'+ fileName + '当前浏览器不支持图片预览.');
	    }
	  });
	
	$('#realName').validatebox({required:true, validType:['maxLength[20]']}); 
	
	$('#regNumber').validatebox({
		required:true, 
		validType:'idcard',
		invalidMessage:'输入的身份证号码格式不对'
	});		

});

function submitAuthInfo(){
	var flag = $("#form1").form('validate');
	if(!flag){
		return false;
	}		
	if(!$("#idCardPic").is(":hidden") ){
		 var picSrc = $("#idCardPic").attr('src');
		 if( picSrc.indexOf('auth_default.png')>=0){
			 $.messager.alert('提示:','必须上传身份证照片'); 
			 return false;
		 }			
	}
	if(!$("#traningCompanyPic").is(":hidden")  && !$("#skillLevlPic").is(":hidden") ){
		 var certSrc1 = $("#traningCompanyPic").attr('src');
		 var certSrc2 = $("#skillLevlPic").attr('src');
		 if( certSrc1.indexOf('auth_default.png')>=0 && certSrc2.indexOf('auth_default.png')>=0){
			 $.messager.alert('提示:','至少需要上传一种证书照片'); 
			 return false;
		 }				 			
	}
	$.messager.progress({
		title:'提示',
		msg:'服务器正在处理中，请耐心等待....'
	});
	$("#form1").submit();
}
</script>
</html>