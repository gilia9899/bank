/*var mainTable =  document.getElementById("mainTable");
var pageMsg =  document.getElementById("pageMsg");
var pageInfo =  document.getElementById("pageInfo");
var usersModal = document.getElementById("usersModal");*/
//当前页码
var currentPage;





$(function(){

			usersSetAll(1);
			getData();
		});


function getData()
{
	var user_name = $.cookie("user_name");
	$("#Name").text(user_name);
}

$("#log_out").click(function() {
		console.log("我要登出了");
    window.location.href="login.html";
	/* Act on the event */
});

/*$(document).on("click", ".log_out", function() {
	console.log("我要登出了");
    window.location.href("login.html");
	/* Act on the event
});*/
/*分页查询*/
		function usersSetAll(pn)
		{
			console.log("随便");
			$.ajax({
				url:"http://localhost:8080/BackstageMusic/queryUsers",
				dataType:"json",
				data:"pn=" + pn,
				type:"get",
				success:function(result)
				{

 					users_left();
					card_head();
					info_or_bill();
					users_search();
					table_head();
 					table(result);
 					page_Msg(result);
 					page_Info(result);


				},
				error:function(e)
				{
					console.log("出错啦");
				}
			});
		}

function users_left()
{
    $("#usersli").addClass("active");
    $("#musicli").removeClass("active");
}

		//标题
		function card_head()
		{
			$("#table_title").empty();
			/*console.log("我日你吗");*/
			$("<h4></h4>").addClass("page-title").appendTo("#table_title").text("用户管理");

		}

		//基本信息or账单查询选择
		function info_or_bill()
		{
/*													<span id="base_info">基本信息</span>&nbsp;
										<span id="users_bill">账单查询</span>*/
			$("#selectOthers").empty();
			console.log("我进入了查询切换");
    		var info = $("<a href='#'>基本信息&nbsp;</a>").addClass("btnChangeBlack btnChangeGray base_info btn:hover");
    		var bill = $("<a href='#'>账单查询</a>").addClass("btnChangeBlack btnChangeGray users_bill btn:hover");
    		$(info).removeClass("btnChangeGray");
    		$(bill).removeClass("btnChangeBlack");
    		$("#selectOthers").append(info).append(bill);


		}
		//搜索信息
		function users_search()
		{
			$("#search").empty();
			/*搜索按钮*/
			var search_i = $("<i></i>").addClass("la la-search search-icon");
			var search_span = $("<span></span>").addClass("input-group-text").append(search_i);
			var search_btn = $("<a href='#' ></a>").append(search_span);
			var search_div = $("<div></div>").addClass("input-group-append search_btn").append(search_btn);

			$("#search").append($("<input type='text' id='search_text' placeholder='Search ...' />").addClass("form-control"))
			.append(search_div);
		}

		//表头
		function table_head()
		{
			$("#mainTable thead").empty();
			var table_tr = $("<tr></tr>");
			var table_uid = $("<th>用户账号</th>");
			var table_uname = $("<th>用户昵称</th>");
			var table_urole = $("<th>用户权限等级</th>");
			var table_uaction = $("<th>操作</th>").addClass("text-right");
			$("<tr></tr>").append(table_uid)
						.append(table_uname)
						.append(table_urole)
						.append(table_uaction)
						.appendTo("#mainTable thead");




		}



		/*表单*/
 		function table(result)
		{
 			$("#mainTable tbody").empty();
			var t = result.extend.queryUsers.list;
			console.log(result);
			$.each(t,function(index,item){
				/*
																			<label class="form-check-label"> <input
																class="form-check-input task-select" type="checkbox">
																<span class="form-check-sign"></span>
															</label>
				*/
/*				var cbb=$("<label></label>").addClass("form-check-label")
						.append($("<input  type='checkbox'/>").addClass("form-check-input task-select"))
						.append($("<span></span>").addClass("form-check-sign"));
				var cb=$("<td></td>").append(cbb);*/
				var user_id=$("<td></td>").append(item.user_phone);
				var user_name=$("<td></td>").append(item.user_name);
				var role_name=$("<td></td>").append(item.role.role_name)

				/*
																			<button type="button" data-toggle="tooltip"
																title="Edit Task"
																class="btn btn-link <btn-simple-primary">
																<i class="la la-edit"></i>
															</button>
				*/
				var editBtn = $("<button></button>").addClass("btn btn-link btn-simple-primary edit_btn")
				.append($("<i></i>").addClass("la la-edit"));

				editBtn.attr("editid",item.user_id);


				var btn=$("<td></td>").append($("<div></div>").addClass("form-button-action ").append(editBtn))
				.addClass("td-actions text-right");

				$("<tr></tr>").append(user_id)
				.append(user_name)
				.append(role_name)
				.append(btn)
				.appendTo("#mainTable tbody");
			});
		}





		//分页信息
 		function page_Msg(result)
 		{
 			$("#pageMsg").empty();
 			var tt=$("<div></div>").append("当前页码："+result.extend.queryUsers.pageNum +"总共："
 					+ result.extend.queryUsers.pages+
 					"页,总记录数：" + result.extend.queryUsers.total);
 			$("#pageMsg").append(tt);

 			currentPage = result.extend.queryUsers.pageNum;
 		}

 		//分页条
		function page_Info(result)
		{
			$("#pageInfo").empty();
			var ull = $("<ul></ul>").addClass("pagination pg-primary");
			//首页
			var sy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("首页")
					.attr("href","#"));
			//前一页
			var qy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&laquo;")
					.attr("href","#"));
			if(result.extend.queryUsers.hasPreviousPage == false)
				{
					sy.addClass("disabled");
					qy.addClass("disabled");
				}
			else
				{
				//翻页
				sy.click(function()
						{
							usersSetAll(1);
						});
				qy.click(function()
						{
							usersSetAll(result.extend.queryUsers.pageNum -1);
						});
				}

			//后一页
			var hy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&raquo;")
					.attr("href","#"));
				/* 			<li class="page-item"><a class="page-link"
				 href="${pageContext.request.contextPath  }/index?pn=${queryUsers.pages }">尾页</a>
				 </li> */
			//尾页
			var wy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("尾页")
					.attr("href","#"));



			if(result.extend.queryUsers.hasNextPage == false)
				{
					hy.addClass("disabled");
					wy.addClass("disabled");
				}
			else
				{
				 wy.click(function()
							{
								usersSetAll(result.extend.queryUsers.pages);
							});
					hy.click(function()
							{
								usersSetAll(result.extend.queryUsers.pageNum +1);
							});
				}

			ull.append(sy).append(qy);
			//分页按钮
			$.each(result.extend.queryUsers.navigatepageNums,function(index,item){
				 var ml = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append(item)
							.attr("href","#"));
				 if(result.extend.queryUsers.pageNum == item)
					 {
					 	ml.addClass("active");
					 }
				 ml.click(function(){
					 usersSetAll(item);
				 });
				 ull.append(ml);
			})
			 ull.append(hy).append(wy).appendTo("#pageInfo");
		}


$("#usersManager").click(function(){
	$("#btn").empty();
	console.log("用户管理");
	usersSetAll(1);
});



$(document).on("click", ".base_info", function() {
	usersSetAll(1);
	/* Act on the event */
});