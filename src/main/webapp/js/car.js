/**
 * Created by lixuy on 2016/5/2.
 */
$(document).ready(function () {
    // 添加欢迎语
    addUserName();
    init();
});

function  init() {
    // 请求购物车中的数据
    request.ajax("GET","car/list","",function successFn(data) {
        if(data.status=="SUCCESS"){
            var arr = $.parseJSON(data.data);
            if(arr.length>0){
                $("#contxt").empty();
                $("#contxt").append("<hr/>");
                for(var i=0;i<arr.length; i++){
                    var json = arr[i];
                    $("#contxt").append("<div class=\"row\"><div class=\"col-lg-2\"> <img src=\"../img"+json.picture+"\"></div>"
                    +"<div  class=\"col-xs-2\"><span>"+json.name+"</span></div>"
                    +"<div  class=\"col-xs-2\"><span>"+json.price+"</span></div>"
                    +"<div  class=\"col-xs-2 col-xs-offset-2\"><a href='javascript:void(0)' onclick='del("+json.id+")'>删除</a></div>"
                    +"</div>");
                    $("#contxt").append("<hr/>");
                }
            }
        }else{
            alert(date.statusMsg);
        }

    });
}

/**
 *  删除购物车中的一本书
 * @param id
 */
function del(id) {
    request.ajax("GET","car/del?bookId="+id,"",function successFn(data) {
        if(data.status=="SUCCESS"){
            location.href="car.html"
        }else {
            alert(data.statusMsg);
        }
    });
}