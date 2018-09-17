		var vue = new Vue({
		el : '#content',
		data : {
			storeInwarehouse : {},
			supplierList:[],
			goodsList:[],
            warehouseList:[],
			edit:false
		},
		methods : {
			addForm : function() {
				vue.storeInwarehouse = {};
				vue.initScan();
                vue.loadsupplierList();
                vue.loadwarehouseList();
                var html="<span onclick='openqr(\"/store/scan/3\")'>使用终端扫描(二维码)</span>";
                $("#goodsId").parent().next().find(".scan-result").html(html);
                $("#title").val("");
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storeinwarehouse/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeInwarehouse=r.storeInwarehouse;
                            vue.loadsupplierList();
                            vue.loadwarehouseList();
                            vue.loadgoods();
						openForm("修改信息");
						}
				    });
					if(vue.storeInwarehouse==null){alert("网络错误")}
				}
				
			},
			loadgoods:function(){
                $.get(transUrl("/storegoods/info/")+vue.storeInwarehouse.goodsId,function(data){
                        $("#goodsId").val(data.storeGoods.id);
                        vue.$data.storeInwarehouse.goodsId=data.storeGoods.id;
                        $("#title").val(data.storeGoods.title);
                        vue.$data.edit=true;
                        timer=''
                })
			},
            initScan:function(){
                var load=function() {
                    $.ajax({
                        type: "POST",
                        url:transUrl( '/storecart/query/3'),
                        data: {},
                        contentType: "application/x-www-form-urlencoded",
                        success: function (r) {
                            if (r.data != undefined) {
                                $.get(transUrl("/storegoods/goodssn?sn=")+r.data.goodsSn,function(data){
                                	if(data.storeGoods!=undefined){
                                        $("#goodsId").val(data.storeGoods.id);
                                        vue.$data.storeInwarehouse.goodsId=data.storeGoods.id;
                                        $("#title").val(data.storeGoods.title);
                                        vue.$data.edit=true;
                                        $("#goodsId").parent().next().find(".scan-result").html("扫描成功");
                                        timer=''
                                	}else{
                                		var html="<a class='tip' onclick='newtab(\"/shop/storegoods.html\",\"商品管理\")' >点此添加商品</a>";
                                        $("#goodsId").parent().next().find(".scan-result").html(html);
                                        alert("当前扫描的商品未录入商品库，请先录入商品信息再入库");
									}
								})
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
            loadgoodsList:function(){
                $.get(transUrl("/storegoods/queryAll"), function(r) {
                    if (check(r)) {
                        if(vue.storeInwarehouse.goodsId!=undefined){
                            var result=[];
                            $.each(r.data,function (index,item) {
                                var obj=item;
                                if(item.goodsId==vue.storeInwarehouse.goodsId){
                                    obj.selected="selected";
                                }
                                result.push(obj);
                            })
                            console.log(result)
                            vue.goodsList=result;
                        }else{
                            vue.goodsList=r.data;
                        }
                    }
                });
            },
            loadsupplierList:function(){
                $.get(transUrl("/storesupplier/queryAll"), function(r) {
                    if (check(r)) {
                        if(vue.storeInwarehouse.supplierId!=undefined){
                            var result=[];
                            $.each(r.data,function (index,item) {
                                var obj=item;
                                if(item.supplierId==vue.storeInwarehouse.supplierId){
                                    obj.selected="selected";
                                }
                                result.push(obj);
                            })
                            console.log(result)
                            vue.supplierList=result;
                        }else{
                            vue.supplierList=r.data;
                        }
                    }
                });
            },
            loadwarehouseList:function(){
                $.get(transUrl("/storewarehouse/queryAll"), function(r) {
                    if (check(r)) {
                        if(vue.storeInwarehouse.warehouseId!=undefined){
                            var result=[];
                            $.each(r.data,function (index,item) {
                                var obj=item;
                                if(item.warehouseId==vue.storeInwarehouse.warehouseId){
                                    obj.selected="selected";
                                }
                                result.push(obj);
                            })
                            console.log(result)
                            vue.warehouseList=result;
                        }else{
                            vue.warehouseList=r.data;
                        }
                    }
                });
            },
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storeinwarehouse/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeInwarehouse= r.storeInwarehouse;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storeinwarehouse/delete");
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
				var url = vue.storeInwarehouse.id == null ? transUrl("/storeinwarehouse/save") : transUrl("/storeinwarehouse/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeInwarehouse),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeInwarehouse={};
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
					fileName : 'storeInwarehouse'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storeinwarehouse/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				goodsId : {
					required : true
				},
				warehouseId : {
					required : true
				},
				number : {
					required : true
				},
				price : {
					required : true
				},
				supplierId : {
					required : true
				},
			},
			messages : {
				goodsId : {
					required : "请填写商品id"
				},
				warehouseId : {
					required : "请填写所在仓库"
				},
				number : {
					required : "请填写数量"
				},
				price : {
					required : "请填写进货价格"
				},
				supplierId : {
					required : "请填写供应商"
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
			title : '商品id',
			field : 'goodsId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '所在仓库',
			field : 'warehouseId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '入库时间',
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
			title : '数量',
			field : 'number',
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
		},			{
			title : '进货价格',
			field : 'price',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '供应商',
			field : 'supplierId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		}, ];
	};
