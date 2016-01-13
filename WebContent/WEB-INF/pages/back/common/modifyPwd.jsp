<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>修改密码</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>修改密码</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}">首页</a></li>
                 <li class="active">修改密码</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
			 <div class="col-lg-5">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form id="modifyForm" class="form-horizontal" action="${ctx }/back/modifyPwd" method="post">
                                <div class="form-group"><label class="col-lg-2 control-label">原始密码</label>
                                    <div class="col-lg-10"><input type="password" name="password" class="form-control"> 
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">新密码</label>
                                    <div class="col-lg-10"><input  type="password" id="newPwd" name="newPwd" class="form-control"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">密码确认</label>

                                    <div class="col-lg-10"><input type="password" name="conPwd" class="form-control"></div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-4 col-lg-10">
                                        <button class="btn btn-w-m btn-success" type="submit">提交</button>
                                        <button class="btn btn-w-m btn-default" onclick="reset()" type="button">重置</button>
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
	function reset(){
		$("input[name='password']").val("");
		$("input[name='newPwd']").val("");
		$("input[name='conPwd']").val("");
	}
	
	$(function(){
		//登录表单校验
		$("#modifyForm").validate({
			rules: {
				password: {required: true},
				newPwd: {required: true},
				conPwd:{required: true,equalTo:"#newPwd"}
			},
		    messages: {
		    	password: {required:"请输入原始密码"},
		    	newPwd: {required:"请输入新密码"},
		    	conPwd: {required:"请确认新密码",equalTo:"确认密码和新密码不一致 "}
		 	},
		 	submitHandler:function(form){
		 		form.submit();
	        }
		});
	});
</script>
</html>