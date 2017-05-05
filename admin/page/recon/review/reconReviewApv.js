/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.pmpf");

(function(){
    xqsight.pmpf.reconReviewApv = function(){

        var ctxData = xqsight.utils.getReconServerPath();

        // 申明内部对象
        var obj = this;

        this.reviewApvTable = {};

        // 初始化页面
        this.init = function() {
            var n1 = new FormatNumber();
            n1.init({trigger:$('[data-type="amount"]'),decimal:2});

            //时间范围日期选择
            $('.input-daterange').datepicker({autoclose:true});

            $("#btn-query").click(function(){
                obj.reviewApvTable.ajax.reload();
            });
            $("#btn-reset").click(function(){
                xqsight.utils.cleanValue("searchDiv");
            });

            obj.loadChannelInfo();
            //加载表
            obj.loadStatisticsTableFun();
        };

        //加载数据表
        this.loadStatisticsTableFun = function(){
            var record_table = $("#reconReviewApv-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : xqsight.utils.getDataZh_chPath()
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "sHeight" : 600,
                "bSort" : false,// 排序
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/review/queryreconfailuredeal',
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
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "beginDate", "value": $("#beginDate").val()},
                        { "name": "endDate", "value": $("#endDate").val() },
                        { "name": "minAmount", "value": parseFloat($("#minAmount").val()== "" ? "0" :$("#minAmount").val()) * 1000},
                        { "name": "maxAmount", "value":parseFloat($("#maxAmount").val()==""?"0":$("#maxAmount").val()) * 1000 },
                        { "name": "tradeType", "value": $("#tradeType").val() },
                        { "name": "channelCode", "value": $("#channelCode").val() }
                    );
                },
                "aoColumns": [{
                    "data": "trade_time",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);;
                    }
                },{
                    "data": "channel_name",
                    sWidth : "100",
                    sClass : "text-center"
                }, {
                    "data": "trade_type",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "消费" : "退款";
                    }
                }, {
                    "data": "amount",
                    sWidth : "80",
                    sClass : "text-right",
                    render : function(value){
                        value = (value == undefined ? 0.00 : value);
                        return xqsight.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "status",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        if(value == "0"){
                           return "未对帐";
                        }else if(value == "1"){
                            return "对账为对上";
                        }else if(value == "2"){
                            return "已对帐";
                        }else if(value == "3"){
                            return "手工平帐中";
                        }else if(value == "4"){
                            return "手工平帐完成";
                        }
                    }
                }, {
                    "data": "reason",
                    sWidth : "120",
                    sClass : "text-left"
                }, {
                    "data": "reason1",
                    sWidth : "120",
                    sClass : "text-left"
                }, {
                    "data": "result_no",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return '<button class="btn btn-xs btn-danger" onclick="reconReviewApv.submitApv(' + value + ')">处理</button>';
                    }
                }]
            });

            obj.reviewApvTable = record_table;
        };

        //提交复合
        this.submitApv = function(resultNo){
            var reason,apvType;
            var layer = window.top.layer;
            layer.prompt1({title: '输入备注信息',btn: ['确认','驳回','取消'], formType: 2}, function(text,index){
               reason = text;
               apvType = "1";
               layer.close(index);
               submit();
            },function(text,index){
               reason = text;
               apvType = "2";
               layer.close(index);
               submit();
            });

            function submit(){
                var index = xqsight.progress.show();
                $.ajax({
                    type: "POST",
                    dataType : 'jsonp',
                    data : "resultNo=" + resultNo + "&apvType=" + apvType + "&apvStep=1&reason=" + reason,
                    url:  encodeURI(encodeURI(ctxData + "/review/savereconfailuredeal")),
                    success: function(objMsg){
                        xqsight.progress.hide(index);
                        if(objMsg.status == "0"){
                            obj.reviewApvTable.ajax.reload();
                        }
                        xqsight.win.alert(objMsg.msg);
                    }
                });
            }
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
                    xqsight.progress.hide();
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
        reconReviewApv.init();
    });
})();

var reconReviewApv = new xqsight.pmpf.reconReviewApv();





