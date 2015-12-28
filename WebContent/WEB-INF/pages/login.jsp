<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/context/common.jsp"%>
<html>
<head>
    <title>首頁</title>
</head>
    <body>
        <div id="wrapper">
	        <div class="row" style="margin-top: 10%">
	        	<div class="col-lg-4"></div>
	        	<div class="col-lg-4">
					<div class="ibox float-e-margins">
						<div class="ibox-title" >
							<div style="font-size: 19px;font-weight: bold;text-align: center;">管理员登陆</div>
                            <div class="ibox-tools"></div>
                        </div>
                        <div class="ibox-content">
                            <form class="form-horizontal">
                                <div class="form-group"><label class="col-lg-2 control-label">用户名</label>
                                    <div class="col-lg-8"><input type="email" placeholder="请输入用户名" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">密码</label>
                                    <div class="col-lg-8"><input type="password" placeholder="请输入密码" class="form-control"></div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-6">
                                        <button style="float:left;width: 120px;" class="btn btn-primary btn-lg" type="submit">登录</button>
                                        <button style="float:right;width: 120px;" class="btn btn-lg btn-default" type="button">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>	        	
	        	</div>
	        	<div class="col-lg-4"></div>
	        </div>
        </div>
	</body>
</html>