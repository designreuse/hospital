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
          <div class="col-lg-9">
          	<table>
          		<tr>
          			<td class="text-right">医生姓名:</td>
          			<td>${dia.doctorName }</td>
          			<td class="text-right">发表状态:</td>
          			<td>
          				<c:if test="${dia.status == 0}">未发表</c:if>
          				<c:if test="${dia.status == 1}">已发表</c:if>
          			</td>
          			<td class="text-right">匿名状态:</td>
          			<td>
          				<c:if test="${dia.isAnonymous == 0}">不匿名</c:if>
          				<c:if test="${dia.isAnonymous == 1}">匿名</c:if>
          			</td>
          		</tr>
          		<tr>
          			<td class="text-right">积分打赏:</td>
          			<td>${dia.score }积分</td>
          			<td class="text-right">发表时间:</td>
          			<td>${dia.creTime }</td>
          			<td class="text-right"></td>
          			<td></td>
          		</tr>
          		<tr>
          			<td class="text-right">患病种类:</td>
          			<td>${dia.illType }</td>
          			<td class="text-right">阅读数:</td>
          			<td>${dia.readCount }</td>
          			<td class="text-right"></td>
          			<td></td>
          		</tr>
          		<tr>
          			<td class="text-right">病症描述:</td>
          			<td colspan="5">${dia.illDesc }</td>
          		</tr>
          		<tr>
          			<td class="text-right">诊后心得:</td>
          			<td colspan="5">${dia.experience }</td>
          		</tr>
          		<tr>
          			<td colspan="6">
          				<c:forEach items="${dia.diagnoseExpImgList }" var="image">
          					<img alt="" src="${image }">
          				</c:forEach>	
          			</td>
          		</tr>
<!--           		<tr> -->
<%--           			<td colspan="6"><button class="btn btn-success pull-right" onclick="edit('${dia.doctorId }')">编辑</button></td> --%>
<!--           		</tr> -->
          	</table>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
// 	function edit(id){
// 		window.location.href = "${ctx}/back/doctor/update/view/"+id+"?diaId=${dia.id }";
// 	}
</script>
</html>