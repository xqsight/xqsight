/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.appMain = function(){
        var ctxData = xqsight.utils.getServerPath("cms");

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
            $("#btn-query").click(function(){
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
            $("#btn-reset").click(function(){
                xqsight.utils.cleanValue(".filter");
            });

            /**
             * 新增
             */
            $("#btn_new").on("click",obj.newFun);

            /**
             * 修改
             */
            $("#btn_upd").on("click",obj.updFun);

            /**
             * 删除
             */
            $("#btn_del").on("click",obj.delFun);

            /**
             * 加载列表
             */
            obj.loadAppTableFun();
        };

        /**
         * 新增 function
         */
        this.newFun = function(){
            xqsight.win.show("应用新增","cms/app/appManage.html",600,300,true);
        }

        /**
         * 修改 function
         */
        this.updFun = function(){
            var selRows = obj.appTable.rows(".success").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("应用修改","cms/app/appManage.html?appId=" + selRows[0].appId,600,300,true);
        }

        /**
         * 删除 function
         */
        this.delFun = function(){
            var selRows = obj.appTable.rows(".success").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/cms/app/delete?date=" + new Date().getTime(),
                        "data": encodeURI(encodeURI("appId=" + selRows[0].appId )),
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg)
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
            var record_table = $("#app-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : xqsight.utils.getServerPath("dataTableLocal")
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/cms/app/query',
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
                        { "name": "appName", "value": $("#appName").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    "data": "appName",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "appDomain",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "appLogo",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "appKeyword",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "appCopyright",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "appDescription",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "createTime",
                    sWidth : "120",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMDHms(value);
                    }
                },{
                    "data": "remark",
                    sWidth : "200",
                    sClass : "text-left"
                }]
            });

            obj.appTable = record_table;

            //单选事件
            $("#app-table tbody").on("click","tr",function() {
                $("#app-table>tbody>tr").removeClass("success");
                $(this).addClass("success");
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
        appMain.init();
    });
})();
var appMain = new xqsight.cms.appMain();





