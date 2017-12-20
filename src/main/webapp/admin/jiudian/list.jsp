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
		<title>酒店列表</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>
		<script type="text/javascript" src="<%=basePath%>admin/js/jiudian.js"></script>
	</head>

	<script type="text/javascript">
        var url='<%=basePath%>';
        var  openW;
        var pages =0;
	</script>
	<body leftmargin="8" topmargin="8">
		<!--  搜索表单  -->
		<form action="admin/jiudian/list.jsp" method="post">

			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>

						<table width="469" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129'>
									请输入酒店名：
								</td>
								<td width='170'>
									<input type='text' name='queryName' value=''
										style='width: 150px' />
								</td>
								<td width='86'>
									<input  id="sub" type="submit" border="0" class="np"
										value='查询' />
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
					<tr bgcolor="#FAFAF1">
						<td height="28" colspan="10">
							&nbsp;

							<a href="admin/jiudian/addHotel.jsp"><strong>酒店添加</strong>
							</a>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7">
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;酒店列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22"   id="tr01">
						<td width="10%">
							排序
						</td>
						<td width="30%">
							酒店名称
						</td>
						<td width="15%">
							所属分类
						</td>
						<td width="15%">
							状态
						</td>
						<td width="15%">
							录入时间
						</td>
						<td width="15%">
							操作
						</td>
					</tr>

				</table>
			</div>
		</form>
		<div ></div>
		<div style="  margin-top: 20px; margin-left: 20px;" id="biuuu_city"></div>
	</body>
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
	</script>
</html>