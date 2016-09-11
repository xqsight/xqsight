/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.forumMain = function(){
        var ctxData = saicfc.utils.getServerPath("cms");

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
        this.forumTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $("#btn-search").click(function(){
                obj.forumTable.ajax.reload();
            });
            
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.forumTable.ajax.reload();
                }
            });

            /**
             * 重置
             */
            $("#btn-undo").click(function(){
                saicfc.utils.cleanValue(".filter");
            });

            /**
             * 修改
             */
            $("#btn-detail").on("click",obj.forumDetailFun);

            /**
             * 删除
             */
            $("#btn-remove").on("click",obj.removeFun);

            
            /**
             * 加载列表
             */
            obj.loadForumTableFun();
        };

        /**
         * 详情 function
         */
        this.forumDetailFun = function(articleId){
            var selRows = obj.forumTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择查看的数据");
                return;
            }
        	var href= ctxData + "/page/cms/forum/forumManage.html?articleId=" + selRows[0].articleId;
        	window.top.index.addTabPageFun("forum_window","查看详情",href,true);
        }

        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.forumTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择删除的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        url: ctxData + "/cms/article/delete?date=" + new Date().getTime(),
                        data: {"articleId":selRows[0].articleId},
                        dataType: "jsonp",
                        cache: false,
                        success: function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.forumTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadForumTableFun = function(){
            var record_table = $("#ask-table").DataTable({
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
                "sAjaxSource": ctxData + '/forum/query?modelCode=FORUM',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        data: aoData,
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
                        { "name": "articleTitle", "value": $("#articleTitle").val() }
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
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "articleDescription",
                    sWidth : "160",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "createTime",
                    sWidth : "120",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMDHms(value);
                    }
                },{
                    data: "articleId",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'> <a class='red' href='javaScript:forumMain.forumDetailFun();'><i class='ace-icon fa fa-search'></i></a> | " +
                            "<a class='red' href='javaScript:forumMain.removeFun();'><i class='ace-icon fa fa-remove'></i></a></div> ";
                    }
                }]
            });

            obj.forumTable = record_table;

            //单选事件
            $("#ask-table tbody").on("click","tr",function() {
                $.each($("#ask-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#ask-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#ask-table tbody").on("dblclick","tr",function() {
                obj.forumDetailFun();
            });
        }

        /**
         *
         * 新增编辑回调函数
         *
         */
        this.editCallBackFun = function(params){
            //加载数据
            obj.forumTable.ajax.reload();
            if(params.articleId== undefined || params.articleId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        forumMain.init();
    });
})();
var forumMain = new xqsight.cms.forumMain();





