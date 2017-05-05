/**
 * Created by user on 2015/12/14.
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
            $("#btn-reset").click(function(){
                xqsight.utils.cleanValue(".filter");
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

            obj.loadDictTreeFun();
            obj.loadDictTableFun();

        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
           if(obj.curSelTree.id == undefined ){
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("字典新增","system/dict/dictManage.html?parentId=" + obj.curSelTree.id,700,400,false);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.dictTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("字典修改","system/dict/dictManage.html?dictId=" + selRows[0].dictId,700,400,false);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.dictTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.utils.delete({url:ctxData + "/sys/dict/" + selRows[0].dictId,callFun:function (rep) {
                obj.loadDictTreeFun();
            }});
        };

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
                "sAjaxSource": ctxData + '/sys/dict/',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }});
                },
                "fnServerParams": function (aoData) {
                    var parentId = 0;
                    if(obj.curSelTree.id != undefined ){
                        parentId = obj.curSelTree.id;
                    }
                    aoData.push(
                        { "name": "filter_LIKES_dict_name", "value": $("#dictName").val() },
                        { "name": "filter_EQI_parent_id", "value": parentId }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "dictName",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "dictName",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "dictCode",
                    sWidth : "80",
                    sClass : "text-left"
                },{
                    data: "dictValue",
                    sWidth : "28",
                    sClass : "text-left"
                },{
                    data: "editable",
                    sWidth : "20",
                    sClass : "text-left",
                    render : function(value){
                        return value == "0" ? "是" : "否";
                    }
                },{
                    data: "sort",
                    sWidth : "60",
                    sClass : "text-center"
                },{
                    data: "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);
                    }
                },{
                    data: "dictId",
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
            xqsight.utils.load({url:ctxData + "/sys/dict/tree",callFun:function (rep) {
                var treeRoot = [{
                    name : "系统字典",
                    id : 0,
                    children : rep.data
                }];
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
                        onClick: function onClick(e, treeId, treeNode) {
                            obj.dictTree.selectNode(treeNode);
                            obj.curSelTree = treeNode;
                            obj.dictTable.ajax.reload();
                            e.preventDefault();
                            return false;
                        }
                    }
                }, treeRoot);

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

                //渲染结束重新设置高度
                parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
            }});
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





