/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.event");

(function () {
    cms.event.eventMain = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.eventTable = {};

        /** 初始化调用 function **/
        this.init = function () {
            /**  查询 **/
            $(".btn-search").click(function () {
                obj.eventTable.ajax.reload();
            });
            $(document).bind("keydown", ".filter input", function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.eventTable.ajax.reload();
                }
            });

            /**  重置  **/
            $("#btn-undo").click(function () {
                xqsight.utils.cleanValue(".filter");
            });

            /**  新增 **/
            $("#btn-plus").on("click", obj.plusFun);

            /** 修改 **/
            $("#btn-edit").on("click", obj.editFun);

            /** 删除 **/
            $("#btn-remove").on("click", obj.removeFun);

            obj.loadtAdTableFun();
        };


        /**  新增 function **/
        this.plusFun = function () {
            xqsight.win.show("事件新增", "cms/event/eventManage.html", 700, 400, true);
        }

        /**  修改 function **/
        this.editFun = function () {
            var selRows = obj.eventTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("事件修改", "cms/event/eventManage.html?adId=" + selRows[0].adId, 700, 400, true);
        }

        /**  删除 function **/
        this.removeFun = function () {
            var selRows = obj.eventTable.rows(".info").data();
            if (selRows.length < 1) {
                xqsight.win.alert("请选择修改的数据");
                return;
            }

            xqsight.utils.delete({
                url: ctxData + "/cms/ad/" + selRows[0].adId, callFun: function () {
                    obj.eventTable.ajax.reload();
                }
            })
        }

        /**  加载数据表 function  **/
        this.loadtAdTableFun = function () {
            var record_table = $("#event-table").DataTable({
                "bAutoWidth": false,
                "bFilter": false,// 搜索栏
                "bLengthChange": true,// 每行显示记录数
                "iDisplayLength": 15,// 每页显示行数
                "bSort": false,
                "bInfo": true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide": true,
                "sAjaxSource": ctxData + '/cms/ad/page',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({
                        url: sUrl, data: aoData, callFun: function (rep) {
                            fnCallback(rep.data);
                            //渲染结束重新设置高度
                            parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                        }
                    })
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        {"name": "filter_LIKES_ad_name", "value": $("#adName").val()},
                        {"name": "filter_INI_type", "value": "2,3"}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }
                ],
                "aoColumns": [{
                    data: "adId",
                    sWidth: "2",
                    render: function (value) {
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                }, {
                    data: "adName",
                    sWidth: "100",
                    sClass: "text-center"
                }, {
                    data: "type",
                    sWidth: "60",
                    sClass: "text-center",
                    render: function (value) {
                        return value == "2" ? "小事件" : "大事件";
                    }
                }, {
                    data: "adBeginTime",
                    sWidth: "80",
                    sClass: "text-center"
                }, {
                    data: "adtId",
                    sWidth: "80",
                    sClass: "text-center",
                    render: function () {
                        return "<div class='bolder'> <a class='red' href='javaScript:eventMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:eventMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.eventTable = record_table;

            //单选事件
            $("#event-table tbody").on("click", "tr", function () {
                $.each($("#event-table tbody").find("input[type='checkbox']"), function (index, object) {
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#event-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#event-table tbody").on("dblclick", "tr", function () {
                obj.editFun();
            });
        }


        /** 新增编辑回调函数 **/
        this.editCallBackFun = function (params) {
            //加载数据
            obj.eventTable.ajax.reload();
            if (params.eventId == undefined || params.eventId == "") {
                return;
            }
            //选中之前选中的数据
        }
    };

    /** 初始化数据 **/
    $(document).ready(function () {
        eventMain.init();
    });
})();
var eventMain = new cms.event.eventMain();





