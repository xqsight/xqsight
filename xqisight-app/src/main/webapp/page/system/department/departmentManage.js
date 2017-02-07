/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.department");

(function(){
    sys.department.departmentManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        var editDepartment = {};

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
            editDepartment.departmentName = $("#departmentName").val();
            editDepartment.departmentCode = $("#departmentCode").val();
            editDepartment.customCode = $("#customCode").val();
            editDepartment.departmentType = $("#departmentType").val();
            editDepartment.icon = $("#icon").val();
            editDepartment.sort = $("#sort").val();
            editDepartment.remark = $("#remark").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#departmentForm").html5Validate(function() {
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
                    if($.getUrlParam("departmentId")== undefined || $.getUrlParam("departmentId") =="" ){
                        url = ctxData + "/sys/department/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/department/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editDepartment,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.departmentMain.editCallBackFun({"departmentId" : $.getUrlParam("departmentId")});
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
            var departmentId = $.getUrlParam("departmentId");
            if(departmentId== undefined || departmentId =="" ){
                editDepartment.parentId = $.getUrlParam("parentId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/department/querybyid?departmentId=" + departmentId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editDepartment.departmentId = data.departmentId;
                        editDepartment.parentId = data.parentId;

                        $("#departmentName").val(data.departmentName);
                        $("#departmentCode").val(data.departmentCode);
                        $("#customCode").val(data.customCode);
                        $("#departmentType").val(data.departmentType);
                        $("#icon").val(data.icon);
                        $("#sort").val(data.sort);
                        $("#remark").val(data.remark);
                    }
                }
            });
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        departmentManage.init();
    });
})();

var departmentManage = new sys.department.departmentManage();