/**
 *
 */
	var checkCode = true;
	$("#code").blur(function () {

		if ($(this).val()==$.cookie("code")){
			var checkCode = true;
		}else {
			var checkCode = false;
		}
	})


	$("#res_btn").click(function() {
		var checkId = true;
		var checkPwd = true;
		if($("#manager").val() == "")
		{
			$("#managerMsg").val("账号不能为空");
			checkId = false;
		}
		if($("#password").val() == "")
		{
			$("#passwordMsg").val("密码不能为空");
			checkPwd = false;
		}
		if(checkId == false || checkPwd == false)
		{
			alert("账号或密码为空");

		}
		if (checkCode == false){
			alert("验证码错误");
		}
		else
		{
			console.log("我进来了吗" + $("#res_form").serialize());
			$.ajax({
				url: "http://localhost/user/res",
				type:"post",
				dataType:"json",
				data : $("#res_form").serialize(),
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

$("#getCode_btn").click(function(phone) {

		console.log("我进来了吗" + $("#phone").serialize());
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
					$.cookie("code",CommonResult.data)
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


/*	check = function()
	{
	    manager = document.getElementById("manager");
	    password = document.getElementById("password");
	    managerMsg = document.getElementById("managerMsg");
	    passwordMsg = document.getElementById("passwordMsg");
	    var isManager = true;
	    var isPassword = true;

	    if(manager.value == "")
	    {
	        managerMsg.innerHTML="账号不能为空";
	        isManager = false;
	        console.log("1");
	    }
	    if(password.value == "")
	    {
	        passwordMsg.innerHTML="密码不能为空";
	        isPassword = false;
	        console.log("2");
	    }
	    if(isManager == true && isPassword == true)
	    {
	        var lf = document.getElementById("lf");
	        document.lf.submit();
	    }
	}

	function showManagerTips()
	{
	    managerMsg.innerHTML="";
	}



	function showPasswordTips()
	{
	    passwordMsg.innerHTML="";
	}*/