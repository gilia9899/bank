var ulSong;
var ulSinger;
var ulAlbum;
var ulSummary;
var ulSongMsg;
var ulSingerMsg;

var aSong;
var aSinger;
var aAlbum;
var aSummary;
var aSongMsg;
var aSingerMsg;

ul = function ()
{
    ulSong = document.getElementById("ulSong");
    ulSinger = document.getElementById("ulSinger");
    ulAlbum = document.getElementById("ulAlbum");
    ulSummary = document.getElementById("ulSummary");

    ulSongMsg = document.getElementById("ulSongMsg");
    ulSingerMsg = document.getElementById("ulSingerMsg");
    if(ulSong.value == "")
    {
        ulSongMsg.innerHTML="歌名不能为空";
        console.log("1");
    }
    if(ulSinger.value == "")
    {
        ulSingerMsg.innerHTML="歌手不能为空";
        console.log("2");
    }

    var a =  false;
    if(a = true)
    {
       var ulf = document.getElementById("ulf");
        document.ulf.submit();
    }



}


ulSongonfocus = function()
{
    ulSongMsg.innerHTML="";
    console.log("3");
}

ulSingeronfocus = function()
{
    ulSingerMsg.innerHTML="";
    console.log("4");
}


