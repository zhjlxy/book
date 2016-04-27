/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function() {
    // Generate a simple captcha
    initValidator();
});
function initValidator() {

    $('#defaultForm').bootstrapValidator({
        //        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名无效',
                validators: {
                    notEmpty: {
                        message: '用户名不能位空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名必须大于6，小于30个字'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母、数字、点和下划线组成'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能位空'
                    }
                }
            },
            role:{
                validators:{
                    notEmpty:{
                        message:"选择角色"
                    }
                }
            }

        }
    });
}
/**
 * 提交表单
 */

function sub(){

    var json = {
        "user_name":$("#username").val(),
        "password":$("#password").val(),
        "role":$("#role").val()
    }

    // 注册
    request.ajax("POST","user/login",json,function(data){
        if(data.status=="SUCCESS"){
            location.href = "bookList.html";
        }else{
            var msg = "unkown error";
            if(data.statusMsg != ""){
                msg = data.statusMsg;
            }
            $("#errorInfo").html(msg);
            $("#myAlert").removeAttr("hidden");
            $("#sub").removeAttr("disabled");
        }

    });
}