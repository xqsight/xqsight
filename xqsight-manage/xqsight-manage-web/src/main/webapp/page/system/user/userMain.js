
/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.user");

(function(){
    sys.user.userMain = function(){
        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;
        this.orgTree = {};
        this.curSelTree = {};
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.userTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.userTable.ajax.reload();
            });
            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                saicfc.utils.cleanValue(".filter");
            });
            /**
             * 新增
             */
            $("#btn-plus").on("click",obj.plusFun);
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
            obj.loadUserTableFun();
            obj.loadOrgTreeFun();
        };

        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("登录用户新增","system/user/userManage.html?orgId=" + obj.curSelTree.id,$(window).width(),500);
        }

        /**
         修改 function
         */
        this.editFun = function(){
            var selRows = obj.userTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("修改","system/user/userManage.html?id=" + selRows[0].id,$(window).width()-150,$(window).height());
        }


        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.userTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择删除的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/login/delete?date=" + new Date().getTime(),
                        "data": "id=" + selRows[0].id,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.userTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadUserTableFun = function(){
            var record_table = $("#user-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getServerPath("dataTableLocal")
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/sys/login/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    var orgId = 1;
                    if(obj.curSelTree.id != undefined ){
                        orgId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "loginId", "value": $("#loginId").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "id",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "userName",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "loginId",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "loginType",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == "1"){
                            return "编号";
                        }else if(value == "2"){
                            return "邮箱";
                        }else if(value == "3"){
                            return "电话";
                        }
                    }
                },{
                    data : "imgUrl",
                    sWidth : "60",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == undefined || value == "")
                            return "";
                        return "<a href=\"javascript:saicfc.win.imgShow('" + value + "');\">查看</a>";
                    }
                },{
                    data : "locked",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "未锁定" : "锁定";
                    }
                },{
                    data : "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                },{
                    data : "id",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:userMain.resetPwdFun(\"" + value + "\")'>重置密码</a> "
                            + "</div> ";
                    }
                },{
                    data : "id",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:userMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | "
                            + "<a class='red' href='javaScript:userMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a> "
                            + "</div> ";
                    }
                }]
            });

            obj.userTable = record_table;

            //单选事件
            $("#user-table tbody").on("click","tr",function() {
                $.each($("#user-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#user-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#user-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /**
         * 重置密码
         * @param id
         */
        this.resetPwdFun = function(id){
            saicfc.win.confirm("确认重置密码吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/login/resetpwd?date=" + new Date().getTime(),
                        "data": "id=" + id,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                        }
                    });
                }
            });
        }

        /*** 加载 tree **/
        this.loadOrgTreeFun = function () {
            $.ajax({
                url: ctxData + "/sys/org/querytree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    if(retData.status == 0){
                        $.fn.zTree.init($("#orgTree"),{
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
                                    obj.orgTree.selectNode(treeNode);
                                    obj.curSelTree = treeNode;
                                    obj.userTable.ajax.reload();
                                    return false;
                                }
                            }
                        }, retData.data);

                        obj.orgTree = $.fn.zTree.getZTreeObj("orgTree");

                        if(obj.curSelTree.id != undefined ){
                            obj.orgTree.selectNode(obj.curSelTree);
                        }else{
                            var nodes = obj.orgTree.getNodes();
                            if (nodes.length>0) {
                                obj.orgTree.selectNode(nodes[0]);
                                obj.curSelTree = nodes[0];
                            }
                        }

                        obj.orgTree.expandAll(true);

                        obj.userTable.ajax.reload();
                    }
                    //渲染结束重新设置高度
                    parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                }
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.userTable.ajax.reload();
            if(params.userId== undefined || params.userId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        userMain.init();
    });
})();
var userMain = new sys.user.userMain();





