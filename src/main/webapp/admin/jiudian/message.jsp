<%@ page language="java"
	import="java.util.*,java.sql.*,com.vdong.commons.db.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mobile = request.getParameter("mobile");
	String checkCode = request.getParameter("checkCode");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>短信验证</title>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<script type="text/javascript">
		
		</script>
		<style type="text/css">
		
		</style>
	</head>
	<body>
		<script language="JavaScript"> 
			location.href = "http://175.25.48.210:6688/sms.aspx?action=send&userid=1410&account=zhangyue456&password=a12345678&mobile=<%=mobile%>&content=【爱度假】您的验证码是：<%=checkCode%>&sendTime=&extno=";
		</script>
	</body>
</html>
