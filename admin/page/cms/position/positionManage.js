/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.positionManage = function(){

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
            $("#positionForm").html5Validate(function() {
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
            var positionId = $.getUrlParam("positionId");
            xqsight.utils.put({url:ctxData + "/cms/position/",data:$("#positionForm").serializeArray(),pk:positionId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.positionMain.editCallBackFun({"positionId" : positionId});
                xqsight.win.close();
            }})
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
            var positionId = $.getUrlParam("positionId");
            if(positionId== undefined || positionId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }

            xqsight.utils.load({url: ctxData + "/cms/position/" + positionId,callFun:function (rep) {
                xqsight.utils.fillForm("positionForm",rep.data);
            }})
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        positionManage.init();
    });
})();

var positionManage = new xqsight.cms.positionManage();