<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>诊后心得列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>诊后心得</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">诊后心得</li>
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
                         <form role="form" class="form-inline" method="GET" action="${ctx }/back/doctor/diaexp/list">
                         	 <div class="row show-grid">
                         	 	<div class="form-group col-lg-3">
	                         	 	<div class="form-group" style="margin-left: 39px;">
		                                 <label for="">医生姓名：</label>
		                                 <input type="text" name="username" class="form-control">
		                             </div>
                         	 	</div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <label for="">发表状态：</label>
		                                 <input type="text" name="name" class="form-control">
		                             </div>
		                         </div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <label for="">发表时间：</label>
		                                 <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
		                                 <input type="text" name="name" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		                             </div>
		                         </div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <button type="submit" class="btn btn-primary">查询</button>	          
		                             </div>
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
							<th>医生姓名</th>
							<th>患者类型</th>
							<th>病情描述</th>
							<th>诊后心得</th>
							<th>积分打赏</th>
							<th>发表状态</th>
							<th>匿名状态</th>
							<th>发表时间</th>
							<th>操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}">
							<tr>
								<td>${item.doctorName}</td>
								<td>${item.illType}</td>
								<td>${item.illDesc}</td>
								<td>${item.experience}</td>
								<td>${item.experience}</td>
								<td>${item.status}</td>
								<td>${item.isAnonymous}</td>
								<td>${item.creTime}</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="detail('${item.id}')">详情</button> 
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="del('${item.id}')">兑换记录</button> 
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
function exchange(){
	
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