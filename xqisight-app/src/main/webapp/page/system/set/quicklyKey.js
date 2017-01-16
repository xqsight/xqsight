/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.set");

(function(){
    sys.set.quicklyKeySet = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        var option = "";

        /**
         * 初始化调用 function
         */
        this.init = function() {
            $("#btn_fun_save").bind("click",obj.validateFun);
            //加载父级菜单
            obj.loadTreeDataFun();

            $(".selectpicker").change(function(){
                $(this).parent().parent().next().find("input").val($(this).find("option:selected").text());
            });
        };

        /**
         * 组装参数
         */
        this.setParamFun = function(){
            var params = {};
            for(var i =1; i <5 ;i++){
                params.funOrder = $("#funOrder" + i).val();
                params.keyTitle= $("#keyTitle" + i).val();
                params.keyIcon= $("#keyIcon" + i).val();
                params.keyValue= $("#keyValue" + i).val();
            }
            return params;
        }

        /**
         * 验证
         */
        this.validateFun = function(){
            $("#funForm").html5Validate(function() {
                obj.saveQuickStartFun();
            }, {
                validate : function() {
                    return true;
                }
            });
        }

        /**
         * 快捷键保存
         */
        this.saveQuickStartFun = function(){
            var callback = function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/quickkey/save?date=" + new Date().getTime() ,
                        "data": obj.setParamFun(),
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };
            saicfc.win.confirm("确认提交吗？",callback);
        }

        /**
         *  获取树选择
         */
        this.loadTreeDataFun = function(){
            $.ajax({
                "dataType": "jsonp",
                "url": ctxData + "/sys/menu/querymenu?date="+new Date().getTime(),
                "success": function(retData){
                    obj.dataProductFun(retData.data);
                    $("#keyValue1").append(option);
                    $("#keyValue2").append(option);
                    $("#keyValue3").append(option);
                    $("#keyValue4").append(option);

                    $('#keyValue1').selectpicker('refresh');
                    $('#keyValue2').selectpicker('refresh');
                    $('#keyValue3').selectpicker('refresh');
                    $('#keyValue4').selectpicker('refresh');
                    obj.loadQuickKeyFun();
                }
            });
        }

        /**
         * 数据处理
         * @param nodes
         */
        this.dataProductFun = function(nodes){
            $.each(nodes,function(index,node){
                if(node.children.length > 0){
                    option += '<optgroup label="' + node.menuName + '">';
                    obj.dataProductFun(node.children);
                }else if(node.type == "0"){
                    var _pinyin = " ";
                    if(typeof(pinyin) == "function" || typeof(pinyin) == "object")
                        _pinyin = pinyin.getCamelChars(node.menuName);

                    option += "<option data-tokens='" + _pinyin + "' value='" + node.url +"'>" + node.menuName + "</option>";
                }
            });
            option += '</optgroup>';
        }

        /**
         * 加载快捷键
         */
        this.loadQuickKeyFun = function() {
            $.ajax({
                "url": ctxData + "/sys/quickkey/querybyid?date=" + new Date().getTime(),
                "success": function (retData) {
                    if (retData.data == undefined || retData.data == null || retData.data.length == 0)
                        return;
                    $.each(retData.data, function (index, object) {
                        $("#keyTitle" + object.funOrder).val(object.keyTitle);
                        $("#keyIcon" + object.funOrder).val(object.keyIcon);
                        $("#keyValue" + object.funOrder).selectpicker('val', object.keyValue);
                    });
                },
                "dataType": "jsonp",
                "cache": false
            });
        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        quicklyKeySet.init();
    });
})();
var quicklyKeySet = new sys.set.quicklyKeySet();





