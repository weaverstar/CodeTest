<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="stylesheet" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js?version=${jsVersion}"></script> --%>
</head>
<body>
<div class="dialogPage">
	<div class="om-panel-header">bind menu</div>
	<div class="treeDiv" style="width:238px;border:1px solid #86a3c4;">
		<ul id="navtree" style="  overflow-y: scroll;"></ul>
	</div>
	<form id="form1" action="bindMenu">
	<div class="editBtn" style="position:absolute;bottom:0px;margin:10px auto;">
		<input type="hidden" name="roleId" value="${roleId}" />
		<input type="hidden" name="menuIds" id="menuIds"/>
		<button type="button" class="button" onclick="doSubmit()" style="margin-bottom:10px;">save</button>
		<button type="button" class="button" onclick="javascript:art.dialog.close();">close</button>
	</div>
	</form>
</div>
<script>
$(function(){
  	$(".treeDiv").css("height","420px");  
    $('#navtree').tree({   
	    url:ctx+'/system/prg/menu/tree',
	    checkbox:true,
	    cascadeCheck: false,
	    onLoadSuccess:function(){
	    	var sRoleMenuJson = ${SRoleMenuJson};
	    	for(var i=0;i<sRoleMenuJson.length;i++){
	    		var n = $("#navtree").tree('find',sRoleMenuJson[i].menuId);
	            if(n){
	                $("#navtree").tree('check',n.target);
	            }
	    	}
	    	$('#navtree').height($('.treeDiv').height());
    		/* 
    		$('#navtree').mCustomScrollbar({
	    		theme:"minimal-dark"
	    	});
    		*/
	    	
	    },       
	    onCheck: function (node, checked) {
            if (checked) {
                var parentNode = $("#navtree").tree('getParent', node.target);
                if (parentNode != null) {
                    $("#navtree").tree('check', parentNode.target);
                }
            } else {
                var childNode = $("#navtree").tree('getChildren', node.target);
                if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                        $("#navtree").tree('uncheck', childNode[i].target);
                    }
                }
            }
        }
	}); 
});
function doSubmit(){
	var nodes = $('#navtree').tree('getChecked');
	var menuIds=[];
    for(var i=0;i <nodes.length; i++){
    	menuIds.push(nodes[i].id);
	}
	$("#menuIds").val(menuIds.toString());
	$("#form1").submit();
}
</script>
</body>
</html>