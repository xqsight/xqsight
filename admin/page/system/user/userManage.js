/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("sys.user");

var layIndex;

(function () {
    sys.user.userManage = function () {

        var ctxData = xqsight.utils.getServerPath("system");

        /**
         * 申明内部对象
         * @type {xqsight.pmpf}
         */
        var obj = this;

        /**
         * 初始化调用 function
         */
        this.init = function () {
            laydate({elem: '#userBorn', format: 'YYYY-MM-DD'});
            //绑定事件
            $("#btn_save").bind("click", obj.validateFun);
            $("#btn_cancel").bind("click", obj.cancelFun);
            //归属公司
            $("#companyShow").ComboBoxTree({
                url: ctxData + "/sys/office/tree?filter_EQI_office_type=1",
                description: "==请选择==",
                height: "195px",
                allowSearch: false,
                callback:function(data){
                    $("#companyId").val(data.id);
                    $("#officeIdDiv").show();
                }
            });
            //
            $("#officeShow").ComboBoxTree({
                url: ctxData + "/sys/office/tree?filter_EQI_office_type=2",//&filter_EQI_parent_id=",
                description: "==请选择==",
                height: "195px",
                allowSearch: false,
                callback:function(data){
                    $("#officeId").val(data.id);
                }
            });

            $("#btn-upload-pic").on("click", function () {
                layIndex = layer.open({
                    type: 2,
                    title: '<i class="ace-icon fa fa-edit"></i>  选择图片',
                    shadeClose: false,
                    shade: 0.2,
                    shift: 1,
                    skin: "layui-layer-molv",
                    moveOut: true,
                    offset: "10px",
                    maxmin: true, //开启最大化最小化按钮
                    area: [$(window).width() - 500 + 'px', $(window).height() - 100 + 'px'],
                    content: "../../component/cropper/cropper.html"
                })
            });
            obj.formSetValue();
        };

        /**
         * 验证 function
         */
        this.validateFun = function () {
            $("#userForm").html5Validate(function () {
                obj.saveFun();
            }, {
                validate: function () {
                    return true;
                }
            });
        };

        /**
         * 保存 function
         */
        this.saveFun = function () {
            var id = $.getUrlParam("id");

            xqsight.utils.put({url:ctxData + "/sys/user/",data:$("#userForm").serializeArray(),pk:id,callFun:function (rep) {
                if($.getUrlParam("id") == undefined || $.getUrlParam("id") == ""){
                    xqsight.win.alert("您的默认密码是:!password");
                }
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.userMain.editCallBackFun({"userId": $.getUrlParam("id")});
                xqsight.win.close();
            }})
        };

        /**
         * 取消 function
         */
        this.cancelFun = function () {
            xqsight.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function () {
            var id = $.getUrlParam("id");
            if (id == undefined || id == "") {
                return;
            }

            xqsight.utils.load({url:ctxData + "/sys/user/"+id,callFun:function (rep) {
                xqsight.utils.fillForm("userForm",rep.data);
                $("#companyShow").ComboBoxTreeSetValue(rep.data.companyId);
                $("#officeShow").ComboBoxTreeSetValue(rep.data.officeId);
                $("#userImage").attr("src", rep.data.imgUrl)
            }})
        }

    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        userManage.init();
    });
})();

var userManage = new sys.user.userManage();

var _imgCallBack = function (data) {
    $("#imgUrl").val(data.url);
    $("#userImage").attr("src", data.url);
    layer.close(layIndex)
}



