/**
 * Created by lixuy on 2016/4/25.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    getSellBook(1);

});

function getSellBook(pageNum) {
    request.ajax("GET","book/sell_book?pageNum="+pageNum+"&pageSize=10","",function successFn(data){
        if(data.flag){
            var jsonArr = $.parseJSON(data.data);
            addSellBookInfo(jsonArr);
            $("#navUl").empty();
            if(data.count>1) {
                for (var c = 1; c <= data.count; c++) {
                    var liDom;
                    if (c == data.pageNum) {
                        liDom = "<li class=\"active\"><a href=\"javascript:void(0)\" onclick='getSellBook(" + c + ")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    } else {
                        liDom = "<li><a a href=\"javascript:void(0)\" onclick=\"getSellBook(" + c + ")\">" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    }
                    $("#navUl").append(liDom);
                }
            }

        }else{
            var msg = data.msg;
            if( msg == ""){
                msg ="error";
            }
        }
    });
}

/**
 * 添加管理的书的信息
 * @param jsonArr
 */
function addSellBookInfo(jsonArr) {
    if(jsonArr.length>0){
        $("#bookInfo").empty();
        $("#bookInfo").append("<tr><th class='text-center'>序号</th><th class='text-center'>图片</th><th class='text-center'>名字</th><th class='text-center'>售价</th><th class='text-center'>状态<th class='text-center'>操作</th></tr>");
        for(var i=0; i<jsonArr.length; i++){
            var json = jsonArr[i];
            $("#bookInfo").append("<tr><td>"+eval(i+1)+"</td><td><img style=\"height: 25px\" src=\"../img"+json.picture+"\"></td><td>"+json.name+"</td><td>"+json.price+"</td><td>"+json.status+"<td><a>下架</a></td></tr>")
        }
    }
}
