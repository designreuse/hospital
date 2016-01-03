<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>心愿列表</title>

</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>心愿列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}">首页</a></li>
                 <li class="active">心愿列表</li>
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
                         <form role="form" class="form-inline" method="GET" action="${ctx }/back/patient/wish/list">
                         	 <div class="row show-grid">
                         	 	<div class="form-group col-lg-3">
	                         	 	<div class="form-group" style="margin-left: 39px;">
		                                 <label for="">患者姓名：</label>
		                                 <input type="text" name="patientName" class="form-control">
		                             </div>
                         	 	</div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <label for="">手机号：</label>
		                                 <input type="text" name="mobile" class="form-control">
		                             </div>
		                         </div>
                         	 	<div class="form-group col-lg-3">
		                             <div class="form-group"  style="margin-left: 50px;">
		                                 <label for="">心愿状态：</label>
		                                 <select class="form-group" name="isComeTrue">
		                                 	<option value="-1">请选择</option>
		                                 	<option value="1">已实现</option>
		                                 	<option value="">未实现</option>
		                                 </select>
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
         		 <jsp:include page="/WEB-INF/pages/context/pagination.jsp">
					<jsp:param value="${ctx}/back/article/doctor/list/1" name="url" />
				 </jsp:include>
           		 <div class="panel-body">
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th width="2%">ID</th>
							<th width="5%">患者姓名</th>
							<th width="10%">心愿内容</th>
							<th width="5%">电话</th>
							<th width="5%">心愿状态</th>
							<th width="10%">回复内容</th>
							<th width="7%">发表时间</th>
							<th width="10%">操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}">
							<tr>
								<td>${item.id}</td>
								<td>${item.patientName}</td>
								<td>${item.content}</td>
								<td>${item.mobile}</td>
								<td id="cometrue${item.id}">
									<c:if test="${item.isComeTrue == 0}">未实现</c:if>
									<c:if test="${item.isComeTrue == 1}">已实现</c:if>
								</td>
								<td id="remark${item.id}">${item.remark}</td>
								<td>${item.postTime}</td>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" onclick="detail('${item.id}')">详情</button> 
									<c:if test="${item.remark == null || item.remark == ''}"><button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="replyBtn${item.id}" onclick="reReply('${item.id}')">回复</button> </c:if>
									<c:if test="${item.isComeTrue == 0}"><button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="cometrueBtn${item.id}" onclick="preCometrue('${item.id}')">实现心愿</button></c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
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
function detail(id){
	window.location.href = "${ctx}/back/patient/wish/detail/"+id;
}
function reReply(id){
	var showCenter = "<textarea id='replyContent' style='border:none;width: 700px;height:300px' placeholder='请输入回复内容...'></textarea>";
	confirmOpt(showCenter,function(){
		reply(id);
	});
}
function preCometrue(id){
	var showCenter = "<div style='text-align: center;padding-top:20px;width: 300px;height:80px;'>是否帮该用户实现心愿</div>";
	confirmOpt(showCenter,function(){
		cometrue(id);
	});
}
function cometrue(id){
	$.ajax({
 		type : "post",
		url : "${ctx}/back/doctor/wish/cometrue/"+id,
		async : false,
		dataType : "json",
		success : function(data){
			if(data.status == "suc"){
				$("#cometrue"+id).text("已实现");
				$("#cometrueBtn"+id).hide();
			}
		}
 	});
}

function reply(id){
	$.ajax({
 		type : "post",
		url : "${ctx}/back/doctor/wish/reply",
		async : false,
		dataType : "json",
		data : {
			id : id,
			remark: $("#replyContent").val()
		},
		success : function(data){
			if(data.status == "suc"){
				$("#remark"+id).text($("#replyContent").val());
				$("#replyBtn"+id).hide();
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