//全局变量
var content = '';
var type=null;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
    //初始化车间信息
    getWrokshops();



});
//请求所有车间
function getWrokshops() {
    $.ajax({
        url:"/itams/role/getWorkshop",
        success:function (res) {
            var li ="";
            for(var x in res){
                li += "<option value=\""+res[x]+"\">"+res[x]+"</option>"
            }
            $("#workshops").append(li);
            // var type = $("#ITtype").val();

        }
    })
}
//设置双向选择框

function dullist (){

    $('select[name="duallistbox_demo1"]').bootstrapDualListbox({
        nonSelectedListLabel: '未拥有权限',
        selectedListLabel: '已有权限',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
        moveSelectedLabel: "添加",
        moveAllLabel: '添加所有',
        removeSelectedLabel: "移除",
        removeAllLabel: '移除所有',
        infoText: '共{0}个',
        infoTextEmpty: '无',
        selectorMinimalHeight: 300    //设置最小高度
    });
}


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
    var id = "setting"+index;
    var delID = "del"+index;
    var sele = "#setting"+index
    var delSele = "#del"+index




    return [
        '<a  value="'+row.roles+'"  onclick="getThisRole(\''+row.roles+'\',\''+row.uid+'\')"  type="button" href="" id="'+id+'" data-toggle="modal"data-target="#settingmod"><i class="glyphicon glyphicon-wrench"></i></a> ',
        '<a style="margin-left: 1rem;" type="button" id="'+delID+'" data-toggle="modal"data-target=""><i class="glyphicon glyphicon-remove"></i></a>'
    ].join('');
}


//点击setting按钮 ,获取当前人员的角色
function getThisRole(param,uid){
    if(!checkPermission()){
        toastr.warning("您没有权限操作!")
        toastr.warning("您可以在个人菜单中申请相应权限")
        setTimeout(function () {
            $('#close').click();// 关闭弹出层
        },1)

    }else{
    //在更改按钮上赋值value
    $("#changeRole").val(uid)

    $("option").attr("selected",false)
    //恢复原始并删除插件.
    var arr = param.split(",")
    for (var i=0;i<arr.length;i++){
        $("#workshops option").each(function () {
            var value = $(this).val();   //获取option值
            if(value==arr[i]){
                // $('select[name="duallistbox_demo1"]')
                $("option[value='"+value+"']").attr("selected","selected");
                console.log("判断成功  : "+$("option[value='"+value+"']").val())

            }
        })
    }
    //有 <option>  之后调用双向选择
    dullist()
    $('select[name="duallistbox_demo1"]').bootstrapDualListbox('refresh', true)
    }
}

//更改Role
function uploadRole() {
    var uid = $("#changeRole").val()
    var roles = $('[name="duallistbox_demo1"]').val()
    console.log(roles)
    $.ajax({
        url: "/itams/role/update",
        type: "get",
        contentType: 'application/json',
        data: {
            uid:uid,
            roles:roles
        },
        success: function (res) {
            console.log(res)
            //弹出提示
            toastr.success("更改权限成功!")
            //刷新table重新获取数据
            //初始化全局变量
            content = "";
            type = null;
            $("#RoleTable").bootstrapTable("refresh", {pageNumber: 1});
            $('#close').click();// 关闭弹出层
        }
    })

}

