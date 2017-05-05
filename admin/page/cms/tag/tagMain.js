/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.tag");

(function () {
    cms.tag.tagMain = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.tagTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function () {
            /**
             * 查询
             */
            $(".btn-search").click(function () {
                obj.tagTable.ajax.reload();
            });
            $(document).bind("keydown", ".filter input", function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.tagTable.ajax.reload();
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

            obj.loadTagTableFun();
        };


        /**
         * 新增 function
         */
        this.plusFun = function () {
            xqsight.win.show("标签新增", "cms/tag/tagManage.html", 700, 400, false);
        }

        /**
         * 修改 function
         */
        this.editFun = function () {
            var selRows = obj.tagTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("标签修改", "cms/tag/tagManage.html?tagId=" + selRows[0].tagId, 700, 400, false);
        }

        /**
         * 删除 function
         */
        this.removeFun = function () {
            var selRows = obj.tagTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }

            xqsight.utils.delete({url:ctxData + "/cms/tag/" + selRows[0].tagId,callFun:function () {
                obj.tagTable.ajax.reload();
            }})
        }

        /**
         * 加载数据表 function
         */
        this.loadTagTableFun = function () {
            var record_table = $("#tag-table").DataTable({
                "bAutoWidth": false,
                "bFilter": false,// 搜索栏
                "bSort": false,
                "bInfo": false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide": true,
                "paging": false,
                "sAjaxSource": ctxData + '/cms/tag/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }})
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        {"name": "filter_LIKES_tag_name", "value": $("#tagName").val()}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }
                ],
                "aoColumns": [{
                    data: "tagId",
                    sWidth: "2",
                    render: function (value) {
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                }, {
                    data: "tagName",
                    sWidth: "100",
                    sClass: "text-center"
                }, {
                    data: "tagType",
                    sWidth: "60",
                    sClass: "text-center",
                    render: function () {
                        return "default";
                    }
                }, {
                    data: "tagId",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function () {
                        return "<div class='bolder'> <a class='red' href='javaScript:tagMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:tagMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.tagTable = record_table;

            //单选事件
            $("#tag-table tbody").on("click", "tr", function () {
                $.each($("#tag-table tbody").find("input[type='checkbox']"), function (index, object) {
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#tag-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#tag-table tbody").on("dblclick", "tr", function () {
                obj.editFun();
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function (params) {
            //加载数据
            obj.tagTable.ajax.reload();
            if (params.tagId == undefined || params.tagId == "") {
                return;
            }
            //选中之前选中的数据
        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        tagMain.init();
    });
})();
var tagMain = new cms.tag.tagMain();





