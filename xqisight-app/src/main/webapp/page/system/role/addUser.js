/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("sys.role");

(function(){
    sys.role.addUser = function(){

        var ctxData = saicfc.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {saicfc.pmpf}
         */
        var obj = this;

        /**
         * 初始化调用 function
         */
        this.init = function() {

            obj.loadRoleDataFun();

            $("#roleName").change(function(){
                obj.loadUserDataFun( $("#roleName").val());
            });

            //绑定事件
            $("#btn_save").bind("click",obj.validateFun);
            $("#btn_cancel").bind("click",obj.cancelFun);

        };

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function(){
            var params = "&roleId=" + $("#roleName").val();
            $("#userList>option").each(function(index, item){
                if($(item).prop('selected'))
                    params += "&id=" + $(item).attr("value");;
            })
            return params;
        };

        /**
         * 验证 function
         */
        this.validateFun = function(){
            $("#userRoleForm").html5Validate(function() {
                obj.saveFun();
            }, {
                validate : function() {
                    if($("#roleName").val() == ""){
                        $("#roleName").testRemind("请选择用户角色");
                        return false;
                    }
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function(){
            var callback = function(btn){
                if(btn == "yes"){
                    var url = ctxData + "/sys/auth/saveuserrole?date=" + new Date().getTime();
                    var index = saicfc.progress.loading();
                    $.ajax({
                        "url": url ,
                        "data":  obj.setParamFun(),
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            saicfc.progress.removeLoading(index);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
            };
            saicfc.win.confirm("确认提交吗？",callback);
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            saicfc.win.close();
        };

        /**
         * 加载 角色 function
         */
        this.loadRoleDataFun = function(){
            var roleId = $.getUrlParam("roleId");

            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                url:  ctxData + "/sys/role/queryall",
                success: function(objMsg){
                    if(objMsg.status == "0"){
                        $.each(objMsg.data,function(index,role){
                            var _pinyin = " ";
                            if(typeof(pinyin) == "function" || typeof(pinyin) == "object")
                                _pinyin = pinyin.getCamelChars(role.roleName);

                            if(roleId != undefined && roleId == role.roleId ){
                                $("#roleName").append("<option data-tokens='" + _pinyin + "' value='" + role.roleId +"' selected>" + role.roleName + "</option>");
                            }else{
                                $("#roleName").append("<option data-tokens='" + _pinyin + "' value='" + role.roleId +"'>" + role.roleName + "</option>");
                            }
                        });
                        $('#roleName').selectpicker('refresh');
                        //加载 用户信息
                        obj.loadUserDataFun(roleId);
                    }
                }
            });
        }

        /**
         * 加载用户信息 function
         */
        this.loadUserDataFun = function(roleId){
            $.ajax({
                type: "POST",
                dataType : 'jsonp',
                url:  ctxData + "/sys/auth/queryauthuser?roleId="+roleId,
                success: function(objMsg){
                    if(objMsg.status == "0"){
                        $("#userList option").remove();
                        $("#bootstrap-duallistbox-selected-list_userList option").remove();
                        $.each(objMsg.data,function(index,user){

                            var _pinyin = " ";
                            if(typeof(pinyin) == "function" || typeof(pinyin) == "object")
                                _pinyin = pinyin.getCamelChars(user.userName);

                            if(user.isSelected == "0"){
                                $("#userList").append("<option filter_value='" + _pinyin + "' value='" + user.id +"' selected='selected'>" + user.userName +"(" +  user.loginId + ")</option>");
                            }else{
                                $("#userList").append("<option filter_value='" + _pinyin + "' value='" + user.id +"'>" + user.userName +"(" +  user.loginId + ")</option>");
                            }
                        });
                        var userList = $("#userList").bootstrapDualListbox({filterPlaceHolder: '过滤',infoTextEmpty: '没有用户', filterTextClear:"展示所有",infoText: '用户数量 {0}',infoTextFiltered: '<span class="label label-purple label-lg">过滤</span>'});
                        var container1 = userList.bootstrapDualListbox('getContainer');
                        container1.find('.btn').addClass('btn-white btn-info btn-bold');
                    }
                }
            });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        addUser.init();
    });
})();

var addUser = new sys.role.addUser();





