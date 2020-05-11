$(function () {
    var loginname = $.cookie("realname");
    var loginTime = $.cookie("loginTime");
    $("#Name").text(loginname);
})
/*
* @Author: Marte
* @Date:   2018-11-28 14:46:11
* @Last Modified by:   Marte
* @Last Modified time: 2018-12-19 11:14:29
*/

/*$("#userMsgBtn").click(function()
{
    $("#userBillBtn").toggleClass("btnChangeGray");
    $("#userMsgBtn").toggleClass("btnChangeBlack");
});

$("#userBillBtn").click(function()
{
    $("#userBillBtn").toggleClass("btnChangeBlack");
    $("#userMsgBtn").toggleClass("btnChangeGray");
});*/
/*function wa()
{
	ajax()
	{
		url:""
	}
}
*/
function clickMsg()
{
    document.getElementById("userMsgBtn").className = "btnChangeBlack";
    document.getElementById("userMsgBtn").style.float = "left";
    document.getElementById("userBillBtn").className = "btnChangeGray";
    document.getElementById("userBillBtn").style.marginLeft = "20px";
    document.getElementById("userMsg").className ="cardBlock card-body";
    document.getElementById("userBill").className = "cardNone card-body";
}


function clickBill()
{
    document.getElementById("userMsgBtn").className = "btnChangeGray";
    document.getElementById("userMsgBtn").style.float = "left";
    document.getElementById("userBillBtn").className = "btnChangeBlack";
    document.getElementById("userBillBtn").style.marginLeft = "20px";
    document.getElementById("userMsg").className ="cardNone card-body";
    document.getElementById("userBill").className = "cardBlock card-body";
}

