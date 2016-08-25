/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.userManage = function(){

        var ctxData = saicfc.utils.getSysServerPath();

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
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
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            var param = "";
            param += "&userName=" + $("#userName").val();
            param += "&loginId=" + $("#loginId").val();
            param += "&locked=" + $("#locked").val();
            return param;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#userForm").html5Validate(function() {
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
            var callback = function(btn){
                if(btn == "yes"){
                    var index = saicfc.progress.loading();
                    $.ajax({
                        "url": ctxData + "/sys/login/save?date=" + new Date().getTime(),
                        "data": obj.setParamFun(),
                        "success": function(retData){
                            saicfc.win.alert(retData.msg);
                            saicfc.progress.removeLoading(index)
                            if(retData.status == "0"){
                                saicfc.win.alert("您的默认密码是:!password");
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.userMain.editCallBackFun({"userId" : $.getUrlParam("id")});
                                saicfc.win.close();
                            }
                        },
                        "error" : function(data){
                            saisaicfc.win.alert(data);
                            saicfc.progress.removeLoading(index);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };
            saicfc.win.confirm("确认提交吗？",callback);
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            saicfc.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function(){
            if($.getUrlParam("id")== undefined || $.getUrlParam("id") =="" ){
                return;
            }



        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        userManage.init();
    });
})();

var userManage = new saicfc.pmpf.userManage();





