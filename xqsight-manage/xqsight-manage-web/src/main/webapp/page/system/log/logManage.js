/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.log");

(function(){
    sys.log.logManage = function(){

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
            $("#btn_cancel").bind("click",obj.cancelFun);

            obj.formSetValue();
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
            var logId = $.getUrlParam("logId");
            if(logId== undefined || logId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/log/querybyid?logId=" + logId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editRole.logId = data.logId;
                        $("#logTitle").val(data.logTitle);
                        $("#logDesc").val(data.logDesc);
                        $("#reqMethod").val(data.reqMethod);
                        $("#reqIp").val(data.reqIp);
                        $("#reqUrl").val(data.reqUrl);
                        $("#reqData").val(data.reqData);
                        $("#agentUser").val(data.agentUser);
                        $("#createTime").val(saicfc.moment.formatYMD(data.createTime));
                        $("#exceptions").val(data.exception);
                    }
                }
            });
        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        logManage.init();
    });
})();

var logManage = new sys.log.logManage();





