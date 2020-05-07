$(function () {
    /**
     * 设置兑换列表
     */
    $.ajax({
        url: "transation/queryAll",
        type:"get",
        dataType:"json",
        success:function(result)
        {
            var list = result;

            var select = document.getElementById("select_change");
            $.each(list,function (index,item) {
                console.log(item);
                var opt = document.createElement("option");
                opt.setAttribute("rate",item.rate);
                opt.innerHTML = item.local;
                select.appendChild(opt);
            })
        }
    });

    var id =  $.cookie("role_id");

    console.log(id);
    $("#userid").val(id);
    setAccount(id);
})

/**
 * 设置用户帐号列表
 * @param userid
 */
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

/**
 * 设置余额显示
 */
function setBalance(){
    var select = document.getElementById("select_account")
    var value = select.options[select.selectedIndex].getAttribute("balance");

    $("#balance").val(value);
}

/**
 * 设置汇率显示
 */
function setRate(){
    var select = document.getElementById("select_change")
    var value = select.options[select.selectedIndex].getAttribute("rate");
    $("#rate").val(value);
}

/**
 * 获得兑换前的金额
 */
function setMoney() {
    var money = $("#money2").val() / $("#rate").val();
    money = money.toFixed(2);
    $("#money").val(money);
}

/**
 * 获得兑换后的金额
 */
function setMoney2() {
    var money2 = $("#money").val() * $("#rate").val();
    money2 = money2.toFixed(2);
    $("#money2").val(money2);
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

