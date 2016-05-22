/**
 * Created by lixuy on 2016/4/25.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    getUserInfo(1);

});

function getUserInfo(pageNum) {
    request.ajax("GET","user?pageNum="+pageNum+"&pageSize=10","",function successFn(data){
        if(data.flag){
            var jsonArr = $.parseJSON(data.data);
            addUserInfo(jsonArr);
            $("#navUl").empty();
            if(data.count>1) {
                for (var c = 1; c <= data.count; c++) {
                    var liDom;
                    if (c == data.pageNum) {
                        liDom = "<li class=\"active\"><a href=\"javascript:void(0)\" onclick='getUserInfo(" + c + ")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    } else {
                        liDom = "<li><a a href=\"javascript:void(0)\" onclick=\"getUserInfo(" + c + ")\">" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    }
                    $("#navUl").append(liDom);
                }
            }

        }else{
            var msg = data.msg;
            if( msg == ""){
                msg ="error";
            }
        }
    });
}

/**
 * 添加管理的书的信息
 * @param jsonArr
 */
function addUserInfo(jsonArr) {
    if(jsonArr.length>0){
        $("#bookInfo").empty();
        $("#bookInfo").append("<tr><th class='text-center'>序号</th><th class='text-center'>用户名</th><th class='text-center'>角色</th><th class='text-center'>操作</th></tr>");
        for(var i=0; i<jsonArr.length; i++){
            var json = jsonArr[i];
            var actionStr ="<a href='javascript:void(0)' onclick=\"resetPassword('"+json.id+"')\">重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            +"<a href='javascript:void(0)' onclick=\"deleteUser('"+json.id+"')\">修改</a>";;

            $("#bookInfo").append("<tr><td>"+eval(i+1)+"</td><td>"+json.userName+"</td><td>"+json.roleDesc+"</td><td>"+actionStr+"</td></tr>")
        }
    }
}

function resetPassword(userId) {
    request.ajax("GET","user/reset_password?userId="+userId,"",function successFn(data){
        if(data.status=="SUCCESS"){
            location.href="userMannger.html";
        }else{
            alert(data.statusMsg);
        }
    });
}

function deleteUser(userId) {
    request.ajax("GET","user/delete?userId="+userId,"",function successFn(data){
        if(data.status=="SUCCESS"){
            location.href="userMannger.html";
        }else{
            alert(data.statusMsg);
        }
    });
}