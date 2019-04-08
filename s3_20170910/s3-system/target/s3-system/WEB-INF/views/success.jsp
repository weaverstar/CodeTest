<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglibs.jsp"%>
<html>
<script type="text/javascript">
var win=art.dialog.open.origin; 
win.showTip("${optionResult}","${faildMessage}");

if( typeof(initFrequency) == "function"){
    initFrequency();
}
art.dialog.close();
</script>
</head>
<body>
</body>
</html>