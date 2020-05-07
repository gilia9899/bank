/**
 *
 */
var currentMusicName
var currentSingerName
var currentAlbumName

 /*查询按钮点击事件*/
 $(document).on("click", ".search_btn", function(){
    console.log("1551");
    currentMusicName = $("#music_text").val();
    console.log("currentMusicName= "+ currentMusicName);
    currentSingerName = $("#singer_text").val();
    console.log("currentSingerName= "+ currentSingerName);
    currentAlbumName = $("#album_text").val();
    console.log("currentAlbumName= "+ currentAlbumName);

    if(currentMusicName == "" || currentMusicName == " ")
    {
        currentMusicName = null;
    }
    else
    {
        currentMusicName = encodeURI(currentMusicName);
    }
    if(currentSingerName == "" || currentSingerName == " ")
    {
        currentSingerName = null;
    }
    else
    {
        currentSingerName = encodeURI(currentSingerName);
    }
    if(currentAlbumName == "" || currentAlbumName == " ")
    {
        currentAlbumName = null;
    }
    else
    {
        currentAlbumName = encodeURI(currentAlbumName);
    }
    console.log("你们编码后是！" + currentMusicName + currentSingerName+ currentAlbumName);
    getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,1);
});




function getMusicQueryMsg(musicName,singerName,albumName,pn)
{
	 console.log("你们是!" + musicName + singerName + albumName);
/*    console.log("你们是!" + musicName + singerName + albumName);

    var username = encodeURI(musicName);
    var singername = encodeURI(singerName);
    var albumname = encodeURI(albumName);
    console.log("你们编码后是！" + username + singername+ albumname);*/
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/queryMusic/"
        + musicName + "/" + singerName + "/" + albumName + "/" + pn,
/*        data="pn=" + pn,*/
        type: "get",
        /*data:{musicName,singerName,albumName,pn},*/
        dataType:"json",
        success:function(result){
            //alert (result.msg);
            console.log("你点我了？");
            console.log(result);
            music_table(result);
            music_page_Msg(result);
            query_music_page_Info(result);
        }
    });
}

        function query_music_page_Info(result)
        {
            $("#pageInfo").empty();
            var ull = $("<ul></ul>").addClass("pagination pg-primary");
            //首页
            var sy=$("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("首页")
                    .attr("href","#"));
            //前一页
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
                            getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,1);
                        });
                qy.click(function()
                        {
                            getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,result.extend.queryMusic.pageNum -1);
                        });
                }

            //后一页
            var hy = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append("&raquo;")
                    .attr("href","#"));
                /*          <li class="page-item"><a class="page-link"
                 href="${pageContext.request.contextPath  }/index?pn=${queryMusic.pages }">尾页</a>
                 </li> */
            //尾页
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
                                getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,result.extend.queryMusic.pages);
                            });
                    hy.click(function()
                            {
                                getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,result.extend.queryMusic.pageNum +1);
                            });
                }

            ull.append(sy).append(qy);
            //分页按钮
            $.each(result.extend.queryMusic.navigatepageNums,function(index,item){
                 var ml = $("<li></li>").addClass("page-item").append($("<a></a>").addClass("page-link").append(item)
                            .attr("href","#"));
                 if(result.extend.queryMusic.pageNum == item)
                     {
                        ml.addClass("active");
                     }
                 ml.click(function(){
                     getMusicQueryMsg(currentMusicName,currentSingerName,currentAlbumName,item);
                 });
                 ull.append(ml);
            })
             ull.append(hy).append(wy).appendTo("#pageInfo");
        }