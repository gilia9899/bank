<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加账户</title>
</head>
<body>
	<h3>添加账户</h3>
	<form:form modelAttribute="account" method="post" action="${pageContext.request.contextPath}/">
		<form:hidden path="balance" value = "0"/>
		<form:hidden path="userid" value = "0"/>
		<p>密码：<form:password path="accpwd"></form:password><form:errors path="accpwd" cssStyle="color:red;"></form:errors></p>
		<p>开户行：<form:select path="bank">
			<form:option value="工商银行">工商银行</form:option>
			<form:option value="建设银行">建设银行</form:option>
			<form:option value="招商银行">招商银行</form:option>
			<form:option value="农业银行">农业银行</form:option>
		</form:select></p>
		<p>info1：<form:input path="info1"></form:input><form:errors path="info1" cssStyle="color:red;"></form:errors></p>
		<p>info2：<form:input path="info2"></form:input><form:errors path="info2" cssStyle="color:red;"></form:errors></p>
	</form:form>
</body>
</html>