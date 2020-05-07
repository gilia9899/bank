/**
 *
 */
$(function () {
    var a = $("#bill_search").val();
    currentPhone = a;
    queryBill(1);
});


        function queryBill(pn)
        {
            console.log("随便");
            $.ajax({
                url:"/fandall/1 " ,
                dataType:"json",
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



//         function info_or_bill2()
//         {
// /*                                                  <span id="base_info">基本信息</span>&nbsp;
//                                         <span id="users_bill">账单查询</span>*/
//             $("#selectOthers").empty();
//             console.log("我进入了查询切换");
//             var info = $("<a href='#'>基本信息&nbsp;</a>").addClass("btnChangeBlack btnChangeGray base_info btn:hover");
//             var bill = $("<a  href='#'>账单查询</a>").addClass("btnChangeBlack btnChangeGray users_bill btn:hover");
//             $(info).removeClass("btnChangeBlack");
//             $(bill).removeClass("btnChangeGray");
//             $("#selectOthers").append(info).append(bill);
//
//
//         }


//表头
//         function bill_table_head()
//         {
//             $("#mainTable thead").empty();
//
//             var bill_user_phone = $("<th>用户账号</th>");//user_phone
//             var bill_user_name = $("<th>用户昵称</th>"); //user_name
//             var bill_price_money = $("<th>支付金额</th>");//price_money
//             var bill_bill_time = $("<th>支付时间</th>");//bill_time
//
//             $("<tr></tr>").append(bill_user_phone)
//             .append(bill_user_name)
//             .append(bill_price_money)
//             .append(bill_bill_time)
//             .appendTo("#mainTable thead");
//
//         }


        /*表单*/
        function bill_table(result)
        {
            $("#mainTable tbody").empty();
            var t = result.extend.queryBill.list;
            console.log(result);




            $.each(t,function(index,item){
                // var billT = parseInt(item.bill_time);
                    //实例化一个新的日期格式，使用1970 年 1 月 1 日至今的毫秒数为参数
                // var payDate = new Date(billT);



                var bill_localnum = $("<td></td>").append(item.exchange.localnum);
                var bill_local = $("<td></td>").append(item.exchange.local);
                var bill_rate = $("<td></td>").append(item.exchange.rate);

                $("<tr></tr>").append(bill_localnum)
                .append(bill_local)
                .append(bill_rate)
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

