		var vue = new Vue({
		el : '#content',
		data : {
			sysRole : {}
		},
		methods : {
			addForm : function() {
				vue.sysRole = {};
				openForm("新增");
			},
			menuForm:function(){
				var id= getSelectedRow();
				if (id != '') {
					vue.sysRole.id=id;
					var url=transUrl('/sysrolemenu/menulist/')+id;
					//zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
					var setting = {
							 view: {
					          selectedMulti: true
					      },
					      data: {
					          simpleData: {
					        	 enable: false,
					  			idKey: "id",
					  			pIdKey: "pid",
					  			rootPId: 0
					          }
					      },
					      edit: {
					          enable: false,
					      },
							check:{
								enable: true,
								chkStyle: "checkbox",
								chkboxType: { "Y": "p", "N": "s" }
							}
							
					};
					var zTreeObj;
					 //初始化数据列表 
					var zNodes = null;
					$.get(url,function(r){
						zNodes=r.menulist;
						//获取服务器数据
						zTreeObj = $.fn.zTree.init($("#menu-tree"), setting, zNodes);
						vue.modalTitle="分配权限";
						$("#menumodal").modal("show");
					})
						
				}
			},
			saveMenu:function(){
				var treeObj = $.fn.zTree.getZTreeObj("menu-tree");
				var nodes = treeObj.getCheckedNodes(true);;
				var menuIds=[];
				$.each(nodes,function(index,item){
					menuIds[index]=item.id;
				})
				var url=transUrl("/sysrolemenu/save?id=")+vue.sysRole.id+"&menuIds="+menuIds;
				$.get(url, function (r) {
					if (r.code === 0) {
					} else {
						alert(r.msg);
					}
					$('#menumodal').modal('hide');
				});
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/sysrole/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysRole=r.sysRole;
						openForm("修改信息");
						}
				    });
					if(vue.sysRole==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/sysrole/delete");
						$.ajax({
							type : "POST",
							url : url,
							contentType : "application/json",
							data : JSON.stringify(id),
							success : function(r) {
								if(check(r)){
									vue.reload();
									tips("删除成功");
								}
							},
							error : function() {
								alert("网络错误，请稍后重试");
							}
						});
					});
				}
			},
			saveOrUpdate : function() {
				var url = vue.sysRole.id == null ? transUrl("/sysrole/save") : transUrl("/sysrole/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysRole),
					success : function(r) {
						if(check(r)){
							vue.reload();
							closeForm();
							tips("操作成功");
						}
					}
				});
			},
			reload : function() {
				var table=$("tr.selected").eq(0).closest("table");
				table=table.length==0?$('#datatable'):table;
				table.bootstrapTable('refresh', {query:{
					searchField : $("#searchField").val(),
					searchText : $("#searchText").val(),
					startTime : "",
					endTime : ""
				},'silent': true});
			},
			timeQuery : function() {
				$('#datatable').bootstrapTable('refresh', {query:{
					searchField : "",
					searchText : "",
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},'silent': true});
			},
			exportData : function(exportType) {
				$('#datatable').tableExport({
					type : exportType,
					escape : 'false',
					fileName : 'sysRole'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/sysrole/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$('#datatable').on('post-body.bs.table',function(){
			$(".getDept").each(function(){
				getDept(this);
			})
			$(".getUser").each(function(){
				getUser(this);
			})
		})
		$("#modal-form").validate({
			rules : {			
				name : {
					required : true
				},
				remark : {
					required : true
				},
				createId : {
					required : true
				},
				createTime : {
					required : true
				},
				deptId : {
					required : true
				}			},
			messages : {
				name : {
					required : "请填写角色名称"
				},
				remark : {
					required : "请填写备注"
				},
				createId : {
					required : "请填写创建者ID"
				},
				createTime : {
					required : "请填写创建时间"
				},
				deptId : {
					required : "请填写部门ID"
				}			},
			submitHandler : function(form) {
				vue.saveOrUpdate();
			},
			errorPlacement : function(error, element) {
				tipError(error, element);
			}
		});
	});
	var initColumn = function() {
		return [ {
			checkbox : true
		}, 	{
			title : '序号',
			field : 'id',
			align : 'left',
			visible : true,
			valign : 'middle',
			formatter : function(value, row, index) {
				return autoId("datatable", index);
		}}, 	{
			title : '主键',
			field : 'id',
			align : 'left',
			visible : false,
			valign : 'middle'
		},{
			title : '角色名称',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '备注',
			field : 'remark',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '创建者ID',
			field : 'createId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '创建时间',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '部门ID',
			field : 'deptId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		} ];
	};
