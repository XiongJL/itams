//全局变量
var content = '';
var type=null;
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
        $('#RoleTable').bootstrapTable({
            url: '/itams/role/getData',         //请求后台的URL（*）
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
            clickToSelect: true,                //是否启用点击选中行
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            paginationLoop: true,             //是否分页条无限循环
            columns: [
                {    //复选框
                  filed:'',
                  checkbox:true,
                  align:'center'
                },
                {
                    field:'uid',
                    title:'id'
                },
                {
                    field:'uname',
                    title:'用户'
                },
                {
                    field: 'Name',
                    title: '姓名'
                },
                {
                    field: 'roles',
                    title: '角色'
                },
                {
                    field: 'createDate',
                    title: '创建日期'
                },
                {
                    field: 'operate',
                    title: '操作',
                    formatter: operateFormatter //自定义方法，添加操作按钮
                },
            ],
            onExpandRow: function(index, row, $detail) {
                //这一步就是相当于在当前点击列下新创建一个table
                var html = "";
                console.log(row)
                var assetsID = row.assetsID
                $.ajax({
                    type: "get",
                    url: "/itams/datas/getThisData",       //子表请求的地址
                    data: {"AssetsID":assetsID},//我这里是点击父表后，传递父表列id和nama到后台查询子表数据
                    async: false,           //很重要，这里要使用同步请求
                    dataType:"html",
                    success: function(data) {
                        $detail.html(data); // 关键地方
                    }
                });
            }
        });
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.pageSize,   //页面大小
            offset:params.pageNumber,    //页码
            content:content,  //最上面全局变量,用以搜索初始值为""
            type:type   //初始值为null
        };
        return temp;
    };
    return oTableInit;
};
function operateFormatter(value, row, index) {//赋予的参数
    return [
        '<a  type="button" href="" id="setting" data-toggle="modal"data-target="#settingmod"><i class="glyphicon glyphicon-wrench"></i></a> ',
        '<a style="margin-left: 1rem;" type="button" id="" data-toggle="modal"data-target=""><i class="glyphicon glyphicon-remove"></i></a>'
    ].join('');
}
