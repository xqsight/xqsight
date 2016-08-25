// 设置图片合并的最小间隔
//fis.config.set('settings.spriter.csssprites.margin', 20);

//开启autoCombine可以将零散资源进行自动打包
//fis.config.set('settings.postpackager.simple.autoCombine', true);

var now = new Date();
fis.config.set('timestamp', [now.getFullYear(), now.getMonth()+1, now.getDate(), now.getHours()].join(''));

//设置输出的domain路径  
fis.config.set("roadmap.domain", "http://xqsight.cn/manage"); //配置domain
//fis.config.set("roadmap.domain", "http://127.0.0.1:8080"); //配置domain

// 取消下面的注释开启simple插件，注意需要先进行插件安装 npm install -g fis-postpackager-simple
fis.config.set('modules.postpackager', 'simple');

//设置文件打包为时间戳
fis.config.set('roadmap.path', [
    {
        reg: /.*\.(js)$/,
        query: '?t=${timestamp}',
        //useSprite: true
        useHash: false
    },{
        reg: /.*\.(css)$/,
        query: '?t=${timestamp}',
        //useSprite: true
        useHash: false
    },{
        reg: /.*\.(png|jpg|gif)$/,
        query: '?t=${timestamp}',
        //useSprite: true
        useHash: false
    },{
        reg: /.*\.(json)$/,
        //useSprite: true
        useHash: false
    },{
        reg: '**.html',
        useCache: false
    }
]);

fis.config.merge({
    roadmap : {
        path : [{
                //所有的js文件
                reg : 'page/**.js',
                //发布到/static/js/xxx目录下
                release : '/static/js$&'
                //访问url是/mm/static/js/xxx
                // url : '/mm/static/js$&'
            },{
                //所有的js文件
                reg : '**.js'
                //发布到/static/js/xxx目录下
                //release : '/static/js$&'
                //访问url是/mm/static/js/xxx
               // url : '/mm/static/js$&'
            }, {
                //所有的css文件
                reg : '**.css'
                //发布到/static/css/xxx目录下
                //release : '/static/css$&'
                //访问url是/pp/static/css/xxx
               // url : '/pp/static/css$&'
            }, {
                //所有image目录下的.png，.gif文件
                reg : /^\/images\/(.*\.(?:png|gif))/i
                //发布到/static/pic/xxx目录下
                //release : '/static/pic/$1'
                //访问url是/oo/static/baidu/xxx
               // url : '/oo/static/baidu$&'
            }
        ]
    }
});

// 取消下面的注释设置打包规则
fis.config.set('pack', {
    'static/js/pkg/pkg.h5forie8.js': [
        'static/ace/js/html5shiv.js',
        'static/ace/js/respond.js'
    ],
    'static/js/pkg/pkg.bootstrap.ace.pub.js': [
        'static/ace/js/bootstrap.js',
        'static/ace/js/ace.js',
        'static/js/pub.js',
        'static/js/pinyin.js',
        'static/js/format-number.js',
        'static/bootstrap-select/js/bootstrap-select.js',
        'static/bootstrap-select/js/i18n/defaults-zh_CN.js',
        'static/ace/js/date-time/moment.js'
    ],
    'static/js/pkg/pgk.jquery.dataTables.bootstrap.js': [
        'static/ace/js/dataTables/jquery.dataTables.js',
        'static/ace/js/dataTables/jquery.dataTables.bootstrap.js'
    ],
    'static/js/pkg/pgk.daterangepicker.js': [
        'static/ace/js/date-time/bootstrap-datepicker.js',
        'static/ace/js/date-time/daterangepicker.js'
    ],
    'static/js/pkg/pgk.fileinput.js': [
        'static/fileinput/js/plugins/canvas-to-blob.js',
        'static/fileinput/js/fileinput.js',
        'static/fileinput/js/fileinput_locale_zh.js'
    ],
    'static/js/pkg/pgk.zoomimage.js': [
        'static/zoomimage/js/eye.js',
        'static/zoomimage/js/support.js',
        'static/zoomimage/js/zoomimage.js',
        'static/zoomimage/js/layout.js'
    ],
    'static/js/pkg/pgk.kindeditor.js': [
        'static/kindeditor/kindeditor.js',
        'static/kindeditor/lang/zh_CN.js'
    ],
    /*'static/js/pkg/pgk.bootstrap.select.jquery.js': [
        'static/bootstrap-select/js/bootstrap-select.js',
        'static/bootstrap-select/js/i18n/defaults-zh_CN.js',
        'static/js/format-number.js',
        'static/ace/js/date-time/moment.js'
    ],*/
    'static/js/pkg/pgk.bootstrap.ace.editable.js': [
        'static/ace/js/jquery.gritter.js',
        'static/ace/js/ace/elements.fileinput.js',
        'static/ace/js/x-editable/bootstrap-editable.js',
        'static/ace/js/x-editable/ace-editable.js'
    ],
    /*'static/js/pkg/pgk.layer.js': [
        'static/layer/layer.js',
        'static/layer/extend/layer.ext.js'
    ],*/
    // 取消下面的注释设置CSS打包规则，CSS打包的同时会进行图片合并
    'static/css/pkg/pkg.bootstrap.css': [
        'static/ace/css/bootstrap.css',
        'static/ace/css/font-awesome.css',
        'static/bootstrap-select/css/bootstrap-select.css'
    ],
    // 取消下面的注释设置CSS打包规则，CSS打包的同时会进行图片合并
    'static/css/pkg/pkg.ace.common.css': [
        'static/css/common.css',
        'static/ace/css/ace-fonts.css',
        'static/ace/css/ace.css'
    ],
    'static/css/pkg/pkg.zoomimage.css': [
        'static/zoomimage/css/zoomimage.css',
        'static/zoomimage/css/custom.css'
    ]
});
