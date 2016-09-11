/**
 * Created by wangganggang on 15/12/11.
 */
saicfc.nameSpace.reg("sys");

(function(){
    sys.index = function() {

        var ctxData = saicfc.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;

        /**  初始化页面 */
        this.init = function () {
            obj.initMenuFun();

            //绑定菜单点击事件
            $("#portal_menus").on("click","li>a",obj.menuClickFun);

            //鼠标进入时，添加tab改变样式;鼠标退出时，删除tab改变样式
            $("#portal_tabs").delegate("li>a>span","mouseover",function(event) {
                $(event.currentTarget).addClass("badge");
                $(event.currentTarget).addClass("badge-warning");
            });
            $("#portal_tabs").delegate("li>a>span","mouseout",function(event) {
                $(event.currentTarget).removeClass("badge");
                $(event.currentTarget).removeClass("badge-warning");
            });

            //加载用户信息
            obj.initUserInfoFun();

            //加载快捷键
            obj.loadQuickKeyFun();

            //选中tab是修改选中菜单
            $("#portal_tabs").on("click","li>a",function(e){
                if($(e.target).hasClass("fa-remove"))
                    return false;
                obj.selectMenuFun($(this).attr("menuid"))
            });

            //点击移除事件
            obj.tabCloseFun();

            //tab工具栏初始化
            obj.tabToolInitFun();

            //tab工具栏绑定事件
            obj.tabToolEvent();

            //登出
            $("#btn-logout").on("click",obj.logoutFun);

            //本地时间
            obj.setLocalLock();
        };

        /** 本地时间 */
        this.setLocalLock = function(){
            $('#localClock').html(saicfc.common.localClock());
            setTimeout("index.setLocalLock()", 200);
        };

        /** 初始化菜单 */
        this.initMenuFun = function(){
            $.ajax({
                "url": ctxData + "/sys/menu/querymenubyuser?date=" + new Date().getTime() ,
                "success": function(retData){
                    var menuHtml = "";
                    menuHtml =  obj.foreachFun(menuHtml,retData.data) + "</ul></li>";
                    $("#portal_menus").append(menuHtml);
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        /** 菜单循环处理 */
        this.foreachFun = function(menuHtml,datas){
            $.each(datas,function(index,object){
                if(object.menuId == undefined)
                    return;
                if(object.children != undefined && object.children.length > 0){
                    if(index > 0){
                        menuHtml += '</ul></li>';
                    }
                    menuHtml += '<li><a href="javascript:void(0);" class="dropdown-toggle" isleaf="1">';
                    menuHtml += '<i class="menu-icon fa ' + (object.icon == "" ? "fa-list" : object.icon) + '"></i>';
                    menuHtml += '<span class="menu-text">' + object.menuName + '</span>';
                    menuHtml += '<b class="arrow fa fa-angle-down"></b></a>';
                    menuHtml += '<b class="arrow"></b>';
                    menuHtml += '<ul class="submenu">';

                    if(object.children.length > 0){
                        var chaildrenHtml = "";
                        menuHtml += obj.foreachFun(chaildrenHtml,object.children);
                    }
                }else{
                    menuHtml += '<li><a href="javascript:void(0);" rel="' + object.url + '" menuid="' + object.menuId + '" isleaf="0" title="' + object.menuName + '">';
                    menuHtml += '<i class="menu-icon fa ' + (object.icon == "" ? "fa-caret-right" : object.icon)  + '"></i>';
                    menuHtml += '<span class="menu-text">' + object.menuName + '</span>';
                    menuHtml += '</a><b class="arrow"></b></li>';
                }
            });
            return menuHtml;
        };

        /** 选中指定的菜单 */
        this.selectMenuFun = function(menuId){
            var menuA = $("#portal_menus a[menuid=" + menuId + "]").parent();
            if(menuA == undefined)
                return;

            $("#portal_menus li").removeClass("active");
            menuA.addClass("active");

            $("#portal_menus li ul").css("display","none");
            $("#portal_menus li").removeClass("open");

            if(menuA.parent().hasClass("submenu")){
                menuA.parent().css("display","block");
                menuA.parent().parent().addClass("open");
            }
        }

        /** 关闭事件 */
        this.tabCloseFun = function(){
            $("#portal_tabs").delegate("li>a>span","click",function(event) {
                var currentClickTab = $(this).parent().parent();

                obj.closeCurrentTab(currentClickTab);

                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });
        }

        /** 关闭指定的tab */
        this.closeCurrentTab = function(currentSelectedTab){

            if($(currentSelectedTab).attr("id") == "tab_home"){
                saicfc.win.alert("首页不能关闭");
                obj.tabToolsFun("hide");
                return false;
            }

            var nextSelectTab;
            if ($(currentSelectedTab).prev().length > 0) {
                nextSelectTab = $(currentSelectedTab).prev();
            } else if ($(currentSelectedTab).next().length > 0) {
                nextSelectTab = $(currentSelectedTab).next();
            } else {
                nextSelectTab = $("#portal_tabs li").eq(0);
            }

            //移除tab内容
            $("#portal_tab_content "+ $(currentSelectedTab).find("a").attr("href")).remove();
            //移除tab项
            $(currentSelectedTab).remove();

            //如果有选中的tab则返回
            if($("#portal_tabs .active>a").length > 0){
                return false;
            }
            //下一个tab选中
            $(nextSelectTab).addClass("active");
            $("#portal_tab_content > "+ $(nextSelectTab).find("a").attr("href") +"").addClass("active");
            obj.selectMenuFun($(nextSelectTab).find("a").attr("menuid"));
        }

        /** 菜单点击事件 */
        this.menuClickFun = function(){
            var href = $(this).attr("rel"),
                id = $(this).attr("menuid"),
                title = $(this).attr("title");

            if($(this).attr("isleaf") == 1)
                return;

            //改变样式
            $("#portal_menus li").removeClass("active");
            $(this).parent().addClass("active");

            obj.addTabPageFun(id,title,href);
        }

        /** 添加tab function */
        this.addTabPageFun = function(id,title,href,reload){
            reload = (reload == undefined) ? false : reload;
            //移除选中样式
            $("#portal_tabs li").removeClass("active");
            $("#portal_tab_content div").removeClass("active");

            //判断是否存在，不存在添加，存在选中
            if($("#portal_tabs #tab_" + id).length > 0){
                $("#tab_" + id).addClass("active");
                $("#tab_content_" + id).addClass("active");
                if(reload)
                    $("#iframe_" + id).attr("src",href);

                return;
            }
            var joinChar = (href.indexOf("?") > 0) ? "&" : "?";

            //添加
            obj.addTabFun(id,title);
            obj.addTabContentFun(id,href + joinChar + "iframeId=iframe_" +id);
        }

        /** tab reload function **/
        this.tabReloadFun = function(){
            var currentTabContentId = $("#portal_tabs .active>a").attr("href"),
                currentTabContentIframe = $(currentTabContentId + ">iframe")[0];
            $(currentTabContentIframe).attr("src",currentTabContentIframe.src);
            obj.tabToolsFun("hide");
        }
        this.tabReloadByIdFun = function(id){
            $("#iframe_" + id).attr("src",$("#iframe_" + id).attr("src"));
        }

        /** 添加tab */
        this.addTabFun = function(id,title){
            var tabHtml = '<li role="presentation" class="active" id="tab_' + id + '">';
            tabHtml += '<a href="#tab_content_' + id + '" aria-controls="' + id + '" menuid="' + id + '" role="tab" data-toggle="tab">';
            tabHtml += title + '<span class=""><i class="ace-icon fa fa-remove"></i></span></a></li>';

            $("#portal_tabs").append(tabHtml);
        }

        /** 添加tab内容 */
        this.addTabContentFun = function(id,href){
            var tabContent = '<div class="tab-pane active" id="tab_content_' + id + '">';
            tabContent += '<iframe src="' + href + '" scrolling="auto" class="no-border" frameborder="0px;" width="100%" ';
            tabContent += ' id="iframe_' + id + '" onload="saicfc.common.setIframeHeight(this.id);"></iframe> </div>';

            $("#portal_tab_content").append(tabContent);
        }

        /** logout function **/
        this.logoutFun = function(){
            $.ajax({
                url: ctxData + "/authc/logout?date=" + new Date().getTime() ,
                success: function(){
                    window.location.reload(true);
                },
                error : function(){
                    window.location.reload(true);
                },
                dataType: "jsonp",
                cache: false
            });
        };

        /** 展示用户信息  **/
        this.initUserInfoFun = function(){
            $.ajax({
                "url": ctxData + "/sys/login/queryuserinfo?date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.data == undefined || retData.data == null)
                        return;
                    $("#userName").html(retData.data.userName);
                    if(retData.data.imgUrl != "" && retData.data.imgUrl != undefined) {
                        $('#userImg').attr('src', retData.data.imgUrl).fadeIn();
                    }else {
                        $('#userImg').attr('src', "../static/images/user.jpg").fadeIn();
                    }
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        /** 跳转页面 **/
        this.forWardFun = function(flag){
            var id,title,href;
            if(flag == "userInfo"){
                id = "111",title = "用户信息",href = "system/set/userInfo.html";
            }else if(flag == "set"){
                id = "222",title = "设置",href = "system/set/quicklyKey.html";
            }
            obj.addTabPageFun(id,title,href);
        };

        /**  加载快捷键 */
        this.loadQuickKeyFun = function(){
            $.ajax({
                "url": ctxData + "/sys/quickkey/querybyid?date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.data == undefined || retData.data == null || retData.data.length == 0)
                        return;
                    $("#quickKey").html("");
                    var quickHtml='<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">';
                    $.each(retData.data,function(index,object){
                        var iconCss,buttonCss;
                        if(index == 0){
                            iconCss="fa-signal", buttonCss = "btn-success";
                        }else if(index == 1){
                            iconCss="fa-pencil",buttonCss = "btn-info";
                        }else if(index == 2){
                            iconCss="fa-users",buttonCss = "btn-warning";
                        }else if(index == 3){
                            iconCss="fa-cogs",buttonCss = "btn-danger";
                        }

                        quickHtml += '<button id="quick_btn' + index + '" class="btn '+ buttonCss + '" data-rel="tooltip" data-placement="' + (index > 1 ? "left" : "right") + '" title="';
                        quickHtml += object.keyTitle + '" rel="' + object.keyValue + '">';
                        quickHtml += ' <i class="ace-icon fa ' + (object.keyIcon == "" ? iconCss : object.keyIcon) + '"></i></button>&nbsp;';
                    });
                    quickHtml += '</div>';

                    quickHtml += '<div class="sidebar-shortcuts-mini"><span class="btn btn-success"></span><span class="btn btn-info"></span>';
                    quickHtml += '<span class="btn btn-warning"></span><span class="btn btn-danger"></span></div>';
                    $("#quickKey").html(quickHtml);
                    $('#quickKey [data-rel=tooltip]').tooltip();

                    $("#quickKey button").on("click",function(){
                        obj.addTabPageFun($(this).attr("id"),$(this).attr("data-original-title"),$(this).attr("rel"));
                    });
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        /**  绑定右键和双击菜单事件 */
        obj.tabToolInitFun = function(){
            //鼠标右键显示工具栏
            $("#portal_tabs").delegate("li","contextmenu",function(event){
                //移除选中样式
                $("#portal_tabs li").removeClass("active");
                $("#portal_tab_content div").removeClass("active");

                //选中当前tab
                $(this).addClass("active");
                $($(this).find("a").attr("href")).addClass("active");
                obj.selectMenuFun($(this).find("a").attr("menuid"));

                //设置工具栏位置
                var leftPosition = $(this).offset().left - $("#sidebar").width(),
                    topPostion = $(this).offset().top - $("#navbar").height() ;
                $("#tabtool").css({left:leftPosition + "px",top:topPostion + "px"}).slideDown("fast");

                obj.tabToolsFun("show");
                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });

            //鼠标双击显示工具栏
            $("#portal_tabs").delegate("li","dblclick",function(){
                var leftPosition = $(this).offset().left - $("#sidebar").width(),
                    topPostion = $(this).offset().top - $("#navbar").height() ;

                //设置工具栏位置
                $("#tabtool").css({left:leftPosition + "px",top:topPostion + "px"}).slideDown("fast");

                obj.tabToolsFun("show");
            });

            //点击其他地方关闭工具栏
            $(document).delegate("#tabToolsShade","click",function(){
                obj.tabToolsFun("hide");
            });
        };

        /**  工具栏菜单事件 */
        obj.tabToolEvent = function() {
            //批量关闭tab
            var batchCloseTab = function(tabs){
                $.each(tabs,function(index,object){
                    if($(object).attr("id") != "tab_home"){
                        $("#portal_tab_content "+ $(object).find("a").attr("href")).remove();
                        $(object).remove();
                    }
                });
            }

            // 刷新
            $('#tabtool_refresh').click(function() {
                obj.tabReloadFun();
                obj.tabToolsFun("hide");
            });

            // 关闭当前
            $('#tabtool_close').click(function() {
                //当前选中的tab
                var currentSelectedTab = $("#portal_tabs .active>a").parent();

                obj.closeCurrentTab(currentSelectedTab);

                obj.tabToolsFun("hide");

                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });

            // 全部关闭
            $('#tabtool_closeall').click(function() {

                batchCloseTab($('#portal_tabs li'));

                $("#tab_home").addClass("active");
                $("#tab_content_home").addClass("active");

                obj.tabToolsFun("hide");
            });

            // 关闭除当前之外的TAB
            $('#tabtool_closeother').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();

                batchCloseTab($(currentSelectedTab).prevAll());
                batchCloseTab($(currentSelectedTab).nextAll());

                obj.tabToolsFun("hide");
            });

            // 关闭当前右侧的TAB
            $('#tabtool_closeright').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();

                batchCloseTab($(currentSelectedTab).nextAll());

                obj.tabToolsFun("hide");
            });

            // 关闭当前左侧的TAB
            $('#tabtool_closeleft').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();

                batchCloseTab($(currentSelectedTab).prevAll());

                obj.tabToolsFun("hide");
            });

            // 退出
            $("#tabtool_exit").click(function() {
                obj.tabToolsFun("hide");
            });
        }

        /** 工具栏显示和隐藏 **/
        this.tabToolsFun = function(action){
            if(action == "show"){
                $('body').append('<div class="layui-layer-shade" id="tabToolsShade" style="z-index:2015; background-color:#000;opacity: 0.1; filter:alpha(opacity=20);"></div>');
                $("#tabtool").show();
            }else if(action == "hide"){
                $("#tabToolsShade").remove();
                $("#tabtool").hide("slow");
            }
        }
    }

    // 初始化数据
    $(document).ready(function() {
        index.init();
    });
})();
var index = new sys.index();

