/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.role");

(function(){
    sys.role.roleMain = function(){
        var ctxData = xqsight.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.roleTable = {};
        this.officeTree = {};
        this.curSelTree={};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.roleTable.ajax.reload();
            });
            
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.roleTable.ajax.reload();
                }
            });
            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                xqsight.utils.cleanValue(".filter");
            });

            /**
             * 新增
             */
            $("#btn-plus").on("click",obj.newFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.editFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);

            /**
             * 加载列表
             */
            obj.loadRolereeFun();
            obj.loadRoleTableFun();
        };

        /**
         * 新增 function
         */
        this.newFun = function(){
            if(obj.curSelTree.id == undefined ){
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("角色新增","system/role/roleManage.html?officeId=" + obj.curSelTree.id,600,400,false);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.roleTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("角色修改","system/role/roleManage.html?roleId=" + selRows[0].roleId,600,400,false);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.roleTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.utils.delete({url:ctxData + "/sys/role/" + selRows[0].roleId,callFun:function (rep) {
                obj.roleTable.ajax.reload();
            } });
        };

        /**
         * 加载数据表 function
         */
        this.loadRoleTableFun = function(){
            var record_table = $("#role-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/sys/role/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }});
                },
                "fnServerParams": function (aoData) {
                    var officeId = 0;
                    if(obj.curSelTree.id != undefined ){
                        officeId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "filter_LIKES_role_name", "value": $("#roleName").val() },
                        { "name": "filter_EQI_office_id", "value": officeId }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "roleId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "roleName",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "roleEnname",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "sysFlag",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        return value == "0" ? "是" : "否";
                    }
                },{
                    data: "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);
                    }
                },{
                    data: "remark",
                    sWidth : "120",
                    sClass : "text-left"
                },{
                    data: "roleId",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                        + "<a class='red' href='javaScript:roleMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | "
                        + "<a class='red' href='javaScript:roleMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a> "
                        + "</div> ";
                    }
                },{
                    data: "roleId",
                    sWidth : "120",
                    sClass : "text-center",
                    render : function(value){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:roleMain.addUserFun(\"" + value + "\")'>添加用户</a> | "
                            + "<a class='red' href='javaScript:roleMain.addmenuFun(\"" + value + "\")'>添加权限</a> "
                            + "</div> ";
                    }
                }]
            });

            obj.roleTable = record_table;

            //单选事件
            $("#role-table tbody").on("click","tr",function() {
                $.each($("#role-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#role-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#role-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        this.addUserFun = function(roleId){
            var selRows = obj.roleTable.rows(".info").data();
            xqsight.win.show("添加用户","system/role/addUser.html?roleId=" + selRows[0].roleId,$(window).width()-150,$(window).height());
        }


        this.addmenuFun = function(roleId){
            var selRows = obj.roleTable.rows(".info").data();
            xqsight.win.show("添加权限","system/role/addMenu.html?roleId=" + selRows[0].roleId,400,$(window).height(),false);
        }


        /*** 加载 tree **/
        this.loadRolereeFun = function () {
            xqsight.utils.load({url:ctxData + "/sys/office/tree",callFun:function (rep) {
                $.fn.zTree.init($("#officeTree"),{
                    check: {
                        enable: false,
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onClick: function onClick(e, treeId, treeNode) {
                            obj.officeTree.selectNode(treeNode);
                            obj.curSelTree = treeNode;
                            obj.roleTable.ajax.reload();
                            return false;
                        }
                    }
                }, rep.data);

                obj.officeTree = $.fn.zTree.getZTreeObj("officeTree");

                if(obj.curSelTree.id != undefined ){
                    obj.officeTree.selectNode(obj.curSelTree);
                }else{
                    var nodes = obj.officeTree.getNodes();
                    if (nodes.length>0) {
                        obj.officeTree.selectNode(nodes[0]);
                        obj.curSelTree = nodes[0];
                    }
                }

                obj.officeTree.expandAll(true);

                obj.roleTable.ajax.reload();

                //渲染结束重新设置高度
                parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
            }})
        };


        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.roleTable.ajax.reload();
            if(params.roleId== undefined || params.roleId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        roleMain.init();
    });
})();
var roleMain = new sys.role.roleMain();





