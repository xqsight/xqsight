/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.office");

(function(){
    sys.office.officeMain = function(){
        var ctxData = xqsight.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.officeTable = {};
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
                obj.officeTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.officeTable.ajax.reload();
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
            $("#btn-plus").on("click",obj.plusFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.editFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);

            obj.loadOfficeTreeFun();
            obj.loadOfficeTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("机构新增","system/office/officeManage.html?parentId=" + obj.curSelTree.id,$(window).width()-150,500);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.officeTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("机构修改","system/office/officeManage.html?officeId=" + selRows[0].officeId,$(window).width()-150,500);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.officeTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.utils.delete({url:ctxData + "/sys/office/" + selRows[0].officeId,callFun:function () {
                obj.loadOfficeTreeFun();
            } });
        };

        /**
         * 加载数据表 function
         */
        this.loadOfficeTableFun = function(){
            var record_table = $("#office-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/sys/office/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }});
                },
                "fnServerParams": function (aoData) {
                    var parentId = 1;
                    if(obj.curSelTree.id != undefined ){
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "filter_LIKES_office_name", "value": $("#officeName").val() },
                        { "name": "filter_LIKES_office_code", "value": $("#officeCode").val() },
                        { "name": "filter_EQI_parent_id", "value": parentId }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "officeId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "officeName",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    data: "officeCode",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    data: "officeType",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        if(value == "1"){
                            return "公司";
                        }else if(value == "2"){
                            return "部门";
                        }else if(value == "3"){
                            return "小组";
                        }else {
                            return "其他";
                        }

                    }
                },{
                    data: "master",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    data: "phone",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    data: "sort",
                    sWidth : "40",
                    sClass : "text-center"
                },{
                    data: "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);
                    }
                },{
                    data: "officeId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:officeMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:officeMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.officeTable = record_table;

            //单选事件
            $("#office-table tbody").on("click","tr",function() {
                $.each($("#office-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#office-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#office-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /*** 加载 tree **/
        this.loadOfficeTreeFun = function () {
            xqsight.utils.load({url:ctxData + "/sys/office/tree",callFun:function (rep) {
                var treeRoot = [{
                    name : "系统机构",
                    id : 0,
                    children : rep.data
                }];
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
                            obj.officeTable.ajax.reload();
                            return false;
                        }
                    }
                }, treeRoot);

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

                obj.officeTable.ajax.reload();

                //渲染结束重新设置高度
                parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
            }});
        };
        
        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.loadOfficeTreeFun();
            if(params.officeId== undefined || params.officeId =="" ){
                return;
            }
            //选中之前选中的数据

        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        officeMain.init();
    });
})();
var officeMain = new sys.office.officeMain();





