<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生认证审核列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>医生认证审核列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">医生认证审核列表</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox float-e-margins">
                        <form class="form-horizontal" id="queryForm" method="GET" action="${ctx}/back/doctor/authentication/list">
                       		<div class="col-lg-12">
                       			<label class="col-lg-1 control-label">开始时间：</label>
	                          	<div class="form-group col-lg-2">
	                                <div class="input-group">
	                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                            </div>
	                          	<label class="col-lg-1 control-label">结束时间：</label>
	                          	<div class="form-group col-lg-2">
	                                <div class="input-group">
	                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                            </div>
	                          	<div class="form-group col-lg-2" id="endDate">
	                                <button class="btn btn-success col-md-offset-3" type="submit">查询</button>
	                            </div>
                       		</div>
                        </form>
                </div>
				</div>
           </div>
           <div class="col-lg-12">
           		 <div class="panel-body">
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th width="5%">ID</th>
							<th width="5%">医生姓名</th>
							<th width="20%">认证图</th>
							<th width="8%">操作时间</th>
							<th width="5%">审核状态</th>
							<th width="5%">验证</th>
							<th width="5%">操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}">
							<tr>
								<td>${item.id}</td>
								<td>${item.name}</td>
								<td>
									<img width="150" height="90" src="${item.crtWithPhotoUrl }">
									<img width="150" height="90" src="${item.crtWithNameUrl }">
								</td>
								<td>${item.crtOperTime}</td>
								<td id="authStatus${item.id}">
									<c:if test="${item.verifyed==0}">未通过</c:if>
									<c:if test="${item.verifyed==1}">已通过</c:if>
									<c:if test="${item.verifyed==2}">审核未通过</c:if>
									<c:if test="${item.verifyed==-1}">已删除</c:if>
								</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-success dropdown-toggle" id="passBtn${item.id}" onclick="updateAuthStatus(this,'${item.id}',1)">通过</button> 
									<button data-toggle="dropdown" class="btn btn-success dropdown-toggle" id="unPassBtn${item.id}" onclick="updateAuthStatus(this,'${item.id}',2)">未通过</button> 
								</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-danger dropdown-toggle" onclick="updateAuthStatus(this,'${item.id}',-1)">删除</button> 
								</td>
							</tr>
						</c:forEach>
					</table>
					<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/article/doctor/list/1" name="url" />
					</jsp:include>
				</div>
           </div>
       </div>
    </div>
</div>

</body>
<script type="text/javascript">

function detail(id){
	window.location.href = "${ctx}/back/doctor/diaexp/detail/"+id;
}
function updateAuthStatus(obj,id,flag){
	$.ajax({
 		type : "post",
		url : "${ctx}/back/doctor/updateAuthStatus/"+id+"/"+flag,
		async : false,
		dataType : "json",
		success : function(data){
			if(data.status == "suc"){
				if(flag==1){
					$("#authStatus"+id).html("已通过");
				}
				if(flag==2){
					$("#authStatus"+id).html("审核未通过");
				}
				if(flag==-1){
					$("#authStatus"+id).html("已删除");
				}
			}
		}
 	});
}
$(function(){
	$('#startDate .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: true,
        forceParse: true,
        calendarWeeks: true,
        autoclose: true
    });
	
	$('#endDate .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: true,
        forceParse: true,
        calendarWeeks: true,
        autoclose: true
    });
});
</script>
</html>