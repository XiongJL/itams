$(function () {
    refresh()
    //初始化toastr弹出框
    toastr.options.positionClass = 'toast-top-center';
    //保存操作  (主键不自增.保存管理员操作事件)
})
$('#commit').on('click',function () {
    var data = $('#formValues').serialize()
    console.log(data);
    //获取当前选择的下拉框选项
    var type = $("#select").val();
        $.ajax({
            url: "/itams/select/changeValues",
            type: "post",
            data: data,
            success: function (res) {
                console.log(res)
                //弹出提示
                if (res=="ok"){
                    toastr.success("更改权限成功!")
                }else{

                }
            }
        })

})
//更改下拉框的事件
$("#select").on('change',function () {
    getValues($(this).val())
})
//获取对应的下拉框的值
function getValues(select) {
    //清空列表
    $("#values").empty();
    var url ;
    var selector;
    if (select =="area"){
        url = "/itams/select/Area"
    } else if (select=="state"){
        url = "/itams/select/Astate"
    }else if (select=="step"){
        url = "/itams/select/Step"
    }else if(select == "department"){
        url = "/itams/select/Department"
    }else{
        alert("未选择正确!")
        return ;
    }
    var labels = "";
    $.ajax({
        url:url,
        success: function (res) {
            for (var x in res){
                labels += '<div id="value'+res[x].id+'" class="input-group col-xs-10 col-xs-offset-1" style="margin-top: 1%">' +
                    '        <input class="form-control" name="'+res[x].id+'" id="'+res[x].id+'" readonly value="'+res[x].value+'"/>' +
                    '        <span class="input-group-btn">' +
                    '        <button class="btn btn-default" onclick="edit('+res[x].id+')" type="button">编辑</button>' +
                    '        <button class="btn btn-default" data-toggle="modal" data-target="#myModal" onclick="readydel('+res[x].id+')" type="button">删除</button>' +
                    '        </span>' +
                    '      </div>'
            }
            $("#values").append(labels)
        }
    })
}

//启用编辑
function edit(id){
    $("#"+id).removeAttr("readonly")
    //focus
    $("#"+id).focus();
}
//删除准备
var readyid = 0;
function readydel(id){
    readyid = parseInt(id)
}
//删除该列,并在数据库中删除
function del() {
    console.log(readyid)
    $("#value"+readyid).remove();
    //关闭模态框
    $("#myModal").modal('hide');
    //发送请求
    var type = $("#select").val();
    $.ajax({
        url:"/itams/select/delValue",
        data:{type:type,id:readyid},
        success:function (res) {
            if (res=="ok"){
                toastr.success("删除成功.")
            } else{
                toastr.warning("删除失败!")
            }
        }
    })
}
//移除该列
function remove(id) {
    $("#value"+id).remove();
}

//重置
function refresh(){
    getValues($("#select").val())
}

//添加
function addValue() {
    //首先获取已存在的最大ID
    var max =0 ;
    $("#values").find("input").each(function () {
        if(parseInt(this.id)>max){
            max = this.id
        }
    })
    max ++;
    $("#values").prepend(' <div id="value'+max+'" class="input-group col-xs-9 col-xs-offset-1">' +
        '                                    <input  class="form-control" name="'+max+'" id="'+max+'" value=""/>' +
        '                                    <span class="input-group-btn">' +
        '                                      <button class="btn btn-default" onclick="remove('+max+')" type="button">删除</button>' +
        '                                    </span>' +
        '                                </div>')
}