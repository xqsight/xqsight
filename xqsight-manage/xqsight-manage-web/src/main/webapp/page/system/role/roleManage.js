/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.role");

(function(){
    sys.role.roleManage = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var editRole = {};

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
            editRole.roleName = $("#roleName").val();
            editRole.roleCode = $("#roleCode").val();
            editRole.remark = $("#remark").val();
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
            var callback = function(btn){
                if(btn == "yes"){
                    obj.setParamFun();
                    var url = "";
                    if($.getUrlParam("roleId")== undefined || $.getUrlParam("roleId") =="" ){
                        url = ctxData + "/sys/role/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/role/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editRole,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.roleMain.editCallBackFun({"roleId" : $.getUrlParam("id")});
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
            var roleId = $.getUrlParam("roleId");
            if(roleId== undefined || roleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/role/querybyid?roleId=" + roleId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editRole.roleId = data.roleId;
                        $("#roleName").val(data.roleName);
                        $("#roleCode").val(data.roleCode);
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
        roleManage.init();
    });
})();

var roleManage = new sys.role.roleManage();





