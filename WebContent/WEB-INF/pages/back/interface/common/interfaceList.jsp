<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生管理</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>接口列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">接口列表</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href=''">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
       	<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<button type="button" class="btn btn-w-m btn-success" id="addBtn">新增</button>
						<div class="ibox-tools">
							<input type="text" style="width:200px;height:32px"id="search_content">
                       	 	<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="search_btn">搜索</button> 
						</div>
					</div>
					<div class="jqGrid_wrapper">
						<table id="tableList" class="table-striped"></table>
						<div id="pagerList"></div>
					</div>
				</div>
           </div>
       </div>
    </div>
</div>
</body>
<script src="${ctx }/static/js/interface.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tableList').jqGrid({
		url : "${ctx}/back/interface/list",
		datatype : "json",
		mtype : "POST",
		height: 600,
		autowidth: true,
		shrinkToFit: true,
		altRows:true,
        altclass:'jqGrid-row-color',
		rowNum : 10,
		rowList: [10, 15, 20],
		colNames: ['编号', '接口名称','调用方式', '调用地址', '操作'],
		colModel: [
			{name: 'id', index: 'id', width: 50, align:"center",sorttype: "int"},
			{name: 'itemDesc', index: 'itemDesc', width: 200, align:"left",sortable: false},
			{name: 'method', index: 'method', width: 50, align:"center",sortable: false},
			{name: 'name', index: 'name', width: 120, align:"left",sortable: false},
			{name: 'operate', index: 'operate', width: 50, align:"center",sortable: false},
		],
		pager: "#pagerList",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	var ids = jQuery('#tableList').jqGrid('getDataIDs');
        	for (var i = 0; i<ids.length; i++){
        		var id = ids[i];
        		var rowdata=$('#tableList').getRowData(id);
        		var editBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='_edit(\""+id+"\")'>编辑</a>";
        		jQuery('#tableList').jqGrid('setRowData', ids[i], {operate:editBtn});
        	}
        	$("#tableList").setGridWidth($(".jqGrid_wrapper").width());
        }
	});
	$('#addBtn').click(function(){
		window.location.href = "${ctx}/back/interface/view/add";
	});
	
	//搜索操作
	$('#search_btn').click(function(){
		var lock = "";
		if($("#lock").is(':checked')){
			lock = "2";
		}else{
			lock = "1";
		}
		var rows = $("#table_userList").jqGrid('getGridParam', 'rowNum');
		var content = $('#search_content').val();
		var value = document.all.selectItem.value;
		var postData = {};
		var params = {};
		params[value] = content;
		postData.page = 1;
		postData.rows = rows;
		postData.lock = lock;
		postData["params"] = JSON.stringify(params);
		$("#table_userList").jqGrid('setGridParam', {
			datatype : 'json',
			postData : postData,
			page : 1,
		}).trigger("reloadGrid");
	});
});
function _edit(id){
	window.location.href = "${ctx}/back/interface/update/"+id;
}
</script>
</html>