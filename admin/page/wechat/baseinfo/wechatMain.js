/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.pmpf");

(function(){
    xqsight.pmpf.baseInfo = function(){

        var ctxData = xqsight.utils.getWechatServerPath();

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
        this.wechatTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $("#btn-query").click(function(){
                obj.wechatTable.ajax.reload();
            });
            /**
             * 重置
             */
            $("#btn-reset").click(function(){
                xqsight.utils.cleanValue("searchDiv");
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
            obj.loadWechatTableFun();
        };

        /**
         * 新增 function
         */
        this.newFun = function(){
            xqsight.win.show("新增","wechat/baseinfo/wechatManage.html",$(window).width()-150,$(window).height());
        }

        /**
         * 修改 function
         */
        this.updFun = function(){
            var selRows = obj.wechatTable.rows(".success").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("修改","wechat/baseinfo/wechatManage.html?wxId=" + selRows[0].wxId,$(window).width()-150,$(window).height());
        }


        /**
         * 删除 function
         */
        this.delFun = function(){
            var selRows = obj.wechatTable.rows(".success").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    var index = xqsight.progress.show();
                    $.ajax({
                        "url": ctxData + "/wechat/baseinfo/delete?date=" + new Date().getTime(),
                        "data": "id=" + selRows[0].wxId,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.progress.hide(index);
                            xqsight.win.alert(retData.msg)
                            if(retData.status == "0"){
                                obj.wechatTable.ajax.reload();
                            }
                        },
                        "error" : function(retData){
                            xqsight.progress.hide(index);
                            xqsight.win.alert(retData.msg)
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadWechatTableFun = function(){
            var record_table = $("#user-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : xqsight.utils.getDataZh_chPath()
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/wechat/baseinfo/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    var index = xqsight.progress.loading();
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                            xqsight.progress.removeLoading(index);
                        },
                        "error" : function(){
                            xqsight.progress.removeLoading(index);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "wxName", "value": $("#wxName").val() },
                        { "name": "wxCode", "value": $("#wxCode").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    "data": "wxName",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "wxCode",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "wxToken",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "wxOriginalId",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "wxType",
                    sWidth : "120",
                    sClass : "text-center",
                    render : function(value){
                        if(value == 0){
                            return "订阅号";
                        }else if(value == 1){
                            return "服务号";
                        }else if(value == 2){
                            return "企业号";
                        }else{
                            return value;
                        }
                    }
                },{
                    "data": "wxAppid",
                    sWidth : "200",
                    sClass : "text-center"
                },{
                    "data": "wxAppsecret",
                    sWidth : "200",
                    sClass : "text-center"
                },{
                    "data": "wxDescript",
                    sWidth : "200",
                    sClass : "text-center"
                }]
            });

            obj.wechatTable = record_table;

            //单选事件
            $("#wechat-table tbody").on("click","tr input[type='checkbox']",function() {
                var check = $(this).get(0).checked ;
                if(check)
                    $(this).parents("tr").addClass("success");
                else
                    $(this).parents("tr").removeClass("success");
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.wechatTable.ajax.reload();
            if(params.wxId== undefined || params.wxId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        baseInfo.init();
    });
})();
var baseInfo = new xqsight.pmpf.baseInfo();





