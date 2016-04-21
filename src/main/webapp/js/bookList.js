/**
 * Created by lixuy on 2016/4/18.
 */
var rowSize = 4;
$(document).ready(function () {
    getData(1);
});

function getData(pageNum) {

    request.ajax("GET","book?pageSize=8&pageNum="+pageNum,"",function successFn(data) {

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
                    var aDom = $("<a href='#'  class='thumbnail'></a>");
                    var imgDom = $(" <img style='height: 180px' src=\"../img"+json.picture+"\">");
                    var pDom = $("<p class=\"text-center\">"+json.name+"<label class='btn btn-danger'><b>"+json.price+"<span class=\"glyphicon-yen\" aria-hidden=\"true\"></span></b></label></p>")

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
            for (var c = 1; c <= count; c++) {
                var liDom;
                if (c == pagNum) {
                    liDom = "<li class=\"active\"><a href=\"javascript:void(0)\" onclick='getData(" + c + ")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                } else {
                    liDom = "<li><a a href=\"javascript:void(0)\" onclick='getData(" + c + ")'>" + c + " <span class=\"sr-only\">(current)</span></a></li>";
                }
                $("#navUl").append(liDom);
            }

        } else {
            alert("data error")
        }
    });
}