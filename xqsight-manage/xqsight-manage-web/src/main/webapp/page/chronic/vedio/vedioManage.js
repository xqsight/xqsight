/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.chronic");

(function(){
    xqsight.chronic.vedioManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.chronic}
         */
        var obj = this;

        var editvedio = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.formSetValue();

            $("#fileId").on("fileuploaded", function (event, data, previewId, index) {
                var retData = data.response;
                saicfc.win.alert(retData.msg);
                if(retData.status == "0"){
                	$.each(retData.data,function(index,object){
                		editvedio.fileId = object.fileId ;
                	})
                	obj.showPicFun(retData.data)
                }
            });

            obj.initFileInput();
        };
        
		 //初始化控件
		this.initFileInput = function(){
			var control = $('#fileId'); 
		    control.fileinput({
		        language: 'zh', //设置语言
		        uploadUrl: ctxData + "/file/manage/upload", //上传的地址
		        allowedFileExtensions : ['avi', 'mp4'],//接收的文件后缀
		        showUpload: true, //是否显示上传按钮
		        showCaption: false,//是否显示标题
		        showClose : false,
		        showPreview : false,
		        showRemove : false,
		        autoReplace : true, //自动替换
		        maxFileCount: 1,
		        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		        browseClass: "btn btn-primary", //按钮样式             
		        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
		    });
		};
	
        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            editvedio.vedioName = $("#vedioName").val();
            editvedio.vedioDescription = $("#vedioDescription").val();
            editvedio.remark = $("#remark").val();
            editvedio.createTime=undefined;
            editvedio.updateTime=undefined;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#vedioForm").html5Validate(function() {
                obj.saveFun();
            }, {
                validate : function() {
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function(){
            var callback = function(btn){
                if(btn == "yes"){
                    obj.setParamFun();
                    var url = "";
                    if($.getUrlParam("vedioId")== undefined || $.getUrlParam("vedioId") =="" ){
                        url = ctxData + "/gene/vedio/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/gene/vedio/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editvedio,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.vedioMain.editCallBackFun({"vedioId" : $.getUrlParam("id")});
                                saicfc.win.close();
                            }
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };
            saicfc.win.confirm("确认提交吗？",callback);
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            saicfc.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function(){
            var vedioId = $.getUrlParam("vedioId");
            if(vedioId== undefined || vedioId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/gene/vedio/querybyid?vedioId=" + vedioId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        editvedio = retData.data;
                        $("#vedioName").val(editvedio.vedioName);
                        $("#vedioDescription").val(editvedio.vedioDescription);
                        $("#remark").val(editvedio.remark);
                        /** 加载视频 **/
                        obj.loadImgFun(editvedio.fileId);
                    }
                }
            });
        }
    
        /**
         * 加载图片
         */
        this.loadImgFun = function(fileId){
        	 $.ajax({
                 "dataType": "jsonp",
                 "cache": false,
                 "url": ctxData + "/file/manage/query?fileId=" + fileId + "&date=" + new Date().getTime,
                 "success": function(retData){
                     if(retData.status == "0"){
                    	  obj.showPicFun(retData.data)
                     }
                 }
             });
        }
        
        /**
         * 渲染图片
         */
        this.showPicFun = function(data){
        	var showPic = "";
        	$.each(data,function(index,object){
        		showPic += "<li id='" + object.fileId + "'><a href='"+ object.fileUrl + "' data-rel='colorbox' class='cboxElement'>";
        		showPic += '<img width="120" height="120" alt="120x120" src="' + object.fileUrl + '">';
        		showPic += '<div class="text"><div class="inner">' + object.fileName + '</div></div></a>';
        		showPic += '<div class="tools tools-bottom in"><a href="javascript:void(0);">';
        		showPic += '<i class="ace-icon fa fa-times red" onclick="javascript:vedioManage.picDeleteFun(' + object.fileId + ');"></i></a></div></li>';
        	})
        	
        	$("#picShow").html("");
        	$("#picShow").append(showPic);
        }
   
        /**
         * 删除图片
         */
        this.picDeleteFun = function(fileId){
        	 $.ajax({
                 "dataType": "jsonp",
                 "cache": false,
                 "url": ctxData + "/file/manage/deletebyid?fileId=" + fileId + "&date=" + new Date().getTime,
                 "success": function(retData){
                	 saicfc.win.alert(retData.msg,retData.status);
                	 if(retData.status == "0"){
                		 editvedio.fileId = saicfc.utils.replaceAll(editvedio.fileId,fileId+",","");
                		 $("#picShow #" + fileId).remove();
                	 }
                	 
                 }
             });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        vedioManage.init();
    });
})();

var vedioManage = new xqsight.chronic.vedioManage();





