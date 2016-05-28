/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    var id = url_util.queryString("id");
    if(id!=null) {
        // 修改
        // 填充信息
        $("#show").removeClass("hidden");
        request.ajax("GET","want_to_buy/"+id,"",function successFn(data){
            if(data.status=="SUCCESS"){
                var json = $.parseJSON(data.data);
                $("#title").val(json.title);
                $("#userName").val(json.userName);
                $("#content").val(json.content);
                $("#remark").val(json.remark);
            }else{
                alert(data.statusMsg);
            }
        });

    }else{
        $("#add").removeClass("hidden");
        $("#submit").removeClass("hidden");
        initValidator();
    }
});


function initValidator() {

    $('#defaultForm').bootstrapValidator({

    });
}

function sub() {
    // 取数据
    var json = {
        "title":$("#add_title").val(),
        "content":$("#content").val(),
        "remark":$("#remark").val(),

    }
    request.ajax("POST","/want_to_buy",json,function(data){
        if(data.status=="SUCCESS"){
            location.href="wantToBuyList.html";
        }else {
            alert(data.statusMsg);
        }
    });
}

