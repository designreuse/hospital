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
          <div class="col-lg-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal" action="${ctx }/back/interface/category/add" method="POST">
                            <div class="form-group"><label class="col-lg-2 control-label">接口分类名称</label>
                                <div class="col-lg-7"><input type="text" class="form-control" name="name"></div>
                            </div>
                            <div class="form-group"><label class="col-lg-2 control-label">接口分类类型</label>
                                <div class="col-lg-7">
                                	<select class="form-control m-b" name="type">
                                		<option value="1">医生</option>
                                		<option value="2">患者</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-7 col-lg-6">
                                    <button class="btn btn-success" type="submit">提交</button>
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
		
	});
</script>
</html>