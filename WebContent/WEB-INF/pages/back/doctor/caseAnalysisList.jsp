</html><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>病例精析列表</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>病例精析列表</h2>
         </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th width="5%">医生姓名</th>
                                    <th width="5%">专家类别</th>
                                    <th width="10%">医院</th>
                                    <th width="10%">标题</th>
                                    <th width="5%">病历图片</th>
<!--                                     <th width="10%">解读病历</th> -->
                                    <th width="10%">发表时间</th>
                                    <th width="10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${page.datas }" var="item">
                                		<tr>
	                                    	<td>${item.doctorName }</td>
		                                    <td>
		                                    	<c:if test="${item.eliteType == 1}">金牌专家</c:if>
		                                    	<c:if test="${item.eliteType == 2}">普通专家</c:if>
		                                    </td>
		                                    <td>${item.hospital }</td>
		                                    <td>${item.title }</td>
		                                    <td>
		                                    	<img width="200" height="100" src="${item.illCaseImage }">
		                                    </td>
<%-- 		                                    <td>${item.analysis }</td> --%>
		                                    <td>${item.postTime }</td>
		                                    <td>
		                                    	<button  class="btn btn-success" onclick="detail('${item.id}')">详情</button>
		                                    	<button  class="btn btn-success" onclick="update('${item.id}')">修改</button>
		                                    	<button  class="btn btn-danger" onclick="del('${item.id}')">删除</button>
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
	function detail(id){
		window.location.href = "${ctx}/back/doctor/caseAnalysis/detail?id="+id;
	}
	function update(id){
		window.location.href = "${ctx}/back/doctor/caseAnalysis/upateView?id="+id;
	}
	function del(id){
		window.location.href = "${ctx}/back/doctor/caseAnalysis/del?id="+id;
	}
</script>
</html>