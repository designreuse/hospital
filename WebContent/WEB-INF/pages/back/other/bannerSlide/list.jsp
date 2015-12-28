<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>轮播图管理</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>轮播图</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">轮播图</li>
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
						<button type="button" class="btn btn-w-m btn-success" id="addBtn">新增</button>
					</div>
					
				</div>
            </div>
            <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>跳转类型</th>
                                    <th>跳转地址</th>
                                    <th>描述</th>
                                    <th>指向平台</th>
                                    <th>轮播图</th>
                                </tr>
                                </thead>
                                <tbody>
								<c:forEach items="${list }" var="item">
									<tr>
	                                    <td>${item.id }</td>
	                                    <td>${item.redirectType }</td>
	                                    <td>${item.redirectAddr }</td>
	                                    <td>${item.description }</td>
	                                    <td>${item.platform }</td>
	                                    <td>
	                                    	<img width="50" height="50" src="${ctx }${item.picUrl }">
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
	$(function(){
		$("#addBtn").on("click",function(){
			window.location.href = "${ctx}/back/other/bannerSlide/view/add";
		});
		
	});
</script>
</html>
