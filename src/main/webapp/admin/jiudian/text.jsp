<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<script type="text/javascript"
	src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
打 <input  />
	<div id="u851" class="ax_default ax_default_hidden" data-label="新建酒店"
		style="display: none; visibility: hidden">
		<div id="u851_state0" class="panel_state" data-label="State1">
			<div id="u851_state0_content" class="panel_state_content">
				<!-- Unnamed (矩形) -->
				<div id="u856" class="ax_default label">
					<div id="u856_div" class=""></div>
					<!-- Unnamed () -->
					<div id="u857" class="text" style="visibility: visible;">
						<p>
							<span>新建酒店</span>
						</p>
					</div>
				</div>
				<!-- 切换标签 (组合) -->
				<div id="u858" class="ax_default" data-label="切换标签">
					<!-- Unnamed (矩形) -->
					<div id="u861" class="ax_default box_1 selected"
						selectiongroup="nav-bar-4">
						<div id="u861_div" class="selected"></div>
						<!-- Unnamed () -->
						<div id="u862" class="text" style="visibility: visible;">
							<p style="font-size: 16px;">
								<span style="font-family: 'Helvetica';">&nbsp;</span><span
									style="font-family: 'PingFangSC-Regular', 'PingFang SC'; font-size: 14px;">基本信息</span>
							</p>
						</div>
					</div>

					<!-- Unnamed (矩形) -->
					<div id="u863" class="ax_default box_1" selectiongroup="nav-bar-4">
						<div id="u863_div" class=""></div>
						<!-- Unnamed () -->
						<div id="u864" class="text" style="visibility: visible;">
							<p style="font-size: 14px;">
								<span style="font-family: 'Helvetica'; font-size: 12px;">&nbsp;</span><span
									style="font-family: 'PingFangSC-Regular', 'PingFang SC';">服务设施</span>
							</p>
						</div>
					</div>

					<!-- Unnamed (矩形) -->
					<div id="u865" class="ax_default box_1" selectiongroup="nav-bar-4">
						<div id="u865_div" class=""></div>
						<!-- Unnamed () -->
						<div id="u866" class="text" style="visibility: visible;">
							<p>
								<span>酒店详情</span>
							</p>
						</div>
					</div>

					<!-- Unnamed (矩形) -->
					<div id="u867" class="ax_default box_1" selectiongroup="nav-bar-4">
						<div id="u867_div" class=""></div>
						<!-- Unnamed () -->
						<div id="u868" class="text" style="visibility: visible;">
							<p>
								<span>房型详情</span>
							</p>
						</div>
					</div>
				</div>

				<!-- 取消／确认 (组合) -->
				<div id="u869" class="ax_default" data-label="取消／确认">

					<!-- Unnamed (矩形) -->
					<div id="u870" class="ax_default primary_button">
						<div id="u870_div" class=""></div>
						<!-- Unnamed () -->
						<div id="u871" class="text" style="visibility: visible;">
							<p>
								<span>确认</span>
							</p>
						</div>
					</div>

					<!-- Unnamed (矩形) -->
					<div id="u872" class="ax_default primary_button">
						<div id="u872_div" class=""></div>
						<!-- Unnamed () -->
						<div id="u873" class="text" style="visibility: visible;">
							<p>
								<span>取消</span>
							</p>
						</div>
					</div>
				</div>

				<!-- 4酒店详情 (动态面板) -->
				<div id="u876" class="ax_default ax_default_hidden"
					data-label="4酒店详情" style="display: none; visibility: hidden">
					<div id="u876_state0" class="panel_state" data-label="State1">
						<div id="u876_state0_content" class="panel_state_content">

						


						

								<!-- Unnamed (下拉列表框) -->
								<div id="u906" class="ax_default _下拉列表框">
									<select id="u906_input">
										<option selected value="宋体">宋体</option>
										<option value="微软雅黑">微软雅黑</option>
										<option value="楷体">楷体</option>
										<option value="隶书">隶书</option>
										<option value="仿宋体">仿宋体</option>
									</select>
								</div>

								<!-- Unnamed (下拉列表框) -->
								<div id="u907" class="ax_default _下拉列表框">
									<select id="u907_input">
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="12">12</option>
										<option selected value="14">14</option>
										<option value="16">16</option>
										<option value="18">18</option>
										<option value="20">20</option>
										<option value="24">24</option>
										<option value="32">32</option>
										<option value="48">48</option>
										<option value="72">72</option>
									</select>
								</div>

								<!-- text (多行文本框) -->
								<div id="u908" class="ax_default _多行文本框" data-label="text">
									<textarea id="u908_input">趣闻 1947 年，时装设计师 Elsa Schiaparelli 将“艳粉色”引入西方时尚圈。 桃色可以营造亲密氛围，减少攻击性和敌意。 由于听说粉色有一种镇定效果，有些球队会把客队的休息室漆成粉色。 对于粉色的研究发现，男性举重运动员在粉色房间内似乎感到力不从心，而女性举重运动员面对这种颜色反而会有变强的倾向。 糕点从粉色盒子里取出或盛在粉色盘子里时，尝起来会更美味（这种情况仅适用于甜点），因为粉色令我们渴望糖份。</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 3房型详情 (动态面板) -->
				<div id="u909" class="ax_default ax_default_hidden"
					data-label="3房型详情" style="display: none; visibility: hidden">
					<div id="u909_state0" class="panel_state" data-label="State1">
						<div id="u909_state0_content" class="panel_state_content">

							<!-- Unnamed (矩形) -->
							<div id="u910" class="ax_default label">
								<div id="u910_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u911" class="text" style="visibility: visible;">
									<p>
										<span>窗型：</span>
									</p>
								</div>
							</div>

	
							<!-- Unnamed (文本框) -->
							<div id="u914" class="ax_default text_field">
								<input id="u914_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u915" class="ax_default primary_button">
								<div id="u915_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u916" class="text" style="visibility: visible;">
									<p>
										<span>添加房型</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u917" class="ax_default label">
								<div id="u917_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u918" class="text" style="visibility: visible;">
									<p>
										<span>房型名称：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u919" class="ax_default box_1">
								<div id="u919_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u920" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u921" class="ax_default text_field">
								<input id="u921_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u922" class="ax_default label">
								<div id="u922_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u923" class="text" style="visibility: visible;">
									<p>
										<span>床型：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u924" class="ax_default droplist">
								<select id="u924_input">
									<option selected value="单床">单床</option>
									<option value="双床">双床</option>
									<option value="大床／双床">大床／双床</option>
									<option value="套房">套房</option>
									<option value="三床">三床</option>
									<option value="四床">四床</option>
									<option value="多床">多床</option>
									<option value="上下铺">上下铺</option>
									<option value="通铺">通铺</option>
									<option value="圆床">圆床</option>
									<option value="水床">水床</option>
									<option value="拼床">拼床</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u925" class="ax_default label">
								<div id="u925_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u926" class="text" style="visibility: visible;">
									<p>
										<span>最大人数：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u927" class="ax_default label">
								<div id="u927_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u928" class="text" style="visibility: visible;">
									<p>
										<span>楼层：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u929" class="ax_default box_1">
								<div id="u929_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u930" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u931" class="ax_default text_field">
								<input id="u931_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u932" class="ax_default box_1">
								<div id="u932_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u933" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u934" class="ax_default text_field">
								<input id="u934_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u935" class="ax_default label">
								<div id="u935_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u936" class="text" style="visibility: visible;">
									<p>
										<span>可加床：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u937" class="ax_default droplist">
								<select id="u937_input">
									<option value="是">是</option>
									<option selected value="否">否</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u938" class="ax_default label">
								<div id="u938_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u939" class="text" style="visibility: visible;">
									<p>
										<span>早餐：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u940" class="ax_default droplist">
								<select id="u940_input">
									<option selected value="无早">无早</option>
									<option value="单早">单早</option>
									<option value="双早">双早</option>
									<option value="含早">含早</option>
									<option value="不定">不定</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u941" class="ax_default primary_button">
								<div id="u941_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u942" class="text" style="visibility: visible;">
									<p>
										<span>删除房型</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (水平线) -->
							<div id="u943" class="ax_default line">
								<img id="u943_img" class="img " src="images/3_1酒店列表/u943.png" />
								<!-- Unnamed () -->
								<div id="u944" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u945" class="ax_default label">
								<div id="u945_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u946" class="text" style="visibility: visible;">
									<p>
										<span>面积：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u947" class="ax_default box_1">
								<div id="u947_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u948" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u949" class="ax_default text_field">
								<input id="u949_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u950" class="ax_default label">
								<div id="u950_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u951" class="text" style="visibility: visible;">
									<p>
										<span>宽带：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u952" class="ax_default checkbox">
								<label for="u952_input"> <!-- Unnamed () -->
									<div id="u953" class="text" style="visibility: visible;">
										<p>
											<span>免费有线</span>
										</p>
									</div>
								</label> <input id="u952_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u954" class="ax_default checkbox">
								<label for="u954_input"> <!-- Unnamed () -->
									<div id="u955" class="text" style="visibility: visible;">
										<p>
											<span>免费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u954_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u956" class="ax_default checkbox">
								<label for="u956_input"> <!-- Unnamed () -->
									<div id="u957" class="text" style="visibility: visible;">
										<p>
											<span>收费有线</span>
										</p>
									</div>
								</label> <input id="u956_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u958" class="ax_default checkbox">
								<label for="u958_input"> <!-- Unnamed () -->
									<div id="u959" class="text" style="visibility: visible;">
										<p>
											<span>收费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u958_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u960" class="ax_default label">
								<div id="u960_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u961" class="text" style="visibility: visible;">
									<p>
										<span>窗型：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u962" class="ax_default box_1">
								<div id="u962_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u963" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u964" class="ax_default text_field">
								<input id="u964_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u965" class="ax_default label">
								<div id="u965_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u966" class="text" style="visibility: visible;">
									<p>
										<span>房型名称：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u967" class="ax_default box_1">
								<div id="u967_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u968" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u969" class="ax_default text_field">
								<input id="u969_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u970" class="ax_default label">
								<div id="u970_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u971" class="text" style="visibility: visible;">
									<p>
										<span>床型：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u972" class="ax_default droplist">
								<select id="u972_input">
									<option selected value="单床">单床</option>
									<option value="双床">双床</option>
									<option value="大床／双床">大床／双床</option>
									<option value="套房">套房</option>
									<option value="三床">三床</option>
									<option value="四床">四床</option>
									<option value="多床">多床</option>
									<option value="上下铺">上下铺</option>
									<option value="通铺">通铺</option>
									<option value="圆床">圆床</option>
									<option value="水床">水床</option>
									<option value="拼床">拼床</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u973" class="ax_default label">
								<div id="u973_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u974" class="text" style="visibility: visible;">
									<p>
										<span>最大人数：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u975" class="ax_default label">
								<div id="u975_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u976" class="text" style="visibility: visible;">
									<p>
										<span>楼层：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u977" class="ax_default box_1">
								<div id="u977_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u978" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u979" class="ax_default text_field">
								<input id="u979_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u980" class="ax_default box_1">
								<div id="u980_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u981" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u982" class="ax_default text_field">
								<input id="u982_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u983" class="ax_default label">
								<div id="u983_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u984" class="text" style="visibility: visible;">
									<p>
										<span>可加床：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u985" class="ax_default droplist">
								<select id="u985_input">
									<option value="是">是</option>
									<option selected value="否">否</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u986" class="ax_default label">
								<div id="u986_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u987" class="text" style="visibility: visible;">
									<p>
										<span>早餐：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u988" class="ax_default droplist">
								<select id="u988_input">
									<option selected value="无早">无早</option>
									<option value="单早">单早</option>
									<option value="双早">双早</option>
									<option value="含早">含早</option>
									<option value="不定">不定</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u989" class="ax_default primary_button">
								<div id="u989_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u990" class="text" style="visibility: visible;">
									<p>
										<span>删除房型</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (水平线) -->
							<div id="u991" class="ax_default line">
								<img id="u991_img" class="img " src="images/3_1酒店列表/u943.png" />
								<!-- Unnamed () -->
								<div id="u992" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u993" class="ax_default label">
								<div id="u993_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u994" class="text" style="visibility: visible;">
									<p>
										<span>面积：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u995" class="ax_default box_1">
								<div id="u995_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u996" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u997" class="ax_default text_field">
								<input id="u997_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u998" class="ax_default label">
								<div id="u998_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u999" class="text" style="visibility: visible;">
									<p>
										<span>宽带：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1000" class="ax_default checkbox">
								<label for="u1000_input"> <!-- Unnamed () -->
									<div id="u1001" class="text" style="visibility: visible;">
										<p>
											<span>免费有线</span>
										</p>
									</div>
								</label> <input id="u1000_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1002" class="ax_default checkbox">
								<label for="u1002_input"> <!-- Unnamed () -->
									<div id="u1003" class="text" style="visibility: visible;">
										<p>
											<span>免费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u1002_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1004" class="ax_default checkbox">
								<label for="u1004_input"> <!-- Unnamed () -->
									<div id="u1005" class="text" style="visibility: visible;">
										<p>
											<span>收费有线</span>
										</p>
									</div>
								</label> <input id="u1004_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1006" class="ax_default checkbox">
								<label for="u1006_input"> <!-- Unnamed () -->
									<div id="u1007" class="text" style="visibility: visible;">
										<p>
											<span>收费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u1006_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1008" class="ax_default label">
								<div id="u1008_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1009" class="text" style="visibility: visible;">
									<p>
										<span>窗型：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1010" class="ax_default box_1">
								<div id="u1010_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1011" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1012" class="ax_default text_field">
								<input id="u1012_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1013" class="ax_default label">
								<div id="u1013_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1014" class="text" style="visibility: visible;">
									<p>
										<span>房型名称：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1015" class="ax_default box_1">
								<div id="u1015_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1016" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1017" class="ax_default text_field">
								<input id="u1017_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1018" class="ax_default label">
								<div id="u1018_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1019" class="text" style="visibility: visible;">
									<p>
										<span>床型：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1020" class="ax_default droplist">
								<select id="u1020_input">
									<option selected value="单床">单床</option>
									<option value="双床">双床</option>
									<option value="大床／双床">大床／双床</option>
									<option value="套房">套房</option>
									<option value="三床">三床</option>
									<option value="四床">四床</option>
									<option value="多床">多床</option>
									<option value="上下铺">上下铺</option>
									<option value="通铺">通铺</option>
									<option value="圆床">圆床</option>
									<option value="水床">水床</option>
									<option value="拼床">拼床</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1021" class="ax_default label">
								<div id="u1021_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1022" class="text" style="visibility: visible;">
									<p>
										<span>最大人数：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1023" class="ax_default label">
								<div id="u1023_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1024" class="text" style="visibility: visible;">
									<p>
										<span>楼层：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1025" class="ax_default box_1">
								<div id="u1025_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1026" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1027" class="ax_default text_field">
								<input id="u1027_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1028" class="ax_default box_1">
								<div id="u1028_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1029" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1030" class="ax_default text_field">
								<input id="u1030_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1031" class="ax_default label">
								<div id="u1031_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1032" class="text" style="visibility: visible;">
									<p>
										<span>可加床：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1033" class="ax_default droplist">
								<select id="u1033_input">
									<option value="是">是</option>
									<option selected value="否">否</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1034" class="ax_default label">
								<div id="u1034_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1035" class="text" style="visibility: visible;">
									<p>
										<span>早餐：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1036" class="ax_default droplist">
								<select id="u1036_input">
									<option selected value="无早">无早</option>
									<option value="单早">单早</option>
									<option value="双早">双早</option>
									<option value="含早">含早</option>
									<option value="不定">不定</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1037" class="ax_default primary_button">
								<div id="u1037_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1038" class="text" style="visibility: visible;">
									<p>
										<span>删除房型</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (水平线) -->
							<div id="u1039" class="ax_default line">
								<img id="u1039_img" class="img " src="images/3_1酒店列表/u943.png" />
								<!-- Unnamed () -->
								<div id="u1040" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1041" class="ax_default label">
								<div id="u1041_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1042" class="text" style="visibility: visible;">
									<p>
										<span>面积：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1043" class="ax_default box_1">
								<div id="u1043_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1044" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1045" class="ax_default text_field">
								<input id="u1045_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1046" class="ax_default label">
								<div id="u1046_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1047" class="text" style="visibility: visible;">
									<p>
										<span>宽带：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1048" class="ax_default checkbox">
								<label for="u1048_input"> <!-- Unnamed () -->
									<div id="u1049" class="text" style="visibility: visible;">
										<p>
											<span>免费有线</span>
										</p>
									</div>
								</label> <input id="u1048_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1050" class="ax_default checkbox">
								<label for="u1050_input"> <!-- Unnamed () -->
									<div id="u1051" class="text" style="visibility: visible;">
										<p>
											<span>免费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u1050_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1052" class="ax_default checkbox">
								<label for="u1052_input"> <!-- Unnamed () -->
									<div id="u1053" class="text" style="visibility: visible;">
										<p>
											<span>收费有线</span>
										</p>
									</div>
								</label> <input id="u1052_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1054" class="ax_default checkbox">
								<label for="u1054_input"> <!-- Unnamed () -->
									<div id="u1055" class="text" style="visibility: visible;">
										<p>
											<span>收费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u1054_input" type="checkbox" value="checkbox" />
							</div>

							<!-- 房型信息4 (组合) -->
							<div id="u1056" class="ax_default ax_default_hidden"
								data-label="房型信息4" style="display: none; visibility: hidden">

								<!-- Unnamed (矩形) -->
								<div id="u1057" class="ax_default label">
									<div id="u1057_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1058" class="text" style="visibility: visible;">
										<p>
											<span>窗型：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1059" class="ax_default box_1">
									<div id="u1059_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1060" class="text"
										style="display: none; visibility: hidden">
										<p>
											<span></span>
										</p>
									</div>
								</div>

								<!-- Unnamed (文本框) -->
								<div id="u1061" class="ax_default text_field">
									<input id="u1061_input" type="text" value="" />
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1062" class="ax_default label">
									<div id="u1062_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1063" class="text" style="visibility: visible;">
										<p>
											<span>房型名称：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1064" class="ax_default box_1">
									<div id="u1064_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1065" class="text"
										style="display: none; visibility: hidden">
										<p>
											<span></span>
										</p>
									</div>
								</div>

								<!-- Unnamed (文本框) -->
								<div id="u1066" class="ax_default text_field">
									<input id="u1066_input" type="text" value="" />
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1067" class="ax_default label">
									<div id="u1067_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1068" class="text" style="visibility: visible;">
										<p>
											<span>床型：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (下拉列表框) -->
								<div id="u1069" class="ax_default droplist">
									<select id="u1069_input">
										<option selected value="单床">单床</option>
										<option value="双床">双床</option>
										<option value="大床／双床">大床／双床</option>
										<option value="套房">套房</option>
										<option value="三床">三床</option>
										<option value="四床">四床</option>
										<option value="多床">多床</option>
										<option value="上下铺">上下铺</option>
										<option value="通铺">通铺</option>
										<option value="圆床">圆床</option>
										<option value="水床">水床</option>
										<option value="拼床">拼床</option>
									</select>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1070" class="ax_default label">
									<div id="u1070_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1071" class="text" style="visibility: visible;">
										<p>
											<span>最大人数：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1072" class="ax_default label">
									<div id="u1072_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1073" class="text" style="visibility: visible;">
										<p>
											<span>楼层：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1074" class="ax_default box_1">
									<div id="u1074_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1075" class="text"
										style="display: none; visibility: hidden">
										<p>
											<span></span>
										</p>
									</div>
								</div>

								<!-- Unnamed (文本框) -->
								<div id="u1076" class="ax_default text_field">
									<input id="u1076_input" type="text" value="" />
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1077" class="ax_default box_1">
									<div id="u1077_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1078" class="text"
										style="display: none; visibility: hidden">
										<p>
											<span></span>
										</p>
									</div>
								</div>

								<!-- Unnamed (文本框) -->
								<div id="u1079" class="ax_default text_field">
									<input id="u1079_input" type="text" value="" />
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1080" class="ax_default label">
									<div id="u1080_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1081" class="text" style="visibility: visible;">
										<p>
											<span>可加床：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (下拉列表框) -->
								<div id="u1082" class="ax_default droplist">
									<select id="u1082_input">
										<option value="是">是</option>
										<option selected value="否">否</option>
									</select>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1083" class="ax_default label">
									<div id="u1083_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1084" class="text" style="visibility: visible;">
										<p>
											<span>早餐：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (下拉列表框) -->
								<div id="u1085" class="ax_default droplist">
									<select id="u1085_input">
										<option selected value="无早">无早</option>
										<option value="单早">单早</option>
										<option value="双早">双早</option>
										<option value="含早">含早</option>
										<option value="不定">不定</option>
									</select>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1086" class="ax_default primary_button">
									<div id="u1086_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1087" class="text" style="visibility: visible;">
										<p>
											<span>删除房型</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1088" class="ax_default label">
									<div id="u1088_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1089" class="text" style="visibility: visible;">
										<p>
											<span>面积：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1090" class="ax_default box_1">
									<div id="u1090_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1091" class="text"
										style="display: none; visibility: hidden">
										<p>
											<span></span>
										</p>
									</div>
								</div>

								<!-- Unnamed (文本框) -->
								<div id="u1092" class="ax_default text_field">
									<input id="u1092_input" type="text" value="" />
								</div>

								<!-- Unnamed (矩形) -->
								<div id="u1093" class="ax_default label">
									<div id="u1093_div" class=""></div>
									<!-- Unnamed () -->
									<div id="u1094" class="text" style="visibility: visible;">
										<p>
											<span>宽带：</span>
										</p>
									</div>
								</div>

								<!-- Unnamed (复选框) -->
								<div id="u1095" class="ax_default checkbox">
									<label for="u1095_input"> <!-- Unnamed () -->
										<div id="u1096" class="text" style="visibility: visible;">
											<p>
												<span>免费有线</span>
											</p>
										</div>
									</label> <input id="u1095_input" type="checkbox" value="checkbox" />
								</div>

								<!-- Unnamed (复选框) -->
								<div id="u1097" class="ax_default checkbox">
									<label for="u1097_input"> <!-- Unnamed () -->
										<div id="u1098" class="text" style="visibility: visible;">
											<p>
												<span>免费Wi-Fi</span>
											</p>
										</div>
									</label> <input id="u1097_input" type="checkbox" value="checkbox" />
								</div>

								<!-- Unnamed (复选框) -->
								<div id="u1099" class="ax_default checkbox">
									<label for="u1099_input"> <!-- Unnamed () -->
										<div id="u1100" class="text" style="visibility: visible;">
											<p>
												<span>收费有线</span>
											</p>
										</div>
									</label> <input id="u1099_input" type="checkbox" value="checkbox" />
								</div>

								<!-- Unnamed (复选框) -->
								<div id="u1101" class="ax_default checkbox">
									<label for="u1101_input"> <!-- Unnamed () -->
										<div id="u1102" class="text" style="visibility: visible;">
											<p>
												<span>收费Wi-Fi</span>
											</p>
										</div>
									</label> <input id="u1101_input" type="checkbox" value="checkbox" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 2服务内容 (动态面板) -->
				<div id="u1103" class="ax_default ax_default_hidden"
					data-label="2服务内容" style="display: none; visibility: hidden">
					<div id="u1103_state0" class="panel_state" data-label="State1">
						<div id="u1103_state0_content" class="panel_state_content">

							<!-- Unnamed (水平线) -->
							<div id="u1104" class="ax_default line">
								<img id="u1104_img" class="img " src="images/3_1酒店列表/u1104.png" />
								<!-- Unnamed () -->
								<div id="u1105" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1106" class="ax_default label">
								<div id="u1106_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1107" class="text" style="visibility: visible;">
									<p>
										<span>酒店服务：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1108" class="ax_default label">
								<div id="u1108_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1109" class="text" style="visibility: visible;">
									<p>
										<span>酒店设备：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1110" class="ax_default checkbox">
								<label for="u1110_input"> <!-- Unnamed () -->
									<div id="u1111" class="text" style="visibility: visible;">
										<p>
											<span>携带宠物</span>
										</p>
									</div>
								</label> <input id="u1110_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1112" class="ax_default checkbox">
								<label for="u1112_input"> <!-- Unnamed () -->
									<div id="u1113" class="text" style="visibility: visible;">
										<p>
											<span>看护小孩服务</span>
										</p>
									</div>
								</label> <input id="u1112_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1114" class="ax_default checkbox">
								<label for="u1114_input"> <!-- Unnamed () -->
									<div id="u1115" class="text" style="visibility: visible;">
										<p>
											<span>接机服务(免费)</span>
										</p>
									</div>
								</label> <input id="u1114_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1116" class="ax_default checkbox">
								<label for="u1116_input"> <!-- Unnamed () -->
									<div id="u1117" class="text" style="visibility: visible;">
										<p>
											<span>接机服务(收费)</span>
										</p>
									</div>
								</label> <input id="u1116_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1118" class="ax_default checkbox">
								<label for="u1118_input"> <!-- Unnamed () -->
									<div id="u1119" class="text" style="visibility: visible;">
										<p>
											<span>行李寄存</span>
										</p>
									</div>
								</label> <input id="u1118_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1120" class="ax_default checkbox">
								<label for="u1120_input"> <!-- Unnamed () -->
									<div id="u1121" class="text" style="visibility: visible;">
										<p>
											<span>洗衣服务</span>
										</p>
									</div>
								</label> <input id="u1120_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1122" class="ax_default checkbox">
								<label for="u1122_input"> <!-- Unnamed () -->
									<div id="u1123" class="text" style="visibility: visible;">
										<p>
											<span>叫醒服务</span>
										</p>
									</div>
								</label> <input id="u1122_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1124" class="ax_default checkbox">
								<label for="u1124_input"> <!-- Unnamed () -->
									<div id="u1125" class="text" style="visibility: visible;">
										<p>
											<span>租车服务</span>
										</p>
									</div>
								</label> <input id="u1124_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1126" class="ax_default checkbox">
								<label for="u1126_input"> <!-- Unnamed () -->
									<div id="u1127" class="text" style="visibility: visible;">
										<p>
											<span>穿梭机场班车</span>
										</p>
									</div>
								</label> <input id="u1126_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1128" class="ax_default checkbox">
								<label for="u1128_input"> <!-- Unnamed () -->
									<div id="u1129" class="text" style="visibility: visible;">
										<p>
											<span>停车场</span>
										</p>
									</div>
								</label> <input id="u1128_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1130" class="ax_default checkbox">
								<label for="u1130_input"> <!-- Unnamed () -->
									<div id="u1131" class="text" style="visibility: visible;">
										<p>
											<span>会议室</span>
										</p>
									</div>
								</label> <input id="u1130_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1132" class="ax_default checkbox">
								<label for="u1132_input"> <!-- Unnamed () -->
									<div id="u1133" class="text" style="visibility: visible;">
										<p>
											<span>健身房</span>
										</p>
									</div>
								</label> <input id="u1132_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1134" class="ax_default checkbox">
								<label for="u1134_input"> <!-- Unnamed () -->
									<div id="u1135" class="text" style="visibility: visible;">
										<p>
											<span>室内游泳池</span>
										</p>
									</div>
								</label> <input id="u1134_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1136" class="ax_default checkbox">
								<label for="u1136_input"> <!-- Unnamed () -->
									<div id="u1137" class="text" style="visibility: visible;">
										<p>
											<span>室外游泳池</span>
										</p>
									</div>
								</label> <input id="u1136_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1138" class="ax_default checkbox">
								<label for="u1138_input"> <!-- Unnamed () -->
									<div id="u1139" class="text" style="visibility: visible;">
										<p>
											<span>中式餐厅</span>
										</p>
									</div>
								</label> <input id="u1138_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1140" class="ax_default checkbox">
								<label for="u1140_input"> <!-- Unnamed () -->
									<div id="u1141" class="text" style="visibility: visible;">
										<p>
											<span>西式餐厅</span>
										</p>
									</div>
								</label> <input id="u1140_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1142" class="ax_default checkbox">
								<label for="u1142_input"> <!-- Unnamed () -->
									<div id="u1143" class="text" style="visibility: visible;">
										<p>
											<span>酒吧</span>
										</p>
									</div>
								</label> <input id="u1142_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1144" class="ax_default checkbox">
								<label for="u1144_input"> <!-- Unnamed () -->
									<div id="u1145" class="text" style="visibility: visible;">
										<p>
											<span>SPA</span>
										</p>
									</div>
								</label> <input id="u1144_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1146" class="ax_default checkbox">
								<label for="u1146_input"> <!-- Unnamed () -->
									<div id="u1147" class="text" style="visibility: visible;">
										<p>
											<span>温泉</span>
										</p>
									</div>
								</label> <input id="u1146_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1148" class="ax_default checkbox">
								<label for="u1148_input"> <!-- Unnamed () -->
									<div id="u1149" class="text" style="visibility: visible;">
										<p>
											<span>无烟室</span>
										</p>
									</div>
								</label> <input id="u1148_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1150" class="ax_default checkbox">
								<label for="u1150_input"> <!-- Unnamed () -->
									<div id="u1151" class="text" style="visibility: visible;">
										<p>
											<span>棋牌室</span>
										</p>
									</div>
								</label> <input id="u1150_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1152" class="ax_default checkbox">
								<label for="u1152_input"> <!-- Unnamed () -->
									<div id="u1153" class="text" style="visibility: visible;">
										<p>
											<span>商务中心</span>
										</p>
									</div>
								</label> <input id="u1152_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1154" class="ax_default checkbox">
								<label for="u1154_input"> <!-- Unnamed () -->
									<div id="u1155" class="text" style="visibility: visible;">
										<p>
											<span>桑拿</span>
										</p>
									</div>
								</label> <input id="u1154_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1156" class="ax_default checkbox">
								<label for="u1156_input"> <!-- Unnamed () -->
									<div id="u1157" class="text" style="visibility: visible;">
										<p>
											<span>残疾人设施</span>
										</p>
									</div>
								</label> <input id="u1156_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1158" class="ax_default checkbox">
								<label for="u1158_input"> <!-- Unnamed () -->
									<div id="u1159" class="text" style="visibility: visible;">
										<p>
											<span>公共区域免费Wi-Fi</span>
										</p>
									</div>
								</label> <input id="u1158_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1160" class="ax_default checkbox">
								<label for="u1160_input"> <!-- Unnamed () -->
									<div id="u1161" class="text" style="visibility: visible;">
										<p>
											<span>接待儿童</span>
										</p>
									</div>
								</label> <input id="u1160_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1162" class="ax_default checkbox">
								<label for="u1162_input"> <!-- Unnamed () -->
									<div id="u1163" class="text" style="visibility: visible;">
										<p>
											<span>接待老人</span>
										</p>
									</div>
								</label> <input id="u1162_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1164" class="ax_default checkbox">
								<label for="u1164_input"> <!-- Unnamed () -->
									<div id="u1165" class="text" style="visibility: visible;">
										<p>
											<span>接待外宾</span>
										</p>
									</div>
								</label> <input id="u1164_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1166" class="ax_default checkbox">
								<label for="u1166_input"> <!-- Unnamed () -->
									<div id="u1167" class="text" style="visibility: visible;">
										<p>
											<span>可做饭</span>
										</p>
									</div>
								</label> <input id="u1166_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1168" class="ax_default checkbox">
								<label for="u1168_input"> <!-- Unnamed () -->
									<div id="u1169" class="text" style="visibility: visible;">
										<p>
											<span>有吸烟区</span>
										</p>
									</div>
								</label> <input id="u1168_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1170" class="ax_default checkbox">
								<label for="u1170_input"> <!-- Unnamed () -->
									<div id="u1171" class="text" style="visibility: visible;">
										<p>
											<span>提供发票</span>
										</p>
									</div>
								</label> <input id="u1170_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1172" class="ax_default checkbox">
								<label for="u1172_input"> <!-- Unnamed () -->
									<div id="u1173" class="text" style="visibility: visible;">
										<p>
											<span>银联信用卡</span>
										</p>
									</div>
								</label> <input id="u1172_input" type="checkbox" value="checkbox" />
							</div>

							<!-- Unnamed (复选框) -->
							<div id="u1174" class="ax_default checkbox">
								<label for="u1174_input"> <!-- Unnamed () -->
									<div id="u1175" class="text" style="visibility: visible;">
										<p>
											<span>外币信用卡</span>
										</p>
									</div>
								</label> <input id="u1174_input" type="checkbox" value="checkbox" />
							</div>
						</div>
					</div>
				</div>

				<!-- 1基本内容 (动态面板) -->
				<div id="u1176" class="ax_default ax_default_hidden"
					data-label="1基本内容" style="display: none; visibility: hidden">
					<div id="u1176_state0" class="panel_state" data-label="State1">
						<div id="u1176_state0_content" class="panel_state_content">

							<!-- Unnamed (矩形) -->
							<div id="u1177" class="ax_default label">
								<div id="u1177_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1178" class="text" style="visibility: visible;">
									<p>
										<span>所属分类：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1179" class="ax_default box_1">
								<div id="u1179_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1180" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1181" class="ax_default droplist">
								<select id="u1181_input">
									<option selected value="度假酒店">度假酒店</option>
									<option value="精品民宿">精品民宿</option>
									<option value="业主别墅">业主别墅</option>
									<option value="商务酒店">商务酒店</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1182" class="ax_default label">
								<div id="u1182_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1183" class="text" style="visibility: visible;">
									<p>
										<span>酒店名称：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1184" class="ax_default box_1">
								<div id="u1184_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1185" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1186" class="ax_default label">
								<div id="u1186_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1187" class="text" style="visibility: visible;">
									<p>
										<span>简介：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1188" class="ax_default box_1">
								<div id="u1188_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1189" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1190" class="ax_default label">
								<div id="u1190_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1191" class="text" style="visibility: visible;">
									<p>
										<span>地址：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1192" class="ax_default box_1">
								<div id="u1192_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1193" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1194" class="ax_default label">
								<div id="u1194_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1195" class="text" style="visibility: visible;">
									<p>
										<span>坐标：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1196" class="ax_default box_1">
								<div id="u1196_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1197" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1198" class="ax_default label">
								<div id="u1198_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1199" class="text" style="visibility: visible;">
									<p>
										<span>标签：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1200" class="ax_default box_1">
								<div id="u1200_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1201" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1202" class="ax_default label">
								<div id="u1202_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1203" class="text" style="visibility: visible;">
									<p>
										<span>电话：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1204" class="ax_default box_1">
								<div id="u1204_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1205" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1206" class="ax_default label">
								<div id="u1206_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1207" class="text" style="visibility: visible;">
									<p>
										<span>营业时间：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1208" class="ax_default box_1">
								<div id="u1208_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1209" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1210" class="ax_default label">
								<div id="u1210_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1211" class="text" style="visibility: visible;">
									<p>
										<span>QQ：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1212" class="ax_default label">
								<div id="u1212_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1213" class="text" style="visibility: visible;">
									<p>
										<span>经度：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1214" class="ax_default box_1">
								<div id="u1214_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1215" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1216" class="ax_default label">
								<div id="u1216_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1217" class="text" style="visibility: visible;">
									<p>
										<span>纬度：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1218" class="ax_default primary_button">
								<div id="u1218_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1219" class="text" style="visibility: visible;">
									<p>
										<span>采集坐标</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1220" class="ax_default text_field">
								<input id="u1220_input" type="text" value="08：00" />
							</div>


							<!-- Unnamed (矩形) -->
							<div id="u1227" class="ax_default label">
								<div id="u1227_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1228" class="text" style="visibility: visible;">
									<p>
										<span>至</span>
									</p>
								</div>
							</div>


							<!-- Unnamed (矩形) -->
							<div id="u1238" class="ax_default label">
								<div id="u1238_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1239" class="text" style="visibility: visible;">
									<p>
										<span>图片：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1243" class="ax_default primary_button">
								<div id="u1243_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1244" class="text" style="visibility: visible;">
									<p>
										<span>上传图片</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1245" class="ax_default box_1">
								<div id="u1245_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1246" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1247" class="ax_default label">
								<div id="u1247_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1248" class="text" style="visibility: visible;">
									<p>
										<span>账号：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1249" class="ax_default text_field">
								<input id="u1249_input" type="text" value="" />
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1250" class="ax_default box_1">
								<div id="u1250_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1251" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1252" class="ax_default label">
								<div id="u1252_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1253" class="text" style="visibility: visible;">
									<p>
										<span>密码：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1254" class="ax_default text_field">
								<input id="u1254_input" type="text" value="" />
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1255" class="ax_default droplist">
								<select id="u1255_input">
									<option selected value="浙江">浙江</option>
									<option value="江苏">江苏</option>
									<option value="上海">上海</option>
									<option value="深圳">深圳</option>
								</select>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1256" class="ax_default droplist">
								<select id="u1256_input">
									<option selected value="宁波">宁波</option>
									<option value="杭州">杭州</option>
									<option value="绍兴">绍兴</option>
									<option value="湖州">湖州</option>
								</select>
							</div>

							<!-- Unnamed (下拉列表框) -->
							<div id="u1257" class="ax_default droplist">
								<select id="u1257_input">
									<option selected value="鄞州区">鄞州区</option>
									<option value="海曙区">海曙区</option>
									<option value="江北">江北</option>
									<option value="镇海">镇海</option>
								</select>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1258" class="ax_default box_1">
								<div id="u1258_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1259" class="text"
									style="display: none; visibility: hidden">
									<p>
										<span></span>
									</p>
								</div>
							</div>

							<!-- Unnamed (矩形) -->
							<div id="u1260" class="ax_default label">
								<div id="u1260_div" class=""></div>
								<!-- Unnamed () -->
								<div id="u1261" class="text" style="visibility: visible;">
									<p>
										<span>排序：</span>
									</p>
								</div>
							</div>

							<!-- Unnamed (文本框) -->
							<div id="u1262" class="ax_default text_field">
								<input id="u1262_input" type="text" value="" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
