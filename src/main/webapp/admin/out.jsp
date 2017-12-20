<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
</head>
 <body style="margin: 0px;height: 100%;width:100%;">
       <iframe src="<%=basePath%>admin/left.jsp"
              style="height:100%;width:100%;border-width: 0px;">
       </iframe>

</body>
</html>
