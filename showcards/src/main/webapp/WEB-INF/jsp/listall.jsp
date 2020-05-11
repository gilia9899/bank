<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="/page-tags" prefix="pager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
<title>展示所有银行卡页面</title>
</head>
<body>
	<table>
		<tr><th>卡号</th><th>用户id</th><th>余额</th><th>开户行</th><th>创建日期</th><th>info1</th><th>info2</th></tr>
		<tbody id="tbody">
			<c:forEach items="${accounts}" varStatus="vs" var="acc">
				<tr><td>#{acc.accno }</td><td>#{acc.userid}</td><td>#{acc.balance}</td><td>#{acc.bank}</td><td>#{acc.createdate}</td><td>#{acc.info1}</td><td>#{acc.info2}</td></tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<pager:page pageIndex="${pageinfo.pageNum}" pageSize="${pageinfo.pages}" recordCount="${pageinfo.total }"/>
	</div>
	
	<script type="text/javascript">
	var url = "${pageContext.request.contextPath}/all?pageIndex=";
	$("a[data-type='page']").on("click",function(){
		window.location.href=url+$(this).html();
		
	})
	
	$("a[data-type='nextpage']").on("click",function(){
		var nextpage = parseInt($(".current").html().trim())+1;
		window.location.href=url+nextpage;
	})
	$("a[data-type='prepage']").on("click",function(){
		var prepage = parseInt($(".current").html().trim())-1;
		window.location.href=url+prepage;
	})
	$("#pager_jump_btn").on("click",function(){
		
		window.location.href=url+$("#pager_jump_page_size").val();
	})
	</script>
</body>
</html>