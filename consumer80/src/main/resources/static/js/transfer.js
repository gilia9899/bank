$(function () {
    setAccount(1001);
})


function setAccount(userid){
    $.ajax({
        url: "transation/queryAccountByUserid/" + userid,
        type:"get",
        dataType:"json",
        success:function(result)
        {
            console.log("获得那啥子");
            console.log(result);
            var list = result.data;
            var select = document.getElementById("select_account");
            $.each(list,function (index,item) {
                var opt = document.createElement("option");
                opt.setAttribute("balance",item.balance);
                opt.innerHTML = item.accno;
                select.appendChild(opt);
            })
        }
    });
}

function setBalance(){
    var select = document.getElementById("select_account")
    var value = select.options[select.selectedIndex].getAttribute("balance");
    console.log(value);

    $("#balance").val(value);
}

/**
 * 转账
 */
$("#transfer_btn").click(function() {
        console.log("我进来了吗" + $("#transfer_form").serialize());
        $.ajax({
            url: "transation/intraBankTransfer",
            type:"post",
            dataType:"json",
            data:$("#transfer_form").serialize(),
            success:function(result)
            {
                console.log(result);
                alert(result.message);
            }
        });
    /* Act on the event */
});
