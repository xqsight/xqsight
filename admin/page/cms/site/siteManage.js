/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.siteManage = function(){

        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        /**
         * 初始化调用 function
         */
        this.init = function() {
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.formSetValue();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#siteForm").html5Validate(function() {
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
            var siteId =  $.getUrlParam("siteId");
            xqsight.utils.put({url:ctxData + "/cms/site/",data:$("#siteForm").serializeArray(),pk:siteId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.siteMain.editCallBackFun({"siteId" : siteId});
                xqsight.win.close();
            }});
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
            var siteId = $.getUrlParam("siteId");
            if(siteId== undefined || siteId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }

            xqsight.utils.load({url:ctxData + "/cms/site/" + siteId,callFun:function (rep) {
                xqsight.utils.fillForm("siteForm",rep.data);
            }})
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        siteManage.init();
    });
})();

var siteManage = new xqsight.cms.siteManage();