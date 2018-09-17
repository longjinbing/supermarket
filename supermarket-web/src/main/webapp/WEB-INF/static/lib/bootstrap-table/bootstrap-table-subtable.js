/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author fengshuonan
 */
(function () {
    var SUBTable = function (bstableId, url, columns) {
        this.btInstance = null;                 //jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = url;
        this.method = "get";
        this.paginationType = "server";         //默认分页方式是服务器分页,可选项"client"
        this.toolbarId = "toolbar";
        this.columns = columns;
        this.height = '';                      //默认表格高度665
        this.data = {};
        this.queryParams = {}; // 向后台传递的自定义参数
        this.detailView=false;
        this.detailFun=function(index,row, $detail){};
    };

    SUBTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            this.btInstance =
                $('#'+tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    url: this.url,              //请求地址
                    method: this.method,        //ajax方式,post还是get
                    ajaxOptions: {              //ajax请求的附带参数
                        data: this.data
                    },
                    toolbar: "",//顶部工具条
                    striped: true,              //是否显示行间隔色
                    cache: false,               //是否使用缓存,默认为true
                    pagination: true,           //是否显示分页（*）
                    sortable: true,             //是否启用排序
                    sortOrder: "desc",          //排序方式
                    pageNumber: 1,                  //初始化加载第一页，默认第一页
                    pageSize: 10,               //每页的记录行数（*）
                    pageList: [10, 20, 50,100],    //可供选择的每页的行数（*）
                    queryParamsType: 'limit',   //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParams: function (param) {
                        return $.extend(this.queryParams, param);
                    }, // 向后台传递的自定义参数
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: false,              //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    strictSearch: false,         //设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: false,          //是否显示所有的列
                    showRefresh: false,          //是否显示刷新按钮
                    trimOnSearch:true,
                    showFullscreen:true,
                    idField:"id",
                    uniqueId:"id",
                    buttonsAlign:'right',
                    toolbarAlign:'left',
                    showHeader:true,
                    minimumCountColumns: 2,     //最少允许的列数
                    clickToSelect: true,        //是否启用点击选中行
                    searchOnEnterKey: true,     //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,      //列数组
                    pagination: false,           //是否显示分页条
                    height: this.height,
                    responseHandler:function(res){
                    	return res.page;
                    },
                    icons: {
                        refresh: 'glyphicon-repeat',
                        toggle: 'glyphicon-list-alt',
                        columns: 'glyphicon-list',
                        detailOpen: 'glyphicon-plus icon-plus',
                        detailClose: 'glyphicon-minus icon-minus'
                    },
                    iconSize: 'outline',
                    detailView:this.detailView,
                    onExpandRow:this.detailFun
                });
            return this;
        },
        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {
            this.queryParams = param;
        },
        
        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },
        setDetailView:function(bool){
        	this.detailView=bool;
        	return this;
        },
        setDetailFun:function(obj){
        	this.detailFun=obj;
        	return this;
        },
        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query params specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        }
    };

    window.SUBTable = SUBTable;

}());
var autoId=function (tableId,index) {
    //获取每页显示的数量
    var pageSize=$('#'+tableId).bootstrapTable('getOptions').pageSize;  
    //获取当前是第几页  
    var pageNumber=$('#'+tableId).bootstrapTable('getOptions').pageNumber;
    //返回序号，注意index是从0开始的，所以要加上1
    return pageSize * (pageNumber - 1) + index + 1;
};