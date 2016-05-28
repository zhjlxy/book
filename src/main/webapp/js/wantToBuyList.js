/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function () {

    // 添加欢迎语
    addUserName();
    carNum();
    getWantToBuyList(1);
});

function getWantToBuyList(pageNum) {
    request.ajax("GET","want_to_buy?pageNum="+pageNum+"&pageSize=10","",function successFn(data){
        if(data.flag){
            var jsonArr = $.parseJSON(data.data);
            addWantToBuyList(jsonArr);
            $("#navUl").empty();
            if(data.count>1) {
                for (var c = 1; c <= data.count; c++) {
                    var liDom;
                    if (c == data.pageNum) {
                        liDom = "<li class=\"active\"><a href=\"javascript:void(0)\" onclick='getUserInfo(" + c + ")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    } else {
                        liDom = "<li><a a href=\"javascript:void(0)\" onclick=\"getUserInfo(" + c + ")\">" + c + " <span class=\"sr-only\">(current)</span></a></li>";
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
function  addWantToBuyList (jsonArr) {
    if(jsonArr.length>0){
        $("#wantToBuy").empty();
        $("#wantToBuy").append("<tr><th class='text-center'>序号</th><th class='text-center'>发布用户</th><th class='text-center'>标题</th><th class='text-center'>内容</th><th class='text-center'>发布时间<a href='addWantToBuy.html'> &nbsp;&nbsp;添加</a></th></tr>");
        for(var i=0; i<jsonArr.length; i++){
            var json = jsonArr[i];
            var content = json.content;
            if(content.length>20){
                content = content.substring(0,20)+"..."
            }
            $("#wantToBuy").append("<tr onclick=\"show('"+json.id+"')\"><td>"+eval(i+1)+"</td><td>"+json.userName+"</td><td>"+json.title+"</td><td>"+content+"</td><td>"+json.ctsStr+"</td></tr>")
        }
    }
}

function show (id) {
    location.href="addWantToBuy.html?id="+id;

}


