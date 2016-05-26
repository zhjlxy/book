/**
 * Created by lixuy on 2016/4/25.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    var bookId = url_util.queryString("bookId");
    if(bookId == null || bookId == ""){
        alert("系统错误，请与管理员联系！");
        return;
    }
    request.ajax("GET","book/"+bookId,"",function successFn(data){
        if(data.status=="SUCCESS"){
            var json = $.parseJSON(data.data);
            $("#name").html(json.name)
            $("#author").html(json.author);
            $("#desc").html(json.name);
            $("#price").html(json.price+"<span class=\"glyphicon-yen\" aria-hidden=\"true\"></span>");
            $("#new_status").html(json.new_status);

            $("#id").val(json.id);

            // img
            $("#picture").attr("src","../img"+json.picture);
        }else{
            alert(data.statusMsg);
        }
    });

    // 如果是游客不能添加到购物测
    if($("#role").val() == "VISITOR"){
        $("#addCar").hide();
    }

});

function addCar() {
    var id = $("#id").val();
    if(id == "" || id == undefined){
        alert("error");
        return;
    } 
    request.ajax("GET","car/add?book_id="+id,"",function successFn(data){
        if(data.status=="SUCCESS"){
            carNum();
            location.href = "car.html";
        }else{
            var msg = data.statusMsg;
            if( msg == ""){
                msg ="error";
            }
        }
    });
}