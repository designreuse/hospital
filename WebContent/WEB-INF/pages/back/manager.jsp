<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>管理员列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>管理员列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">管理员列表</li>
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
                    <div class="ibox-title">
                        <button type="button" class="btn btn-w-m btn-success" onclick="addManager()">添加</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table">
                            <thead>
	                            <tr>
	                                <th>编号</th>
	                                <th>用户名</th>
	                                <th>姓名</th>
	                                <th>角色</th>
	                                <th>头像</th>
	                                <th>操作</th>
	                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${list }" var="admin" varStatus="index">
                            		<tr>
		                                <td>${index.index+1 }</td>
		                                <td>${admin.username }</td>
		                                <td>${admin.name }</td>
		                                <td>
		                                	<c:if test="${admin.level ==1}">高级管理员</c:if>
		                                	<c:if test="${admin.level !=1}">普通管理员</c:if>
		                                </td>
		                                <td>
		                                	<img alt="" src="${admin.profileImageUrl }">
		                                </td>
		                                <td>
		                                	<button type="button" class="btn btn-w-m btn-primary" onclick="resetpwd('${admin.id }')">密码重置</button>
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
	function addManager(){
		window.location.href = "${ctx}/back/manager/add/view";
	}
	function resetpwd(id){
		window.location.href = "${ctx}/back/manager/resetpwd/view?id="+id;
	}
</script>
</html>