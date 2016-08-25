/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.appManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        var editapp = {};

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
            editapp.appName = $("#appName").val();
            editapp.appCode = $("#appCode").val();
            editapp.appDomain = $("#appDomain").val();
            editapp.appLogo = $("#appLogo").val();
            editapp.appKeyword = $("#appKeyword").val();
            editapp.appCopyright = $("#appCopyright").val();
            editapp.appDescription = $("#appDescription").val();
            editapp.remark = $("#remark").val();
            editapp.createTime=undefined;
            editapp.updateTime=undefined;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#appForm").html5Validate(function() {
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
                    obj.setParamFun();
                    var url = "";
                    if($.getUrlParam("appId")== undefined || $.getUrlParam("appId") =="" ){
                        url = ctxData + "/cms/app/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/cms/app/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editapp,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.appMain.editCallBackFun({"appId" : $.getUrlParam("id")});
                                saicfc.win.close();
                            }
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
            var appId = $.getUrlParam("appId");
            if(appId== undefined || appId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/cms/app/querybyid?appId=" + appId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        editapp = retData.data;
                        $("#appName").val(editapp.appName);
                        $("#appCode").val(editapp.appCode);
                        $("#appDomain").val(editapp.appDomain);
                        $("#appLogo").val(editapp.appLogo);
                        $("#appKeyword").val(editapp.appKeyword);
                        $("#appCopyright").val(editapp.appCopyright);
                        $("#appDescription").val(editapp.appDescription);
                        $("#remark").val(editapp.remark);
                    }
                }
            });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        appManage.init();
    });
})();

var appManage = new xqsight.cms.appManage();