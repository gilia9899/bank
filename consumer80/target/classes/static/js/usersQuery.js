/**
 *
 */
var currentName

 /*查询按钮点击事件*/
 $(document).on("click", ".search_btn", function(){
    console.log("呜呜呜呜");
    var a = $("#search_text").val();
    currentName = a;
    if(a == "" || a == " ")
    {
        usersSetAll(1);
    }
    else
    {
    getUsersQueryMsg(a,1);
    }


});




function getUsersQueryMsg(userName,pn)
{
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/queryUsers/" + userName + "/" + pn,
/*        data="pn=" + pn,*/
        type: "get",
        dataType:"json",
        success:function(result){
            //alert (result.msg);
            console.log(result);
            table(result);
            page_Msg(result);
            query_users_page_Info(result);
        }
    });
}
//翻页条
        function query_users_page_Info(result)
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
                            getUsersQueryMsg(currentName,1);
                        });
                qy.click(function()
                        {
                            getUsersQueryMsg(currentName,result.extend.queryUsers.pageNum -1);
                        });
                }

            //后一页
            var hy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&raquo;")
                    .attr("href","#"));
                /*          <li class="page-item"><a class="page-link"
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
                                getUsersQueryMsg(currentName,result.extend.queryUsers.pages);
                            });
                    hy.click(function()
                            {
                                getUsersQueryMsg(currentName,result.extend.queryUsers.pageNum +1);
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
                     getUsersQueryMsg(currentName,item);
                 });
                 ull.append(ml);
            })
             ull.append(hy).append(wy).appendTo("#pageInfo");
        }