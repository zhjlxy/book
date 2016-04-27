/**
 * Created by admin on 2016/4/26.
 */
/**
 * 取用名插入到导航栏中
 */
function addUserName(){
    request.ajax("GET","user/user_name","",function(data){
        var userName = "欢迎您【】";
        if(data.status=="SUCCESS"){
            var json = $.parseJSON(data.data);
            userName = "欢迎您【"+json.user_mame+"】";
        }else{
            var msg = "unkown error";
            if(data.statusMsg == ""){
                msg = data.statusMsg;
            }
            alert(msg);
        }

        $("#welcomeLab").empty();
        $("#welcomeLab").html(userName);
    });
}