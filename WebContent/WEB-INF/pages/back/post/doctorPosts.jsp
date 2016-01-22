<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生帖子列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>医生帖子列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">医生帖子列表</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        <div class="ibox float-e-margins">
                        <form class="form-horizontal" id="queryForm" method="GET" action="${ctx}/back/post/doctor/list/view">
                       		<div class="col-lg-12">
                       			<label class="col-lg-1 control-label">用户名：</label>
	                          	<div class="form-group col-lg-2" >
	                                <div class="input-group">
	                                    <input type="text" class="form-control" name="username" value="">
	                                </div>
	                            </div>
                       			<label class="col-lg-1 control-label">发表时间：</label>
	                          	<div class="form-group col-lg-2">
	                                <div class="input-group">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="postTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                            </div>
	                            <div class="form-group col-lg-2">
                          			<label class="col-lg-1 control-label"></label>
                          			<button class="btn btn-success" type="submit">查询</button>
                          		</div>
                       		</div>
                        </form>
                </div>
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
            		<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/post/doctor/list/view" name="url" />
					</jsp:include>
                    <div class="ibox-content">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>用户名</th>
                                <th>图片</th>
                                <th class="text-left">内容</th>
                                <th>发表时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="item" items="${page.datas}">
                            		<tr>
                            			<td>${item.id }</td>
                            			<td>${item.username }</td>
                            			<td>
                            				<c:forEach items="${item.imageList }" var="images">
                            					<img width="120" height="100" alt="" src="${images.imageUrl }">
                            				</c:forEach>
                            			</td>
                            			<td  class="text-left">${item.content }</td>
                            			<td>${item.creTime }</td>
                            			<td>
	                            			<button type="button" onclick="detail('${item.id }')" class="btn btn-w-m btn-success">详情</button>
	                            			<button type="button" onclick="del('${item.id }')" class="btn btn-w-m btn-danger">删除</button>
                            			</td>
                            		</tr>
                            	</c:forEach>
                            </tbody>
                        </table>

                </div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
function detail(id){
	window.location.href = "${ctx}/back/post/doctor/detail?id="+id;
}
function del(id){
	window.location.href = "${ctx}/back/post/doctor/del?id="+id;
}
$(function(){
	$('#postTime .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: true,
        forceParse: true,
        calendarWeeks: true,
        autoclose: true
    });
});
</script>
</html>