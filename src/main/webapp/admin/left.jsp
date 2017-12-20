<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font: 12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}

#container {
	width: 182px;
}

H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;
}

H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}

.content {
	width: 182px;
	height: 26px;
}

.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}

.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}

.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px, 0px, 0px, 0px);
}

.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}

.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>
<body>
	<table width="100%" height="280" border="0" cellpadding="0"
		cellspacing="0" bgcolor="#EEF2FB">
		<tr>
			<td width="182" valign="top">
				<div id="container">
					<c:if test="${userLogin.code=='VADMIN'}">
				 <h1 class="type">
						<a href="javascript:void(0)">系统参数</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"/></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/sysPro.jsp" target="main">参数配置</a>
							</li>
						</ul> 
					</div>
					<h1 class="type">
						<a href="javascript:void(0)">用户维护</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/admin/list.jsp" target="main">用户维护</a>
							</li>
						</ul>
					</div>
					</c:if>
					
					<c:if test="${userLogin.code=='PT_MANAGER'}">
					<h1 class="type">
						<a href="javascript:void(0)">问候管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/index/alterWelcome.jsp"
								target="main">欢迎语维护</a></li>

						</ul>
					</div>
					
					<h1 class="type">
						<a href="javascript:void(0)">酒店管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/jiudian/list.jsp" target="main">酒店列表</a></li>
						</ul>
						<ul class="MM">
							<li><a href="<%=path%>/admin/jiudian/kind.jsp" target="main">酒店分类</a></li>
						</ul>
					</div>
					
					
					<h1 class="type">
						<a href="javascript:void(0)">房型管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/product/list.jsp" target="main">房型列表</a></li>
						</ul>
						<ul class="MM">
							<li><a href="<%=path%>/admin/product/kind.jsp" target="main">房型分类</a></li>
						</ul>
					</div>
					
					<h1 class="type">
						<a href="javascript:void(0)">发现管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">

							<li><a href="<%=path%>/admin/Advertisement/list.jsp"
								target="main">发现列表</a></li>
						</ul>
					</div>
					
					</c:if>
					<h1 class="type">
						<a href="javascript:void(0)">订单管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">

							<li><a href="<%=basePath%>platform/OrderList.html" target="main">订单列表</a>
							</li>
						</ul>
					</div>
					<c:if test="${userLogin.code=='PT_MANAGER'}">
					<h1 class="type">
						<a href="javascript:void(0)">业主管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/owner/owner.jsp" target="main">业主列表</a></li>
						    <li><a href="<%=path%>/admin/owner/HolidayMoney.jsp" target="main">赠送管理</a></li>
						    <li><a href="<%=path%>/admin/owner/friendManager.jsp" target="main">亲友管理</a></li>
						</ul>
					</div>
					
					<h1 class="type">
						<a href="javascript:void(0)">页面管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/news/list.jsp" target="main">页面管理</a>
							</li>
						</ul>
					</div>
					
					<h1 class="type">
						<a href="javascript:void(0)">游客管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/menu_topline.gif" width="182"
									height="5" /></td>
							</tr>
						</table>
						<ul class="MM">
							<li><a href="<%=path%>/admin/user/list.jsp" target="main">游客信息维护</a>
							</li>

						</ul>
					</div>

						<h1 class="type">
							<a href="javascript:void(0)">财务管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="images/menu_topline.gif" width="182"
											 height="5" /></td>
								</tr>
							</table>
							<ul class="MM">
								<li><a href="<%=path%>/admin/yuding/coinManager.jsp" target="main">充值与扣款</a>
								</li>

							</ul>
						</div>
					 </c:if>
				</div> <script type="text/javascript">
					var contents = document.getElementsByClassName('content');
					var toggles = document.getElementsByClassName('type');

					var myAccordion = new fx.Accordion(toggles, contents, {
						opacity : true,
						duration : 400
					});
					myAccordion.showThisHideOpen(contents[0]);
				</script>
			</td>
		</tr>
	</table>
</body>
</html>
