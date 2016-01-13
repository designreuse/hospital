<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>新增医院</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>新增医院</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}">首页</a></li>
                 <li class="active">新增医院</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
    	<form action="${ctx }/back/saveHospital" method="POST" id="hospitalForm"> 
	       <div class="row">
	       		<div class="col-sm-1"><label style="margin-top: 9px;font-size: 16px;" class="control-label">选择区域：</label></div>
		 		<div class="col-sm-2">
			 		<select class="form-control m-b" onchange="getCity()" id="pSel" name="pSel">
		 				<c:forEach items="${plist }" var="p">
		 				 <option value="${p.id }">${p.name }</option>
		 				</c:forEach>
	               	</select>
		  		</div>	
		 		<div class="col-sm-2">
			 		<select class="form-control m-b" id="citySel" name="citySel" onchange="getCounty()"><option value='-1'>-请选择-</option></select>
		  		</div>	
		 		<div class="col-sm-2">
			 		<select class="form-control m-b" id="countySel" name="countySel"><option value='-1'>-请选择-</option></select>
		  		</div>	
	       </div>
	       <div class="row">
	       		<div class="col-sm-1"><label style="margin-top: 9px;font-size: 16px;" class="control-label">医院名称：</label></div>
	       		<div class="col-sm-2">
			 		<input type="text" name="hospital" class="form-control" />
		  		</div>
	       </div>
	       <div class="row">
	       <div class="col-sm-1"></div>
	       		<div class="col-sm-2">
			 		<button style="margin-top:30px;" class="btn btn-w-m btn-success">添加医院</button>
		  		</div>
	       </div>
       </form>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
	 		type : "post",
			url : "${ctx}/back/getCity",
			async : false,
			dataType : "json",
			data : {
				p : 1
			},
			success : function(data){
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var name = data[i].name;
					var option = "<option value='"+id+"'>"+name+"</option>";
					$("#citySel").append(option);
				}
			}
	 	});
		
		$.ajax({
	 		type : "post",
			url : "${ctx}/back/getCounty",
			async : false,
			dataType : "json",
			data : {
				cid : 1
			},
			success : function(data){
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var name = data[i].name;
					var option = "<option value='"+id+"'>"+name+"</option>";
					$("#countySel").append(option);
				}
			}
	 	});
	})
	function getCity(){
		$("#citySel").html("");
		$("#countySel").html("");
		var p = $("#pSel").find("option:selected").val();
		$.ajax({
	 		type : "post",
			url : "${ctx}/back/getCity",
			async : false,
			dataType : "json",
			data : {
				p : p
			},
			success : function(data){
				$("#citySel").append("<option value='-1'>-请选择-</option>");
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var name = data[i].name;
					var option = "<option value='"+id+"'>"+name+"</option>";
					$("#citySel").append(option);
				}
			}
	 	});
	}
	function getCounty(){
		$("#countySel").html("");
		var p = $("#citySel").find("option:selected").val();
		$.ajax({
	 		type : "post",
			url : "${ctx}/back/getCounty",
			async : false,
			dataType : "json",
			data : {
				cid : p
			},
			success : function(data){
				$("#countySel").append("<option value='-1'>-请选择-</option>");
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var name = data[i].name;
					var option = "<option value='"+id+"'>"+name+"</option>";
					$("#countySel").append(option);
				}
			}
	 	});
	}
	
	$(function(){
		//登录表单校验
		$("#hospitalForm").validate({
			rules: {
				hospital: {required: true}
			},
		    messages: {
		    	hospital: {required:"请输入医院名称"}
		 	},
		 	submitHandler:function(form){
		 		form.submit();
	        }
		});
	});
</script>
</html>