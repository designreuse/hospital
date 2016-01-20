<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>学术会议详情</title>
<style>
	.show-grid [class^="col-"] {
	    border: none;
	    background-color: #F3F3F4 !important;
    }
</style>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>学术会议详情</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row" style="font-size: 15px;">
       		<div class="row show-grid">
                 <div class="col-md-1 text-right">会议标题：</div>
                 <div class="col-md-11 text-left">${academic.title }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">发表时间：</div>
                 <div class="col-md-11 text-left">${academic.creTime }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">参会积分：</div>
                 <div class="col-md-11 text-left">${academic.score }</div>
             </div>
       		<div class="row show-grid">
                 <div class="col-md-1  text-right">会议内容：</div>
                 <div class="col-md-11 text-left">${academic.content }</div>
             </div>
       		<div class="row show-grid">
       		<div class="col-md-1  text-right"></div>
                 <div class="col-md-11 text-left"><button style="width: 130px" class="btn btn-success" type="button" onclick="edit('${academic.id }')">修改</button></div>
       		
             </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
function edit(id){
	window.location.href = "${ctx}/back/doctor/academicSupport/update/view?id="+id;
}
</script>
</html>