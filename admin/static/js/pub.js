(function ($) {
    var _progress_index = 0;
    $.ajaxSetup({
        method: "post",
        beforeSend: function () {
            if (_progress_index > 0) {
                console.debug("ajax before close progress index : " + _progress_index);
                xqsight.progress.hide(_progress_index);
                _progress_index = 0;
            }
            _progress_index = xqsight.progress.show();
            console.debug("ajax before start progress index :" + _progress_index);
        },
        complete: function (xhr) {
            xqsight.progress.hide(_progress_index);
            console.debug("ajax complete close progress index : " + _progress_index);
            if (xhr.status == 302) {
                window.location.reload(true);
            }
            if (xhr.responseJSON != undefined && xhr.responseJSON.message == "unlogin") {
                window.location="http://localhost/admin/page/index.html";
            }
        },
        error: function (data) {
            console.debug("ajax error close progress index : " + _progress_index);
            xqsight.progress.hide(_progress_index);
            /*if(data.status != 404)
             window.location.reload(true);*/
        },
        dataType: "json",
        cache: false
    });

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    $('.tipshow [data-rel=tooltip]').tooltip({show: {delay: 0}});
})(jQuery);

var xqsight = xqsight || {};
xqsight.iframeId = "iframeId";

xqsight.common = {
    /** 金额格式化，三位一瞥 **/
    formatMoney: function (amt, pre) {
        if (amt == undefined)
            return "0.00";
        var isfu = false;
        if (parseFloat(amt) < 0) {
            amt = parseFloat(amt) * -1;
            isfu = true;
        }
        pre = pre > 0 && pre <= 20 ? pre : 2;
        amt = parseFloat((amt + "").replace(/[^\d\.-]/g, "")).toFixed(pre) + "";
        var left = amt.split(".")[0].split("").reverse();
        var right = amt.split(".")[1];

        var t = "";
        var fu = "";
        for (var i = 0; i < left.length; i++) {
            t += left[i] + ((i + 1) % 3 == 0 && (i + 1) != left.length ? "," : "");
        }

        fu = isfu ? "-" : "";

        return fu + t.split("").reverse().join("") + "." + right;

    },

    /** 金额去格式化 **/
    unFormatMoney: function (money) {
        money = new String(money);
        while (money.indexOf(',', 0) != -1) {
            money = money.replace(',', '');
        }
        return money;
    },

    /** 只输入数字 **/
    NumberText: function () {
        if (!(event.keyCode == 46) && !(event.keyCode == 8) && !(event.keyCode == 37) && !(event.keyCode == 39) && !(event.keyCode == 190))
            if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)))
                event.returnValue = false;
    },

    /** 设置iframe 高度  **/
    setIframeHeight: function (iframeId) {
        var iframe = $("#" + iframeId);
        var winHeight = $(window).height() - 2 * ($("#navbar").height() + $("#portal_tabs").height());
        var iframeHeight = $(iframe.contents()).find("body").height();
        iframe.height(Math.max(winHeight, iframeHeight));
    },

    /** 设置所有Iframe的宽度 **/
    setAllIframeWidth: function () {
        var iframes = document.getElementsByTagName("iframe");
        for (var i = 0; i < iframes.length; i++) {
            var iframe = $("#" + iframes[i].id);
            var width = $(window).width() - $("#sidebar").width();
            $(iframe).width(width - 5);
            $(iframe.body).width(width);
            //设置高度
            var winHeight = $(window).height() - 2 * ($("#navbar").height() + $("#portal_tabs").height());
            var iframeHeight = $(iframe.contents()).find("body").height();
            iframe.height(Math.max(winHeight, iframeHeight));
        }
    },

    /** 本地时钟 **/
    localClock: function () {
        var now = new Date();
        var year = now.getFullYear(); // getFullYear getYear
        var month = now.getMonth();
        var date = now.getDate();
        var day = now.getDay();
        var hour = now.getHours();
        var minu = now.getMinutes();
        var sec = now.getSeconds();
        var week;
        month = month + 1;
        if (month < 10)
            month = "0" + month;
        if (date < 10)
            date = "0" + date;
        if (hour < 10)
            hour = "0" + hour;
        if (minu < 10)
            minu = "0" + minu;
        if (sec < 10)
            sec = "0" + sec;
        var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
        week = arr_week[day];

        return year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;
    }
};

/** 日期格式化 */
xqsight.moment = {
    formatYMDHms: function (value) {
        return window.top.moment(value).format("YYYY-MM-DD HH:mm:ss");
    },
    formatYMD: function (value) {
        return window.top.moment(value).format("YYYY-MM-DD");
    }
}

xqsight.utils = {
    /** 全屏处理  **/
    handleFullScreen: function () {
        var de = document.documentElement;
        if (de.requestFullscreen) {
            de.requestFullscreen();
        } else if (de.mozRequestFullScreen) {
            de.mozRequestFullScreen();
        } else if (de.webkitRequestFullScreen) {
            de.webkitRequestFullScreen();
        } else if (de.msRequestFullscreen) {
            de.msRequestFullscreen();
        } else {
            alert("当前浏览器不支持全屏！");
        }
    },

    /** 退出全屏 **/
    exitFullscreen: function () {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
    },

    browserName: function () {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        if (isOpera) {
            return "Opera";
        }
        ; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "FF";
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1) {
            if (window.navigator.webkitPersistentStorage.toString().indexOf("DeprecatedStorageQuota") > -1) {
                return "Chrome";
            } else {
                return "360";
            }
        } //判断是否Chrome浏览器//360浏览器
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } //判断是否Safari浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }; //判断是否IE浏览器
    },

    /** 替换所有字符串 */
    replaceAll: function (str, oldVal, newVal) {
        return str.replace(new RegExp(oldVal, "gm"), newVal);
    },

    /** 创建from表单并提交，一般用于下载 */
    createFromAndSubmit: function (url, data, method) {
        if (url && data) {
            data = typeof data == "string" ? data : jQuery.param(data);
            var inputs = "";
            $.each(data.split("&"), function () {
                var pair = this.split("=");
                inputs += "<input type=\"hidden\" name=\"" + pair[0] + "\" value=\"" + pair[1] + "\" />";
            });
            $("<form action=\"" + url + "\" method=\"" + (method || "post") + "\">" + inputs + "</form>").appendTo("body").submit().remove();
        }
        ;
    },

    /** 清除查询的值 **/
    cleanValue: function (elementObj) {
        $(elementObj + " input").val("");
        $(elementObj + " select option:first").prop("selected", 'selected');
    },

    /** json fill form **/
    fillForm: function (_formId, data) {
        var $form = $("#" + _formId);
        $.each(data, function (key, value) {
            var $ctrls = $form.find('[name=' + key + ']');
            if ($ctrls.is('select')) {
                if($ctrls.hasClass("selectpicker")){
                    $ctrls.selectpicker('val',value);
                    return;
                }
                $('option', $ctrls).each(function () {
                    if (this.value == value) {
                        this.selected = true;
                    }
                });
            } else if ($ctrls.is('textarea')) {
                $ctrls.val(value);
            } else {
                switch ($ctrls.attr("type")) {
                    case "text":
                    case "hidden":
                    case "number":
                    case "email":
                    case "tel":
                        $ctrls.val(value);
                        break;
                    case "radio":
                        if ($ctrls.length >= 1) {
                            $.each($ctrls, function (index) {  // every individual element
                                var elemValue = $(this).attr("value");
                                var elemValueInData = singleVal = value;
                                if (elemValue === value) {
                                    $(this).prop('checked', true);
                                } else {
                                    $(this).prop('checked', false);
                                }
                            });
                        }
                        break;
                    case "checkbox":
                        if ($ctrls.length > 1) {
                            //console.log("$ctrls.length: " + $ctrls.length + " value.length: " + value.length);
                            $.each($ctrls, function (index) {
                                var elemValue = $(this).attr("value");
                                var elemValueInData = undefined;
                                var singleVal;
                                for (var i = 0; i < value.length; i++) {
                                    singleVal = value[i];
                                    console.log("singleVal : " + singleVal + " value[i][1]" + value[i][1]);
                                    if (singleVal === elemValue) {
                                        elemValueInData = singleVal
                                    };
                                }

                                if (elemValueInData) {
                                    //console.log("TRUE elemValue: " + elemValue + " value: " + value);
                                    $(this).prop('checked', true);
                                    //$(this).prop('value', true);
                                }
                                else {
                                    //console.log("FALSE elemValue: " + elemValue + " value: " + value);
                                    $(this).prop('checked', false);
                                    //$(this).prop('value', false);
                                }
                            });
                        } else if ($ctrls.length == 1) {
                            $ctrl = $ctrls;
                            if (value) {
                                $ctrl.prop('checked', true);
                            }
                            else {
                                $ctrl.prop('checked', false);
                            }

                        }
                        break;
                }
            }
        });
    },

    /** ajax load data **/
    load: function (option) {
        $.ajax({
            url: option.url,
            data: option.data,
            method: "get",
            success: function (rep) {
                if (rep.code != 0) {
                    xqsight.win.alert(rep.message, rep.code);
                }else{
                    option.callFun(rep);
                }
            }
        });
    },

    /** ajax delete data **/
    delete: function (option) {
        var tipMsg = (option.tipMsg == undefined || option.tipMsg == "") ? "确认删除吗?" : option.tipMsg;
        var msg = (option.msg == undefined || option.msg == "") ? "删除成功!" : option.msg;
        xqsight.utils._commitFun(option.url, option.data, option.callFun, "delete", tipMsg, msg);
    },

    /** ajax save or update data **/
    put: function (option) {
        var tipMsg = "确认提交吗?";
        var msg = "处理成功!";
        if(option.tipMsg != undefined && option.tipMsg != ""){
            tipMsg = option.tipMsg;
        }

        if(option.msg == undefined || option.msg == ""){
            if(option.pk == undefined || option.pk == ""){
                msg = "保存成功!"
            }else{
                msg = "修改成功!"
            }
        }else{
            msg = option.msg;
        }

        xqsight.utils._commitFun(option.url, option.data, option.callFun, "post", tipMsg, msg);
    },

    _commitFun: function (url, data, callFun, method, tipMsg, msg) {
        xqsight.win.confirm(tipMsg, function (canSure) {
            if (canSure == "yes") {
                $.ajax({
                    url: url,
                    data: data,
                    method: method,
                    success: function (rep) {
                        if (rep.code != 0) {
                            xqsight.win.alert(rep.message, rep.code);
                        }else {
                            xqsight.win.alert(msg, rep.code);
                            callFun(rep);
                        }
                    }
                });
            }
        });
    },

    /** 获取请求的服务 **/
    getServerPath: function (reqType) {
        var serverPath = "";
        switch (reqType) {
            case "system" :  //系统管理
                serverPath = "http://localhost:8080";//xqsight.utils.getContextPath();
                break;
            case "cms" :
                serverPath = "http://localhost:8080";//xqsight.utils.getContextPath();
                break;
            case "dataTableLocal" :
                serverPath = xqsight.utils.getContextPath() + "/static/ace/js/dataTables/language/zn_ch.json";
                break;
            default:
                serverPath = xqsight.utils.getContextPath();
        }
        return serverPath;
    },

    /** 获取服务的根 **/
    getContextPath: function () {
        var contextPath = document.location.pathname;
        var index = contextPath.substr(1).indexOf("/"); //这个地方可能有问题，要根据具体项目适当修改
        contextPath = contextPath.substr(0, index + 1);
        return contextPath == "/page" ? "" : contextPath;
    }

};

xqsight.tab = {
    getCurrentIframeContent: function () {
        return window.top.$("#portal_tab_content .active > iframe")[0].contentWindow;
    }
};

xqsight.win = {
    /** 显示对话框 **/
    show: function (title, url, width, height, fit) {
        //获取宽度和高度
        var leftWidth = window.top.$("#sidebar").width(),
            topHeight = (window.top.$("#navbar").height() + window.top.$("#portal_tabs").height()),
            windowWidth = $(window).width(),
            windowHeight = window.screen.availHeight;//$(window).height();

        fit = fit == undefined ? true : fit;
        var offset = fit ? [topHeight + "px", leftWidth + "px"] : "100px";

        width = fit ? windowWidth - 10 : (width == undefined ? 600 : width);
        height = fit ? (windowHeight - 3 * topHeight) : (height == undefined ? 400 : height);

        window.top.layer.open({
            type: 2,
            title: '<i class="ace-icon fa fa-edit"></i>  ' + title,
            shadeClose: false,
            shade: 0.2,
            shift: 1,
            skin: "layui-layer-molv",
            moveOut: true,
            offset: offset,
            maxmin: true, //开启最大化最小化按钮
            area: [width + 'px', height + 'px'],
            content: url
        });

    },

    close: function () {
        var index = window.top.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        window.top.layer.close(index); //再执行关闭
    },

    /** 提示框 **/
    alert: function (msg, type) {
        if (type == 0) {
            window.top.layer.msg(msg, {icon: 1, offset: 100, shift: 6});
        } else if (type == -1) {
            window.top.layer.msg(msg, {icon: 5, offset: 100, shift: 6});
        } else {
            window.top.layer.alert(msg, {title: '温馨提示', skin: "layui-layer-molv", icon: 0});
        }
    },

    /** 确认对话框 **/
    confirm: function (msg, callbackFun) {
        window.top.layer.confirm(msg, {
            icon: 3, title: '温馨提示', skin: "layui-layer-molv",
            btn: ['确认', '取消'] //按钮
        }, function (index) {
            window.top.layer.close(index)
            callbackFun("yes");
        }, function (index) {
            window.top.layer.close(index)
            callbackFun("cancel");
        });
    },

    // 提示层
    tipShow: function (text, maxWidth, maxHeight) {
        return "<div class='tipshow'>" +
            "<div data-rel='tooltip' style='overflow: hidden;max-width:" + maxWidth + "px;max-height:" + maxHeight + "px;' " +
            "title='" + text + "'>" + text + "</div></div>"
    },

    // 图片展示层
    imgShow: function (url) {
        window.top.layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: true,
            area: ['600px', '400px'],
            content: "<img width='600px' height='400px' src='" + url + "'/>"
        });
    }

};

xqsight.progress = {
    loading: function () {
        return window.top.layer.load(0, {
            shade: [0.1, '#000'],
            time: 5000
        });
    },

    removeLoading: function (index) {
        window.top.layer.close(index);
    },

    startPageLoading: function (options) {
        if (options && options.animate) {
            $('.page-spinner-bar').remove();
            $('body').append('<div class="page-spinner-bar"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div>');
        } else {
            $('.page-loading').remove();
            $('body').append('<div class="page-loading"><img src="' + this.getGlobalImgPath() + 'loading-spinner-blue.gif"/>&nbsp;&nbsp;<span>' + (options && options.message ? options.message : 'Loading...') + '</span></div>');
        }
    },

    stopPageLoading: function () {
        $('.page-loading, .page-spinner-bar').remove();
    },

    show: function () {
        return window.top.layer.load(0, {
            shade: [0.1, '#000'],
            time: 5000
        });
    },

    hide: function (index) {
        window.top.layer.close(index);
    }


}


//命名空间
xqsight.nameSpace = {
    reg: function (s) {
        var arr = s.split('.');
        var namespace = window;

        for (var i = 0, k = arr.length; i < k; i++) {
            if (typeof namespace[arr[i]] == 'undefined') {
                namespace[arr[i]] = {};
            }
            namespace = namespace[arr[i]];
        }
    },

    del: function (s) {
        var arr = s.split('.');
        var namespace = window;

        for (var i = 0, k = arr.length; i < k; i++) {
            if (typeof namespace[arr[i]] == 'undefined') {
                return;
            } else if (k == i + 1) {
                delete  namespace[arr[i]];
                return;
            } else {
                namespace = namespace[arr[i]];
            }
        }
    }
};


xqsight.validata = {
    //前后去空格
    strTrim: function (str) {
        return str.replace(/(^\s*)|(\s*$)/g, '');
    },

    //去所有空格
    strTrimAll: function (str) {
        return str.replace(/\s/ig, '');
    },

    //是不是手机号码
    isPhone: function (str) {
        var reg = /^1\d{10}$/;
        return reg.test(str);
    },

    isEmail: function (str) {
        var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        return reg.test(str);
    },
    //15位和18位身份证号码的基本校验
    isUserIdentity: function (str) {
        var reg = /^\d{15}|(\d{17}(\d|x|X))$/;
        return reg.test(str);
    }
};




