		var vue = new Vue({
		el : '#content',
		data : {
			sysUser : {},
			rolelist:[],
			roleIds:[],
			editPass:{},
			ShowPass:false
		},
		methods : {
			addForm : function() {
				vue.sysUser = {};
				openForm("新增");
			},
			roleForm:function(){
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/sysrole/rolelist/");
					$.get(url+ id, function (r) {
						vue.sysUser.id=id;
						vue.modalTitle="分配角色";
						vue.rolelist=r.rolelist;
						var i=0;
						$.each(r.rolelist,function(index,item){
							if(item.checked==true){
								vue.roleIds[i]=item.id;
								i++
							}
						})
						$('#rolemodal').modal('show');
				    });
					if(vue.sysUser==null){alert("网络错误")}
				}
			},
			saveRole:function(){
				var url=transUrl("/sysuserrole/save?id=")+vue.sysUser.id+"&roleIds="+vue.roleIds;
				
				$.get(url, function (r) {
					if (r.code === 0) {
						vue.reload();
					} else {
						alert(r.msg);
					}
					$('#rolemodal').modal('hide');
				});
			},
			editPassForm:function(){
				var id=getSelectedRow();
				if (id != '') {
						vue.editPass.id=id;
						vue.modalTitle="重置密码";
						$('#passmodal').modal('show');
				}
			},
			resetPassword:function(){
				if(vue.editPass.password==vue.editPass.confirmpassword&&vue.editPass.password.length>5){
				var url=transUrl("/sysuser/resetpass?userId=")+vue.editPass.id+"&password="+vue.editPass.password+"&confirmpassword="+vue.editPass.confirmpassword;
				$.get(url, function (r) {
					if (r.code === 0) {
					} else {
						alert(r.msg);
					}
					$("#passerror").html("");
					$('#passmodal').modal('hide');
			    });
				}else{
					$("#passerror").html("密码有误，密码至少需要6位");
				}
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/sysuser/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysUser=r.sysUser;
						openForm("修改信息");
						}
				    });
					if(vue.sysUser==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/sysuser/delete");
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
				var url = vue.sysUser.id == null ? transUrl("/sysuser/save") : transUrl("/sysuser/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysUser),
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
					fileName : 'sysUser'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/sysuser/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$('#datatable').on('post-body.bs.table',function(){
			$(".getUser").each(function(){
				getUser(this);
			})
		})
		$("#modal-form").validate({
			rules : {			
				username : {
					required : true
				},
				password : {
					required : true
				},
				email : {
					required : true
				},
				mobile : {
					required : true
				},
				status : {
					required : true
				},
				createId : {
					required : true
				},
				createTime : {
					required : true
				},
				updateId : {
					required : true
				},
				updateTime : {
					required : true
				}			},
			messages : {
				username : {
					required : "请填写用户名"
				},
				password : {
					required : "请填写密码"
				},
				email : {
					required : "请填写邮箱"
				},
				mobile : {
					required : "请填写手机号"
				},
				status : {
					required : "请填写状态  0：禁用   1：正常"
				},
				createId : {
					required : "请填写创建者ID"
				},
				createTime : {
					required : "请填写创建时间"
				},
				updateId : {
					required : "请填写更新者ID"
				},
				updateTime : {
					required : "请填写更新时间"
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
		}},{
			title : '主键',
			field : 'id',
			align : 'left',
			visible : false,
			valign : 'middle'
		},{
			title : '用户名',
			field : 'username',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '邮箱',
			field : 'email',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '手机号',
			field : 'mobile',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '状态',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return transAccountStatus(value);
			}
		},{
			title : '创建者',
			field : 'createId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%'
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
			title : '更新者',
			field : 'updateId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%'
		},{
			title : '更新时间',
			field : 'updateTime',
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