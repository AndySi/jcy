$(function () {
    $("#jqGrid").jqGrid({
        url: '../product/list',
        datatype: "json",
        styleUI: "Bootstrap",
        width: 1000,
        responsive: true,
        formtype: 'vertical',
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '产品名称', name: 'name', index: 'name', width: 80},
            {label: '产品类型', name: 'typeName', index: 'type_Name', width: 80},
            {label: '产品图片', name: 'picture', index: 'picture', width: 80, formatter: imgFormatter, align: 'center'},
            {label: '产品详情', name: 'details', index: 'details', width: 80},
            {label: '更新时间', name: 'updateTime', index: 'update_time', width: 80},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    new AjaxUpload('#upload', {
        action: '../sys/oss/upload',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                layer.alert("上传成功", function (index) {
                    $('#upload').val(r.url);
                    layer.close(index);
                });
            } else {
                alert(r.msg);
            }
        }
    });
});

/**
 * 图片格式化
 *
 * @param cellvalue
 * @param options
 * @param rowdata
 * @returns {string}
 */
function imgFormatter(cellvalue, options, rowdata) {
    return '<img src="' + cellvalue + '" style="width:100px;height:160px;"/>';
}

/**
 * 树初始化
 */
var ztree;
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: null,
            rootPId: null
        },
        key: {
            name: 'typeName'
        }
    },
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "level"
    }
};

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        product: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.product = {};
            vm.getMenuTree(null);
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
        },
        saveOrUpdate: function (event) {
            var url = vm.product.id == null ? "../product/save" : "../product/update";
            //获取选择的菜单
            var nodes = ztree.getCheckedNodes(true);
            vm.product.typeId = nodes.length > 0 ? nodes[0].id : 0;
            vm.product.picture = $('#upload').val();
            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                cache: false,
                data: JSON.stringify(vm.product),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            fn_confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../product/delete",
                    dataType: "json",
                    contentType: "application/json",
                    cache: false,
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get("../product/info/" + id, function (r) {
                vm.product = r.product;
                vm.getMenuTree(r.product.typeId);
            }, 'json');
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        },
        getMenuTree: function (typeId) {
            //加载菜单树
            $.get("../producttype/select", function (r) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.typeList);
                //展开所有节点
                ztree.expandAll(true);

                if (typeId != null) {
                    vm.getType(typeId);
                }
            }, 'json');
        },
        getType: function (typeId) {
            var node = ztree.getNodeByParam("id", typeId);
            ztree.checkNode(node, true, false);
        }
    }
});