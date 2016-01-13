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
<div id="page-wrapper" style="background-color: #ffffff">
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
            <div class="col-lg-12 c">
            <div style="font-size: 25px;font-weight: bold;}">${d.username }  </div>
            <div style="font-size: 17px;">${d.postTime }    </div>
            
            <div style="margin-top: 10px;border-top: 1px solid #dddddd;padding-top: 10px">${d.content }</div>
            <div>
            	<c:forEach items="${d.imageList }" var="image">
            		<img alt="" src="${image.imageUrl }">
            	</c:forEach>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
</script>
</html>