<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>患者列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">患者列表</li>
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
					<%-- <div class="ibox-title">
						<button type="button" class="btn btn-w-m btn-success" id="flush_btn">刷新</button>
						<div class="ibox-tools">
							<a href="${ctx}/u/topstars" style="margin-right: 20px">查看明星推荐</a>
							<input type="checkbox" name="lock" id="lock" style="margin-top: -2px;vertical-align: middle;"/> <label for="lock" style="margin-right: 20px;">是否锁定</label>
							<select id="selectItem">
								<option value="mobile">手机号</option>
								<option value="nickname">昵称</option>
								<option value="id">ID</option>
							</select>         						
							<input type="text" style="width:200px;height:32px"id="search_content">
                       	 	<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="search_btn">搜索</button> 
						</div>
					</div> --%>
				</div>
           </div>
           <div class="col-lg-12">
           		 <div class="panel-body">
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>姓名</th>
							<!-- <th>医院名称</th>
							<th>科室</th>
							<th>所在地</th>
							<th>技术职称</th>
							<th>医生积分</th>
							<th>医生验证</th>
							<th>注册时间</th>
							<th>操作</th> -->
						</tr>
						<c:forEach var="item" items="${page.datas}" varStatus="st">
							<tr>
								<td>${st.index+1}</td>
								<td>${item.username}</td>
								<td>${item.name}</td>
								<%-- <td>${item.hospital}</td>
								<td>${item.department}</td>
								<td>${item.address}</td>
								<td>${item.technicalTitle}</td>
								<td>${item.score}</td> --%>
								<%-- <td>
									<c:if test="${item.verifyed == 0}">未通过</c:if>
									<c:if test="${item.verifyed == 1}">通过</c:if>
								</td>
								<td>${item.registerTime}</td> --%>
								<td>
									<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" id="search_btn">查看详情</button> 
								</td>
							</tr>
						</c:forEach>
					</table>
					<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/article/doctor/list/1" name="url" />
					</jsp:include>
				</div>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">

</script>
</html>