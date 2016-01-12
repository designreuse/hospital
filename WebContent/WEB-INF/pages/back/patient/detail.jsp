<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>患者详情</title>
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
             <h2>患者列表</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">患者详情</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
				<div class="ibox float-e-margins">
    				<div class="pdiv">
    					<span>用户名：</span>
    					<span>${p.username }</span>
	    				<span>姓名</span>
	    				<span>${p.name }</span>
						<span>性别：</span>
						<span>${p.agender }</span>
    				</div>
					<div class="pdiv">
						 体重${p.weight }
						 手机号${p.mobile }
						 出生日期：${p.birthday }
					</div>
		
					<div class="pdiv">
						 注册日期${p.registerTime }
 						患者积分${p.score }
					</div>
 
					<div class="pdiv">
						 病史备注}
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