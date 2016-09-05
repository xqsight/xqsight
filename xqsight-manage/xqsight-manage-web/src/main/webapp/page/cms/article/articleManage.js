/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.articleManage = function(){
        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var editArticle = {};

        var artileEditor = {};
        /**
         * 存放所有的图片
         */
        var img = [];

        /**
         * 初始化调用 function
         */
        this.init = function() {
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.loadModeCodeFun();

            obj.Editor();

        };

        this.Editor = function(){
            KindEditor.ready(function(K) {
                artileEditor = K.create('#articleContent', {
                    uploadJson :ctxData+ '/file/manage/uploadftp',
                    //  fileManagerJson :ctxData+ '/cms/article/query', //图片空间获取图片
                    allowFileManager : true,
                    afterBlur: function(){this.sync();},
                    afterUpload : function(url) {//获取上传图片的路径
                        img.push(url);
                    },
                    allowImageRemote: false,//隐藏网络上传图片
                    urlType:'',
                    width:"100%",height:"400px",
                    items: ["source", "|", "undo", "redo", "|", "preview", "print", "template", "code", "cut", "copy", "paste", "plainpaste", "wordpaste", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "flash", "media", "insertfile", "table", "hr", "emoticons", "baidumap", "pagebreak", "anchor", "link", "unlink", "|", "about"]
                });
            });
        }

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            editArticle.articleTitle = $("#articleTitle").val();
            editArticle.modelId = $("#modelId").val();
            editArticle.articleDescription = $("#articleDescription").val();
            editArticle.articleContent=artileEditor.html();
            editArticle.createTime=undefined;
            editArticle.updateTime=undefined;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#articleForm").html5Validate(function() {
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
                    if($.getUrlParam("articleId")== undefined || $.getUrlParam("articleId") =="" ){
                        url = ctxData + "/cms/article/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/cms/article/update?date=" + new Date().getTime();
                    }

                    $.ajax({
                        "url": url +"?img="+img,
                        "data": editArticle,
                        "type":"POST",
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.articleMain.editCallBackFun({"articleId" : $.getUrlParam("articleId")});
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
            var articleId = $.getUrlParam("articleId");
            if(articleId== undefined || articleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/cms/article/querybyid?articleId=" + articleId + "&date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.status == "0"){
                        editArticle = retData.data.cmsArticle;
                        $("#articleTitle").val(editArticle.articleTitle);
                        $("#articleDescription").val(editArticle.articleDescription);
                        $("#modelId").selectpicker('val', editArticle.modelId);
                        img = retData.data.img;
                        artileEditor.html(editArticle.articleContent);
                        artileEditor.sync();
                    }
                }
            });
        }

        /**
         * 渲染 模块
         */
        this.loadModeCodeFun = function(){
            $.ajax({
                "url": "modeData.json",
                "dataType": "json",
                "cache": true,
                "success": function(retData){
                    var data = ($.getUrlParam("appId") == "1") ? retData.CHRONIC_GENE : retData.CHRONIC_MANAGE;
                    $.each(data,function(index,object){
                        $("#modelId").append("<option value='" + object.modelId +"'>" + object.modelName + "</option>");
                    });
                    $('#modelId').selectpicker('refresh');

                    obj.formSetValue();
                }
            });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        articleManage.init();
    });
})();

var articleManage = new saicfc.pmpf.articleManage();
