/**
 *
 */
//绑定弹出新增模态框单击事件
$(document).on("click", ".add_btn", function() {
    $("#addMusicModal").modal({
        backdrop:"static"
    });

    /* Act on the event */
});

//绑定保存单击事件
$("#add_music_btn").click(function() {
    var fd = new FormData();
    var musicname = $("#add_music").val();
    var singername = $("#add_singer").val();
    var albumname = $("#add_album").val();
    var music_path_file = $("#add_music_flie").get(0).files[0];
    var lyrics_path_file = $("#add_lyrics_file").get(0).files[0];
    var music_img_file = $("#add_music_img").get(0).files[0];
    console.log("有file" + music_path_file);
    console.log(typeof(music_path_file));
    console.log("无file" + lyrics_path_file);
    console.log(typeof(lyrics_path_file));
    /*console.log(music_path_file);*/
    if("undefined" == typeof(music_path_file))
    {
        alert("歌曲文件不能为空");
        return;
    }

    if("undefined" == typeof(lyrics_path_file))
    {
        alert("歌词文件不能为空");
        return;
    }

    if("undefined" == typeof(music_img_file))
    {
        alert("歌曲图片不能为空");
        return;
    }

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
        url:"http://localhost:8080/BackstageMusic/addMusic",
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
               $("#addMusicModal").modal("hide");
               musicSetAll(totalRecord);
            }
        },
                error:function(e)
                {
                    console.log("出错啦");
                }
    });


/*    $.ajax({
        url: "http://localhost:8080/BackstageMusic/addMusic/",
        type: 'POST',
        dataType: 'json',
        data: $("#addMusicModal form").serialize(),
        success:function(result)
        {
         if(result.code == 2)
         {
            alert(result.msg);
        }
        else
        {
            console.log($("#addMusicModal form").serialize());
            $("#addMusicModal").modal("hide");
            musicSetAll(totalRecord);
        }

    }
    });
*/

    /* Act on the event */
});