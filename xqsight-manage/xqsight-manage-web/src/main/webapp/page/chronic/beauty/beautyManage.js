/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.chronic");

(function(){
    xqsight.chronic.beautyManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.chronic}
         */
        var obj = this;

        var editbeauty = {};
        
         var beautyEditor = "";

        /**
         * 初始化调用 function
         */
        this.init = function() {
        	KindEditor.ready(function(K) {
                beautyEditor = K.create('#beautyDescript',{
                	minHeight : 120,
                	before : function(){},
                	items: ["source", "|", "undo", "redo", "|", "preview", "template", "cut", "copy", "paste", "plainpaste", "wordpaste", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "table", "hr", "emoticons", "pagebreak", "link"]
                });
                obj.formSetValue();
       		});
       		
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            $("#fileId").on("fileuploaded", function (event, data, previewId, index) {
                var retData = data.response;
                saicfc.win.alert(retData.msg,retData.status);
                if(retData.status == "0"){
                	if(editbeauty.fileId == undefined)
            			editbeauty.fileId ="";
                	$.each(retData.data,function(index,object){
                		editbeauty.fileId += object.fileId+",";
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
		        allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
		        showUpload: true, //是否显示上传按钮
		        showCaption: false,//是否显示标题
		        showClose : false,
		        showPreview : false,
		        showRemove : false,
		        maxFileCount: 9,
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
            editbeauty.beautyName = $("#beautyName").val();
            editbeauty.beautyAddress = $("#beautyAddress").val();
            editbeauty.beautyPhone = $("#beautyPhone").val();
            editbeauty.beautyDescript = beautyEditor.html();;
            editbeauty.remark = $("#remark").val();
            editbeauty.createTime=undefined;
            editbeauty.updateTime=undefined;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#beautyForm").html5Validate(function() {
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
                    if($.getUrlParam("beautyId")== undefined || $.getUrlParam("beautyId") =="" ){
                        url = ctxData + "/beauty/parlor/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/beauty/parlor/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editbeauty,
                        "method" : "post",
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.beautyMain.editCallBackFun({"beautyId" : $.getUrlParam("id")});
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
            var beautyId = $.getUrlParam("beautyId");
            if(beautyId== undefined || beautyId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/beauty/parlor/querybyid?beautyId=" + beautyId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        editbeauty = retData.data;
                        /** 加载图片 **/
                        obj.loadImgFun(editbeauty.fileId);
                        $("#beautyName").val(editbeauty.beautyName);
                        $("#beautyAddress").val(editbeauty.beautyAddress);
                        $("#beautyPhone").val(editbeauty.beautyPhone);
                        beautyEditor.html(editbeauty.beautyDescript)
                        beautyEditor.sync();
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
        		showPic += "<li id='" + object.fileId + "'>";
        		showPic += '<img width="120" height="120" alt="120x120" src="' + object.fileUrl + '" layer-src="' + object.fileUrl + '">';
        		showPic += '<div class="tools tools-bottom in"><a href="javascript:void(0);">';
        		showPic += '<i class="ace-icon fa fa-times red" onclick="javascript:beautyManage.picDeleteFun(' + object.fileId + ');"></i></a></div></li>';
        	})
        	
        	$("#picShow").append(showPic);

            layer.ready(function(){ //为了layer.ext.js加载完毕再执行
                layer.photos({
                    photos: '#picShow'
                });
            });
        }
   
        /**
         * 删除图片
         */
        this.picDeleteFun = function(fileId){
        	 $.ajax({
                 "dataType": "jsonp",
                 "cache": false,
                 "url": ctxData + "/file/manage/delete?fileId=" + fileId + "&date=" + new Date().getTime,
                 "success": function(retData){
                	 saicfc.win.alert(retData.msg,retData.status);
                	 if(retData.status == "0"){
                		 editbeauty.fileId = saicfc.utils.replaceAll(editbeauty.fileId,fileId+",","");
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
        beautyManage.init();
    });
})();

var beautyManage = new xqsight.chronic.beautyManage();





