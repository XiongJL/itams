# itams
## 注意
在logo.html 120行起, 默认调用了一个域账号验证接口. 在本地测试时,应当首先注释掉该JS语句.
```
function  getad(){
	
        $.ajax({
            type:"get",
            url:"##########:9090/api/Add/GetClientUser",
            ......
    	    	 $("#itemid").html("当前访问员工 :"+ typeaa);
            },
            error: function(xhr, textStatus, errorThrown){
            	layer.msg("发生未知错误");
    		}
        });
```
# 导入项目
该项目由Maven构建,在application.yml中更改相应设置.
###注意
由于本项目使用的是双数据源, 因此在导入后只有单数据源的,需要更改为如下配置
```
spring:
  datasource:
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver  #sqlserver
    url: jdbc:sqlserver://127.0.0.1;DatabaseName=yourDatabase
    username: sa
    password: 123456
```
随后将config包下的三个java文件的 `@Configuration`  注解删除或注释掉, 或者直接删除三个文件均可.
