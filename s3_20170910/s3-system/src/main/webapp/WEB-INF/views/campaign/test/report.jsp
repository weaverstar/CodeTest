<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<body>
<form id="form1" action="insert">
	<input type="hidden" name="status" value="0">
	<div class="dialogPage">
		<div class="om-panel-header">Test</div>
		<div class="editDiv">
			<table class="editTable">
				<tr>
					<td></span>campaign_Id：</td>
					<td>${campaignId}</td>
				</tr>
				<tr>
					<td></span>deviceid：</td>
					<td>${deviceId}</td>
				</tr>
				<tr>
					<td></span>landingpage：</td>
					<td>${landingpage}</td>
				</tr>
				<tr>
					<td></span>result：</td>
					<td>${result}</td>
				</tr>
				<tr>
					<td></span>reason：</td>
					<td>${reason}</td>

				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>