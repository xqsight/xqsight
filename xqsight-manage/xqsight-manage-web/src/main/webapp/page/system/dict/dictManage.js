/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.dict");

(function(){
    sys.dict.dictManage = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var editdict = {};

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
            editdict.dictName = $("#dictName").val();
            editdict.dictCode = $("#dictCode").val();
            editdict.remark = $("#remark").val();
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
            var callback = function(btn){
                if(btn == "yes"){
                    obj.setParamFun();
                    var url = "";
                    if($.getUrlParam("dictId")== undefined || $.getUrlParam("dictId") =="" ){
                        url = ctxData + "/sys/dict/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/dict/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editdict,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.dictMain.editCallBackFun({"dictId" : $.getUrlParam("dictId"),"type" : "dict"});
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
            var dictId = $.getUrlParam("dictId");
            if(dictId== undefined || dictId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/dict/querybyid?dictId=" + dictId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editdict.dictId = data.dictId;
                        
                        $("#dictName").val(data.dictName);
                        $("#dictCode").val(data.dictCode);
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
        dictManage.init();
    });
})();

var dictManage = new sys.dict.dictManage();





