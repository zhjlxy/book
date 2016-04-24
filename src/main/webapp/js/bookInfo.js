/**
 * Created by lixuy on 2016/4/25.
 */
$(document).ready(function () {
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

});
