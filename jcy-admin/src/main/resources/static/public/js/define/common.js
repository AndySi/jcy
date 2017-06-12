/**
 * Created by ZhangSi on 2017/4/27 0027.
 */
(function ($) {
    $.extend({
        /**
         * handlebars模块引擎
         * @param scriptId  模板ID
         * @param data  json数据
         * @param el    绑定元素ID
         */
        handlebars: function (scriptId, data, el) {
            //用jquery获取模板
            var tpl = $(scriptId).html();
            //预编译模板
            var template = Handlebars.compile(tpl);
            //匹配json内容
            var html = template(data);
            //输入模板
            $(el).html(html);
        }
    });

    //iframe自适应
    $(window).on('resize', function () {
    }).resize();
})(jQuery);

//重写alert
window.alert = function (msg, callback) {
    parent.layer.alert(msg, function (index) {
        parent.layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        parent.layer.alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        parent.layer.alert("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        parent.layer.alert("只能选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}

function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

/**
 * 询问框
 * @param msg
 * @param callback
 */
function fn_confirm(msg, callback) {
    parent.layer.confirm(msg, {
        btn: ['是', '否'], //按钮
        title: '提示'
    }, function () {
        callback();
    });
}
/**
 * tip提示
 * @param msg 提示信息
 * @param ele 绑定元素ID
 * @param type 方向
 * @param color 颜色
 * @param time 时间
 */
function fn_tips(msg, ele, type, color, time) {
    time = (time == null || time == '' || typeof (time) == 'undefined') ? "1000" : time;
    color = (color == null || color == '' || typeof (color) == 'undefined') ? "#1ABC9C" : color;
    layer.tips(msg, ele, {
        tips: [type, color],
        time: time
    });
}
