/**
 *
 */

	$("#login_btn").click(function() {
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
		else
		{
			console.log("我进来了吗" + $("#login_form").serialize());
			$.ajax({
				url: "http://localhost:8080/BackstageMusic/managerLogin",
				type:"get",
				dataType:"json",
				data:$("#login_form").serialize(),
				success:function(result)
				{
/*					console.log(result);
					console.log("backstageIndex.html?user_name=" +  result.extend.managerLogin.user_name
											+ "&role_id=" + result.extend.managerLogin.role_id);*/
					if(result.code == 1)
					{
/*						document.cookie = "user_name = " + result.extend.managerLogin.user_name;
						document.cookie = "role_id" + result.extend.managerLogin.role_id;*/
						$.cookie("user_name",result.extend.managerLogin.user_name);
						$.cookie("role_id",result.extend.managerLogin.role_id);
 						window.location.href="backstageIndex.html";
					}
					else if(result.code == 2)
					{
						alert(result.msg);
					}
					else
					{
						alert(result.msg);
					}

				}
			});


		}
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