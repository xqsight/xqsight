/**
 * Created by user on 2015/6/10.
 */

xqsight.nameSpace.reg("sys");

(function () {
    sys.login = function () {

        var ctxData = xqsight.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;

        // 初始化页面
        this.init = function () {
            if (window.parent.location.href != window.location.href) {
                window.parent.location.href = window.location.href;
            }

            $("#btn_login").on("click", obj.loginFun);
            document.onkeydown = function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    $("#btn_login").click();
                }
            }
        };


        // 登录
        this.loginFun = function () {
            var login_id = $("#login_id").val();
            var validateCode = $("#validateCode").val();
            var pwdResult = $("#password").val();

            $("#loginForm").html5Validate(function () {
                var rmb_m = $("#rmb_m").is(":checked");
                //登录之后的跳转页面
                if(login_id == "admin" && pwdResult == "admin"){
                    $.cookie("admin","admin",{ expires: 1 });
                    var redirect = xqsight.utils.getServerPath("domain") + "/page/index.html";
                    window.location.href = redirect;
                }else {
                    xqsight.win.alert("登录名或密码不对!");
                }
            }, {
                validate: function () {
                    if (login_id == "") {
                        $("#login_id").testRemind("请填写正确有效的用户名/手机号/邮箱");
                        $("#login_id").focus();
                        $("#login_id").select();
                        $(window).scrollTop($("#login_id").offset().top - 50);
                        return false;
                    }
                    if (pwdResult == "" || pwdResult == undefined) {
                        $("#password").testRemind("密码不能为空");
                        $("#password").focus();
                        $("#password").select()
                        $(window).scrollTop($("#password").offset().top - 50);
                        return false;
                    }
                    return true;
                }
            });
        };
    };

    // 初始化数据
    $(document).ready(function () {
        login.init();
    });
})();

var login = new sys.login();