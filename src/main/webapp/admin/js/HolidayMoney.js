
//格式化时间
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function  fycx(numstr){
			 clearOldData();
			 var pageNum ='20';  // 一页显示10个
			 if(numstr=='0'){// 初始化分页显示
				 var  curPage="1";	// 当前页
			 }else{
				 var  curPage=numstr;// 当前页
			 }
			 debugger;
		   var queryName=$("#queryName").val();
           var queryFriend=$("#queryFriend").val();
		   var jsonData={"queryName":queryName,"queryFriend":queryFriend, "pageNum":pageNum,"curPage":curPage};
			$.ajax({
				url:url+"user/holidaylist.html",
				data:jsonData,
				cache:false,
				async: false,
				type:"post",
				dataType:"json",
				success:function(_data) {
					// 定义好赋值字段的顺序
				  pages =_data.pagecount;
				  data=_data.info;
				    var str="";	
					for(var i = 0; i < data.length;i++) {
						var json = data[i];
                        debugger;
                        var d=  new Date();



                        var friendName=json.friendName==null?'/':json.friendName;
                        var sum=json.sum==null?'0':json.sum;
                        var receiveTime=json.receiveTime=="0000-0-00 12:00:00"?"未领取":json.receiveTime;
                        var giveTime=json.giveTime==null?"":json.giveTime;


						 str+="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\"    height=\"22\"  class=\"tr\">";
						 str+="	 <td align=\"center\">  "+json.id;
						 str+="	</td>  	<td  align=\"center\"> "+json.yzName ;
						 str+="	 </td>	<td   align=\"center\"> "+friendName;
                         str+="	 </td>	<td   align=\"center\"> "+sum;
                         str+="	 </td>	<td   align=\"center\"> "+giveTime;
                         str+="	 </td>	<td   align=\"center\"> "+receiveTime;
						 str+="  </td> </tr>";
					}
					if(data.length == 0){
						alert("未查询到数据，请重新检索！");
					}
				     $("#tr01").after(str);

				}
			});
		 }
		
		//清除
		function clearOldData() {
			$(".tr").remove();
		}    
        
		function test(){
			laypage({
			    cont: 'biuuu_city',
			    pages: pages,
			    jump: function(obj){
			       fycx(obj.curr); 
			    }
			})
	}