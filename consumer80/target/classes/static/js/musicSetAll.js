/*var mainTable =  document.getElementById("mainTable");
var pageMsg =  document.getElementById("pageMsg");
var pageInfo =  document.getElementById("pageInfo");
var usersModal = document.getElementById("usersModal");*/

//定义全局总记录数
var totalRecord;
 $(document).on("click", ".musicManager", function(){
    console.log("音乐管理");
    musicSetAll(1);

});

/*$("#musicManager").click(function(){
    console.log("音乐管理");
    musicSetAll(1);
});*/




/*分页查询*/
        function musicSetAll(pn)
        {
            console.log("随便");
            $.ajax({

                url:"http://localhost:8080/BackstageMusic/queryMusic",
                dataType:"json",
                data:"pn=" + pn,
                type:"get",
                success:function(result)
                {

                    console.log("进入music success");
                    music_left();
                    music_card_head();
                    music_option();
                    music_search();
                    addclick();
                    music_table_head();
                    music_table(result);
                    music_page_Msg(result);
                    music_page_Info(result);
                },
                error:function(e)
                {
                    console.log("出错啦");
                }
            });
            console.log("我没有进去ajax");
        }

        //标题
        function music_card_head()
        {
            $("#table_title").empty();
            /*console.log("我日你吗");*/
            $("<h4></h4>").addClass("page-title").appendTo("#table_title").text("音乐管理");
        }


        function music_option()
        {
/*                                                  <span id="base_info">基本信息</span>&nbsp;
                                        <span id="users_bill">账单查询</span>*/
            $("#selectOthers").empty();
            console.log("我进入了查询切换");
            var music = $("<a href='#'>音乐查询&nbsp;</a>").addClass("btnChangeBlack btnChangeGray music_select btn:hover");
            var album = $("<a  href='#'>专辑查询&nbsp;</a>").addClass("btnChangeBlack btnChangeGray album_select btn:hover");
            var singers = $("<a  href='#'>歌手查询</a>").addClass("btnChangeBlack btnChangeGray singers_select btn:hover");
            $(album).removeClass("btnChangeBlack");
            $(music).removeClass("btnChangeGray");
            $(singers).removeClass("btnChangeBlack");
            $("#selectOthers").append(music).append(album).append(singers);


        }


        //搜索信息
        function music_search()
        {
            $("#search").empty();
            //搜索按钮
            var search_i = $("<i></i>").addClass("la la-search search-icon");
            var search_span = $("<span></span>").addClass("input-group-text").append(search_i);
            var search_btn = $("<a href='#' ></a>").append(search_span);
            var search_div = $("<div></div>").addClass("input-group-append search_btn").append(search_btn);
            //歌曲名
            var search_music = $("<div></div>").addClass("input-group-append")
            .append($("<span></span>").addClass("input-group-text").text("歌名："));
            //歌手
            var search_singer = $("<div></div>").addClass("input-group-append")
            .append($("<span></span>").addClass("input-group-text").text("歌手："));
            //专辑
            var search_album = $("<div></div>").addClass("input-group-append")
            .append($("<span></span>").addClass("input-group-text").text("专辑："));
            //输入框
            var music_text = $("<input type='text' id='music_text' placeholder='Search ...' />").addClass("form-control");
            var singer_text = $("<input type='text' id='singer_text' placeholder='Search ...' />").addClass("form-control");
            var album_text = $("<input type='text' id='album_text' placeholder='Search ...' />").addClass("form-control");
            $("#search").append(search_music).append(music_text)
                        .append(search_singer).append(singer_text)
                        .append(search_album).append(album_text)
                        .append(search_div);


        }
        //上传与删除按钮
        function addclick()
        {
/*
                                           <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal" >
                                                歌曲上传
                                            </button>*/
            $("#btn").empty();
            $("<button tyoe='button'></button>").addClass("btn btn-primary add_btn").text("歌曲上传").appendTo("#btn");
            $("<span></span>").text("&nbsp;");
            $("<button tyoe='button'></button>").addClass("btn btn-danger delete_btn")
                                                .text("歌曲删除").appendTo("#btn");
        }



        //表头
        function music_table_head()
        {
            $("#mainTable thead").empty();

/*                                                        <th>
                                                            <div class="form-check">
                                                                <label class="form-check-label">
                                                                    <input class="form-check-input  select-all-checkbox" type="checkbox" data-select="checkbox" data-target=".task-select">
                                                                    <span class="form-check-sign"></span>
                                                                </label>
                                                            </div>
                                                        </th>*/
             var checkbox_div = $("<div></div>").addClass("form-check");
             var checkbox_label = $("<label></label>").addClass("form-check-label")
                    .append($("<input type='checkbox' />").addClass("form-check-input  select-all-checkbox music_check_all")
                                .attr("data-select","checkbox").attr("data-target",".task-select"))
                    .append($("<span></span>").addClass("form-check-sign")).appendTo(checkbox_div);


            var table_checkbox = $("<th></th>").append(checkbox_label);

            var table_id = $("<th>歌曲编号</th>");
            var table_music = $("<th>歌曲名</th>");
            var table_singer = $("<th>歌手</th>");
            var table_album = $("<th>专辑</th>");
            var table_uaction = $("<th>操作</th>").addClass("text-right");
            $("<tr></tr>").append(table_checkbox)
                        .append(table_id)
                        .append(table_music)
                        .append(table_singer)
                        .append(table_album)
                        .append(table_uaction)
                        .appendTo("#mainTable thead");



        }



        /*表单*/
        function music_table(result)
        {
            $("#mainTable tbody").empty();
            var t = result.extend.queryMusic.list;
            console.log(result);
            $.each(t,function(index,item){

                var checkbox_div = $("<div></div>").addClass("form-check");
                var checkbox_label=$("<label></label>").addClass("form-check-label")
                        .append($("<input  type='checkbox'/>").addClass("form-check-input task-select music_check_item"))
                        .append($("<span></span>").addClass("form-check-sign")).appendTo(checkbox_div);
                var table_checkbox=$("<td></td>").append(checkbox_label);
                var music_id = $("<td></td>").append(item.music_id);
                var music_name=$("<td></td>").append(item.music_name);
                var singers_name=$("<td></td>").append(item.singers.singer_name);
                var album_name=$("<td></td>").append(item.album.album_name);

               //编辑按钮
                var editBtn = $("<button></button>").addClass("btn btn-link btn-simple-primary music_edit_btn")
                .append($("<i></i>").addClass("la la-edit"));

                editBtn.attr("editid",item.music_id);

               //删除按钮
               var delBtn = $("<button></button>").addClass("btn btn-link btn-simple-danger music_delete_btn")
                .append($("<i></i>").addClass("la la-times"));

                delBtn.attr("delid",item.music_id);


                var btn=$("<td></td>").append($("<div></div>").addClass("form-button-action ")
                    .append(editBtn).append(delBtn))
                .addClass("td-actions text-right");

                $("<tr></tr>").append(table_checkbox)
                .append(music_id)
                .append(music_name)
                .append(singers_name)
                .append(album_name)
                .append(btn)
                .appendTo("#mainTable tbody");
            });
        }





        //分页信息
        function music_page_Msg(result)
        {
            $("#pageMsg").empty();
            var tt=$("<div></div>").append("当前页码："+result.extend.queryMusic.pageNum +"总共："
                    + result.extend.queryMusic.pages+
                    "页,总记录数：" + result.extend.queryMusic.total);
            $("#pageMsg").append(tt);

            currentPage = result.extend.queryMusic.pageNum;
            totalRecord = result.extend.queryMusic.total;
        }

        //分页条
        function music_page_Info(result)
        {
            $("#pageInfo").empty();
            var ull = $("<ul></ul>").addClass("pagination pg-primary");
            var sy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("首页")
                    .attr("href","#"));

            var qy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&laquo;")
                    .attr("href","#"));
            if(result.extend.queryMusic.hasPreviousPage == false)
                {
                    sy.addClass("disabled");
                    qy.addClass("disabled");
                }
            else
                {
                //翻页
                sy.click(function()
                        {
                            musicSetAll(1);
                        });
                qy.click(function()
                        {
                            musicSetAll(result.extend.queryMusic.pageNum -1);
                        });
                }


            var hy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&raquo;")
                    .attr("href","#"));
                /*          <li class="page-item"><a class="page-link"
                 href="${pageContext.request.contextPath  }/index?pn=${queryMusic.pages }">尾页</a>
                 </li> */
            var wy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("尾页")
                    .attr("href","#"));



            if(result.extend.queryMusic.hasNextPage == false)
                {
                    hy.addClass("disabled");
                    wy.addClass("disabled");
                }
            else
                {
                 wy.click(function()
                            {
                                musicSetAll(result.extend.queryMusic.pages);
                            });
                    hy.click(function()
                            {
                                musicSetAll(result.extend.queryMusic.pageNum +1);
                            });
                }

            ull.append(sy).append(qy);
            $.each(result.extend.queryMusic.navigatepageNums,function(index,item){
                 var ml = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append(item)
                            .attr("href","#"));
                 if(result.extend.queryMusic.pageNum == item)
                     {
                        ml.addClass("active");
                     }
                 ml.click(function(){
                     musicSetAll(item);
                 });
                 ull.append(ml);
            })
             ull.append(hy).append(wy).appendTo("#pageInfo");
        }

//左边条
function music_left()
{
    $("#musicli").addClass("active");
    $("#usersli").removeClass("active");
}


$(document).on("click", ".music_select", function(event) {
    musicSetAll(1);
    /* Act on the event */
});
