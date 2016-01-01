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
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
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
                             	<div class="form-group col-lg-3"  id="startDate">
	                                 <label for="">注册起始时间：</label>
	                                 <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" value="">
	                                </div>
	                             </div>
	                             <div class="form-group col-lg-3"  id="endDate">
	                                 <label for="">注册终止时间：</label>
	                                 <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" value="">
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
									<button type="button" class="btn btn-primary">选择兑换</button>	                             
									<button type="button" class="btn btn-primary">一键兑换</button>	                             
									<button type="button" class="btn btn-primary">导出</button>	
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
							<th>序号</th>
							<th>用户名</th>
							<th>姓名</th>
							<th>医院名称</th>
							<th>科室</th>
							<th>所在地</th>
							<th>技术职称</th>
							<th>医生积分</th>
							<th>医生验证</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}" varStatus="st">
							<tr>
								<td>${st.index+1}</td>
								<td>${item.username}</td>
								<td>${item.name}</td>
								<td>${item.hospital}</td>
								<td>${item.department}</td>
								<td>${item.address}</td>
								<td>${item.technicalTitle}</td>
								<td>${item.score}</td>
								<td>
									<c:if test="${item.verifyed == 0}">未通过</c:if>
									<c:if test="${item.verifyed == 1}">通过</c:if>
								</td>
								<td>${item.registerTime}</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="detail('${item.id}')">详情</button> 
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="exchage('${item.id}')">兑换积分</button> 
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="exchage_record('${item.id}')">兑换记录</button> 
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
	window.location.href = "${ctx}/back/doctor/detail/"+id;
}
function exchange(){
	
}
function exchage_record(){
	
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