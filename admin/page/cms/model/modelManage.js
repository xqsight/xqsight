/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function () {
    xqsight.cms.modelManage = function () {
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

            //归属站点
            $("#siteShow").ComboBoxTree({
                url: ctxData + "/cms/site/tree?date="+new Date().getTime(),
                description: "==请选择==",
                height: "195px",
                allowSearch: false,
                callback:function(data){
                    $("#siteId").val(data.id);
                }
            });

            obj.formSetValue();
        };


        /**
         * 验证 function
         */
        this.validateFun = function () {
            $("#modelForm").html5Validate(function () {
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
            var modelId = $.getUrlParam("modelId");
            xqsight.utils.put({url:ctxData + "/cms/model/",data:$("#modelForm").serializeArray(),pk:modelId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.modelMain.editCallBackFun({"modelId": modelId});
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
            var modelId = $.getUrlParam("modelId");
            if (modelId == undefined || modelId == "") {
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/cms/model/" + modelId,callFun:function (rep) {
                xqsight.utils.fillForm("modelForm",rep.data);
                $("#siteId").ComboBoxTreeSetValue(rep.data.siteId);
            }})
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        modelManage.init();
    });
})();

var modelManage = new xqsight.cms.modelManage();