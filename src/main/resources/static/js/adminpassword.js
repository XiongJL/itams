$(function () {
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
})
//全局变量 是否两次密码相同
var exist = false;
$('#password3').on('focus',function () {
    $("#err3").remove()
    exist = false;
})
$('#password3').on('blur',function () {
    //console.log('移出输入框')
    var data = $('#password3').val();
    var data1 = $('#password2').val();
    if(data!=data1){
        //追加错误信息 position: fixed;top: 43%;right: 22%;
        $("#password3").after('<p class="help-block" id="err3" style="color: red;">新密码不一致</p>')
    }else{
    	exist = true;
    }
})
function adminpassword() {
    console.log("123123")
    var password1 = $('#password1').val()
    var password2 = $('#password2').val()
    var password3 = $('#password3').val()
    if (password1==null||password1==null||password2==null||password1==""||password1==""||password2==""){
        toastr.warning("请填写完整")
    }
    if(password2==password3){
        $.ajax({
            url:"/itams/updatepassword",
            type:'post',
            data: {password1:password1,password2:password2},
            success:function (res) {
                console.log(res)
                if ("ok"==res){///itams/logout
                    toastr.success("修改成功")
                    setTimeout(function () {
                        window.location.href="/itams/logout";
                    },1000)
                }else if("no"==res){
                    toastr.warning("原密码输入错误，请核对正确后再修改")
                }
            }
        });
    }else{
    	$("#password3").after('<p class="help-block" id="err3" style="color: red;">新密码不一致</p>')
    	exist = false;
    }
}