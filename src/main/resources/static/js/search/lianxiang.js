$(document).keydown(function(event){
    //回车
    if(event.keyCode==13){
        search();
    }
});
search=function () {

    var input = $("input[name='uname']").val();
    if (input != "") {
        $.ajax({
            url: "/itams/search/"+input,
            type: "get",
            dataType: "html",
            async: true,
            success: function (result) {
                console.log(result)
                $("#result").show();
                $("#result").html(result);

            }
        });
    } else {
        $("#result").html("");
        $("#result").hide();
    }
    $("#lns").hide();
}

$(function () {
    // 键盘松开的时候触发联想功能
    $("input[name=uname]").keyup(function (e) {
        e.keyCode
        if(e.keyCode==13){
            //松开的是回车,不触发
        }else{
            var uname = $(this).val();
            if (uname != "") {
                $.ajax({
                    url: "/itams/search/lianxiang",
                    type: "get",
                    data: {"uname": uname},
                    dataType: "html",
                    async: true,
                    success: function (result) {
                        $("#lns").show();
                        $("#lns").html(result);

                        // 点击模糊列表的值，必须在这里写，其他位置不起作用
                        $("li").unbind("click").bind("click", function () {
                            $("input[name=uname]").val($(this).html());
                            $("input[name=uname]").focus();
                            $("#lns").hide();
                        });

                        // 点击其他地方的时候隐藏
                        //$("input[name=uname]").blur(function(){
                        //	$("#lns").hide();
                        //});
                    }
                });
            } else {
                $("#lns").html("");
                $("#lns").hide();
            }
        }


    });


})
//  搜索出的内容
$(function () {

    $("button[name=search]").click(function(){
        search()
    });

})
