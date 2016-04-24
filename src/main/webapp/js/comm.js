//通用ajax调用
var request = {
    ajax: function (method, url, param, successFn, sync) {
        $.ajax({
            type: method,
            url: '/book/' + url,
            cache: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(param),
            sync: sync == true ? true : false,
            success: function (data) {
                successFn(data);
            },
            error: function () {
                console.log(method + "请求" + url + "异常！");
            }
        });
    },



//初始化fileinput控件（第一次初始化）
inputfile:function initFileInput(ctrlName, uploadUrl, successFn) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: '/book'+ uploadUrl, //上传的地址
            allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
            showUpload: true    , //是否显示上传按钮
            showCaption:true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            showPreview:false,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });

    // 上传成功
    control.on("fileuploaded", function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;

        successFn(response);
        console.log('File uploaded triggered');
    });
    control.on('fileuploaderror', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log(data);
        console.log('File upload error');
    });

    control.on('fileerror', function(event, data) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
    });

    },


    ajaxFileUpload: function (url, param, fileElementId, successFn) {
        $.ajaxFileUpload({
            url: '/book/' + url,
            data: param,
            fileElementId: fileElementId,
            dataType: 'json',
            secureuri: false,
            success: function (data) {
                successFn(data);
            },
            error: function (data) {
                console.log("请求" + url + "异常！");
            }
        });
    },

    json_format: function (txt, compress/*是否为压缩模式*/) {/* 格式化JSON源码(对象转换为JSON文本) */
        var indentChar = '    ';
        if (/^\s*$/.test(txt)) {
            //alert('数据为空,无法格式化! ');
            return;
        }
        try {
            var data = eval('(' + txt + ')');
        }
        catch (e) {
            //alert('数据源语法错误,格式化失败! 错误信息: ' + e.description, 'err');
            return null;
        }
        ;
        var draw = [], last = false, This = this, line = compress ? '' : '\n', nodeCount = 0, maxDepth = 0;

        var notify = function (name, value, isLast, indent/*缩进*/, formObj) {
            nodeCount++;
            /*节点计数*/
            for (var i = 0, tab = ''; i < indent; i++)tab += indentChar;
            /* 缩进HTML */
            tab = compress ? '' : tab;
            /*压缩模式忽略缩进*/
            maxDepth = ++indent;
            /*缩进递增并记录*/
            if (value && value.constructor == Array) {/*处理数组*/
                draw.push(tab + (formObj ? ('"' + name + '":') : '') + '[' + line);
                /*缩进'[' 然后换行*/
                for (var i = 0; i < value.length; i++)
                    notify(i, value[i], i == value.length - 1, indent, false);
                draw.push(tab + ']' + (isLast ? line : (',' + line)));
                /*缩进']'换行,若非尾元素则添加逗号*/
            } else if (value && typeof value == 'object') {/*处理对象*/
                draw.push(tab + (formObj ? ('"' + name + '":') : '') + '{' + line);
                /*缩进'{' 然后换行*/
                var len = 0, i = 0;
                for (var key in value)len++;
                for (var key in value)notify(key, value[key], ++i == len, indent, true);
                draw.push(tab + '}' + (isLast ? line : (',' + line)));
                /*缩进'}'换行,若非尾元素则添加逗号*/
            } else {
                if (typeof value == 'string')value = '"' + value + '"';
                draw.push(tab + (formObj ? ('"' + name + '":') : '') + value + (isLast ? '' : ',') + line);
            }
            ;
        };
        var isLast = true, indent = 0;
        notify('', data, isLast, indent, false);
        return draw.join('');
    }

};
var url_util = {
    //获取链接URL中的某个key的值
    queryString: function (val) {
        var uri = window.location.search;
        var re = new RegExp("" + val + "\\=([^\\&\\?]*)", "ig");
        return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
    }
};