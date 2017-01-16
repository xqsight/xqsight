/**
 * Created by user on 2015/12/14.
 */

saicfc.nameSpace.reg("saicfc.pmpf");

(function(){
    saicfc.pmpf.menuMain = function(){
        var ctxData = saicfc.utils.getServerPath("system");

        var obj = this;

        this.init = function(){
            obj.loadMenuTableFun();
        }

        var newNodes = [{id:0, pId:"", name:"系统菜单", open:true}];

        /**
         * 加载数据表 function
         */
        this.loadMenuTableFun = function(){
            $.ajax({
                "dataType": "jsonp",
                "url": ctxData + "/sys/menu/querymenu?date="+new Date().getTime(),
                "success": function(retData){
                    obj.dataProductFun(retData.data);
                    obj.initTreeSelectFun();
                }
            });
        }

        /**
         * 数据处理
         * @param nodes
         */
        this.dataProductFun = function(nodes){
            $.each(nodes,function(index,node){
                if(node.children.length > 0){
                    newNodes.push({id:node.menuId, pId:node.parentId, name:node.menuName,open : true});
                }else{
                    newNodes.push({id:node.menuId, pId:node.parentId, name:node.menuName});
                }
                if(node.children.length > 0){
                    obj.dataProductFun(node.children);
                }
            });
        }

        /**
         * 初始化树控件
         */
        this.initTreeSelectFun = function(){
            var setting = {
                check: {
                    enable: false,
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };

            $.fn.zTree.init($("#menuTree"), setting, newNodes);
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getNodes();
            if (nodes.length>0) {
                treeObj.selectNode(nodes[0]);
            }
        }



    };

    /**
     * 初始化数据
     */
    $(document).ready(function() {
        menuMain.init();
    });
})();
var menuMain = new saicfc.pmpf.menuMain();





