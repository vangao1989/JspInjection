<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.alibaba.fastjson.JSON"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp bean test</title>
</head>
<body>
	<table border="0"><tr><td><form action="/handleJava" method="POST"><fieldset><legend>java runtime input:</legend>javaCode:<br>
	<textarea id="javaCode" name="javaCode" rows="35" cols="100">${javaCode}</textarea>
	<br><br><input type="submit" value="Submit"></fieldset></form></td><td>
	<form><fieldset><legend>java runtime output:</legend>result:<br><textarea id="result" name="result" rows="35" cols="100">
	
		<%@page	import="com.dianwoba.redcliff.mongo.provider.RiderSpaceProvider"%> 
		
		<%!String toString(Object o) {
			if (o == null) {
				return "";
			}
			return JSON.toJSONString(o);
		}%>	
			
		<%
			ServletContext context = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			RiderSpaceProvider riderSpaceProvider = (RiderSpaceProvider) ctx.getBean("riderSpaceProvider");
			out.println(toString(riderSpaceProvider.findRiderSpace(3, 11452)));
		%>
		
	</textarea><br><br><button type="button" onclick="document.getElementById('javaCode').value='';document.getElementById('result').value='';">clear</button></fieldset></form></td></tr></table>

</body>
</html>