<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>预约服务</title>
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<!-- 禁止缓存开始 -->
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!-- 禁止缓存结束 -->
<link rel="stylesheet" type="text/css"
	href="${STATIC_URL}/css/font-awesome.min.css">
<link rel="stylesheet" href="${STATIC_URL}/css/common.css?v=3" />
<link rel="stylesheet" href="${STATIC_URL}/css/public.css">
<link rel="stylesheet" href="${STATIC_URL}/css/home-1.0.css">
<link rel="stylesheet" href="${STATIC_URL}/css/mobiscroll/iosSelect.css" />
<script type="text/javascript"
	src="${STATIC_URL}/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/js/date/iosSelect.js"></script>
<script type="text/javascript" src="${STATIC_URL}/js/date/iscroll.js"></script>
<script type="text/javascript">
	var BASE_URL = '${BASE_URL}';
</script>
</head>
<body>
	<div class="hand">
		<a class="goto" href="javascript:history.go(-1);"><img
			src="${STATIC_URL}/img/ico_goto.png" /></a>
		<h1>预约服务</h1>
	</div>
	<!-----------hand-end------------>
	<div class="servicele-box">
		<div class="servicele-top">
			<div class="servicele-left  vertical-line">
				<c:if test="${empty carMap.picurl}">
					<img src="${STATIC_URL}/img/touxiang.png" alt="" class="left">
				</c:if>
				<c:if test="${!empty carMap.picurl}">
					<img src="${IMG_URL}${carMap.picurl}" alt="" class="left">
				</c:if>
				<span> <input type="hidden"
					value="${carMap.brand}${carMap.carline}${carMap.variety}" id="car" />
					<p>${user.name }</p>
					<p class="car"></p>
				</span>
			</div>
			<div class="servicele-right">
				<p>
					<c:if test="${empty carMap.mileage}">未知</c:if>
					<c:if test="${!empty carMap.mileage}">${carMap.mileage}</c:if>
				</p>
				<p>公里数</p>
			</div>
		</div>
		<!---------------以上用户个人信息----------------->
		<div class="se-cen vertical-line">
			<ul>
				<li><a href="javascript:void(0);">
					预约时间：
					<span class="right">
					      <input id="picktime" name="picktime" value="" placeholder="预约您接受服务的时间 "/>&nbsp;&nbsp;
					      <i class="fa fa-angle-right fa-lg"> </i>
					</span> </a>
				</li>
				<li><a href="${BASE_URL}/technicianInfo/index?userid=${userid}">
					选择技师：<span class="right">
					<input id="select-js" placeholder="选择您的服务技师 "/>
						&nbsp;&nbsp;<i class="fa fa-angle-right fa-lg "> </i></span>
					</a>
				</li>
				<li>
					<a href="${BASE_URL}/factoryInfo/index">选择门店：
						<span class="right"><input id="select-md" placeholder="选择您的服务门店"/> 
						&nbsp;&nbsp;<i class="fa fa-angle-right fa-lg"> </i></span>
					</a>
				</li>
			</ul>
		</div>
		<!---------------以上------------------>
		<div class="se-cen">
			<ul id="serviceList">
				<!-- <li><a href="#" class="se-font">更换机油机</a></li>
				<li><a href="#">818机油<span class="right red">￥170</span></a></li>
				<li><a href="#">百希特机滤<span class="right red">￥170</span></a></li>
				<li><a href="#">更换机油机滤工时<span class="right red">￥170</span></a></li>
				<li><a href="#" class="se-font">更换蓄电池</a></li>
				<li><a href="#">百希特蓄电池<span class="right red">￥170</span></a></li>
				<li><a href="#">更换蓄电池工时<span class="right red">￥170</span></a></li>
				<p class="red  box-w ">
					<span class="right">总价：￥170</span>
				</p> -->
			</ul>
		</div>
		<div class="cen-btn">确认预约</div>
	</div>
</body>
<script type="text/javascript">
   //初始化商品数据
   <c:if test="${!empty serviceinfoMap}">
	    var serviceIds = '${serviceIds}'.split(',');
	    var serviceinfoMap = JSON.parse('${serviceinfoMap}');
	    var summoney = 0;//总价
	    for(var i = 0; i < serviceIds.length; i++){
	        var sid = serviceIds[i]; //服务ID
	        var serviceinfo = serviceinfoMap[sid]; //服务对象
	        var goodsList = serviceinfo.goodsList; //服务下面的商品
	        //服务
	        $("#serviceList").append('<li><a href="#" class="se-font">'+serviceinfo.name+'</a></li>');
	        //商品
	        for(var j = 0; j < goodsList.length; j++){
	        	var money = (goods.price * price.num);
	        	var goods = goodsList[j];
	        	$("#serviceList").append('<li><a href="#">'+goods.name+'<span class="right red">￥'+ money +'</span></a></li>');
	        	summoney += money;
	        }
	        
	        
	    }
	    //总计
	    $("#serviceList").append('<p class="red  box-w "><span class="right">总价：￥'+summoney+'</span></p>');
	 
	</c:if>

	var car = $('#car').val();
	var carString = car.slice(0, 10);
	$('.car').text(carString + "...");

	var selectDateDom = $('#picktime');
	var showDateDom = $('#picktime');

	// 数据初始化
	function formatYear() {
		var arr = [];
		var date = new Date();
		var weeks = [ "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ];
		var week = '';
		for (var i = 1; i <= 30; i++) {
			if (i == 1) {
				week = '今天';
				var h = date.getHours() + 1;
				if (h > 20) {
					date.setDate(date.getDate() + 1);
					continue;
				}
			} else if (i == 2) {
				week = '明天';
			} else {
				week = weeks[date.getDay()];
			}
			arr.push({
				id : date.getFullYear()
						+ '-'
						+ (date.getMonth() + 1 < 10 ? '0'
								+ (date.getMonth() + 1) : date.getMonth() + 1)
						+ '-'
						+ (date.getDate() < 10 ? '0' + date.getDate() : date
								.getDate()),
				value : /* date.getFullYear() + '年' +  */(date.getMonth() + 1)
						+ '月'
						+ (date.getDate() < 10 ? '0' + date.getDate() : date
								.getDate()) + '日' + week
			});

			date.setDate(date.getDate() + 1);
		}
		return arr;
	}

	var yearData = function(callback) {
		callback(formatYear());
	}

	function formatHour(y) {
		var hours = [];
		var date = new Date(); //当前时间
		var newDate = date.getFullYear()
				+ '-'
				+ (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1)
						: date.getMonth() + 1) + '-'
				+ (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
		var h = date.getHours() + 1;
		if (y == newDate) {
			//今天时间
			for (i = h; i < 21; i++) {
				var key = "";
				if (i < 10) {
					key = "0" + i + ":00";
				} else {
					key = i + ":00";
				}
				hours.push({
					'id' : key,
					'value' : key
				});
			}
		} else {
			//其他时间
			for (i = 7; i < 21; i++) {
				var key = "";
				if (i < 10) {
					key = "0" + i + ":00";
				} else {
					key = i + ":00";
				}

				hours.push({
					'id' : key,
					'value' : key
				});
			}
		}
		return hours;
	};
	//选择小时
	var hourDataF = function(y, callback) {
		callback(formatHour(y));
	};
	selectDateDom.bind('click', function() {
		var iosSelect = new IosSelect(2, [ yearData, hourDataF ], {
			title : '时间选择',
			itemHeight : 35,
			relation : [ 1, 1, 0, 0 ],
			callback : function(selectOneObj, selectTwoObj) {
				showDateDom.val(selectOneObj.id + ' ' + selectTwoObj.id);
			}
		});
	});
</script>
</html>
