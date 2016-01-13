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
			<div class="col-lg-12">
                   <div class="ibox float-e-margins">
                       <div class="ibox-content">
                           <form class="form-horizontal" id="articleform" method="post" action="${ctx }/back/manager/add" enctype="multipart/form-data">
                               <div class="form-group"><label class="col-lg-2 control-label">用户名</label>
                                   <div class="col-lg-6"><input type="text" name="username" class="form-control"></div>
                               </div>
                               <div class="form-group"><label class="col-lg-2 control-label">密码</label>
                                   <div class="col-lg-6"><input type="password" id="password" name="password" class="form-control"></div>
                               </div>
                               <div class="form-group"><label class="col-lg-2 control-label">确认密码</label>
                                   <div class="col-lg-6"><input type="password" name="cfmPwd" class="form-control"></div>
                               </div>
                               <div class="form-group"><label class="col-lg-2 control-label">姓名</label>
                                   <div class="col-lg-6"><input type="text" name="name" class="form-control"></div>
                               </div>
                               <div class="form-group"><label class="col-lg-2 control-label">头像</label>
                                   <div class="col-lg-6"><input type="file" name="profileImageUrl" class="form-control"></div>
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

$(function(){
	//登录表单校验
	$("#articleform").validate({
		rules: {
			username: {required: true},
			password: {required: true},
			cfmPwd: {required: true,equalTo:"#password"}
		},
	    messages: {
	    	username: {required:"请输入用户名"},
			password: {required:"请输入密码"},
			cfmPwd: {required:"请输入确认密码",equalTo:"确认密码和密码不一致"}
	 	},
	 	submitHandler:function(form){
	 		form.submit();
        }
	});
});
</script>
</html>