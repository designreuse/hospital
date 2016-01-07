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
				<button  class="btn btn-primary pull-right" onclick="addManager()">返回</button>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
	function addManager(){
		window.location.href = "${ctx}/back/manager/add/view";
	}
</script>
</html>