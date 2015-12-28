<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生管理</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>接口分类列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">接口分类列表</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
          	<button type="button" class="btn btn-w-m btn-success" id="addBtn">新增</button>
          </div>
          <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>名称</th>
                                <th>接口类型</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${list }" var="item">
                            		<tr>
                            			<td>${item.id }</td>
                            			<td>${item.name }</td>
                            			<td>	
                            				<c:if test="${item.type==1 }">医生</c:if>
                            				<c:if test="${item.type==2 }">患者</c:if>
                            			</td>
                            			<td></td>
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
	$(function(){
		$("#addBtn").on("click",function(){
			window.location.href = "${ctx}/back/interface/category/view/add";
		});
		
		
	});
</script>
</html>