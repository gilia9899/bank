/**
 *
 */
/*var userID = document.getElementById("userID");
var userName = document.getElementById("userName");
var phone = document.getElementById("phone");
var email = document.getElementById("email");*/
/*var userID = document.getElementById("userID");*/



var checkCode;
var userid = $.cookie("role_id");
var username = $.cookie("user_name");

$(function () {
    $("#userid").val(userid);
    $("#username").val(username);
})

$("#code").blur(function () {
    console.log($("#code").val());
    console.log("cookie"+$.cookie("updatecode"));
    if ($("#code").val()===$.cookie("updatecode")){
        console.log("验证码正确");
        checkCode = true;
    }else {
        console.log("验证码错误");
        checkCode = false;
    }
});


$("#getCode_btn").click(function() {

    var phone = $.cookie("phone")
    console.log("我进来了吗" + phone.serialize());
    $.ajax({
        url: "http://localhost/user/getcode/"+phone,
        type:"get",
        dataType:"json",
        success:function(CommonResult)
        {
            /*
                                console.log("backstageIndex.html?user_name=" +  result.extend.managerLogin.user_name
                                                        + "&role_id=" + result.extend.managerLogin.role_id);*/

            if(CommonResult.code == 200)
            {
                /*						document.cookie = "user_name = " + result.extend.managerLogin.user_name;
                                        document.cookie = "role_id" + result.extend.managerLogin.role_id;*/
                $.cookie("updatecode",CommonResult.data);
                $(this).attr('disabled',true);
                console.log(CommonResult.data);
            }
            else if(CommonResult.code == 404)
            {
                alert(CommonResult.message);
            }
            else
            {
                alert(CommonResult.message);
            }

        }
    });

    /* Act on the event */
});


$("#update_btn").click(function() {
    var checkPwd = true;
    var check = true;
    if($("#userpwd").val() == "")
    {
        checkPwd = false;
        alert("密码为空");
    }
    if ($("#userpwd") != $("#check")){
        console.log("两次密码不一样")
        check = false;
        alert("两次密码不一致");
    }

    if (checkCode == false){
        alert("验证码错误");
    }
    else
    {
        console.log("我进来了吗" + $("#update_form").serialize());
        $.ajax({
            url: "http://localhost/user/update",
            type:"post",
            dataType:"json",
            data : $("#update_form").serialize(),
            success:function(CommonResult)
            {
                /*
                                    console.log("backstageIndex.html?user_name=" +  result.extend.managerLogin.user_name
                                                            + "&role_id=" + result.extend.managerLogin.role_id);*/
                console.log(CommonResult);
                if(CommonResult.code == 200)
                {
                    /*						document.cookie = "user_name = " + result.extend.managerLogin.user_name;
                                            document.cookie = "role_id" + result.extend.managerLogin.role_id;*/
                    window.location.href="login.html";
                }
                else if(CommonResult.code == 404)
                {
                    alert(CommonResult.message);
                }
                else
                {
                    alert(CommonResult.message);
                }

            }
        });


    }
    /* Act on the event */
});
