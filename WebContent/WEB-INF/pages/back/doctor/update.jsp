<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生详情</title>
<style type="text/css">
	table{
		width: 80%;
    	margin: 0 0 0 10px;	
    	font-family: verdana,arial,sans-serif;
		font-size:16px;
		color:#333333;
		border-collapse: collapse;
	}
	table td {
		padding: 8px;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>医生详情</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">医生详情</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
          	<form action="${ctx }/back/doctor/update" method="post">
          		<input type="hidden" name="id" value="${d.id }"/>
	          	<table>
	          		<tr>
	          			<td class="text-right">注册手机号:</td>
	          			<td>${d.mobile }</td>
	          			<td class="text-right">姓名:</td>
	          			<td><input type="text" name="name" value="${d.name }"/></td>
	          			<td class="text-right">性别:</td>
	          			<td>
	          				<select name="agender">
	          					<option value="1" <c:if test="${d.agender == 1 }">selected</c:if>>男</option>
	          					<option value="2" <c:if test="${d.agender == 2 }">selected</c:if>>女</option>
	          				</select>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td class="text-right">出生日期:</td>
	          			<td>${d.birthday }</td>
	          			<td class="text-right">医生积分:</td>
	          			<td>${d.score }</td>
	          			<td class="text-right">医院:</td>
	          			<td><input type="text" name="hospital" value="${d.hospital }"/></td>
	          		</tr>
	          		<tr>
	          			<td class="text-right">科室:</td>
	          			<td>
	          			${d.department }
	          			</td>
	          			<td class="text-right">所在地:</td>
	          			<td>${d.address }</td>
	          			<td class="text-right">技术职称:</td>
	          			<td>${d.technicalTitle }</td>
	          		</tr>
	          		<tr>
	          			<td class="text-right">教学职称:</td>
	          			<td>${d.teachingTitle }</td>
	          			<td class="text-right">注册日期:</td>
	          			<td>${d.registerTime }</td>
	          			<td class="text-right">密码:</td>
	          			<td>已加密</td>
	          		</tr>
	          		<tr>
	          			<td class="text-right">医生认证:</td>
	          			<td>
	          				<c:if test="${d.verifyed == 0 }">未通过</c:if>
	          				<c:if test="${d.verifyed == 1 }">通过</c:if>
	          			</td>
	          			<td colspan="4"></td>
	          		</tr>
	          		<tr>
	          			<td></td>
	          			<td></td>
	          			<td></td>
	          			<td></td>
	          			<td></td>
	          			<td colspan="1">
		          			<button style="width: 130px" class="btn btn-success pull-left" type="submit">保存</button>
		          			<button style="width: 130px" class="btn btn-default pull-right" onclick="cancel()">取消</button>
	          			</td>
	          		</tr>
	          	</table>
          	</form>
           </div>
       </div>
    </div>
</div>
</body>
<script type="text/javascript">
	function cancel(){
		window.location.href = "${ctx}/back/doctor/update/"+id;
	}
</script>
</html>