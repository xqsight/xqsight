/**
 * Created by user on 2015/12/14.
 */
(function () {
    var EventManage = {
        init: function () {
            EventManageMVC.View.initEditor();
            EventManageMVC.View.initControl();
            EventManageMVC.View.bindEvent();
            EventManageMVC.Controller.load();
        }
    };

    var EventManageCommon = {
        baseUrl: xqsight.utils.getServerPath("cms"),
        adEditor: {}
    };

    var EventManageMVC = {
        URLs: {
            save: EventManageCommon.baseUrl + "/cms/ad/",
            load: EventManageCommon.baseUrl + "/cms/ad/",
            file: EventManageCommon.baseUrl + "/files/core/editor"
        },
        View: {
            initControl: function () {
                laydate({elem: '#adBeginTime', istime:true,format: 'YYYY-MM-DD'});
                $("#btn-upload-pic").on("click",function(){
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
                        content: "../../component/cropper/cropper.html"
                    })
                });
                $("#btn-preview").on("click",function(){
                    xqsight.win.imgShow($("#adImage").val());
                });
            },
            initEditor: function () {
                var adEditor = new wangEditor('adText');
                // 上传图片
                adEditor.config.uploadImgUrl = EventManageMVC.URLs.file;
                adEditor.config.menus = [
                    'source', '|', 'bold', 'underline', 'italic', 'strikethrough', 'eraser', 'forecolor', 'bgcolor',
                    '|', 'quote', 'fontfamily', 'fontsize', 'head', 'unorderlist', 'orderlist', 'alignleft', 'aligncenter', 'alignright',
                    '|', 'link', 'unlink', '|', 'undo', 'redo', 'fullscreen'
                ];
                adEditor.config.withCredentials = false;
                adEditor.config.hideLinkImg = true;
                adEditor.config.printLog = false;
                adEditor.create();

                EventManageCommon.adEditor = adEditor;
            },
            bindEvent: function () {
                $("#btn_save").on("click", EventManageMVC.Controller.validate);
                $("#btn_cancel").on("click", EventManageMVC.Controller.cancel);
            }
        },
        Controller: {
            validate: function () {
                $("#eventForm").html5Validate(function () {
                   EventManageMVC.Controller.save();
                }, {
                    validate: function () {
                        return true;
                    }
                });
            },
            save: function () {
                var adId = $.getUrlParam("adId");
                xqsight.utils.put({url:EventManageMVC.URLs.save,data:$("#eventForm").serializeArray(),pk:adId,callFun:function (rep) {
                    var iframeContent = xqsight.tab.getCurrentIframeContent();
                    iframeContent.eventMain.editCallBackFun({"adId": adId});
                    xqsight.win.close();
                }})
            },
            cancel: function () {
                xqsight.win.close();
            },
            load: function () {
                var adId = $.getUrlParam("adId");
                if (adId == undefined || adId == "") {
                    return;
                }
                xqsight.utils.load({
                    url: EventManageMVC.URLs.load + adId, callFun: function (rep) {
                        xqsight.utils.fillForm("eventForm", rep.data);
                        EventManageCommon.adEditor.$txt.html(rep.data.adText);
                        $("#imgUrl").attr("src", rep.data.adImage);
                    }
                });
            }
        }
    };

    EventManage.init();

})();

var _imgCallBack = function(data){
    $("#adImage").val(data.url);
    $("#imgUrl").attr("src",data.url);
    layer.close(layIndex);
}