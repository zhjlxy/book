/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function() {
    // Generate a simple captcha
    // 添加欢迎语
    addUserName();
    carNum();
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
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能位空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '两次密码不一致'
                    },
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码不一致'
                    },
                }
            }
        }
    });
}
/**
 * 提交表单
 */

function sub(){


    var password=$("#password").val();
    // 注册
    request.ajax("GET","user/change_password?password="+password,"",function(data){
        if(data.status=="SUCCESS"){
            alert("修改密码成功");
            location.href = "login.html";
        }else{
            var msg = "unkown error";
            if(data.statusMsg != ""){
                msg = data.statusMsg;
            }
            alert(msg);
        }
        location.href = "changePassword.html";
    });
}