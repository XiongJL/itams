//全局变量
var roles = null
$(document).ready(function() {
    //获取用户权限集
    $.ajax({
        url: "/itams/user/permission",
        success:function (res) {
            console.log("你的权限有:"+res)
            roles = res
        }
    })

    //添加导航栏选中样式
    $("#operate").addClass("active")
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
    //限制HDD多选框的数量
    $('input[type=checkbox]').click(function() {
        $("input[name='HDD']").attr('disabled', true);
        if ($("input[name='HDD']:checked").length >= 2) {
            $("input[name='HDD']:checked").attr('disabled', false);
        } else {
            $("input[name='HDD']").attr('disabled', false);
        }
    });



    //获取维护工作人员
    $.ajax({
        url:"/itams/operate/Staff",
        success:function (res) {
            var option ="";
            for(var x in res){
                option += "<option>"+res[x]+"</option>>"
            }
            $("#Staff").append(option)
        }
    });

    //设置购买时间和入系统时间默认值为今天
    var mydate = new Date();
    var t=mydate.toLocaleString();

    $("#pdvalue").datetimepicker("setDate", new Date())
    $("#envalue").datetimepicker("setDate", new Date())
})
//公共方法,根据类别改变品牌的值
function changeBrand(type){
    //清空原有内容
    $("#Brand").empty();
    $.ajax({
        url:"/itams/operate/Brands",
        data:{type:type},
        success:function (res) {
            console.log(res)
            var option ="";
            if(res!=null &&res!=""){
                for(var x in res){
                    option += "<option value='"+res[x]+"'>"+res[x]+"</option>"
                }
                $("#Brand").append(option)
                //品牌更改完毕后,修改型号
                changeModel()
            }

        }
    })
}
//公共方法,根据类别,品牌改变型号
function changeModel(){
    //清空原有内容
    $("#Model").empty();
    var type = $("#ITtype").val()
    var brand = $("#Brand").val()
    $.ajax({
        url:"/itams/operate/Models",
        data:{type:type,brand:brand},
       // async: false,//使用同步的方式,true为异步方式
        success:function (res) {
            console.log(res)
            var option ="";
            if(res!=null || res!=""){
                for(var x in res){
                    option += "<option value='"+res[x]+"'>"+res[x]+"</option>"
                }
                $("#Model").append(option)
            }
        }
    })
}

// 获取类别
function getITtype(){
    $.ajax({
        url:"/itams/operate/type",
        success:function (res) {
            var option ="";
            for(var x in res){
                option += "<option value='"+res[x]+"'>"+res[x]+"</option>"
            }
            $("#ITtype").append(option);
            var type = $("#ITtype").val();
            changeBrand(type)
        }
    });
}
//当IT具体类别改变时,查询对应的品牌,以及品牌对应的型号
$("#ITtype").change(function () {
    var type = $("#ITtype").val();
    changeBrand(type)
    if (type!="台式机"&&type!="笔记本"){
        $("#hardinfo").hide()
    }else{
        $("#hardinfo").show()
    }
})
//当品牌改变时,查询对应的型号
$("#Brand").change(function(){
    changeModel()
})

//资产ID是否存在查询
$('#AssetsID').on('focus',function () {
    $("#err1").remove()
})
$('#AssetsID').on('blur',function () {
    //console.log('移出输入框')
    var data = $('#AssetsID').val()
    $.ajax({
        url:"/itams/operate/asidExist",
        type:'get',
        data: {asid:data},
        success:function (res) {
            console.log(res)
            $("#err1").remove()
            if(res=="exist"){
                //追加错误信息
                $("#AssetsID").after('<p class="help-block" id="err1" style="color: red">资产已存在</p>')
            }
        }
    });
})


//自动查询责任人工号
$('#PersonliableID').on('focus',function () {
    $("#err2").remove()
    $("#Personliable").val("")
})
$('#PersonliableID').on('blur',function () {
    //console.log('移出输入框')
    var data = $('#PersonliableID').val()
    if (data!=null && data!=""){
        $.ajax({
            url:"/itams/operate/getName",
            type:'get',
            data: {userid:data},
            success:function (res) {
                console.log(res)
                if(res==null || res==""){
                    //追加错误信息
                    $("#PersonliableID").after('<p class="help-block" id="err2" style="color: red">不存在此工号</p>')
                }else{  //修改责任人的文本
                    $("#Personliable").val(res)
                }
            }
        });
    }

})
//自动查询使用人工号
$('#UserID').on('focus',function () {
    $("#err3").remove()
    $("#UserName").val("")
})
$('#UserID').on('blur',function () {
    //console.log('移出输入框')
    var data = $('#UserID').val()
    if(data!=null && data!=""){
        $.ajax({
            url:"/itams/operate/getName",
            type:'get',
            data: {userid:data},
            success:function (res) {
                if(res==null || res==""){
                    //追加错误信息
                    $("#UserID").after('<p class="help-block" id="err3" style="color: red">不存在此工号</p>')
                }else{  //修改责任人的文本
                    $("#UserName").val(res)
                }
            }
        });
    }

})


//点击有线MAC2----有
$("#WireMAC2On").bind("click",function(){
    $("#WiredMAC2").removeAttr("disabled")
})
//点击无线MAC----无
$("#wirelessOn").bind("click",function(){
    $("#WirelessMac").removeAttr("disabled")
})




//提交表单,保存资产
$("#commit").bind("click",function(){
    console.log($('#hardware').serialize())
    var bro = $("#AssetsID").siblings("p");  //查找是否存在错误提示
    if(bro.length==0){
        console.log(bro.length)
        $.ajax({
            url:"/itams/operate/saveHardware",
            data:$('#hardware').serialize(),
            type: 'post',
            success:function (res) {
                console.log(res)
                if(res=="ok"){ // 保存成功
                    toastr.success('提交数据成功');
                    $("#AssetsID").val("")

                }else if(res=="assetsidExist"){ //已存在,误操作
                    toastr.warning('资产编号已存在!');
                }else if (res=="assetsidEmpty"){
                    toastr.warning('资产编号不能为空!');
                }
                else{  //未知异常
                    toastr.warning("操作失败,未知错误!")
                }
            }
        })
    }else{
        $('body,html').animate({scrollTop:0},1000);
        $('.help-block').fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200);
    }

})

//提交表单,更新资产
$("#update2").bind("click",function(){
    if(!checkRole()){ //无权修改
        toastr.warning("您没有权限修改此资产!")
    }else{
    console.log($('#hardware').serialize())
        $.ajax({
            url:"/itams/operate/saveHardware",
            data:$('#hardware').serialize(),
            type: 'post',
            success:function (res) {
                console.log(res)
                if(res=="ok") { // 保存成功
                    toastr.success('更新成功');
                } else{  //未知异常
                    console.log(res)
                    toastr.warning("更新失败!")
                }
            }
        })
    }

})

//根据全局变量值判断是否能够操作该资产,以显示或者禁用按钮
function checkRole() {
    //获取当前资产ID的车间
    var workshop = $("#Area").val();
    var boolean = false;
    for(key in roles){
        if(roles[key]==workshop){
            console.log("拥有权限!")
            boolean = true
        }
    }
    return boolean
}