/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.set");

(function() {
    sys.set.userInfo = function () {

        var ctxData = xqsight.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;

        // 初始化页面
        this.init = function () {
            $("#btn_pwd_upd").bind("click", obj.updPasswordFun);
            //editables on first profile page
            $.fn.editable.defaults.mode = 'inline';
            $.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
            $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>' +
                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';

            obj.loadUserInfoFun();

            $("#fileId").on("fileuploaded", function (event, data, previewId, index) {
                var retData = data.response;
                xqsight.win.alert(retData.msg,retData.status);
                if (retData.status == "0") {
                    $("#imgUrl").attr("src", retData.data).fadeIn();
                }
            });
            obj.initFileInput();
        };
        //初始化控件
        this.initFileInput = function () {
            var control = $('#fileId');
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: ctxData + "/sys/login/updimg", //上传的地址
                allowedFileExtensions: ['jpg', 'png', 'gif'],//接收的文件后缀
                showUpload: true, //是否显示上传按钮
                showCaption: false,//是否显示标题
                showClose: false,
                showPreview: false,
                showRemove: false,
                browseClass: "btn btn-primary", //按钮样式
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            });
        };

        /**
         * 加载用户信息
         */
        this.loadUserInfoFun = function () {
            $.ajax({
                "url": ctxData + "/sys/login/queryuserinfo?date=" + new Date().getTime(),
                "success": function (retData) {
                    if (retData.data == undefined || retData.data == null)
                        return;
                    $("#userName").html(retData.data.userName);
                    $("#loginName").html(retData.data.loginId);
                    $("#createTime").html(xqsight.moment.formatYMD(retData.data.createTime));
                    if (retData.data.imgUrl != "" && retData.data.imgUrl != undefined)
                        $("#imgUrl").attr("src", retData.data.imgUrl).fadeIn();
                    obj.updUserFun();
                },
                "dataType": "jsonp",
                "cache": false
            });
        };


        /**
         * 更新用户信息
         */
        this.updUserFun = function () {
            //editables
            $('#userName').editable({
                type: 'text',
                name: 'userName',
                success: function (response, newValue) {
                    if (newValue != "") {
                        if (newValue == "") {
                            xqsight.win.alert("用户名不能为空！");
                            return false;
                        }
                        $.ajax({
                            "url": ctxData + "/sys/login/updusername?date=" + new Date().getTime(),
                            "data": "userName=" + newValue,
                            "success": function (retData) {
                                xqsight.win.alert(retData.msg,retData.status);
                            },
                            "dataType": "jsonp",
                            "cache": false
                        });
                    }
                }
            });
        };

        /**
         * 修改用户密码
         */
        this.updPasswordFun = function () {
            var callback = function (btn) {
                if (btn == "yes") {
                    url = ctxData + "/sys/login/updpwd?date=" + new Date().getTime();
                    $.ajax({
                        "url": url,
                        "data": "password=" + $("#password").val(),
                        "success": function (retData) {
                            xqsight.win.alert(retData.msg,retData.status);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };

            $("#updForm").html5Validate(function () {
                xqsight.win.confirm("确认修改吗？", callback);
            }, {
                validate: function () {
                    if (xqsight.validata.strTrim($("#password").val()) == "") {
                        $("#password").testRemind("新密码不能为空");
                        $(window).scrollTop($("#password").offset().top - 50);
                        return false;
                    } else if (xqsight.validata.strTrim($("#password").val()) != xqsight.validata.strTrim($("#repassword").val())) {
                        $("#repassword").testRemind("新密码和确认密码不一致");
                        $(window).scrollTop($("#repassword").offset().top - 50);
                        return false;
                    }
                    return true;
                }
            });
        }
    };

    // 初始化数据
    $(document).ready(function() {
        userInfo.init();
    });
})();

var userInfo = new sys.set.userInfo()