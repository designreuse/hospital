<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>病例精析详情</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>病例精析详情</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight white-bg">
       <div class="row " style="font-size: 15px;">
       		<div class="row show-grid">
                 <div class="col-md-1 text-right">医生名称：</div>
                 <div class="col-md-11 text-left">${detail.doctorName }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">专家类别：</div>
                 <div class="col-md-11 text-left">
                 	<c:if test="${detail.eliteType == 1}">金牌专家</c:if>
		            <c:if test="${detail.eliteType == 2}">普通专家</c:if>
                 </div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">医院：</div>
                 <div class="col-md-11 text-left">${detail.hospital }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">标题：</div>
                 <div class="col-md-11 text-left">${detail.title }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">发布时间：</div>
                 <div class="col-md-11 text-left">${detail.postTime }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">病例图：</div>
                 <div class="col-md-11 text-left"><img alt="" src="${detail.illCaseImage }"> </div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">会议内容：</div>
                 <div class="col-md-11 text-left">${detail.analysis }</div>
             </div>
       </div>
    </div>
</div>
</body>
</html>