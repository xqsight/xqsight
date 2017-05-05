/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.dict");

(function(){
    sys.dict.dictManage = function(){

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
            $("#dictForm").html5Validate(function() {
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
            var dictId = $.getUrlParam("dictId");
            xqsight.utils.put({url:ctxData + "/sys/dict/",data:$("#dictForm").serializeArray(),pk:dictId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.dictMain.editCallBackFun({"dictId" : dictId});
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
            var dictId = $.getUrlParam("dictId");
            if(dictId == undefined || dictId =="" ){
                $("#parentId").val($.getUrlParam("parentId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/sys/dict/" + dictId,callFun:function (rep) {
                xqsight.utils.fillForm("dictForm",rep.data);
            }});
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        dictManage.init();
    });
})();

var dictManage = new sys.dict.dictManage();





