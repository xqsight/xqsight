/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.role");

(function(){
    sys.role.addMenu = function(){

        var ctxData = xqsight.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.menuTree = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            obj.loadMenuTreeFun();
            obj.loadRoleDataFun();

            $("#roleName").change(function(){
                obj.loadAuthMenu();
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
            var params = "&roleId=" + $.getUrlParam("roleId");
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
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function(){
            xqsight.utils.put({url:ctxData + "/sys/auth/rolemenu",data:obj.setParamFun(),callFun:function (rep) {

            }})
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            xqsight.win.close();
        };

        this.loadAuthMenu = function () {
            xqsight.utils.load({url:ctxData + "/sys/auth/authmenu",data:{ "roleId" : $("#roleName").val()},callFun:function(rep){
                obj.menuTree.checkAllNodes(false);
                $.each(rep.data,function(index,object){
                    var nodes = obj.menuTree.getNodesByParam("id", object.id, null);
                    obj.menuTree.checkNode(nodes[0], true, false);
                });
            }});
        };

        /**
         *  获取树选择
         */
        this.loadMenuTreeFun = function(){
            xqsight.utils.load({url: ctxData + "/sys/menu/tree",callFun:function (rep) {
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
                }, rep.data);

                obj.menuTree = $.fn.zTree.getZTreeObj("menuTree");

                obj.loadAuthMenu();

                obj.menuTree.expandAll(true);
            }})
        }

        /**
         * 加载 角色 function
         */
        this.loadRoleDataFun = function(){
            var roleId = $.getUrlParam("roleId");
            xqsight.utils.load({url:ctxData + "/sys/role/",callFun:function (rep) {
                $.each(rep.data,function(index,role){
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
            }})
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
