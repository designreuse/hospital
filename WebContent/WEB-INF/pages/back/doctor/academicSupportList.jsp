<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>学术支持列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>学术支持</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">学术支持</li>
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
                         <form role="form" class="form-inline" method="GET" action="${ctx }/back/doctor/academicSupport/list">
                       		<label>活动标题：</label>
                         	<div class="form-group" id="startDate">
                               <div class="input-group">
                                   <input type="text" name="title" class="form-control">
                               </div>
                            </div>
                     		<label>会议状态：</label>
                       	    <div class="form-group">
	                             <div class="input-group">
	                                <select name="type" class="form-control">
	                                	<option value="-1">全部</option>
	                                	<option value="0">近期会议</option>
	                                	<option value="1">往期会议</option>
	                                </select>
	                             </div>
                            </div>
                            
                            <label>发表时间：</label>
                            <div class="form-group" id="creTime">
                                <div class="input-group date">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input type="text" class="form-control" name="creTime" value="">
                                </div>
                            </div>
                            <div class="input-group">
                                <button type="submit" style="margin-left:30px;" class="btn btn-primary">查询</button>
                            	<button type="button" style="margin-left:30px;" id="addActivity" class="btn btn-success">新建学术活动</button>
                            </div>
                            
                         </form>
                     </div>
				</div>
           </div>
           <div class="col-lg-12">
           		 <div class="panel-body">
           		 	<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx }/back/doctor/academicSupport/list" name="url" />
					</jsp:include>
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th>ID</th>
							<th>会议标题</th>
							<th>会议内容</th>
							<th>会议状态</th>
							<th>参会积分</th>
							<th>参会人数</th>
							<th>发表时间</th> 	
							<th>操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}">
							<tr>
								<td>${item.id}</td>
								<td>${item.title}</td>
								<td>${item.content}</td>
								<td>
									<c:if test="${item.type == 0}">近期会议</c:if>
									<c:if test="${item.type == 1}">往期会议</c:if>
								</td>
								<td>${item.score}</td>
								<td>${item.totalTakePart}</td>
								<td>${item.creTime}</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="detail('${item.id}')">详情</button> 
									<button data-toggle="dropdown" class="btn btn-danger dropdown-toggle" onclick="del('${item.id}')">删除</button> 
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
function detail(id){
	window.location.href = "${ctx}/back/doctor/academicSupport/detail?id="+id;
}
function del(id){
	window.location.href = "${ctx}/back/doctor/academicSupport/del?id="+id;
}


function exchange(){
	
}
$(function(){
	$('#creTime .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: true,
        forceParse: true,
        calendarWeeks: true,
        autoclose: true
    });
	
	$("#addActivity").on("click",function(){
		window.location.href = "${ctx }/back/doctor/academicSupport/add/view";
	});
});
</script>
</html>