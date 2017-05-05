/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.site");

(function(){
    cms.site.siteMain = function(){
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        this.siteTable = {};
        this.siteTree = {};
        this.curSelTree={};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.siteTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.siteTable.ajax.reload();
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

            obj.loadSiteTreeFun();
            obj.loadSiteTableFun();

        };


        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                xqsight.win.alert("请选择要添加的节点");
                return;
            }
            xqsight.win.show("站点新增","cms/site/siteManage.html?parentId=" + obj.curSelTree.id,700,400,false);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.siteTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("站点修改","cms/site/siteManage.html?siteId=" + selRows[0].siteId,700,400,false);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.siteTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.utils.delete({url: ctxData + "/cms/site/" + selRows[0].siteId,callFun:function (rep) {
                obj.loadSiteTreeFun();
            } });
        };

        /**
         * 加载数据表 function
         */
        this.loadSiteTableFun = function(){
            var record_table = $("#site-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bSort" : false,
                "bInfo" : false,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "paging":   false,
                "sAjaxSource": ctxData + '/cms/site/',
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
                        { "name": "filter_LIKES_site_name", "value": $("#siteName").val() },
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
                    data : "siteId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "siteName",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "siteCode",
                    sWidth : "100",
                    sClass : "text-left"
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
                    data: "siteId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:siteMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:siteMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.siteTable = record_table;

            //单选事件
            $("#site-table tbody").on("click","tr",function() {
                $.each($("#site-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#site-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#site-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /*** 加载 tree **/
        this.loadSiteTreeFun = function () {
            xqsight.utils.load({url:ctxData + "/cms/site/tree",callFun:function (rep) {
                var treeRoot = [{
                    name : "系统站点",
                    id : 0,
                    children : rep.data
                }];
                $.fn.zTree.init($("#siteTree"),{
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
                            obj.siteTree.selectNode(treeNode);
                            obj.curSelTree = treeNode;
                            obj.siteTable.ajax.reload();
                            e.preventDefault();
                            return false;
                        }
                    }
                }, treeRoot);

                obj.siteTree = $.fn.zTree.getZTreeObj("siteTree");

                if(obj.curSelTree.id != undefined ){
                    obj.siteTree.selectNode(obj.curSelTree);
                }else{
                    var nodes = obj.siteTree.getNodes();
                    if (nodes.length>0) {
                        obj.siteTree.selectNode(nodes[0]);
                        obj.curSelTree = nodes[0];
                    }
                }

                obj.siteTree.expandAll(true);

                obj.siteTable.ajax.reload();

                //渲染结束重新设置高度
                parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
            }});
        };


        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.loadSiteTreeFun();
            if(params.siteId== undefined || params.siteId =="" ){
                return;
            }
            //选中之前选中的数据

        }


    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        siteMain.init();
    });
})();
var siteMain = new cms.site.siteMain();





