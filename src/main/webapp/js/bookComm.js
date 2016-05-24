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
            userName = "欢迎您【"+json.userName+"】";
            var role = json.role;
            buildNav(role);
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

/**
 * 填充页面中购物车数量
 */
function carNum(){
    request.ajax("GET","car/list","",function successFn(data){
        if(data.status=="SUCCESS"){
            var json = $.parseJSON(data.data);
            if(json !== null && json.length>0){
                $("#carNum").html("+"+json.length);
            }
        }else{
            var msg = data.statusMsg;
            if( msg == ""){
                msg ="error";
            }
            alert(msg);
        }
    });
}

function buildNav(role) {
    $("#role").val(role);
    if("ADMIN" == role){
        $(".buyers").removeClass("hidden");
        $(".admin").removeClass("hidden");
        $(".seller").removeClass("hidden");
    }else if("SELLER"  == role){
        $(".buyers").removeClass("hidden");
        $(".seller").removeClass("hidden");
    }else if("BUYERS"  == role){
        $(".buyers").removeClass("hidden");
    }else if("VISITOR" == role){
        $(".visitor").removeClass("hidden");
    }else {
        alert("role error")
    }


}