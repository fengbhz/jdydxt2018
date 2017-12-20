<%@ page language="java"
         import="java.util.*,java.sql.*,com.vdong.commons.db.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>产品信息修改</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=basePath%>webuploader/webuploader.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=basePath%>webuploader/webuploader.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
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

var ue = UE.getEditor('editor'); 
	function html2Escape(sHtml) {
 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
		
function alterSystem(){
if($("#file")==""){
alert("您还没有选择图片");
return;
}
$("#details").val(html2Escape(UE.getEditor('editor').getContent()));
	 var pic="";
         $("li").each(function(){
         pic+=($(this).attr("attach")+",");
       });
       
      $("#picarry").val(pic);
      $("#from1").submit();
}
$(document).ready(function(){
	var service = $("#service").val().split(",");
	var installation = $("#installation").val().split(",");
	
	for(var j=0;j<service.length;j++){
	for(var i=0;i<$("[name='serviceCheckBox']").length;i++){
	var  obj=$("[name='serviceCheckBox']")[i];
		if(service[j] == $(obj).val()){
		$("[name='serviceCheckBox']")[i].checked=true;
		}
	}
	}
	
	for(var j=0;j<installation.length;j++){
	for(var i=0;i<$("[name='installationCheckBox']").length;i++){
	var  obj2=$("[name='installationCheckBox']")[i];
		if(installation[j] == $(obj2).val()){
			$("[name='installationCheckBox']")[i].checked=true;
		}
	}
	}
});
</script>
<style type="text/css">
<!--
.STYLE3 {
	font-size: 16px
}
-->
</style>
</head>
<body>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<form action="platform/toSavelogo.html"  id="from1" method="post"  enctype="multipart/form-data">
		<%
			Connection conn = DBManager.getConnection();
			String id = request.getParameter("id");
			String sqlRoom = "SELECT * FROM hotelroom WHERE id = " + id;
			PreparedStatement stat = conn.prepareStatement(sqlRoom);
			ResultSet rsRoom = stat.executeQuery();
			rsRoom.next();
		%>
		<table width="70%" border="0" align="center" cellspacing="1"   style=" background-color:d2d2da " >
			<input name="id" type="hidden" id="id" value='<%=id%>'>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">排序：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><label><input
						name="sid" type="text" id="sid" value=<%=rsRoom.getString("id")%>></label>
				</td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">供应商：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><label><select name="pid">
							<%
								String sqlSupplier = "select id, hotelName from hotelsupplier";
								PreparedStatement pstmSupplier = conn.prepareStatement(sqlSupplier);
								ResultSet rsSupplier = pstmSupplier.executeQuery();
								while (rsSupplier.next()) {
							%>
							<option value=<%=rsSupplier.getString("id")%>
								<%if (rsSupplier.getString("id").equals(rsRoom.getString("pid"))) {%>
								selected="selected" <%}%>><%=rsSupplier.getString("hotelName")%></option>
							<%
								}
							%>
					</select></label></td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">产品分类：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><label> <select
						name="other">
							<%
								String sqlkind = "select id, hotelKind from hotelkind";
								PreparedStatement pstmtkind = conn.prepareStatement(sqlkind);
								ResultSet rstkind = pstmtkind.executeQuery();
								while (rstkind.next()) {
							%>
							<option value=<%=rstkind.getString("id")%>
								<%if (rstkind.getString("id").equals(rsRoom.getString("other"))) {%>
								selected="selected" <%}%>>
								<%=rstkind.getString("hotelKind")%>
							</option>
							<%
								}
							%>
					</select>
				</label></td>
			</tr>
			<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							产品属性：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label><select name="property">
						<option value="0"
						<%if ("0".equals(rsRoom.getString("property"))) {%>
						selected="selected" <%}%>>推荐</option>
						<option value="1"
						<%if ("1".equals(rsRoom.getString("property"))) {%>
						selected="selected" <%}%>>热卖</option>
						<option value="2"
						<%if ("2".equals(rsRoom.getString("property"))) {%>
						selected="selected" <%}%>>特价</option>
						<option value="3"
						<%if ("3".equals(rsRoom.getString("property"))) {%>
						selected="selected" <%}%>>新品</option>
					</select></label>
					</td>
				</tr>
			  <tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">封面图片：</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label><input type="file" name="file" id="file"></label>
					</td>
				</tr>
			<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">图片预览：</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<img src='<%=basePath%><%=rsRoom.getString("itemPic")%>' >
						<input type="hidden" value="<%=rsRoom.getString("itemPic")%>" name="picUrl" id="picUrl" >
					</td>
				</tr>
				<tr>
					<td width="10%"  bgcolor="#FFFFFF" >
						<div align="right" class="STYLE3">详情轮播：</div>
					</td>
					<td class="table-td" colspan="3"  style="padding: 0px;background-color: #fffdf0">
						<input type="hidden" id="picarry"  name="picarry">
			  	<div class="pic_box">
				  <ul style="overflow:hidden">
				    <c:forEach  items="${attachL}" var="v" >
	                      <li attach="${v.id}" style="list-style-type:none;position:relative;float:left;padding-left:10px" >
	                         <img src="<%=basePath%>${v.url}" title="${v.url}" border="0" style="width:250px;height:150px;" />
		                     <img  src="<%=basePath%>images/deleted.png"  onclick="removePic(this)" style="position: absolute;right:0px;width:12%;background-color: #ffffff;top:0px" />
                             <input type="hidden" name="featureImgs" value="${v.id}" />
	                      </li>
                      </c:forEach>
				  </ul>
				</div>
				<div id="picker" style="padding: 0px 10px 3px;">选择图片</div>
				<input type="hidden" id="picarry"  name="picarry">
			</td>
				</tr>
				
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">房型名称：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><label><input
						name="roomName" type="text" id="roomName" value=<%=rsRoom.getString("roomName")%>></label>
				</td>
			</tr>

			<tr>
				<td width="10%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 支付方式： </div>
				</td>
				<td width="90%" bgcolor="#FFFFFF">
					<select  name="payway"  id="payway">
						<option   value="1"
								<% if ("1".equals(rsRoom.getString("payway"))) { %>
								  selected="selected" <%}
						%>
						>微信</option>
							<option   value="2"
									<% if ("2".equals(rsRoom.getString("payway"))) { %>
									  selected="selected" <%}
							%>
									   >度假币</option>
							<option   value="3"  	<% if ("3".equals(rsRoom.getString("payway"))) { %>
									  selected="selected" <%}
							%>>度假币or微信</option>
					</select>
				</td>
			</tr>

			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 度假币： </div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
					<label><input name="virtualCoin" type="text" id="virtualCoin" value=<%=rsRoom.getString("virtualCoin")%>></label>
				</td>
			</tr>
			
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 实体货币： </div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
					<label><input name="coin" type="text" id="coin" value=<%=rsRoom.getString("coin")%>></label>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" bgcolor="#FFFFFF">服务设施</td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">酒店服务：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
					<%
						String sqlservice = "select id, name, type from hotelservice";
						PreparedStatement pstmtservice = conn.prepareStatement(sqlservice);
						ResultSet supplier = pstmtservice.executeQuery();
						while (supplier.next()) {
							if ("0".equals(supplier.getString("type"))) {
					%> <label><input name="serviceCheckBox" type="checkbox"
						value="<%=supplier.getString("id")%>" /> <%=supplier.getString("name")%></label>
					<% } } %>
					<input type="hidden" name="service" id="service" value="<%=rsRoom.getString("service")%>"/>
				</td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">酒店设备：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
				<input type="hidden" name="installation" id="installation" value="<%=rsRoom.getString("installation")%>"/>
					<%
						ResultSet supplier1 = pstmtservice.executeQuery();
						while (supplier1.next()) {
							if ("1".equals(supplier1.getString("type"))) {
					%> <label><input name="installationCheckBox" type="checkbox"
						value="<%=supplier1.getString("id")%>" /> <%=supplier1.getString("name")%></label>
					<%
 	}
 	}
 	if (pstmtservice != null){
 		pstmtservice.close();
 	}
 	if (supplier != null){
 		supplier.close();
 	}
 	if (supplier1 != null){
 		supplier1.close();
 	}
 	
 	%>
 	</td>
 	</tr>
			<tr>
				<td colspan="2" bgcolor="#FFFFFF">房型详情</td>
					<input type="hidden"  id="deta" value="<%=rsRoom.getString("details")%>">
					<input type="hidden"  id="details"  name="details" value="">
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 购买须知： </div>
				</td>
				<td bgcolor="#FFFFFF">
					<textarea rows="10" cols="80" name="notes"><%=rsRoom.getString("notes").replaceAll("<br>","\r\n").replaceAll("&nbsp;"," ")%></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 费用说明： </div>
				</td>
				<td bgcolor="#FFFFFF">
					<textarea rows="10" cols="80"  name="costExplain"><%=rsRoom.getString("costExplain").replaceAll("<br>","\r\n").replaceAll("&nbsp;"," ")%></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 产品详情： </div>
				</td>
				<td bgcolor="#FFFFFF">
				 <div>
                      <script id="editor" type="text/plain" style="width:100%;height:300px;">
</script>
               </div> 
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#FFFFFF">

					<div align="center">
						<label> <input type="button" name="Submit" onclick="alterSystem();" value="提交"> </label>
					</div>

				</td>
			</tr>
		</table>



		<%
		if (pstmSupplier != null) {
	 		pstmSupplier.close();
	 	}
	 	if (rsSupplier != null) {
	 		rsSupplier.close();
	 	}
	 	if (rstkind != null) {
			rstkind.close();
		}
		if (pstmtkind != null) {
			pstmtkind.close();
		}
		if (rsRoom != null){
			rsRoom.close();}
		if (stat != null){
			stat.close();}
		if (conn != null){
			conn.close();}
		%>
	</form>
	<script type="text/javascript">

		function save() {
			//editor.sync();
			document.forms[0].submit();
		}
		
	   function noEscapeHtml(html) {  
        return html.replace(/(\&|\&)gt;/g, ">")  
                    .replace(/(\&|\&)lt;/g, "<")  
                    .replace(/(\&|\&)quot;/g, "\"");  
     }  
      $(window).load(function() {  
      $(".view").html($("#deta").val());
      }); 
     ue.ready(function(){
           ue.setContent($("#deta").val()); 
});

     // 初始化Web Uploader 
var uploader = WebUploader.create({
    // 选完文件后，是否自动上传。
    auto: true,
    // swf文件路径
    swf: '<%=basePath%>Uploader.swf',
    // 文件接收服务端。
    server: '<%=basePath%>upload/attachment.html',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
     pick : {id : '#picker',multiple: false},
    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});

  
  uploader.on('uploadSuccess', function(file, json) {
    var obj=json.data;
	if(json.resource){
		var _html = '<li attach="'+obj.id+'" style="list-style-type:none;position:relative;float:left;padding-left:10px" >';
		_html += '<img src="<%=basePath%>'+obj.url+'" title="'+obj.fileName+'" border="0" style="width:250px;height:150px;" />';
		_html += '<img  src="<%=basePath%>images/deleted.png"  onclick="removePic(this)" style="position: absolute;right:0px;width:12%;background-color: #ffffff" />';
		_html += '<input type="hidden" name="featureImgs" value="'+obj.id+'" />';
		_html += '</li>';
		$("div.pic_box ul").append(_html);
	}else{
		parent.Ext.Msg.alert("提示","文件上传失败！("+json.msg+")");
		alert('文件上传失败！');
	}
}); 
    function removePic(obj){
         $(obj).parent().remove();
    }
</script>
</body>
</html>
