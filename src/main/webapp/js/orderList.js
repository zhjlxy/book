/**
 * Created by xuyang.li on 2016/5/11.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    request.ajax("GET","/order","",function(data){
        if(data.status=="SUCCESS"){
            var jsonArr = $.parseJSON(data.data);
            for(var i=0;i<jsonArr.length;i++){
                var json = jsonArr[i]
                var doStr = "";
                if("N" == json.status){
                    doStr="<td><a href=\"javascript:void(0)\" onclick=\"finishOrder('"+json.id+"')\">付款</a>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"delOrder('"+json.id+"')\">撤消</a></td>"
                }
                $("#contxt").append("<table class=\"table table-bordered table-condensed text-center\">"
                +"<tr><th colspan='5'>"+json.cts+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单号:"+json.id+"</th></tr>"
                +"<tr><td><table width=\"100%\" style=\"line-height: 55px\"  id='bookList_"+i+"'></table></td><td>"+json.name+"</td><td>"+json.total+"</td><td>"+json.statusDesc+"</td>"+doStr+"</tr>");

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

function  finishOrder(orderId) {
    var json={
        "id":orderId,
        "status":"F"
    };
    request.ajax("POST","order/update_status", json,function(data){
        if(data.status=="SUCCESS"){
            location.href="orderList.html";
        }else{
            alert(data.statusMsg);
        }
    });
}

/**
 * 取消订单
 * @param orderId
 */
function delOrder(orderId) {
    var json={
        "id":orderId,
        "status":"D"
    };

    request.ajax("POST","order/update_status", json,function(data){
        if(data.status=="SUCCESS"){
            location.href="orderList.html";
        }else{
            alert(data.statusMsg);
        }
    });
}