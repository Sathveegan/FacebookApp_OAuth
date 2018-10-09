<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="com.sample.oauth.core.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<html>
<body>
<h2>Hello World!</h2>

<a href="<%=fbConnection.getFBAuthUrl()%>"> login
		</a>
</body>
</html>
