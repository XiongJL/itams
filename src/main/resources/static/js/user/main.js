//申请进度,默认1
var step = 1 ;
$(function () {
    getWorkshops();
    doIApply();
})
function nextSetp() {
    //下一阶段
    $("#nextBtn").click();
}
function whatIApply() {
    //查询用户申请的权限,并添加到HTML中
    var content =    "你正在申请：";
    $.ajax({
        url:"/itams/role/whatIApply",
        success:function (res) {
            content+=res;
            content+= "权限";
            $("#whatIApply").append(content);
        }
    })


}
function doIApply() {  //查询该账号是否已经申请过了
    $.ajax({
        url: "/itams/role/doIApply",
        success:function (res) {
            if (res=="ok"){  //没有申请
                $("#ongoing").addClass("hidden")
            }else {
                whatIApply();
                $("#toApply").addClass("hidden")
                $("#ongoing").removeClass("hidden")
                if (res=="1"){
                    nextSetp();
                }
                if (res=="2"){
                    nextSetp();nextSetp();
                }

            }
        }
    })
}
function getWorkshops() { //获取搜索角色名称
    $.ajax({
        url:"/itams/role/getWorkshop",
        success:function (res) {
            var li ="";
            for(var x in res){
                li += "<option value=\""+res[x]+"\">"+res[x]+"</option>"
            }
            $("#role").append(li);
        }
    })
}
$("#apply").click(function () {  //申请临时权限
    alert($("#reason").val())
    $.ajax({
        url: "/itams/role/apply",
        data: {"role":$("#role").val(),
            "reason": $("#reason").val()
        },
        success: function (e) {
            if ("ok"==e){
                whatIApply()
                $("#toApply").addClass("hidden")
                $("#ongoing").removeClass("hidden")
            } else{

            }
        }
    })
})