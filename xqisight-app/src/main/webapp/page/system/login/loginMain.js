
/**
 * Created by login on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.login");

(function(){
    sys.login.loginMain = function(){
        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;
        this.curSelTree = {};
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.loginTable = {};

        /**
         * 初始化调用 function
         */
        this.init = function() {
            /**
             * 查询
             */
            $(".btn-search").click(function(){
                obj.loginTable.ajax.reload();
            });
            $(document).bind("keydown",".filter input",function(e){
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    obj.loginTable.ajax.reload();
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
             * 加载列表
             */
            obj.loadUserTableFun();
        };

        /**
         * 新增 function
         */
        this.plusFun = function(){
            if(obj.curSelTree.id == undefined ){
                saicfc.win.alert("请选择要添加的节点");
                return;
            }
            saicfc.win.show("登录用户新增","system/login/loginManage.html",$(window).width(),500);
        }

        /**
         修改 function
         */
        this.editFun = function(){
            var selRows = obj.loginTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择修改的数据");
                return;
            }
            saicfc.win.show("修改","system/login/loginManage.html?id=" + selRows[0].id,$(window).width()-150,$(window).height());
        }


        /**
         * 删除 function
         */
        this.removeFun = function(){
            var selRows = obj.loginTable.rows(".info").data();
            if(selRows.length < 1){
                saicfc.win.alert("请选择删除的数据");
                return;
            }
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/login/delete?date=" + new Date().getTime(),
                        "data": "id=" + selRows[0].id,
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.loginTable.ajax.reload();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载数据表 function
         */
        this.loadUserTableFun = function(){
            var record_table = $("#login-table").DataTable({
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
                "sAjaxSource": ctxData + '/sys/login/query',
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
                        { "name": "loginId", "value": $("#loginId").val() },
                        { "name": "userName", "value": $("#userName").val() }
                    );
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [{
                    data : "id",
                    sWidth : "2",
                    render : function(value){
                        return '<label class="pos-rel"><input id="' + value + '" type="checkbox" class="ace" /><span class="lbl"></span></label>';
                    }
                },{
                    data: "loginName",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "loginId",
                    sWidth : "120",
                    sClass : "text-center",
                    sSort : false
                },{
                    data: "loginType",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == "1"){
                            return "编号";
                        }else if(value == "2"){
                            return "邮箱";
                        }else if(value == "3"){
                            return "电话";
                        }
                    }
                },{
                    data : "imgUrl",
                    sWidth : "60",
                    sClass : "text-center",
                    sSort : false,
                    render : function(value){
                        if(value == undefined || value == "")
                            return "";
                        return "<a href=\"javascript:saicfc.win.imgShow('" + value + "');\">查看</a>";
                    }
                },{
                    data : "locked",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return value == "0" ? "未锁定" : "锁定";
                    }
                },{
                    data : "createTime",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMD(value);
                    }
                },{
                    data : "id",
                    sWidth : "80",
                    sClass : "text-center",
                    render : function(value){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:loginMain.resetPwdFun(\"" + value + "\")'>重置密码</a> "
                            + "</div> ";
                    }
                },{
                    data : "id",
                    sWidth : "60",
                    sClass : "text-center",
                    render : function(){
                        return "<div class='bolder'>"
                            + "<a class='red' href='javaScript:loginMain.editFun()'><i class='ace-icon fa fa-edit'></i></a> | "
                            + "<a class='red' href='javaScript:loginMain.removeFun()'><i class='ace-icon fa fa-remove'></i></a> "
                            + "</div> ";
                    }
                }]
            });

            obj.loginTable = record_table;

            //单选事件
            $("#login-table tbody").on("click","tr",function() {
                $.each($("#login-table tbody").find("input[type='checkbox']"),function(index,object){
                    object.checked = false;
                });
                $(this).find("input[type='checkbox']").get(0).checked = true;
                $("#login-table>tbody>tr").removeClass("info");
                $(this).addClass("info");
            });

            $("#login-table tbody").on("dblclick","tr",function() {
                obj.editFun();
            });
        }

        /**
         * 重置密码
         * @param id
         */
        this.resetPwdFun = function(id){
            saicfc.win.confirm("确认重置密码吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/sys/login/resetpwd?date=" + new Date().getTime(),
                        "data": "id=" + id,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                        }
                    });
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
            obj.loginTable.ajax.reload();
            if(params.loginId== undefined || params.loginId =="" ){
                return;
            }
            //选中之前选中的数据

        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        loginMain.init();
    });
})();
var loginMain = new sys.login.loginMain();





