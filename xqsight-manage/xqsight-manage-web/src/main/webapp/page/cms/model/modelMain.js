/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("cms.model");

(function(){
    cms.model.modelMain = function(){
        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        this.modelTable = {};
        this.modelTree = {};
        this.curSelTree={};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.modelTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.modelTable.ajax.reload();
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

            obj.loadModelTreeFun();
            obj.loadModelTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("菜单新增","cms/model/modelManage.html?parentId=" + obj.curSelTree.id,$(window).width()-150,500);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.modelTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("菜单修改","cms/model/modelManage.html?modelId=" + selRows[0].modelId,$(window).width()-150,500);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.modelTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/cms/model/delete?date=" + new Date().getTime(),
                        "data": {modelId : selRows[0].modelId },
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.loadModelTreeFun();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadModelTableFun = function(){
            var record_table = $("#model-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/cms/model/query',
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
                    var parentId = 0;
                    if(obj.curSelTree.id != undefined ){
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "modelName", "value": $("#modelName").val() },
                        { "name": "modelCode", "value": $("#modelCode").val() },
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
                    "data": "modelTitle",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "modelCode",
                    sWidth : "60",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "modelThumbnails",
                    sWidth : "40",
                    sClass : "text-center",
                    render : function (value) {
                        return "<span class='model-icon fa " + (value == "" ? "fa-caret-right" : value) + "'></span>";
                    }
                },{
                    "data": "modelSort",
                    sWidth : "40",
                    sClass : "text-center"
                },{
                    "data": "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                },{
                    "data": "modelId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:modelMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:modelMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.modelTable = record_table;

            //单选事件
            $("#model-table tbody").on("click","tr",function() {
                $("#model-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });
        }

        /*** 加载 tree **/
        this.loadModelTreeFun = function () {
            $.ajax({
                url: ctxData + "/cms/model/querytree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    if(retData.status == 0){
                        $.fn.zTree.init($("#modelTree"),{
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
                                    obj.modelTree.selectNode(treeNode);
                                    obj.curSelTree = treeNode;
                                    obj.modelTable.ajax.reload();
                                    return false;
                                }
                            }
                        }, retData.data);

                        obj.modelTree = $.fn.zTree.getZTreeObj("modelTree");

                        if(obj.curSelTree.id != undefined ){
                            obj.modelTree.selectNode(obj.curSelTree);
                        }else{
                            var nodes = obj.modelTree.getNodes();
                            if (nodes.length>0) {
                                obj.modelTree.selectNode(nodes[0]);
                                obj.curSelTree = nodes[0];
                            }
                        }

                        obj.modelTree.expandAll(true);

                        obj.modelTable.ajax.reload();
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
            obj.loadModelTreeFun();
            if(params.modelId== undefined || params.modelId =="" ){
                return;
            }
            //选中之前选中的数据

        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        modelMain.init();
    });
})();
var modelMain = new cms.model.modelMain();





