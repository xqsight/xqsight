/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.reconUpload = function(){

        var ctxData = saicfc.utils.getReconServerPath();

        // 申明内部对象
        var obj = this;

        this.reconManageTable = {};

        // 初始化页面
        this.init = function() {
            //时间范围日期选择
            $('.input-daterange').datepicker({autoclose:true});

            $("#btn-query").click(function(){
                obj.reconManageTable.ajax.reload();
            });

            $("#btn-fileUpload").bind("click",obj.fileUploadFun);

            $("#btn-reset").click(function(){
                saicfc.utils.cleanValue("searchDiv");
            });

            //加载表
            obj.loadRecordTableFun();
        };

        this.fileUpChangeFun = function(){
            if($("#fileToUpload").val() == "")
                return;
            var arr = $("#fileToUpload").val().split("\\");
            var name = arr[arr.length - 1];
            $("#fileShow").val(name);
        }

        //文件上传
        this.fileUploadFun = function(){
            var name = $("#fileShow").val();
            var ext = name.substr(name.lastIndexOf(".") +1);
            if(ext == "" || ext == undefined || (ext != "rar" && ext != "zip")){
                saicfc.win.alert("上传文件必须是zip或rar格式的文件");
                return;
            }
            saicfc.progress.show();
            $.ajaxFileUpload
            ({
                url:ctxData + "/recon/fileupload",
                fileElementId:'fileToUpload',
                dataType: 'jsonp',
                data:{name: new Date().getTime()},
                success: function (){
                    $("#fileShow").val("");
                    $("#fileToUpload").val("");
                    saicfc.progress.hide();
                    obj.reconManageTable.ajax.reload();
                },
                error: function (){
                    obj.reconManageTable.ajax.reload();
                    saicfc.progress.hide();
                    $("#fileShow").val("");
                    $("#fileToUpload").val("");
                }
            });
        }

        //加载数据表
        this.loadRecordTableFun = function(){
            var numNo = 1;
            var record_table = $("#reconUpload-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : saicfc.utils.getDataZh_chPath()
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 15,// 每页显示行数
                "iDisplayStart" : 1,
                "bSort" : false,
                "showRowNumber" : true,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : true,
                "sAjaxSource": ctxData + '/recon/query',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    var index = saicfc.progress.loading();
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            numNo = 1;
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                            saicfc.progress.removeLoading(index);
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "fnServerParams": function (aoData) {
                    aoData.push(
                        { "name": "startTime", "value": $("#startTime").val() },
                        { "name": "endTime", "value": $("#endTime").val() }
                    );
                },
                "aoColumns": [{
                    "data": "reconNo",
                    sWidth : "80",
                    sClass : "text-center",
                    sSort : false,
                    render : function(){
                        return numNo ++;
                    }
                },{
                    "data": "createTime",
                    sWidth : "200",
                    sClass : "text-center",
                    render : function(value){
                        return saicfc.moment.formatYMDHms(value);
                    }
                }, {
                    "data": "fileName",
                    sWidth : "180",
                    sClass : "text-center"
                },{
                    "data": "reconStatus",
                    sWidth : "120",
                    sClass : "text-center",
                    render : function(data){
                        if(data == "00"){
                            return "已导入";
                        }else if(data == "01"){
                            return "已校验";
                        }else if(data == "02"){
                            return "已作废";
                        }else if(data == "03"){
                            return "通过";
                        }else if(data == "04"){
                            return "已对账";
                        }
                    }
                },{
                    "data": "reconStatus",
                    sWidth : "300",
                    sClass : "text-center",
                    render : function(data,type,row){
                        var reconNo = row.reconNo;
                        if(data == "00"){//已导入
                            return "<div class='grey' style='font-weight: bold;'>"
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"check\")'>校验</a> | "
                                + "下载 | 通过 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"cancel\")'>撤销</a> | "
                                + " 对账"
                                + "</div> ";
                        }else if(data == "01"){//已校验
                            return  "<div class='grey' style='font-weight: bold;'> 校验 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"down\")'>下载</a> | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"pass\")'>通过</a> | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"cancel\")'>撤销</a> | "
                                + "对账</div> ";
                        }else if(data == "02"){//已作废
                            return "<div class='grey' style='font-weight: bold;'>校验 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"down\")'>下载</a> | "
                                + " 通过 | 撤销 | 对账 ";
                        }else if(data == "03"){//通过
                            return "<div class='grey' style='font-weight: bold;'> 校验 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"down\")'>下载</a> | "
                                + " 通过 | 撤销 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"recon\")'>对账</a> "
                        }else if(data == "04"){//已对账
                            return  "<div class='grey' style='font-weight: bold;'> 校验 | "
                                + "<a href='javaScript:reconUpload.reconHandlerFun(\"" + reconNo + "\",\"down\")'>下载</a> | "
                                + " 通过 | 撤销 | 对账 </div> ";
                        }
                    }
                }]
            });

            obj.reconManageTable = record_table;
        };

        //对账处理
        this.reconHandlerFun = function(reconNo,handlerType){
            var callback = function(btn){
                if(btn == "yes"){
                    var index = saicfc.progress.show();
                    $.ajax({
                        type: "POST",
                        dataType : 'jsonp',
                        url:  encodeURI(encodeURI(ctxData + url + "&date=" + new Date())),
                        success: function(objMsg){
                            saicfc.progress.hide(index);
                            if(objMsg.status == "0"){
                                obj.reconManageTable.ajax.reload();
                            }
                            saicfc.win.alert(objMsg.msg)
                        }
                    });
                }
            };

            var url = "";
            var msg = "处理";
            if(handlerType == "check"){//校验
                url = "/recon/check?reconNo=" + reconNo;
                msg = "校验";
            }else if(handlerType == "pass"){//通过
                url = "/recon/updstatus?reconNo=" + reconNo + "&reconStatus=03";
                msg = "通过";
            }else if(handlerType == "cancel"){//撤销
                url = "/recon/updstatus?reconNo=" + reconNo + "&reconStatus=02";
                msg = "撤销";
            }else if(handlerType == "recon"){//对账
                url = "/recon/deal?reconNo=" + reconNo;
                msg = "对账";
            }else if(handlerType == "down"){//下载
                obj.downLoadFun(reconNo);
                return;
            }
            saicfc.win.confirm("确认" + msg + "吗？",callback);
        }

        //下载
        this.downLoadFun = function(reconNo){
            var url = "/recon/download?reconNo=" + reconNo
            saicfc.utils.createFromAndSubmit(ctxData + url);
        }

    };

    // 初始化数据
    $(document).ready(function() {
        reconUpload.init();
    });
})();

var reconUpload = new saicfc.pmpf.reconUpload();





