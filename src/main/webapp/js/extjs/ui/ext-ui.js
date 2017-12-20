/**
 * @author zhaozhi3758 项目自身扩展的ui组件或常用类
 */
 
// ------------------------------------------- 过滤空格

Ext.apply(Ext.form.TextField.prototype, {
			validator : function(text) {
				if (this.allowBlank == false
						&& Ext.util.Format.trim(text).length == 0)
					return false;
				else
					return true;
			}
		});
Ext.apply(Ext.form.ComboBox.prototype, {
			validator : function(text) {
				if (this.allowBlank == false
						&& Ext.util.Format.trim(text).length == 0)
					return false;
				else
					return true;
			}
		});

//-------------------------------------------------- textarea 配置 maxLength属性限制输入的长度 

Ext.form.TextField.prototype.size = 150;  
Ext.form.TextField.prototype.initValue = function()  
{  
    if(this.value !== undefined){  
        this.setValue(this.value);  
    }else if(this.el.dom.value.length > 0){  
        this.setValue(this.el.dom.value);  
    }  
    this.el.dom.size = this.size;  
    if (!isNaN(this.maxLength) && (this.maxLength *1) > 0 && (this.maxLength != Number.MAX_VALUE)) {  
        this.el.dom.maxLength = this.maxLength *1;  
    }  
  
}; 
		
// -------------------------------------------  覆盖 radiogroup 的赋值和取值方式
		
Ext.override(Ext.form.RadioGroup, {
		setValue : function(v) {
			if (this.rendered) {
				this.items.each(function(item) {
							item.setValue(item.inputValue == v);
				});
			} else {
				for (var k in this.items) {
					this.items[k].checked = this.items[k].inputValue == v;
				}
			}
		},
		getValue : function() {  
            var v;  
            this.items.each(function(item) {  
                if (item.getValue()) {  
                    v = item.getRawValue();  
                    return false;  
                }  
            });  
            return v;  
        }
});

// --------------------------------------------------分页组件封装

var PageInfoBar = Ext.extend(Ext.PagingToolbar, {
			displayInfo : true,
			prependButtons : true,
			displayMsg : '显示{0}条至{1}条，共{2}条',
//			plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			emptyMsg : "没有查询到相关记录",
			initComponent : function() {
				var _this = this;
				_this.items = ['-', '&nbsp;&nbsp;', new Ext.form.ComboBox({
							hiddenName : 'pagesize',
							typeAhead : true,
							triggerAction : 'all',
							lazyRender : true,
							mode : 'local',
							store : new Ext.data.ArrayStore({
										fields : ['value', 'text'],
										data : [[5, '5条/页'], [10, '10条/页'],[15, '15条/页'],
												[20, '20条/页'], [30, '30条/页'],
												[50, '50条/页'],[100, '100条/页']]
									}),
							valueField : 'value',
							displayField : 'text',
							value : _this.pageSize,
							editable : false,
							width : 85,
							listeners : {
								select : function(comboBox) {
									if (!_this.pageMethod) {
										Ext.Msg.alert('提示', 'pageMethod未指定！');
									}
									var number = parseInt(comboBox.getValue());
									_this.pageSize = number;
									_this.pageMethod(number,0);
								}
							}
						})

				]
				PageInfoBar.superclass.initComponent.call(this);
			}
			,
			doLoad : function(start){
		        var o = {}, pn = this.getParams();
		        o[pn.start] = start;
		        o[pn.limit] = this.pageSize;
		        if(this.fireEvent('beforechange', this, o) !== false){
		        	if(this.pageMethod!=null){
		        		this.pageMethod(this.pageSize,start);
					}else{
						this.store.load({params:o});
					}
		        }
		    }
		});
Ext.reg('pageinfobar', PageInfoBar);

// -------------------------------------------------- window 组件封装

var ExtWin = Ext.extend(Ext.Window, {
			layout : 'fit',
			width : 500,
			height : 300,
			resizable : false,
//			draggable : false,
			closeAction : 'hide',
			title : '',
			plain : true,
			modal : true,
			collapsible : false, 
			titleCollapse : false,
			maximizable : false,
			border : false,
			animCollapse : true,
			animateTarget : Ext.getBody(),
			constrain : true,
			buttonAlign : 'center',
			initComponent : function() {
				var config = {
					pageY : 20,
					pageX : document.body.clientWidth / 2 - this.width / 2

				};
				Ext.apply(this, config);
				ExtWin.superclass.initComponent.call(this);
			}
		});
Ext.reg('extwin', ExtWin);

// -------------------------------------------------- store 组件封装
// 框架 spring mvc
var FrameWorkStore = Ext.extend(Ext.data.JsonStore, {
			constructor : function(_cfg) {
				var config = {
					 totalProperty : 'results',
					 root : 'list',
					 idProperty : 'id',
					 listeners : { 
						 loadexception : safetys.storeLoadError
					 },
					 paramNames : { start : 'page.start',limit : 'page.limit',sort : 'page.sort',dir : 'page.dir' }

				};
				Ext.apply(config, _cfg);
				config.url = cfg.cxt + config.url;
				FrameWorkStore.superclass.constructor.call(this,config);
			}
		});
Ext.reg('frameworkstroe', FrameWorkStore);

// spring mvc 使用
var ExtStore = Ext.extend(Ext.data.JsonStore, {
	constructor : function(_cfg) {
		var config = {
			 totalProperty : 'results',
			 root : 'list',
			 idProperty : 'id',
			 listeners : { 
				 loadexception : safetys.storeLoadError
			 },
			 paramNames : { start : 'start',limit : 'limit',sort : 'sort',dir : 'dir' }

		};
		Ext.apply(config, _cfg);
		config.url = cfg.cxt + config.url;
		ExtStore.superclass.constructor.call(this,config);
	}
});
Ext.reg('extstroe', ExtStore);

//-------------------------------------------------- grid 组件封装
var ExtGrid = Ext.extend(Ext.grid.GridPanel, {
			loadMask : { msg : '加载数据中，请稍候...'},
			autoScroll : true,
			border : false,
			stripeRows : true,
			viewConfig : { forceFit : true },
			initComponent : function() {
				ExtGrid.superclass.initComponent.call(this);
			}
		});
Ext.reg('extgrid', ExtGrid);

// -------------------------------------------------- ajax 同步调用,返回类型为json字符串

var ajaxSyncCall = function(urlStr, paramsStr) {
		var obj,res;
		if (window.ActiveXObject) {
			obj = new ActiveXObject('Microsoft.XMLHTTP');
		} else if (window.XMLHttpRequest) {
			obj = new XMLHttpRequest();
		}
		obj.open('POST', urlStr, false);
		obj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		obj.send(paramsStr);
		var res = obj.responseText;
		try{
			Ext.decode(res);//如果因传递错误的url，或者服务器报错，这里会出现异常
		} catch(e){
			Ext.Msg.alert("出错","数据解析失败，或者url不可用！")
			return "";
		}
	    return res;
}


// -------------------------------------------------- 右下角弹出 TipWindow

var TipWindowMgr = {
	positions : []
};
var TipWindow = Ext.extend(Ext.Window, {
			initComponent : function() {
				Ext.apply(this, {
							iconCls : this.iconCls || 'information',
							autoScroll : true,
							autoDestroy : true,
							plain : false,
							bodyStyle : 'background-color:#FFFFFF;font-size: 13px;',
							shadow : false
						});
				this.task = new Ext.util.DelayedTask(this.hide, this);
				TipWindow.superclass.initComponent.call(this);
			},
			setMessage : function(msg) {
				this.body.update(msg);
			},
			setTitle : function(title, iconCls) {
				TipWindow.superclass.setTitle.call(this, title, iconCls || this.iconCls);
			},
			onRender : function(ct, position) {
				TipWindow.superclass.onRender.call(this, ct, position);
			},
			onDestroy : function() {
				TipWindowMgr.positions.remove(this.pos);
				TipWindow.superclass.onDestroy.call(this);
			},
			afterShow : function() {
				TipWindow.superclass.afterShow.call(this);
				this.on('move', function() {
							TipWindowMgr.positions.remove(this.pos);
							this.task.cancel();
						}, this);
				this.task.delay(5000);
			},
			animShow : function() {
				this.pos = 0;
				while (TipWindowMgr.positions.indexOf(this.pos) > -1)
					this.pos++;
				TipWindowMgr.positions.push(this.pos);
				this.setSize(this.width, this.height);
				this.el.alignTo(document, "br-br",
						[-20, -20 - ((this.getSize().height + 10) * this.pos)]);
				this.el.slideIn('b', {
							duration : 2,
							callback : this.afterShow,
							scope : this
						});
			},
			animHide : function() {
				TipWindowMgr.positions.remove(this.pos);
				this.el.ghost("b", {
							duration : 2,
							remove : true,
							scope : this,
							callback : this.destroy
						});
			}
});

// -------------------------------------------------- 文件上传窗口

var SwfWin = Ext.extend(ExtWin, {
			title : '文件上传',
			closable : false,
			width : 500,
			height : 300,
			uploadConfig :null,
			listeners : {
				show : function(){
					 UploadPanel.grid.getStore().removeAll();
				}
				
			},
			checkFile : function(){
				var _return = {};
				_return.rel = true;
				var ds  = UploadPanel.grid.getStore();
		    	var upnum =  ds.getCount();
		    	if( upnum == 0){
		    		alert('上传列表中，不存在您要上传的文件！');
		    		return _return;
		    	}
		    	var _store = new Ext.data.SimpleStore({
					fields : ['fileId', 'fileName', 'fileSize', 'fileType', 'fileState']
				});
		    	//判断文件 -4 上传成功 -5 取消上传
		    	for( var i=0;i<upnum;i++){
		    		var r = ds.getAt(i);
			    	if (r.data.fileState == SWFUpload.FILE_STATUS.QUEUED
							|| r.data.fileState == SWFUpload.FILE_STATUS.IN_PROGRESS) {
			    		alert('您所选的文件尚未上传！');
						return _return;
					}
			    	if(r.data.fileState == SWFUpload.FILE_STATUS.ERROR){
			    		alert('文件未上传成功！');
						return _return;
			    	}
			    	if(r.data.fileState == SWFUpload.FILE_STATUS.COMPLETE ){
			    		_store.add(r);
			    	}
		    	}
		    	
		    	
		    	_return.rel = false;
		    	_return.store = _store;
		    	return _return;
			},
			initComponent : function() {
				var config = {
					xtype : 'uploadpanel',
					id : '_upload',
					uploadUrl :  cfg.cxt+'/upload.do',
					filePostName : 'upfile',
					flashUrl : cfg.cxt+'/js/extjs/ux/uploadpanel/swf/swfupload.swf',
					fileSize : '10MB',
					border : false,
					singleFile : false,//是否上传队列中只能放置一个文件
					fileTypes : '*.*', // 在这里限制文件类型:*.jpg;*.png;*.gif
					fileTypesDescription : '所有文件',
					postParams : {
						'uploaduser' : 'swfuploader'//上传者
						//'uploadpath' : 'module' //分类路径
					}
				} 
				
				this.items = [ this.uploadConfig ?  Ext.apply(config,this.uploadConfig) : config ];
				SwfWin.superclass.initComponent.call(this);
				
			}
		});
Ext.reg('swfwin', SwfWin);

//-------------------------------------------------- 选择树（可作为区域或者枚举等编码字典的选择）

DisplayTree = Ext.extend(Ext.tree.TreePanel, {
			autoScroll : true,
			height : 200,
			border : false,
			bbar : [{
			   text : '取消所选',
			   iconCls : 'deleteIcon',
			   height : 10,
			   handler : function() {
			   	    var thiz =  this.ownerCt.ownerCt.ownerCt;
					thiz.setValue("");
					thiz.tree.getRootNode().reload();
					thiz.canCollapse = true;
			   }
			}],
			rootItem : {
				id : "",
				name : "",
				viewCode : ""
			},
			ownerCt : {},
			constructor : function(_cfg) {
				if (_cfg == null) {
					_cfg = {};
				}
				Ext.apply(this, _cfg);
				this.rootVisible = this.ownerCt.rootVisible || false;
				this.root = new Ext.tree.AsyncTreeNode({// 根节点
					text : this.rootItem.name,
					id : this.rootItem.id,
					viewCode : this.rootItem.viewCode,
					expanded : true
				});
				if(Ext.isEmpty(this.ownerCt.sListUrl))Ext.Mytip.msg('提示','sListUrl未指定！');
				this.loader = new Ext.tree.TreeLoader({
							autoLoad : true,
							url : cfg.cxt + "/"+ this.ownerCt.sListUrl,
							processResponse : function(response, node, callback) {
								var json = Ext.util.JSON.decode(response.responseText);
								try {
									node.beginUpdate();
									var l = this;
									Ext.each(json, function(item) {
										var n = l.createNode(item);
										if (n) {
											node.appendChild(n);
										}
									});
									node.endUpdate();
									this.runCallback(callback, scope || node,node);
								} catch (e) {
									this.handleFailure(response);
								}
							},
							listeners : {
								beforeload : function(l, n, c) {
									l.baseParams = {
										viewCode : n.attributes.viewCode
									}
								}
							}
						})
				DisplayTree.superclass.constructor.apply(this, arguments);
			},
			listeners : {
				click : function(node) {
					this.ownerCt.canCollapse = true;
					if (Ext.isFunction(this.ownerCt.setValue)){
						this.ownerCt.bnode = node;
						this.ownerCt.setValue(node.attributes);
					}
						
					this.ownerCt.collapse();
				},
				expandnode : function() {
					this.ownerCt.canCollapse = true;
				},
				beforeload : function() {
					this.ownerCt.canCollapse = false;
				},
				beforeexpandnode : function() {
					this.ownerCt.canCollapse = false;
				},
				beforecollapsenode : function() {
					this.ownerCt.canCollapse = false;
				}
			}
});

BmzdTree = Ext.extend(Ext.form.ComboBox, {
			store : new Ext.data.ArrayStore({
						fields : [],
						data : [[]]
			}),
			selectItem : {
				id : "",
				name : "",
				viewCode : ""
			},
			rootVisible : false,
			viewCode : "",
			border : false,
			editable : false,
			shadow : false,
			mode : 'local',
			triggerAction : 'all',
			selectedClass : '',
			onSelect : null,
			canCollapse : true,
			bnode : null,
			parentLabel:true,
			constructor : function(_cfg) {
				if (_cfg == null) {
					_cfg = {};
				}
				Ext.apply(this, _cfg);
				this.treerenderid = Ext.id();
				this.tpl = String.format('<tpl for="."><div id="ext-combobox-tree{0}"></div></tpl>',this.treerenderid);
				BmzdTree.superclass.constructor.apply(this, arguments);
				var cmb = this;
				if(Ext.isEmpty(this.vCodeUrl))Ext.Mytip.msg('提示','vCodeUrl未指定！');
				if (!Ext.isEmpty(this.viewCode)) {
					Ext.Ajax.request({
								url :  cfg.cxt + "/"+ this.vCodeUrl,
								params : {
									viewCode : this.viewCode
								},
								method : "POST",
								success : function(response, opt) {
									try {
										var etree = Ext.util.JSON.decode(response.responseText);
										if (etree) {
											var ritem = {
												id : etree.id,
											    name : etree.text,
												viewCode : etree.viewCode
											};
											cmb.tree = new DisplayTree({
													rootItem : ritem,
													ownerCt : cmb,
													height : cmb.height || 200,
													width  : cmb.width || 188 //ie must set width
											 });
										} else {
											Ext.Msg.alert("异常", String.format("[{0}] 没有找到对应的编码", cmb.viewCode))
										}
									} catch (e) {
									}
								},
								failure : function(response, otp) {
									Ext.Msg.alert("error", response);
								}
							});
				}
				this.on('expand', this.expandHandler, this);
				this.on('collapse', this.collapseHandler, this);
			},
			setValue : function(v) {
				var text = v;
				if (Ext.isObject(v)) {
					text = v.text;
					this.hiddenValue=v.viewCode;
					this.selectItem = v
					if (this.hiddenField) {
						this.hiddenField.value = v.viewCode;
					}
				}
				if(Ext.isEmpty(v)){
					this.selectItem = {};
					if (this.hiddenField) {
						this.hiddenField.value = "";
					}
				}
				var att = [];
				if(this.bnode && this.parentLabel){
					att.push(this.bnode.attributes.text);
				    var p = this.bnode.parentNode;
					while(p){
						att.push(p.attributes.text);
						p = p.parentNode;
					}
					text = att.reverse().join(" ");
				}else if(this.bnode && !this.parentLabel){
					text = this.bnode.attributes.text;
				}
				Ext.form.ComboBox.superclass.setValue.call(this, text);
				this.value = v;
				return this;
			},
			expandHandler : function expand() {
				this.canCollapse = true;
				if (this.tree) {
					this.tree.render('ext-combobox-tree' + this.treerenderid);
					this.canCollapse = true;
					if(this.expandAll){ //这个选项配置，将展开所有节点
						this.tree.expandAll();
					} else {
					    this.tree.getRootNode().expand();
					}
					
				}
			},
			collapseHandler : function collapse() { //点击加载节点保持展开状态
				if (!this.canCollapse) {
					this.expand();
				}
			}

		});
Ext.reg('bmzdtree', BmzdTree);

//-------------------------------------------------- KindEditor  富文本编辑器封装

KindEditorShow= function(config){
	 this.textareaId="textareaId";
	 this.width=550;
	 this.name="";
	 this.height=200;
	 this.renderTo=Ext.getBody();
	 this.items=[
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'table', 'image', 'link'];
	if(config.name!=null){
		this.name=config.name;
	} 
	if(config.name!=null){
		this.name=config.name;
	}else{
		this.name=this.textareaId;
	} 
	if(config.height!=null){
		this.height=config.height;
	}
	if(config.width!=null){
		this.width=config.width;
	}
	if(config.textareaId!=null){
		this.textareaId=config.textareaId;
	}
	if(config.items!=null){
		this.items=config.items;
	}
	var textareaHtml = '<textarea id="' + this.textareaId + '" name="'
				+ this.name + '" style="width:'+this.width+'px;height:'+this.height+'px;visibility:hidden;"></textarea>';
		   
	if(config.renderTo!=null){
		this.renderTo=config.renderTo;
		var renderToDiv =document.getElementById(this.renderTo);
		if(renderToDiv!=null){
		    renderToDiv.innerHTML=textareaHtml;
		}
	} else {
		Ext.getBody().insertHtml(textareaHtml);
	}
	This=this;
	return KindEditor.create('textarea[id="'+textareaId+'"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		uploadJson : 'js/public/kindeditor/jsp/upload_json.jsp',
		items :This.items 
		
	});
	
}
