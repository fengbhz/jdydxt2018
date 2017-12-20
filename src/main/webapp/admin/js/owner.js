$(function(){
    		// 让当前表单调用validate方法，实现表单验证功能
    		$("#coinManageForm").validate({
    		 rules: {
	      coin: {
	         required: true,
	         digits:true
	      },
	    },
	    messages: {
	      coin: {
	        required: "请输入金额",
	        digits: "金额只能为正整数"
	         },
 	         }
	        });
	        
       	$("#QyForm").validate({
       	
       	   });
    	});

      var _qy="";		    		


		// 权益内容添加修改
		  function QyManage(id,qy,oid){
		  	$('#QyDialog').dialog({
				    title: '业主权益',
				    align:"center",
				    width: 600,
				    height: 300,
				    closed: false,
				    cache: false,
				    modal: true,
				    onOpen:function(){// 将业主的权益内容加载进去
		  	       
		  		$.ajax({
					url:url1+"owner/ownerQy.html",
					data:{"oid":id},
					cache:false,
					async: false,
					type:"post",
					dataType:"json",
					success:function(_data) {
					if(_data.success){
						_qy=_data.data;
						   $("#orderId").val(id);
						      $("#qy").val(_qy);
					}
				}
			});
				      
				      },
				    buttons:[{
						text:'确定',
						handler:function(obj){
					    	$("#QyForm").submit();
						}
					},{
						text:'取消',
						handler:function(){
							$('#QyDialog').window('close'); 
						}
					}]
				});
			}
		
			function addOwner(){
				$('#addOwnerDialog').dialog({
				    title: '添加业主',
				    width: 600,
				    height: 200,
				    closed: false,
				    cache: false,
				    modal: true,
				    buttons:[{
						text:'确定',
						handler:function(obj){
						 addOwnerForm();
						}
					},{
						text:'取消',
						handler:function(){
							$('#addOwnerDialog').window('close'); 
						}
					}]
				});
			}
			
			  function addOwnerForm(){
			  var mobile=$("#mobile").val();
			  var id =$("#id").val();
			   	$.ajax({
						url : "platform/checkUser.html",
						data : {
							"account" : mobile,
							"phone" : mobile
						},
						type : "post",
						dataType : "json",
						success : function(data) {
						   if(data.success==true){
						   alert("此号码已经被占用，请更换号码重试！");
						       return;
						   }else{
						   	$("#addOwnerForm").submit();
						   }
						
						}
					});
			  }

            // 去左右空格;
            function trim(s){
                return s.replace(/(^\s*)|(\s*$)/g, "");
            }
			  function updateOwer(oid,uid,obj,kind,hotelname){
			  $("#uid").val(uid);
			  $("#owerid").val(oid);
				  // 先将数据加载到页面
                var  name=  $(obj).parent().parent().find("td").eq(1).text().replace("\"","");
                var  mobile=  $(obj).parent().parent().find("td").eq(2).text().replace("\"","");
                var  sfzhm=  $(obj).parent().parent().find("td").eq(4).text().replace("\"","");
                  $('#addOwnerDialog').dialog({
                      title: '业主修改',
                      width: 600,
                      height: 200,
                      closed: false,
                      cache: false,
                      modal: true,
                      onOpen:function(){
                          $("#ownerName").val(trim(name));
                          $("#mobile").val(trim(mobile));
                          $("#idCard").val(trim(sfzhm));
                            $(".selector").find("option[value='"+trim(kind)+"']").attr("selected",true);
                           $(".selector2").find("option[value='"+hotelName+"']").attr("selected",true);
                          
                      },
                      buttons:[
                          {
                          text:'确定',
                          handler:function(obj){
                              addOwnerForm();
                          }
                      },{
                          text:'取消',
                          handler:function(){
                              $('#addOwnerDialog').window('close');
                          }
                      }]
                  });


              }
			
			function moneyManage(id, name, sum,type){
			if(type==1||type==3){
			     }else{
			     alert("该用户暂时还未激活或审核，无法进行充值操作!");
			      return ;
			   }
				$("#ownerNameLabel").html(name);
				$("#coinLabel").html(sum);
				$("#ownerId")[0].value = id;
				$('#coinManageDialog').dialog({
				    title: '充值扣款',
				    width: 500,
				    height: 220,
				    closed: false,
				    cache: false,
				    modal: true,
				    buttons:[{
						text:'确定',
						handler:function(obj){
                              if($("#opType").val()=='1'){    // 扣款
                               if( parseInt($("#coinLabel").text()==""?0:$("#coinLabel").text())-parseInt($("#coin").val())<0){
                                     alert("操作错误，扣款总数不能大于余额总数！")
								   return;
							   }else{
                                   $("#coinManageForm").submit();
							   }
							  }else{
                                  $("#coinManageForm").submit();
							  }	


						}
					},{
						text:'取消',
						handler:function(){
							$('#coinManageDialog').window('close'); 
						}
					}]
				});
			}
			
			function approve(id, status,account){
				$("#ownerId1")[0].value = id;
				$("#account").val(account) ;
				$("#tempApprove")[0].value = status;
				$('#fasDialog').dialog({
				    title: '审批结果',
				    width: 500,
				    height: 220,
				    closed: false,
				    cache: false,
				    modal: true,
				    buttons:[{
						text:'确定',
						handler:function(obj){
							$("#approveForm").submit();
						}
					},{
						text:'取消',
						handler:function(){
							$('#fasDialog').window('close'); 
						}
					}]
				});
			}
			
			function delOwner(id, status){
				window.location.href = "DelOwnerAction?id=" + id + "&status=" + status;
			}
			
			function resetCoin(){
			  if(confirm("确定要进行一键清零操作吗?")){
				window.location.href = "ResetCoinAction?type=1";
			  }
			}
			
			function  fycx(numstr){
				 clearOldData();
				 var pageNum ='20';  // 一页显示10个
				 if(numstr=='0'){// 初始化分页显示
					 var  curPage="1";	// 当前页
				 }else{
					 var  curPage=numstr;// 当前页
				 }
			   var queryName=$("#queryName").val();
			   var queryMobile=$("#queryMobile").val();
			   var queryStatus=$("#queryStatus").val();
			   var jsonData={"queryName":queryName,"queryMobile":queryMobile,"queryStatus":queryStatus,"pageNum":pageNum,"curPage":curPage};
				$.ajax({
					url:url1+"owner/ownerList.html",
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
							var qy=json.ownerqy==null?"":json.ownerqy;
						   // qy =decToHex(qy);
							 str+="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\"  class=\"tr\">";	
							 str+="	<td> "+(i+1)+" </td> <td>   "+json.name;
                             str+="	</td>  	<td> "+json.account;
							 str+="	</td>  	<td> "+json.mobile;	
							 str+="	</td>  	<td> "+json.virtualCoin;	
							 str+="	</td>  	<td> "+json.idCard;	
							 str+="	</td>  	<td> "+json.kind;	
							 str+="	</td>  	<td> "+json.inputDate;	
							 str+="	</td>  	<td> ";	
							   if(json.status=='0'){
								   str+="	<input type=\"button\" style=\"width:50px; height:25px; background-color:red; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\" onclick=\"delOwner('"+json.id+"', '1')\" value=\"停用\"/>";		   
							   }else{
								   str+="   <input type=\"button\" onclick=\"delOwner('"+json.id+"', '0')\" value=\"启用\"/>";
							   }
							   str+=" 	| <input type=\"button\" onclick=\"updateOwer('"+json.id+"','"+json.uid+"', this,'"+json.kind+"','"+json.hotelName+"')\" value=\"修改\"/> | ";
							   if(json.applyStatus=='0'){
								   str+=" <input type=\"button\"   style=\"width:100px; height:25px; background-color:#3f97c9; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\"  onclick=\"approve('"+json.id+"', '0','"+json.account+"')\" value=\"审核\"/> |	";  
							   }else if(json.applyStatus=='1'){
								   str+=" 	<input type=\"button\"  style=\"width:100px; height:25px; background-color:#6CCA8F; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\"    value=\"已审核\"/> | ";
							   }else if(json.applyStatus=='2'){
								   str+="  <input type=\"button\"  style=\"width:100px; height:25px; background-color:#3f97c9; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\"   value=\"待激活\"/> |";
						       }else if(json.applyStatus=='3'){
								   str+="  <input type=\"button\"  style=\"width:100px; height:25px; background-color:#6CCA8F; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\"  value=\"已激活\"/> ||";
							   }else{
								 str+=" <input type=\"button\"  style=\"width:100px; height:25px; background-color:#7f878c; font-size:13px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;\"  value=\"未通过\"/> | ";  
							   }
							   str+="  <input type=\"button\" onclick=\"moneyManage('"+json.id+"', '"+json.name+"',  '"+json.virtualCoin+"', '"+json.applyStatus+"')\" value=\"充值扣款\"/>  | ";
							   str+="   | <input type=\"button\" onclick=\"QyManage('"+json.id+"', '','"+json.id+"')\" value=\"业主权益\"/>  " ;
							   str+=" </td> </tr>";		
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
			
			
			function   getQy(id){
				$.ajax({
					url:url1+"owner/ownerQy.html",
					data:{"oid":id},
					cache:false,
					async: false,
					type:"post",
					dataType:"json",
					success:function(_data) {
					if(_data.success){
						_qy=_data.data;
					}
				}
			});
			}
			
			// 转为unicode 编码  
		/*	function encodeUnicode(str) {  
			    var res = [];  
			    for ( var i=0; i<str.length; i++ ) {  
			    res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);  
			    }  
			    return "\\u" + res.join("\\u");  
			}  
	        
	     // 解码  
	        function decodeUnicode(str) {  
	            str = str.replace(/\\/g, "%");  
	            return unescape(str);  
	        }  
	        
	        
	        
	        var decToHex = function(str) {
	            var res=[];
	            for(var i=0;i < str.length;i++)
	                res[i]=("00"+str.charCodeAt(i).toString(16)).slice(-4);
	            return "\\u"+res.join("\\u");
	        }
	        
	        var hexToDec = function(str) {
	            str=str.replace(/\\/g,"%");
	            return unescape(str);
	        }*/
	        
	function test(){
		laypage({
		    cont: 'biuuu_city',
		    pages: pages,
		    jump: function(obj){
		       fycx(obj.curr); 
		    }
		})
}