/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.user");

(function(){
    sys.user.userManage = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;
        var editUser = {};
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
            editUser.userName= $("#userName").val();
            editUser.loginId= $("#loginId").val();
            editUser.locked= $("#locked").val();
            editUser.remark= $("#remark").val();
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
                    obj.setParamFun();
                    $.ajax({
                        "url": ctxData + "/sys/login/save?date=" + new Date().getTime(),
                        "data": editUser,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                saicfc.win.alert("您的默认密码是:!password");
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.userMain.editCallBackFun({"userId" : $.getUrlParam("id")});
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
            var id = $.getUrlParam("id");
            if(id == undefined || id == "" ){
                editUser.orgId = $.getUrlParam("orgId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/login/querybyid?id=" + id + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editUser.id = data.id;
                        editUser.orgId = data.orgId;

                        $("#userName").val(data.userName);
                        $("#loginId").val(data.loginId);
                        $("#locked").val(data.locked);
                        $("#remark").val(data.remark);
                    }
                }
            });
        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        userManage.init();
    });
})();

var userManage = new sys.user.userManage();





