/**
 * Created by lixuy on 2016/4/25.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    getAuthBook(1);

});

function getAuthBook(pageNum) {
    request.ajax("GET","book/auth_book?pageNum="+pageNum+"&pageSize=10","",function successFn(data){
        if(data.flag){
            var jsonArr = $.parseJSON(data.data);
            addAuthBookInfo(jsonArr);
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
function addAuthBookInfo(jsonArr) {
    if(jsonArr.length>0){
        $("#bookInfo").empty();
        $("#bookInfo").append("<tr><th class='text-center'>序号</th><th class='text-center'>图片</th><th class='text-center'>名字</th><th class='text-center'>售价</th><th class='text-center'>状态<th class='text-center'>操作</th></tr>");
        for(var i=0; i<jsonArr.length; i++){
            var json = jsonArr[i];

            var actionStr = "<a href='javascript:void(0)' onclick=\"updateAuthStatus('in','"+json.id+"')\">通过</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                +"<a href='javascript:void(0)' onclick=\"updateAuthStatus('audit_fail','"+json.id+"')\">不通过</a>";
            $("#bookInfo").append("<tr><td>"+eval(i+1)+"</td><td><img style=\"height: 25px\" src=\"../img"+json.picture+"\"></td><td>"+json.name+"</td><td>"+json.price+"</td><td>"+json.sellStatusDesc+"<td>"+actionStr+"</td></tr>")
        }
    }
}

function updateAuthStatus(status, bookId) {
    request.ajax("GET","book/update_sell_status?sellStatus="+status+"&bookId="+bookId,"",function successFn(data){
        if(data.status=="SUCCESS"){
            location.href="authBook.html";
        }else{
            alert(data.statusMsg);
        }
    });
}