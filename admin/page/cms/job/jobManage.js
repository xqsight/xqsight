/**
 * Created by user on 2015/12/14.
 */

xqsight.nameSpace.reg("xqsight.cms");

(function () {
    xqsight.cms.jobManage = function () {
        var ctxData = xqsight.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.cms}
         */
        var obj = this;

        var jobEditor = {};
        var positionEditor = {};

        /**
         * 初始化调用 function
         */
        this.init = function () {
            //laydate({elem: '#jobStartTime', istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
            //laydate({elem: '#jobEndTime', istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
            //绑定事件
            $("#btn_save").bind("click", obj.validateFun);
            $("#btn_cancel").bind("click", obj.cancelFun);
            obj.editorFun();
            obj.formSetValue();
        };

        this.editorFun = function () {
            jobEditor = new wangEditor("jobNeed");
            jobEditor.config.printLog = false;
            jobEditor.config.menus = [
                'bold', 'underline', 'italic',  'strikethrough', 'eraser', 'quote', 'fontfamily', 'fontsize',
                'head', 'unorderlist', 'orderlist', 'alignleft', 'aligncenter', 'alignright', 'link', 'unlink'
            ];
            jobEditor.create();

            positionEditor = new wangEditor("positionDesp");
            positionEditor.config.printLog = false;
            positionEditor.config.menus = [
                'bold', 'underline', 'italic',  'strikethrough', 'eraser', 'quote', 'fontfamily', 'fontsize',
                'head', 'unorderlist', 'orderlist', 'alignleft', 'aligncenter', 'alignright', 'link', 'unlink'
            ];
            positionEditor.create();
        }

        /**
         * 设置参数 function
         * @returns {string}
         */
        this.setParamFun = function () {
            editJob.jobName = $("#jobName").val();
           // editJob.jobStartTime = $("#jobStartTime").val();
            //editJob.jobEndTime = $("#jobEndTime").val();
           // editJob.jobPhone = $("#jobPhone").val();
            editJob.jobEmail = $("#jobEmail").val();
            editJob.active = $("#active").val();
            editJob.positionDesp =  encodeURIComponent(positionEditor.$txt.html());
            editJob.jobNeed = encodeURIComponent(jobEditor.$txt.html());
            editJob.jobDepartment = $("#jobDepartment").val();
            //editJob.jobContent = encodeURIComponent(jobEditor.$txt.html());
            editJob.jobType = $("#jobType").val();
        };

        /**
         * 验证 function
         */
        this.validateFun = function () {
            $("#jobForm").html5Validate(function () {
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
            var jobId = $.getUrlParam("jobId");
            xqsight.utils.put({url: ctxData + "/cms/job/",data:$("#jobForm").serializeArray(),pk:jobId,callFun:function (rep) {
                var iframeContent = xqsight.tab.getCurrentIframeContent();
                iframeContent.jobMain.editCallBackFun({"jobId": jobId});
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
            var jobId = $.getUrlParam("jobId");
            if (jobId == undefined || jobId == "") {
                $("#positionId").val($.getUrlParam("positionId"));
                return;
            }
            xqsight.utils.load({url:ctxData + "/cms/job/" + jobId ,callFun:function (rep) {
                xqsight.utils.fillForm("jobForm",rep.data);
                jobEditor.$txt.html(rep.data.jobNeed);
                positionEditor.$txt.html(rep.data.positionDesp);
            }})
        };
    };

    /**
     * 初始化数据
     */
    $(document).ready(function () {
        jobManage.init();
    });
})();

var jobManage = new xqsight.cms.jobManage();