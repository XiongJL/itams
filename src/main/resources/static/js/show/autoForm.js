$(function () {
    var userid = "";
    $.ajax({
        url:"http://172.60.45.198:8091/itams/api/getUserOperate",        //测试地址
        //url:"",    //正式地址
        data:{userid:userid},
        success:function (res) {
            console.log(res)
        }
    })
})