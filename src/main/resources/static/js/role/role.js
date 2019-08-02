$(function () {

    getWrokshops()
    clearRole("普通用户")
    clearRole("超级管理员")

    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
    //搜索工号或者用户名
    $('#search').on('click',function () {
        var data = $('#searchValue').val()
        $.ajax({
            url: "/itams/role/getRoleByIdAndUser",
            data: {content:data},
            success:function (res) {
                console.log(res)
                console.log(res.roles)
                $('#userid').val(res.userid)
                $('#name').val(res.uname)
                var arr = res.roles.split(",")
                for (var i=0;i<arr.length;i++){
                    $("input[type=checkbox]").each(function () {
                        var value = $(this).val();
                        if(value==arr[i]){
                            $(this).prop("checked",true)
                        }
                    })
                }
                //获取部门
                getDep(res.userid)
            }
        })
    })

    //保存操作
    $('#commit').on('click',function () {
        console.log($('#userForm').serialize());
        if ($("#userid").val()==""){
            toastr.warning("工号不能为空")
        }else{
            var userid = $("#userid").val()
            //获取所有已选择的权限
            var roles = new Array()
            $('input[type=checkbox]').each(function () {
                if ($(this).prop('checked')==true) {
                    roles.push($(this).val())
                }
            })
            console.log(roles);
            setTimeout(function () {
                $.ajax({
                    url: "/itams/role/update",
                    data: {
                        userid:userid,
                        roles:roles
                    },
                    success: function (res) {
                        console.log(res)
                        //弹出提示
                        if (res=="ok"){
                            toastr.success("更改权限成功!")
                        }else{

                        }
                    }
                })
            },200)
        }
    })
})
//清除其他所有权限的选择
function clearRole(name) {
    $("body").on('click','input[type=checkbox][value="'+name+'"]',function () {
        //console.log($(this).prop('checked'))
        var that = $(this);
        if ($(this).prop('checked')==true) {
            $('input[type=checkbox]').each(function () {
                $(this).prop('checked',false);
            })
            that.prop('checked',true)
        }
    })
}
//请求所有权限
function getWrokshops() {
    $.ajax({
        url:"/itams/role/getWorkshop",
        success:function (res) {
            var biaoqian ="";
            for(var x in res){
                biaoqian +="<input class=\"col-md-2\" type=\"checkbox\" id=\""+res[x]+"\" name=\""+x+"\" value=\""+res[x]+"\">"
                biaoqian += "<label for=\"256GSSD\" class=\"col-md-10\" style=\"text-align: left;\">"+res[x]+"</label>"
            }
            $("#roles").append(biaoqian);
        }
    })
}

//自动查询使用人工号
$('#userid').on('focus',function () {
    $("#name").val("")
    $('#dep').val("")
})
$('#userid').on('blur',function () {
    var data = $('#userid').val()
    if(data!=null && data!=""){
        $.ajax({
            url:"/itams/operate/getName",
            type:'get',
            data: {userid:data},
            success:function (res) {
                if(res==null || res==""){
                }else{  //修改责任人的文本
                    $("#name").val(res)
                }
            }
        });
        //获取部门
        getDep(data)
    }

})

function getDep(userid) {
    //获取部门
    $.ajax({
        url:"/itams/getDep",
        data: {personid:userid},
        success: function (res) {
            console.log(res)
            $('#dep').val(res)
        }
    })
}