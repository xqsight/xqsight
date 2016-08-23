/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.reconReview = function(){

        var ctxData = saicfc.utils.getServerPath("recon");

        // 申明内部对象
        var obj = this;


        // 初始化页面
        this.init = function() {

            //时间范围日期选择
            //$('.input-daterange').datepicker({autoclose:true});

            $("#btn-query").click(function(){
                obj.orderSyncFun();

            });
            $("#btn-reset").click(function(){
                saicfc.utils.cleanValue("searchDiv");
            });
        };


        /**
         *  加载渠道信息
         */
        this.orderSyncFun =function(){
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            if(startTime == "" || endTime == ""){
                saicfc.win.alert("开始日期和结束日期布不能为空")
                return;
            }
            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                url:  encodeURI(encodeURI(ctxData + "/order/sync")),
                data : "startTime=" + startTime +"&endTime=" + endTime,
                success: function(objMsg){
                    saicfc.win.alert(objMsg.msg);
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





