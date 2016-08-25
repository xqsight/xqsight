/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.reconStatistics = function(){

        var ctxData = saicfc.utils.getReconServerPath();

        // 申明内部对象
        var obj = this;

        this.statisticsTable = {};

        // 初始化页面
        this.init = function() {
            //时间范围日期选择
            $('.input-daterange').datepicker({autoclose:true});

            $("#btn-query").click(function(){
                obj.statisticsTable.ajax.reload();
            });
            $("#btn-reset").click(function(){
                saicfc.utils.cleanValue("searchDiv");
            });

            obj.loadChannelInfo();

            //加载表
            obj.loadStatisticsTableFun();
        };

        //加载数据表
        this.loadStatisticsTableFun = function(){
            var record_table = $("#statistics-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getDataZh_chPath()
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "iDisplayStart" : 1,
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/statistics/result',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    var index = saicfc.progress.loading();
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success":  function(data){
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
                        { "name": "beginDate", "value": $("#beginDate").val()},
                        { "name": "endDate", "value": $("#endDate").val() },
                        { "name": "tradeType", "value": $("#tradeType").val() },
                        { "name": "channelCode", "value": $("#channelCode").val() },
                        { "name": "filterEqual", "value": $("#filterEqual").prop("checked")? "1" : "0" }
                    );
                },
                "aoColumns": [{
                    "data": "tradeTime",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                },{
                    "data": "channelName",
                    sWidth : "120",
                    sClass : "text-center"
                }, {
                    "data": "tradeType",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "消费" : "退款";
                    }
                }, {
                    "data": "cwgsTotalCount",
                    sWidth : "100",
                    sClass : "text-right cwgsall hand"
                }, {
                    "data": "cwgsTotalAmount",
                    sWidth : "100",
                    sClass : "text-right",
                    render : function(value){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "bankTotalCount",
                    sWidth : "100",
                    sClass : "text-right bankall hand"
                }, {
                    "data": "bankTotalAmount",
                    sWidth : "100",
                    sClass : "text-right",
                    bSortable : false,
                    render : function(value,type,row){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "cwgsExist",
                    sWidth : "100",
                    sClass : "text-right cwgs hand"
                }, {
                    "data": "cwgsAmount",
                    sWidth : "100",
                    sClass : "text-right",
                    bSortable : false,
                    render : function(value,type,row){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "bankExist",
                    sWidth : "100",
                    sClass : "text-right bank hand"
                }, {
                    "data": "bankAmount",
                    sWidth : "100",
                    sClass : "text-right",
                    bSortable : false,
                    render : function(value,type,row){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }]
            });

            //行单机选中
            $('#statistics-table tbody').on( 'click', 'tr', function (evt) {
                if ( $(this).hasClass('success') ) {
                    $(this).removeClass('success');
                } else {
                    record_table.$('tr.success').removeClass('success');
                    $(this).addClass('success');
                }

                var data = obj.statisticsTable.row( this ).data();
                if($(evt.target).hasClass("cwgsall")){
                    if(data.cwgsTotalCount > 0){
                        data.type = "cwgsall";
                        obj.forwardFun(data);
                    }
                }else if($(evt.target).hasClass("bankall")){
                    if(data.bankTotalCount > 0){
                        data.type = "bankall";
                        obj.forwardFun(data);
                    }
                }else if($(evt.target).hasClass("cwgs")){
                    if(data.cwgsExist > 0){
                        data.type = "cwgs";
                        obj.forwardFun(data);
                    }
                }else if($(evt.target).hasClass("bank")){
                    if(data.bankExist > 0){
                        data.type = "bank";
                        obj.forwardFun(data);
                    }
                }
                return;
            } );

            this.statisticsTable = record_table;
        };

        /**
         * 跳转页面
         *
         * type = cwgsall 财务公司数据 ,cwgs 财务公司已收银行未收 ，bankall 银行数据 bank 银行已收财务公司未收
         */
        this.forwardFun = function(rowData){
            var url ="",id="",title="";
            if(rowData.type=="cwgsall"){
                url = "recon/statistics/reconCwgsShow.html?channelCode=" + rowData.channelCode
                + "&channelName=" + rowData.channelName
                + "&tradeType=" + rowData.tradeType
                + "&tradeTime=" + rowData.tradeTime
                + "&filterEqual=0"
                id = rowData.channelCode + rowData.tradeType + rowData.type + rowData.tradeTime;
                title = "财务公司数据";
            }else if(rowData.type=="cwgs"){
                url = "recon/statistics/reconCwgsShow.html?channelCode=" + rowData.channelCode
                    + "&channelName=" + rowData.channelName
                    + "&tradeType=" + rowData.tradeType
                    + "&tradeTime=" + rowData.tradeTime
                    + "&filterEqual=1"
                id = rowData.channelCode + rowData.tradeType + rowData.type + rowData.tradeTime;
                title = "财务公司已收银行未收";
            }else if(rowData.type=="bankall"){
                url = "recon/statistics/reconBankShow.html?channelCode=" + rowData.channelCode
                    + "&channelName=" + rowData.channelName
                    + "&tradeType=" + rowData.tradeType
                    + "&tradeTime=" + rowData.tradeTime
                    + "&filterEqual=0"
                id = rowData.channelCode + rowData.tradeType + rowData.type + rowData.tradeTime;
                title = "银行数据";
            }else if(rowData.type=="bank"){
                url = "recon/statistics/reconBankShow.html?channelCode=" + rowData.channelCode
                    + "&channelName=" + rowData.channelName
                    + "&tradeType=" + rowData.tradeType
                    + "&tradeTime=" + rowData.tradeTime
                    + "&filterEqual=1"
                id = rowData.channelCode + rowData.tradeType + rowData.type + rowData.tradeTime;
                title = "银行已收财务公司未收";
            }
            parent.index.addTabPageFun(id,title,encodeURI(encodeURI(url)));
        }


        /**
         *  加载渠道信息
         */
        this.loadChannelInfo =function(){
            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                url:  encodeURI(encodeURI(ctxData + "/statistics/channelinfo")),
                success: function(objMsg){
                    if(objMsg.status == "0"){
                        $.each(objMsg.data,function(index,obj){
                            var _pinyin = " ";
                            if(typeof(pinyin) == "function" || typeof(pinyin) == "object")
                                _pinyin = pinyin.getCamelChars(obj.CHANNEL_NAME);

                            $("#channelCode").append("<option data-tokens='" + _pinyin + "' value='" + obj.CHANNEL_CODE +"'>" + obj.CHANNEL_NAME + "</option>");
                        });
                        $('#channelCode').selectpicker('refresh');
                    }
                }
            });
        }
    };

    // 初始化数据
    $(document).ready(function() {
        reconStatistics.init();
    });
})();

var reconStatistics = new saicfc.pmpf.reconStatistics();





