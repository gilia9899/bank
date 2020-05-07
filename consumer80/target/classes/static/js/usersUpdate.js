/**
 *
 */
/*var userID = document.getElementById("userID");
var userName = document.getElementById("userName");
var phone = document.getElementById("phone");
var email = document.getElementById("email");*/
/*var userID = document.getElementById("userID");*/

 /*编辑按钮点击事件*/
 $(document).on("click", ".edit_btn", function(){
    getRole("#usersModal select");
/*    console.log(this);
    console.log($(this).attr("editid"));*/
    //获取个人信息
    getUsersMsg($(this).attr("editid"));
    //把用户Id传递到更新按钮上
    $("#user_update_btn").attr("ubid",$(this).attr("editid"));
    $("#usersModal").modal({
        backdrop:"static"
    });
    /* Act on the event */
});


 /*模态框获取权限选择器*/
 function getRole(select)
 {
    $(select).empty();
    console.log("我进来了");
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/getRole",
        type: "get",
        dataType:"json",
        success:function(result)
        {
                //$("#usersModal select").append('Some text')
                $.each(result.extend.getRole, function(index, item) {
                    var roleOption = $("<option></option>").append(item.role_name).attr("value", item.role_id);
                    roleOption.appendTo(select);
                    /* iterate through array or object*/
                });

                }
            });
}

/*获取个人信息*/
function getUsersMsg(id)
{
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/getOneUser/" + id,
        type: "get",
        dataType:"json",
        success:function(result)
        {
            var userMsg = result.extend.getOneUser;
            $("#userID").val(userMsg.user_id);
            $("#userName").val(userMsg.user_name);
            $("#phone").val(userMsg.user_phone);
/*            console.log(userMsg);
            console.log(userMsg.userInfo);*/
            $("#email").val(userMsg.userInfo.infor_email);
            $("#usersModal select").val([userMsg.role_id]);

        }
    });
}


//更新按钮
$("#user_update_btn").click(function()
{
    $.ajax({
        url: "http://localhost:8080/BackstageMusic/updateUser/" + $(this).attr("ubid"),
        type: "put",
        data:$("#usersModal form").serialize(),
        dataType:"json",
        success:function(result){
            //alert (result.msg);
            $("#usersModal").modal("hide");
            usersSetAll(currentPage);
        }
    });
});