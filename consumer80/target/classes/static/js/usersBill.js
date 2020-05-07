/**
 *
 */
var currentPhone;

$(document).on("click", ".users_bill", function(event) {
    info_or_bill2();
    bill_table_head();
    bill_users_search();
    $("#mainTable tbody").empty();
    $("#pageMsg").empty();
    $("#pageInfo").empty();
    /* Act on the event */
});


function bill_users_search()
{
    $("#search").empty();
    /*搜索按钮*/
    var search_i = $("<i></i>").addClass("la la-search search-icon");
    var search_span = $("<span></span>").addClass("input-group-text").append(search_i);
    var search_btn = $("<a href='#' ></a>").append(search_span);
    var search_div = $("<div></div>").addClass("input-group-append bill_search_btn").append(search_btn);

    $("#search").append($("<input type='text' id='bill_search' placeholder='Search ...' />").addClass("form-control"))
    .append(search_div);
}


//搜索单击
 $(document).on("click", ".bill_search_btn", function(){
    console.log("呜呜呜呜");
    var a = $("#bill_search").val();
    currentPhone = a;

    info_or_bill2();
    bill_table_head();
    queryBill(a,1);
});


        function queryBill(user_phone,pn)
        {
            console.log("随便");
            $.ajax({
                url:"http://localhost:8080/BackstageMusic/queryBill/" + user_phone + "/" + pn,
                dataType:"json",
                type:"get",
                success:function(result)
                {

                    bill_table(result);
                    bill_page_Msg(result);
                    bill_page_Info(result);



                },
                error:function(e)
                {
                    console.log("出错啦");
                }
            });

        }



        function info_or_bill2()
        {
/*                                                  <span id="base_info">基本信息</span>&nbsp;
                                        <span id="users_bill">账单查询</span>*/
            $("#selectOthers").empty();
            console.log("我进入了查询切换");
            var info = $("<a href='#'>基本信息&nbsp;</a>").addClass("btnChangeBlack btnChangeGray base_info btn:hover");
            var bill = $("<a  href='#'>账单查询</a>").addClass("btnChangeBlack btnChangeGray users_bill btn:hover");
            $(info).removeClass("btnChangeBlack");
            $(bill).removeClass("btnChangeGray");
            $("#selectOthers").append(info).append(bill);


        }


//表头
        function bill_table_head()
        {
            $("#mainTable thead").empty();

            var bill_user_phone = $("<th>用户账号</th>");//user_phone
            var bill_user_name = $("<th>用户昵称</th>"); //user_name
            var bill_price_money = $("<th>支付金额</th>");//price_money
            var bill_bill_time = $("<th>支付时间</th>");//bill_time

            $("<tr></tr>").append(bill_user_phone)
            .append(bill_user_name)
            .append(bill_price_money)
            .append(bill_bill_time)
            .appendTo("#mainTable thead");

        }


        /*表单*/
        function bill_table(result)
        {
            $("#mainTable tbody").empty();
            var t = result.extend.queryBill.list;
            console.log(result);




            $.each(t,function(index,item){
                var billT = parseInt(item.bill_time);
                    //实例化一个新的日期格式，使用1970 年 1 月 1 日至今的毫秒数为参数
                var payDate = new Date(billT);



                var bill_user_phone = $("<td></td>").append(item.user.user_phone);
                var bill_user_name = $("<td></td>").append(item.user.user_name);
                var bill_price_money = $("<td></td>").append(item.price.price_money);
                var bill_bill_time = $("<td></td>").append(payDate.toLocaleString());

                $("<tr></tr>").append(bill_user_phone)
                .append(bill_user_name)
                .append(bill_price_money)
                .append(bill_bill_time)
                .appendTo("#mainTable tbody");
            });
        }


        //分页信息
        function bill_page_Msg(result)
        {
            $("#pageMsg").empty();
            var tt=$("<div></div>").append("当前页码："+result.extend.queryBill.pageNum +"总共："
                    + result.extend.queryBill.pages+
                    "页,总记录数：" + result.extend.queryBill.total);
            $("#pageMsg").append(tt);

            currentPage = result.extend.queryBill.pageNum;
        }

        //分页条
        function bill_page_Info(result)
        {
            $("#pageInfo").empty();
            var ull = $("<ul></ul>").addClass("pagination pg-primary");
            //首页
            var sy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("首页")
                    .attr("href","#"));
            //前一页
            var qy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&laquo;")
                    .attr("href","#"));
            if(result.extend.queryBill.hasPreviousPage == false)
                {
                    sy.addClass("disabled");
                    qy.addClass("disabled");
                }
            else
                {
                //翻页
                sy.click(function()
                        {
                            queryBill(currentPhone,1);
                        });
                qy.click(function()
                        {
                            queryBill(currentPhone,result.extend.queryBill.pageNum -1);
                        });
                }

            //后一页
            var hy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&raquo;")
                    .attr("href","#"));

            //尾页
            var wy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("尾页")
                    .attr("href","#"));



            if(result.extend.queryBill.hasNextPage == false)
                {
                    hy.addClass("disabled");
                    wy.addClass("disabled");
                }
            else
                {
                 wy.click(function()
                            {
                                queryBill(currentPhone,result.extend.queryBill.pages);
                            });
                    hy.click(function()
                            {
                                queryBill(currentPhone,result.extend.queryBill.pageNum +1);
                            });
                }

            ull.append(sy).append(qy);
            //分页按钮
            $.each(result.extend.queryBill.navigatepageNums,function(index,item){
                 var ml = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append(item)
                            .attr("href","#"));
                 if(result.extend.queryBill.pageNum == item)
                     {
                        ml.addClass("active");
                     }
                 ml.click(function(){
                     queryBill(currentPhone,item);
                 });
                 ull.append(ml);
            })
             ull.append(hy).append(wy).appendTo("#pageInfo");
        }

