/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.department");

(function(){
    sys.department.departmentMain = function(){
        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        this.departmentTable = {};
        this.departmentTree = {};
        this.curSelTree={};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.departmentTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.departmentTable.ajax.reload();
                }
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

            obj.loaddepartmentTreeFun();
            obj.loaddepartmentTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("部门新增","system/department/departmentManage.html?parentId=" + obj.curSelTree.id,$(window).width()-150,500);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.departmentTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("部门修改","system/department/departmentManage.html?departmentId=" + selRows[0].departmentId,$(window).width()-150,500);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.departmentTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/department/delete?date=" + new Date().getTime(),
                        "data": {departmentId : selRows[0].departmentId },
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.loaddepartmentTreeFun();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loaddepartmentTableFun = function(){
            var record_table = $("#department-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/sys/department/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                        }
                    });
                },
                "fnServerParams": function (aoData) {
                    var parentId = 1;
                    if(obj.curSelTree.id != undefined ){
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "departmentName", "value": $("#departmentName").val() },
                        { "name": "departmentCode", "value": $("#departmentCode").val() },
                        { "name": "customCode", "value": $("#customCode").val() },
                        { "name": "parentId", "value": parentId }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "departmentId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    "data": "departmentName",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "departmentCode",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "customCode",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                },{
                    "data": "departmentId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:departmentMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:departmentMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.departmentTable = record_table;

            //单选事件
            $("#department-table tbody").on("click","tr",function() {
                $.each($("#department-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#department-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#department-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /*** 加载 tree **/
        this.loaddepartmentTreeFun = function () {
            $.ajax({
                url: ctxData + "/sys/department/querytree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    if(retData.status == 0){
                        $.fn.zTree.init($("#departmentTree"),{
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
                                    obj.departmentTree.selectNode(treeNode);
                                    obj.curSelTree = treeNode;
                                    obj.departmentTable.ajax.reload();
                                    return false;
                                }
                            }
                        }, retData.data);

                        obj.departmentTree = $.fn.zTree.getZTreeObj("departmentTree");

                        if(obj.curSelTree.id != undefined ){
                            obj.departmentTree.selectNode(obj.curSelTree);
                        }else{
                            var nodes = obj.departmentTree.getNodes();
                            if (nodes.length>0) {
                                obj.departmentTree.selectNode(nodes[0]);
                                obj.curSelTree = nodes[0];
                            }
                        }

                        obj.departmentTree.expandAll(true);

                        obj.departmentTable.ajax.reload();
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
            obj.loaddepartmentTreeFun();
            if(params.departmentId== undefined || params.departmentId =="" ){
                return;
            }
            //选中之前选中的数据

        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        departmentMain.init();
    });
})();
var departmentMain = new sys.department.departmentMain();





