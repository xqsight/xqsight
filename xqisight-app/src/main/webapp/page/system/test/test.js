/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.test = function(){
        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var editRole = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);
        };


        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#roleForm").html5Validate(function() {
                obj.saveFun();
            }, {
                validate : function() {
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function(){
            var url = $("#action").val() + $("#url").val()
            $.ajax({
                url: url ,
                data: $("#params").val(),
                success: function(retData){
                    $("#resp").val(JSON.stringify(retData))
                },
                dataType: "jsonp",
                cache: false,
                beforeSend : function(){
                },
                complete : function(xhr) {
                },
                error : function(data){
                    alert(JSON.stringify(data));
                }
            });

        };

    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        test.init();
    });
})();

var test = new saicfc.pmpf.test();





