/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.chronic");

(function(){
    xqsight.chronic.productManage = function(){

        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.chronic}
         */
        var obj = this;

        var editproduct = {};
        
        var productEditor = "";

        /**
         * 初始化调用 function
         */
        this.init = function() {
        	
        	KindEditor.ready(function(K) {
                productEditor = K.create('#productContent',{
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
                xqsight.win.alert(retData.msg,retData.status);
                if(retData.status == "0"){
                	if(editproduct.fileId == undefined)
            			editproduct.fileId ="";

                    $.each(retData.data,function(index,object){
                        editproduct.fileId += object.fileId+",";
                    })

                	obj.showPicFun(retData.data)
                }
            });
            obj.initFileInput();
        };
        
		 /** 初始化控件 **/
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
            editproduct.productName = $("#productName").val();
            editproduct.productPrice = $("#productPrice").val();
            editproduct.productContent = productEditor.html();
            editproduct.productDescription="";
            editproduct.createTime=undefined;
            editproduct.updateTime=undefined;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#productForm").html5Validate(function() {
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
                    if($.getUrlParam("productId")== undefined || $.getUrlParam("productId") =="" ){
                        url = ctxData + "/product/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/product/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editproduct,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = xqsight.tab.getCurrentIframeContent();
                                iframeContent.productMain.editCallBackFun({"productId" : $.getUrlParam("id")});
                                xqsight.win.close();
                            }
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };
            xqsight.win.confirm("确认提交吗？",callback);
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            xqsight.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function(){
            var productId = $.getUrlParam("productId");
            if(productId== undefined || productId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/product/querybyid?productId=" + productId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        editproduct = retData.data;

                        $("#productName").val(editproduct.productName);
                        $("#productPrice").val(editproduct.productPrice);
                        productEditor.html(editproduct.productContent);
                        productEditor.sync();
                        /** 加载图片 **/
                        obj.loadImgFun(editproduct.fileId);
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
                showPic += '<i class="ace-icon fa fa-times red" onclick="javascript:productManage.picDeleteFun(' + object.fileId + ');"></i></a></div></li>';
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
                	 xqsight.win.alert(retData.msg,retData.status);
                	 if(retData.status == "0"){
                		 editproduct.fileId = xqsight.utils.replaceAll(editproduct.fileId,fileId+",","");
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
        productManage.init();
    });
})();

var productManage = new xqsight.chronic.productManage();





