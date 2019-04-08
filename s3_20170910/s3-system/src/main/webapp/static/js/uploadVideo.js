var  $ = qcVideo.get('$'), ErrorCode = qcVideo.get('ErrorCode') , Log = qcVideo.get('Log'), 
             JSON = qcVideo.get('JSON') , util = qcVideo.get('util'), Code = qcVideo.get('Code'), 
             Version = qcVideo.get('Version');
$(function(){
    var Version = qcVideo.get('Version');
    if( !qcVideo.uploader.supportBrowser() ){
        alert('当前浏览器不支持上传，请升级系统版本或者下载最新的chrome浏览器');
        return;
    }
    //初始化上传
    var secretId = "AKIDP9CNkDdVbpAcF9vtTE79GiIzmjrF4YyX";
    accountDone('pickfiles',secretId,1,0);
    
    //进度条
	prograss.init();
	
	$('#videoName').validatebox({
		required:true,
		validType:['isExist["checkVideoNameIsExist","videoName"]','maxLength[32]']  
	}); 
	
	//提交
	$("#submitBtn").click(submit);
	
	//关闭按钮
	$("#closeBtn").click(function(){
		art.dialog.close();
	});
	
	//取消上传
    $('#qxUpload').on('click', function (e) {
    	console.log("取消了");
    	$("#pickfiles").show();
    	$("div[id^='html5_']").show();
    	$(".uploadPrograss").hide();
        //删除文件
		Log.debug('delete', fileId);
		if(qcVideo.uploader){
			qcVideo.uploader.deleteFile(fileId);
		}
    	fileId = "";
    	finalFileId = "";
   });
});

var proTimer=null;

var prograss={
	init:function(){
		var bf=$(".uploadPrograssTt .fr").text(),now=$(".uploadPrograssTt .fr").text();
		var len=now.length;
		if(len>1){
			now=100-now.substr(0,len-1);
			$(".uploadPrograssBar div").css("right",now+"%");
		}
		proTimer=setInterval(function(){
			now=$(".uploadPrograssTt .fr").text();
			if(now != bf){
				var len01=now.length;
				if(len01>1){
					now=100-now.substr(0,len01-1);
					$(".uploadPrograssBar div").css("right",now+"%");
				}
			}
			console.log(123);
		},100);
	},
	clear:function(){
		clearInterval(proTimer);
		$("#submitBtn,#closeBtn").removeAttr("disabled"); 
	}
}

function makeChapters(){
	var cs = $("#courseSelect");
	if(cs.val()!=""){
		 $.post(ctx+'ktCourse/queryChapterByCourseId',{"courseId":cs.val()}, function(data) {
			   if(data){
				   var html=[];
				   html.push('<option value="">请选择章节</option>');
					for(var i in data){
						var ss=data[i].haveClasses == 1 ? ' 【已开课】' :'';
						//1：已开课(视频状态是已发布)，0未开课  
						html.push('<option value="'+data[i].chapterId+'">'+data[i].chapterName+ ss +' </option>');
					}					
					$('#chapterSelect').empty().html(html.join(''));
				} 
			}, 'json');
	}
}

//表单提交
function submit(){
	if($('#form1').form('validate')){
		if($('#courseSelect').val()==""){
			$.messager.alert('提示:','请选择课程!');
			return;
		}		
		if($('#chapterSelect').val()==""){
			$.messager.alert('提示:','请选择章节!');
			return;
		}
		if(fileId == "" || fileId == 1){
			$.messager.alert('提示:','请上传视频!');
			return;
		}else if( finalFileId == "" || finalFileId == 1){
			$.messager.alert('提示:','请等待视频上传完成!');
			return;
		}
		$.messager.progress({
			title:'提示',
			msg:'服务器正在处理中，请耐心等待....'
		});
		if(flag == 0){
			//正在上传
			$(window).unbind("beforeunload");
			flag = 1;
			$('#form1').submit();
		}
	}
}

//本地文件Id
var fileId = "1";
//上传成功后得文件Id
var finalFileId = "1";
//提交标识
var flag = 0;

/**
 * @param upBtnId 上传按钮ID
 * @param secretId 云api secretId
 * @param isTranscode 是否转码
 * @param isWatermark 是否设置水印
 * @param [transcodeNotifyUrl] 转码成功后的回调
 * @param [classId] 分类ID
 */
var accountDone = function (upBtnId,secretId, isTranscode, isWatermark,transcodeNotifyUr) {
	//获取地址
	var url = document.location.href;
	url = url.substring(0,url.indexOf("/ktCourse/"))+"/static/js/calculator_worker_sha1.js";
	
  

    qcVideo.uploader.init(
            {//1: 上传基础条件
                web_upload_url: 'http://vod.qcloud.com/v2/index.php',
                secretId: secretId, // 云api secretId
                secretKey: "4LjDmdgo2NsTAKf8hRRYm8jVOYpK29Z4",
                getSignature: function(argStr,cb){
                     //注意：出于安全考虑， 服务端接收argStr这个参数后，需要校验其中的Action参数是否为 "MultipartUploadVodFile",用来证明该参数标识上传请求
                     $.post("getSignature", {"args" : encodeURIComponent(argStr)}, 
                    		 function(d) {  cb(d['result']); } 
                 	 		 ,"json");
                },
                upBtnId: upBtnId, //上传按钮ID（任意页面元素ID）
                isTranscode: isTranscode,//是否转码
                isWatermark: isWatermark//是否设置水印
                ,after_sha_start_upload: true//sha计算完成后，开始上传 (默认关闭立即上传)
                //,sha1js_path: url //计算sha1的位置
                ,disable_multi_selection: false //禁用多选 ，默认为false
                ,transcodeNotifyUrl: "http://www.qcloud.com/wiki/v2/MultipartUploadVodFile"//(转码成功后的回调地址)isTranscode==true,时开启； 回调url的返回数据格式参考  http://www.qcloud.com/wiki/v2/MultipartUploadVodFile
                ,classId: null
            }, 
            {//2: 回调
                /**
                 * 更新文件状态和进度
                 * @param args { id: 文件ID, size: 文件大小, name: 文件名称, status: 状态, percent: 进度 speed: 速度, 
                 * 				errorCode: 错误码,serverFileId: 后端文件ID }
                 */
                onFileUpdate: function (args) {
                    fileId = args.id;
                    var codeName = "";
                	if(parseInt(util.getHStorage(args.size)) > 500){
                		$('#qxUpload').click();
                		alert('视频大小超过限制');
                		return;
                	}
                	$("#submitBtn,#closeBtn").attr("disabled","disabled");
                    switch (args.status) {
                    	case 1:
                    		codeName = "等待上传";
                    		break;
	                    case 2:
	                    	codeName = "上传中";
	                    	 console.log("上传中"+args.percent);
	                    	 if(args.percent>=1){
	                    		 $("#jd").text(parseInt(args.percent) + '%');
	                    	 }else{
	                    		 $("#jd").text(parseInt(args.percent*100) + '%');
	                    	 }
	                    	 $(window).on("beforeunload", function(){
	                    			return '您当前有视频文件正在上传，您的操作将取消上传。';
	                    	 });
	                    	 break;
	                    case 5:
	                    	$(window).on("beforeunload", function(){
                    			return '您当前有视频文件未提交，您的操作将丢失文件。';
	                    	});
	                    	codeName = "上传完成";
	                    	$("#jd").text('100%');
	                    	$(".uploadPrograssBar div").css("right","0");
	                    	finalFileId = args.serverFileId
	                    	$("#videoSize").val(args.size);
	                    	$("#attachType").val(args.name.replace(args.filename,""));//后缀名，类型
	                    	$("#attachDir").val(finalFileId);//腾讯云上的视频ID
	                    	prograss.clear();
	                    	break;
	                    case 4:
	                    	codeName = "上传失败";
	                    	prograss.clear();
	                    	break;
	                    case 10:
	                    	$(".uploadPrograss").show();
	                    	$("#pickfiles").hide();
	                    	$("div[id^='html5_']").hide();
	                    	codeName = "扫描中…"
	                    	//$("#jd").text(args.percent + '%');
	                    	$("#title").text(args.name);//视频名称
	                    	$("#size").text(util.getHStorage(args.size));
	                    	break;
                    }
                    $("#jsign").text(codeName);
                },
                /**
                 * 文件状态发生变化
                 * @param info  { done: 完成数量 , fail: 失败数量 , sha: 计算SHA或者等待计算SHA中的数量 , wait: 等待上传数量 , uploading: 上传中的数量 }
                 */
                onFileStatus: function (info) {
                  	//console.log('各状态总数-->' + JSON.stringify(info));
                },
                /**
                 *  上传时错误文件过滤提示
                 * @param args {code:{-1: 文件类型异常,-2: 文件名异常} , message: 错误原因 ， solution: 解决方法}
                 */
                onFilterError: function (args) {
                    var msg = 'message:' + args.message + (args.solution ? (';solution==' + args.solution) : '');
                    alert(msg);
                }
            }
    );
};