<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        table tr th, table tr td {
            border:1px solid #a8aeb2;
            /*padding: 5px 10px;*/
        }
        table {
            min-height: 25px;
            line-height: 25px;
            text-align: center;
            border-collapse: collapse;
        }
        table tr td{
            text-align:center;
        }
    </style>

    <style media=print>
        .Noprint{display:none;}
        .PageNext{page-break-after: always;}
    </style>

    <script type="text/javascript">
        function doPrint() {
            window.print();
        }
    </script>
</head>
<body>
<table width="98%" class="haveBorder" style="border:0">
    <tr style="border:0">
        <td colspan="5" align="left" style="border:0">
            <table style="width:100%" style="border:0">
                <tr>
                    <td style="width:30%;text-align:left;border:0">
                        <img src="${img}/yinxiang3.png">
                    </td>
                    <td style="width:70%;text-align:left;font-size:24px;font-weight:bolder;border:0;padding-left: 80px;">
                        <span>银象水泵售货单</span>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
       <td align="left" colspan="2">流水号:${resaleMain.flowId}</td>
       <td align="right" colspan="3"> ${year} 年 ${month} 月 ${day}日</td>
    </tr>
    <tr>
        <th>商品名称</th>
        <th>数量</th>
        <th>单位</th>
        <th>单价</th>
        <th>合计</th>
    </tr>
<c:forEach items="${resaleList}" var="resaleModel" varStatus="index">
    <tr>
        <td>${resaleModel.productType}-${resaleModel.product}</td>
        <td>${resaleModel.productNum}</td>
        <td>${resaleModel.unit}</td>
        <td>${resaleModel.price}</td>
        <td>${resaleModel.price * resaleModel.productNum}</td>
    </tr>
</c:forEach>

<c:forEach var="i" begin="1" end="${5 - size}" step="1">
    <tr>
        <td style="height:36px;"></td>
        <td style="height:36px;"></td>
        <td style="height:36px;"></td>
        <td style="height:36px;"></td>
        <td style="height:36px;"></td>
    </tr>
</c:forEach>
    <tr>
        <td colspan="2">合计</td>
        <td></td>
        <td></td>
        <td>${resaleMain.temp1}</td>
    </tr>
    <tr>
        <td colspan="1">
            说明:
        </td>
        <td colspan="4">
        </td>
    </tr>
</table>
<div class="editBtn">
    <button type="button" onclick="doPrint()" class="Noprint" style="border: 1px solid #3186E6;background: #3186E6;color: #fff;width: 50px;height:30px;border-radius: 4px;">打印</button>
</div>

</body>
</html>
