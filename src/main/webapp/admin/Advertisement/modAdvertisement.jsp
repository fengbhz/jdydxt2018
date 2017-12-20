<%@ page language="java"
	import="java.util.*,java.sql.*,com.vdong.commons.db.*"
	pageEncoding="UTF-8"%>
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

		<title>广告信息修改</title>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
     <LINK href="css/admin.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
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
	function html2Escape(sHtml) {
 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
		
function alterSystem(){
if($("#file")==""){
alert("您还没有选择图片");
return;
}
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
			<%
				Connection conn = DBManager.getConnection();
				String id = request.getParameter("id");
				String sql = "select * from hotelad where id='" + id + "'";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="90%" border="0" align="center" cellspacing="1"
				style="background-color:#d2d2da;line-height: 250%;">
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							广告分类：
						</div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label>
							<input name="adKind" type="text" id="adKind"
								value=<%=rs.getString("adKind")%>>
							<input name="id" type="hidden" id="id" value='<%=id%>'>
						</label>
					</td>
				</tr>
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							广告标题：
						</div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label>
							<input name="adTitle" type="text" id="adTitle"
								value=<%=rs.getString("adTitle")%>>
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
                    <input type="hidden"  name="info"  id="info" value="">
                     <input type="hidden"  name="infos"   id="infos" value="<%=rs.getString("adContent")%>">
					</td>
				</tr>

				

				

				

				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						<label>
							<div align="center">
								<input type="button" name="Submit" onclick="alterSystem();" value="提交"> 

							</div>
						</label>
					</td>
				</tr>
			</table>
			<%
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			%>
		</form>
	</body>
	<script type="text/javascript">
	   $(window).load(function() {  
      $(".view").html($("#infos").val());
      }); 
ue.ready(function(){
           ue.setContent($("#infos").val()); 
});
	
	</script>
</html>
