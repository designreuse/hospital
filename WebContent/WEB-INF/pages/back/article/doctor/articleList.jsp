<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>文章上传（医生）</title>
<style type="text/css">
	table tr td{text-align: center;}
</style>
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
                        <form class="form-horizontal" id="queryForm" method="GET" action="${ctx}/back/article/list/1">
                       		<div class="col-lg-12">
                       			<label class="col-lg-1 control-label">开始时间：</label>
	                          	<div class="form-group col-lg-2">
	                                <div class="input-group">
	                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                                </div>
	                            </div>
	                          	<label class="col-lg-1 control-label">结束时间：</label>
	                          	<div class="form-group col-lg-2">
	                                <div class="input-group">
	                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                    <input type="text" class="form-control" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
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
	                                	  <option value="-1">全部</option>
	                                      <option value="1">首页轮播图</option>
	                                      <option value="2">医疗动态</option>
	                                      <option value="3">轻松一刻</option>
	                                      <option value="4">首页H6连接</option>
	                            	 </select>
                          		</div>
                          		<div class="form-group col-lg-2">
                          			<label class="col-lg-2 control-label"></label>
                          			<button class="btn btn-w-m btn-success" type="submit">查询</button>
                          		</div>
                          	</div>
                        </form>
                </div>
                <div class="panel-body">
                	<jsp:include page="/WEB-INF/pages/context/pagination.jsp">
						<jsp:param value="${ctx}/back/article/list/1" name="url" />
					</jsp:include>
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
								<td>
									<c:if test="${item.category == 1}">首页轮播图</c:if>
									<c:if test="${item.category == 2}">医疗动态</c:if>
									<c:if test="${item.category == 3}">轻松一刻</c:if>
									<c:if test="${item.category == 4}">首页H5链接</c:if>
								</td>
								<td>${item.title}</td>
								<td>
									<img width="200" height="100" src="${item.coverImageUrl}">
								</td>
								<td>${item.postTime}</td>
								<td>
									<c:if test="${item.delFlag == 0	}">未删除</c:if>
									<c:if test="${item.delFlag == 1	}">已删除</c:if>
								</td>
								<td>
									<button class="btn btn-success" onclick="detail('${ctx}/back/article/detail/${item.id}')">详情</button>
									<c:if test="${item.delFlag == 0	}">
										<button class="btn btn-success" onclick="update('${ctx}/back/article/update/${item.id}')">修改</button>
										<button class="btn btn-danger" onclick="del('${ctx}/back/article/delete/${item.id}')">删除</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
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