<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>文章详情(患者)</title>
</head>
<style type="text/css">
	body{font-size: 16px;}
</style>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>>文章详情(患者)</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight ">
    <div class="ibox float-e-margins">
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">上传类别：</div>
	         <div class="col-md-2">
	         	<c:if test="${article.illType == 1 }">首页轮播图</c:if>
			 	<c:if test="${article.illType == 2 }">首页H5链接</c:if>
			 	<c:if test="${article.illType == 3 }">三高</c:if>
			 	<c:if test="${article.illType == 4 }">冠心病</c:if>
			 	<c:if test="${article.illType == 5 }">心梗</c:if>
			 	<c:if test="${article.illType == 6 }">心衰</c:if>
	         </div>
	         <c:if test="${article.illType != 1 && article.illType != 2 }">
               	<div class="col-md-1 text-right">文章分类：</div>
               	<div class="col-md-3">
	                 <c:if test="${article.category == 5 }">心漫画</c:if>
	                 <c:if test="${article.category == 6 }">心视频</c:if>
	                 <c:if test="${article.category == 7 }">心知识</c:if>
		        </div>
             </c:if>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">发布时间：</div>
	         <div class="col-md-3">${article.postTime }</div>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">封面图：</div>
	         <div class="col-md-3"><img width="100" height="100" src="${article.coverImageUrl }"></div>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">文章标题：</div>
	         <div class="col-md-3">${article.title }</div>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">链接地址：</div>
	         <div class="col-md-3">${article.linkUrl }</div>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">链接指向：</div>
	         <div class="col-md-3">
	         	<c:if test="${article.linkType == 1 }">跳转至文章详情</c:if>
	            <c:if test="${article.linkType == 2 }">链接地址</c:if>
	         </div>
	     </div>
	    <div class="row show-grid">
	         <div class="col-md-1 text-right">正文：</div>
	         <div class="col-md-11">
	         	${article.content}
	         </div>
	     </div>
	     </div>
    </div>
</div>
</body>
</html>