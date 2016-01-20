<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>修改患者资料</title>
</head>
<style type="text/css">
	.pdiv{margin-left: 30px;margin-top:20px;font-size: 17px;}
</style>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>修改患者资料</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">修改患者资料</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated">
		<div class="ibox float-e-margins white-bg" >
		<form action="${ctx }/back/patient/updatePatient" method="post">
		<input type="hidden" name="userId" value="${p.userId }"/>
		<div class="row show-grid">
	         <div class="col-md-1 text-right">用户名：</div>
	         <div class="col-md-1">
	         	${p.username }
	         </div>
	          <div class="col-md-1 text-right">姓名：</div>
	         <div class="col-md-1">
	         	<input type="text" class="form-control" name="name" value="${p.name }" />
	         </div>
	         <div class="col-md-1 text-right">性别：</div>
	         <div class="col-md-1">
	         	<input type="radio" name="agender" value="1">男
	         	<input type="radio" name="agender" value="2">女
	         </div>
	     </div>
		<div class="row show-grid">
	         <div class="col-md-1 text-right">体重：</div>
	         <div class="col-md-1">
	         	<input type="text" class="form-control" name="weight" value="${p.weight }" />
	         </div>
	          <div class="col-md-1 text-right">手机号：</div>
	         <div class="col-md-2">
	        	 <input type="text" class="form-control" name="mobile" value="${p.mobile }" />
	         </div>
	         <div class="col-md-1 text-right"> 出生日期：</div>
	         <div class="col-md-2">
	         	<input type="text" class="form-control" name="birthday" value="${p.birthday }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	         </div>
	     </div>
		<div class="row show-grid">
	         <div class="col-md-1 text-right"> 患者积分</div>
	         <div class="col-md-1">
	         	${p.score }
	         </div>
	         <div class="col-md-1 text-right"> 注册日期：</div>
	         <div class="col-md-2">
	         	${p.registerTime }
	         </div>
	     </div>
		<div class="row show-grid">
	         <div class="col-md-1 text-right">病史备注</div>
	         <div class="col-md-5">
	         <input type="text" class="form-control" name="illProfile" value="${p.illProfile }"s/>
	         </div>
	     </div>
		<div class="row show-grid">
		 <div class="col-md-1 text-right"></div>
	         <div class="col-md-7">
	         	<button class="btn btn-w-m  btn-success" type="submit" >保存</button>
	         	<button class="btn btn-w-m  btn-default" type="button" onclick="javascript:history.go(-1)">取消</button>
	         </div>
	     </div>
        </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
	function updatePatient(id){
		window.location.href = "${ctx}/back/patient/updatePatientView?id="+id;
		
	}
	
	$(function(){
		if('${p.agender }' == '男'){
			$("input[type='radio'][value='1']").attr("checked",'checked');
		}else{
			$("input[type='radio'][value='2']").attr("checked",'checked');
		}
        
	});
</script>
</html>