/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.log");

(function(){
    sys.log.logMain = function(){
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
        this.logTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            laydate({
                elem: '#startDate'
            });
            laydate({
                elem: '#endDate'
            });
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.logTable.ajax.reload();
            });
            
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.logTable.ajax.reload();
                }
            });
            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                xqsight.utils.cleanValue(".filter");
            });


            /**
             * 查看
             */
            $("#btn-view").on("click",obj.viewFun);


            /**
             * 加载列表
             */
            obj.loadRoleTableFun();
        };

        /**
         * 修改 function
         */
        this.viewFun = function(){
            var selRows = obj.logTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("日志查看","system/log/logManage.html?logId=" + selRows[0].logId);
        }

        /**
         * 加载数据表 function
         */
        this.loadRoleTableFun = function(){
            var record_table = $("#data-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : xqsight.utils.getServerPath("dataTableLocal")
                },
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/sys/log/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "startDate", "value": $("#startDate").val() },
                        { "name": "endDate", "value": $("#endDate").val() },
                        { "name": "createOprId", "value": $("#createOprId").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }

                ],
                "aoColumns": [{
                    data : "logId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "logTitle",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "reqMethod",
                    sWidth : "60",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "reqIp",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "reqUrl",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "agentUser",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "createTime",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);
                    }
                },{
                    data: "createOprId",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "logId",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                        + "<a class='red' href='javaScript:logMain.viewFun()'><i class='ace-icon fa fa-search'></i></a> "
                        + "</div> ";
                    }
                }]
            });

            obj.logTable = record_table;

            //单选事件
            $("#data-table tbody").on("click","tr",function() {
                $.each($("#data-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#data-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#data-table tbody").on("dblclick","tr",function() {
                obj.viewFun();
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.logTable.ajax.reload();
            if(params.logId== undefined || params.logId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        logMain.init();
    });
})();
var logMain = new sys.log.logMain();





