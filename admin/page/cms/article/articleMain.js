/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("cms.article");

(function(){
    cms.article.articleMain = function(){
        var ctxData = xqsight.utils.getServerPath("cms");
        /**
         * 申明内部对象 
         * @type {xqsight.cms}
         */
        var obj = this;
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
                xqsight.utils.cleanValue(".searchDiv");
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

            obj.loadArtilceTableFun();
        };

        /**
         * 新增 function
         */
        this.plusFun = function(){
            //window.top.index.addTabPageFun("article","新增文章","cms/article/articleManage.html");
           xqsight.win.show("新增文章","cms/article/articleManage.html",600,300,true);
        }

        /**
         * 修改 function
         */
        this.editFun = function(){
            var selRows = obj.artilceTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择修改的数据");
                return;
            }
            xqsight.win.show("修改文章","cms/article/articleManage.html?articleId="+ selRows[0].articleId,600,300,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.artilceTable.rows(".info").data();
            if(selRows.length < 1){
                xqsight.win.alert("请选择删除的数据");
                return;
            }
            xqsight.utils.delete({url:ctxData + "/cms/article/" + selRows[0].articleId,callFun:function (rep) {
                obj.artilceTable.ajax.reload();
            }});
        }

        /**
         * 加载数据表 function
         */
        this.loadArtilceTableFun = function(){
            var record_table = $("#article-table").DataTable({
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/cms/article/page',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    xqsight.utils.load({url:sUrl,data:aoData,callFun:function (rep) {
                        fnCallback(rep.data);
                        //渲染结束重新设置高度
                        parent.xqsight.common.setIframeHeight($.getUrlParam(xqsight.iframeId));
                    }})
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "filter_LIKES_article_title", "value": $("#articleTitle").val()}
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "articleId",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "articleTitle",
                    sWidth : "100",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "articleAuthor",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "articleDesp",
                    sWidth : "120",
                    sClass : "text-left",
                    render : function(value){
                        return xqsight.win.tipShow(value,400,100);
                    }
                },{
                    data: "articleUrl",
                    sWidth : "120",
                    sClass : "text-left",
                    render : function(value){
                        return "<a href=" + value + " target='_blank'>预览</a>";
                    }
                },{
                    data: "publishTime",
                    sWidth : "100",
                    sClass : "text-center",
                    render : function(value){
                        return xqsight.moment.formatYMD(value);
                    }
                },{
                    data: "articleId",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:articleMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | " +
                            "<a class='red' href='javaScript:articleMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.artilceTable = record_table;

            //单选事件
            $("#article-table tbody").on("click","tr",function() {
                $.each($("#article-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#article-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#article-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /**  新增编辑回调函数 * */
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





