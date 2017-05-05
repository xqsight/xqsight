/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.area");

(function(){
    sys.area.areaManage = function(){
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
            $("#areaForm").html5Validate(function() {
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
            var areaId = $.getUrlParam("areaId");
            xqsight.utils.put({url:ctxData + "/sys/area/",data:$("#areaForm").serializeArray(),pk:areaId,callFun:function(rep){
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.areaMain.editCallBackFun({"areaId" : areaId});
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
            var areaId = $.getUrlParam("areaId");
            if(areaId == undefined || areaId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/sys/area/" + areaId,callFun:function (rep) {
                xqsight.utils.fillForm("areaForm",rep.data);
            }});
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        areaManage.init();
    });
})();

var areaManage = new sys.area.areaManage();





