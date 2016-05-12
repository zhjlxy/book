/**
 * Created by xuyang.li on 2016/5/11.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    request.ajax("GET","/order","",function(data){
        if(data.status=="SUCCESS"){
            var jsonArr = $.parseJSON(data.data);
            for(var i=0;i<jsonArr.length;i++){
                var json = jsonArr[i]
                $("#contxt").append("<table class=\"table table-bordered table-condensed\">"
                +"<tr><th colspan='5'>"+json.cts+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单号:"+json.id+"</th></tr>"
                +"<tr><td><table width=\"100%\" style=\"line-height: 55px\"  id='bookList_"+i+"'></table></td><td>"+json.name+"</td><td>"+json.total+"</td><td>"+json.status+"</td></tr>");

                var bookArr = json.bookList;
                for(var j=0;j<bookArr.length; j++){
                    var book = bookArr[j];
                    $("#bookList_"+i).append("<tr><td width=\"50%\"><img src=\"../img/"+book.picture+"\" style=\"height: 50px\"></td><td>"+book.name+"</td><td>"+book.price+"</td></tr>");
                }
            }
        }else {
            alert(data.statusMsg);
        }
    });
});

/**
 * 确认下单
 */
function sub() {

    $("#submit").attr("disabled","true");

    var json = {
        "name":$("#name").val(),
        "tel":$("#tel").val(),
        "address":$("#address").val(),
        "remarks":$("#remarks").val(),
    };
    alert(json);
    
    
    request.ajax("POST","/order",json,function(data){
        alert(data.data);
    });
}