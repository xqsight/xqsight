/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function () {
    xqsight.cms.tagManage = function () {
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
            obj.formSetValue();
        };

        /**
         * 验证 function
         */
        this.validateFun = function () {
            $("#tagForm").html5Validate(function () {
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
            var tagId =  $.getUrlParam("tagId");
            xqsight.utils.put({url:ctxData + "/cms/tag/",data:$("#tagForm").serializeArray(),pk:tagId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.tagMain.editCallBackFun({"tagId": tagId});
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
            var tagId = $.getUrlParam("tagId");
            if (tagId == undefined || tagId == "") {
                return;
            }
            xqsight.utils.load({url:ctxData + "/cms/tag/" + tagId,callFun:function (rep) {
                xqsight.utils.fillForm("tagForm",rep.data);
            }})
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        tagManage.init();
    });
})();

var tagManage = new xqsight.cms.tagManage();