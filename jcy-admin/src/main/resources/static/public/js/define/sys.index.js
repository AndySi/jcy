layui.config({
    base: 'public/js/lib/' //扩展组件目录
}).use(['layer', 'navtab'], function () {
    window.jQuery = window.$ = layui.jquery;
    window.layer = layui.layer;
    var element = layui.element(),
        navtab = layui.navtab({
            elem: '.larry-tab-box'
        });

    //iframe自适应
    $(window).on('resize', function () {
        var $content = $('#larry-tab .layui-tab-content');
        $content.height($(this).height() - 163);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
        tab_W = $('#larry-tab').width();
        // larry-footer：p-admin宽度设定
        var larryFoot = $('#larry-footer').width();
        $('#larry-footer p.p-admin').width(larryFoot - 300);
    }).resize();

    $('#larry-nav-side').click(function () {
        if ($(this).attr('lay-filter') !== undefined) {
            $(this).children('ul').find('li').each(function () {
                var $this = $(this);
                if ($this.find('dl').length > 0) {
                    var $dd = $this.find('dd').each(function () {
                        $(this).on('click', function () {
                            var $a = $(this).children('a');
                            var href = $a.data('url');
                            var icon = $a.children('i:first').data('icon');
                            var title = $a.children('span').text();
                            var data = {
                                href: href,
                                icon: icon,
                                title: title
                            }
                            navtab.tabAdd(data);
                        });
                    });
                } else {
                    $this.on('click', function () {
                        var $a = $(this).children('a');
                        var href = $a.data('url');
                        var icon = $a.children('i:first').data('icon');
                        var title = $a.children('span').text();
                        var data = {
                            href: href,
                            icon: icon,
                            title: title
                        }
                        navtab.tabAdd(data);
                    });
                }
            });
        }
    }).trigger("click");

    // larry-side-menu向左折叠
    $('.larry-side-menu').click(function () {
        var sideWidth = $('#larry-side').width();
        if (sideWidth === 200) {
            $('#larry-body').animate({
                left: '0'
            }); //admin-footer
            $('#larry-footer').animate({
                left: '0'
            });
            $('#larry-side').animate({
                width: '0'
            });
        } else {
            $('#larry-body').animate({
                left: '200px'
            });
            $('#larry-footer').animate({
                left: '200px'
            });
            $('#larry-side').animate({
                width: '200px'
            });
        }
    });

    // 刷新iframe操作
    $("#refresh_iframe").click(function () {
        $(".layui-tab-content .layui-tab-item").each(function () {
            if ($(this).hasClass('layui-show')) {
                $(this).children('iframe')[0].contentWindow.location.reload(true);
            }
        });
    });
});

var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li class="layui-nav-item">',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<em class="layui-nav-more"></em>',
        '</a>',
        '<dl v-if="item.type === 0" class="layui-nav-child">',
        '<dd v-for="item in item.list">',
        '<a v-if="item.type === 1" href="javascript:;" :data-url="item.url"><i v-if="item.icon != null" :class="item.icon" :data-icon="item.icon"></i> <span>{{item.name}}</span></a>',
        '</dd>',
        '</dl>',
        '<a v-if="item.type === 1" href="javascript:;" :data-url="item.url"><i v-if="item.icon != null" :class="item.icon" :data-icon="item.icon"></i> <span>{{item.name}}</span></a>',
        '</li>'
    ].join('')
});
//注册菜单组件
Vue.component('menuItem', menuItem);
var vm = new Vue({
    el: '#layui_layout',
    data: {
        user: {},
        menuList: {},
        main: 'sys/main.html',
        navTitle: '后台首页',
    },
    methods: {
        getUser: function () {
            $.getJSON('sys/user/info?_' + $.now(), function (r) {
                vm.user = r.user;
            });
        },
        getMenuList: function () {
            $.getJSON("sys/menu/user?_" + $.now(), function (r) {
                vm.menuList = r.menuList;
                window.permissions = r.permissions;
            });
        }
    },
    created: function () {
        this.getUser();
        this.getMenuList();
    }
});





