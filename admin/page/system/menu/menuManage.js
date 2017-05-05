/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.menu");

(function(){
    sys.menu.menuManage = function(){

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
            $("#btn_save").on("click",obj.validateFun);
            $("#btn_cancel").on("click",obj.cancelFun);

            obj.formSetValue();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#menuForm").html5Validate(function() {
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
            var menuId = $.getUrlParam("menuId");
            xqsight.utils.put({url:ctxData + "/sys/menu/",data:$("#menuForm").serializeArray(),pk:menuId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.menuMain.editCallBackFun({"menuId" : menuId});
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
            var menuId = $.getUrlParam("menuId");
            if(menuId == undefined || menuId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/sys/menu/" + menuId,callFun:function (rep) {
                xqsight.utils.fillForm("menuForm",rep.data);
            }});
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        menuManage.init();
    });
})();

var menuManage = new sys.menu.menuManage();





