$(function () {
    /**
     * ���öһ��б�
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
 * �����û��ʺ��б�
 * @param userid
 */
function setAccount(userid){
    $.ajax({
        url: "transation/queryAccountByUserid/" + userid,
        type:"get",
        dataType:"json",
        success:function(result)
        {
            console.log("�����ɶ��");
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
 * ���������ʾ
 */
function setBalance(){
    var select = document.getElementById("select_account")
    var value = select.options[select.selectedIndex].getAttribute("balance");

    $("#balance").val(value);
}

/**
 * ���û�����ʾ
 */
function setRate(){
    var select = document.getElementById("select_change")
    var value = select.options[select.selectedIndex].getAttribute("rate");
    $("#rate").val(value);
}

/**
 * ��öһ�ǰ�Ľ��
 */
function setMoney() {
    var money = $("#money2").val() / $("#rate").val();
    money = money.toFixed(2);
    $("#money").val(money);
}

/**
 * ��öһ���Ľ��
 */
function setMoney2() {
    var money2 = $("#money").val() * $("#rate").val();
    money2 = money2.toFixed(2);
    $("#money2").val(money2);
}
/**
 * ת��
 */
$("#transfer_btn").click(function() {
        console.log("�ҽ�������" + $("#transfer_form").serialize());
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

