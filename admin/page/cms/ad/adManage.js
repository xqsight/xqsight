/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

var layIndex;

(function () {
    xqsight.cms.adManage = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        /**
         * 初始化调用 function
         */
        this.init = function () {
            //绑定事件
            $("#btn_save").bind("click", obj.validateFun);
            $("#btn_cancel").bind("click", obj.cancelFun);
            $("#btn-upload-pic").on("click", function () {
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
                    area: [$(window).width() - 500 + 'px', $(window).height() - 100 + 'px'],
                    content: "../../component/cropper/cropper.html"
                })
            });
            $("#btn-preview").on("click", function () {
                xqsight.win.imgShow($("#adImage").val());
            });
            obj.formSetValue();
        };

        /**  验证 function */
        this.validateFun = function () {
            $("#adForm").html5Validate(function () {
                obj.saveFun();
            }, {
                validate: function () {
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function () {
            var adId = $.getUrlParam("adId");
            xqsight.utils.put({url:ctxData + "/cms/ad/",data:$("#adForm").serializeArray(),pk:adId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.adMain.editCallBackFun({"adId":adId});
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
            var adId = $.getUrlParam("adId");
            if (adId == undefined || adId == "") {
                return;
            }
            xqsight.utils.load({url: ctxData + "/cms/ad/" + adId,callFun:function (rep) {
                xqsight.utils.fillForm("adForm",rep.data);
                $("#imgUrl").attr("src", rep.data.adImage);
            }})
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        adManage.init();
    });
})();

var adManage = new xqsight.cms.adManage();

var _imgCallBack = function (data) {
    $("#adImage").val(data.url);
    $("#imgUrl").attr("src", data.url);
    layer.close(layIndex)
}