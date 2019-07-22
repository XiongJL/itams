function uploadfile() {
    var filename = document.getElementById("uploadBtn").value
    if(filename==null||filename==""){
        toastr.warning("请先选择文件!")
    }else{
        var str = filename.split(".")
        var type = str[str.length-1];
        if(type=="xlsx" || type == "xls"){
            var fileObj = document.getElementById("uploadBtn").files[0];
            var form = new FormData();
            var uptype = $("#Uptype").val();
            if(uptype=="资产"){
                uptype = 0
            }
            if(uptype=="IT"){
                uptype = 1
            }
            //form.append("author", "hooyes"); // 可以增加表单数据
            form.append("file",fileObj);
            $("#loading").show() //显示加载动画
            $("#uploadButton").prop("disabled",true)//禁用button按钮
            $.ajax({
                url: "/itams/upload/excel",
                type: "post",
                data: form,
                /**
                 *必须false才会自动加上正确的Content-Type
                 */
                contentType: false,
                /**
                 * 必须false才会避开jQuery对 formdata 的默认处理
                 * XMLHttpRequest会对 formdata 进行正确的处理
                 */
                processData: false,
                success: function (res) {
                    if(res=="fail"){
                        toastr.warning("上传失败!")
                        $("#loading").hide()
                        $("#uploadButton").prop("disabled",false)//启用button按钮
                    }
                    else{ //返回文件服务器路径
                        //请求去解析,.
                        $.ajax({
                            url: "/itams/upload/resolvExcel",
                            type: "get",
                            data: {"path":res,"type":uptype},
                            success: function (result) {
                                if(result=="ok"){
                                    toastr.success("上传成功!解析成功!")
                                    $("#loading").hide();//隐藏加载
                                    $("#uploadButton").prop("disabled",false)//启用button按钮
                                    $('#close').click();// 关闭弹出层
                                    setTimeout(function () {
                                        $('#update').modal('hide');//用js来控制模板弹窗。
                                        $('#update').modal('toogle');
                                    },10)
                                }else{
                                    toastr.warning("上传成功,但解析失败!!")
                                    $("#uploadButton").prop("disabled",false)//启用button按钮
                                    $("#loading").hide();//隐藏加载
                                }
                            },
                            error: function () {
                                toastr.warning("请下载示例模板进行导入!")
                                $("#uploadButton").prop("disabled",false)//启用button按钮
                                $("#loading").hide();//隐藏加载
                            }
                        })
                    }
                },
                error: function (err) {
                    console.log(err)
                    $("#uploadButton").prop("disabled",false)//启用button按钮
                    $("#loading").hide();//隐藏加载
                }
            })
        }else{
            toastr.warning("文件类型错误!")
            $("#uploadButton").prop("disabled",false)//启用button按钮
            $("#loading").hide();//隐藏加载
        }
    }


}