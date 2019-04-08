<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.content{margin-top:15px}
.skin{padding:10px 0 10px 5px;margin-left:100px;font-size:12px;border:#ddd 1px solid;width:680px;}
.tleft{float:left;width:100px;vertical-align:middle;}
.tname{float:right;font-weight:bold;color:#0165BA;font-size:14px;}
.bleft{width:140px;height:25px;text-align:right;}
</style>
</head>
<body>
<form id="form1" action="update">
<input type="hidden"  id="userId"  name="userId"  value="${authInfo.userId}" />
<div class="dialogPage">
	<div class="om-panel-header">认证审核</div>
	<div class="editDiv">		       
	            <div class="content">
					<div class="tleft">
						<span class="tname">认证信息：</span>
					</div>
					<div class="skin" style="height: 100%">
						<table>
							<tr>
								<td class="bleft">真实姓名：</td>
								<td width="200px">${authInfo.realName}</td>
								<td class="bleft">身份证号：</td>
								<td width="200px">${authInfo.regNumber}</td>
								<td class="bleft">认证状态：</td>
								<td width="200px"><c:choose>
								         <c:when test="${authInfo.status==-1}" >未认证</c:when><c:when test="${authInfo.status==0}" >待审核</c:when>
								         <c:when test="${authInfo.status==1}" >已认证</c:when><c:when test="${authInfo.status==2}" >审核未通过</c:when>
								    </c:choose>
								</td>											
							</tr>								
							<tr>
								<td class="bleft">认证申请时间：</td>
								<td><fmt:formatDate  value="${authInfo.authDate}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>								
								<td class="bleft">在岗职称：</td>
								<td width="200px">${userInfo.skillLevel}</td>
								<td colspan="2">&nbsp;</td>													
							</tr>	
					 </table>
					 <table>
					        <tr>
							    <td class="bleft">身份证照片：</td>
							    <td width="200px">
								   <c:if test="${not empty authInfo.idCardPic}"><img  width="196"  height="130" alt="身份证照片" 
								         src="${authInfo.idCardPic}"  onclick="zoomPicSize(this);"  /></c:if>
								   <c:if test="${empty authInfo.idCardPic}"><img  width="196"  height="130" alt="身份证照片"  src="${img}/auth_default.png"/></c:if>
								</td>
								<td class="bleft">资格认证：</td>
								<td width="340px">
								   <c:if test="${not empty authInfo.traningCompanyPic}"><img  width="196"  height="130" alt="资格认证"  
								         src="${authInfo.traningCompanyPic}"  onclick="zoomPicSize(this);"  /></c:if>
								   <c:if test="${empty authInfo.traningCompanyPic}"><img  width="196"  height="130" alt="资格认证"  src="${img}/auth_default.png"/></c:if>
								</td>
							</tr>
							<tr><td colspan="4">&nbsp;</td></tr>	
							<tr>
								<td class="bleft">国家技术等级：</td>
								<td colspan="3"  width="620px">								       
                                    <c:if test="${not empty authInfo.skillLevlPic}"><img  width="196"  height="130" alt="资格认证"  
                                         src="${authInfo.skillLevlPic}"   onclick="zoomPicSize(this);"   /></c:if>
								    <c:if test="${empty authInfo.skillLevlPic}"><img  width="196"  height="130" alt="资格认证"  src="${img}/auth_default.png"/></c:if>
                                </td>
							</tr>		
					 </table>
												
					</div>
				</div>	
				<div class="content">
					<div class="tleft">
						<span class="tname">审核意见：</span>
					</div>
					<div class="skin" style="height: 100%">
						<table>	
						    <tr>
								<td style="width:80px;height:25px;text-align:right;">认证等级：</td>
								<td colspan="5"  width="620px">
							           <input id="authLevel" name="authLevel" type="combo"/>
							    </td>
							</tr>							
							<tr>
								<td style="width:80px;height:25px;text-align:right;">审核提示：</td>
								<td colspan="5"  width="620px"><textarea id="remark"  rows="3"  style="width:96%;resize:none;"  maxlength="150"  onblur="doKeyUp(this);" 
							           onkeyup="doKeyUp(this);"  ></textarea>
							           <br/><span style="color:#5e5e5e;">如果审核不通过，请填写原因，限制在150字以内</span>
							    </td>
							</tr>							
					 </table>
					
					<div class="editBtn" style="margin-top:12px;">
	                         <button type="button" class="button" onclick="javascript:auditAuthInfo(1);">&nbsp;通过&nbsp;</button>
	                         <button type="button" class="button" onclick="javascript:auditAuthInfo(2);">&nbsp;不通过&nbsp;</button>
			                 <button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		            </div>						
			      </div>
		     </div>      
	    </div>
</div>
</form>
</body>
<script type="text/javascript">
$(function() {	
    $('#authLevel').combobox({      	
    	data:[{'value':'','text':'----请选择认证等级'}, {'value':'维修技工','text':'维修技工'}, {'value':'维修技师','text':'维修技师'}, {'value':'维修组长','text':'维修组长'},
    	      {'value':'车间主管','text':'车间主管'}, {'value':'技术总监','text':'技术总监'}   	      
    	     ],
    	editable:false,
    	value:'',
    	panelHeight:'auto'
    }); 
});

function auditAuthInfo(status){
	var userId = $("#userId").val();
    var authLevel = $('#authLevel').combobox('getValue');
	var remark = $("#remark").val();
	remark = remark.trim();
	if(status==2){ //审核不通过		
	    if(remark==""){
	    	$.messager.alert('提示:','请填写审核不通过的原因'); 
			return ;
	    }
	}
	$.messager.progress({
		title:'提示',
		msg:'服务器正在处理中，请耐心等待....'
	});
	$.ajax({
		url: 'auditAuthInfo',
		data: {'userId':userId, 'status':status, 'authLevel' : authLevel, 'remark': remark},
		type: "POST",
		success: function(data)	{
			$.messager.progress('close');
			var win=art.dialog.open.origin; 
			win.refreshGrid('grid');
			art.dialog.close();
		}
    });
}

function doKeyPress(obj,evt){//键入时
	maxLength = obj.getAttribute("maxlength");
	var e = window.event ? event.keyCode : evt.which;
	if ( (e == 32) || (e == 13) || (e > 47)) { //IE
	    if(maxLength && (obj.value.length > maxLength-1)) {
		     if (window.event) {
		           window.event.returnValue = null;
		     } 
		     else {
		       evt.preventDefault();
		       return false;
		     }
	   }
	}
} 

function doKeyUp(obj){//按键时
	maxLength = obj.getAttribute("maxlength");
	if(maxLength && obj.value.length > maxLength){
	     obj.value = obj.value.substr(0,maxLength);
	}		
}

function  zoomPicSize(obj){
	//window.open ( obj.src, "图片放大", "left=485,top=165,height=660,width=900,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
	art.dialog({
		lock:true,
		drag: false,
		content: '<img src="'+obj.src+'"  width="450" />',
		top:20,
		resize:true
	});
}
</script>
</html>