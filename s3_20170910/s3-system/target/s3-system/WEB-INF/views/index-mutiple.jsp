﻿
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<!-- 禁止缓存开始 -->
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!-- 禁止缓存结束 -->
<title>选择服务及产品</title>
<link rel="stylesheet" href="${STATIC_URL}/css/common.css?v=3" />
<link rel="stylesheet" href="${STATIC_URL}/css/public.css">
<link rel="stylesheet" href="${STATIC_URL}/css/home-1.0.css">
<link rel="stylesheet" href="${STATIC_URL}/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${STATIC_URL}/css/changefwcp-mutiple.css?v=3" />
<link rel="stylesheet" href="${STATIC_URL}/css/swiper.min.css" />

<script type="text/javascript">
	var BASE_URL = '${BASE_URL}';
	var STATIC_URL = '${STATIC_URL}';
	var IMG_URL = '${IMG_URL}';
	var type = '${type}';
</script>
<script type="text/javascript"
	src="${STATIC_URL}/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/js/swiper.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/js/alert.js?v=3"></script>

<link rel="stylesheet"
	href="${STATIC_URL}/css/mobiscroll.custom-2.5.2.min.css"
	type="text/css" />
<script type="text/javascript"
	src="${STATIC_URL}/js/date/mobiscroll.custom-2.5.2.min.js"></script>
<!--
<script type="text/javascript" src="${STATIC_URL}/js/service/service.js?v=1008"></script>
 -->

</head>
<body>
	<input type="hidden" id="userid" value="${userid}">
	<input type="hidden" id="type" value="${type}">

	<!--主页-->
	<div class="xxffjcp-div">
		<div class="hand">
			<a class="goto" href="javascript:void(0);"><img
				src="${STATIC_URL}/img/ico_goto.png" /> </a>
			<h1>项目选择</h1>
		</div>
		<div class="container">

			<div class="por-tis box-w"
				style="margin-top: 8px; position: absolute; white-space: nowrap">

				<a
					href="${BASE_URL}/userGarage/index?type=${type}&action=mutiple&userid=${userid}&garageid=${carMap.garageid}">
					<!-- 车库不为空 --> <c:if test="${!empty carlist}">
						<%-- <c:forEach items="${carlist}" var="w">
                    <!-- 显示默认车辆 -->
                        <c:if test="${carMap.isdefault eq '1'}">--%>
						<input type="hidden" id="garageid" value="${carMap.garageid}">
						<img src="${IMG_URL}${carMap.picurl}" alt=""
							style="margin-top: -12px;"> &nbsp;
                <span style="height: 2.6rem; line-height: 3.6rem;">${carMap.brand}&nbsp;${carMap.variety}</span>
						<i class="fa fa-angle-right fa-lg "></i>
						<%--</c:if>
                 </c:forEach>--%>
					</c:if>
				</a>
				<!-- 车库为空 -->
				<c:if test="${carlist==null || fn:length(carlist)==0 }">
					<a href="${BASE_URL}/userInfo/findUserCar?type=${type}"><span>换车</span></a>
				</c:if>
			</div>

			<c:if test="${pagetype != 'viewserviceprice'}">
				<div class="travel-box ">
					<%-- 	<c:forEach items="${carlist}" var="w"> --%>
					<%-- <c:if test="${w.isdefault eq '1'}"> --%>
					<fmt:formatDate value="${carMap.purchasedate }"
						pattern="yyyy-MM-dd " var="purchaseDate" />
					<div class="tr-input">
						行驶里程 <input type="number" name="mileage" id="mileage"
							placeholder="输入行驶里程" value="${carMap.mileage }"
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">

						<!-- input type="number" name="mileage" id="mileage"
							placeholder="输入行驶里程" value="${carMap.mileage }"-->
					</div>
					<div class="tr-input">
						购车时间<input type="text" name="purchasedate" id="purchasedate"
							placeholder="输入购车时间" value="${purchaseDate}">
					</div>
					<%-- </c:if> --%>
					<%-- </c:forEach> --%>
				</div>
			</c:if>


			<!--更换产品-->

			<div class="fin">
				<ul class="changefw">
					<c:forEach var="serviceinfo" items="${serviceList }">
						<li>

							<div class="service-info">
								<div ck="0" name="serviceinfo" id="${serviceinfo.serviceid}"
									servicename="${serviceinfo.name}"
									<%-- serviceprice="${serviceinfo.price} --%>" onclick="serviceCheck(this);">
									<i class="fa fa-check-circle-o fa-lg"></i>&nbsp;&nbsp;${serviceinfo.name}
									<c:if
										test="${serviceinfo.serviceid == SERVICEID && serviceIdStatus == 1}">
										<p style="color: red; font-size: smaller;">(今日套餐已抢完)</p>
									</c:if>
									<a href="javascript:void(0);" class="changeOther"
										id="${serviceinfo.serviceid}"
										style="float: right; width: 20%; display: none;"><span
										class="right">编辑</span></a>
								</div>
							</div>

							<div class="add-jylb-grid display-none">
								<c:forEach var="goodsinfo" items="${serviceinfo.goodsInfos}">
									<div class="c-l-box01" isMatch="${goodsinfo.isMatch}"
										isService="0"
										<c:if test="${goodsinfo.isMatch=='1'}">isDefault="1" </c:if>
										<c:if test="${goodsinfo.isMatch!='1'}">isDefault="0" </c:if>
										serviceid="${goodsinfo.serviceid}"
										goodsid="${goodsinfo.goodsid}" onclick="goodsCheck(this);"
										cate="${goodsinfo.cate}" price="${goodsinfo.price}" name="${goodsinfo.name}" picurl="${goodsinfo.picurl}">
										<ul>
											<li class="c-l-w c-l-img"><img
												src="${IMG_URL}${goodsinfo.picurl}" class="img-cp" /></li>
											<li class="c-l-h c-l-name" style="display: table;"><h1>${goodsinfo.name}</h1></li>

											<li class="c-l-h c-l-num" style="display: none;">
												<div class="c-r-sr changeNumber"
													goodsid="${goodsinfo.goodsid}">
													<span class="div_plug" onclick="getCount(this,event);">+</span>
													<input name="goodscount" type="text" value="1"
														readonly="readonly"> <span class="div_jian"
														onclick="getCount(this,event);">-</span>
												</div>
											</li>

											<li class="c-l-tr c-l-mon" style="display: table;"><span
												class="c-r-tj">价 格：￥${goodsinfo.price}</span> <span
												class="right thisNumber">X<i>1</i></span></li>
											<li class="c-l-trs c-l-change" style="display: none;"><a
												href="javascript:void(0);"
												serviceid="${goodsinfo.serviceid}" cate="${goodsinfo.cate}"
												onclick="changeGoods(this);"> <span class="right"><i
														class="fa fa-refresh"></i>&nbsp;更换</span></a></li>
										</ul>
									</div>
								</c:forEach>


								<div class="c-l-box01" serviceid="${serviceinfo.serviceid}"
									isService="1" isMatch="1" isDefault="1"
									style="cursor: hand; display: table;"
									price="${serviceinfo.price}">
									<ul>
										<li class="c-l-w"><img
											src="${IMG_URL}/service/content/gsf.jpg" class="img-cp" /></li>
										<li class="c-l-h"><h1>服务费</h1></li>
										<li class="c-l-tr"><input name="goodscount"
											id="servicemoney" type="hidden" value="1"><span
											class="c-r-tj">服务费：￥${serviceinfo.price}</span></li>
									</ul>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!--产品列表-->
			<div class="add-jylb">
				<div class="add-cplist-title display-none"></div>
				<div class="add-jylb-grid"></div>
			</div>

			<div class="add-jllb add-jylb">
				<div class="add-cplist-title display-none"></div>
				<div class="add-jllb-grid"></div>
			</div>
			<div id="serviceprice"></div>
		</div>
		<div class="footer te-ri">
			<span class="sum-title">合计金额：￥</span><span class="sum-amount">0</span>
			<c:if test="${param.action!='viewserviceprice'}">
				<input class="a-next-s" value="下一步" type="button" />
			</c:if>
		</div>
	</div>

	<script type="text/javascript">
		$('.goto').on('click', function() {

			location.replace("${BASE_URL}/index/index?type=1");
		});

		$(function() {

			var now = new Date();

			// Mobiscroll Date & Time initialization
			$('#purchasedate').mobiscroll().date(
					{
						theme : 'mobiscroll',
						lang : 'zh',
						display : 'bottom',
						minDate : new Date(now.getFullYear() - 6, 0, 1),
						maxDate : new Date(now.getFullYear(), now.getMonth(),
								now.getDate()),
						setText : '确定',
						cancelText : '取消',
						dateFormat : "yy-mm-dd",
						dateOrder : 'yymmdd',
					});
		});

		$(document).ready(
				function() {
					$.each($("i[name=serviceinfo]"), function() {

						var serviceid = $(this).attr("id");
						var num = 0;
						var cate = $(".c-l-box01[serviceid=" + serviceid + "]")
								.attr("cate");
						$.each($(".c-l-box01[serviceid=" + serviceid + "]"),
								function() {
									if ($(this).attr("cate") == cate) {
										console.log("cate:"
												+ $(this).attr("cate"));
										num++;
									}
								});

						$.each($(".c-l-box01[serviceid=" + serviceid + "]"),
								function() {
									console.log("num:" + num);
									console.log("calss:"
											+ $(
													$($(this).children()[0])
															.children()[3])
													.attr("class"));
									if ($(this).attr("cate") == cate) {
										if (num > 1) {
											$(
													$($(this).children()[0])
															.children()[3])
													.css("display", "table");
										} else if (num <= 1) {
											$(
													$($(this).children()[0])
															.children()[3])
													.css("display", "none");
										}
									}
								});

						num = 0;
					});
				});

		$(function() {

			$("#mileage").attr("value", $("#mileages").val());
			$("#purchasedate").attr("value", $("#purchasedates").val());

			//purchasedate change event
			$("#purchasedate").on(
					"change",
					function() {
						var purchasedate = $("#purchasedate").val();
						var garageid = $("#garageid").val();
						console.log("purchasedate:" + purchasedate);

						var userid = $("#userid").val();
						var type = $("#type").val();

						$.ajax({
							url : BASE_URL + '/userGarage/AjaxByPurchasedate',
							data : {
								garageid : garageid,
								purchasedate : purchasedate,
								userid : userid,
								type : type,
							},
							type : 'post',
							cache : false,
							dataType : "json",
							success : function(data) {

								//window.top.location=BASE_URL+"/serviceInfo/index?type="+type+"&userid="+userid;
								window.top.location = BASE_URL
										+ "/serviceInfo/index-mutiple?type="
										+ type + "&userid=" + userid
										+ "&action=mutiple" + "&garageid="
										+ garageid;

							}
						});

					});

			//mileage change event
			$("#mileage").on(
					"change",
					function() {
						var mileage = $("#mileage").val();
						var garageid = $("#garageid").val();
						console.log("mileage:" + mileage);

						var userid = $("#userid").val();
						var type = $("#type").val();

						$.ajax({
							url : BASE_URL + '/userGarage/AjaxByMileage',
							data : {
								garageid : garageid,
								mileage : mileage,
								userid : userid,
								type : type,
							},
							type : 'post',
							cache : false,
							dataType : "json",
							success : function(data) {

								//window.top.location=BASE_URL+"/serviceInfo/index?type="+type+"&userid="+userid;
								window.top.location = BASE_URL
										+ "/serviceInfo/index-mutiple?type="
										+ type + "&userid=" + userid
										+ "&action=mutiple" + "&garageid="
										+ garageid;

							}
						});
					});

			$(".changeUserGarage").on("click", function() {

				var num = $(".userGarage").size();
				console.log(num);
				if (num < 2) {
					return;
				}
				$(".userGarage").toggle();
				$(this).hide();

			});

			//isDefault change event
			$(".userGarage").on(
					"click",
					function() {
						$(".userGarage").show();
						$(this).hide();
						$(".userGarage").toggle();
						$(".changeUserGarage").show();
						var garageid = $(this).attr("garageid");
						console.log("garageid:" + garageid);

						var userid = $("#userid").val();
						var type = $("#type").val();

						$.ajax({
							url : BASE_URL + '/userGarage/AjaxByIsdefault',
							data : {
								garageid : garageid,
								userid : userid,
								type : type,
							},
							type : 'post',
							cache : false,
							dataType : "json",
							success : function(data) {

								//window.top.location=BASE_URL+"/serviceInfo/index?type="+type+"&userid="+userid;
								window.top.location = BASE_URL
										+ "/serviceInfo/index-mutiple?type="
										+ type + "&userid=" + userid;

							}
						});

					});

			$(".changeOther").on(
					"click",
					function(event) {

						console.log($(this).attr("id"));
						var serviceid = $(this).attr("id");
						var content = $(this).children().html();
						var obj = $(this).parent().parent().next();

						//$(this).html('<span class="right">编辑</span>');

						//$($(".thisNumber")[1]).children().html();

						if (content == "编辑") {
							$(this).html('<span class="right">保存</span>');
							//隐藏商品名称，隐藏金钱及数量
							//显示选择数量，显示更换按钮
							obj.find(".c-l-name").hide();
							obj.find(".c-l-mon").hide();
							obj.find(".c-l-num").show();
							obj.find(".c-l-change").show();
						} else if (content == "保存") {
							$(this).html('<span class="right">编辑</span>');
							//隐藏选择数量，隐藏更换按钮
							//显示商品名称，显示金钱及数量
							obj.find(".c-l-num").hide();
							obj.find(".c-l-change").hide();
							obj.find(".c-l-name").show();
							obj.find(".c-l-mon").show();
						}

						//$(this).parent().next().find(".c-l-box01");
						var cate = 0;
						var len = 0;
						$.each($(this).parent().parent().next().find(
								".c-l-box01"), function() {
							cate = $(this).attr("cate");
							len = $(
									".add-jylb-grid .c-l-box01[cate=" + cate
											+ "]").size();
							if (len > 1) {
								if (content == "编辑") {
									$(this).children().find(".c-l-change")
											.show();
								}
							} else {
								$(this).children().find(".c-l-change").hide();
							}
						});

						/* var obj = $($($(this).parent()).next()).children();
						 if(obj.length < 3 ){
						 return ;
						 }

						 var serviceid = obj[0].getAttribute("serviceid");
						 $("[serviceid="+serviceid+"]").toggle();

						 $.each($("div [serviceid="+serviceid+"]"),function(){
						 $(this).css("display","table");
						 if($(this).attr("ismatch") == 1){
						 $(this).addClass($(this).attr("class")+" cplist-cen-checked");
						 }
						 }); */

						event.stopPropagation();

					});
		});

		function changeGoods(obj) {
			var serviceid = $(obj).attr("serviceid");
			var cate = $(obj).attr("cate");

			$(obj).parent().parent().parent().parent().prev().find(
					".changeOther").html("");

			$("div[serviceid=" + serviceid + "]").css("display", "none");
			$("div[cate=" + cate + "]").css("display", "table");

			//var serviceid = obj[0].getAttribute("serviceid");
			//$("[serviceid="+serviceid+"]").toggle();

			$.each($("div [serviceid=" + serviceid + "]"), function() {
				//$(this).css("display","table");
				if ($(this).attr("ismatch") == 1
						&& $(this).attr("serviceid") == serviceid) {
					$(this).addClass(
							$(this).attr("class") + " cplist-cen-checked");
				}
				if ($(this).attr("cate") == cate) {
					$($($(this).children()[0]).children()[3]).css("display",
							"none");
				}
			});
		}

		function goodsCheck(obj) {

			/* var th = $(obj).find(".c-l-name").css("display");
			 if(th == 'table'){
			 return ;
			 } */

			var objs = $(obj).parent();
			objs.find(".c-l-num").hide();
			objs.find(".c-l-change").hide();
			objs.find(".c-l-name").show();
			objs.find(".c-l-mon").show();
			var serviceid = $(obj).attr("serviceid");
			var cate = $(obj).attr("cate");
			var serviceGoods = $(".add-jylb-grid .c-l-box01[serviceid='"
					+ serviceid + "']");

			if (serviceGoods.length < 3) {
				return;
			}

			objcheck = $(obj);
			if (objcheck.attr("class").indexOf("cplist-cen-checked") != -1) {
				//console.log(objcheck.attr("class"));
				return;
			}

			$.each(serviceGoods, function() {
				if ($(this).attr("cate") == cate) {
					if ($(this).attr('ismatch') == '1') {
						$(this).attr("ismatch", "0");
						console.log($(this).attr("class"));
						$(this).attr("class", "c-l-box01");
					}
				}
			});

			$(obj).attr("ismatch", "1");

			serviceGoods.css("display", "none");
			$.each(serviceGoods,
					function() {

						if ($(this).attr('ismatch') == '1') {

							console.log($(this).attr("class"));
							if ($(this).attr("class").indexOf(
									"cplist-cen-checked") != -1) {
								$(this).attr("class", "c-l-box01");
							}
							$(this).css("display", "table");

						}
						if ($(this).attr("cate") == cate) {
							$($($(this).children()[0]).children()[3]).css(
									"display", "table");
						}
					});

			//显示选择数量，显示更换按钮
			//隐藏商品名称，隐藏金钱及数量
			objs.find(".c-l-name").hide();
			objs.find(".c-l-mon").hide();
			objs.find(".c-l-num").show();
			objs.find(".c-l-change").show();

			//$(this).parent().find(".c-l-box01");
			var cate = 0;
			var len = 0;
			$.each($(obj).parent().find(".c-l-box01"), function() {
				cate = $(this).attr("cate");
				len = $(".add-jylb-grid .c-l-box01[cate=" + cate + "]").size();
				if (len > 1) {
					$(this).children().find(".c-l-change").show();
				} else {
					$(this).children().find(".c-l-change").hide();
				}
			});

			$(obj).parent().prev().find(".changeOther").html(
					'<span class="right">保存</span>');

			summaryPrices();
		}

		//服务选择
		//
		function serviceCheck(obj) {

			var objs = $(obj).parent().next();
			objs.find(".c-l-num").hide();
			objs.find(".c-l-change").hide();
			objs.find(".c-l-name").show();
			objs.find(".c-l-mon").show();

			var serviceid = $(obj).attr("id");

			var objs = $("div[serviceid=" + serviceid + "]");
			var len = objs.length;
			console.log("$(obj).attr(ck):" + $(obj).attr("ck"));
			if ($(obj).attr("ck") == '0') {
				if (len > 2) {
					$(obj).find(".changeOther").show();
				}

				$(obj).addClass("btn-color");
				$(obj).attr("ck", '1');
				$(obj).parent().siblings(".add-jylb-grid").removeClass(
						"display-none");

				$.each(objs, function() {
					console.log("isDefault:" + $(this).attr("isDefault"));
					if ($(this).attr("isDefault") == '1') {
						$(this).css('display', 'table');
					} else {
						$(this).css('display', 'none');
					}
				});

			} else {
				if (len > 2) {
					$(obj).find(".changeOther").html(
							'<span class="right">编辑</span>');
					$(obj).find(".changeOther").hide();
				}

				$(obj).removeClass("btn-color");
				$(obj).attr("ck", "0");
				$(obj).parent().siblings(".add-jylb-grid").addClass(
						"display-none");

				$.each(objs, function() {
					console.log("isDefault:" + $(this).attr("isDefault"));
					if ($(this).attr("isDefault") == '1') {
						$(this).css('display', 'table');
					} else {
						$(this).css('display', 'none');
					}
				});
			}

			summaryPrices();
		}

		function summaryPrices() {
			//获取选中的商品
			var checkedGoods = $(".add-jylb-grid .c-l-box01[ismatch=1]");
			var summaryPrice = 0;
			//汇总选中商品的总额
			//汇总服务总额
			checkedGoods.each(function(index, obj) {
				var serviceid = $(obj).attr("serviceid");
				if ($("div[id='" + serviceid + "']").attr("ck") == '1') { //商品对应的服务选中状态
					//summaryPrice=summaryPrice+ parseFloat($(obj).attr("price"));

					console.log("size:" + $(obj).find("input").val());
					console.log("summaryPrice:" + summaryPrice);
					summaryPrice = summaryPrice
							+ parseFloat($(obj).attr("price"))
							* parseFloat($(obj).find("input").val());
				}
			});

			if (summaryPrice > 0) {
				var vv = Math.pow(10, 2);
				summaryPrice = Math.round(summaryPrice * vv) / vv
			}
			$(".sum-amount").text(summaryPrice);

		}

		// 计数器
		function getCount(obj, event) {
			var div = $(obj).parent("div");
			var index_value = parseInt(div.find("input").val());

			//$(obj).parent().parent().next().children()[1];

			if ($(obj).filter(".div_plug").length > 0) {
				index_value = index_value + 1;
				$(obj).parent("div").find("input").attr("value", index_value);
				$($(obj).parent().parent().next().children()[1]).html(
						"X" + index_value);
			} else {
				if (index_value > 1) {
					index_value = index_value - 1;
				}
				$(obj).parent("div").find("input").attr("value", index_value);
				$($(obj).parent().parent().next().children()[1]).html(
						"X" + index_value);
			}
			summaryPrices();
			event.stopPropagation();
		}

		//下一步
		$(".a-next-s")
				.click(
						function() {
							//存储到本地的数据
							var serviceinfoMap= {}; 
							var serviceIdMap = {};
							
							var goodsIds = "";
							var goodsCounts = "";
							var serviceIds = "";
							
							//遍历选中服务
							$("div[name='serviceinfo']").each(
									function(index, obj) {
										if ($(obj).attr("ck") == '1') {

											if (serviceIds.length > 0)
												serviceIds = serviceIds + ",";
											serviceIds = serviceIds+ $(obj).attr("id");
											
											//服务ID对应的名称
											serviceIdMap[$(obj).attr("id")] = $(obj).attr("servicename");

										}
									});
									
							//获取选中的商品
							var checkedGoods = $(".add-jylb-grid .c-l-box01[ismatch=1]");
							//遍历选中商品
							checkedGoods
									.each(function(index, obj) {
										var serviceid = $(obj)
												.attr("serviceid");

										if ($("div[id='" + serviceid + "']")
												.attr("ck") == '1') //商品对应的服务选中状态
										{
											if ($(obj).attr("isservice") != '1') {

												if (goodsIds.length > 0)
													goodsIds = goodsIds + ",";
												goodsIds = goodsIds + $(obj).attr("goodsid");

												var goodsCount = $(obj).find("input[name='goodscount']").val();
												if (goodsCounts.length > 0)
													goodsCounts = goodsCounts+ ",";
													goodsCounts = goodsCounts+ goodsCount;
													
													//选中的商品{价格，名称，数量，图片}
													var goodsInfo ={price:$(obj).attr("price"),name:$(obj).attr("name"),num: goodsCount,picurl:$(obj).attr("price")};
													
													var serviceObj = serviceinfoMap.get(serviceid);
													if(serviceObj == null){
														//服务{id,名称,商品}
														serviceObj = {id:serviceid,name:serviceIdMap(serviceid),goodsList:[]};
														
														serviceinfoMap[serviceid]= serviceObj;
													}
													//添加商品
													serviceObj.goodsList.push(goodsInfo);

											}
										}
									})


							if (goodsIds.length <= 0) {
								alertMessage("请选择至少一个服务.");
							} else {
								$.ajax({
											url : 'shoppingCartInfo/add-summary',// 跳转到 action
											data : {
												goodsIds : goodsIds,
												goodsCounts : goodsCounts,
												serviceIds : serviceIds,
											},
											type : 'post',
											cache : false,
											dataType : "json",
											success : function(data) {
												if (data.status == 0) {
													//存储到本地
													var objStr=JSON.stringify(serviceinfoMap);
													//window.localStorage.setItem("serviceinfoMap", objStr);
													
													//location.href = "technicianInfo/index?serviceid="+serviceid+"&userid="+$("#userid").val(); //location.href实现客户端页面的跳转
													location.href = "serviceInfo/reservationService?userid=${userid}"
															+ "&serviceids="
															+ serviceIds
															+ "&goodsids="
															+ goodsIds
															+ "&serviceinfoMap="
															+ objStr;
												} else {
													alertMessage(data.msg);
												}
											},
											error : function() {

												alertMessage("异常！");
											}
										});
							}
						});
	</script>
	<input type="hidden" id="action" value="${param.action}">
	<input type="hidden" id="variety" value="${param.variety}">
</body>
</html>
