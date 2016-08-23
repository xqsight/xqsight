/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.reconBankShow = function(){

        var ctxData = saicfc.utils.getReconServerPath();

        // 申明内部对象
        var obj = this;

        this.reconBankTable = {};

        // 初始化页面
        this.init = function() {
            //设置总信息
            obj.setSummaryFun();

            //加载表
            obj.loadReconBankTableFun();
        };

        /**
         * 设置总信息
         */
        this.setSummaryFun = function(){
            var channelName = $.getUrlParam("channelName");
            var tradType = $.getUrlParam("tradeType");
            var tradeTime = $.getUrlParam("tradeTime");
            var filterEqual = $.getUrlParam("filterEqual");
            $("#channelName").html(decodeURI(decodeURI(channelName)));
            $("#tradeTime").html(saicfc.moment.formatYMD(tradeTime));
            $("#tradeType").html((tradType == "0" ? "消费" : "退款"));
            $("#showMethod").html((filterEqual == "0" ? "银行数据" : "银行已收财务公司未收"));
        }

        //加载数据表
        this.loadReconBankTableFun = function(){
            var record_table = $("#reconCwgsShow-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getDataZh_chPath()
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "sHeight" : 600,
                "bSort" : false,// 排序
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/statistics/bankdetail',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    var index = saicfc.progress.loading();
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                            saicfc.progress.removeLoading(index);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "channelCode", "value":  $.getUrlParam('channelCode') },
                        { "name": "tradeTime", "value":  saicfc.moment.formatYMDHms($.getUrlParam("tradeTime")) },
                        { "name": "tradeType", "value":  $.getUrlParam('tradeType') },
                        { "name": "filterEqual", "value":  $.getUrlParam('filterEqual') }
                    );
                },
                "aoColumns": [{
                    "data": "trade_no",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    "data": "channel_order_no",
                    sWidth : "120",
                    sClass : "text-center"
                }, {
                    "data": "amount",
                    sWidth : "120",
                    sClass : "text-right",
                    render : function(value){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "reason",
                    sWidth : "200",
                    sClass : "text-left"
                }]
            });
            obj.reconBankTable = record_table;
        };
    };

    // 初始化数据
    $(document).ready(function() {
        reconBankShow.init();
    });
})();

var reconBankShow = new saicfc.pmpf.reconBankShow();





