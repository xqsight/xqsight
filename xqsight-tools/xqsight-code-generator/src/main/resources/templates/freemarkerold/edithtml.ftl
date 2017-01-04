<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>${table.remarks}</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fonts -->
    <link rel="stylesheet" href="../../../static/ace/css/bootstrap.css" />
    <link rel="stylesheet" href="../../../static/ace/css/font-awesome.css" />

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="../../../static/ace/css/datepicker.css" />

    <!-- ace styles & fonts & common-->
    <link rel="stylesheet" href="../../../static/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
    <link rel="stylesheet" href="../../../static/ace/css/ace-fonts.css" />
    <link rel="stylesheet" href="../../../static/css/common.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../../static/ace/css/ace-part2.css" class="ace-main-stylesheet"/>
    <![endif]-->
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../../static/ace/css/ace-ie.css"/>
    <![endif]-->

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="../../../static/ace/js/html5shiv.js"></script>
    <script src="../../../static/ace/js/respond.js"></script>
    <![endif]-->
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>
    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-md-12 col-xs-12">
                        <div class="widget-box transparent">
                            <div class="widget-header widget-header-flat">
                                <h4 class="widget-title light-grey">
                                    <i class="ace-icon fa fa-filter"></i>
                                    过滤
                                </h4>
                                <div class="widget-toolbar">
                                    <a href="javascript:void(0);" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-10">
                                    <!-- start condition -->
                                    <div class="row">
                                        <div class="col-sm-12 con-filter">
                                            <ul class="list-unstyled list-inline">
                                                <span>标题:</span>
                                                <li>
                                                    <input type="text" class="input-xlarge" id="articleTitle" placeholder="标题"/>
                                                </li>
                                                <li class="pull-right">
                                                    <button id="btn-query" type="button" data-rel="tooltip" title="查询" class="btn btn-white btn-default btn-round">
                                                        <i class="ace-icon fa fa-search"></i>查询
                                                    </button>
                                                    <button id="btn-reset" type="button" data-rel="tooltip" title="重置" class="btn btn-white btn-default btn-round">
                                                        <i class="ace-icon fa fa-undo"></i>重置
                                                    </button>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-12 hr hr12 hr-dotted"></div>
                                    </div>
                                    <!-- end condition -->
                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div>
                    </div>

                    <div class="col-md-12 tipshow">
                        <button class="btn btn-white btn-default btn-round" data-rel="tooltip" id="btn_new" title="新增">
                            <i class="ace-icon fa fa-plus"></i>
                        </button>
                        <button class="btn btn-white btn-default btn-round" data-rel="tooltip" id="btn_del" title="删除">
                            <i class="ace-icon fa fa-remove"></i>
                        </button>
                    </div>
                    <div class="col-md-12">
                        <table id="article-table" class="table table-striped table-bordered table-hover">
                            <thead>
                                <th class="left">
                                    <label class="pos-rel">
                                        <input type="checkbox" name="checkall" class="ace" />
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">标题</th>
                                <th class="center">登录名称</th>
                                <th class="center">登录类型</th>
                                <th class="center">图片</th>
                                <th class="center">是否锁定</th>
                                <th class="center">创建时间</th>
                                <th class="center">备注</th>
                                <th class="center">重置密码</th>
                            </thead>
                        </table>
                    </div>

                </div><!-- /.col -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->
<!--[if !IE]> -->
<script src="../../../static/ace/js/jquery.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="../../../static/ace/js/jquery1x.js"></script>
<![endif]-->

<script src="../../../static/ace/js/bootstrap.js"></script>
<script src="../../../static/ace/js/ace.js"></script>
<script src="../../../static/js/pub.js"></script>

<!-- page specific plugin scripts -->
<!--datatables组件-->
<script src="../../../static/ace/js/dataTables/jquery.dataTables.js"></script>
<script src="../../../static/ace/js/dataTables/jquery.dataTables.bootstrap.js"></script>

<script src="userMain.js"></script>
</body>
</html>
