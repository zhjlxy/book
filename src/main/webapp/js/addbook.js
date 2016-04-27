/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    var bookId = url_util.queryString("bookId");
    if(bookId!=null) {
        // 修改
        // 填充信息
        request.ajax("GET","book/"+bookId,"",function successFn(data){
            if(data.status=="SUCCESS"){
                var json = $.parseJSON(data.data);
                $("#name").val(json.name);
                $("#author").val(json.author);
                $("#desc").val(json.name);
                $("#price").val(json.price);
                $("#new_status").val(json.new_status);
                $("#picture").val(json.picture);
                $("#id").val(json.id);

                // img
                $("#img_msg").attr("src","../img"+json.picture);
                $("#img_msg").removeAttr("hidden");
            }else{
                alert(data.statusMsg);
            }
        });
    }
    request.inputfile("img", "/book/upload", function (data) {
        alert(data.data);
        var result = $.parseJSON(data.data)
        alert(result.picture);

        $("#picture").val(result.picture);
    });

});
function sub() {
    // 取数据
    // 图片是否已经上传；
    $("#submit").attr("disabled","true");
    alert("sub");
    var img = $("#picture").val();
    if(img == ""){
        alert("请先上传图片！");
        $("#submit").removeAttr("disabled");
        return;
    }

    var json = {
        "name":$("#name").val(),
        "author":$("#author").val(),
        "desc":$("#desc").val(),
        "price":$("#price").val(),
        "new_status":$("#new_status").val(),
        "picture":$("#picture").val(),
        "id":$("#id").val()
    }
    request.ajax("POST","/book/save_or_update",json,function(data){
        alert(data);
    });
}

