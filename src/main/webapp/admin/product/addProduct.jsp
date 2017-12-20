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

		<title>产品信息添加</title>

		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>webuploader/webuploader.css">
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>webuploader/webuploader.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
    
        <script type="text/javascript" src="<%=basePath%>js/vailjs/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/vailjs/messages_zh.js"></script>
				<style>
form.cmxform label.error,label.error {
	color: red;
	font-style: italic
}
</style>
<script type="text/javascript">
		
		$(function(){
		    var validate = $("#from1").validate({
       	    rules: {
	      id: {
	         required: true,
	         digits:true
	      },
	    },
	    messages: {
	      id: {
	        required: "请输入排序",
	        digits: "排序只能为正整数"
	         },
 	         }
       	   });
	   	});
		
		
		
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
		// 编码	
	  function html2Escape(sHtml) {
          return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
     }
			
			
			
	function alterSystem(){
	$("#details").val(html2Escape(UE.getEditor('editor').getContent()));
	 //  获取图片的上传ID
	 
	 var pic="";
         $("li").each(function(){
         pic+=($(this).attr("attach")+",");
       });
       
      $("#picarry").val(pic);
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
		<form  action="platform/addProduct.html" id="from1"  method="post"  enctype="multipart/form-data" >
		<input type="hidden"   value="" id="details"  name="details">
			<table width="70%" border="0" align="center" cellspacing="1"
				style="background-color:#d2d2da;line-height: 250%;">
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 产品排序： </div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><input name="id" type="text" id="id" required><span style="color:red">&nbsp;&nbsp; *(必填)</span></label>
					</td>
				</tr>
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							供应商：
						</div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><select name="pid" >
							<%
								Connection conn = DBManager.getConnection();
								String sqlSupplier = "select id, hotelName from hotelsupplier";
								PreparedStatement pstmSupplier = conn.prepareStatement(sqlSupplier);
								ResultSet rsSupplier = pstmSupplier.executeQuery();
								while (rsSupplier.next()) {
							%>
							<option value=<%=rsSupplier.getString("id")%>><%=rsSupplier.getString("hotelName")%></option>
							<%
								}
							%>
						</select></label>
					</td>
				</tr>
				<tr>
				<td width="10%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">产品分类：</div>
				</td>
				<td width="90%" bgcolor="#FFFFFF"><label> <select
						name="other">
							<%
								String sqlkind = "select id, hotelKind from hotelkind where status = '0'";
								PreparedStatement pstmtkind = conn.prepareStatement(sqlkind);
								ResultSet rstkind = pstmtkind.executeQuery();
								while (rstkind.next()) {
							%>
							<option value=<%=rstkind.getString("id")%>>
								<%=rstkind.getString("hotelKind")%>
							</option>

							<%
								}
							%>
					</select>
				</label></td>
				</tr>
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							产品属性：
						</div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><select name="property">
						<option value="0">推荐</option>
						<option value="1">热卖</option>
						<option value="2">特价</option>
						<option value="3">新品</option>
					</select></label>
					</td>
				</tr>
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 房型名称： </div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><input name="roomName" type="text" id="roomName" required></label>
					</td>
				</tr>


				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 支付方式： </div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
					<select  name="payway">
						<option   value="1">微信</option>
						<option   value="2">度假币</option>
						<option   value="3">度假币or微信</option>
					</select>
					</td>
				</tr>


				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 度假币： </div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><input name="virtualCoin" type="text" id="virtualCoin" required></label>
					</td>
				</tr>
				
				<tr>
					<td width="10%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 实体货币： </div>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<label><input name="coin" type="text" id="coin" required></label>
					</td>
				</tr>
				
					<tr>
					<td width="10%"  bgcolor="#FFFFFF" >
						<div align="right" class="STYLE3">封面首图：</div>
					</td>
					<td class="table-td" colspan="3"  style="padding: 0px;background-color: #fffdf0">
			      	<input type="file" id="file"  name="file">
			       </td>
				</tr>
				
				<tr>
					<td width="10%"  bgcolor="#FFFFFF" >
						<div align="right" class="STYLE3">详情轮播：</div>
					</td>
					<td class="table-td" colspan="3"  style="padding: 0px;background-color: #fffdf0">
			  	<div class="pic_box">
				  <ul style="overflow:hidden">
				  </ul>
				</div>
				<div id="picker" style="padding: 0px 10px 3px;">选择图片</div>
				<input type="hidden" id="picarry"  name="picarry">
			</td>
				</tr>
				
			<tr>
				<td colspan="2" bgcolor="#FFFFFF">服务设施</td>
			</tr>
			<tr>
				<td width="10%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">酒店服务：</div>
				</td>
				<td width="90%" bgcolor="#FFFFFF">
					<%
						String sqlservice = "select id, name, type from hotelservice";
						PreparedStatement pstmtservice = conn.prepareStatement(sqlservice);
						ResultSet supplier = pstmtservice.executeQuery();
						while (supplier.next()) {
							if ("0".equals(supplier.getString("type"))) {
					%> <label><input name="serviceCheckBox" type="checkbox"
						value="<%=supplier.getString("id")%>" /> <%=supplier.getString("name")%></label>
					<% } } %>
					<input type="hidden" name="service" id="service"/>
				</td>
			</tr>
			<tr>
				<td width="10%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">酒店设备：</div>
				</td>
				<td width="90%" bgcolor="#FFFFFF">
				<input type="hidden" name="installation" id="installation" required/>
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
 	if (conn != null) {
 		conn.close();
 	}
 %>
			<tr>
				<td colspan="2" bgcolor="#FFFFFF">房型详情</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 购买须知： </div>
				</td>
				<td bgcolor="#FFFFFF">
					<textarea rows="10" cols="80"  name="notes" required></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 费用说明： </div>
				</td>
				<td bgcolor="#FFFFFF">
					<textarea rows="10" cols="80"  name="costExplain" required></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 产品详情： </div>
				</td>
				<td bgcolor="#FFFFFF">
				<div>
                      <script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
               </div>
				</td>
			</tr>
				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						
							<div align="center"><label>
								<input type="button"  onclick="alterSystem()"   name="Submit" value="提交">
								<input type="reset" name="Submit2" value="重置"></label>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
<script type="text/javascript"><!--
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
</html>
