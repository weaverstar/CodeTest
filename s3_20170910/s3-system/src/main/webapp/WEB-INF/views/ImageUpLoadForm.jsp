<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<style type="text/css">
	.plupload_file_desc{float: right;width: 100px;text-align: center;}
	.file_action  a{color: #333;display: inline-block; padding: 2px;cursor: pointer;float: left;width: 52px;text-align: center;background-color: #ccc;margin: 2px;font-size: 13px;}
	.plupload_upload_status{    margin-top: 8px; display: block;}
</style>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<c:url value='/static/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css'/>?version=${jsVersion}" />
<script type="text/javascript" src="<c:url value='/static/js/plupload-2.1.2/js/plupload.full.min.js'/>?version=${jsVersion}"></script>
<script type="text/javascript" src="<c:url value='/static/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js'/>?version=${jsVersion}"></script>
<script type="text/javascript" src="<c:url value='/static/js/plupload-2.1.2/js/i18n/zh_CN.js'/>"></script>
</head>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
//参数：title,tableName,pk,pkField,fk,fkField,urlColumn,descColumn,seqName,imgId,relationId,imgUrl,imgDesc
/**
 必须传的参数：
 	title 		标题,
 	tableName 	存放图片的表名,
 	pk 			存放图片主键列名,
 	pkField 	存放图片主键属性名,
 	fk 			图片关联的业务数据主键列名,
 	fkField 	图片关联的业务数据主键属性名,
 	urlColumn 	图片URL列名,
 	descColumn 	存放图片列名,
 	seqName 	序列名称,
 	imageFolder 存放图片子目录
 	 	
fk,fkField不是必填
 */
var uploader;
var ctx = '<%=request.getContextPath()%>';
var imgJson = {};
imgJson.tableName = "${param.tableName}";
imgJson.pk = "${param.pk}";
imgJson.fk = "${param.fk}";
imgJson.urlColumn = "${param.urlColumn}";
imgJson.descColumn = "${param.descColumn}";
//var newObject = jQuery.extend(true, {}, imgJson);
	<!--plupload-->	
	$(function() {		
		 uploader =  $("#uploader").pluploadQueue({
	        // General settings
	        runtimes : 'html5,flash,silverlight,html4',//当前使用的上传方式
	        url : ctx + '/imgUpLoad/upload',//文件操作的服务器地址
	        file_data_name:"file",//上传文件名name ，相当于<input type="file" name="file"> 
	       // chunk_size : '1mb', //当上传文件大于服务器接收端文件大小限制的时候，可以分多次请求发给服务器，如果不需要从设置中移出
	        rename : false,       //是否重命名
	        dragdrop: false,	  //允许拖拽 false        
	        filters : { //文件的过滤器	         
	            max_file_size : '10mb',
	            mime_types: [ {title : "Image files", extensions : "jpg,gif,png"} ]	  ,     //extensions 扩展        
	            prevent_duplicates : true //不允许选取重复文件
	        },	 
	        has_img_desc : true, //是否需要图片描述input输入框   ??设置false 没用
	        multiple_queues : true,	 // 多文件上传
	        flash_swf_url : "<c:url value='/static/js/plupload-2.1.2/js/Moxie.swf' />",	 // Flash settings     
	        silverlight_xap_url : "<c:url value='/static/js/plupload-2.1.2/js/Moxie.xap' />", // Silverlight settings
	        init : {//事件
	        	BeforeUpload: function(up, file) {      		
	        		var select="#"+file.id+" input[name=imgDesc]";
	        		var desc = $(select).val();
	        		var paraJson = jQuery.extend(true, {}, imgJson);
	        		paraJson.imgDesc = desc;
	        		paraJson.relationId=$('input[name="${param.fkField}"]').val()
	        		up.settings.multipart_params = paraJson; //传递参数给后台 
				},
	        	FileUploaded: function(up, file, info) {        		
	        		var json = eval('(' + info.response + ')');   	
	        		var ${param.pkField}s = $("input[name=${param.pkField}s]").val();//图片在表 t_question_images 的ID
	        		if(${param.pkField}s.length > 0){
	        			${param.pkField}s += ","; 
	        		}
	        		${param.pkField}s += json.ids; // 拼接ID，<input type="hidden" name="ids" id="ids" value="180,181">
	        		$("input[name=${param.pkField}s]").val(${param.pkField}s);
	        		var o ={};
	        		o.pk = json.ids;
	        		o.imgDesc  = json.desc;
	        		o.imgUrl  = json.imgUrl;
	        		var ul =$(".uploadedFiles").css('display','block').find('ul');//预览图片的div ul
	        		showImg(ul,o ,file.id);	        			        			        		
	        	}	        	
	        }
	        
	    });
	   
	    if ($('input[name="${param.fkField}"]').val().length > 0) {
	    	var paraJson = jQuery.extend(true, {}, imgJson);
    		paraJson.relationId=$('input[name="${param.fkField}"]').val()
		   $.post(ctx+ '/imgUpLoad/findImagesByFkId',paraJson,
					function(result){
						var ul =$(".uploadedFiles").css('display','block').find('ul');					
						$.each(result,function(i, item){							
							showImg(ul,item,null);
						});															
			},"json");
	   	} 
	});
	
	function showImg(ul,item,oldFileId){
		if(typeof(item.imgDesc) == "undefined"){
			item.imgDesc ="";
		}
		ul.append('<li class="uploadImg" id="uploadImg_'+item.pk+'">' +									
					'<div class="file_thumb"><img src="'+item.imgUrl+'" /></div>' +		
					'<div class="file_desc"><input name="imgDesc_'+item.pk+'" placeholder="添加图片描述" type="text" value="'+item.imgDesc+'"></div>' +	
					'<div class="file_action">'+
					'	<a class="updateImg_'+item.pk+'" img_id="'+item.pk+'" >添加描述</a>' +       //style="display:${param.display}"
					'	<a class="deleteImg_'+item.pk+'" img_id="'+item.pk+'" oldFileId="'+oldFileId+'" >删除</a></div>' +
					'<div class="file_tip_'+item.pk+'" style="text-align: center;font-size: 12px;color: red;"></div>' +	
				  '</li>'); 
		
		
		$(".updateImg_"+item.pk).on("click",function(e){
			e.preventDefault();
			var imgId = $(this).attr("img_id");
			var desc = $('input[name=imgDesc_'+imgId+']').val();
			var paraJson = jQuery.extend(true, {}, imgJson);
			paraJson.imgId = imgId;
			paraJson.imgDesc = desc;
			$.post(ctx+ '/imgUpLoad/updateImageDescById',paraJson,function(result){
				if(result.success){
					$(".file_tip_"+imgId).html(result.msg);
				}
			},'json');
		});
		
		$(".deleteImg_"+item.pk).on("click",function(e){//alert($(this).attr('img_id'));		
			e.preventDefault();
			var imgId = $(this).attr("img_id");	
			var paraJson = jQuery.extend(true, {}, imgJson);
			paraJson.imgId = imgId;
			paraJson.imgUrl = item.imgUrl;
			//console.log(JSON.stringify(paraJson));
			$.post(ctx + '/imgUpLoad/deleteImgsByImgIds',paraJson,function(result){
				if(result.success){
					$("#uploadImg_"+imgId).remove();
				}
			},'json');	
			
			var oldFileId=$(this).attr("oldFileId");	
			//删除队列中的文件li
			$("#uploader_filelist li").each(function(i){
				var t=$(this);
				if(t.attr("id") == oldFileId ){
					t.remove();
				}
			});
			
		    var uploader = $('#uploader').pluploadQueue();  // 取得上传队列  
		    if (uploader.files.length > 0) { 
		    	uploader.files.splice(oldFileId, 1);
		    }
			
		});		
	}
</script>
<fieldset>
	<legend>${param.title}</legend>
	<div class="uploadedFiles">
		<ul style="overflow: hidden;"></ul>
		<div class="clearfix"></div>
	</div>
	<div id="uploader" ></div>
</fieldset>
