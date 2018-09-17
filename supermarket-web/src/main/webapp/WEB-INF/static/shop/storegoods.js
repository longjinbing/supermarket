		var vue = new Vue({
		el : '#content',
		data : {
			storeGoods : {},
            categoryList:[],
			edit:false
		},
		methods : {
			addForm : function() {
				vue.storeGoods = {};
                vue.loadCategoryList();
                vue.initScan();
				openForm("新增");
			},
			initScan:function(){
                var load=function() {
                    $.ajax({
                        type: "POST",
                        url: transUrl('/storecart/query/1'),
                        data: {},
                        contentType: "application/x-www-form-urlencoded",
                        success: function (r) {
                            console.log(r);
                            if (r.data != undefined) {
                                vue.$data.storeGoods.goodsSn = r.data.goodsSn;
                                $("#goodsSn").val(r.data.goodsSn);
                               vue.$data.edit=true;
                                $("#goodsSn").parent().next().find(".scan-result").html("扫描成功");
                                timer=''
                            }else{
                                var timer=setTimeout(function(){
                                    load();
                                },2000);
							}
                        },
                        error: function () {
                            $("#result_strip").html('<p class="scan-error">网络错误，请检测网络</p>');
                        }
                    })
                }
                load();
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storegoods/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeGoods=r.storeGoods;
                            vue.$data.edit=true;
						vue.loadCategoryList();
						openForm("修改信息");
						}
				    });
					if(vue.storeGoods==null){alert("网络错误")}
				}
				
			},
            loadCategoryList:function(){
                $.get(transUrl("/storecategory/queryAll"), function(r) {
                    if (check(r)) {
                        if(vue.storeGoods.id!=undefined){
                            var result=[];
                            $.each(r.data,function (index,item) {
                                var obj=item;
                                if(item.id==vue.storeGoods.id){
                                    obj.selected="selected";
                                }
                                result.push(obj);
                            })
                            console.log(result)
                            vue.categoryList=result;
                        }else{
                            vue.categoryList=r.data;
                        }
                    }
                });
            },
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storegoods/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeGoods= r.storeGoods;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storegoods/delete");
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
				var url = vue.storeGoods.id == null ? transUrl("/storegoods/save") : transUrl("/storegoods/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeGoods),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeGoods={};
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
					fileName : 'storeGoods'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storegoods/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				categoryId : {
					required : true
				},
				goodsSn : {
					required : true
				},
				title : {
					required : true
				},
				goodsRemark : {
					required : true
				},
				picUrl : {
					required : true
				},
				number : {
					required : true
				},
				remark : {
					required : true
				},
				price : {
					required : true
				},
				marketPrice : {
					required : true
				},
				goodsDesc : {
					required : true
				},
			},
			messages : {
				categoryId : {
					required : "请填写分类ID"
				},
				goodsSn : {
					required : "请填写商品编号"
				},
				title : {
					required : "请填写标题"
				},
				goodsRemark : {
					required : "请填写商品描述"
				},
				picUrl : {
					required : "请填写商品主图"
				},
				number : {
					required : "请填写销售量"
				},
				remark : {
					required : "请填写特点"
				},
				price : {
					required : "请填写价格"
				},
				marketPrice : {
					required : "请填写市场价格"
				},
				goodsDesc : {
					required : "请填写"
				},
			},
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
		}}, 
						{
			title : '商品编号',
			field : 'goodsSn',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '标题',
			field : 'title',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},	{
			title : '商品主图',
			field : 'picUrl',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'10%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '库存',
			field : 'number',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},	{
			title : '价格',
			field : 'price',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '市场价格',
			field : 'marketPrice',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '添加时间',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '修改时间',
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
