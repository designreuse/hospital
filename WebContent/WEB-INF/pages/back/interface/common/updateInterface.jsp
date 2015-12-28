<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>

<div class="container" style="width:100%!important;">
	<div class="panel panel-default">
		
		<div class="panel-body">
		<div class="panel-heading">
			<ol class="breadcrumb" id="breadcrumb">
				<button  class="btn btn-primary pull-right" onclick="javascript:history.go(-1)">返回</button>
			</ol>
		</div>
			<div class="container">
				<div class="row">
					<section>
						<div class="col-lg-12">
							<form action="${ctx}/back/interface/update" method="post" class="form-horizontal" role="form" id="iItemForm">
								<input type="hidden" name="id" value="${iItem.id}">
								<div class="page-header">
									<h4>接口信息</h4>
								</div>
								<div class="form-group">
									<label for="itemDesc" class="col-lg-3 control-label">接口名称：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" name="itemDesc"
											id="itemDesc" placeholder="请输入接口名称"
											value="${iItem.itemDesc }">
									</div>
								</div>

								<div class="form-group">
									<label for="action" class="col-lg-3 control-label">接口地址：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" name="action"
											id="action" placeholder="请输入接口地址" value="${iItem.name }">
									</div>
								</div>
								<div class="form-group">
									<label for="errorDesc" class="col-lg-3 control-label">错误描述：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="8" name="errorDesc"
											id="errorDesc" placeholder="请输入错误描述">${iItem.errorDesc }</textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="successResult" class="col-lg-3 control-label">正常返回示例：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="8" name="successResult"
											id="successResult" placeholder="请输入正常返回示例">${iItem.successResult}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="errorResult" class="col-lg-3 control-label">错误返回示例：</label>
									<div class="col-lg-8">
										<textarea class="form-control" rows="5" name="errorResult"
											id="errorResult" placeholder="请输入错误返回示例">${iItem.errorResult}</textarea>
									</div>
								</div>
								<c:forEach items="${fields}" var="f">
									<div class="form-group">
										<label for="fieldName" class="col-lg-3 control-label">
											返回参数：</label>
										<div class="col-lg-2">
											<input type="text" class="input-sm form-control" name="fieldName"
												id="fieldName" placeholder="字段名称" value="${f.fieldName}">
										</div>
										<div class="col-lg-4">
											<input type="text" class="input-sm form-control" name="fieldDescn"
												id="fieldDescn" placeholder="字段描述" value="${f.fieldDescn }">
										</div>
										<div class="col-lg-2">
											<select class="form-control input-sm" name="fieldType"
												id="fieldType">
												<option value="int"
													<c:if test="${f.fieldType eq 'int'}">selected</c:if>>int</option>
												<option value="string"
													<c:if test="${f.fieldType eq 'string'}">selected</c:if>>string</option>
												<option value="boolean"
													<c:if test="${f.fieldType eq 'boolean'}">selected</c:if>>boolean</option>
												<option value="float"
													<c:if test="${f.fieldType eq 'float'}">selected</c:if>>float</option>
												<option value="double"
													<c:if test="${f.fieldType eq 'double'}">selected</c:if>>double</option>
												<option value="json array"
													<c:if test="${f.fieldType eq 'json array'}">selected</c:if>>json array</option>
												<option value="json object"
													<c:if test="${f.fieldType eq 'json object'}">selected</c:if>>json object</option>
											</select>
										</div>
										<button type="button" class="btn btn-danger btn-sm"
											id="btnAdd" onclick="delArg(this)">删除</button>
									</div>

								</c:forEach>
								<div class="form-group" id="div_field">
									<label for="method" class="col-lg-3 control-label">
										所属模块：</label>
									<div class="col-lg-8">
										<select class="form-control" name="categoryId"
											id="categoryId">
											<c:forEach var="iCategory" items="${iCategories}">
												<option value="${iCategory.id }"
													<c:if test="${iItem.categoryId == iCategory.id}">selected</c:if>>${iCategory.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="status" class="col-lg-3 control-label">是否完成：</label>
									<div class="col-lg-8">
										<select class="form-control" name="status"
											id="status">
											<option value="1"
												<c:if test="${iItem.status eq 1}">selected</c:if>>完成</option>
											<option value="0"
												<c:if test="${iItem.status eq 0}">selected</c:if>>未完成</option>	
											<option value="2"
												<c:if test="${iItem.status eq 2}">selected</c:if>>保留</option>								
										</select>
									</div>
								</div>
								<div class="form-group">

									<label for="method" class="col-lg-3 control-label">请求类型：</label>
									<div class="col-lg-8">
										<select class="form-control" name="method" id="method">
											<option value="post"
												<c:if test="${iItem.method eq 'post'}">selected</c:if>>post</option>
											<option value="get"
												<c:if test="${iItem.method eq 'get'}">selected</c:if>>get</option>
											<option value="multipart"
												<c:if test="${iItem.method eq 'multipart'}">selected</c:if>>multipart</option>
										</select>
									</div>
								</div>
								<c:forEach items="${params}" var="p">
									<div class="form-group">
										<label for="method" class="col-lg-3 control-label">
											参数列：</label>
										<div class="col-lg-2">
											<input type="text" class="input-sm form-control" name="label"
												id="label" placeholder="请输入描述" value="${p.label}">
										</div>
										<div class="col-lg-2">
											<input type="text" class="input-sm form-control" name="name"
												id="name" placeholder="请输入名称" value="${p.name }">
										</div>
										<div class='col-lg-2'>
											<select class='form-control input-sm' name='isRest' id='isRest'>
												<option value='0' <c:if test="${p.isRest eq '1'}">selected</c:if>>Form表单参数</option>
												<option value='1' <c:if test="${p.isRest eq '1'}">selected</c:if>>URL路径参数</option>
											</select>
										</div>
										<div class="col-lg-1">
											<select class="form-control input-sm" name="argType"
												id="argType">
												<option value="int"
													<c:if test="${p.argType eq 'int'}">selected</c:if>>int</option>
												<option value="string"
													<c:if test="${p.argType eq 'string'}">selected</c:if>>string</option>
											</select>
										</div>
										<div class="col-lg-1">
											<select class="form-control input-sm" name="isRequired"
												id="isRequired">
												<option value="1"
													<c:if test="${p.isRequired == 1}">selected</c:if>>必填</option>
												<option value="0"
													<c:if test="${p.isRequired == 0}">selected</c:if>>非必填</option>
											</select>
										</div>
										<button type="button" class="btn btn-danger btn-sm"
											id="btnAdd" onclick="delArg(this)">删除</button>
									</div>

								</c:forEach>
								<div class="form-group" id="div_operate">
									<div class="col-lg-offset-4 col-lg-8">
										<button type="submit" id="btnSave" class="btn btn-success">保存</button>
										<button type="button" id="btnBack" class="btn btn-primary"
											url="${ctx}/admin/iItem/list">取消</button>
										<button type="button" onclick="addArg()"
											class="btn btn-warning">添加参数</button>
										<button type="button" onclick="addFiledDescn()"
											class="btn btn-danger">添加说明</button>
									</div>
								</div>
							</form>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function addArg() {
			var html = "<div class='form-group'>"
					+ "<label for='method' class='col-lg-3 control-label'>"
					+ "参数列：</label>"
					+ "<div class='col-lg-2'>"
					+ "<input type='text' class='input-sm form-control' name='label'"+
				"id='label' placeholder='请输入描述'>"
					+ "</div>"
					+ "<div class='col-lg-2'>"
					+ "<input type='text' class='input-sm form-control' name='name'"+
				"id='name' placeholder='请输入名称'>"
					+ "</div>"
					+"<div class='col-lg-2'>"
					+"<select class='form-control input-sm' name='isRest' id='isRest'>"
					+"<option value='0'>Form表单参数</option>"
					+"<option value='1'>URL路径参数</option>"
					+"</select>"
					+"</div>"
					+ "<div class='col-lg-1'>"
					+ "<select class='form-control input-sm' name='argType' id='argType'>"
					+ "<option value='int'>int</option>"
					+ "<option value='string'>string</option>"
					+ "</select>"
					+ "</div>"
					+ "<div class='col-lg-1'>"
					+ "<select class='form-control input-sm' name='isRequired' id='isRequired'>"
					+ "<option value='1'>必填</option>"
					+ "<option value='0'>非必填</option>"
					+ "</select>"
					+ "</div>"
					+ "<button type='button' class='btn btn-danger btn-sm' id='btnAdd' onclick='delArg(this)'>删除</button>"
					+ "</div>";
			$('#div_operate').before(html);
		}

		function addFiledDescn() {
			var html = "<div class='form-group'>"
					+ "<label for='fieldName' class='col-lg-3 control-label'>"
					+ "返回参数：</label>"
					+ "<div class='col-lg-2'>"
					+ "<input type='text' class='input-sm form-control' name='fieldName'"+
				"id='fieldName' placeholder='字段名称'>"
					+ "</div>"
					+ "<div class='col-lg-4'>"
					+ "<input type='text' class='input-sm form-control' name='fieldDescn'"+
				"id='fieldDescn' placeholder='字段描述'>"
					+ "</div>"
					+ "<div class='col-lg-2'>"
					+ "<select class='form-control input-sm' name='fieldType' id='fieldType'>"
					+ "<option value='int'>int</option>"
					+ "<option value='string'>string</option>"
					+ "<option value='boolean'>boolean</option>"
					+ "<option value='float'>float</option>"
					+ "<option value='double'>double</option>"
					+ "<option value='json array'>json array</option>"
					+ "<option value='json object'>json object</option>"
					+ "</select>"
					+ "</div>"
					+ "<button type='button' class='btn btn-danger btn-sm' id='btnAdd' onclick='delArg(this)'>删除</button>"
					+ "</div>";
			$('#div_field').before(html);

		}

		function delArg(obj) {
			$(obj).parent().remove();
		}
	</script>
</div>