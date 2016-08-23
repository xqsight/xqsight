/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.menu");

(function(){
    sys.menu.menuManage = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        var editMenu = {};

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        /**
         * 初始化调用 function
         */
        this.init = function() {
            $("#btn_save").on("click",obj.validateFun);
            $("#btn_cancel").on("click",obj.cancelFun);

            obj.formSetValue();
        };

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            editMenu.menuName = $("#menuName").val();
            editMenu.icon = $("#icon").val();
            editMenu.type = $("#type").val();
            editMenu.url = $("#url").val();
            editMenu.sort = $("#sort").val();
            editMenu.permission = $("#permission").val();
            editMenu.remark = $("#remark").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#menuForm").html5Validate(function() {
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
                    if($.getUrlParam("menuId")== undefined || $.getUrlParam("menuId") =="" ){
                        url = ctxData + "/sys/menu/save?date=" + new Date().getTime();
                    }else{
                        url = ctxData + "/sys/menu/update?date=" + new Date().getTime();
                    }
                    $.ajax({
                        "dataType": "jsonp",
                        "url": url,
                        "data": editMenu,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                var iframeContent = saicfc.tab.getIframeContent();
                                iframeContent.menuMain.editCallBackFun({"menuId" : $.getUrlParam("id")});
                                saicfc.win.close();
                            }
                        }
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
            var menuId = $.getUrlParam("menuId");
            if(menuId == undefined || menuId =="" ){
                editMenu.parentId = $.getUrlParam("parentId");
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/sys/menu/querybyid?menuId=" + menuId + "&date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.status == "0"){
                        var data = retData.data;
                        editMenu.menuId = data.menuId;
                        editMenu.parentId = data.parentId;
                        
                        $("#menuName").val(data.menuName);
                        $("#type").selectpicker('val',data.type);
                        $("#icon").val(data.icon);
                        $("#url").val(data.url);
                        $("#sort").val(data.sort);
                        $("#permission").val(data.permission);
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
        menuManage.init();
    });
})();

var menuManage = new sys.menu.menuManage();





