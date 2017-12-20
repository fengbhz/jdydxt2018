<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>广告信息添加</title>

		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		  <LINK href="css/admin.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
		<style type="text/css">
<!--
.STYLE3 {
	font-size: 16px
}
-->
</style>

<script type="text/javascript">
   var ue = UE.getEditor('editor'); 
		
		// 编码	
	function html2Escape(sHtml) {
       return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
     }
			
			
			
	function alterSystem(){
	$("#info").val(html2Escape(UE.getEditor('editor').getContent()));
        $("#from1").submit();
	}
</script>
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<form id="from1"  action="msg/addAd.html" method="post" enctype="multipart/form-data">
		<input type="hidden"  id="info"  name="info">
			<table width="70%" border="0" align="center" cellspacing="1"
			style="background-color:#d2d2da;line-height: 250%;">

					<input name="id" type="hidden" id="id">
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							广告分类：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="adKind" type="text" id="adKind">
						</label>
					</td>
				</tr>

				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							广告标题：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="adTitle" type="text" id="adTitle">
						</label>
					</td>
				</tr>

				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							广告图片：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input type="file" name="adPic" id="adPic">
						</label>
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							 广告内容：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
					 <div>
                    <script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
						 </div>
					</td>
				</tr>
				



				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
							<div align="center" style="padding-left:50px">
								<input type="button"    onclick="alterSystem()"   name="Submit" value="提交">
							</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
