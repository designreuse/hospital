<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>心愿详情</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>心愿详情</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}">首页</a></li>
                 <li class="active">心愿详情</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight" style="padding-left: 50px;font-size: 16px;">
    	<div class="row">
    		<div class="form-group col-lg-3">患者姓名：${wish.patientName }</div>
    	</div>
    	<div class="row">
    		<div class="form-group col-lg-3">手机号码：${wish.mobile }</div>
    	</div>
    	<div class="row">
    		<div class="form-group col-lg-3">发表时间：${wish.postTime }</div>
    	</div>
    	<div class="row">
    		<div class="form-group col-lg-3">心愿状态：
    			<c:if test="${wish.isComeTrue ==0}">未实现</c:if>
    			<c:if test="${wish.isComeTrue ==1}">已实现</c:if>
    		</div>
    	</div>
    	<div class="row">
    		<div class="form-group col-lg-3">心愿内容：${wish.content } </div>
    	</div>
    	<div class="row">
    		<div class="form-group col-lg-3">回复内容：${wish.remark } </div>
    	</div>
    </div>
</div>
</body>
<script type="text/javascript">

</script>
</html>