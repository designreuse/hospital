<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>文章上传（医生）</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>文章上传（医生）</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
			<div class="col-lg-12">
                <div class="ibox float-e-margins">
                        <form class="form-horizontal" id="queryForm" method="GET" action="${ctx}/back/article/list/2">
                       		<div class="col-lg-12">
                       			<label class="col-lg-1 control-label">开始时间：</label>
	                          	<div class="form-group col-lg-2" id="startDate">
	                                <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" value="">
	                                </div>
	                            </div>
	                          	<label class="col-lg-1 control-label">结束时间：</label>
	                          	<div class="form-group col-lg-2" id="endDate">
	                                <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" value="">
	                                </div>
	                            </div>
	                            <label class="col-lg-1 control-label">是否删除：</label>
	                            <div class="form-group col-lg-2">
	                                <select class="form-control m-b" name="delFlag">
		                                 <option value="-1">全部</option>
		                                 <option value="0">未删除</option>
		                                 <option value="1">已删除</option>
		                             </select>
	                            </div>
                       		</div>
                          	<div class="col-lg-12">
                          		<label class="col-lg-1 control-label">文章分类：  </label>
                          		<div class="form-group col-lg-2">
                          			<select class="form-control m-b" name="category">
	                                        <option value="-1">-请选择-</option>
	                                        <option value="5">心漫画</option>
	                                        <option value="6">心视野</option>
	                                        <option value="7">心知识</option>
	                                        <option value="8">首页轮播图</option>
	                                        <option value="9">首页H5链接</option>
	                                    </select>
                          		</div>
                          		<label class="col-lg-1 control-label">疾病分类：  </label>
                          		<div class="form-group col-lg-2">
                          			<select class="form-control m-b" name="illType">
	                                        <option value="-1">-请选择-</option>
	                                        <option value="1">三高</option>
	                                        <option value="2">冠心病</option>
	                                        <option value="3">心梗</option>
	                                        <option value="4">心衰</option>
	                                    </select>
                          		</div>
                          		<div class="form-group col-lg-2">
                          			<label class="col-lg-1 control-label"></label>
                          			<button class="btn btn-success" type="submit">查的询</button>
                          		</div>
                          	</div>
                        </form>
                </div>
                <div class="panel-body">
					<table
						class="table table-striped table-bordered table-hover">
						<tr>
							<th>序号</th>
							<th>文章分类</th>
							<th>文章标题</th>
							<th>封面图片</th>
							<th>发布时间</th>
							<th>删除状态</th>
							<th>操作</th>
						</tr>
						<c:forEach var="item" items="${page.datas}" varStatus="st">
							<tr>
								<td>${st.index+1}</td>
								<td>${item.category}</td>
								<td>${item.title}</td>
								<td>
									<img width="100" height="100" src="${item.coverImageUrl}">
								</td>
								<td>${item.postTime}</td>
								<td>
									<c:if test="${item.delFlag == 0	}">未删除</c:if>
									<c:if test="${item.delFlag == 1	}">已删除</c:if>
								</td>
								<td>
									<button class="btn btn-success" onclick="detail('${ctx}/back/article/detail/${item.id}')">详情</button>
									<button class="btn btn-success" onclick="update('${ctx}/back/article/update/${item.id}')">修改</button>
									<c:if test="${item.delFlag == 0	}"><button class="btn btn-success" onclick="del('${ctx}/back/article/delete/${item.id}')">删除</button></c:if>
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
	$(function(){
		$('#startDate .input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: true,
            forceParse: true,
            calendarWeeks: true,
            autoclose: true
        });
		
		$('#endDate .input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: true,
            forceParse: true,
            calendarWeeks: true,
            autoclose: true
        });
	});
</script>
</html>