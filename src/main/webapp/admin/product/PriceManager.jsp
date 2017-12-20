<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>价格批量设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/css/ext-all.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_patch_firefox.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/ux/css/ux-all.css"/>
	 <link rel="stylesheet" href="<%=basePath%>calendar/build/calendar-price-jquery.css">
 	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-all.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ux/ux-all.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ux/Toast.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/swfupload.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/uploadPanel.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/extjs/ui/ext-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/publicExj.js"></script>
<style>
    	body {
    		margin: 0; padding: 0; font-family: "Microsoft YaHei UI";
    	}
    	.box:after{display: block;height: 0;content: '';clear: both;overflow: hidden;visibility: hidden;}    	
    	.container{width: 90%;}
    	.contant{background: rgba(245, 245, 245, 0);height: 100%;width: 10%;}
    	.left{float: left;}
    	.right{float: right;}
    	p{margin: 0;padding: 0;}
    	.to-month{height: 40px;line-height:40px;padding-left: 10px;}
    	.active{background: #098cc2;color: #fff;}
    	input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		    -webkit-appearance: none !important;
		    margin: 0;
		}
		input[type="number"] {
		   -moz-appearance: textfield;
		} 
    	
    </style>
  </head>
  
 <body>
<div class="box">
	<div class="left contant"></div>
	<div class="container right"></div>
</div>	

<script src="<%=basePath%>calendar/demo/jquery-1.12.4.min.js"></script>
<script src="<%=basePath%>calendar/src/calendar-price-jquery.js"></script>

<script>
    var lastMonth = 6;
    var endDate = formatDate(new Date(new Date().setMonth(new Date().getMonth() + lastMonth)), "yyyy-MM-dd");
    (function(){

    var mockData = JSON.stringify(${pjson});
     console.log(endDate);
        console.log(mockData);
    var cToObj=eval("("+mockData+")"); 
    // mockData=;
     
     
    for (var i = 0; i < 100; i++) {
//      mockData.push({
//          date: '2017-'+ fd(i%5 + 6) +'-' + fd(randNum(30)),
//          stock: i*21,
//          buyNumMax: "50",
//          buyNumMin: "1",
//          price: randNum(i) + '.00',
//          priceMarket: "100.00",
//          priceSettlement: "90.00",
//          priceRetail: "99.00"
//      });
    }
    $.CalendarPrice({
        el: '.container',
        menu_el: '.contant',
//        startDate: '2017-08-02',
        endDate: endDate,
        data: cToObj,
        // 配置需要设置的字段名称
        config: [
		   {
   		        key: 'stock',
        		name: '每天库存'
        	},
        	{
        		key: 'buyNumMax',
        		name: '最多购买数'
        	},
			 {
   		        key: 'priceMarket',
        		name: '门市价'
        	},
        	{
        		key: 'buyNumMin',
        		name: '最少购买数'
        	},
        	{
        		key: 'price',
        		name: '销售价'
        	},
        	{
        		key: 'djbprice',
        		name: '度假币金额'
        	},
        	{
        		key: 'priceSettlement',
        		name: '结算价'
        	},
        	{
        		key: 'priceRetail',
        		name: '度假币限制'
        	},
        	{
        	    key: 'status',
        	    name: '状态',
        	    type: 'radio',
        	    items: [{ text: '正常', value: '0' }, { text: '下架', value: '1' }, ],
        	},
        	{
        	    key: 'paytype',
        	    name: '支付方式',
        	    type: 'select',
        	    items: [ { text: '度假币支付', value: '2' },{ text: '微信or度假币支付', value: '3' } ],
        	}
            
        ],
        // 配置在日历中要显示的字段
        show: [
        	{
        		key: 'price',
                name: '销售价:￥'
        	},
        	{
                key: 'djbprice',
                name: '度假币:￥'
        	},
        	{
                key: 'stock',
        		name: '库:'
        	}
        ],
        callback: function (data) {
         //   console.log(data);
            	var json=JSON.stringify(data)
            var roomid='${roomId}';
            //向后台提交数据
            $.ajax({  
            url: '<%=basePath%>product/insertPrice.html',  
            data: { "data": json,"roomId":roomid },  
            dataType: "json",  
            type: "POST",  
            traditional: true,  
            success: function (data) { 
            Ext.Mytip.msg('提示', data.bundle); 
    }  
});  
            
            
        },
        cancel: function () {
        	console.log('取消设置 ....');
        	// 取消设置
        	// 这里可以触发关闭设置窗口
        	// ...
        },
        error: function (err) {
            console.error(err.msg);
            alert(err.msg);
        },
        // 自定义颜色
        style: {
            // 头部背景色
            headerBgColor: '#098cc2',
            // 头部文字颜色
            headerTextColor: '#fff',
            // 周一至周日背景色，及文字颜色
            weekBgColor: '#098cc2',
            weekTextColor: '#fff',
            // 周末背景色，及文字颜色
            weekendBgColor: '#098cc2',
            weekendTextColor: '#fff',
            // 有效日期颜色
            validDateTextColor: '#333',
            validDateBgColor: '#fff',
            validDateBorderColor: '#eee',
            // Hover
            validDateHoverBgColor: '#098cc2',
            validDateHoverTextColor: '#fff',
            // 无效日期颜色
            invalidDateTextColor: '#ccc',
            invalidDateBgColor: '#fff',
            invalidDateBorderColor: '#eee',
            // 底部背景颜色
            footerBgColor: '#fff',
            // 重置按钮颜色
            resetBtnBgColor: '#77c351',
            resetBtnTextColor: '#fff',
            resetBtnHoverBgColor: '#55b526',
            resetBtnHoverTextColor: '#fff',
            // 确定按钮
            confirmBtnBgColor: '#098cc2',
            confirmBtnTextColor: '#fff',
            confirmBtnHoverBgColor: '#00649a',
            confirmBtnHoverTextColor: '#fff',
            // 取消按钮
            cancelBtnBgColor: '#fff',
            cancelBtnBorderColor: '#bbb',
            cancelBtnTextColor: '#999',
            cancelBtnHoverBgColor: '#fff',
            cancelBtnHoverBorderColor: '#bbb',
            cancelBtnHoverTextColor: '#666'
        }
        // 点击有效的某一触发的回调函数
        // 注意：配置了此参数，设置窗口无效，即不能针对日期做参数设置
        // 返回每天的数据
    //    everyday: function (dayData) {        
       //     console.log('点击某日，返回当天的数据');
        //    console.log(dayData);
    //  },
        // 隐藏底部按钮（重置、确定、取消），前台使用该插件时，则需要隐藏底部按钮
   //  hideFooterButton: true
    });
    //menu.init($('.contant'), endDate);
})();

function randNum(max) {
    return Math.round(Math.random() * max);
}

function fd(n) {
    n = n.toString();
    return n[1] ? n : '0' + n;
}
</script>

</body>
</html>
