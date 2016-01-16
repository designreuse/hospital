<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>医生列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">医生列表</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
                         <form role="form" class="form-inline" method="GET" action="${ctx }/back/doctor/list">
                         	 <div class="row show-grid">
                         	 	<div class="form-group col-lg-3">
	                         	 	<div class="form-group" style="margin-left: 39px;">
		                                 <label for="">用户名：</label>
		                                 <input type="text" name="username" class="form-control">
		                             </div>
                         	 	</div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <label for="">姓名：</label>
		                                 <input type="text" name="name" class="form-control">
		                             </div>
		                         </div>
		                         <div class="form-group col-lg-3">
		                             <div class="form-group">
		                                 <label for="">显示人数：</label>
		                                 <select class="form-control" name="exportRowCount" style="width: 130px;">
			                                 <option value="10">10</option>
			                                 <option value="20">20</option>
			                                 <option value="30">30</option>
			                                 <option value="50">50</option>
			                             </select>
		                             </div>
	                             </div>
                             </div>
                             <div class="row show-grid">
                             	<div class="form-group col-lg-3">
	                                 <label for="">注册起始时间：</label>
	                                 <div class="input-group">
	                                 <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                             </div>
	                             <div class="form-group col-lg-3">
	                                 <label for="">注册终止时间：</label>
	                                 <div class="input-group">
	                                 <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                             </div>
                             	<div class="form-group col-lg-3">
	                                 <label for="">医生验证：</label>
	                                 <div class="input-group">
	                                    <select class="form-control" name="verifyed" style="width: 130px;">
			                                <option value="0">未通过</option>
			                                 <option value="1">通过</option>
			                             </select>
	                                </div>
	                             </div>
                             </div>
                             <div class="row show-grid" style="margin-left: 26px;">
                             	<div class="form-group col-lg-5">
	                                 <label for="">可用积分：</label>
	                                 <input type="text" id="" name="startScore" class="form-control"> --
	                                 <input type="text" id="" name="endScore" class="form-control">
	                             </div>
	                             <div class="form-group col-lg-3">	
									<button type="submit" class="btn btn-primary">查询</button>	                             
									<button type="button" class="btn btn-primary" onclick="selectExchage()">选择兑换</button>	                             
									<button type="button" class="btn btn-primary" onclick="onekeyExchange()">一键兑换</button>	                             
									<button type="button" class="btn btn-primary" onclick="exportDoctor()">导出</button>	
								</div>
                             </div>
                         </form>
                     </div>
				</div>
           </div>
           <div class="col-lg-12">
           		 <div class="panel-body">
           		 <form action="${ctx }/back/doctor/selectExchage" method="post" id="exchageform">
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th width="2%">全选<input type="checkbox" name="all"  /> </th>
							<th width="5%">用户名</th>
							<th width="5%">姓名</th>
							<th width="5%">医院名称</th>
							<th width="5%">科室</th>
							<th width="10%">所在地</th>
							<th width="5%">技术职称</th>
							<th width="5%">医生积分</th>
							<th width="5%">医生验证</th>
							<th width="10%">注册时间</th>
							<th width="13%">操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}" varStatus="st">
							<tr>
								<td><input type="checkbox" name="single" value="${item.id}"/></td>
								<td>${item.username}</td>
								<td id="name${item.id}">${item.name}</td>
								<td>${item.hospital}</td>
								<td>${item.department}</td>
								<td>${item.address}</td>
								<td>${item.technicalTitle}</td>
								<td id="score${item.id}">${item.score}</td>
								<td>
									<c:if test="${item.verifyed == 0}">未通过</c:if>
									<c:if test="${item.verifyed == 1}">通过</c:if>
								</td>
								<td>${item.registerTime}</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="detail('${item.id}')">详情</button> 
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="preExchange('${item.id}')">兑换积分</button> 
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="exchange_record('${item.id}')">兑换记录</button> 
								</td>
							</tr>
						</c:forEach>
					</table>
					</form>
					<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/article/doctor/list/1" name="url" />
					</jsp:include>
				</div>
           </div>
       </div>
    </div>
</div>

<div id="tip_container" class="confirm_container theme-popover">
	<div>
		<div id="centerShow"></div>
		<div style="margin-bottom: 20px;overflow: hidden;">
			<button type="button" style="width: 100px;height: 40px;float:right;margin-right:20px;margin-top:10px;" class="btn btn-default" onclick="cancel()">取消</button>
			<button type="button" style="width: 100px;height: 40px;float:right;margin-right:20px;margin-top:10px;" class="btn btn-success" onclick="confirm()">确定</button>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
$(function(){
	$("input[type='checkbox'][name='all']").on("click",function(){    
	    if(this.checked){    
	    	$("input[type='checkbox'][name='single']").prop("checked",true); 
	    }else{    
	    	$("input[type='checkbox'][name='single']").removeAttr("checked"); 
	    } 
	});
});
function detail(id){
	window.location.href = "${ctx}/back/doctor/detail/"+id;
}
function exportDoctor(){
	window.location.href = "${ctx}/back/doctor/export";
}
function preExchange(id){
	var name = $("#name"+id).text();
	var showCenter = "<div style='text-align: center;padding-top:20px;width: 300px;height:80px;'>是否要为"+name+"医生兑换积分？</div>";
	confirmOpt(showCenter,function(){
		exchange(id);
	});
}
function exchange(id){
	$.ajax({
 		type : "post",
		url : "${ctx}/back/doctor/exchange",
		async : false,
		dataType : "json",
		data:{userId : id},
		success : function(data){
			if(data.status == "suc"){
				$("#score"+id).text(0);
			}
		}
 	});
}
function exchange_record(id){
	window.location.href = "${ctx}/back/doctor/exchangeHistoryView?userId="+id;
}
function selectExchage(){
	if($("input[type='checkbox'][name='single']:checked").length==0){
		alert("请选择医生");
	}else{
		$("#exchageform").submit();
	}
}
function onekeyExchange(){
	window.location.href = "${ctx}/back/doctor/onekeyExchange";
}
</script>
</html>