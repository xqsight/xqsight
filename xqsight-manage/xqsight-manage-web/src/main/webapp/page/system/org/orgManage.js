/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.org");

(function(){
    sys.org.orgManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        var editorg = {};

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
            editorg.orgName = $("#orgName").val();
            editorg.orgCode = $("#orgCode").val();
            editorg.customCode = $("#customCode").val();
            editorg.orgType = $("#orgType").val();
            editorg.icon = $("#icon").val();
            editorg.sort = $("#sort").val();
            editorg.remark = $("#remark").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#orgForm").html5Validate(function() {
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
                    if($.getUrlParam("orgId")== undefined || $.getUrlParam("orgId") =="" ){
                        url = ctxData + "/sys/org/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/org/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editorg,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.orgMain.editCallBackFun({"orgId" : $.getUrlParam("orgId")});
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
            var orgId = $.getUrlParam("orgId");
            if(orgId== undefined || orgId =="" ){
                editorg.parentId = $.getUrlParam("parentId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/org/querybyid?orgId=" + orgId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editorg.orgId = data.orgId;
                        editorg.parentId = data.parentId;

                        $("#orgName").val(data.orgName);
                        $("#orgCode").val(data.orgCode);
                        $("#customCode").val(data.customCode);
                        $("#orgType").val(data.orgType);
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
        orgManage.init();
    });
})();

var orgManage = new sys.org.orgManage();