/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("xqsight.cms");

(function(){
    xqsight.cms.forumManage = function(){

        var ctxData = saicfc.utils.getServerPath("cms");

        /**
         * 申明内部对象
         * @type {xqsight.chronic}
         */
        var obj = this;


        /**
         * 初始化调用 function
         */
        this.init = function() {
            obj.formSetValue();
            obj.loadComentFun();
        };

        /**
         * 取消 function
         */
        this.cancelFun = function(){
            saicfc.win.close();
        };

        /**
         * form 表单初始化数据
         */
        this.formSetValue = function(){
            var articleId = $.getUrlParam("articleId");
            if(articleId== undefined || articleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/forum/querybyid?articleId=" + articleId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        $("#articleComent").html(retData.data.articleContent);
                    }
                }
            });
        }


        /**
         * 删除 function
         */
        this.delFun = function(commentId){
            saicfc.win.confirm("确认删除吗？",function(btn){
                if(btn == "yes"){
                    $.ajax({
                        "url": ctxData + "/cms/comment/delete?date=" + new Date().getTime(),
                        "data": {"commentId":commentId},
                        "dataType": "jsonp",
                        "cache": false,
                        "success": function(retData){
                            saicfc.win.alert(retData.msg,retData.status);
                            if(retData.status == "0"){
                                obj.loadComentFun();
                            }
                        }
                    });
                }
            });
        }

        /**
         * 加载评论
         */
        this.loadComentFun = function(){
            var articleId = $.getUrlParam("articleId");
            if(articleId== undefined || articleId =="" ){
                return;
            }
            $.ajax({
                "dataType": "jsonp",
                "cache": false,
                "url": ctxData + "/forum/querycommentbyarticleid?articleId=" + articleId + "&date=" + new Date().getTime,
                "success": function(retData){
                    if(retData.status == "0"){
                        var commentHtml = "";
                        $.each(retData.data,function(index,object){
                            commentHtml += '<div class="profile-activity clearfix"><div>';
                            commentHtml += '<img class="pull-left" alt="' + object.userName + '" src="' + ctxData + object.imgUrl + '">';
                            commentHtml += object.comment;
                            commentHtml += '<div class="time"><i class="ace-icon fa fa-clock-o bigger-110"></i>';
                            commentHtml += saicfc.moment.formatYMDHms(object.createTime);
                            commentHtml += '</div></div>';
                            commentHtml += '<div class="tools action-buttons">';
                            commentHtml += '<a href="javascript:forumManage.delFun(\'' + object.commentId + '\')" class="red">';
                            commentHtml += '<i class="ace-icon fa fa-times bigger-125"></i></a>'
                            commentHtml += '</div></div>';
                        });
                        $("#comment").html("").html(commentHtml);

                        parent.saicfc.common.setIframeHeight($.getUrlParam(saicfc.iframeId));
                        
                    }
                }
            });
        }
    
        /**
         * 加载图片
         */
        this.loadImgFun = function(fileId){
        	 $.ajax({
                 "dataType": "jsonp",
                 "cache": false,
                 "url": ctxData + "/file/manage/query?fileId=" + fileId + "&date=" + new Date().getTime,
                 "success": function(retData){
                     if(retData.status == "0"){
                    	  obj.showPicFun(retData.data)
                     }
                 }
             });
        }

        /**
         * 渲染图片
         */
        this.showPicFun = function(data){
        	var showPic = "";
        	$.each(data,function(index,object){
        		showPic += "<li id='" + object.fileId + "'><a href='" + ctxData + object.fileUrl + "' data-rel='colorbox' class='cboxElement'>";
        		showPic += '<img width="120" height="120" alt="120x120" src="' + ctxData + object.fileUrl + '">';
        		showPic += '<div class="text"><div class="inner">' + object.fileName + '</div></div></a>';
        		showPic += '<div class="tools tools-bottom in"><a href="javascript:void(0);">';
        		showPic += '<i class="ace-icon fa fa-times red" onclick="javascript:articleManage.picDeleteFun(' + object.fileId + ');"></i></a></div></li>';
        	})

        	$("#picShow").append(showPic);
        }

        /**
         * 删除图片
         */
        this.picDeleteFun = function(fileId){
        	 $.ajax({
                 "dataType": "jsonp",
                 "cache": false,
                 "url": ctxData + "/file/manage/delete?fileId=" + fileId + "&date=" + new Date().getTime,
                 "success": function(retData){
                	 saicfc.win.alert(retData.msg,retData.status);
                	 if(retData.status == "0"){
                		 editarticle.fileId = saicfc.utils.replaceAll(editarticle.fileId,fileId+",","");
                		 $("#picShow #" + fileId).remove();
                	 }

                 }
             });
        }
    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        forumManage.init();
    });
})();

var forumManage = new xqsight.cms.forumManage();





