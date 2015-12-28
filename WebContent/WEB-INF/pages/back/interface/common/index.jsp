<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${ctx }/static/images/favicon.ico">
<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx }/static/js/jquery-1.11.2.js"></script>
	<script src="${ctx }/static/js/bootstrap.min.js"></script>
<title>测试工具</title>
<style type="text/css">
.bg-white {
	background-color: #fff;
}

.min-height {
	min-height: 400px;
}
.nav > li.active {
   border-left:none;
}

body {
	padding-top: 50px;
}
.container{width:100%!important}
</style>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${ctx}/back/interface/i">测试工具</a></li>
					<li><a href="${ctx}/i/getApiList">接口目录</a></li>
					<li><a href="${ctx}/i/errorCode">错误码说明</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h1>接口测试工具</h1>
		<div class="row">
			<div class="col-md-4">
			<p>请选择测试接口：</p>
				<div class="well">
					<div class="form-group">
						<label for="categoryID">分类名称：</label> <select class="input-sm form-control" style="height: 40px;font-size: 20px;"
							id="categoryID" onchange="checkCategory(this.value);">
							<c:forEach var="category" items="${iCategories }">
								<option value='${category.id }'>${category.name }</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="itemID">接口名称：</label> <select class="input-sm form-control" style="height: 40px;font-size: 20px;"
							id="itemID" onchange="checkItem(this.value);">
							<option value='0'>请选择接口名称</option>
							<c:forEach var="item" items="${iItems }">
								<option value='${item.id }'>${item.name }</option>
							</c:forEach>

						</select>
					</div>

					<hr />

					<div id="iForm"></div>
				</div>

			</div>
			<div class="col-md-4">
				<p>返回的内容：</p>
				<textarea class="well bg-white" cols="70" rows="23"
					 id="result"></textarea>
			</div>
			<div class="col-md-4">
				<p>返回值描述：</p>
				<table style="text-align: center;" class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr class="info">
							<td>字段</td>
							<td>类型</td>
							<td>说明</td>
						</tr>
					</thead>
					<tbody id="fieldDescn">
						<tr align="center">
							<td colspan="3">暂无数据</td>
						</tr>
					</tbody>
				</table>
				<p>正常返回：</p>
				<textarea class="well bg-white" draggable="false" cols="68" rows="10"
					 id="successResult"></textarea>
				<p>错误返回：</p>
				<textarea class="well bg-white" draggable="false" cols="68" rows="4"
					 id="errorResult"></textarea>
			</div>
		</div>

	</div>
	<script src="${ctx }/static/js/interface.js" type="text/javascript"></script>
	<input type="hidden" id="ctx" name="ctx" value="${ctx }" />
</body>
</html>