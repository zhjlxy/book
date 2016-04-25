/**
 * Created by lixuy on 2016/4/21.
 */
$(document).ready(function() {
    // Generate a simple captcha
    initValidator();
});
function randomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
};
function initValidator() {
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

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
                    },
                    remote: {
                        url: '/book/user/valid',
                        message: '用户名不可用'
                    },
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能位空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '两次密码不一致'
                    },
                    different: {
                        field: 'username',
                        message: '用户名和密码不能相同'
                    }
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
                    different: {
                        field: 'username',
                        message: '用户名和密码不能相同'
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
    alert("aa");
    var json = {
        "user_name":$("#username").val(),
        "password":$("#password").val(),
        "role":$("#role").val()
    }

    // 注册
    request.ajax("POST","user/register",json,function(data){
        alert(data);
    });
}