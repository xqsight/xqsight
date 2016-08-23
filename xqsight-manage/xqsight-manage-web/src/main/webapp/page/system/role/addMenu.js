/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.role");

(function(){
    sys.role.addMenu = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        this.menuTree = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            obj.loadMenuTreeFun();

            $("#roleName").change(function(){
                obj.loadAuthMenu( $("#roleName").val());
            });

            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);


        };

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            var params = "&roleId=" + $("#roleName").val();
            var zTree = $.fn.zTree.getZTreeObj("menuTree");
            $.each(zTree.getCheckedNodes(),function(index,node){
                if(node.id != 0)
                    params += "&menuId=" + node.id;
            });
            return params;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#userRoleForm").html5Validate(function() {
                obj.saveFun();
            }, {
                validate : function() {
                    if($("#roleName").val() == ""){
                        $("#roleName").testRemind("请选择用户角色");
                        return false;
                    }
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
                    var url = ctxData + "/sys/auth/savemenurole?date=" + new Date().getTime();
                    $.ajax({
                        "url": url ,
                        "data":  obj.setParamFun(),
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
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

        this.loadAuthMenu = function (roleId) {
            $.ajax({
                url: ctxData + "/sys/menu/querymenubyrole?date="+new Date().getTime(),
                data : { "roleId" : roleId},
                dataType: "jsonp",
                success: function(retData){
                    obj.menuTree.checkAllNodes(false);
                    $.each(retData.data,function(index,object){
                        var nodes = obj.menuTree.getNodesByParam("id", object.id, null);
                        obj.menuTree.checkNode(nodes[0], true, false);
                    });
                }
            });
        }

        /**
         *  获取树选择
         */
        this.loadMenuTreeFun = function(){
            $.ajax({
                url: ctxData + "/sys/menu/queryalltree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    $.fn.zTree.init($("#menuTree"), {
                        check: {
                            enable: true,
                        },
                        data: {
                            simpleData: {
                                enable:true,
                                idKey: "id",
                                pIdKey: "parentId",
                                rootPId: ""
                            }
                        },
                    }, retData.data);

                    obj.menuTree = $.fn.zTree.getZTreeObj("menuTree");
                    obj.loadRoleDataFun();

                    obj.menuTree.expandAll(true);
                }
            });
        }

        /**
         * 加载 角色 function
         */
        this.loadRoleDataFun = function(){
            var roleId = $.getUrlParam("roleId");
            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                url:  ctxData + "/sys/role/queryall",
                success: function(objMsg){
                    if(objMsg.status == "0"){
                        $.each(objMsg.data,function(index,role){
                            var _pinyin = " ";
                            if(typeof(pinyin) == "function" || typeof(pinyin) == "object")
                                _pinyin = pinyin.getCamelChars(role.roleName);

                            if(roleId != undefined && roleId == role.roleId ){
                                $("#roleName").append("<option data-tokens='" + _pinyin + "' value='" + role.roleId +"' selected>" + role.roleName + "</option>");
                            }else{
                                $("#roleName").append("<option data-tokens='" + _pinyin + "' value='" + role.roleId +"'>" + role.roleName + "</option>");
                            }
                        });
                        $('#roleName').selectpicker('refresh');

                        obj.loadAuthMenu(roleId);
                    }
                }
            });
        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        addMenu.init();
    });
})();

var addMenu = new sys.role.addMenu();
