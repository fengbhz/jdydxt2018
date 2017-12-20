<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*" pageEncoding="UTF-8"%>
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

		<title>酒店添加</title>

		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<style type="text/css">
			<!--
			.STYLE3 {
				font-size: 16px
			}
			-->
		</style>
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function up() {
				var pop = new Popup( {
					contentType : 1,
					isReloadOnClose : false,
					width : 400,
					height : 200
				});
				pop.setContent("contentUrl", "<%=path%>/upload/upload.jsp");
				pop.setContent("title", "文件上传");
				pop.build();
				pop.show();
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
		<form action="hotel/addHotel.html" method="post">
			<table width="70%" border="0" align="center" cellspacing="1"
				  style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							酒店名称：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="hotelName" type="text" id="hotelName">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							酒店分类：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<select name="hotelid">
							<%
							Connection conn = DBManager.getConnection(); 
							String sqlkind = "select id, jiudianKind from hotelsupplierkind";
							PreparedStatement pstmtkind = conn.prepareStatement(sqlkind);
							ResultSet rskind = pstmtkind.executeQuery();
							while (rskind.next()) {
							%>
								<option value=<%=rskind.getString("id") %>>
									<%=rskind.getString("jiudianKind") %>
								</option>
								<%} %>
							</select>
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							排序：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="order" type="text" id="order">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							酒店首图：
						</div>
					</td>
					<td width="75%" bgcolor="#FFFFFF">
			    		<label>
							<input type="text" name="hotelPic" id="hotelPic" size="30" readonly="readonly"/>
							<input type="button" value="上传" onclick="up()" />
							<input type="hidden" name="hotelPic" id="hotelPic"/>
						</label>
			    	</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							账号：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="hotelAccount" type="text" id="hotelAccount" onblur="checkAccount()">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							密码：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="hotelPass" type="password" id="hotelPass">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							联系电话：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="tel" type="text" id="tel">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							所在地区：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="area" type="text" id="area"  >
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							详细地址：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="address" type="text" id="address" onblur="mover()">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							坐标采集：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="coordinate" type="text" id="coordinate">
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							备注：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<textarea name="remark" id="remark" style="width: 600px; height: 250px;"></textarea>
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						<label>
							<div align="center">
								<input type="button" name="Submit" onclick="save();" value="提交">
								<input type="reset" name="Submit2" value="重置">
							</div>
						</label>
					</td>
				</tr>
			</table>
		</form>

		<script type="text/javascript">
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="info"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
							'bold', 'italic', 'underline', 'removeformat', '|',
							'justifyleft', 'justifycenter', 'justifyright',
							'insertorderedlist', 'insertunorderedlist' ]
				});
			});
			
		
	function save() {
		if (checkAccount()) {
			document.forms[0].submit();
		}
	}

	function mover() {
		var adress = $("#address").val();
		if (adress != "") {
			$.ajax( {
				url : "SosoMapAction",
				data : {
					"adress" : adress
				},
				cache : false,
				async : false,
				type : "post",
				dataType : "json",
				success : function(data) {
					var lgn = data.date.lng + "," + data.date.lat;
					$("#coordinate").val(lgn);
				}
			});

		}
	}

		function checkAccount() {
			var account = $("#hotelAccount").val();
			var  flag=true;
			$.ajax( {
				url : "<%=basePath%>hotel/checkAccount.html",
							data : {
								"account" : account
							},
							cache : false,
							async : false,
							type : "post",
							dataType : "json",
							success : function(data) {
							 if(data.resource){
								alert("该账号已经存在，请更换！");
								 $("#hotelAccount").val("");
								 $("#hotelAccount").focus();
								 flag= false;
							 }else{
								 flag= true;
							 }
							}
						});
					   return flag;
					}
		</script>
	</body>
</html>
