/**
 *
 */

 /*编辑按钮点击事件*/
 $(document).on("click", ".music_edit_btn", function(){
/*    getRole("#updateMusicModal select");*/
/*    console.log(this);
    console.log($(this).attr("editid"));*/
    //获取个人信息
    getMusicMsg($(this).attr("editid"));
    //把用户Id传递到更新按钮上
    console.log("点击音乐更新事件");
    $("#music_update_btn").attr("ubid",$(this).attr("editid"));
    $("#updateMusicModal").modal({
        backdrop:"static"
    });
    /* Act on the event */
});
//获取歌曲信息
function getMusicMsg(music_id)
{
        $.ajax({
        url: "http://localhost:8080/BackstageMusic/getOneMusic/" + music_id,
        type: "get",
        dataType:"json",
        success:function(result)
        {
            console.log("我我我我我我w");
            console.log(result);
             console.log("这是啥？" + $("#updateMusicModal form").serialize());
            var musicMsg = result.extend.getOneMusic;
            $("#update_music_id").val(musicMsg.music_id);
            $("#update_music_name").val(musicMsg.music_name);
            $("#update_singer_name").val(musicMsg.singers.singer_name);
            $("#update_album_name").val(musicMsg.album.album_name);
/*            var userMsg = result.extend.getOneUser;
            $("#userID").val(userMsg.user_id);
            $("#userName").val(userMsg.user_name);
            $("#phone").val(userMsg.user_phone);
            console.log(userMsg);
            console.log(userMsg.userInfo);
            $("#email").val(userMsg.userInfo.infor_email);
            $("#usersModal select").val([userMsg.role_id]);*/

        }
    });
}
$("#music_update_btn").click(function() {
    var fd = new FormData();
    var musicid = $("#update_music_id").val();
    var musicname = $("#update_music_name").val();
    var singername = $("#update_singer_name").val();
    var albumname = $("#update_album_name").val();
    var music_path_file = $("#update_music_file").get(0).files[0];
    var lyrics_path_file = $("#update_lyrics_file").get(0).files[0];
    var music_img_file = $("#update_music_img").get(0).files[0];
    /*console.log(music_path_file);*/
    fd.append("music_id",musicid)
    fd.append("file",music_path_file);
    fd.append("file2",lyrics_path_file);
    fd.append("file3",music_img_file);
    fd.append("music_name",musicname);
    fd.append("singer_name",singername);
    fd.append("album_name",albumname);
console.log(fd);

    console.log("我真的点了吗?");
    console.log(fd);
    $.ajax({
        url:"http://localhost:8080/BackstageMusic/updateMusicWithFile",
        type: "POST",
        data: fd,
        contentType: false,
         processData:false,
        success:function(result)
        {
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
                error:function(e)
                {
                    console.log("出错啦");
                }
    });


/*    console.log($("#updateform").serialize());
    $.ajax({
        type: "POST",
        data: $("#updateform").serialize(),
        success:function(result)
        {
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
                error:function(e)
                {
                    console.log("出错啦");
                }
    });*/


    /* Act on the event */
});

/*$("#music_update_btn").click(function()
{
    var mid  = $(this).attr("ubid");
    console.log("我点了");

    $.ajax({
        url: "http://localhost:8080/BackstageMusic/updateMusic/" + $(this).attr("ubid"),
        type: "post",
        data:$("#updateMusicModal form").serialize(),
        dataType:"json",
        success:function(result)
        {
            //alert (result.msg);
            console.log(result);
            console.log($("#updateMusicModal form").serialize());
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }

           /* updateFile(mid);*/
/*            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
        error:function(e)
                {
                    console.log("出错啦");
                }
    });*/
/*    console.log("我点了");
    console.log($("#updateMusicModal form").serialize() + "&file=" + $("#file")) ;
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/updateMusic" ,
        type: "post",
        data:$("#updateMusicModal form").serialize() + "&file=" + $("#file"),
        dataType:"json",
        success:function(result)
        {
            //alert (result.msg);
            console.log(result);
            console.log($("#updateMusicModal form").serialize());
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
        error:function(e)
                {
                    console.log("出错啦");
                }
    });*/
/*});*/



/*    var formData = new FormData();
    var uploadFile = $("#update_lyrics_file").get(0).files[0];
    var music_name = $("#update_music_name").val();
    var singer_name = $("#update_singer_name").val();
    var album_name = $("#update_album_name").val();
    formData.append("uploadFile",uploadFile);
    formData.append("music_name",music_name);
    formData.append("singer_name",singer_name);
    formData.append("album_name",album_name);

    $.ajax({
        url: "http://localhost:8080/BackstageMusic/updateMusic",
        type: "post",
        data: formData,
        async: false,
        cache: false,
        processData:false,
        contextType:false,
        success:function(result)
        {
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
                error:function(e)
                {
                    console.log("出错啦");
                }
    });*/

/*    var form = new FormData(document.getElementById("updateform"));
    console.log(form);
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/updateMusic",
        type: "post",
        data: form,
        processData:false,
        contextType:false,
        success:function(result)
        {
            if(result.code == 2)
            {
                alert(result.msg);
            }
            else
            {
                console.log("修改成功");
               $("#updateMusicModal").modal("hide");
               musicSetAll(currentPage);
            }
        },
                error:function(e)
                {
                    console.log("出错啦");
                }
    });*/

