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
             <h2>添加菜单</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">添加菜单</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<button type="button" class="btn btn-w-m btn-success" id="flush_btn">刷新</button>
						<div class="ibox-tools">
							<a href="${ctx}/u/topstars" style="margin-right: 20px">查看明星推荐</a>
							<input type="checkbox" name="lock" id="lock" style="margin-top: -2px;vertical-align: middle;"/> <label for="lock" style="margin-right: 20px;">是否锁定</label>
							<select id="selectItem">
								<option value="mobile">手机号</option>
								<option value="nickname">昵称</option>
								<option value="id">ID</option>
							</select>         						
							<input type="text" style="width:200px;height:32px"id="search_content">
                       	 	<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="search_btn">搜索</button> 
						</div>
					</div>
					<div class="jqGrid_wrapper">
						<table id="table_doctorList" class="table-striped"></table>
						<div id="pager_doctorList"></div>
					</div>
				</div>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#table_doctorList').jqGrid({
		url : "${ctx}/back/doctor/list",
		datatype : "json",
		mtype : "POST",
		height: 600,
		autowidth: true,
		shrinkToFit: true,
		altRows:true,
        altclass:'jqGrid-row-color',
		rowNum : 10,
		rowList: [10, 15, 20],
		colNames: ['#编号', '用户名','姓名', '医院名称', '科室', '所在地', '技术职称', '医生积分', '医生验证', '注册时间','操作'],
		colModel: [
			{name: 'id', index: 'id', width: 80, align:"center",sorttype: "int"},
			{name: 'username', index: 'username', width: 120, align:"center",sortable: false},
			{name: 'name', index: 'name', width: 120, align:"center",sortable: false},
			{name: 'hospital', index: 'hospital', width: 120, align:"center",sortable: false},
			{name: 'department', index: 'department', width: 120, align:"center",sortable: false},
			{name: 'address', index: 'address', width: 120, align:"center",sortable: false},
			{name: 'technicalTitle', index: 'technicalTitle', width: 120, align:"center",sortable: false},
			{name: 'score', index: 'score', width: 120, align:"center",sortable: false},
			{name: 'verifyed', index: 'verifyed', width: 120, align:"center",sortable: false},
			{name: 'registerTime', index: 'registerTime', width: 120, align:"center",sortable: false},
			{name: 'operate', index: 'operate', width: 120, align:"center",sortable: false},
		],
		pager: "#pager_doctorList",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	var ids = jQuery('#table_doctorList').jqGrid('getDataIDs');
        	for (var i = 0; i<ids.length; i++){
        		var id = ids[i];
        		var rowdata=$('#table_userList').getRowData(id);
        		var detailBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='_edit(\""+id+"\")'>详情</a>";
        		var exchangeScoreBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='_edit(\""+id+"\")'>兑换积分</a>";
        		var exchangeHistoryBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='_edit(\""+id+"\")'>兑换记录</a>";
        		jQuery('#table_doctorList').jqGrid('setRowData', ids[i], {operate: detailBtn +"  " +exchangeScoreBtn+" "+ exchangeHistoryBtn});
        	}
        	$("#table_doctorList").setGridWidth($(".jqGrid_wrapper").width());
        }
	});
	$('#flush_btn').click(function(){
		var page = $('#table_doctorList').jqGrid('getGridParam', 'page');
		var rows = $('#table_doctorList').jqGrid('getGridParam', 'rowNum');
		$('#table_doctorList').jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				page : page,
				rows : rows,
			},
			page : page ,
		}).trigger("reloadGrid"); 
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
</script>
</html>