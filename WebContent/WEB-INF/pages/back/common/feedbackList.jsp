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
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox float-e-margins">
                        <form class="form-horizontal" id="queryForm" method="GET" action="${ctx}/back/feedback/list">
                       		<div class="col-lg-12">
                       			<label class="col-lg-1 control-label">开始时间：</label>
	                          	<div class="form-group col-lg-2" id="startDate">
	                                <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" value="">
	                                </div>
	                            </div>
	                          	<label class="col-lg-1 control-label">结束时间：</label>
	                          	<div class="form-group col-lg-2" id="endDate">
	                                <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" value="">
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
							<th width="2%">ID</th>
							<th width="5%">用户名</th>
							<th width="15%">反馈内容</th>
							<th width="8%">手机号</th>
							<th width="8%">邮箱</th>
							<th width="10%">反馈时间</th>
							<th width="5%">操作状态</th>
							<th width="10%">回复内容</th>
							<th width="5%">操作人</th>
							<th width="5%">操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}">
							<tr>
								<td>${item.id}</td>
								<td>${item.username}</td>
								<td>${item.content}</td>
								<td>${item.mobile}</td>
								<td></td>
								<td>${item.feedBackTime}</td>
								<td id="replyStatus${item.id}">
									<c:if test="${item.status==0}">待回复</c:if>
									<c:if test="${item.status==1}">已回复</c:if>
								</td>
								<td id="reply${item.id}">${item.reply}</td>
								<td>手术费</td>
								<td><button  class="btn btn-success" onclick="preReply('${item.id}')">回复</button></td>
							</tr>
						</c:forEach>
					</table>
					<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/feedback/list" name="url" />
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
function preReply(id){
	var showCenter = "<textarea id='replyContent' style='border:none;width: 700px;height:300px' placeholder='请输入回复内容...'></textarea>";
	confirmOpt(showCenter,function(){
		reply(id);
	}); 
}
function reply(id){
	$.ajax({
 		type : "post",
		url : "${ctx}/back/feedback/replay",
		async : false,
		dataType : "json",
		data : {
			id : id,
			content: $("#replyContent").val()
		},
		success : function(data){
			if(data.status == "suc"){
				$("#reply"+id).text($("#replyContent").val());
				$("#replyStatus"+id).text("已回复");
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