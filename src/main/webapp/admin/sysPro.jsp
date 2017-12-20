<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			function upCodePic() {
				var pop = new Popup( {
					contentType : 1,
					isReloadOnClose : false,
					width : 400,
					height : 200
				});
				pop.setContent("contentUrl", "<%=path%>/upload/upload.jsp");
				pop.setContent("title", "二维码上传");
				pop.build();
				pop.show();
			}
			
			function upCer() {
				var pop = new Popup( {
					contentType : 1,
					isReloadOnClose : false,
					width : 400,
					height : 200
				});
				pop.setContent("contentUrl", "<%=path%>/upload/uploadCer.jsp");
				pop.setContent("title", "证书上传");
				pop.build();
				pop.show();
			}
			
			function upKey() {
				var pop = new Popup( {
					contentType : 1,
					isReloadOnClose : false,
					width : 400,
					height : 200
				});
				pop.setContent("contentUrl", "<%=path%>/upload/uploadKey.jsp");
				pop.setContent("title", "密码文件上传");
				pop.build();
				pop.show();
			}
			
			function alterSystem(){
				$("#systemForm").submit();
			}
			
			function perView(path){
				window.location.href = "<%=path%>" + path;
			}
		</script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
		<form id="systemForm" action="HotelSystemAction" method="post">
			<%
				Connection conn = DBManager.getConnection();
				String id = request.getParameter("id");
				String sql = "select publicSignalName, codePic, kfPhone,appId, appsecret, commercialTenantNo, secretKey, payCertificateFile, certificateKeyFile from hotelsystem ";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
				<tr bgcolor="#EEF4EA">
			    	<td colspan="2" background="<%=path %>/images/wbg.gif" class='title'><span>微信公众号配置</span></td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">公众号名称：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="publicSignalName" type="text" id="publicSignalName" value='<%=rs.getString("publicSignalName")%>'>
						</label>
			    	</td>
			  	</tr>
			  	
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">客服电话：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="kfPhone" type="text" id="kfPhone" value='<%=rs.getString("kfPhone")%>'>
						</label>
			    	</td>
			  	</tr>
			 	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">二维码图片：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<%-- <input name="codePic" type="text" id="codePic" value='<%=rs.getString("codePic")%>'> --%>
							<input type="text" name="fujian" id="fujian" size="30" readonly="readonly" value='<%=rs.getString("codePic")%>'/>
							<input type="button" value="上传" onclick="upCodePic()" />
							<input type="button" value="预览" onclick="perView('<%=rs.getString("codePic")%>')" />
							<input type="hidden" name="codePic" id="codePic" value='<%=rs.getString("codePic")%>'/>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">AppId：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="appId" type="text" id="appId" value='<%=rs.getString("appId")%>'>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">Appsecret：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="appsecret" type="text" id="appsecret" value='<%=rs.getString("appsecret")%>'>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">商户号：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="commercialTenantNo" type="text" id="commercialTenantNo" value='<%=rs.getString("commercialTenantNo")%>'>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">支付密钥：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input name="secretKey" type="text" id="secretKey" value='<%=rs.getString("secretKey")%>'>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">支付证书文件：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<%-- <input name="payCertificateFile" type="text" id="payCertificateFile" value='<%=rs.getString("payCertificateFile")%>'> --%>
							<input type="text" name="payCertificate" id="payCertificate" size="30" readonly="readonly" value='<%=rs.getString("payCertificateFile")%>'/>
							<input type="button" value="上传" onclick="upCer()" />
							<input type="button" value="下载" onclick="perView('<%=rs.getString("payCertificateFile")%>')" />
							<input type="hidden" name="payCertificateFile" id="payCertificateFile" value='<%=rs.getString("payCertificateFile")%>'/>
						</label>
			    	</td>
			  	</tr>
			  	<tr bgcolor="#FFFFFF">
			    	<td width="25%" bgcolor="#FFFFFF" align="right">证书密码文件：</td>
			    	<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<%-- <input name="certificateKeyFile" type="text" id="certificateKeyFile" value='<%=rs.getString("certificateKeyFile")%>'> --%>
							<input type="text" name="certificateKey" id="certificateKey" size="30" readonly="readonly" value='<%=rs.getString("certificateKeyFile")%>'/>
							<input type="button" value="上传" onclick="upKey()" />
							<input type="button" value="下载" onclick="perView('<%=rs.getString("certificateKeyFile")%>')" />
							<input type="hidden" name="certificateKeyFile" id="certificateKeyFile" value='<%=rs.getString("certificateKeyFile")%>'/>
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
			
			<div style="text-align: center; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
				<input id="systemBtn" type="button" onclick="alterSystem()" value="保存" style="width:80px; height:30px; background:url(images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
			</div>
		</form>
	</body>
</html>
