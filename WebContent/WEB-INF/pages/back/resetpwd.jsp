<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>添加管理员</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>添加管理员</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">添加管理员</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
			<div class="col-lg-5">
                   <div class="ibox float-e-margins">
                       <div class="ibox-content">
                           <form class="form-horizontal" id="articleform" method="post" action="${ctx }/back/manager/updatePwd">
                          	<input type="hidden" name="id" value="${id }" />
                               <div class="form-group"><label class="col-lg-2 control-label">原始密码</label>
                                   <div class="col-lg-6"><input type="text" name="oldPwd" class="form-control"></div>
                               </div>
                               <div class="form-group"><label class="col-lg-2 control-label">新密码</label>
                                   <div class="col-lg-6"><input type="password" name="password" class="form-control"></div>
                               </div>
                               <div class="form-group">
                                   <div class="col-lg-offset-2 col-lg-10">
                                       <button class="btn btn-success col-lg-2" type="submit" id="btnPost">提交</button>
                                   </div>
                               </div>
                           </form>
                       </div>
                   </div>
          		</div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
</script>
</html>