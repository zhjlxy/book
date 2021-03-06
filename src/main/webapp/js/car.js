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
                var total = 0;
                for(var i=0;i<arr.length; i++){
                    var json = arr[i];
                    $("#contxt").append("<div class=\"row\"><div class=\"col-lg-2\"> <img src=\"../img"+json.picture+"\" style=\"height: 50px\"></div>"
                    +"<div  class=\"col-xs-2\"><span>"+json.name+"</span></div>"
                    +"<div  class=\"col-xs-2\"><span>"+json.price+"</span></div>"
                    +"<div  class=\"col-xs-2 col-xs-offset-2\"><a href='javascript:void(0)' onclick='del("+json.id+")'>删除</a></div>"
                    +"</div>");
                    $("#contxt").append("<hr/>");
                    total = Number(total)+Number(json.price);
                }
                $("#contxt").append("<div class=\"row\">"
                    +"<div class=\"col-md-10 col-md-offset-8\">"
                    +"<strong>共"+arr.length+"本书 总价"+total+"元</strong>"
                    +"</div></div>");

                $("#contxt").append("<div class=\"row\">"
                +"<div class=\"col-md-10 col-md-offset-8\">"
                +"<button class=\"btn btn-primary\" onclick=\"order()\">下单</button>"
                +"</div></div>");
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

function order() {
    location.href = "order.html";
}