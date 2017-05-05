/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.article");
var layIndex;

(function () {
    cms.article.articleManage = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        var artileEditor = {};


        /**  初始化调用 function **/
        this.init = function () {
            laydate({elem: '#publishTime', format: 'YYYY-MM-DD'});

            //绑定事件
            $("#btn_save").bind("click", obj.validateFun);
            $("#btn_cancel").bind("click", obj.cancelFun);
            $("#thumbnailImgShow").on("click",function(){
                layIndex = layer.open({
                    type: 2,
                    title: '<i class="ace-icon fa fa-edit"></i>  选择图片',
                    shadeClose: false,
                    shade: 0.2,
                    shift: 1,
                    skin: "layui-layer-molv",
                    moveOut: true,
                    offset: "10px",
                    maxmin: true, //开启最大化最小化按钮
                    area: [$(window).width()-500 + 'px', $(window).height()-100 + 'px'],
                    content: "../../component/cropper/cropper.html?type=1"
                })
            });
            $("#articleImgShow").on("click",function(){
                layIndex = layer.open({
                    type: 2,
                    title: '<i class="ace-icon fa fa-edit"></i>  选择图片',
                    shadeClose: false,
                    shade: 0.2,
                    shift: 1,
                    skin: "layui-layer-molv",
                    moveOut: true,
                    offset: "10px",
                    maxmin: true, //开启最大化最小化按钮
                    area: [$(window).width()-500 + 'px', $(window).height()-100 + 'px'],
                    content: "../../component/cropper/cropper.html?type=2"
                })
            });
            obj.editorFun();
            obj.tags();
        };

        this.tags = function () {
            var tag_input = $('#tags');
            try {
                tag_input.tag({
                    placeholder: tag_input.attr('placeholder'),
                    allowDuplicates: false,
                    //enable typeahead by specifying the source array
                    source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
                    //or fetch data from database, fetch those that match "query"
                    source : function (query, process) {
                        $.ajax({
                            url : ctxData + '/cms/tag/?tagName=' + encodeURIComponent(query),
                            beforeSend : function () {},
                            method : "get",
                            success : function (result_items) {
                                array = new Array();
                                $.each(result_items.data,function(index,object){
                                    array.push(object.tagName);
                                })
                                process(array);
                            },
                            complete : function () {},
                            error : function () {}
                        });
                    }
                });
                obj.formSetValue();
            }
            catch (e) {
            }
        };

        this.editorFun = function () {
            artileEditor = new wangEditor('articleContent');
            // 上传图片
            artileEditor.config.uploadImgUrl = ctxData + '/files/core/editor';
            artileEditor.config.withCredentials = false;
            artileEditor.config.hideLinkImg = true;
            artileEditor.config.printLog = false;
            artileEditor.config.menus = [
                 'source','bold', 'underline', 'italic',  'strikethrough', 'eraser', 'forecolor', 'bgcolor',
                 'quote', 'fontfamily', 'fontsize', 'head', 'unorderlist', 'orderlist', 'alignleft', 'aligncenter', 'alignright',
                 'link', 'unlink',  'table', 'emotion',
                 'img',  'video', 'insertcode', 'undo', 'redo', 'fullscreen'

            ];
            artileEditor.config.uploadParams = {
                "editor": 'wangeditor',
                "action":"uploadimage"
            };
            artileEditor.config.uploadImgFns.onload = function (resultText, xhr) {
                artileEditor.command(null, 'InsertImage', resultText);
            };

            artileEditor.create();
        }

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function () {
            editArticle.articleTitle = $("#articleTitle").val();
            editArticle.articleDesp = $("#articleDesp").val();
            editArticle.articleAuthor = $("#articleAuthor").val();
            editArticle.articleSource = $("#articleSource").val();
            editArticle.articleHit = $("#articleHit").val();
            editArticle.publishTime = $("#publishTime").val();
            editArticle.articleImg = $("#articleImg").val();
            editArticle.thumbnailImg = $("#thumbnailImg").val();
            editArticle.department = $("#department").val();
            editArticle.articleContent = encodeURIComponent(artileEditor.$txt.html());
            editArticle.tags = $("#tags").val();
        };

        /**  验证 function */
        this.validateFun = function () {
            $("#articleForm").html5Validate(function () {
                obj.saveFun();
            }, {
                validate: function () {
                    return true;
                }
            });
        };

        /**  保存 function */
        this.saveFun = function () {
            var articleId = $.getUrlParam("articleId");
            var data = $("#articleForm").serializeArray();
            data.push({name:"articleContent",value:encodeURIComponent(artileEditor.$txt.html())});
            xqsight.utils.put({url:ctxData + "/cms/article/",data:data,pk:articleId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.articleMain.editCallBackFun({"articleId":articleId});
                xqsight.win.close();
            }})
        };

        /**
         * 取消 function
         */
        this.cancelFun = function () {
            xqsight.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function () {
            var articleId = $.getUrlParam("articleId");
            if (articleId == undefined || articleId == "") {
                return;
            }

            xqsight.utils.load({url:ctxData + "/cms/article/" + articleId,callFun:function (rep) {
                xqsight.utils.fillForm("articleForm",rep.data);
                $("#imgArticle").attr("src",rep.data.articleImg);
                $("#imgThumbnail").attr("src",rep.data.thumbnailImg);
                artileEditor.$txt.html(rep.data.articleContent);
                $.each(rep.data.tags,function(index,object){
                    var $tag_obj = $('#tags').data('tag');
                    $tag_obj.add(object);
                });
            }})
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        articleManage.init();
    });
})();

var articleManage = new cms.article.articleManage();

var _imgCallBack = function(object){
    if(object.type == "1"){
        $("#thumbnailImg").val(object.url);
        $("#imgThumbnail").attr("src",object.url);
    }else if(object.type == "2"){
        $("#articleImg").val(object.url);
        $("#imgArticle").attr("src",object.url);
    }

    layer.close(layIndex)
}