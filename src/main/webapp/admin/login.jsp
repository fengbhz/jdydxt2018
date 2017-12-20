<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<title>网站管理员登陆</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
</script>
<script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
	 <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
	 <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
	      
	 <script language="javascript">
	function login()
{
	var name= $("#username").val();
	var password= $("#userpwd").val();
	 if(name==''){
	 alert("用户名不能为空!");
	 return;
	 }
	 if(password==''){
	 alert("密码不能为空!");
	 return;
	 }
	 $("#fromid").submit();
}
	
 
	 </script>

<link href="<%=basePath%>admin/images/skin.css" rel="stylesheet" type="text/css">
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><!-- <img src="images/logo.png" width="279" height="68"> --></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p> </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p> </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p> </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom">
        
        <table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登陆后台管理</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
				       <form id="fromid"  action="<%=basePath%>platform/checkLogin.html"  method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text"><input  id="username" name="username" class="editbox4" value="" size="20">                            </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" size="20" id="userpwd"
												name="userpwd">
                              <img src="<%=basePath%>admin/images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          
                          <tr>
                            <td height="35" >&nbsp;<img id="indicator" src="<%=basePath %>kindeditor/themes/common/loading.gif" style="display:none"/></td>
                            <td width="20%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 陆" onClick="return check1()"> </td>
                            <td width="67%" class="top_hui_text"><input name="cs" type="reset" class="button" id="cs" value="取 消"></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                  <td>
                  <font  color="red">
                  ${msg}</font></td></tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="<%=basePath%>admin/images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright</span></td>
      </tr>
    </table></td>
  </tr>
</table>
