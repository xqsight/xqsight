/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("cms.report");

(function(){
    cms.report.reportMain = function(){
        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.appTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.appTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.appTable.ajax.reload();
                }
            });
            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                saicfc.utils.cleanValue(".filter");
            });


            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.viewFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);

            /**
             * 加载列表
             */
            obj.loadAppTableFun();
        };
        

        /**
         * 修改 function
         */
        this.viewFun = function(){
            var selRows = obj.appTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择查看的数据");
                return;
            }
            saicfc.win.show("应用修改","cms/report/reportManage.html?reportId=" + selRows[0].reportId,600,300,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.appTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择删除的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/cms/article/report/delete?date=" + new Date().getTime(),
                        "data": encodeURI(encodeURI("reportId=" + selRows[0].reportId )),
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status)
                            if(retData.status == "0"){
                                obj.appTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadAppTableFun = function(){
            var record_table = $("#data-table").DataTable({
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
                "sAjaxSource": ctxData + '/cms/article/report/query',
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
                    aoData.push(
                        { "name": "articleTitle", "value": $("#articleTitle").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "reportId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    "data": "articleTitle",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "reportType",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "dealStatus",
                    sWidth : "160",
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
                    "data": "reportId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:reportMain.viewFun()'><i class='ace-icon fa fa-eye'></i></a> | " +
                            "<a class='red' href='javaScript:reportMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.appTable = record_table;

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
                obj.editFun();
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.appTable.ajax.reload();
            if(params.appId== undefined || params.appId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        reportMain.init();
    });
})();
var reportMain = new cms.report.reportMain();
