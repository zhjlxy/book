/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
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
                addType(json.type);
            }else{
                alert(data.statusMsg);
            }
        });
    }else{
        addType('-1');
    }
    request.inputfile("img", "/book/upload", function (data) {
        var result = $.parseJSON(data.data)

        $("#picture").val(result.picture);
    });

});
/**
 * 添加类型
 */
function addType(type){
    request.ajax("GET","bookType","",function successFn(data) {
        if(data.status=="SUCCESS"){
            var arr = $.parseJSON(data.data);
            for(var i=0;i<arr.length; i++){
                var json = arr[i];
                if(type == json.id){
                    $("#type").append("<option selected value=\""+json.id+"\">"+json.desc+"</option>");
                }else{
                    $("#type").append("<option value=\""+json.id+"\">"+json.desc+"</option>");
                }

            }

        }else{
            alert(date.statusMsg);
        }

    });
}
function sub() {
    // 取数据
    // 图片是否已经上传；
    $("#submit").attr("disabled","true");
    var type = $("#type").val()
    var img = $("#picture").val();
    if(img == ""){
        alert("请先上传图片！");
        $("#submit").removeAttr("disabled");
        return;
    }
    if(type == ""){
        alert("请选择类型！");
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
        "id":$("#id").val(),
        "type":$("#type").val()
    }
    request.ajax("POST","book/save_or_update",json,function(data){
        location.href="bookMannger.html";
    });
}

