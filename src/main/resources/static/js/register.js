$(function () {
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
})
//全局变量 是否存在工号
var exist = false;
//自动查询使用人工号
$('#userid').on('focus',function () {
    $("#err3").remove()
    exist = false;
})
$('#userid').on('blur',function () {
    //console.log('移出输入框')
    var data = $('#userid').val()
    if(data!=null && data!=""){
        $.ajax({
            url:"/itams/operate/getName",
            type:'get',
            data: {userid:data},
            success:function (res) {
                console.log()
                if(res==null || res==""){
                    //追加错误信息 position: fixed;top: 43%;right: 22%;
                    $("#userid").after('<p class="help-block" id="err3" style="color: red;">不存在此工号</p>')
                }else {  //修改姓名的文本
                    exist = true;
                    $("#username").val(res)
                }
            }
        });
    }

})

function register() {
    console.log("123123")
    var userid = $('#userid').val()
    var username = $('#username').val()
    var password = $('#password').val()
    if (userid==null||username==null||password==null||userid==""||username==""||password==""){
        toastr.warning("请填写完整")
    }else if(exist == false){
        toastr.error("此工号不能注册")
    }
    else{
        $.ajax({
            url:"/itams/register",
            type:'post',
            data: {userid:userid,username:username,password:password},
            success:function (res) {
                console.log(res)
                if (res=="ok"){
                    toastr.success("注册成功,即将跳转到登录页面")
                    setTimeout(function () {
                        window.location.href="/itams/login";
                    },3000)
                }else if (res=="exist") {
                    toastr.warning("账号已存在")
                }
            }
        });
    }
}