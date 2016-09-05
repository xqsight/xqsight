/**
 * Created by user on 2015/6/10.
 */

saicfc.nameSpace.reg("sys");

(function(){
    sys.login = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;

        // 初始化页面
        this.init = function() {
            if(window.parent.location.href != window.location.href){
                window.parent.location.href = window.location.href;
            }

            //加载验证码
            this.getValidateCodeFun();

            $("#validateCode").val("");

            $("#generateCode").on("click",function(){
                obj.getValidateCodeFun();

                event.cancelBubble=true;
            });

            $("#btn_login").on("click",obj.loginFun);
            document.onkeydown = function (e) {
                var theEvent = window.event || e;
                var code = theEvent.keyCode || theEvent.which;
                if (code == 13) {
                    $("#btn_login").click();
                }
            }
        };

        /**
         * 获取验证码
         */
        this.getValidateCodeFun = function(){
            $('#generateCode').attr('src', ctxData + '/system/getCode?date=' + Math.floor(Math.random()*100) ).fadeIn()
        };

        // 登录
        this.loginFun = function() {
            var login_id = $("#login_id").val();
            var validateCode = $("#validateCode").val();
            var pwdResult =  $("#password").val();

            $("#loginForm").html5Validate(function() {
                var rmb_m = $("#rmb_m").is(":checked");
                //登录之后的跳转页面
                var redirect = saicfc.utils.getServerPath("domain") + "/page/index.html";
                //设置参数
                var loginParam = {};
                loginParam.vali_code = validateCode;
                loginParam.rmb_m = rmb_m;
                loginParam.login_id = login_id;
                loginParam.pass_wd = pwdResult;
                //loginParam.goto = redirect;
                loginParam.is_aes = -1;
                $.ajax({
                    "url": ctxData + "/system/ajaxlogin?date=" + new Date().getTime() ,
                    "data" : loginParam,
                    "success": function(retData){
                        // 登录成功
                        if (retData.status == "0") {
                            if(redirect !== null || redirect != undefined){
                                window.location.href = unescape(redirect);
                            }else{
                                window.location.href = saicfc.utils.getServerPath("domain") + "/page/index.html";
                            }
                        } else {// 提示信息
                            saicfc.win.alert(retData.msg,retData.status);
                            obj.getValidateCodeFun();
                            $("#validateCode").focus();
                            $("#validateCode").select()
                        }
                    },
                    "dataType": "jsonp",
                    "cache": false
                });
            }, {
                validate : function() {
                    if (login_id == "" ) {
                        $("#login_id").testRemind("请填写正确有效的用户名/手机号/邮箱");
                        $("#login_id").focus();
                        $("#login_id").select();
                        $(window).scrollTop($("#login_id").offset().top - 50);
                        return false;
                    }
                    if(pwdResult == "" || pwdResult == undefined){
                        $("#password").testRemind("密码不能为空");
                        $("#password").focus();
                        $("#password").select()
                        $(window).scrollTop($("#password").offset().top - 50);
                        return false;
                    }
                    if (validateCode == "" || validateCode == undefined) {
                        $("#validateCode").testRemind("请输入验证码");
                        $("#validateCode").focus();
                        $("#validateCode").select();
                        $(window).scrollTop($("#validateCode").offset().top - 50);
                        return false;
                    }
                    return true;
                }
            });
        };
    };

    // 初始化数据
    $(document).ready(function() {
        login.init();
    });
})();

var login = new sys.login();