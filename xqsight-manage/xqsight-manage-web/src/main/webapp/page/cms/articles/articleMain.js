/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("cms.article");

(function(){
    cms.article.articleMain = function(){
        var ctxData = saicfc.utils.getServerPath("cms");
        var appId= $.getUrlParam("appId");
        /**
         * 申明内部对象 
         * @type {xqsight.cms}
         */
        var obj = this;
        this.modelTree = {};
        this.curSelTree = {};
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.artilceTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.artilceTable.ajax.reload();
            });
            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                saicfc.utils.cleanValue(".searchDiv");
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

            obj.loadModelTreeFun();

            obj.loadArtilceTableFun();
        };

        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("新增文章","cms/articles/articleManage.html?modelId=" + obj.curSelTree.id,600,300,true);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.artilceTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("修改文章","cms/articles/articleManage.html",600,300,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.artilceTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择删除的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/cms/article/delete?date=" + new Date().getTime(),
                        "data": {"articleId":selRows[0].articleId },
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg)
                            if(retData.status == "0"){
                                obj.artilceTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadArtilceTableFun = function(){
            var record_table = $("#article-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getServerPath("dataTableLocal")
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/cms/article/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "articleTitle", "value": $("#articleTitle").val()},
                        { "name": "modelId", "value": $("#modelId").val()}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data: "articleTitle",
                    sWidth : "250",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "articleDescription",
                    sWidth : "200",
                    sClass : "text-left",
                    render : function(value){
                        return saicfc.win.tipShow(value,400,100);
                    }
                },{
                    data: "createTime",
                    sWidth : "200",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                }]
            });

            obj.artilceTable = record_table;
            //单选事件
            $("#article-table tbody").on("click","tr",function() {
                $("#article-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });
        }

        /*** 加载 tree **/
        this.loadModelTreeFun = function () {
            $.ajax({
                url: ctxData + "/cms/model/querytree?date="+new Date().getTime(),
                dataType: "jsonp",
                success: function(retData){
                    if(retData.status == 0){
                        $.fn.zTree.init($("#modelTree"),{
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
                                    obj.modelTree.selectNode(treeNode);
                                    obj.curSelTree = treeNode;
                                    obj.artilceTable.ajax.reload();
                                    return false;
                                }
                            }
                        }, retData.data);

                        obj.modelTree = $.fn.zTree.getZTreeObj("modelTree");

                        if(obj.curSelTree.id != undefined ){
                            obj.modelTree.selectNode(obj.curSelTree);
                        }else{
                            var nodes = obj.modelTree.getNodes();
                            if (nodes.length>0) {
                                obj.modelTree.selectNode(nodes[0]);
                                obj.curSelTree = nodes[0];
                            }
                        }

                        obj.modelTree.expandAll(true);

                        obj.artilceTable.ajax.reload();
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
            obj.artilceTable.ajax.reload();
            if(params.appId== undefined || params.appId =="" ){
                return;
            }
            //选中之前选中的数据

        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        articleMain.init();
    });
})();
var articleMain = new cms.article.articleMain();





