/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.chronic");

(function(){
    xqsight.chronic.beautyMain = function(){
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
        this.beautyTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.beautyTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.beautyTable.ajax.reload();
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
            $("#btn-plus").on("click",obj.newFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.editFun);

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
         * 新增 function
         */
        this.newFun = function(){
            xqsight.win.show("美容院新增","chronic/beauty/beautyManage.html",600,300,true);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.beautyTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("美容院修改","chronic/beauty/beautyManage.html?beautyId=" + selRows[0].beautyId,600,300,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.beautyTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/beauty/parlor/delete?date=" + new Date().getTime(),
                        "data": encodeURI(encodeURI("beautyId=" + selRows[0].beautyId )),
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.beautyTable.ajax.reload();
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
            var record_table = $("#beauty-table").DataTable({
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
                "sAjaxSource": ctxData + '/beauty/parlor/query',
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
                        { "name": "beautyName", "value": $("#beautyName").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "beautyId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    "data": "beautyName",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "beautyAddress",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "beautyPhone",
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
                    "data": "beautyId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:beautyMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | "
                            + "<a class='red' href='javaScript:beautyMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a> "
                            + "</div> ";
                    }
                }]
            });

            obj.beautyTable = record_table;

            //单选事件
            $("#beauty-table tbody").on("click","tr",function() {
                $.each($("#beauty-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#beauty-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#beauty-table tbody").on("dblclick","tr",function() {
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
            obj.beautyTable.ajax.reload();
            if(params.beautyId== undefined || params.beautyId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        beautyMain.init();
    });
})();
var beautyMain = new xqsight.chronic.beautyMain();





