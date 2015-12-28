<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>轮播图管理</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>添加菜单</h2>
             <ol class="breadcrumb">
                 <li><a href="${_base}/page/index.jsp">首页</a></li>
                 <li class="active">添加菜单</li>
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
                        <div class="ibox-title">
                            <h5>轮播图列表</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <form class="form-horizontal" method="post" action="${ctx }/back/other/bannerSlide/add" enctype="multipart/form-data">
                                <div class="form-group"><label class="col-lg-2 control-label">跳转类型</label>
                                    <div class="col-lg-3">
                                    	<select class="form-control m-b" name="redirectType">
	                                        <option value="0">不跳转</option>
	                                        <option>option 2</option>
	                                        <option>option 3</option>
	                                        <option>option 4</option>
	                                    </select>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">跳转地址</label>
                                    <div class="col-lg-3"><input type="text" name="redirectAddr" class="form-control"></div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">上传图片</label>
                                    <div class="col-lg-3"><input type="file" name="images" class="form-control"></div>
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">轮播图指向</label>
                                    <div class="col-lg-3">
                                    	<select class="form-control m-b" name="platform">
	                                        <option value="1">医生</option>
	                                        <option value="2">患者</option>
	                                    </select>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">排序</label>
                                    <div class="col-lg-3"><input type="text" name="sequence" class="form-control"></div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button class="btn btn-success col-lg-3" type="submit">提交</button>
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

</script>
</html>
