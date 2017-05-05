/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.job");

(function () {
    cms.job.jobMain = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.jobTable = {};
        this.positionTree = {};
        this.curSelTree = {};

        /**
         * 初始化调用 function
         */
        this.init = function () {
            /**
             * 查询
             */
            $(".btn-search").click(function () {
                obj.jobTable.ajax.reload();
            });
            $(document).bind("keydown", ".filter input", function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.jobTable.ajax.reload();
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

            $("#btn-stop").on("click", obj.stopFun);

            obj.loadPositionTreeFun();
            obj.loadJobTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function () {
            if (obj.curSelTree.id == undefined) {
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("招聘新增", "cms/job/jobManage.html?positionId=" + obj.curSelTree.id, 700, 400,true);
        }

        /**
         * 修改 function
         */
        this.editFun = function () {
            var selRows = obj.jobTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("招聘修改", "cms/job/jobManage.html?jobId=" + selRows[0].jobId,700, 400,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function () {
            var selRows = obj.jobTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }

            xqsight.utils.delete({url:ctxData + "/cms/job/" + selRows[0].jobId,callFun:function () {
                obj.jobTable.ajax.reload();
            }})
        }

        this.stopFun = function () {
            var selRows = obj.jobTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择停止的数据");
                return;
            }
            xqsight.utils.delete({url:ctxData + "/cms/job/logic/" + selRows[0].jobId,tipMsg:"确认停止吗？",msg:"停止成功!",callFun:function () {
                obj.jobTable.ajax.reload();
            }})
        }


        /**
         * 加载数据表 function
         */
        this.loadJobTableFun = function () {
            var record_table = $("#job-table").DataTable({
                "bAutoWidth": false,
                "bFilter": false,// 搜索栏
                "bSort": false,
                "bInfo": false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide": true,
                "paging": false,
                "sAjaxSource": ctxData + '/cms/job/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }})
                },
                "fnServerParams": function (aoData) {
                    var positionId = 0;
                    if (obj.curSelTree.id != undefined) {
                        positionId = obj.curSelTree.id;
                    }
                    aoData.push(
                        {"name": "filter_LIKES_job_name", "value": $("#jobName").val()},
                        {"name": "filter_EQI_position_id", "value": positionId}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }
                ],
                "aoColumns": [{
                    data: "jobId",
                    sWidth: "2",
                    render: function (value) {
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                }, {
                    data: "jobName",
                    sWidth: "100",
                    sClass: "text-center"
                }, {
                    data: "jobDepartment",
                    sWidth: "100",
                    sClass: "text-center"
                }, {
                    data: "active",
                    sWidth: "100",
                    sClass: "text-center",
                    render: function (value) {
                        return value == "0" ? "正常 " : "结束";
                    }
                }, {
                    data: "jobEmail",
                    sWidth: "80",
                    sClass: "text-center"
                }, {
                    data: "jobType",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function (value) {
                        return value == "0" ? "全职" : "兼职";
                    }
                }, {
                    data: "jobId",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function () {
                        return "<div class='bolder'> <a class='red' href='javaScript:jobMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:jobMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.jobTable = record_table;

            //单选事件
            $("#job-table tbody").on("click", "tr", function () {
                $.each($("#job-table tbody").find("input[type='checkbox']"), function (index, object) {
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#job-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#job-table tbody").on("dblclick", "tr", function () {
                obj.editFun();
            });
        }

        /*** 加载 tree **/
        this.loadPositionTreeFun = function () {
            xqsight.utils.load({url:ctxData + "/cms/position/tree",callFun:function (rep) {
                $.fn.zTree.init($("#positionTree"), {
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
                            obj.positionTree.selectNode(treeNode);
                            obj.curSelTree = treeNode;
                            obj.jobTable.ajax.reload();
                            return false;
                        }
                    }
                }, rep.data);

                obj.positionTree = $.fn.zTree.getZTreeObj("positionTree");

                if (obj.curSelTree.id != undefined) {
                    obj.positionTree.selectNode(obj.curSelTree);
                } else {
                    var nodes = obj.positionTree.getNodes();
                    if (nodes.length > 0) {
                        obj.positionTree.selectNode(nodes[0]);
                        obj.curSelTree = nodes[0];
                    }
                }
                obj.positionTree.expandAll(true);
                obj.jobTable.ajax.reload();

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
            obj.jobTable.ajax.reload();
            if (params.jobId == undefined || params.jobId == "") {
                return;
            }
            //选中之前选中的数据

        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        jobMain.init();
    });
})();
var jobMain = new cms.job.jobMain();





