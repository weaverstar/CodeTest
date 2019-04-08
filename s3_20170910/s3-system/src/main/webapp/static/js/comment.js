$(function(){
  //防止刷阅读量
  /*var timeoutId_viewCount = 0;
  timeoutId_viewCount = window.setTimeout(function(){
    var url= ctx + "/file/updateReadNum";
    var json={"fileId":$('#fileId').val()};
    url = url + "?ts="+new Date().getTime();
    $.getJSON(url,json,function (data) {
      //$("#file_read").html("<i class=\"fa fa-eye\"></i> " + data.FILE_READ);
    });
  },15*1000);

  $(window).unload(function(){
    window.clearTimeout(timeoutId_viewCount);
  });*/

  var attachType = $('#attachType').val();
  var attachDir = $('#attachDir').val();
  var htmArr = [];
  if(/(gif|jpg|png|bmp)$/i.test(attachType)){
	  //if(true){
		//attachDir = 'http://jsb-test.oss-cn-hangzhou.aliyuncs.com/60a01bfd25474c0baf9cbee6dc489a84.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/5c79ed8dddba4bba84e545e441b4342f.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/94e602d4d8fa4b53b0ca1011354f540b.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/d68f640368654529886891fd27542f0a.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/3590c50c8483444e931153882f01b2cf.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/d3b3b79ac5de48d18612e1a1dfa2e4b0.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/c493830b7afc4b5bb3fcec2d74dea5cb.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/d9706ae0f682424fa47a3a9fd1a6afee.jpg,http://jsb-test.oss-cn-hangzhou.aliyuncs.com/f78308d3e68c4fb7a33fac88011d421a.jpg';
	    var imgUrlArr = attachDir.split(',');
	    $.each(imgUrlArr, function(){
	      htmArr.push('<img src="', this, '" style="max-width: 100%;display:block;margin:0 auto 5px;" >');
	    });
  /*if(/(gif|jpg|png|bmp)$/i.test(fileType)){
    htmArr.push('<img src="', attachDir, '" width="868" >');*/
    $('.control-show').append(htmArr.join(''));
  }else if(/\.swf$/i.test(attachType)){
    var swfC = WriteSWF(attachDir);
    $(".control-show").html(swfC);
  }else if(/(ppt|pptx|pot|potx|pps|dps|dpt)$/i.test(attachType)){
    htmArr.push('<iframe src="http://officeweb365.com/o/?i=7073&furl=', attachDir, '" width="868" height="600"  frameborder="no" border="0" >');
    $('.control-show').append(htmArr.join(''));
  } else{
    htmArr.push('<iframe src="http://officeweb365.com/o/?i=7073&furl=', attachDir, '" width="100%" height="100%"  frameborder="no" border="0" >');
    $('.control-show').append(htmArr.join(''));
  }

})