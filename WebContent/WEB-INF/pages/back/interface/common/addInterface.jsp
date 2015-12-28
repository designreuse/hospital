<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>医生管理</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>添加接口分类</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">添加接口分类</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    		<button  class="btn btn-primary pull-right" onclick="javascript:window.location.href='${_base}/menu/more/guessLike?code=skill'">返回</button>
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
          <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="${ctx }/back/interface/add" method="post" class="form-horizontal" role="form" id="iItemForm">
								<div class="form-group">
									<label for="itemDesc" class="col-lg-3 control-label">接口名称：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" name="itemDesc" id="itemDesc" placeholder="请输入接口名称">
									</div>
								</div>
								<div class="form-group">
									<label for="action" class="col-lg-3 control-label">接口地址：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" name="action" id="action" placeholder="请输入接口地址">
									</div>
								</div>
								<div class="form-group">
									<label for="errorDesc" class="col-lg-3 control-label">错误描述：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="8" name="errorDesc" id="errorDesc" placeholder="请输入错误描述"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="successResult" class="col-lg-3 control-label">正常返回示例：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="8" name="successResult" id="successResult" placeholder="请输入正常返回示例"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="errorResult" class="col-lg-3 control-label">错误返回示例：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="5" name="errorResult" id="errorResult" placeholder="请输入错误返回示例"></textarea>
									</div>
								</div>
								<div class="form-group" id="div_field">
									<label for="method" class="col-lg-3 control-label">
										所属模块：</label>
									<div class="col-lg-8">
										<select class="form-control" name="categoryId" id="categoryId">
												<c:forEach items="${clist }" var="c">
													<option value="${c.id }">${c.name }</option>
												</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="status" class="col-lg-3 control-label">是否完成：</label>
									<div class="col-lg-8">
										<select class="form-control" name="status" id="status">
											<option value="1" selected="">完成</option>
											<option value="0">未完成</option>
											<option value="2">保留</option>									
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="method" class="col-lg-3 control-label">请求类型：</label>
									<div class="col-lg-8">
										<select class="form-control" name="method" id="method">
											<option value="post">post</option>
											<option value="get">get</option>
											<option value="multipart">multipart</option>
										</select>
									</div>
								</div>
								
								<div class="form-group" id="div_operate">
									<div class="col-lg-offset-4 col-lg-8">
										<button type="submit" id="btnSave" class="btn btn-success">保存</button>
										<button type="button" id="btnBack" class="btn btn-primary" url="/yuenr/admin/iItem/list">取消</button>
										<button type="button" onclick="addArg()" class="btn btn-warning">添加参数</button>
										<button type="button" onclick="addFiledDescn()" class="btn btn-danger">添加说明</button>
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
		function addArg() {
			var html = "<div class='form-group'>"+
			"<label for='method' class='col-lg-3 control-label'>"+
				"参数列：</label>"+
			"<div class='col-lg-2'>"+
				"<input type='text' class='input-sm form-control' name='label'"+
					"id='label' placeholder='请输入描述'>"+
			"</div>"+
			"<div class='col-lg-2'>"+
				"<input type='text' class='input-sm form-control' name='name'"+
					"id='name' placeholder='请输入名称'>"+
			"</div>"+
			"<div class='col-lg-2'>"+
				"<select class='form-control input-sm' name='isRest' id='isRest'>"+
					"<option value='0'>Form表单参数</option>"+
					"<option value='1'>URL路径参数</option>"+
				"</select>"+
			"</div>"+
			"<div class='col-lg-1'>"+
				"<select class='form-control input-sm' name='argType' id='argType'>"+
					"<option value='int'>int</option>"+
					"<option value='string'>string</option>"+
				"</select>"+
			"</div>"+
			"<div class='col-lg-1'>"+
				"<select class='form-control input-sm' name='isRequired' id='isRequired'>"+
					"<option value='1'>必填</option>"+
					"<option value='0'>非必填</option>"+
				"</select>"+
			"</div>"+
			"<button type='button' class='btn btn-danger btn-sm' id='btnAdd' onclick='delArg(this)'>删除</button>"+
		"</div>";
			$('#div_operate').before(html);
		}
		
		function addFiledDescn(){
			var html = "<div class='form-group'>"+
			"<label for='fieldName' class='col-lg-3 control-label'>"+
				"返回参数：</label>"+
			"<div class='col-lg-2'>"+
				"<input type='text' class='input-sm form-control' name='fieldName'"+
					"id='fieldName' placeholder='字段名称'>"+
			"</div>"+
			"<div class='col-lg-4'>"+
				"<input type='text' class='input-sm form-control' name='fieldDescn'"+
					"id='fieldDescn' placeholder='字段描述'>"+
			"</div>"+
			"<div class='col-lg-2'>"+
				"<select class='form-control input-sm' name='fieldType' id='fieldType'>"+
					"<option value='int'>int</option>"+
					"<option value='string'>string</option>"+
					"<option value='boolean'>boolean</option>"+
					"<option value='float'>float</option>"+
					"<option value='double'>double</option>"+
					"<option value='json array'>json array</option>"+
					"<option value='json object'>json object</option>"+
				"</select>"+
			"</div>"+
			"<button type='button' class='btn btn-danger btn-sm' id='btnAdd' onclick='delArg(this)'>删除</button>"+
		"</div>";
			$('#div_field').before(html);
			
		}
		
		
		function delArg(obj){
			$(obj).parent().remove();
		}
	</script>
</html>