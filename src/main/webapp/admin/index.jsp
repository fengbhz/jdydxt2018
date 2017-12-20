<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
ArrayList roleList = new ArrayList();
try {
	String userName = (String)request.getSession().getAttribute("user");
	if (userName == null) {
		response.sendRedirect("/jdydxt/admin/login.jsp");
		return;
	}
} catch (Exception ex) {
	response.sendRedirect("/jdydxt/admin/login.jsp");
}
%>

<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="<%=basePath%>admin/admin_top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="200,*"   id="frame">
	<frame  id="iframepage"    src="<%=basePath%>admin/left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame  id="iframepage2"   src="<%=basePath%>platform/OrderList.html" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
 　<noframes></noframes>
  </frameset>
</html>
