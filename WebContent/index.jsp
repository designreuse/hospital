<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>后台管理系统</title>
<style type="text/css">
	body{background: #ffffff}
</style>
</head>
<body>
<div>
	<div class="row">
	    <div class="col-lg-4"></div>
	    <div class="col-lg-4">
	    	<div class="ibox float-e-margins" style="margin-top: 130px;">
                <div >
                    <h2 class="text-center" style="margin-bottom: 30px;">管理员登录</h2>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" action="${ctx }/back/index" method="get">
                        <div class="form-group"><label class="col-lg-2 control-label" style="height: 50px;line-height: 50px;">用户名</label>
                            <div class="col-lg-10">
                            	<input type="username" style="height: 50px;" placeholder="请输入用户名" class="form-control">
                            </div>
                        </div>
                        <div class="form-group"><label class="col-lg-2 control-label" style="height: 50px;line-height: 50px;">密码</label>
                            <div class="col-lg-10">
                            	<input type="password" style="height: 50px;" placeholder="请输入密码" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-4 col-lg-10">
                                <button class="btn btn-w-m btn-success" style="height: 45px;" type="submit">登录</button>
                                <button class="btn btn-w-m btn-default" style="height: 45px;" type="submit">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
	    </div>
	    <div class="col-lg-4"></div>
    </div>
    <div class="row" style="">
	    <div style="background-color: #dddddd;text-align: center;bottom: 0px;position: absolute;font-size: 16px;font-weight: bold;width:100% ;height: 40px;line-height: 40px;">
	    	@Copyright 1999-2015 www.ixinzang.com All rights reserved 版权所有 北京医康世纪科技有限公司     京ICP备14049723号-1 京公网安备11011402000145号
	    </div>
    </div>
</div>
<script type="text/javascript">
	
</script>
</body>
</html>