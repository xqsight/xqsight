/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.office");

(function(){
    sys.office.officeManage = function(){

        var ctxData = xqsight.utils.getServerPath("system");

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

            //归属区域
            $("#areaShow").ComboBoxTree({
                url: ctxData + "/sys/area/tree",
                description: "==请选择==",
                height: "195px",
                allowSearch: false,
                callback:function(data){
                    $("#areaId").val(data.id);
                }
            });
            obj.formSetValue();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#officeForm").html5Validate(function() {
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
            var officeId = $.getUrlParam("officeId");
            xqsight.utils.put({url:ctxData + "/sys/office/",data:$("#officeForm").serializeArray(),pk:officeId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.officeMain.editCallBackFun({"officeId" : officeId});
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
            var officeId = $.getUrlParam("officeId");
            if(officeId== undefined || officeId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }
            xqsight.utils.load({url: ctxData + "/sys/office/" + officeId ,callFun:function (rep) {
                xqsight.utils.fillForm("officeForm",rep.data);
                $("#areaShow").ComboBoxTreeSetValue(rep.data.areaId);
                //$("#officeType").selectpicker('val',data.officeType);
            }});
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        officeManage.init();
    });
})();

var officeManage = new sys.office.officeManage();