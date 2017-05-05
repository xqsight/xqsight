/**
 * Created by DictDetail on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.dict");

(function(){
    sys.dict.dictMain = function(){
        var ctxData = xqsight.utils.getServerPath("system");

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
        this.dictDetailTable = {};

        this.dictDatas = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $("#btn-search").click(function(){
                obj.loadDictFun();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.loadDictFun();
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
            $("#btn-plus").on("click",obj.plusFun);
            $("#btn-detail-plus").on("click",obj.plusDetailFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.editFun);
            $("#btn-detail-edit").on("click",obj.editDetailFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);
            $("#btn-detail-remove").on("click",obj.removeDetailFun);

            /** 同步 **/
            $('#btn-retweet').on("click",obj.retweetFun);

            /**
             * 加载列表
             */
            obj.loadDictFun();
            obj.loadDictDetailTableFun();

            $("#dictList").on("click","a",function(e){
                if($(e.currentTarget).hasClass("showDict")){
                    $("#dictList>a").removeClass("active");
                    $(this).addClass("active");
                    obj.dictDetailTable.ajax.reload();
                }
                return;
            })

            $("#dict-search-input").on("keyup",obj.searchFun);

        };

        /** 模糊查询 **/
        this.searchFun = function(){
            var oldValue = "";
            var searchValue = $("#dict-search-input").val().replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
            if($.trim(searchValue) == "" || oldValue == searchValue){
                obj.renderDictFun(obj.dictDatas);
                oldValue = searchValue;
                return;
            }
            oldValue = searchValue;

           var searchText = function(search_string,regex){
               var part, parts, _i, _len;
               if (regex.test(search_string)) {
                   return true;
               } else if (this.enable_split_word_search && (search_string.indexOf(" ") >= 0 || search_string.indexOf("[") === 0)) {
                   parts = search_string.replace(/\[|\]/g, "").split(" ");
                   if (parts.length) {
                       for (_i = 0, _len = parts.length; _i < _len; _i++) {
                           part = parts[_i];
                           if (regex.test(part)) {
                               return true;
                           }
                       }
                   }
               }
           }
            var get_search_regex = function(escaped_search_string) {
                var regex_anchor;
                regex_anchor = "";
                return new RegExp(regex_anchor + escaped_search_string, 'i');
            };

            var newDatas = [];
            var regex = get_search_regex(searchValue);
            $.each(obj.dictDatas,function(index,object){
                var pinYinText = pinyin.getCamelChars(object.dictName);
                var text = object.dictName;
                var code = object.dictCode;

                var search_match = searchText(pinYinText,regex)||searchText(text,regex)||searchText(code,regex);

                if(search_match){
                    newDatas.push(object);
                }
            });

            obj.renderDictFun(newDatas);

        }

        /**
         * 新增 function
         */
        this.plusFun = function(){
            xqsight.win.show("字典新增","system/dict/dictManage.html",600,300,false);
        }
        this.plusDetailFun = function(){
            var id = $("#dictList>a.active").attr("id");
            if(id == undefined || id == ""){
                xqsight.win.alert("请选择所属字典数据");
                return;
            }
            xqsight.win.show("字典明细新增","system/dict/dictDetailManage.html?dictId="+id,$(window).width(),500);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selObj = $("#dictList>a.active");
            var id = selObj.attr("id");
            if(id == undefined || id == ""){
                xqsight.win.alert("请选择编辑的数据");
                return;
            }
            xqsight.win.show("字典编辑","system/dict/dictManage.html?dictId=" + id,600,300,false);
        }
        this.editDetailFun = function(){
            var selRows = obj.dictDetailTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("字典明细修改","system/dict/dictDetailManage.html?dictDetailId=" + selRows[0].dictDetailId,$(window).width()-150,500);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selObj = $("#dictList>a.active");
            var id = selObj.attr("id");
            if(id == undefined || id == ""){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/dict/delete?date=" + new Date().getTime(),
                        "data": "dictId=" + id,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                selObj.remove();
                                if(selObj.next().length > 0){
                                    selObj.next().addClass("active");
                                    obj.dictDetailTable.ajax.reload();
                                }
                            }
                        }
                    });
                }
            });
        }
        this.removeDetailFun = function(){
            var selRows = obj.dictDetailTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/dict/deletedetail?date=" + new Date().getTime(),
                        "data": "dictDetailId=" + selRows[0].dictDetailId ,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.dictDetailTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        this.retweetFun = function(){
            xqsight.win.confirm("确认同步吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/dict/reload?date=" + new Date().getTime(),
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            xqsight.win.alert(retData.msg,retData.status);
                        }
                    });
                }
            });
        }
        /**
         * 加载数据表 function
         */
        this.loadDictDetailTableFun = function(){
            var record_table = $("#detail-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/sys/dict/querydetail',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    //TODO 添加参数判断
                    var id = $("#dictList>a.active").attr("id");
                    if(id == undefined || id == "")
                        return false;
                    aoData.push(
                        { "name": "dictId", "value":id }
                    )
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
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "dictValue",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    "data": "dictValue",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "dictDesp",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "active",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == 0)
                            return "启用"
                        else
                            return "禁用"
                    }
                },{
                    "data": "editable",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == 0)
                            return "可编辑"
                        else
                            return "不可编辑"
                    }
                },{
                    "data": "remark",
                    sWidth : "100",
                    sClass : "text-left"
                },{
                    "data": "dictId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:dictMain.editDetailFun()'><i class='ace-icon fa fa-edit'></i></a> | "
                            + "<a class='red' href='javaScript:dictMain.removeDetailFun()'><i class='ace-icon fa fa-remove'></i></a> "
                            + "</div> ";
                    }
                }]
            });

            obj.dictDetailTable = record_table;


            //单选事件
            $("#detail-table tbody").on("click","tr",function() {
                $.each($("#detail-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#detail-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#detail-table tbody").on("dblclick","tr",function() {
                obj.editDetailFun();
            });
        }
        this.loadDictFun = function(){
            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                data : "dictName=" + $("#dictName").val(),
                url:  ctxData + "/sys/dict/query",
                success: function(objMsg){
                    if(objMsg.status == "0"){
                        obj.dictDatas = objMsg.data;
                        obj.renderDictFun(objMsg.data);
                    }else{
                        //xqsight.win.alert(objMsg.msg);
                    }
                }
            });
        }
        this.renderDictFun = function(datas){
            var renderHtml = "";
            $("#dictList>a.showDict").remove();
            $.each(datas,function(index,data){
               /* if(index == 0)
                    renderHtml += '<a href="#" id="' + data.dictId + '" class="list-group-item showDict active">';
                else*/
                renderHtml += '<a href="#" id="' + data.dictId + '" class="list-group-item showDict">';
                renderHtml += '<label class="inline"><span class="lbl">' + data.dictName + "[" + data.dictCode;
                renderHtml += ']</span></label></a>';
            });
            $("#dictList").append(renderHtml);
            /*if(datas.length > 0){
                obj.dictDetailTable.ajax.reload();
            }*/
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            if(params.type=="dict"){
                obj.loadDictFun();
            }else{
                obj.dictDetailTable.ajax.reload();
                if(params.dictId== undefined || params.dictId =="" ){
                    return;
                }
            }
            //选中之前选中的数据

        }
    };
    /**
     * 初始化数据
     */
    $(document).ready(function() {
        dictMain.init();
    });
})();
var dictMain = new sys.dict.dictMain();





