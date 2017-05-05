/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.model");

(function () {
    cms.model.modelMain = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.modelTable = {};
        this.modelTree = {};
        this.curSelTree = {};

        /**
         * 初始化调用 function
         */
        this.init = function () {
            /**
             * 查询
             */
            $(".btn-search").click(function () {
                obj.modelTable.ajax.reload();
            });
            $(document).bind("keydown", ".filter input", function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.modelTable.ajax.reload();
                }
            });

            /**
             * 重置
             */
            $("#btn-undo").click(function () {
                xqsight.utils.cleanValue(".filter");
            });

            /**
             * 新增
             */
            $("#btn-plus").on("click", obj.plusFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click", obj.editFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click", obj.removeFun);

            obj.loadModelTreeFun();
            obj.loadModelTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function () {
            if (obj.curSelTree.id == undefined) {
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("模块新增", "cms/model/modelManage.html?parentId=" + obj.curSelTree.id, 700, 400, false);
        }

        /**
         * 修改 function
         */
        this.editFun = function () {
            var selRows = obj.modelTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("模块修改", "cms/model/modelManage.html?modelId=" + selRows[0].modelId, 700, 400, false);
        }

        /**
         * 删除 function
         */
        this.removeFun = function () {
            var selRows = obj.modelTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }

            xqsight.utils.delete({url:ctxData + "/cms/model/" + selRows[0].modelId,callFun:function (rep) {
                obj.loadModelTreeFun();
            } });
        }

        /**
         * 加载数据表 function
         */
        this.loadModelTableFun = function () {
            var record_table = $("#model-table").DataTable({
                "bAutoWidth": false,
                "bFilter": false,// 搜索栏
                "bSort": false,
                "bInfo": false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide": true,
                "paging": false,
                "sAjaxSource": ctxData + '/cms/model/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }})
                },
                "fnServerParams": function (aoData) {
                    var parentId = 0;
                    if (obj.curSelTree.id != undefined) {
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        {"name": "filter_LIKES_model_name", "value": $("#modelName").val()},
                        {"name": "filter_LIKES_model_code", "value": $("#modelCode").val()},
                        {"name": "filter_EQI_parent_id", "value": parentId}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }
                ],
                "aoColumns": [{
                    data: "modelId",
                    sWidth: "2",
                    render: function (value) {
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                }, {
                    data: "modelName",
                    sWidth: "100",
                    sClass: "text-center"
                }, {
                    data: "modelCode",
                    sWidth: "60",
                    sClass: "text-center"
                }, {
                    data: "createTime",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function (value) {
                        return xqsight.moment.formatYMD(value);
                    }
                }, {
                    data: "modelId",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function () {
                        return "<div class='bolder'> <a class='red' href='javaScript:modelMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:modelMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.modelTable = record_table;

            //单选事件
            $("#model-table tbody").on("click", "tr", function () {
                $.each($("#model-table tbody").find("input[type='checkbox']"), function (index, object) {
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#model-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#model-table tbody").on("dblclick", "tr", function () {
                obj.editFun();
            });
        }

        /*** 加载 model tree **/
        this.loadModelTreeFun = function () {
            xqsight.utils.load({url:ctxData + "/cms/model/tree",callFun:function (rep) {
                var treeRoot = [{
                    name: "系统模块",
                    id: 0,
                    children: rep.data
                }];

                $.fn.zTree.init($("#modelTree"), {
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
                }, treeRoot);

                obj.modelTree = $.fn.zTree.getZTreeObj("modelTree");

                if (obj.curSelTree.id != undefined) {
                    obj.modelTree.selectNode(obj.curSelTree);
                } else {
                    var nodes = obj.modelTree.getNodes();
                    if (nodes.length > 0) {
                        obj.modelTree.selectNode(nodes[0]);
                        obj.curSelTree = nodes[0];
                    }
                }

                obj.modelTree.expandAll(true);

                obj.modelTable.ajax.reload();

                //渲染结束重新设置高度
                parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
            }})
        }


        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function (params) {
            //加载数据
            obj.loadModelTreeFun();
            if (params.modelId == undefined || params.modelId == "") {
                return;
            }
            //选中之前选中的数据

        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        modelMain.init();
    });
})();
var modelMain = new cms.model.modelMain();





