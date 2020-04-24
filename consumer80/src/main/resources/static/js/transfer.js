
$)("#transfer_btn".click(function() {
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
