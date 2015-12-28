<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${ctx }/static/images/favicon.ico">
<title>添加诊后心得</title>
<meta content="" name="keywords" />
<meta content="" name="description" />
<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>添加诊后心得</h1>
		<form id="interfaceForm" action="${ctx }/doctor/diagnose_experience/add" enctype="multipart/form-data" method="post">
			<div class="form-group">
				<label for="illType">患者类型</label> 
				<input type="text" class="form-control" id="illType" name="illType">
			</div>
			<div class="form-group">
				<label for="illDesc">病情描述</label> 
				<input type="text" class="form-control" id="illDesc" name="illDesc">
			</div>
			<div class="form-group">
				<label for="experience">诊后心得</label> 
				<input type="text" class="form-control" id="experience" name="experience">
			</div>
			<div class="form-group">
				<label for="experience">图片上传</label> 
				<input type="text" class="form-control" name="imageBase64s">
				<input type="text" class="form-control" name="imageBase64s">
			</div>
			<div class="form-group">
				<label for="isAnonymous">是否匿名</label> 
				<input type="checkbox" class="form-control" style="width: 20px;" id="isAnonymous" name="isAnonymous">
			</div>
			<button type="submit" class="btn btn-primary" onclick="save()">保存</button>
			<button type="submit" class="btn btn-primary" onclick="saveAndPost()">保存并发表</button>
			<br /> <br />
		</form>
	</div>
	<script type="text/javascript">
		function save(){
			if($("#isAnonymous").attr("checked") == true){
				alert()
			}
			
		}
	</script>
</body>
</html>
