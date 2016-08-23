/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.modelManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        var editmodel = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.loadAppDataFun();
        };

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            editmodel.modelTitle = $("#modelTitle").val();
            editmodel.modelCode = $("#modelCode").val();
            editmodel.appId = $("#appId").val();
            editmodel.modelClass = $("#modelClass").val();
            editmodel.modelThumbnails = $("#modelThumbnails").val();
            editmodel.modelSort = $("#modelSort").val();
            editmodel.modelDescription = $("#modelDescription").val();
            editmodel.remark = $("#remark").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#modelForm").html5Validate(function() {
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
                    if($.getUrlParam("modelId")== undefined || $.getUrlParam("modelId") =="" ){
                        url = ctxData + "/cms/model/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/cms/model/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editmodel,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.modelMain.editCallBackFun({"modelId" : $.getUrlParam("id")});
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
            var modelId = $.getUrlParam("modelId");
            if(modelId== undefined || modelId =="" ){
                editmodel.parentId = $.getUrlParam("parentId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/cms/model/querybyid?modelId=" + modelId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editmodel.modelId = data.modelId;
                        editmodel.parentId = data.parentId;

                        $("#modelTitle").val(data.modelTitle);
                        $("#appId").val(data.appId);
                        $("#modelCode").val(data.modelCode);
                        $("#modelClass").val(data.modelClass);
                        $("#modelThumbnails").val(data.modelThumbnails);
                        $("#modelSort").val(data.modelSort);
                        $("#modelDescription").val(data.modelDescription);
                        $("#remark").val(data.remark);
                    }
                }
            });
        };

        /**
         * 加载 app
         */
        this.loadAppDataFun = function(){
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/cms/app/queryall?date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        $.each(retData.data,function(index,object){
                            $("#appId").append("<option value=" + object.appId + ">" + object.appName + "</option>");
                        });
                    }
                    obj.formSetValue();
                }
            });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        modelManage.init();
    });
})();

var modelManage = new xqsight.cms.modelManage();