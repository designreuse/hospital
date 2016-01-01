<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生详情</title>
<style type="text/css">
	table{
		width: 80%;
    	margin: 0 0 0 10px;	
    	font-family: verdana,arial,sans-serif;
		font-size:16px;
		color:#333333;
		border-collapse: collapse;
	}
	table td {
		padding: 8px;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>医生详情</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">医生详情</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
          	<table>
          		<tr>
          			<td class="text-right">注册手机号:</td>
          			<td>${d.mobile }</td>
          			<td class="text-right">姓名:</td>
          			<td>${d.name }</td>
          			<td class="text-right">性别:</td>
          			<td>
          				<c:if test="${d.agender == 1 }">男</c:if>
          				<c:if test="${d.agender == 2 }">女</c:if>
          			</td>
          		</tr>
          		<tr>
          			<td class="text-right">出生日期:</td>
          			<td>${d.birthday }</td>
          			<td class="text-right">医生积分:</td>
          			<td>${d.score }</td>
          			<td class="text-right">医院:</td>
          			<td>${d.hospital }</td>
          		</tr>
          		<tr>
          			<td class="text-right">科室:</td>
          			<td>${d.department }</td>
          			<td class="text-right">所在地:</td>
          			<td>${d.address }</td>
          			<td class="text-right">技术职称:</td>
          			<td>${d.technicalTitle }</td>
          		</tr>
          		<tr>
          			<td class="text-right">教学职称:</td>
          			<td>${d.teachingTitle }</td>
          			<td class="text-right">注册日期:</td>
          			<td>${d.registerTime }</td>
          			<td class="text-right">密码:</td>
          			<td>已加密</td>
          		</tr>
          		<tr>
          			<td class="text-right">医生认证:</td>
          			<td>
          				<c:if test="${d.verifyed == 0 }">未通过</c:if>
          				<c:if test="${d.verifyed == 1 }">通过</c:if>
          			</td>
          			<td colspan="4"></td>
          		</tr>
          		<tr>
          			<td colspan="6"><button class="btn btn-success pull-right" onclick="edit('${d.id }')">编辑</button></td>
          		</tr>
          	</table>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
	function edit(id){
		window.location.href = "${ctx}/back/doctor/update/view/"+id;
	}
</script>
</html>