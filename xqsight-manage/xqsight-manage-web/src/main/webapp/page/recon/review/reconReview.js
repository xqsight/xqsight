/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.reconReview = function(){

        var ctxData = saicfc.utils.getServerPath("recon");

        // 申明内部对象
        var obj = this;

        this.reviewTable = {};

        // 初始化页面
        this.init = function() {
            var n1 = new FormatNumber();
            n1.init({trigger:$('[data-type="amount"]'),decimal:2});

            //时间范围日期选择
            $('.date-range').datepicker({autoclose:true});

            /**
             * 查询时间
             */
            $("#btn-query").click(function(){
                obj.reviewTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.reviewTable.ajax.reload();
                }
            });
            $(document).on("click",".filter-item>ul>li",function(ev){
                ev.preventDefault();
                $(this).prevAll("li").removeClass("active");
                $(this).nextAll("li").removeClass("active");
                $(this).addClass("active");
                if($(this).find("input").length == 0){
                    obj.reviewTable.ajax.reload();
                }
            });

            /**
             * 重置
             */
            $("#btn-reset").click(function(){
                saicfc.utils.cleanValue("searchDiv");
            });

            obj.loadChannelInfo();
            //加载表
            obj.loadStatisticsTableFun();
        };

        /**
         * 获取ul选中的值
         * @param ulEle
         * @returns {*}
         */
        obj.getUlValueFun = function(ulEle){
            var value = ulEle.find("li.active a").attr("value");
            return value;
        };

        //加载数据表
        this.loadStatisticsTableFun = function(){
            var record_table = $("#reconReview-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getDataZh_chPath()
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
                "sAjaxSource": ctxData + '/review/queryresultdetail',
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
                        { "name": "beginDate", "value": $("#beginDate").val()},
                        { "name": "endDate", "value": $("#endDate").val() },
                        { "name": "minAmount", "value": parseFloat($("#minAmount").val()== "" ? "0" :$("#minAmount").val()) * 1000},
                        { "name": "maxAmount", "value":parseFloat($("#maxAmount").val()==""?"0":$("#maxAmount").val()) * 1000 },
                        { "name": "tradeType", "value": obj.getUlValueFun($("#tradeType")) },
                        { "name": "channelCode", "value": $("#channelCode").val() },
                        { "name": "status", "value": obj.getUlValueFun($("#status"))},
                        { "name": "tradeTime", "value": (obj.getUlValueFun($("#tradeTime")) == undefined ? "other" : obj.getUlValueFun($("#tradeTime")))}
                    );
                },
                "aoColumns": [{
                    "data": "trade_time",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);;
                    }
                },{
                    "data": "channel_name",
                    sWidth : "120",
                    sClass : "text-center"
                }, {
                    "data": "trade_type",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "消费" : "退款";
                    }
                },{
                    "data": "channel_order_no",
                    sWidth : "120",
                    sClass : "text-center"
                }, {
                    "data": "amount",
                    sWidth : "80",
                    sClass : "text-right",
                    render : function(value){
                        value = (value == undefined ? 0.00 : value);
                        return saicfc.common.formatMoney(value/1000);
                    }
                }, {
                    "data": "status",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        if(value == "0"){
                           return "未对帐";
                        }else if(value == "1"){
                            return "对帐未对上";
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
                    "data": "status",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value,type,row){
                       if(value == "1"){
                            return '<button class="btn btn-xs btn-danger" onclick="reconReview.submitApv(' + row.result_no + ')">提交复核</button>';
                        }else {
                            return "";
                        }
                    }
                }]
            });

            obj.reviewTable = record_table;

            $('#reconReview-table tbody').on( 'click', 'tr', function (evt) {
                if ($(this).hasClass('success')) {
                    $(this).removeClass('success');
                } else {
                    record_table.$('tr.success').removeClass('success');
                    $(this).addClass('success');
                }
            });
        };

        //提交复合
        this.submitApv = function(resultNo){
            var layer = window.top.layer;
            layer.prompt({title: '输入备注信息,不能为空', formType: 2,offset: '50px'}, function(text,index){
               layer.close(index);

               var id = saicfc.progress.show();
                $.ajax({
                    type: "POST",
                    dataType : 'jsonp',
                    data : "resultNo=" + resultNo + "&apvType=1&apvStep=0&reason=" + text,
                    url:  encodeURI(encodeURI(ctxData + "/review/savereconfailuredeal")),
                    success: function(objMsg){
                        saicfc.progress.hide(id);
                        if(objMsg.status == "0"){
                            obj.reviewTable.ajax.reload();
                        }
                        saicfc.win.alert(objMsg.msg);
                    }
                });
            });
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
        reconReview.init();
    });
})();

var reconReview = new saicfc.pmpf.reconReview();





