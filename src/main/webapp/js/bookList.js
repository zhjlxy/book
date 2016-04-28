/**
 * Created by lixuy on 2016/4/18.
 */
var rowSize = 4;
$(document).ready(function () {
    // 添加欢迎语
    addUserName();
    initType();
    getData(1,"");
});

/**
 * 初始化类型
 */
function initType(){
    request.ajax("GET","bookType","",function successFn(data) {
        if(data.status=="SUCCESS"){
            var arr = $.parseJSON(data.data);
            for(var i=0;i<arr.length; i++){
                var json = arr[i];
                $("#type_ul").append("<li class=\"menuItem menuItemWith text-center\"><a href=\"javascript:void(0)\" onclick=\"getType(1,'"+json.id+"',this)\">"+json.desc+"</a></li>");
            }

        }else{
            alert(date.statusMsg);
        }

    });
}

function getType(pageNum,type,obj) {
    $(obj).parent().parent().children().removeClass("active");
    $(obj).parent().addClass("active");
    getData(pageNum, type);
}


/**
 *  填充课本信息
 * @param pageNum 第几页
 * @param type 类型
 */
function getData(pageNum,type) {

    var add_type=""
    if(type != ""){
        add_type = "&type="+type;
    }
    request.ajax("GET","book?pageSize=8&pageNum="+pageNum+add_type,"",function successFn(data) {

        // 成功
        if (data.flag) {
            var count = data.count;
            var pagNum = data.pageNum;

            var array = eval(data.data);
            // var array = data.data;
            var length = array.length;
            var row = length % rowSize == 0 ? length / rowSize : parseInt(length / rowSize) + 1;

            // list显示
            $("#contxt").empty();
            var line = rowSize;
            for (var i = 0; i < row; i++) {
                // row对象
                var rowDom = $("<dev class='row'></dev>");
                line = parseInt(length) - parseInt(i*rowSize) >parseInt(rowSize)?rowSize:parseInt(length) - parseInt(i*rowSize);
                for (var r = 0; r < line; r++) {
                    var json = array[i * rowSize + r];

                    var devDom = $("<dev class='col-xs-6 col-md-3'></dev>");
                    var aDom = $("<a href='bookInfo.html?bookId="+json.id+"' class='thumbnail'></a>");
                    var imgDom = $(" <img style='height: 180px' src=\"../img"+json.picture+"\">");
                    var pDom = $("<p class=\"text-center\">"+json.name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class='btn btn-danger'><b><span class=\"glyphicon-yen\" aria-hidden=\"true\"></span>"+json.price+"</b></label></p>")

                    aDom.append(imgDom);

                    devDom.append(aDom);
                    devDom.append(pDom);

                    rowDom.append(devDom);
                }
                $("#contxt").append(rowDom);
            }

            // 如果count>1有分页
            // 分页
            $("#navUl").empty();
            if(count>1) {
                for (var c = 1; c <= count; c++) {
                    var liDom;
                    if (c == pagNum) {
                        liDom = "<li class=\"active\"><a href=\"javascript:void(0)\" onclick='getData(" + c + ",\"" + type + "\")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    } else {
                        liDom = "<li><a a href=\"javascript:void(0)\" onclick=\"getData(" + c + ",'" + type + "')\">" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                    }
                    $("#navUl").append(liDom);
                }
            }
        } else {
            alert("data error")
        }
    });
}