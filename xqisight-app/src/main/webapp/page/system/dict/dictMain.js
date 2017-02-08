/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.dict");

(function(){
    sys.dict.dictMain = function(){
        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        this.dictTable = {};
        this.dictTree = {};
        this.curSelTree={};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.dictTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.dictTable.ajax.reload();
                }
            });

            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                saicfc.utils.cleanValue(".filter");
            });

            /**
             * 新增
             */
            $("#btn-plus").on("click",obj.plusFun);

            /**
             * 修改
             */
            $("#btn-edit").on("click",obj.editFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);

            /**
             * 删除
             */
            $("#btn-stop").on("click",obj.stopFun);

            obj.loadDictTreeFun();
            obj.loadDictTableFun();

        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("字典新增","system/dict/dictManage.html?parentId=" + obj.curSelTree.id,$(window).width()-150,500);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.dictTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("字典修改","system/dict/dictManage.html?dictId=" + selRows[0].dictId,$(window).width()-150,500);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.dictTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/dict/delete?date=" + new Date().getTime(),
                        "data": {dictId : selRows[0].dictId },
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.loadDictTreeFun();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 停用 function
         */
        this.stopFun = function(){
            var selRows = obj.dictTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择停用的数据");
                return;
            }
            saicfc.win.confirm("确认停用吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/dict/logicDel?date=" + new Date().getTime(),
                        "data": {dictId : selRows[0].dictId },
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.dictTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadDictTableFun = function(){
            var record_table = $("#dict-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/sys/dict/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                        }
                    });
                },
                "fnServerParams": function (aoData) {
                    var parentId = 0;
                    if(obj.curSelTree.id != undefined ){
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "dictName", "value": $("#dictName").val() },
                        { "name": "parentId", "value": parentId }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "dictId",
                    sWidth : "1",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    "data": "dictName",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    "data": "dictCode",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    "data": "dictValue",
                    sWidth : "100",
                    sClass : "text-left"
                },{
                    "data": "active",
                    sWidth : "40",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "是" : "否";
                    }
                },{
                    "data": "editable",
                    sWidth : "40",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "是" : "否";
                    }
                },{
                    "data": "remark",
                    sWidth : "100",
                    sClass : "text-center"
                },{
                    "data": "dictId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:dictMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:dictMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.dictTable = record_table;

            //单选事件
            $("#dict-table tbody").on("click","tr",function() {
                $.each($("#dict-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#dict-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#dict-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /*** 加载 tree **/
        this.loadDictTreeFun = function () {
            $.ajax({
                url: ctxData + "/sys/dict/querytree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    if(retData.status == 0){
                        $.fn.zTree.init($("#dictTree"),{
                            check: {
                                enable: false,
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onClick: function onClick(event, treeId, treeNode) {
                                    obj.dictTree.selectNode(treeNode);
                                    obj.curSelTree = treeNode;
                                    obj.dictTable.ajax.reload();
                                    //阻止事件冒泡
                                    event.stopPropagation();
                                    //阻止事件执行
                                    event.preventDefault();
                                    return false;
                                }
                            }
                        }, retData.data);

                        obj.dictTree = $.fn.zTree.getZTreeObj("dictTree");

                        if(obj.curSelTree.id != undefined ){
                            obj.dictTree.selectNode(obj.curSelTree);
                        }else{
                            var nodes = obj.dictTree.getNodes();
                            if (nodes.length>0) {
                                obj.dictTree.selectNode(nodes[0]);
                                obj.curSelTree = nodes[0];
                            }
                        }

                        obj.dictTree.expandAll(true);

                        obj.dictTable.ajax.reload();
                    }
                    //渲染结束重新设置高度
                    parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                }
            });
        }


        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.loadDictTreeFun();
            if(params.dictId== undefined || params.dictId =="" ){
                return;
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





