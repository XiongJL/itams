//全局
var content = "";
var type = null;

$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#AssetsTabel').bootstrapTable({
            url: '/itams/datas/getData',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            // striped: false,                      //是否显示行间隔色
            showLoading: true,
            showFullscreen: true,
            silent: true,
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            queryParamsType: "",                //不设置此项, param.limit 和 param.offset 传参会错误!!!
            // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            //contentType: "application/x-www-form-urlencoded",
            contentType: "application/json; charset=utf-8",
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            // showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            showExport: true,               //显示导出按钮
            clickToSelect: true,                //是否启用点击选中行
            //    exportDataType: "selected",        //导出checkbox选中的行数
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            paginationLoop: true,             //是否无限循环
            //  detailFormatter: 'detailFormatter', //子表方法
            columns: [
                // {    //复选框
                //   filed:'',
                //   checkbox:true,
                //   align:'center'
                // },
                {
                    field: 'deviceID',
                    title: '设备号'
                },
                {
                    field: 'assetsType',
                    title: '资产类型'
                },
                {
                    field: 'assetsCategory',
                    title: '资产类别'
                },

                {
                    field: 'assetsID',
                    title: '资产号'
                },
                {
                    field: 'factoryID',
                    title: '出厂编号'
                },
                {
                    field: 'assetsName',
                    title: '资产名'
                }, {
                    field: 'model',
                    title: '型号'
                },
                {
                    field: 'supplier',
                    title: '供应商'
                },
                {
                    field: 'userName',
                    title: '使用人'
                },
                {
                    field: 'userID',
                    title: '工号'
                },
                {
                    field: 'getTime',
                    title: '领取时间'
                },
                {
                    field: 'operate',
                    title: '操作',
                    formatter: operateFormatter //自定义方法，添加操作按钮
                },
            ],
            onExpandRow: function (index, row, $detail) {
                //这一步就是相当于在当前点击列下新创建一个table
                var html = "";
                console.log(row)
                var assetsID = row.assetsID
                $.ajax({
                    type: "get",
                    url: "/itams/datas/getThisData",       //子表请求的地址
                    data: {"AssetsID": assetsID},//我这里是点击父表后，传递父表列id和nama到后台查询子表数据
                    async: false,           //很重要，这里要使用同步请求
                    dataType: "html",
                    success: function (data) {
                        $detail.html(data); // 关键地方
                    }
                });
            }
            // ,
            // rowStyle: function (row, index) {
            //     var classesArr = ['success', 'info'];
            //     var strclass = "";
            //     if (index % 2 === 0) {//偶数行
            //         strclass = classesArr[0];
            //     } else {//奇数行
            //         strclass = classesArr[1];
            //     }
            //     return { classes: strclass };
            // },//隔行变色
        });

    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.pageSize,   //页面大小
            offset: params.pageNumber,    //页码
            content: content,  //最上面全局变量,用以搜索初始值为""
            type: type   //初始值为null
        };
        return temp;
    };
    return oTableInit;
};


function operateFormatter(value, row, index) {//赋予的参数
    return [
        '<a class="btn  " href="/itams/operate?AssetsID=' + row.assetsID + '">编辑</a>',
        '<a class="btn " href="#">删除</a>'
    ].join('');
}

//注册加载子表的事件。你可以理解为点击父表中+号时触发的事件
/*
function detailFormatter(index, row) {
    var html = []
    var assetsID = row.assetsID
    console.log("查询"+assetsID)
    $.ajax({
        url:"/itams/datas/getAllData",
        type:"get",
        data: {"AssetsID":assetsID},
        success: function (res) {
            console.log(res)
            $.each(res.data, function (key, value) {
                console.log(key+":"+value)
                html.push('<p><b>' + key + ':</b> ' + value + '</p>')
                console.log("ajax:"+html)
            })
            debugger
            return html.join('')
        }
    })
    console.log(html)

     $.each(row, function (key, value) {
         html.push('<p><b>' + key + ':</b> ' + value + '</p>')
     })
    return html.join('')
}*/

//资产ID查询
$('#searchAssets').click(function () {
    var Type = "1"
    chaxun(Type)
});
//工号查询
$('#searchUserID').click(function () {
    var Type = "2";
    chaxun(Type)
});
//设备ID查询
$('#searchDevice').click(function () {
    var Type = "3"
    chaxun(Type)
})

/*查询方法*/
function chaxun(Type) {
    if ($("#searchValue").val().length == 0) {
        //初始化全局变量
        content = "";
        type = null;
        $("#AssetsTabel").bootstrapTable("refresh", {pageNumber: 1});
    } else {
        //查询之后重新从第一页算起
        content = $("#searchValue").val();
        type = Type;//1为搜索AssetsID 2为搜索工号
        $('#AssetsTabel').bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 10});
    }
}

/*下载选择的资产*/
$('#exportSearch').click(function () {
    console.log("开始导出数据")
    if (content == "" || type == null) {
        toastr.warning("请先进行搜索再导出")
    } else {
        window.location.href = "/itams/download/AssetsData?content=" + content + "&type=" + type;
    }
});