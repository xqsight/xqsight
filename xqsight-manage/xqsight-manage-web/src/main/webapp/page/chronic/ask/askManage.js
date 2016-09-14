/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.chronic");

(function(){
    xqsight.chronic.askManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.chronic}
         */
        var obj = this;

        var editComent = {};

        var askEditor = "";

        /**
         * 初始化调用 function
         */
        this.init = function() {
            KindEditor.ready(function(K) {
                askEditor = K.create('#articleContent',{
                    minHeight : 120,
                    before : function(){},
                    items: ["source", "|", "undo", "redo", "|", "preview", "template", "cut", "copy", "paste", "plainpaste", "wordpaste", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "table", "hr", "emoticons", "pagebreak", "link"]
                });
                obj.formSetValue();
            });
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.loadComentFun();

        };
        
        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            editComent.comment = askEditor.html();
            editComent.associcationId = $.getUrlParam("articleId");
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            if(askEditor.html().length < 1){
                saicfc.win.alert("恢复内容不能为空");
                return false;
            }
            obj.saveFun();
        };

        /**
         * 保存 function
         */
        this.saveFun = function(){
            var callback = function(btn){
                if(btn == "yes"){
                    obj.setParamFun();
                    var url = ctxData + "/cms/comment/save";
                    $.ajax({
                        "url": url ,
                        "data": editComent,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == 0)
                                obj.loadComentFun();
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
            var articleId = $.getUrlParam("articleId");
            if(articleId== undefined || articleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/ask/querybyid?articleId=" + articleId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        $("#articleTitle").html(retData.data.articleTitle);
                        $("#articleComent").html(retData.data.articleContent);
                        obj.loadImgFun(retData.data.fileId);
                    }
                }
            });
        }

        /**
         * 加载评论
         */
        this.loadComentFun = function(){
            var articleId = $.getUrlParam("articleId");
            if(articleId== undefined || articleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/ask/querycommentbyarticleid?articleId=" + articleId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var commentHtml = "";
                        $.each(retData.data,function(index,object){
                            commentHtml += '<div class="profile-activity clearfix"><div>';
                            commentHtml += '<img class="pull-left" alt="' + object.userName + '" src="' + object.imgUrl + '">';
                            commentHtml += object.comment;
                            commentHtml += '<div class="time"><i class="ace-icon fa fa-clock-o bigger-110"></i>';
                            commentHtml += saicfc.moment.formatYMDHms(object.createTime);
                            commentHtml += '</div></div></div>';
                        });
                        $("#comment").html("").html(commentHtml);

                        parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                    }
                }
            });
        }
    
        /**
         * 加载图片
         */
        this.loadImgFun = function(fileId){
            if(fileId == undefined || fileId == "")
                return;
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
                showPic += '<img width="120" height="120" alt="120x120" src="' + object.fileUrl + '" layer-src="' + object.fileUrl + '"></li>';
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
                	 saicfc.win.alert(retData.msg);
                	 if(retData.status == "0"){
                		 editarticle.fileId = saicfc.utils.replaceAll(editarticle.fileId,fileId+",","");
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
        askManage.init();
    });
})();

var askManage = new xqsight.chronic.askManage();





