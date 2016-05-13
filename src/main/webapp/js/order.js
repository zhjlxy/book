/**
 * Created by xuyang.li on 2016/5/11.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
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
        if(data.status=="SUCCESS"){
            var json = $.parseJSON(data.data);
            location.href="orderList.html"
        }else{
            alert(data.statusMsg);
        }
    });
}