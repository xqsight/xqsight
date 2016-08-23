/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.dict");

(function(){
    sys.dict.dictDetailManage = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var editdictDetail = {};

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
            editdictDetail.dictValue = $("#dictValue").val();
            editdictDetail.dictDesp = $("#dictDesp").val();
            editdictDetail.dictLang = $("#dictLang").val();
            editdictDetail.active = $("#active").val();
            editdictDetail.editable = $("#editable").val();
            editdictDetail.remark = $("#remark").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#dictDetailForm").html5Validate(function() {
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
                    if($.getUrlParam("dictDetailId")== undefined || $.getUrlParam("dictDetailId") =="" ){
                        url = ctxData + "/sys/dict/savedetail?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/dict/updatedetail?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "url": url ,
                        "data": editdictDetail,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.dictMain.editCallBackFun({"dictDetailId" : $.getUrlParam("dictDetailId")});
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
            var dictDetailId = $.getUrlParam("dictDetailId");
            if(dictDetailId== undefined || dictDetailId =="" ){
                editdictDetail.dictId = $.getUrlParam("dictId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/dict/querydetailbyid?dictDetailId=" + dictDetailId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editdictDetail.dictDetailId = data.dictDetailId;
                        editdictDetail.dictId = data.dictId;
                        
                        $("#dictValue").val(data.dictValue);
                        $("#dictDesp").val(data.dictDesp);
                        $("#dictLang").selectpicker('val', data.dictLang);
                        $("#active").selectpicker('val', data.active);
                        $("#editable").selectpicker('val', data.editable);
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
        dictDetailManage.init();
    });
})();

var dictDetailManage = new sys.dict.dictDetailManage();





