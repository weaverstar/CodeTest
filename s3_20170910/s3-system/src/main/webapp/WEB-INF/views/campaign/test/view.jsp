<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        a{text-decoration:underline;}
        a:hover{text-decoration:underline;color:red;cursor:pointer}
        #detailTitle{color:red}
    </style>
</head>
<body>
<div id="toolbar">
    <form id="list" action="list">
        <div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0">
            <input type="hidden" id="dictId" name="dictId" value="${dictId}">
            <table class="searchTable">
                <tr>
                    <td>campaignName：</td>
                    <td><input type="text" id="campaignNameSearch" name="detailName"></td>
                    <td style="width:15px;">&nbsp;</td>
                    <td><button id="queryBtn" type="button" class="button">search</button></td>
                    <td><button id="clearBtn" type="button" class="button">clean</button></td>
                </tr>
            </table>
        </div>
    </form>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
    $(function() {

        $('#grid').datagrid({
            url:'${ctx}/campaignTest/list?dictId=${dictId}',
            pageSize :20,
            pageList : [10, 20, 30, 40, 50],
            striped : true,
            rownumbers : true,
            pagination : true,
            toolbar : '#toolbar',
            columns : [[ {width : '50', field : 'ck',checkbox:true},
                {width : '100',title : 'campaign_id',field : 'campaignId'},
                {width : '200',title : 'campaign_name',field : 'campaignName'},
                {width : '150',title : 'device_id',field : 'deviceId'},
                {width : '200',title : 'landingpage',field : 'landingpage'},
                {width : '100',title : 'result',field : 'result'},
                {width : '100',title : 'reason',field : 'reason'},
                {width : '200',title : 'updateTime',field : 'updateTime'}
            ]]
        });


        $("#queryBtn").click(function(){
            var campaignNameSearch = $("#campaignNameSearch").val();
            $('#grid').datagrid({
                url:'${ctx}/campaignTest/search?campaignNameSearch='+campaignNameSearch,
                pageSize :20,
                pageList : [10, 20, 30, 40, 50 ],
                striped : true,
                rownumbers : true,
                pagination : true,
                toolbar : '#toolbar',
                columns : [[ {width : '50', field : 'ck',checkbox:true},
                    {width : '100',title : 'campaign_id',field : 'campaignId'},
                    {width : '200',title : 'campaign_name',field : 'campaignName'},
                    {width : '150',title : 'device_id',field : 'deviceId'},
                    {width : '200',title : 'landingpage',field : 'landingpage'},
                    {width : '100',title : 'result',field : 'result'},
                    {width : '100',title : 'reason',field : 'reason'},
                    {width : '200',title : 'updateTime',field : 'updateTime'}
                ]]
            });
        })
    })
</script>
</html>