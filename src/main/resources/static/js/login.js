$(function () {
    $("#login").bind("click",function () {
        $.ajax({
            url: "/itams/login",
            type: "post",
            data: $("#formdata").serialize(),
            success:function (res) {
                console.log(res)
                if ("ok"==res){
                    window.location.href = "/itams/datas"
                }
                if("0"==res){
                    alert("账户或密码错误")
                }
            },
            error:function (err) {
                console.log(err)
            }
        })
    })
})