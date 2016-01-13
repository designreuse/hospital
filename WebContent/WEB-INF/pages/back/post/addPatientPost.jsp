<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>新建患者帖子</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/back/common/head.jsp"%>
<%@ include file="/WEB-INF/pages/back/common/menu.jsp"%>
<div id="page-wrapper" class="gray-bg">
    <div class="row wrapper border-bottom white-bg page-heading">
         <div class="col-lg-10">
             <h2>新建患者帖子</h2>
             <ol class="breadcrumb">
                 <li><a href="${ctx}/back/home">首页</a></li>
                 <li class="active">新建患者帖子</li>
             </ol>
         </div>
         <div class="col-lg-2 pull-right" style="margin-top: 30px;">
    	</div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        	<div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form class="form-horizontal">
                                <div class="form-group"><label class="col-lg-2 control-label">新建时间</label>
                                    <div class="col-lg-4">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    	<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control"> 
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">Password</label>

                                    <div class="col-lg-4"><input type="password" placeholder="Password" class="form-control"></div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button class="btn btn-sm btn-white" type="submit">Sign in</button>
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