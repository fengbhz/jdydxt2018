<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*"
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>亲友管理</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>
		<script type="text/javascript">
			
			
			function delOwner(id, status){
				window.location.href = "DelOwnerAction?id=" + id + "&status=" + status;
			}
			
			function resetCoin(){
			  if(confirm("确定要进行一键清零操作吗?")){
				window.location.href = "ResetCoinAction?type=0";
			  }
			}

            var url='<%=basePath%>';
            function  exportExcel(){
                document.location.href="<%=basePath%>";
            }
		</script>
		<script type="text/javascript" src="<%=basePath%>admin/js/fiendMoney.js"></script>
	</head>
	<body leftmargin="8" topmargin="8">
		<!--  搜索表单  -->
		<form  id="from1"  action="admin/owner/friendManager.jsp" method="post">
			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>

						<table width="100%" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129' style='text-align:right;'>
									亲友姓名：
								</td>
								<td width='170'>
									<input type='text' name='queryFriend' id="queryFriend"  value='' style='width: 150px' />
								</td>
								<td width='129' style='text-align:right;'>
									手机：
								</td>
								<td width='170'>
									<input type='text' name='phone'  id="phone" value='' style='width: 150px' />
								</td>
								<td width='86'>
									<input name="imageField"  id="sub" type="button" border="0" class="np" value='查询' /> &nbsp;&nbsp; | &nbsp;&nbsp;
									<input name="imageField"  id="export" type="button" border="0" class="np" value='导出' />
								</td>
								<td width="84">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<!--  快速转换位置按钮  -->
		<!--  内容列表   -->
		<form name="form2">
			<div id="printTable">
				<table width="98%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#3333FF" align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
					<tr bgcolor="#E7E7E7">
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;业主列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22"   id="tr01">
						<td width="8%">
					      	 序号
						</td>
						<td width="10%">
							亲友名称
						</td>
						<td width="15%">
							手机号码
						</td>
						<td width="8%">
							度假币余额
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: left; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
		    	<input id="resetBtn" type="button" onclick="resetCoin()" value="一键清零" 
		    	style="width:80px; height:30px; background-color:red; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
		    </div>
		</form>
		<div ></div>
		<div style="  margin-top: 20px; margin-left: 20px;" id="biuuu_city"></div>

		<script type="text/javascript">
            $("#sub").on("click", function () {
                fycx();
                test();
            });


            //测试数据
            fycx();
            var nums = 15; //每页出现的数量

            //调用分页
            laypage({
                cont: 'biuuu_city',
                pages: pages,
                jump: function(obj){
                    fycx(obj.curr);
                }
            })

		 $("#export").on("click", function () {
	       window.location.href="<%=basePath%>friend/exportExcel.html";
	        //$("#from1").submit();
            });
		</script>
	</body>
</html>