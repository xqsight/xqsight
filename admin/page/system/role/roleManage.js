/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.role");

(function(){
    sys.role.roleManage = function(){

        var ctxData = xqsight.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
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
            $("#roleForm").html5Validate(function() {
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
            var roleId = $.getUrlParam("roleId");
            xqsight.utils.put({url:ctxData + "/sys/role/",data:$("#roleForm").serializeArray(),pk:roleId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.roleMain.editCallBackFun({"roleId" : roleId});
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
            var roleId = $.getUrlParam("roleId");
            if(roleId== undefined || roleId =="" ){
                $("#officeId").val($.getUrlParam("officeId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/sys/role/" + roleId,callFun:function (rep) {
                xqsight.utils.fillForm("roleForm",rep.data);
            }});
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        roleManage.init();
    });
})();

var roleManage = new sys.role.roleManage();





