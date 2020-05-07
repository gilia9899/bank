/**
 *
 */
//单个删除
$(document).on("click", ".music_delete_btn", function() {
    /*$(this).prop("checked");*/
    var music_id = $(this).attr("delid");
    var music_name = $(this).parents("tr").find("td:eq(2)").text();
        if(confirm("确认删除["+ music_name +"]吗？"))
        {
            $.ajax({
                url: "http://localhost:8080/BackstageMusic/deleteMusic/" + music_id,
                type: "delete",
                dataType: "json",
                success:function(result)
                {
                    console.log(result.msg);
                    /*alert(result.msg);*/
                    musicSetAll(currentPage);
                }
            })
        }
});


//全选全不选
$(document).on("click", ".music_check_all", function() {
    /*$(this).prop("checked");*/
    $(".music_check_item").prop("checked",$(this).prop("checked"));
    /* Act on the event */
});


$(document).on("click", ".music_check_item", function() {
    /*$(this).prop("checked");*/
    var check = $(".music_check_item:checked").length == $(".music_check_item").length;
    $(".music_check_all").prop("checked",check);
    /* Act on the event */
});


//批量删除
$(document).on("click", ".delete_btn", function() {
    var music_id = "";
    var music_name = "";
    $.each($(".music_check_item:checked"), function() {
        music_id += $(this).parents("tr").find("td:eq(1)").text() + "-";
        music_name += $(this).parents("tr").find("td:eq(2)").text() + ",";

        /*alert(music_name);*/
         /* iterate through array or object */
    });
    music_id = music_id.substring(0,music_id.length-1);
    music_name = music_name.substring(0,music_name.length-1);

        if(confirm("确认删除["+ music_name +"]吗？"))
        {
            $.ajax({
                url: "http://localhost:8080/BackstageMusic/deleteMusic/" + music_id,
                type: "delete",
                dataType: "json",
                success:function(result)
                {
                    console.log(result.msg);
                    /*alert(result.msg);*/
                    musicSetAll(currentPage);
                }
            })
        }


    /* Act on the event */
});